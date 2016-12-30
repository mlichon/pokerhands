package com.maciej.lichon.poker.logic.rules;

import com.maciej.lichon.poker.domain.Hand;
import com.maciej.lichon.poker.domain.deck.Card;
import com.maciej.lichon.poker.domain.deck.CardNumber;
import com.maciej.lichon.poker.logic.rules.abstracts.Rule;

/**
 *
 * @author mlichon
 */
public class FourOfAKind extends Rule {

    @Override
    protected boolean handWins(Hand hand) {

        if (hand.getCardCount() < 4) {
            return false;
        }

        Card card = null;
        CardNumber startNumberFit = null;
        int goodHits = 0;
        for (int cntStarting = 0; cntStarting < hand.getCardCount(); ++cntStarting) {

            startNumberFit = hand.getLowestCard(cntStarting).getNumber();
            goodHits = 0;
            for (int cnt = 0; cnt < hand.getCardCount(); ++cnt) {
                card = hand.getLowestCard(cnt);
                if (card.getNumber().ordinal() == startNumberFit.ordinal()) {
                    ++goodHits;
                }
            }

            if (goodHits == 4) {
                return true;
            }
        }
        return false;
    }

}
