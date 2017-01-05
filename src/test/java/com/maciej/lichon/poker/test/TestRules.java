package com.maciej.lichon.poker.test;

import com.maciej.lichon.poker.domain.Hand;
import com.maciej.lichon.poker.domain.Settings;
import com.maciej.lichon.poker.domain.deck.CardFactory;
import com.maciej.lichon.poker.domain.deck.FullDeck;
import com.maciej.lichon.poker.logic.GeneratorCase;
import com.maciej.lichon.poker.logic.GeneratorSet;
import com.maciej.lichon.poker.logic.RuleCase;
import com.maciej.lichon.poker.logic.RuleSet;
import com.maciej.lichon.poker.logic.generators.HighCardHigher;
import com.maciej.lichon.poker.logic.generators.abstracts.Generator;
import com.maciej.lichon.poker.logic.generators.abstracts.TemplateGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test class checking the validity of rules on a reduced permutations random
 * tests
 *
 * @author mlichon
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:conf/simple-job-launcher-context.xml"})
public class TestRules {

    private static final Logger log = LogManager.getLogger(TestRules.class);

    @Test
    @Repeat(10000)
    public void testFlush() throws Exception {
        Settings settings = new Settings();
        CardFactory cardFactory = new CardFactory();
        FullDeck deck = new FullDeck(cardFactory, settings);
        GeneratorSet generatorSet = new GeneratorSet();

        Generator highCardGenerator = generatorSet.getGenerator(GeneratorCase.HIGH_CARD.getValue());
        Generator flushGenerator = generatorSet.getGenerator(GeneratorCase.FLUSH.getValue());
        Hand hand2 = highCardGenerator.generateHand(deck);
        Hand hand1 = flushGenerator.generateHand(deck);

        log.debug("Hand1: " + hand1.toString());
        log.debug("Hand2: " + hand2.toString());

        RuleSet ruleSet = new RuleSet();
        Assert.assertEquals(ruleSet.checkRule(RuleCase.FLUSH.getValue(), hand1, hand2), -1);
        Assert.assertEquals(ruleSet.checkRule(RuleCase.FLUSH.getValue(), hand2, hand1), 1);
        Assert.assertEquals(ruleSet.checkRule(RuleCase.FLUSH.getValue(), hand1, hand1), 0);
    }

    @Test
    @Repeat(10000)
    public void testFourOfAKind() throws Exception {
        Settings settings = new Settings();
        CardFactory cardFactory = new CardFactory();
        FullDeck deck = new FullDeck(cardFactory, settings);
        GeneratorSet generatorSet = new GeneratorSet();

        Generator highCardGenerator = generatorSet.getGenerator(GeneratorCase.HIGH_CARD.getValue());
        Generator fourOfAKindGenerator = generatorSet.getGenerator(GeneratorCase.FOUR_OF_A_KIND.getValue());
        Hand hand2 = highCardGenerator.generateHand(deck);
        Hand hand1 = fourOfAKindGenerator.generateHand(deck);

        log.debug("Hand1: " + hand1.toString());
        log.debug("Hand2: " + hand2.toString());

        RuleSet ruleSet = new RuleSet();
        Assert.assertEquals(ruleSet.checkRule(RuleCase.FOUR_OF_A_KIND.getValue(), hand1, hand2), -1);
        Assert.assertEquals(ruleSet.checkRule(RuleCase.FOUR_OF_A_KIND.getValue(), hand2, hand1), 1);
        Assert.assertEquals(ruleSet.checkRule(RuleCase.FOUR_OF_A_KIND.getValue(), hand1, hand1), 0);

    }

