package com.maciej.lichon.poker.logic;

import com.maciej.lichon.poker.domain.Hand;
import com.maciej.lichon.poker.domain.deck.Card;
import com.maciej.lichon.poker.domain.deck.CardNumber;
import com.maciej.lichon.poker.domain.deck.CardSuit;
import com.maciej.lichon.poker.domain.deck.FullDeck;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * This class does not generate all possible hand types - it generates limited
 * test hands and is not optimized.
 *
 * @author mlichon
 */
//TODO: check absolute minimum conditions
@Deprecated
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

        while (!deck.containsNum(randSuit, Hand.HAND_SIZE)) {
            randSuit = getRandSuit();
        }

        while (hand.getCardCount() < Hand.HAND_SIZE) {
            Card card = deck.drawFromDeck(randSuit);

            if (card != null) {
                hand.addCard(card);
            }
        }

        return hand;
    }

    public Hand generateFourOfAKind(FullDeck deck) {
        Hand hand = new Hand();
        CardNumber randomNumber = getRandNumber();

        while (!deck.containsNum(randomNumber, 4)) {
            randomNumber = getRandNumber();
        }

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

        while (randomNumber3 == randomNumber2 || !(deck.containsNum(randomNumber2, 2) && deck.containsNum(randomNumber3, 3))) {
            randomNumber3 = getRandNumber();
            randomNumber2 = getRandNumber();
        }

        //TODO: think how to remove magic numbers
        while (hand.getCardCount() < 3) {
            Card randCard = deck.drawFromDeck(randomNumber3);

            if (randCard != null) {
                hand.addCard(randCard);
            }
        }

        while (hand.getCardCount() < 5) {
            Card randCard = deck.drawFromDeck(randomNumber2);

            if (randCard != null) {
                hand.addCard(randCard);
            }
        }

        return hand;
    }

    //TODO: this can be run with 100% accuracy only on full card deck
    /**
     * Generate a hand of a subset of high cards hands TODO: remember this
     * method is not ready for a missing suit of cards
     *
     *
     * @param deck
     * @return
     */
    public Hand generateHighCard(FullDeck deck) {

        if (deck.getCardCount() < Hand.HAND_SIZE) {
            return null;
        }

        Hand hand = new Hand();
        //we need to allow at least one skip to not get a flush.

        boolean goodStartingNumber = false;

        int startCard = 0;

        //TODO: limit operations - we assume higher level logic checks if a high card is doable in the deck
        while (!goodStartingNumber) {
            startCard = r.nextInt(CardNumber.values().length - Hand.HAND_SIZE - 2);
            int hits = 0;

            for (int cnt = 0; cnt < CardNumber.values().length - startCard; ++cnt) {
                if (deck.contains(CardNumber.values()[startCard + cnt])) {
                    ++hits;
                }
            }

            goodStartingNumber = hits > Hand.HAND_SIZE;
        }

        CardSuit prevSuit = null;
        int skipNumber = 0;
        CardSuit randSuit = null;
        boolean skippedSuit = false;
        Card addedCard = null;
        int numberOffset = 0;

        //TODO: limit operations - we assume higher level logic checks if a high card is doable in the deck
        while (hand.getCardCount() < Hand.HAND_SIZE) {

            if (hand.getCardCount() != 0 && hand.getCardCount() <= 1) {
                ++numberOffset;
            } else if (skipNumber == 1 && CardNumber.values().length - (startCard + numberOffset + hand.getCardCount() + 1) > Hand.HAND_SIZE - hand.getCardCount()) {

                /*                if (deck.contains(CardNumber.values()[startCard
                            + numberOffset + 1
                            + hand.getCardCount()])) {*/
                //++numberOffset;
                //}
                //continue;
            }

            prevSuit = randSuit;

            randSuit = getRandSuit();

            if (prevSuit != null) {//force the change at least on the second card
                while (prevSuit == randSuit) {
                    randSuit = getRandSuit();
                    if (!deck.contains(randSuit,
                            CardNumber.values()[startCard
                            + numberOffset
                            + hand.getCardCount()])) {
                        randSuit = prevSuit;
                    }
                }
                skippedSuit = true;
            }
            addedCard = deck.drawFromDeck(randSuit, CardNumber.values()[startCard + numberOffset + hand.getCardCount()]);

            if (addedCard == null) {
                continue;
            }

            hand.addCard(addedCard);
            skipNumber = r.nextInt(2);
        }

        if (numberOffset == 0) {
            //TODO: ensure that there's at least one number skip.
        }

        if (!skippedSuit) {
            //TODO: ensure that there's at least one suit skip.
        }

        return hand;
    }

    /**
     * Generate standard high hand that is higher then handCompared - if you
     * can't create a high card hand that is lower then the input return null
     * TODO: Remember this method is not prepared for missing suit. TODO:
     * Remember that the input hand will be treated as a proper high card hand
     * without questioning
     *
     * @param deck deck from
     * @param handCompared hand against the generated hand should be lower
     * @return not null if hand lower possible from handCompared
     */
    public Hand generateHighCardHigher(FullDeck deck, Hand handCompared) {

        int changePositions = 1;

        if (deck.getCardCount() < Hand.HAND_SIZE) {
            return null;
        }

        int randomPosition = 0;
        int howMuchHigher = 0;

        Set<Integer> changes = new HashSet<>();
        Card comparedCard = null;

        CardNumber newNumber = null;
        CardSuit newSuit = null;
        Hand generatedHand = new Hand();

        Card newCard = null;

        //TODO: limit operations
        for (int cnt = 0; cnt < changePositions; ++cnt) {
            do {
                randomPosition = r.nextInt(Hand.HAND_SIZE);
                comparedCard = handCompared.getHighestCard(randomPosition);

                newSuit = getRandSuit();
                howMuchHigher = comparedCard.getNumber().ordinal() + r.nextInt(CardNumber.Ace.ordinal() - comparedCard.getNumber().ordinal()) + 1;

                newNumber = CardNumber.values()[howMuchHigher];
            } while (!deck.contains(newSuit, newNumber) || generatedHand.getNumByNumber(newNumber) != 0 || changes.contains(randomPosition));//ensure there's no pairs
            changes.add(randomPosition);
//ok the case is that we don't have enough space to select a distinct card.
// another while checking this concept.
            newCard = deck.drawFromDeck(newSuit, newNumber);
            generatedHand.addCard(newCard);
        }

        //add missing cards
        //TODO: maybe there's a faster way to create an oposing set of changes.
        for (int cnt = 0; cnt < Hand.HAND_SIZE; ++cnt) {
            if (!changes.contains(cnt)) {
                comparedCard = handCompared.getHighestCard(cnt);
                do {
                    newSuit = getRandSuit();

                } while (!deck.contains(newSuit, comparedCard.getNumber()));

                newCard = deck.drawFromDeck(newSuit, comparedCard.getNumber());
                generatedHand.addCard(newCard);
            }
        }

        return generatedHand;
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
        CardNumber cardNumber = null;

        while (card1 == null || card2 == null) {
            deck.returnCardToDeck(card1);
            deck.returnCardToDeck(card2);
            CardSuit cardSuit1 = getRandSuit();
            CardSuit cardSuit2 = getRandSuit();

            while (cardSuit1 == cardSuit2) {
                cardSuit2 = getRandSuit();
            }

            cardNumber = getRandNumber();

            card1 = deck.drawFromDeck(cardSuit1, cardNumber);
            card2 = deck.drawFromDeck(cardSuit2, cardNumber);
        }

        hand.addCard(card1);
        hand.addCard(card2);

        //TODO: prevent double pair - for now we keep it in "at least 1 pair"
        Card addedCard = null;
        while (hand.getCardCount() != Hand.HAND_SIZE) {
            addedCard = deck.drawFromDeck();
            if (addedCard != null) {
                if (addedCard.getNumber() != cardNumber) {
                    hand.addCard(addedCard);
                }
            }
        }

        return hand;
    }

    //TODO: check starting rules.
    public Hand generateStraight(FullDeck deck) {
        if (deck.getCardCount() < Hand.HAND_SIZE) {
            return null;
        }

        Hand hand = new Hand();
        int randomStartNumberAllowingStraight = Hand.HAND_SIZE + r.nextInt(CardNumber.values().length - Hand.HAND_SIZE - 1);
        int currCardNumber = randomStartNumberAllowingStraight;
        Card card = null;
        CardSuit randomSuit = null;

        while (hand.getCardCount() < Hand.HAND_SIZE) {
            randomSuit = getRandSuit();//todo: this should be a pre-baked array.
            card = deck.drawFromDeck(randomSuit, CardNumber.values()[currCardNumber]);

            if (card != null) {
                hand.addCard(card);
                --currCardNumber;
            }
        }

        return hand;
    }

    public Hand generateStraightFlush(FullDeck deck) {

        if (deck.getCardCount() < Hand.HAND_SIZE) {
            return null;
        }
        Hand hand = new Hand();

        CardSuit cardSuit = null;
        boolean goodRandomStart = false;
        int randomStartNumberAllowingStraight = 0;

        //TODO: limit operations - we assume higher level logic checks if a straight flush is doable in the deck
        while (!goodRandomStart) {
            cardSuit = getRandSuit();
            randomStartNumberAllowingStraight = (Hand.HAND_SIZE - 1)
                    + r.nextInt(CardNumber.values().length - Hand.HAND_SIZE - 1);

            goodRandomStart = true;
            for (int cnt = 0; cnt < Hand.HAND_SIZE; ++cnt) {
                if (!deck.contains(cardSuit, CardNumber.values()[randomStartNumberAllowingStraight - cnt])) {
                    goodRandomStart = false;
                }
            }
        }

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
        CardNumber cardNumber = null;

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

            cardNumber = getRandNumber();

            card1 = deck.drawFromDeck(cardSuit1, cardNumber);
            card2 = deck.drawFromDeck(cardSuit2, cardNumber);
            card3 = deck.drawFromDeck(cardSuit3, cardNumber);
        }

        hand.addCard(card1);
        hand.addCard(card2);
        hand.addCard(card3);

        Card addedCard = null;
        while (hand.getCardCount() != Hand.HAND_SIZE) {
            addedCard = deck.drawFromDeck();

            if (addedCard != null) {

                if (cardNumber != addedCard.getNumber()) {
                    hand.addCard(addedCard);
                }
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
        CardNumber cardNumber2 = null;
        while (card1 == null || card2 == null) {
            deck.returnCardToDeck(card1);
            deck.returnCardToDeck(card2);
            CardSuit cardSuit1 = getRandSuit();
            CardSuit cardSuit2 = getRandSuit();

            while (cardSuit1 == cardSuit2) {
                cardSuit2 = getRandSuit();
            }

            do {
                cardNumber2 = getRandNumber();
            } while (cardNumber2 == cardNumber1);

            card1 = deck.drawFromDeck(cardSuit1, cardNumber2);
            card2 = deck.drawFromDeck(cardSuit2, cardNumber2);
        }

        hand.addCard(card1);
        hand.addCard(card2);

        Card addedCard = null;
        while (hand.getCardCount() - Hand.HAND_SIZE != 0) {
            addedCard = deck.drawFromDeck();
            if (addedCard != null) {
                if (addedCard.getNumber() != cardNumber1
                        && addedCard.getNumber() != cardNumber2) {
                    hand.addCard(addedCard);
                }
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
