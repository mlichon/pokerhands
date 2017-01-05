package com.maciej.lichon.poker.logic.rules;

import com.maciej.lichon.poker.domain.Hand;
import com.maciej.lichon.poker.domain.deck.CardSuit;
import com.maciej.lichon.poker.logic.rules.abstracts.Rule;

/**
 *
 * @author mlichon
 */
public final class Flush extends Rule {

    private final static int SUIT_HITS_REQUIRED = 5;

    @Override
    protected boolean handWins(Hand hand) {
        CardSuit searchedSuit = hand.getLowestCard(0).getSuit();
        int suitCounter = 1;

        // We can simplify like that due to id there's a change of suit it's not a flush
        for (int cnt = 1; cnt < hand.getCardCount(); ++cnt) {
            if (searchedSuit == hand.getLowestCard(cnt).getSuit()) {
                ++suitCounter;
            }
        }

        if (suitCounter == SUIT_HITS_REQUIRED) {
            return true;
        }

        return false;
    }

}
