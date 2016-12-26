package com.maciej.lichon.poker.domain;

import com.maciej.lichon.poker.domain.deck.Card;
import com.maciej.lichon.poker.domain.deck.CardNumber;
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

    public List<Card> getBySuit(CardSuit cardSuit) {
        return null;
    }

    public List<Card> getByNumber(CardNumber cardnumber) {
        return null;
    }

    public int addCard(Card card) {
        if (cards.size() < HAND_SIZE) {
            cards.add(card);
        }

        cards.sort((card1, card2) -> {
            return Integer.compare(card1.getNumber().getId(), card2.getNumber().getId());
        });

        return HAND_SIZE - cards.size();
    }

    public Card getHighestCard(int order) {
        Card card = null;

        return card;
    }

    public int getCardCount() {
        return cards.size();
    }

}
