package com.maciej.lichon.poker.logic.rules;

import com.maciej.lichon.poker.domain.Hand;
import com.maciej.lichon.poker.domain.deck.CardNumber;
import com.maciej.lichon.poker.logic.rules.abstracts.Rule;

/**
 *
 * @author mlichon
 */
public class TwoPairs extends Rule {

    public final static int REQUIRED_HITS = 2;
    public final static int REQUIRED_TIMES = 2;

    @Override
    protected boolean handWins(Hand hand) {

        int pairHitCounter = 0;

        for (CardNumber number : CardNumber.values()) {
            if (hand.getNumByNumber(number) == REQUIRED_HITS) {
                ++pairHitCounter;
            }
        }

        return pairHitCounter == REQUIRED_TIMES;
    }

}
