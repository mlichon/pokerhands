package com.maciej.lichon.poker.domain.deck;

import com.maciej.lichon.poker.domain.Settings;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author mlichon
 */
public class FullDeck {

    public static final int VERSION = 0;

    public static final int DECK_SIZE = CardSuit.values().length * CardNumber.values().length;

    private List<Card> cards;
    private final CardFactory cardFactory;
    private final Settings settings;
    private final Random r;

    public FullDeck(CardFactory cardFactory, Settings settings) {
        cards = new ArrayList<>(DECK_SIZE);
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
        for (CardSuit suit : CardSuit.values()) {
            for (CardNumber number : CardNumber.values()) {
                cards.add(cardFactory.createCard(number, suit));
            }
        }
    }

    public Card draw() {
        Card card = null;

        return card;
    }

    public Card draw(CardSuit cardSuit, CardNumber cardNumber) {
        Card card = null;

        return card;
    }
}
