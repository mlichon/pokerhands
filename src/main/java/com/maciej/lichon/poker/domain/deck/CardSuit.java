package com.maciej.lichon.poker.domain.deck;

/**
 *
 * @author mlichon
 */
public enum CardSuit {
    Spades(0, 0),
    Hearts(1, 1),
    Diamonds(2, 1),
    Clubs(3, 0);
    
    private final int id;
    private final int color;
    public static final int VERSION = 0;
    
    private CardSuit(int id, int color)
    {
        this.id = id;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public int getColor() {
        return color;
    }
    
    

    
}
