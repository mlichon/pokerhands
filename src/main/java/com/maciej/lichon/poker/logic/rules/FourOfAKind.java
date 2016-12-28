package com.maciej.lichon.poker.logic.rules;

import com.maciej.lichon.poker.domain.Hand;
import com.maciej.lichon.poker.domain.deck.Card;
import com.maciej.lichon.poker.domain.deck.CardNumber;
import com.maciej.lichon.poker.logic.rules.interfaces.Rule;

/**
 *
 * @author mlichon
 */
public class FourOfAKind implements Rule {

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

    private boolean handWins(Hand hand) {

        CardNumber searchedCardNumber = hand.getLowestCard(0).getNumber();
        Card card = null;
        int cardFit = 0;
        for (int cnt = 1; cnt < hand.getCardCount(); ++cnt) {
            card = hand.getHighestCard(cnt);
            if (card.getNumber().ordinal() == searchedCardNumber.ordinal()) {

            }
        }

        return true;
    }

}
