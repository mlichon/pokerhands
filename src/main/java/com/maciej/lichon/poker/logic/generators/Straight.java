package com.maciej.lichon.poker.logic.generators;

import com.maciej.lichon.poker.domain.Hand;
import com.maciej.lichon.poker.domain.deck.Card;
import com.maciej.lichon.poker.domain.deck.CardNumber;
import com.maciej.lichon.poker.domain.deck.CardSuit;
import com.maciej.lichon.poker.domain.deck.FullDeck;
import com.maciej.lichon.poker.logic.generators.abstracts.Generator;

/**
 * This generator is responsible for generating a limited subset of straights
 *
 * @author mlichon
 */
public final class Straight extends Generator {

    @Override
    public Hand generateHand(FullDeck deck) {
        if (deck.getCardCount() < Hand.HAND_SIZE) {
            return null;
        }

        Hand hand = new Hand();
        int randomStartNumberAllowingStraight = Hand.HAND_SIZE
                + getRand().nextInt(CardNumber.values().length - Hand.HAND_SIZE - 1);
        int currCardNumber = randomStartNumberAllowingStraight;
        Card card = null;
        CardSuit randomSuit = null;

        while (hand.getCardCount() < Hand.HAND_SIZE) {
            randomSuit = getRandSuit();//todo: this should be a pre-baked array.
            card = deck.drawFromDeck(randomSuit, CardNumber.values()[currCardNumber]);

            if (card != null) {
                hand.addCard(card);
                --currCardNumber;
            }
        }

        return hand;
    }

}
