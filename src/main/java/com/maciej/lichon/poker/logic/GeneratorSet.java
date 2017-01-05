package com.maciej.lichon.poker.logic;

import com.maciej.lichon.poker.logic.generators.abstracts.Generator;
import com.maciej.lichon.poker.logic.generators.Flush;
import com.maciej.lichon.poker.logic.generators.FourOfAKind;
import com.maciej.lichon.poker.logic.generators.FullHouse;
import com.maciej.lichon.poker.logic.generators.HighCard;
import com.maciej.lichon.poker.logic.generators.Pair;
import com.maciej.lichon.poker.logic.generators.RandomGen;
import com.maciej.lichon.poker.logic.generators.Straight;
import com.maciej.lichon.poker.logic.generators.StraightFlush;
import com.maciej.lichon.poker.logic.generators.ThreeOfAKind;
import com.maciej.lichon.poker.logic.generators.TwoPairs;
import gnu.trove.map.hash.THashMap;

/**
 *
 * @author mlichon
 */
public class GeneratorSet {

    private final THashMap<Integer, Generator> generatorSet;

    public GeneratorSet() {
        generatorSet = new THashMap<>();
        registerGenerator(GeneratorCase.HIGH_CARD.getValue(), new HighCard());
        registerGenerator(GeneratorCase.PAIR.getValue(), new Pair());
        registerGenerator(GeneratorCase.TWO_PAIRS.getValue(), new TwoPairs());
        registerGenerator(GeneratorCase.THREE_OF_A_KIND.getValue(), new ThreeOfAKind());
        registerGenerator(GeneratorCase.STRAIGHT.getValue(), new Straight());
        registerGenerator(GeneratorCase.FLUSH.getValue(), new Flush());
        registerGenerator(GeneratorCase.FULL_HOUSE.getValue(), new FullHouse());
        registerGenerator(GeneratorCase.FOUR_OF_A_KIND.getValue(), new FourOfAKind());
        registerGenerator(GeneratorCase.STRAIGHT_FLUSH.getValue(), new StraightFlush());
        registerGenerator(GeneratorCase.RANDOM.getValue(), new RandomGen());
    }

    public void registerGenerator(int id, Generator generator) {
        generatorSet.put(id, generator);
    }

    public Generator getGenerator(int id) {
        return generatorSet.get(id);
    }
}
