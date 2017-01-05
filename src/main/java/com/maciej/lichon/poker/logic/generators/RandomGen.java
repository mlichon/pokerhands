package com.maciej.lichon.poker.logic.generators;

import com.maciej.lichon.poker.domain.Hand;
import com.maciej.lichon.poker.domain.deck.Card;
import com.maciej.lichon.poker.domain.deck.FullDeck;
import com.maciej.lichon.poker.logic.generators.abstracts.Generator;

/**
 * This generator is responsible for generating a random hand
 *
 * @author mlichon
 */
public final class RandomGen extends Generator {

    @Override
    public Hand generateHand(FullDeck deck) {
        if (deck.getCardCount() < Hand.HAND_SIZE) {
            return null;
        }

        Hand hand = new Hand();
        int cardsLeft = Hand.HAND_SIZE;
        while (cardsLeft != 0) {
            Card card = deck.drawFromDeck();
            if (card != null) {
                cardsLeft = hand.addCard(card);
            }
        }
        return hand;
    }

}
