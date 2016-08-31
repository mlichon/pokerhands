package com.maciej.lichon.poker.domain.deck;

/**
 *
 * @author mlichon
 */
public class Card {
    
    public static final int VERSION = 0;
    
    private final CardSuit suit;
    private final CardNumber number;
    
    protected Card(CardNumber cardNumber ,CardSuit cardSuit)
    {
        this.suit = cardSuit;
        this.number = cardNumber;
    }

    public CardNumber getNumber() {
        return number;
    }

    public CardSuit getSuit() {
        return suit;
    }
    
}
