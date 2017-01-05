package com.maciej.lichon.poker.domain;

import com.maciej.lichon.poker.domain.deck.Card;
import com.maciej.lichon.poker.logic.CardComparator;
import com.maciej.lichon.poker.domain.deck.CardFactory;
import com.maciej.lichon.poker.domain.deck.CardNumber;
import com.maciej.lichon.poker.domain.deck.CardSuit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 * Hand implementation containing cards from card factory
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
    private final List<Card> cards;

    //TODO: better data structure - ex. list based map - memory consumption
    private final Map<CardSuit, Integer> cardSuitCounter;
    private final Map<CardNumber, Integer> cardNumberCounter;

    @Inject
    private CardFactory cardFactory;

    public Hand() {
        cards = new ArrayList<>(HAND_SIZE);

        cardNumberCounter = new HashMap<CardNumber, Integer>();

        for (CardNumber number : CardNumber.values()) {
            cardNumberCounter.put(number, 0);
        }

        cardSuitCounter = new HashMap<CardSuit, Integer>();
        for (CardSuit suit : CardSuit.values()) {
            cardSuitCounter.put(suit, 0);
        }
    }

    /**
     * Get number of cards by suit
     *
     * @param suit the desired card suit
     * @return number of occurrences
     */
    public int getNumBySuit(CardSuit suit) {
        return cardSuitCounter.get(suit);
    }

    /**
     * Get number of cards by their numbers
     *
     * @param number the desired card number
     * @return number of occurrences
     */
    public int getNumByNumber(CardNumber number) {
        return cardNumberCounter.get(number);
    }

    // TODO:
    public List<Card> getBySuit(CardSuit cardSuit) {
        return null;
    }

    // TODO:
    public List<Card> getByNumber(CardNumber cardnumber) {
        return null;
    }

    /**
     * Add card to the hand, update card number counters
     *
     * @param card card produced by
     * {@link com.maciej.lichon.poker.domain.deck.CardFactory.createCard# CardFactory}
     * @return
     */
    public int addCard(Card card) {

        /*if (card == null) {
            return HAND_SIZE - cards.size();
        }*/
        if (cards.size() < HAND_SIZE) {
            cards.add(card);
        }

        int cardNumberCount = cardNumberCounter.get(card.getNumber());

        cardNumberCounter.put(card.getNumber(), cardNumberCount + 1);

        int cardSuitCount = cardSuitCounter.get(card.getSuit());

        cardSuitCounter.put(card.getSuit(), cardSuitCount + 1);

        cards.sort((card1, card2) -> {
            return cardComparator.compare(card1, card2);
        });

        return HAND_SIZE - cards.size();
    }

    /**
     * Remove card from the hand, update the occurrences counters
     *
     * @param card card produced by
     * {@link com.maciej.lichon.poker.domain.deck.CardFactory.createCard# CardFactory}
     * @return
     */
    public int removeCard(Card card) {
        cards.remove(card);

        int cardNumberCount = cardNumberCounter.get(card.getNumber());

        cardNumberCounter.put(card.getNumber(), cardNumberCount - 1);

        int cardSuitCount = cardSuitCounter.get(card.getSuit());

        cardSuitCounter.put(card.getSuit(), cardSuitCount - 1);

        cards.sort((card1, card2) -> {
            return cardComparator.compare(card1, card2);
        });

        return HAND_SIZE - cards.size();
    }

    /**
     * Remove card from the deck by card number and card suit, update the
     * occurrences counters Warning: slower
     *
     * @param cardNumber the desired card number
     * @param cardSuit the desired card suit
     * @return
     */
    public int removeCard(CardNumber cardNumber, CardSuit cardSuit) {
        Card card = cardFactory.createCard(cardNumber, cardSuit);

        int cardNumberCount = cardNumberCounter.get(card.getNumber());

        cardNumberCounter.put(card.getNumber(), cardNumberCount - 1);

        int cardSuitCount = cardSuitCounter.get(card.getSuit());

        cardSuitCounter.put(card.getSuit(), cardSuitCount - 1);

        cards.remove(card);

        cards.sort((card1, card2) -> {
            return cardComparator.compare(card1, card2);
        });

        return HAND_SIZE - cards.size();
    }

    /**
     * Get the lowest card in the deck skipping {
     *
     * @see skipCards}
     * @param skipCards how many cards to skip from the left
     * @return card that is of the desired parameters
     */
    public Card getLowestCard(int skipCards) {

        if (skipCards < getCardCount()) {
            return cards.get(skipCards);
        }

        return null;
    }

    /**
     * Get the highest card in the deck skipping {
     *
     * @see skipCards}
     * @param skipCards how many cards to skip from the right
     * @return card that is of the desired parameters
     */
    public Card getHighestCard(int skipCards) {
        if (skipCards < getCardCount()) {
            return cards.get(getCardCount() - skipCards - 1);
        }

        return null;
    }

    /**
     * Get current card count of the hand
     *
     * @return number of cards in hand
     */
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