    @Test
    @Repeat(10000)
    public void testFullHouse() throws Exception {
        Settings settings = new Settings();
        CardFactory cardFactory = new CardFactory();
        FullDeck deck = new FullDeck(cardFactory, settings);
        GeneratorSet generatorSet = new GeneratorSet();

        Generator highCardGenerator = generatorSet.getGenerator(GeneratorCase.HIGH_CARD.getValue());
        Generator fullHouseGenerator = generatorSet.getGenerator(GeneratorCase.FULL_HOUSE.getValue());

        Hand hand2 = highCardGenerator.generateHand(deck);
        Hand hand1 = fullHouseGenerator.generateHand(deck);

        log.debug("Hand1: " + hand1.toString());
        log.debug("Hand2: " + hand2.toString());

        RuleSet ruleSet = new RuleSet();
        Assert.assertEquals(ruleSet.checkRule(RuleCase.FULL_HOUSE.getValue(), hand1, hand2), -1);
        Assert.assertEquals(ruleSet.checkRule(RuleCase.FULL_HOUSE.getValue(), hand2, hand1), 1);
        Assert.assertEquals(ruleSet.checkRule(RuleCase.FULL_HOUSE.getValue(), hand1, hand1), 0);
    }

    @Test
    @Repeat(10000)
    public void testHighCard() throws Exception {
        Settings settings = new Settings();
        CardFactory cardFactory = new CardFactory();

        GeneratorSet generatorSet = new GeneratorSet();

        Generator highCardGenerator = generatorSet.getGenerator(GeneratorCase.HIGH_CARD.getValue());
        TemplateGenerator highCardHigherGenerator = new HighCardHigher();
        Hand hand1 = null;
        Hand hand2 = null;

        while (hand2 == null) {
            FullDeck deck = new FullDeck(cardFactory, settings);
            hand1 = highCardGenerator.generateHand(deck);

            if (hand1 == null) {
                continue;
            }

            hand2 = highCardHigherGenerator.generateFromTemplate(deck, hand1);
        }

        log.debug("Hand1: " + hand1.toString());
        log.debug("Hand2: " + hand2.toString());

        RuleSet ruleSet = new RuleSet();
        Assert.assertEquals(ruleSet.checkRule(RuleCase.HIGH_CARD.getValue(), hand1, hand2), -1);
        Assert.assertEquals(ruleSet.checkRule(RuleCase.HIGH_CARD.getValue(), hand2, hand1), 1);
        Assert.assertEquals(ruleSet.checkRule(RuleCase.HIGH_CARD.getValue(), hand1, hand1), 0);
    }

    @Test
    @Repeat(10000)
    public void testPair() throws Exception {
        Settings settings = new Settings();
        CardFactory cardFactory = new CardFactory();
        FullDeck deck = new FullDeck(cardFactory, settings);
        GeneratorSet generatorSet = new GeneratorSet();

        Generator highCardGenerator = generatorSet.getGenerator(GeneratorCase.HIGH_CARD.getValue());
        Generator pairCardGenerator = generatorSet.getGenerator(GeneratorCase.PAIR.getValue());

        Hand hand2 = highCardGenerator.generateHand(deck);
        Hand hand1 = pairCardGenerator.generateHand(deck);

        log.debug("Hand1: " + hand1.toString());
        log.debug("Hand2: " + hand2.toString());

        RuleSet ruleSet = new RuleSet();
        Assert.assertEquals(ruleSet.checkRule(RuleCase.PAIR.getValue(), hand1, hand2), -1);
        Assert.assertEquals(ruleSet.checkRule(RuleCase.PAIR.getValue(), hand2, hand1), 1);
        Assert.assertEquals(ruleSet.checkRule(RuleCase.PAIR.getValue(), hand1, hand1), 0);
    }

    @Test
    @Repeat(10000)
    public void testStraight() throws Exception {
        Settings settings = new Settings();
        CardFactory cardFactory = new CardFactory();
        FullDeck deck = new FullDeck(cardFactory, settings);
        GeneratorSet generatorSet = new GeneratorSet();

        Generator highCardGenerator = generatorSet.getGenerator(GeneratorCase.HIGH_CARD.getValue());
        Generator straightCardGenerator = generatorSet.getGenerator(GeneratorCase.STRAIGHT.getValue());

        Hand hand2 = highCardGenerator.generateHand(deck);
        Hand hand1 = straightCardGenerator.generateHand(deck);

        log.debug("Hand1: " + hand1.toString());
        log.debug("Hand2: " + hand2.toString());

        RuleSet ruleSet = new RuleSet();

        Assert.assertEquals(ruleSet.checkRule(RuleCase.STRAIGHT.getValue(), hand1, hand2), -1);
        Assert.assertEquals(ruleSet.checkRule(RuleCase.STRAIGHT.getValue(), hand2, hand1), 1);
        Assert.assertEquals(ruleSet.checkRule(RuleCase.STRAIGHT.getValue(), hand1, hand1), 0);

    }

