package com.maciej.lichon.poker.logic;

import com.maciej.lichon.poker.domain.Hand;
import com.maciej.lichon.poker.domain.deck.Card;
import com.maciej.lichon.poker.domain.deck.CardNumber;
import com.maciej.lichon.poker.domain.deck.CardSuit;
import com.maciej.lichon.poker.domain.deck.FullDeck;
import java.util.Random;

/**
 * This class does not generate all possible hand types - it generates limited
 * test hands and is not optimized.
 *
 * @author mlichon
 */
public class HandGenerator {

    /**
     * Do not inject FullDeck, we don't want a shared state between users of the
     * library.
     */
    Random r;

    public HandGenerator() {
        r = new Random();
    }

    public Hand generateRandom(FullDeck deck) {
        if (deck.getCardCount() < Hand.HAND_SIZE) {
            return null;
        }

        Hand hand = new Hand();
        int cardsLeft = Hand.HAND_SIZE;
        while (cardsLeft != 0) {
            Card card = deck.drawFromDeck();
            if (card != null) {
                cardsLeft = hand.addCard(card);
            }
        }
        return hand;
    }

    public Hand generateFlush(FullDeck deck) {
        Hand hand = new Hand();
        CardSuit randSuit = getRandSuit();
        while (hand.getCardCount() - Hand.HAND_SIZE != 0) {
            Card card = deck.drawFromDeck(randSuit);

            hand.addCard(card);
        }

        return hand;
    }

    public Hand generateFourOfAKind(FullDeck deck) {
        Hand hand = new Hand();
        CardNumber randomNumber = getRandNumber();
        for (CardSuit suit : CardSuit.values()) {
            Card card = deck.drawFromDeck(suit, randomNumber);
            hand.addCard(card);
        }

        Card card = deck.drawFromDeck();

        hand.addCard(card);

        return hand;
    }

    public Hand generateFullHouse(FullDeck deck) {
        Hand hand = new Hand();
        CardNumber randomNumber3 = getRandNumber();
        CardNumber randomNumber2 = getRandNumber();

        while (randomNumber3 == randomNumber2) {
            randomNumber3 = getRandNumber();
            randomNumber2 = getRandNumber();
        }

        for (int cnt = 0; cnt < 3; ++cnt) {
            Card randCard = deck.drawFromDeck(randomNumber3);
            hand.addCard(randCard);
        }

        for (int cnt = 0; cnt < 2; ++cnt) {
            Card randCard = deck.drawFromDeck(randomNumber3);
            hand.addCard(randCard);
        }

        return hand;
    }

    public Hand generateHighCard(FullDeck deck) {
        if (deck.getCardCount() < Hand.HAND_SIZE) {
            return null;
        }

        Hand hand = new Hand();
        //we need to allow at least one skip to not get a flush.
        int startCard = r.nextInt(CardNumber.values().length - Hand.HAND_SIZE + 1);
        CardSuit prevSuit = null;
        int skipNumber = 0;
        CardSuit randSuit = null;
        boolean onlyOnce = false;
        Card addedCard = null;
        for (int cardCnt = startCard; cardCnt < CardNumber.values().length; ++cardCnt) {

            if (skipNumber == 1 && CardNumber.values().length - (cardCnt + 1) < Hand.HAND_SIZE - hand.getCardCount()) {
                continue;
            }

            prevSuit = randSuit;

            randSuit = getRandSuit();

            if (prevSuit != null && !onlyOnce) {//force the change on second card
                while (prevSuit == randSuit) {
                    randSuit = getRandSuit();
                }
                onlyOnce = true;
            }

            addedCard = deck.drawFromDeck(randSuit, CardNumber.values()[cardCnt]);

            if (addedCard == null) {
                --cardCnt;
                continue;
            }

            hand.addCard(addedCard);
            skipNumber = r.nextInt(1);
        }

        return hand;
    }

    //TODO:
    public Hand generateHighCardLower(FullDeck deck, Hand handCompared) {
        Hand hand = new Hand();

        if (deck.getCardCount() < Hand.HAND_SIZE) {
            return null;
        }

        return hand;
    }

    public Hand generatePair(FullDeck deck) {

        if (deck.getCardCount() < Hand.HAND_SIZE) {
            return null;
        }

        Card card1 = null;
        Card card2 = null;
        Hand hand = new Hand();

        //TODO: think about getting information from the deck if we actually can have a pair.
        //TODO: limit operations - we assume higher level logic checks if a pair is doable in the deck
        while (card1 == null || card2 == null) {
            deck.returnCardToDeck(card1);
            deck.returnCardToDeck(card2);
            CardSuit cardSuit1 = getRandSuit();
            CardSuit cardSuit2 = getRandSuit();

            while (cardSuit1 == cardSuit2) {
                cardSuit2 = getRandSuit();
            }

            CardNumber cardNumber = getRandNumber();

            card1 = deck.drawFromDeck(cardSuit1, cardNumber);
            card2 = deck.drawFromDeck(cardSuit2, cardNumber);
        }

        hand.addCard(card1);
        hand.addCard(card2);

        //TODO: prevent double pair - for now we keep it in "at least 1 pair"
        Card addedCard = null;
        while (hand.getCardCount() - Hand.HAND_SIZE != 0) {
            addedCard = deck.drawFromDeck();
            if (addedCard != null) {
                hand.addCard(addedCard);
            }
        }

        return hand;
    }

