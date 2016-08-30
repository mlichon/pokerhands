package com.maciej.lichon.poker.domain.deck;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mlichon
 */
public class FullDeck {
    
    public static final int VERSION = 0;
    
    public static final int DECK_SIZE = 52;
    
    private List<Card> cards;
    
    public FullDeck()
    {
        cards = new ArrayList<>(DECK_SIZE);
    }
}
