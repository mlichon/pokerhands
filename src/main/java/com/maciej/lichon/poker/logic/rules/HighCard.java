package com.maciej.lichon.poker.logic.rules;

import com.maciej.lichon.poker.domain.Hand;
import com.maciej.lichon.poker.domain.deck.Card;
import com.maciej.lichon.poker.domain.exceptions.HandContentException;
import com.maciej.lichon.poker.logic.rules.abstracts.Rule;

/**
 * Normal Poker HighCard - for deviations please implement your rule.
 *
 * @author mlichon
 */
public class HighCard extends Rule {

    @Override
    public int compare(Hand hand1, Hand hand2) throws HandContentException {

        if (hand1.getCardCount() < Hand.HAND_SIZE) {
            throw new HandContentException("Hand 1 contains not enough cards.");
        }

        if (hand2.getCardCount() < Hand.HAND_SIZE) {
            throw new HandContentException("Hand 2 contains not enough cards.");
        }

        Card card1 = null;
        Card card2 = null;

        for (int cnt = 0; cnt < Hand.HAND_SIZE; ++cnt) {
            card1 = hand1.getHighestCard(cnt);
            card2 = hand2.getHighestCard(cnt);

            if (card1.getNumber() != card2.getNumber()) {
                return Integer.compare(card1.getNumber().getId(), card2.getNumber().getId());
            }
        }

        //both hands had the same numbers, suit does not matter.
        return 0;
    }

    @Override
    protected boolean handWins(Hand hand) {
        //we need 2 hands to determine the hand winning - so one hand always losses
        return false;
    }

}
