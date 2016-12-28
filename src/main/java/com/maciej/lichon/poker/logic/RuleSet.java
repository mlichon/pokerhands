package com.maciej.lichon.poker.logic;

import com.maciej.lichon.poker.domain.Hand;
import com.maciej.lichon.poker.logic.rules.Flush;
import com.maciej.lichon.poker.logic.rules.FourOfAKind;
import com.maciej.lichon.poker.logic.rules.FullHouse;
import com.maciej.lichon.poker.logic.rules.HighCard;
import com.maciej.lichon.poker.logic.rules.Pair;
import com.maciej.lichon.poker.logic.rules.Straight;
import com.maciej.lichon.poker.logic.rules.StraightFlush;
import com.maciej.lichon.poker.logic.rules.ThreeOfAKind;
import com.maciej.lichon.poker.logic.rules.TwoPairs;
import com.maciej.lichon.poker.logic.rules.interfaces.Rule;
import gnu.trove.map.hash.THashMap;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains rules for 5 card poker hand.
 *
 * @author mlichon
 */
public class RuleSet {

    /**
     * rule-set map - rules are ordered from lowest to highest, allows for
     * multiple rules for value
     */
    public enum RuleCase {
        STRAIGHT_FLUSH(0),
        FOUR_OF_A_KIND(1),
        FULL_HOUSE(2),
        FLUSH(3),
        STRAIGHT(4),
        THREE_OF_A_KIND(5),
        TWO_PAIRS(6),
        PAIR(7),
        HIGH_CARD(8);

        private final int value;

        private RuleCase(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

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

    public void registerRule(int value, Rule rule) {
        if (ruleSet.get(value) == null) {
            ruleSet.put(value, new ArrayList<>());
        }

        ruleSet.get(value).add(rule);

    }

    public int getRuleCount() {
        return ruleSet.size();
    }

    public int checkRule(int rule, Hand hand1, Hand hand2) {
        return checkRule(rule, 0, hand1, hand2);
    }

    public int checkRule(int rule, int subRule, Hand hand1, Hand hand2) {
        return ruleSet.get(rule).get(subRule).compare(hand1, hand2);
    }
}
