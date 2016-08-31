package com.maciej.lichon.poker.logic;

import javax.inject.Inject;

/**
 *
 * @author mlichon
 */
public class HandComparator {
    private final RuleSet ruleSet;
    
    @Inject
    HandComparator(RuleSet ruleSet)
    {
        this.ruleSet = ruleSet;
    }
}
