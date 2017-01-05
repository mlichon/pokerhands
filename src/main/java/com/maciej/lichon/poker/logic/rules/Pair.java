package com.maciej.lichon.poker.logic.rules;

import com.maciej.lichon.poker.domain.Hand;
import com.maciej.lichon.poker.domain.deck.CardNumber;
import com.maciej.lichon.poker.logic.rules.abstracts.Rule;

/**
 *
 * @author mlichon
 */
public final class Pair extends Rule {

    private static final int REQUIRED_FOR_PAIR = 2;

    @Override
    protected boolean handWins(Hand hand) {
        for (CardNumber number : CardNumber.values()) {
            if (hand.getNumByNumber(number) == REQUIRED_FOR_PAIR) {
                return true;
            }
        }

        return false;
    }

}
