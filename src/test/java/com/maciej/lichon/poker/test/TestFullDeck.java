package com.maciej.lichon.poker.test;

import com.maciej.lichon.poker.domain.Hand;
import com.maciej.lichon.poker.domain.Settings;
import com.maciej.lichon.poker.domain.deck.Card;
import com.maciej.lichon.poker.logic.CardComparator;
import com.maciej.lichon.poker.domain.deck.CardFactory;
import com.maciej.lichon.poker.domain.deck.CardNumber;
import com.maciej.lichon.poker.domain.deck.CardSuit;
import com.maciej.lichon.poker.domain.deck.FullDeck;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test class checking consistency of the deck and hand classes
 *
 * @author mlichon
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:conf/simple-job-launcher-context.xml"})
public class TestFullDeck {

    @Test
    @Repeat(20)
    public void testDrawAllCardsAtRandom() {
        Settings settings = new Settings();
        CardFactory cardFactory = new CardFactory();
        FullDeck deck = new FullDeck(cardFactory, settings);

        for (int cnt = 0; cnt < FullDeck.DECK_SIZE; cnt++) {
            Assert.assertNotEquals(deck.drawFromDeck(), null);
        }
    }

    @Test
    @Repeat(20)
    public void testDrawSpecificCardOneTime() {
        Settings settings = new Settings();
        CardFactory cardFactory = new CardFactory();
        FullDeck deck = new FullDeck(cardFactory, settings);
        Random r = new Random();
        CardSuit suit = null;
        CardNumber number = null;
        int randomSuit = 0;
        int randomCardNumber = 0;
        for (int cnt = 0; cnt < 10000; cnt++) {
            randomSuit = r.nextInt(CardSuit.values().length);
            randomCardNumber = r.nextInt(CardNumber.values().length);
            deck.reset();
            number = CardNumber.values()[randomCardNumber];
            suit = CardSuit.values()[randomSuit];

            Card card = deck.drawFromDeck(suit, number);

            Assert.assertEquals(card.getSuit(), suit);
            Assert.assertEquals(card.getNumber(), number);
        }

    }

    @Test
    @Repeat(20)
    public void testDrawSpecificCardTwoTimes() {
        Settings settings = new Settings();
        CardFactory cardFactory = new CardFactory();
        FullDeck deck = new FullDeck(cardFactory, settings);
        Random r = new Random();
        CardSuit suit = null;
        CardNumber number = null;
        int randomSuit = 0;
        int randomCardNumber = 0;
        for (int cnt = 0; cnt < 10000; cnt++) {
            randomSuit = r.nextInt(CardSuit.values().length);
            randomCardNumber = r.nextInt(CardNumber.values().length);
            deck.reset();
            number = CardNumber.values()[randomCardNumber];
            suit = CardSuit.values()[randomSuit];

            Card firstCard = deck.drawFromDeck(suit, number);

            Assert.assertEquals(firstCard.getSuit(), suit);
            Assert.assertEquals(firstCard.getNumber(), number);

            Card secondCard = deck.drawFromDeck(suit, number);
            Assert.assertEquals(secondCard, null);
        }
    }

    @Test
    @Repeat(20)
    public void testFullHandDrawSorting() {
        Settings settings = new Settings();
        CardFactory cardFactory = new CardFactory();
        FullDeck deck = new FullDeck(cardFactory, settings);
        Hand resultHand = new Hand();

        for (int cnt = 0; cnt < Hand.HAND_SIZE; ++cnt) {
            Card drawedCard = deck.drawFromDeck();
            resultHand.addCard(drawedCard);
        }

        CardComparator cardComparator = new CardComparator();

        for (int cnt = 0; cnt < Hand.HAND_SIZE - 1; ++cnt) {
            Card oneCard = resultHand.getLowestCard(cnt);
            Card secondCard = resultHand.getLowestCard(cnt + 1);

            Assert.assertEquals(cardComparator.compare(oneCard, secondCard), -1);
        }

        Assert.assertEquals(resultHand.getCardCount(), Hand.HAND_SIZE);
        Assert.assertEquals(deck.getCardCount(), FullDeck.DECK_SIZE - Hand.HAND_SIZE);
    }
}
