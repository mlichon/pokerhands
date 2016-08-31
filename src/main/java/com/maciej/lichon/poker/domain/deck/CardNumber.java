package com.maciej.lichon.poker.domain.deck;

/**
 *
 * @author mlichon
 */
public enum CardNumber {
    Two(0),
    Three(1),
    Four(2),
    Five(3),
    Six(4),
    Seven(5),
    Eight(6),
    Nine(7),
    Ten(8),
    Jack(9),
    Queen(10),
    King(11),
    Ace(12);

    private final int id;
    public static final int VERSION = 0;

    private CardNumber(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
