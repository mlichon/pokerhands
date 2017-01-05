package com.maciej.lichon.poker.logic.generators;

import com.maciej.lichon.poker.domain.Hand;
import com.maciej.lichon.poker.domain.deck.Card;
import com.maciej.lichon.poker.domain.deck.CardNumber;
import com.maciej.lichon.poker.domain.deck.CardSuit;
import com.maciej.lichon.poker.domain.deck.FullDeck;
import com.maciej.lichon.poker.logic.generators.abstracts.TemplateGenerator;
import java.util.HashSet;
import java.util.Set;

/**
 * Generate standard high hand that is higher then handCompared - if you can't
 * create a high card hand that is lower then the input return null TODO:
 * Remember this method is not prepared for missing suit. TODO: Remember that
 * the input hand will be treated as a proper high card hand
 *
 * without questioning
 *
 * @author mlichon
 */
public final class HighCardHigher extends TemplateGenerator {

    @Override
    public Hand generateFromTemplate(FullDeck deck, Hand templateHand) {

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
                randomPosition = getRand().nextInt(Hand.HAND_SIZE);
                comparedCard = templateHand.getHighestCard(randomPosition);

                newSuit = getRandSuit();
                howMuchHigher = comparedCard.getNumber().ordinal()
                        + getRand().nextInt(
                                CardNumber.Ace.ordinal()
                                - comparedCard.getNumber().ordinal()) + 1;

                newNumber = CardNumber.values()[howMuchHigher];
            } while (!deck.contains(newSuit, newNumber)
                    || generatedHand.getNumByNumber(newNumber) != 0
                    || changes.contains(randomPosition));//ensure there's no pairs
            changes.add(randomPosition);
            newCard = deck.drawFromDeck(newSuit, newNumber);
            generatedHand.addCard(newCard);
        }

        //add missing cards
        //TODO: maybe there's a faster way to create an oposing set of changes.
        for (int cnt = 0; cnt < Hand.HAND_SIZE; ++cnt) {
            if (!changes.contains(cnt)) {
                comparedCard = templateHand.getHighestCard(cnt);
                do {
                    newSuit = getRandSuit();

                } while (!deck.contains(newSuit, comparedCard.getNumber()));

                newCard = deck.drawFromDeck(newSuit, comparedCard.getNumber());
                generatedHand.addCard(newCard);
            }
        }

        return generatedHand;
    }
}
