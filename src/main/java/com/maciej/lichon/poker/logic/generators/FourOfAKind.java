package com.maciej.lichon.poker.logic.generators;

import com.maciej.lichon.poker.domain.Hand;
import com.maciej.lichon.poker.domain.deck.Card;
import com.maciej.lichon.poker.domain.deck.CardNumber;
import com.maciej.lichon.poker.domain.deck.CardSuit;
import com.maciej.lichon.poker.domain.deck.FullDeck;
import com.maciej.lichon.poker.logic.generators.abstracts.Generator;

/**
 * This generator is responsible for generating a limited subset of four of a
 * kind
 *
 * @author mlichon
 */
public final class FourOfAKind extends Generator {

    @Override
    public Hand generateHand(FullDeck deck) {
        if (deck.getCardCount() < Hand.HAND_SIZE) {
            return null;
        }

        Hand hand = new Hand();
        CardNumber randomNumber = getRandNumber();

        while (!deck.containsNum(randomNumber, 4)) {
            randomNumber = getRandNumber();
        }

        for (CardSuit suit : CardSuit.values()) {
            Card card = deck.drawFromDeck(suit, randomNumber);
            hand.addCard(card);
        }

        Card card = deck.drawFromDeck();

        hand.addCard(card);

        return hand;
    }

}
