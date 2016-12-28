package com.maciej.lichon.poker.domain.deck;

/**
 *
 * @author mlichon
 */
public enum CardNumber {
    Two(0, "2"),
    Three(1, "3"),
    Four(2, "4"),
    Five(3, "5"),
    Six(4, "6"),
    Seven(5, "7"),
    Eight(6, "8"),
    Nine(7, "9"),
    Ten(8, "10"),
    Jack(9, "J"),
    Queen(10, "Q"),
    King(11, "K"),
    Ace(12, "A");

    private final int id;
    private final String representation;
    public static final int VERSION = 0;

    private CardNumber(int id, String representation) {
        this.id = id;
        this.representation = representation;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return representation;
    }
}
