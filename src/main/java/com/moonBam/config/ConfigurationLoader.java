package com.moonBam.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationLoader {
    private Properties properties;

    public ConfigurationLoader() throws IOException {
        loadProperties();
    }

    private void loadProperties() throws IOException {
        properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("com/moonBam/config/config.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new IOException("config.properties 파일을 찾을 수 없습니다.");
            }
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public int getIntProperty(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }
}
