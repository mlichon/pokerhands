package com.maciej.lichon.poker.domain.deck;

/**
 * Definition of suits avaliable in the system and their textual/numerical
 * representation
 *
 * @author mlichon
 */
public enum CardSuit {
    Spades(0, 0, "\u2660"),
    Hearts(1, 1, "\u2764"),
    Diamonds(2, 1, "\u2666"),
    Clubs(3, 0, "\u2663");

    private final int id;
    private final int color;
    private final String representation;
    public static final int VERSION = 0;

    private CardSuit(int id, int color, String representation) {
        this.id = id;
        this.color = color;
        this.representation = representation;
    }

    public int getId() {
        return id;
    }

    public int getColor() {
        return color;
    }

    @Override
    public String toString() {
        return representation;
    }

}
