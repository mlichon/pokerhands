package com.maciej.lichon.poker.logic.generators;

import com.maciej.lichon.poker.domain.Hand;
import com.maciej.lichon.poker.domain.deck.Card;
import com.maciej.lichon.poker.domain.deck.CardNumber;
import com.maciej.lichon.poker.domain.deck.FullDeck;
import com.maciej.lichon.poker.logic.generators.abstracts.Generator;

/**
 * This generator is responsible for generating a limited subset of full houses
 *
 * @author mlichon
 */
public final class FullHouse extends Generator {

    @Override
    public Hand generateHand(FullDeck deck) {
        if (deck.getCardCount() < Hand.HAND_SIZE) {
            return null;
        }

        Hand hand = new Hand();
        CardNumber randomNumber3 = getRandNumber();
        CardNumber randomNumber2 = getRandNumber();

        while (randomNumber3 == randomNumber2 || !(deck.containsNum(randomNumber2, 2)
                && deck.containsNum(randomNumber3, 3))) {
            randomNumber3 = getRandNumber();
            randomNumber2 = getRandNumber();
        }

        //TODO: think how to remove magic numbers
        while (hand.getCardCount() < 3) {
            Card randCard = deck.drawFromDeck(randomNumber3);

            if (randCard != null) {
                hand.addCard(randCard);
            }
        }

        while (hand.getCardCount() < 5) {
            Card randCard = deck.drawFromDeck(randomNumber2);

            if (randCard != null) {
                hand.addCard(randCard);
            }
        }

        return hand;
    }

}
