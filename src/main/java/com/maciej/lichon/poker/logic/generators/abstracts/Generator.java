package com.maciej.lichon.poker.logic.generators.abstracts;

import com.maciej.lichon.poker.logic.generators.base.GeneratorBase;
import com.maciej.lichon.poker.domain.Hand;
import com.maciej.lichon.poker.domain.deck.FullDeck;

/**
 * Base class for a generator that generate card based on a deck. Class of this
 * type does not need to generate all possible hand types - it cam generate
 * limited test hands and it does not need to be optimal. Not used outside of
 * debugging
 *
 * @author mlichon
 */
public abstract class Generator extends GeneratorBase {

    /**
     * Generate a random hand based on the state of the deck
     *
     * @param deck input deck
     * @return random Hand
     */
    public abstract Hand generateHand(FullDeck deck);

}
