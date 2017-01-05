package com.maciej.lichon.poker.logic.generators;

import com.maciej.lichon.poker.domain.Hand;
import com.maciej.lichon.poker.domain.deck.Card;
import com.maciej.lichon.poker.domain.deck.CardNumber;
import com.maciej.lichon.poker.domain.deck.CardSuit;
import com.maciej.lichon.poker.domain.deck.FullDeck;
import com.maciej.lichon.poker.logic.generators.abstracts.Generator;

/**
 * This generator is responsible for generating a limited subset of pairs
 *
 * @author mlichon
 */
public final class Pair extends Generator {

    @Override
    public Hand generateHand(FullDeck deck) {
        if (deck.getCardCount() < Hand.HAND_SIZE) {
            return null;
        }

        Card card1 = null;
        Card card2 = null;
        Hand hand = new Hand();

        //TODO: think about getting information from the deck if we actually can have a pair.
        //TODO: limit operations - we assume higher level logic checks if a pair is doable in the deck
        CardNumber cardNumber = null;

        while (card1 == null || card2 == null) {
            deck.returnCardToDeck(card1);
            deck.returnCardToDeck(card2);
            CardSuit cardSuit1 = getRandSuit();
            CardSuit cardSuit2 = getRandSuit();

            while (cardSuit1 == cardSuit2) {
                cardSuit2 = getRandSuit();
            }

            cardNumber = getRandNumber();

            card1 = deck.drawFromDeck(cardSuit1, cardNumber);
            card2 = deck.drawFromDeck(cardSuit2, cardNumber);
        }

        hand.addCard(card1);
        hand.addCard(card2);

        //TODO: prevent double pair - for now we keep it in "at least 1 pair"
        Card addedCard = null;
        while (hand.getCardCount() != Hand.HAND_SIZE) {
            addedCard = deck.drawFromDeck();
            if (addedCard != null) {
                if (addedCard.getNumber() != cardNumber) {
                    hand.addCard(addedCard);
                }
            }
        }

        return hand;
    }

}
