/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maciej.lichon.poker;

import com.maciej.lichon.poker.domain.Settings;
import com.maciej.lichon.poker.domain.deck.CardFactory;
import com.maciej.lichon.poker.domain.deck.FullDeck;
import com.maciej.lichon.poker.logic.RuleSet;
import javax.inject.Inject;

/**
 *
 * @author mlichon
 */
@Deprecated
public class MainLoop {

    private final RuleSet ruleSet;
    private final CardFactory cardFactory;
    private final Settings settings;

    @Inject
    public MainLoop(Settings settings, RuleSet ruleSet, CardFactory cardFactory) {
        this.settings = settings;
        this.ruleSet = ruleSet;
        this.cardFactory = cardFactory;
    }

    public void executeLoop() {
        FullDeck deck = new FullDeck(cardFactory, settings);
    }
}
