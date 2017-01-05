package com.maciej.lichon.poker.logic.generators.base;

import com.maciej.lichon.poker.domain.deck.CardNumber;
import com.maciej.lichon.poker.domain.deck.CardSuit;
import java.util.Random;

/**
 *
 * @author mlichon
 */
public class GeneratorBase {

    private Random r;

    protected CardSuit getRandSuit() {
        return CardSuit.values()[getRand().nextInt(CardSuit.values().length)];
    }

    protected CardNumber getRandNumber() {
        return CardNumber.values()[getRand().nextInt(CardNumber.values().length)];
    }

    protected final Random getRand() {
        if (r == null) {
            r = new Random();
        }

        return r;
    }
}
