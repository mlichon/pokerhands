package com.maciej.lichon.poker.logic.generators;

import com.maciej.lichon.poker.domain.Hand;
import com.maciej.lichon.poker.domain.deck.Card;
import com.maciej.lichon.poker.domain.deck.CardNumber;
import com.maciej.lichon.poker.domain.deck.CardSuit;
import com.maciej.lichon.poker.domain.deck.FullDeck;
import com.maciej.lichon.poker.logic.generators.abstracts.Generator;

/**
 * This generator is responsible for generating a limited subset of two pairs
 *
 * @author mlichon
 */
public final class TwoPairs extends Generator {

    @Override
    public Hand generateHand(FullDeck deck) {
        if (deck.getCardCount() < Hand.HAND_SIZE) {
            return null;
        }

        Hand hand = new Hand();
        Card card1 = null;
        Card card2 = null;

        //TODO: think about getting information from the deck if we actually can have a pair.
        //TODO: limit operations - we assume higher level logic checks if a pair is doable in the deck
        CardNumber cardNumber1 = null;
        while (card1 == null || card2 == null) {
            deck.returnCardToDeck(card1);
            deck.returnCardToDeck(card2);
            CardSuit cardSuit1 = getRandSuit();
            CardSuit cardSuit2 = getRandSuit();

            while (cardSuit1 == cardSuit2) {
                cardSuit2 = getRandSuit();
            }

            cardNumber1 = getRandNumber();

            card1 = deck.drawFromDeck(cardSuit1, cardNumber1);
            card2 = deck.drawFromDeck(cardSuit2, cardNumber1);
        }

        hand.addCard(card1);
        hand.addCard(card2);

        card1 = null;
        card2 = null;

        //TODO: think about getting information from the deck if we actually can have a pair.
        //TODO: limit operations - we assume higher level logic checks if a pair is doable in the deck
        CardNumber cardNumber2 = null;
        while (card1 == null || card2 == null) {
            deck.returnCardToDeck(card1);
            deck.returnCardToDeck(card2);
            CardSuit cardSuit1 = getRandSuit();
            CardSuit cardSuit2 = getRandSuit();

            while (cardSuit1 == cardSuit2) {
                cardSuit2 = getRandSuit();
            }

            do {
                cardNumber2 = getRandNumber();
            } while (cardNumber2 == cardNumber1);

            card1 = deck.drawFromDeck(cardSuit1, cardNumber2);
            card2 = deck.drawFromDeck(cardSuit2, cardNumber2);
        }

        hand.addCard(card1);
        hand.addCard(card2);

        Card addedCard = null;
        while (hand.getCardCount() - Hand.HAND_SIZE != 0) {
            addedCard = deck.drawFromDeck();
            if (addedCard != null) {
                if (addedCard.getNumber() != cardNumber1
                        && addedCard.getNumber() != cardNumber2) {
                    hand.addCard(addedCard);
                }
            }
        }
        return hand;
    }

}
