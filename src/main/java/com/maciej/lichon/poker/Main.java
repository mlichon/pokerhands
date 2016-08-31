package com.maciej.lichon.poker;

import com.maciej.lichon.poker.logic.RuleSet;
import javax.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author mlichon
 */
public class Main {

    private final RuleSet ruleSet;
    private static final Logger log = LogManager.getLogger(Main.class);

    @Inject
    Main(RuleSet ruleSet) {
        this.ruleSet = ruleSet;
    }

    public static void main(String[] args) {

        ApplicationContext context
                = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
    }
}
