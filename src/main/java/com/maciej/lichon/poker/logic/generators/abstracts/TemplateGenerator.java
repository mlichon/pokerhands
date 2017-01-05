package com.maciej.lichon.poker.logic.generators.abstracts;

import com.maciej.lichon.poker.logic.generators.base.GeneratorBase;
import com.maciej.lichon.poker.domain.Hand;
import com.maciej.lichon.poker.domain.deck.CardNumber;
import com.maciej.lichon.poker.domain.deck.CardSuit;
import com.maciej.lichon.poker.domain.deck.FullDeck;

/**
 * Base class for a generator that generate card based on a deck and a template
 * hand. Class of this type does not need to generate all possible hand types -
 * it cam generate limited test hands and it does not need to be optimal. Not
 * used outside of debugging
 *
 * @author mlichon
 */
public abstract class TemplateGenerator extends GeneratorBase {

    /**
     * Generate a random hand based on the state of the deck and a template hand
     *
     * @param deck input deck
     * @return random Hand
     */
    public abstract Hand generateFromTemplate(FullDeck deck, Hand templateHand);

    protected CardSuit getRandSuit() {
        return CardSuit.values()[getRand().nextInt(CardSuit.values().length)];
    }

    protected CardNumber getRandNumber() {
        return CardNumber.values()[getRand().nextInt(CardNumber.values().length)];
    }
}
