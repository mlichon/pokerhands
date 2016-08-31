package com.maciej.lichon.poker.domain.deck;

/**
 *
 * @author mlichon
 */
public class CardFactory {

    public Card createCard(CardNumber cardNumber, CardSuit cardSuit) {
        return new Card(cardNumber, cardSuit);
    }
}
