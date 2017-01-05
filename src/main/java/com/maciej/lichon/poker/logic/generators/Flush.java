package com.maciej.lichon.poker.logic.generators;

import com.maciej.lichon.poker.domain.Hand;
import com.maciej.lichon.poker.domain.deck.Card;
import com.maciej.lichon.poker.domain.deck.CardSuit;
import com.maciej.lichon.poker.domain.deck.FullDeck;
import com.maciej.lichon.poker.logic.generators.abstracts.Generator;

/**
 * This generator is responsible for generating a limited subset of flushes
 *
 * @author mlichon
 */
public final class Flush extends Generator {

    @Override
    public Hand generateHand(FullDeck deck) {
        if (deck.getCardCount() < Hand.HAND_SIZE) {
            return null;
        }
        Hand hand = new Hand();

        CardSuit randSuit = getRandSuit();

        while (!deck.containsNum(randSuit, Hand.HAND_SIZE)) {
            randSuit = getRandSuit();
        }

        while (hand.getCardCount() < Hand.HAND_SIZE) {
            Card card = deck.drawFromDeck(randSuit);

            if (card != null) {
                hand.addCard(card);
            }
        }

        return hand;
    }

}
