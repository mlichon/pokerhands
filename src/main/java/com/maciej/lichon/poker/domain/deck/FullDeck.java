package com.maciej.lichon.poker.domain.deck;

import com.maciej.lichon.poker.domain.Settings;
import gnu.trove.map.hash.THashMap;
import java.util.Random;

/**
 *
 * @author mlichon
 */
public class FullDeck {

    public static final int VERSION = 0;

    public static final int DECK_SIZE = CardSuit.values().length * CardNumber.values().length;

    private THashMap<Integer, Card> cards;
    private final CardFactory cardFactory;
    private final Settings settings;
    private final Random r;

    public FullDeck(CardFactory cardFactory, Settings settings) {
        cards = new THashMap<>(DECK_SIZE);
        this.cardFactory = cardFactory;

        this.settings = settings;
        if (settings.getSetting("randomSeed") != null) {
            int seed = Integer.getInteger(settings.getSetting("randomSeed"));
            r = new Random(seed);
        } else {
            r = new Random();
        }
        reset();
    }

    public void reset() {
        cards.clear();
        int cardNum = 0;
        for (CardSuit suit : CardSuit.values()) {
            for (CardNumber number : CardNumber.values()) {
                cards.put(cardNum, cardFactory.createCard(number, suit));
                cardNum++;
            }
        }
    }

    /**
     *
     * @return
     */
    public Card drawFromDeck() {

        //We have drawn all the cards
        if (cards.size() == 0) {
            return null;
        }

        Card card = null;
        int cardInd = r.nextInt(DECK_SIZE);

        while (cards.get(cardInd) == null) {
            cardInd = r.nextInt(DECK_SIZE);
        }

        card = cards.get(cardInd);
        cards.remove(cardInd);
        return card;
    }

    /**
     * Draw a specific card
     *
     * @param cardSuit suit of the card
     * @param cardNumber number of the card
     * @return a specific chosen card from the deck
     */
    public Card drawFromDeck(CardSuit cardSuit, CardNumber cardNumber) {
        Card card = null;
        int cardPos = cardSuit.getId() * CardNumber.values().length + cardNumber.getId();

        card = cards.get(cardPos);
        if (card != null) {
            cards.remove(cardPos);
        }

        return card;
    }

    /**
     * Check if the deck contains the specified card
     *
     * @param cardSuit suit of the card
     * @param cardNumber number of the card
     * @return
     */
    public boolean contains(CardSuit cardSuit, CardNumber cardNumber) {
        int cardPos = cardSuit.getId() * CardNumber.values().length + cardNumber.getId();
        Card card = cards.get(cardPos);

        if (card == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Randomly picked card for a suit
     *
     * @param cardSuit desired card suit
     * @return desired card with desired card suit
     */
    public Card drawFromDeck(CardSuit cardSuit) {
        Card card = null;
        int randCardNumber = r.nextInt(CardNumber.values().length);

        card = FullDeck.this.drawFromDeck(cardSuit, CardNumber.values()[randCardNumber]);
        return card;
    }

    /**
     * Randomly picked card for a number
     *
     * @param cardnumber desired card number
     * @return random card with desired card number
     */
    public Card drawFromDeck(CardNumber cardnumber) {
        Card card = null;
        int randCardSuit = r.nextInt(CardSuit.values().length);

        card = FullDeck.this.drawFromDeck(CardSuit.values()[randCardSuit], cardnumber);
        return card;
    }

    /**
     * Return a card to the deck.
     *
     * @param card card that should be returned to the deck
     */
    public void returnCardToDeck(Card card) {
        if (card != null) {
            int cardPos = card.getSuit().getId() * CardNumber.values().length + card.getNumber().getId();
            if (cards.get(cardPos) == null) {
                cards.put(cardPos, card);
            } else { // TODO: warning log

            }
        }
    }

    public int getCardCount() {
        return cards.size();
    }
}
