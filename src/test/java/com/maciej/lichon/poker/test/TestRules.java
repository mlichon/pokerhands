package com.maciej.lichon.poker.test;

import com.maciej.lichon.poker.domain.Hand;
import com.maciej.lichon.poker.domain.Settings;
import com.maciej.lichon.poker.domain.deck.CardFactory;
import com.maciej.lichon.poker.domain.deck.FullDeck;
import com.maciej.lichon.poker.logic.HandGenerator;
import com.maciej.lichon.poker.logic.RuleSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author mlichon
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:conf/simple-job-launcher-context.xml"})
public class TestRules {

    private static final Logger log = LogManager.getLogger(TestRules.class);

    //@Test
    public void testFlush() {
        Settings settings = new Settings();
        CardFactory cardFactory = new CardFactory();
        FullDeck deck = new FullDeck(cardFactory, settings);
        HandGenerator handGenerator = new HandGenerator();
        Hand hand1 = handGenerator.generateFlush(deck);
        Hand hand2 = handGenerator.generateHighCard(deck);
        RuleSet ruleSet = new RuleSet();
        Assert.assertTrue(ruleSet.checkRule(3, hand1, hand2) < 0);
    }

    //@Test
    //@Repeat(1000)
    public void testFourOfAKind() {
        Settings settings = new Settings();
        CardFactory cardFactory = new CardFactory();
        FullDeck deck = new FullDeck(cardFactory, settings);
        HandGenerator handGenerator = new HandGenerator();
        Hand hand1 = handGenerator.generateFourOfAKind(deck);
        Hand hand2 = handGenerator.generateHighCard(deck);
        RuleSet ruleSet = new RuleSet();
        Assert.assertEquals(ruleSet.checkRule(RuleSet.RuleCase.FOUR_OF_A_KIND.getValue(), hand1, hand2), -1);
        Assert.assertEquals(ruleSet.checkRule(RuleSet.RuleCase.FOUR_OF_A_KIND.getValue(), hand2, hand1), 1);
        Assert.assertEquals(ruleSet.checkRule(RuleSet.RuleCase.FOUR_OF_A_KIND.getValue(), hand1, hand1), 0);

    }

    //@Test
    public void testFullHouse() {
        Settings settings = new Settings();
        CardFactory cardFactory = new CardFactory();
        FullDeck deck = new FullDeck(cardFactory, settings);
        HandGenerator handGenerator = new HandGenerator();
        Hand hand1 = handGenerator.generateFullHouse(deck);
        Hand hand2 = handGenerator.generateHighCard(deck);
        RuleSet ruleSet = new RuleSet();
        Assert.assertTrue(ruleSet.checkRule(2, hand1, hand2) < 0);
    }

    //@Test
    public void testHighCard() {
        Settings settings = new Settings();
        CardFactory cardFactory = new CardFactory();
        FullDeck deck = new FullDeck(cardFactory, settings);
        HandGenerator handGenerator = new HandGenerator();
        Hand hand1 = null;
        Hand hand2 = null;

        while (hand2 == null) {
            hand1 = handGenerator.generateHighCard(deck);
            hand2 = handGenerator.generateHighCardLower(deck, hand1);
            deck.reset();
        }

        RuleSet ruleSet = new RuleSet();
        Assert.assertTrue(ruleSet.checkRule(8, hand1, hand2) < 0);
    }

    //@Test
    public void testPair() {
        Settings settings = new Settings();
        CardFactory cardFactory = new CardFactory();
        FullDeck deck = new FullDeck(cardFactory, settings);
        HandGenerator handGenerator = new HandGenerator();
        Hand hand1 = handGenerator.generatePair(deck);
        Hand hand2 = handGenerator.generateHighCard(deck);
        RuleSet ruleSet = new RuleSet();
        Assert.assertTrue(ruleSet.checkRule(7, hand1, hand2) < 0);
    }

    //@Test
    public void testStraight() {
        Settings settings = new Settings();
        CardFactory cardFactory = new CardFactory();
        FullDeck deck = new FullDeck(cardFactory, settings);
        HandGenerator handGenerator = new HandGenerator();
        Hand hand1 = handGenerator.generateStraight(deck);
        Hand hand2 = handGenerator.generateHighCard(deck);
        RuleSet ruleSet = new RuleSet();
        Assert.assertTrue(ruleSet.checkRule(0, hand1, hand2) < 0);
    }

    @Test
    @Repeat(10000)
    public void testStraightFlush() {

        Settings settings = new Settings();
        CardFactory cardFactory = new CardFactory();
        FullDeck deck = new FullDeck(cardFactory, settings);
        HandGenerator handGenerator = new HandGenerator();
        Hand hand1 = handGenerator.generateStraightFlush(deck);
        Hand hand2 = handGenerator.generateHighCard(deck);

        log.debug("Hand1: " + hand1.toString());
        log.debug("Hand2: " + hand2.toString());

        RuleSet ruleSet = new RuleSet();
        Assert.assertEquals(ruleSet.checkRule(RuleSet.RuleCase.STRAIGHT_FLUSH.getValue(), hand1, hand2), -1);
        Assert.assertEquals(ruleSet.checkRule(RuleSet.RuleCase.STRAIGHT_FLUSH.getValue(), hand2, hand1), 1);
        Assert.assertEquals(ruleSet.checkRule(RuleSet.RuleCase.STRAIGHT_FLUSH.getValue(), hand1, hand1), 0);
    }

    //@Test
    public void testThreeOfAKind() {
        Settings settings = new Settings();
        CardFactory cardFactory = new CardFactory();
        FullDeck deck = new FullDeck(cardFactory, settings);
        HandGenerator handGenerator = new HandGenerator();
        Hand hand1 = handGenerator.generateThreeOfAKind(deck);
        Hand hand2 = handGenerator.generateHighCard(deck);
        RuleSet ruleSet = new RuleSet();
        Assert.assertTrue(ruleSet.checkRule(5, hand1, hand2) < 0);
    }

    //@Test
    public void testTwoPairs() {
        Settings settings = new Settings();
        CardFactory cardFactory = new CardFactory();
        FullDeck deck = new FullDeck(cardFactory, settings);
        HandGenerator handGenerator = new HandGenerator();
        Hand hand1 = handGenerator.generateTwoPairs(deck);
        Hand hand2 = handGenerator.generateHighCard(deck);
        RuleSet ruleSet = new RuleSet();
        Assert.assertTrue(ruleSet.checkRule(6, hand1, hand2) < 0);
    }
}