    public Hand generateStraight(FullDeck deck) {
        if (deck.getCardCount() < Hand.HAND_SIZE) {
            return null;
        }

        Hand hand = new Hand();
        int randomStartNumberAllowingStraight = r.nextInt(CardNumber.values().length - Hand.HAND_SIZE);
        int currCardNumber = randomStartNumberAllowingStraight;
        Card card = null;
        CardSuit randomSuit = null;

        while (hand.getCardCount() < Hand.HAND_SIZE) {
            randomSuit = getRandSuit();
            card = deck.drawFromDeck(randomSuit, CardNumber.values()[currCardNumber]);
            --currCardNumber;

            hand.addCard(card);
        }

        return hand;
    }

    public Hand generateStraightFlush(FullDeck deck) {

        if (deck.getCardCount() < Hand.HAND_SIZE) {
            return null;
        }
        Hand hand = new Hand();

        CardSuit cardSuit = getRandSuit();
        int randomStartNumberAllowingStraight = r.nextInt(CardNumber.values().length - Hand.HAND_SIZE);
        int currCardNumber = randomStartNumberAllowingStraight;

        Card card = null;

        while (hand.getCardCount() < Hand.HAND_SIZE) {
            card = deck.drawFromDeck(cardSuit, CardNumber.values()[currCardNumber]);
            --currCardNumber;

            hand.addCard(card);
        }

        return hand;
    }

    public Hand generateThreeOfAKind(FullDeck deck) {
        if (deck.getCardCount() < Hand.HAND_SIZE) {
            return null;
        }

        Hand hand = new Hand();

        Card card1 = null;
        Card card2 = null;
        Card card3 = null;

        //TODO: think about getting information from the deck if we actually can have a three of a kind.
        //TODO: limit operations - we assume higher level logic checks if a three of a kind is doable in the deck
        while (card1 == null || card2 == null || card3 == null) {
            deck.returnCardToDeck(card1);
            deck.returnCardToDeck(card2);
            deck.returnCardToDeck(card3);

            CardSuit cardSuit1 = getRandSuit();
            CardSuit cardSuit2 = getRandSuit();
            CardSuit cardSuit3 = getRandSuit();

            while (cardSuit1 == cardSuit2 || cardSuit1 == cardSuit3 || cardSuit2 == cardSuit3) {
                cardSuit2 = getRandSuit();
                cardSuit3 = getRandSuit();
            }

            CardNumber cardNumber = getRandNumber();

            card1 = deck.drawFromDeck(cardSuit1, cardNumber);
            card2 = deck.drawFromDeck(cardSuit2, cardNumber);
            card3 = deck.drawFromDeck(cardSuit3, cardNumber);
        }

        hand.addCard(card1);
        hand.addCard(card2);
        hand.addCard(card3);

        Card addedCard = null;
        while (hand.getCardCount() - Hand.HAND_SIZE != 0) {
            addedCard = deck.drawFromDeck();
            if (addedCard != null) {
                hand.addCard(addedCard);
            }
        }

        return hand;
    }

    public Hand generateTwoPairs(FullDeck deck) {

        if (deck.getCardCount() < Hand.HAND_SIZE) {
            return null;
        }

        Hand hand = new Hand();
        Card card1 = null;
        Card card2 = null;

        //TODO: think about getting information from the deck if we actually can have a pair.
        //TODO: limit operations - we assume higher level logic checks if a pair is doable in the deck
        CardNumber cardNumber1 = null;
        while (card1 == null || card2 == null) {
            deck.returnCardToDeck(card1);
            deck.returnCardToDeck(card2);
            CardSuit cardSuit1 = getRandSuit();
            CardSuit cardSuit2 = getRandSuit();

            while (cardSuit1 == cardSuit2) {
                cardSuit2 = getRandSuit();
            }

            cardNumber1 = getRandNumber();

            card1 = deck.drawFromDeck(cardSuit1, cardNumber1);
            card2 = deck.drawFromDeck(cardSuit2, cardNumber1);
        }

        hand.addCard(card1);
        hand.addCard(card2);

        card1 = null;
        card2 = null;

        //TODO: think about getting information from the deck if we actually can have a pair.
        //TODO: limit operations - we assume higher level logic checks if a pair is doable in the deck
        while (card1 == null || card2 == null) {
            deck.returnCardToDeck(card1);
            deck.returnCardToDeck(card2);
            CardSuit cardSuit1 = getRandSuit();
            CardSuit cardSuit2 = getRandSuit();

            while (cardSuit1 == cardSuit2) {
                cardSuit2 = getRandSuit();
            }

            CardNumber cardNumber2 = null;
            do {
                cardNumber2 = getRandNumber();
            } while (cardNumber2 != cardNumber1);

            card1 = deck.drawFromDeck(cardSuit1, cardNumber2);
            card2 = deck.drawFromDeck(cardSuit2, cardNumber2);
        }

        hand.addCard(card1);
        hand.addCard(card2);

        Card addedCard = null;
        while (hand.getCardCount() - Hand.HAND_SIZE != 0) {
            addedCard = deck.drawFromDeck();
            if (addedCard != null) {
                hand.addCard(addedCard);
            }
        }
        return hand;
    }

    private CardSuit getRandSuit() {
        return CardSuit.values()[r.nextInt(CardSuit.values().length)];
    }

    private CardNumber getRandNumber() {
        return CardNumber.values()[r.nextInt(CardNumber.values().length)];
    }
}
