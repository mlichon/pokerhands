package com.maciej.lichon.poker.logic.rules;

import com.maciej.lichon.poker.domain.Hand;
import com.maciej.lichon.poker.domain.deck.CardNumber;
import com.maciej.lichon.poker.logic.rules.abstracts.Rule;

/**
 *
 * @author mlichon
 */
public class ThreeOfAKind extends Rule {

    public final static int REQUIRED_FOR_TRIPLE = 3;

    @Override
    protected boolean handWins(Hand hand) {
        for (CardNumber number : CardNumber.values()) {
            if (hand.getNumByNumber(number) == REQUIRED_FOR_TRIPLE) {
                return true;
            }
        }

        return false;
    }

}
