package com.maciej.lichon.poker.logic;

import com.maciej.lichon.poker.domain.deck.Card;
import java.util.Comparator;

/**
 * Comparator for the cards based on their suit color grid position.
 *
 * @author mlichon
 */
public class CardComparator implements Comparator<Card> {

    //TODO: probably have bad num pos of the card. or conditions, we'll find in testing.
    @Override
    public int compare(Card o1, Card o2) {
        //-1 0 1

        if (o1.numericPos() < o2.numericPos()) {
            return -1;
        }

        if (o1.numericPos() == o2.numericPos()) {
            return 0;
        }

        if (o1.numericPos() > o2.numericPos()) {
            return 1;
        }

        return 0;
    }

}
