package com.maciej.lichon.poker.domain.deck;

/**
 *
 * @author mlichon
 */
public enum CardNumber {
    One(0),
    Two(1),
    Three(2),
    Four(3),
    Five(4),
    Six(5);
    
    
    private final int id;
    public static final int VERSION = 0;

    private CardNumber(int id)
    {
        this.id = id;
    }
    
}
