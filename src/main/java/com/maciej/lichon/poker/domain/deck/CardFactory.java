package com.maciej.lichon.poker.domain.deck;

/**
 * The one and only source to get fresh cards - injectable by spring.
 *
 * @author mlichon
 */
public class CardFactory {

    /**
     * Create a brand new immutable card
     *
     * @param cardNumber desired card number
     * @param cardSuit desired card suit
     * @return newly created card
     */
    public Card createCard(CardNumber cardNumber, CardSuit cardSuit) {
        return new Card(cardNumber, cardSuit);
    }
}
