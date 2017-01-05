package com.maciej.lichon.poker.logic;

/**
 *
 * @author mlichon
 */
public enum GeneratorCase {
    STRAIGHT_FLUSH(0),
    FOUR_OF_A_KIND(1),
    FULL_HOUSE(2),
    FLUSH(3),
    STRAIGHT(4),//??
    THREE_OF_A_KIND(5),
    TWO_PAIRS(6),
    PAIR(7),
    HIGH_CARD(8),
    RANDOM(9);

    private final int value;

    private GeneratorCase(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
