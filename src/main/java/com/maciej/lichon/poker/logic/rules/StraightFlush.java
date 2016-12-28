package com.maciej.lichon.poker.logic.rules;

import com.maciej.lichon.poker.domain.Hand;
import com.maciej.lichon.poker.domain.deck.Card;
import com.maciej.lichon.poker.domain.deck.CardNumber;
import com.maciej.lichon.poker.domain.deck.CardSuit;
import com.maciej.lichon.poker.logic.rules.interfaces.Rule;

/**
 * Rule checking the straight flush between 2 hands.
 *
 * @author mlichon
 */
public class StraightFlush implements Rule {

    private static int RULE_HAND_SIZE = 5;

    /**
     * Method comparing a straight flush, returns -1 if hand1 wins 1 when hand2
     * 0 means that 2 hands both contain a straight flush and we have to check
     * the high card
     *
     * @param hand1 first hand of comparsion
     * @param hand2 second hand of comparsion
     */
    @Override
    public int compare(Hand hand1, Hand hand2) {

        boolean doesHand1Win = handWins(hand1);
        boolean doesHand2Win = handWins(hand2);

        if (doesHand1Win && doesHand2Win) {
            return 0;
        }

        if (doesHand1Win) {
            return -1;
        }

        if (doesHand2Win) {
            return 1;
        }

        return 0;
    }

    /**
     * We should check if the color is always the same and cards go lower by 1
     *
     * @param hand hand that should be checked against straight flush.
     * @return true if hand contains a flush
     */
    private boolean handWins(Hand hand) {
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
