package com.maciej.lichon.poker.logic.rules;

import com.maciej.lichon.poker.domain.Hand;
import com.maciej.lichon.poker.domain.deck.Card;
import com.maciej.lichon.poker.domain.deck.CardNumber;
import com.maciej.lichon.poker.domain.deck.CardSuit;
import com.maciej.lichon.poker.logic.rules.abstracts.Rule;

/**
 * Rule checking the straight flush between 2 hands.
 *
 * @author mlichon
 */
public class StraightFlush extends Rule {

    /**
     * We should check if the color is always the same and cards go lower by 1
     *
     * @param hand hand that should be checked against straight flush.
     * @return true if hand contains a flush
     */
    protected boolean handWins(Hand hand) {
        if (hand.getCardCount() < Hand.HAND_SIZE) {
            return false;
        }

        Card highestCard = hand.getLowestCard(0);
        CardSuit checkedSuit = highestCard.getSuit();
        CardNumber startingNumber = highestCard.getNumber();

        Card checkedCardA = null;
        Card checkedCardB = null;

        boolean checkSuccess = true;

        for (int cnt = 0; cnt < Hand.HAND_SIZE - 1; cnt++) {
            checkedCardA = hand.getLowestCard(cnt);
            checkedCardB = hand.getLowestCard(cnt + 1);

            if (!(checkedCardA.getSuit() == checkedSuit
                    && checkedCardB.getSuit() == checkedSuit
                    && checkedCardA.getNumber().ordinal() == startingNumber.ordinal() + cnt
                    && checkedCardB.getNumber().ordinal() == startingNumber.ordinal() + (cnt + 1))) {
                checkSuccess = false;
                break;
            }
        }

        return checkSuccess;
    }

}
