package com.maciej.lichon.poker.logic.rules;

import com.maciej.lichon.poker.domain.Hand;
import com.maciej.lichon.poker.domain.deck.Card;
import com.maciej.lichon.poker.domain.deck.CardNumber;
import com.maciej.lichon.poker.logic.rules.abstracts.Rule;

/**
 *
 * @author mlichon
 */
public class FullHouse extends Rule {

    private final static int TRIPLE_LIMIT = 3;
    private final static int PAIR_LIMIT = 2;

    @Override
    protected boolean handWins(Hand hand) {
        CardNumber number1 = null;
        int number1Cnt = 0;
        CardNumber number2 = null;
        int number2Cnt = 0;

        Card card = null;
        CardNumber numberTemp = null;

        for (int cnt = 0; cnt < hand.getCardCount(); ++cnt) {
            card = hand.getLowestCard(cnt);
            numberTemp = card.getNumber();

            if (number1 == null) {
                number1 = numberTemp;
                ++number1Cnt;
                continue;
            } else if (number1 == numberTemp) {
                ++number1Cnt;
                continue;
            }

            if (number2 == null && number1 != null && number1 != numberTemp) {
                number2 = numberTemp;
                ++number2Cnt;
            } else if (number2 != null && number2 == numberTemp) {
                ++number2Cnt;
            }

        }

        if (number1Cnt == TRIPLE_LIMIT && number2Cnt == PAIR_LIMIT) {
            return true;
        }

        if (number2Cnt == TRIPLE_LIMIT && number1Cnt == PAIR_LIMIT) {
            return true;
        }

        return false;
    }

}
