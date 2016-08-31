package com.maciej.lichon.poker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author mlichon
 */
public class Main {

    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        ApplicationContext context
                = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/beans.xml"});

        MainLoop mainLoop = context.getBean("mainLoop", MainLoop.class);
        mainLoop.executeLoop();
    }
}
