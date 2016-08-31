package com.maciej.lichon.poker.logic;

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
 *
 * @author mlichon
 */
public class RuleSet {
    
    /**
     * rule-set map - rules are ordered from lowest to highest, allows for multiple rules for value
     */
    private final THashMap<Integer, List<Rule>> ruleSet;
    
    public RuleSet()
    {
        ruleSet = new THashMap<>();
        
        registerRule(8, new HighCard());
        registerRule(7, new Pair());
        registerRule(6, new TwoPairs());
        registerRule(5, new ThreeOfAKind());
        registerRule(4, new Straight());
        registerRule(3, new Flush());
        registerRule(2, new FullHouse());
        registerRule(1, new FourOfAKind());
        registerRule(0, new StraightFlush());
    }
    
    public void registerRule(int value, Rule rule)
    {
        if(ruleSet.get(value) == null)
        {
            ruleSet.put(value, new ArrayList<>());
        }
        
        ruleSet.get(value).add(rule);
            
    }
    
    
}
