package com.maciej.lichon.poker.domain;

import com.maciej.lichon.poker.domain.deck.Card;
import com.maciej.lichon.poker.domain.deck.CardComparator;
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

    // gson does not serialize statics
    // TODO: we want to have possibility to change the comparator, is a static enough?
    private static final CardComparator cardComparator = new CardComparator();

    //Since cards are distinct
    private List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    // TODO:
    public List<Card> getBySuit(CardSuit cardSuit) {
        return null;
    }

    // TODO:
    public List<Card> getByNumber(CardNumber cardnumber) {
        return null;
    }

    public int addCard(Card card) {
        if (cards.size() < HAND_SIZE) {
            cards.add(card);
        }

        cards.sort((card1, card2) -> {
            return cardComparator.compare(card1, card2);
        });
        return HAND_SIZE - cards.size();
    }

    public Card getLowestCard(int skipCards) {

        if (skipCards < getCardCount()) {
            return cards.get(skipCards);
        }

        return null;
    }

    public Card getHighestCard(int skipCards) {
        if (skipCards < getCardCount()) {
            return cards.get(getCardCount() - skipCards - 1);
        }

        return null;
    }

    public int getCardCount() {
        return cards.size();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Card card : cards) {
            builder.append(card.toString());
            builder.append(" ");
        }
        return builder.toString();
    }

}
