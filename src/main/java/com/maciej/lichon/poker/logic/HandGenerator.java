package com.maciej.lichon.poker.logic;

import com.maciej.lichon.poker.domain.Hand;
import com.maciej.lichon.poker.domain.deck.Card;
import com.maciej.lichon.poker.domain.deck.FullDeck;

/**
 *
 * @author mlichon
 */
public class HandGenerator {

    /**
     * Do not inject FullDeck, we don't want a shared state between users of the
     * library.
     */
    public HandGenerator() {

    }

    public Hand generateRandom(FullDeck deck) {
        Hand hand = new Hand();
        int cardsLeft = Hand.HAND_SIZE;
        while (cardsLeft != 0) {
            Card card = deck.draw();
            if (card != null) {
                cardsLeft = hand.addCard(card);
            }
        }
        return hand;
    }

    public Hand generateFlush(FullDeck deck) {
        Hand hand = new Hand();
        return hand;
    }

    public Hand generateFourOfAKind(FullDeck deck) {
        Hand hand = new Hand();
        return hand;
    }

    public Hand generateFullHouse(FullDeck deck) {
        Hand hand = new Hand();
        return hand;
    }

    public Hand generateHighCard(FullDeck deck) {
        Hand hand = new Hand();
        return hand;
    }

    public Hand generateHighCardLower(FullDeck deck, Hand handCompared) {
        Hand hand = new Hand();
        return hand;
    }

    public Hand generatePair(FullDeck deck) {
        Hand hand = new Hand();
        return hand;
    }

    public Hand generateStraight(FullDeck deck) {
        Hand hand = new Hand();
        return hand;
    }

    public Hand generateStraightFlush(FullDeck deck) {
        Hand hand = new Hand();
        return hand;
    }

    public Hand generateThreeOfAKind(FullDeck deck) {
        Hand hand = new Hand();
        return hand;
    }

    public Hand generateTwoPairs(FullDeck deck) {
        Hand hand = new Hand();
        return hand;
    }
}
