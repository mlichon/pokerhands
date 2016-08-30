package com.maciej.lichon.poker.domain;

import com.maciej.lichon.poker.domain.deck.Card;
import com.maciej.lichon.poker.domain.deck.CardSuit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mlichon
 */
public class Hand {
    public static final int VERSION = 0;
    
    public static final int HAND_SIZE = 5;
    
    List<Card> cards;

    public Hand() {
        cards = new ArrayList<>(HAND_SIZE);
    }
    
    public List<Card> getBySuit(CardSuit cardSuit)
    {
        return null;
    }
    
}
