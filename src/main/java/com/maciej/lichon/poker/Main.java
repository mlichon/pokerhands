package com.maciej.lichon.poker;

import com.maciej.lichon.poker.logic.rules.RuleSet;
import javax.inject.Inject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author mlichon
 */
public class Main {
    
    
        private final RuleSet ruleSet;
        
        @Inject
        Main(RuleSet ruleSet)
        {
            this.ruleSet = ruleSet;
        }
        
    	public static void main(String [] args)
	{
            ApplicationContext context =
            new ClassPathXmlApplicationContext(new String[] {"beans.xml"});
        }
}
