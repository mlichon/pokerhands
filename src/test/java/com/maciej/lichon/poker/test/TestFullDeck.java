package com.maciej.lichon.poker.test;

import com.maciej.lichon.poker.domain.Settings;
import com.maciej.lichon.poker.domain.deck.Card;
import com.maciej.lichon.poker.domain.deck.CardFactory;
import com.maciej.lichon.poker.domain.deck.CardNumber;
import com.maciej.lichon.poker.domain.deck.CardSuit;
import com.maciej.lichon.poker.domain.deck.FullDeck;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author mlichon
 */
public class TestFullDeck {

    @Test
    public void testDrawAllCardsAtRandom() {
        Settings settings = new Settings();
        CardFactory cardFactory = new CardFactory();
        FullDeck deck = new FullDeck(cardFactory, settings);

        for (int cnt = 0; cnt < FullDeck.DECK_SIZE; cnt++) {
            Assert.assertNotEquals(deck.draw(), null);
        }
    }

    @Test
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

            Card card = deck.draw(suit, number);

            Assert.assertEquals(card.getSuit(), suit);
            Assert.assertEquals(card.getNumber(), number);
        }

    }

    @Test
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

            Card firstCard = deck.draw(suit, number);

            Assert.assertEquals(firstCard.getSuit(), suit);
            Assert.assertEquals(firstCard.getNumber(), number);

            Card secondCard = deck.draw(suit, number);
            Assert.assertEquals(secondCard, null);
        }
    }
}
