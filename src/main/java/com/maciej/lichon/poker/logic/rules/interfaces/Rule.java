package com.maciej.lichon.poker.logic.rules.interfaces;

import com.maciej.lichon.poker.domain.Hand;

/**
 *
 * @author mlichon
 */

//TODO: should be comparable
public interface Rule {
    
    public int compare(Hand hand1, Hand hand2);
}
