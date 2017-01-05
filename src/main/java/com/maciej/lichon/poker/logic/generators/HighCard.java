package com.maciej.lichon.poker.logic.generators;

import com.maciej.lichon.poker.domain.Hand;
import com.maciej.lichon.poker.domain.deck.Card;
import com.maciej.lichon.poker.domain.deck.CardNumber;
import com.maciej.lichon.poker.domain.deck.CardSuit;
import com.maciej.lichon.poker.domain.deck.FullDeck;
import com.maciej.lichon.poker.logic.generators.abstracts.Generator;

/**
 * Generate a hand of a subset of high cards hands TODO: remember this method is
 * not ready for a missing suit of cards TODO: this can be run with 100%
 * accuracy only on full card deck
 *
 * @param deck
 * @return
 *
 * @author mlichon
 */
public final class HighCard extends Generator {

    @Override
    public Hand generateHand(FullDeck deck) {
        if (deck.getCardCount() < Hand.HAND_SIZE) {
            return null;
        }

        Hand hand = new Hand();
        //we need to allow at least one skip to not get a flush.

        boolean goodStartingNumber = false;

        int startCard = 0;

        //TODO: limit operations - we assume higher level logic checks if a high card is doable in the deck
        while (!goodStartingNumber) {
            startCard = getRand().nextInt(CardNumber.values().length - Hand.HAND_SIZE - 2);
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
            } else if (skipNumber == 1
                    && CardNumber.values().length - (startCard + numberOffset
                    + hand.getCardCount() + 1) > Hand.HAND_SIZE - hand.getCardCount()) {

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
            addedCard = deck.drawFromDeck(randSuit,
                    CardNumber.values()[startCard + numberOffset + hand.getCardCount()]);

            if (addedCard == null) {
                continue;
            }

            hand.addCard(addedCard);
            skipNumber = getRand().nextInt(2);
        }

        if (numberOffset == 0) {
            //TODO: ensure that there's at least one number skip.
        }

        if (!skippedSuit) {
            //TODO: ensure that there's at least one suit skip.
        }

        return hand;
    }

}
