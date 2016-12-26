package com.maciej.lichon.poker.logic;

import com.maciej.lichon.poker.domain.Hand;
import javax.inject.Inject;

/**
 *
 * @author mlichon
 */
public class HandComparator {

    private final RuleSet ruleSet;
    private final static int WINNER_NOT_FOUND = -1;

    @Inject
    HandComparator(RuleSet ruleSet) {
        this.ruleSet = ruleSet;
    }

    public int checkRule(Hand hand1, Hand hand2) {
        int result = WINNER_NOT_FOUND;
        for (int cnt = 0; cnt < ruleSet.getRuleCount(); ++cnt) {
            result = ruleSet.checkRule(cnt, hand1, hand2);
            if (result != 0) {
                return result;
            }
        }

        return WINNER_NOT_FOUND;
    }
}
