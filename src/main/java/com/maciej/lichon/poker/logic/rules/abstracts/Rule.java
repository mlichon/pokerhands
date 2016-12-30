package com.maciej.lichon.poker.logic.rules.abstracts;

import com.maciej.lichon.poker.domain.Hand;

/**
 * Base class for a Rule in a RuleSet
 *
 * @author mlichon
 */
//TODO: should be comparable
public abstract class Rule {

    /**
     * Check which hand won the battle
     *
     * @param hand1 player -1
     * @param hand2 player 1
     * @return -1,0,1 based on the compare result
     */
    public int compare(Hand hand1, Hand hand2) {
        boolean doesHand1Win = handWins(hand1);
        boolean doesHand2Win = handWins(hand2);

        if (doesHand1Win && doesHand2Win) {
            return 0;
        }

        if (doesHand1Win) {
            return -1;
        }

        if (doesHand2Win) {
            return 1;
        }

        return 0;
    }

    /**
     * Method that should be implemented by each rule checking the rule for one
     * hand to contain the rule.
     *
     * @param hand hand to check
     * @return true if hand wins
     */
    protected abstract boolean handWins(Hand hand);
}
