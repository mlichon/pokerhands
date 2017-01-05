package com.maciej.lichon.poker.logic.rules;

import com.maciej.lichon.poker.domain.Hand;
import com.maciej.lichon.poker.domain.deck.Card;
import com.maciej.lichon.poker.domain.deck.CardNumber;
import com.maciej.lichon.poker.logic.rules.abstracts.Rule;

/**
 *
 * @author mlichon
 */
public final class Straight extends Rule {

    @Override
    protected boolean handWins(Hand hand) {
        if (hand.getCardCount() < Hand.HAND_SIZE) {
            return false;
        }

        Card highestCard = hand.getLowestCard(0);
        CardNumber startingNumber = highestCard.getNumber();

        Card checkedCardA = null;
        Card checkedCardB = null;

        boolean checkSuccess = true;

        for (int cnt = 0; cnt < Hand.HAND_SIZE - 1; cnt++) {
            checkedCardA = hand.getLowestCard(cnt);
            checkedCardB = hand.getLowestCard(cnt + 1);

            if (!(checkedCardA.getNumber().ordinal() == startingNumber.ordinal() + cnt
                    && checkedCardB.getNumber().ordinal() == startingNumber.ordinal() + (cnt + 1))) {
                checkSuccess = false;
                break;
            }
        }

        return checkSuccess;
    }

}
