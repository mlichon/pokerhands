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

    public Card draw() {

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

    public Card draw(CardSuit cardSuit, CardNumber cardNumber) {
        Card card = null;
        int cardPos = cardSuit.getId() * CardNumber.values().length + cardNumber.getId();

        card = cards.get(cardPos);

        return card;
    }
}
