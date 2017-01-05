package com.maciej.lichon.poker.logic;

import com.maciej.lichon.poker.domain.Hand;
import com.maciej.lichon.poker.domain.exceptions.HandContentException;
import com.maciej.lichon.poker.logic.rules.Flush;
import com.maciej.lichon.poker.logic.rules.FourOfAKind;
import com.maciej.lichon.poker.logic.rules.FullHouse;
import com.maciej.lichon.poker.logic.rules.HighCard;
import com.maciej.lichon.poker.logic.rules.Pair;
import com.maciej.lichon.poker.logic.rules.Straight;
import com.maciej.lichon.poker.logic.rules.StraightFlush;
import com.maciej.lichon.poker.logic.rules.ThreeOfAKind;
import com.maciej.lichon.poker.logic.rules.TwoPairs;
import com.maciej.lichon.poker.logic.rules.abstracts.Rule;
import gnu.trove.map.hash.THashMap;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains rules for 5 card poker hand. rules are ordered from
 * lowest to highest, allows for multiple rules for value
 *
 * @author mlichon
 */
public class RuleSet {

    private final THashMap<Integer, List<Rule>> ruleSet;

    public RuleSet() {
        ruleSet = new THashMap<>();

        registerRule(RuleCase.HIGH_CARD.getValue(), new HighCard());
        registerRule(RuleCase.PAIR.getValue(), new Pair());
        registerRule(RuleCase.TWO_PAIRS.getValue(), new TwoPairs());
        registerRule(RuleCase.THREE_OF_A_KIND.getValue(), new ThreeOfAKind());
        registerRule(RuleCase.STRAIGHT.getValue(), new Straight());
        registerRule(RuleCase.FLUSH.getValue(), new Flush());
        registerRule(RuleCase.FULL_HOUSE.getValue(), new FullHouse());
        registerRule(RuleCase.FOUR_OF_A_KIND.getValue(), new FourOfAKind());
        registerRule(RuleCase.STRAIGHT_FLUSH.getValue(), new StraightFlush());
    }

    /**
     * Register a rule in the system without a subrule
     *
     * @param value rule id
     * @param rule rule implementation
     */
    public void registerRule(int value, Rule rule) {
        if (ruleSet.get(value) == null) {
            ruleSet.put(value, new ArrayList<>());
        }

        ruleSet.get(value).add(rule);

    }

    /**
     * Return how many rules there are in the system
     *
     * @return number of rules
     */
    public int getRuleCount() {
        return ruleSet.size();
    }

    /**
     * Check against a rule in the given ruleset
     *
     * @param rule rule id
     * @param hand1 hand of player (-1)
     * @param hand2 hand of player (1)
     * @return -1,0,1 for comparation result.
     */
    public int checkRule(int rule, Hand hand1, Hand hand2) throws HandContentException {
        return checkRule(rule, 0, hand1, hand2);
    }

    /**
     * Same as checkRule but for a subrule
     *
     * @param rule id of the rule
     * @param subRule id of the subrule
     * @param hand1 hand of player (-1)
     * @param hand2 hand of player (1)
     * @return -1,0,1 for comparation result
     */
    public int checkRule(int rule, int subRule, Hand hand1, Hand hand2) throws HandContentException {
        return ruleSet.get(rule).get(subRule).compare(hand1, hand2);
    }
}
