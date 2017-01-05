package com.maciej.lichon.poker.logic.generators;

import com.maciej.lichon.poker.domain.Hand;
import com.maciej.lichon.poker.domain.deck.Card;
import com.maciej.lichon.poker.domain.deck.CardNumber;
import com.maciej.lichon.poker.domain.deck.CardSuit;
import com.maciej.lichon.poker.domain.deck.FullDeck;
import com.maciej.lichon.poker.logic.generators.abstracts.Generator;

/**
 * This generator is responsible for generating a limited subset of three of a
 * kind
 *
 * @author mlichon
 */
public final class ThreeOfAKind extends Generator {

    @Override
    public Hand generateHand(FullDeck deck) {
        if (deck.getCardCount() < Hand.HAND_SIZE) {
            return null;
        }

        Hand hand = new Hand();

        Card card1 = null;
        Card card2 = null;
        Card card3 = null;
        CardNumber cardNumber = null;

        //TODO: think about getting information from the deck if we actually can have a three of a kind.
        //TODO: limit operations - we assume higher level logic checks if a three of a kind is doable in the deck
        while (card1 == null || card2 == null || card3 == null) {
            deck.returnCardToDeck(card1);
            deck.returnCardToDeck(card2);
            deck.returnCardToDeck(card3);

            CardSuit cardSuit1 = getRandSuit();
            CardSuit cardSuit2 = getRandSuit();
            CardSuit cardSuit3 = getRandSuit();

            while (cardSuit1 == cardSuit2 || cardSuit1 == cardSuit3 || cardSuit2 == cardSuit3) {
                cardSuit2 = getRandSuit();
                cardSuit3 = getRandSuit();
            }

            cardNumber = getRandNumber();

            card1 = deck.drawFromDeck(cardSuit1, cardNumber);
            card2 = deck.drawFromDeck(cardSuit2, cardNumber);
            card3 = deck.drawFromDeck(cardSuit3, cardNumber);
        }

        hand.addCard(card1);
        hand.addCard(card2);
        hand.addCard(card3);

        Card addedCard = null;
        while (hand.getCardCount() != Hand.HAND_SIZE) {
            addedCard = deck.drawFromDeck();

            if (addedCard != null) {

                if (cardNumber != addedCard.getNumber()) {
                    hand.addCard(addedCard);
                }
            }
        }

        return hand;
    }

}
