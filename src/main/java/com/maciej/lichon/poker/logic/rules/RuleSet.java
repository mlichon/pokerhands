package com.maciej.lichon.poker.logic.rules;

import gnu.trove.map.hash.THashMap;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mironix
 */
public class RuleSet {
    
    /**
     * rule-set map - rules are ordered from lowest to highest, allows for multiple rules for value
     */
    THashMap<Integer, List<Rule>> ruleSet;
    
    public RuleSet()
    {
        ruleSet = new THashMap<>();
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
