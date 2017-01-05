package com.maciej.lichon.poker.logic.generators;

import com.maciej.lichon.poker.domain.Hand;
import com.maciej.lichon.poker.domain.deck.Card;
import com.maciej.lichon.poker.domain.deck.CardNumber;
import com.maciej.lichon.poker.domain.deck.CardSuit;
import com.maciej.lichon.poker.domain.deck.FullDeck;
import com.maciej.lichon.poker.logic.generators.abstracts.Generator;

/**
 * This generator is responsible for generating a limited subset of straight
 * flushes
 *
 * @author mlichon
 */
public final class StraightFlush extends Generator {

    @Override
    public Hand generateHand(FullDeck deck) {
        if (deck.getCardCount() < Hand.HAND_SIZE) {
            return null;
        }
        Hand hand = new Hand();

        CardSuit cardSuit = null;
        boolean goodRandomStart = false;
        int randomStartNumberAllowingStraight = 0;

        //TODO: limit operations - we assume higher level logic checks if a straight flush is doable in the deck
        while (!goodRandomStart) {
            cardSuit = getRandSuit();
            randomStartNumberAllowingStraight = (Hand.HAND_SIZE - 1)
                    + getRand().nextInt(CardNumber.values().length - Hand.HAND_SIZE - 1);

            goodRandomStart = true;
            for (int cnt = 0; cnt < Hand.HAND_SIZE; ++cnt) {
                if (!deck.contains(cardSuit, CardNumber.values()[randomStartNumberAllowingStraight - cnt])) {
                    goodRandomStart = false;
                }
            }
        }

        int currCardNumber = randomStartNumberAllowingStraight;

        Card card = null;

        while (hand.getCardCount() < Hand.HAND_SIZE) {
            card = deck.drawFromDeck(cardSuit, CardNumber.values()[currCardNumber]);
            --currCardNumber;

            hand.addCard(card);
        }

        return hand;
    }

}