    @Test
    @Repeat(1000)
    public void testStraightFlush() throws Exception {

        Settings settings = new Settings();
        CardFactory cardFactory = new CardFactory();
        FullDeck deck = new FullDeck(cardFactory, settings);
        GeneratorSet generatorSet = new GeneratorSet();

        Generator highCardGenerator = generatorSet.getGenerator(GeneratorCase.HIGH_CARD.getValue());
        Generator straightFlushCardGenerator = generatorSet.getGenerator(GeneratorCase.STRAIGHT_FLUSH.getValue());
        Hand hand1 = highCardGenerator.generateHand(deck);
        Hand hand2 = straightFlushCardGenerator.generateHand(deck);

        log.debug("Hand1: " + hand1.toString());
        log.debug("Hand2: " + hand2.toString());

        RuleSet ruleSet = new RuleSet();
        Assert.assertEquals(ruleSet.checkRule(RuleCase.STRAIGHT_FLUSH.getValue(), hand1, hand2), 1);
        Assert.assertEquals(ruleSet.checkRule(RuleCase.STRAIGHT_FLUSH.getValue(), hand1, hand1), 0);
        Assert.assertEquals(ruleSet.checkRule(RuleCase.STRAIGHT_FLUSH.getValue(), hand2, hand1), -1);

    }

    @Test
    @Repeat(10000)
    public void testThreeOfAKind() throws Exception {
        Settings settings = new Settings();
        CardFactory cardFactory = new CardFactory();
        FullDeck deck = new FullDeck(cardFactory, settings);
        GeneratorSet generatorSet = new GeneratorSet();

        Generator highCardGenerator = generatorSet.getGenerator(GeneratorCase.HIGH_CARD.getValue());
        Generator threeOfAKindGenerator = generatorSet.getGenerator(GeneratorCase.THREE_OF_A_KIND.getValue());

        Hand hand2 = highCardGenerator.generateHand(deck);
        Hand hand1 = threeOfAKindGenerator.generateHand(deck);

        log.debug("Hand1: " + hand1.toString());
        log.debug("Hand2: " + hand2.toString());

        RuleSet ruleSet = new RuleSet();
        Assert.assertEquals(ruleSet.checkRule(RuleCase.THREE_OF_A_KIND.getValue(), hand1, hand2), -1);
        Assert.assertEquals(ruleSet.checkRule(RuleCase.THREE_OF_A_KIND.getValue(), hand2, hand1), 1);
        Assert.assertEquals(ruleSet.checkRule(RuleCase.THREE_OF_A_KIND.getValue(), hand1, hand1), 0);
    }

    @Test
    @Repeat(10000)
    public void testTwoPairs() throws Exception {
        Settings settings = new Settings();
        CardFactory cardFactory = new CardFactory();
        FullDeck deck = new FullDeck(cardFactory, settings);
        GeneratorSet generatorSet = new GeneratorSet();

        Generator highCardGenerator = generatorSet.getGenerator(GeneratorCase.HIGH_CARD.getValue());
        Generator twoPairsCardGenerator = generatorSet.getGenerator(GeneratorCase.TWO_PAIRS.getValue());

        Hand hand2 = highCardGenerator.generateHand(deck);
        Hand hand1 = twoPairsCardGenerator.generateHand(deck);

        log.debug("Hand1: " + hand1.toString());
        log.debug("Hand2: " + hand2.toString());

        RuleSet ruleSet = new RuleSet();
        Assert.assertEquals(ruleSet.checkRule(RuleCase.TWO_PAIRS.getValue(), hand1, hand2), -1);
        Assert.assertEquals(ruleSet.checkRule(RuleCase.TWO_PAIRS.getValue(), hand2, hand1), 1);
        Assert.assertEquals(ruleSet.checkRule(RuleCase.TWO_PAIRS.getValue(), hand1, hand1), 0);
    }
}

// Authors footnote:
// this is just a start due to we don't test against all possible permutations - those tests are for MVP
// later on i will generate all card permutations as an optional test and check them against theoretical calculated values.
