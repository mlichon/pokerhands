package com.maciej.lichon.poker.domain;

import com.maciej.lichon.poker.Main;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mlichon
 */
public class Settings {

    private Properties properties;

    private static final Logger log = LogManager.getLogger(Settings.class);

    public Settings() {
        InputStream fileStream = Main.class.getClassLoader().getResourceAsStream("internal.prop");
        properties = new Properties();

        try {
            properties.load(fileStream);
        } catch (IOException ex) {
            log.error("Could not read the internal properties file.", ex);
        }
    }

    public String getSetting(String setting) {
        return properties.getProperty(setting);
    }

}
