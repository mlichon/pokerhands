package com.maciej.lichon.poker.domain.deck;

import java.util.Objects;

/**
 *
 * @author mlichon
 */
public class Card {

    public static final int VERSION = 0;

    private final CardSuit suit;
    private final CardNumber number;

    protected Card(CardNumber cardNumber, CardSuit cardSuit) {
        this.suit = cardSuit;
        this.number = cardNumber;
    }

    public CardNumber getNumber() {
        return number;
    }

    public CardSuit getSuit() {
        return suit;
    }

    /**
     * Used for sorting
     *
     * @return position of the card on a card suit and number grid.
     */
    public int numericPos() {
        return suit.ordinal() + number.ordinal() * CardSuit.values().length;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.suit);
        hash = 53 * hash + Objects.hashCode(this.number);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Card other = (Card) obj;
        if (this.suit != other.suit) {
            return false;
        }
        if (this.number != other.number) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return number.toString() + suit.toString();
    }

}
