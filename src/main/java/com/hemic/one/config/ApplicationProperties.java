package com.hemic.one.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to One.
 * <p>
 * Properties are configured in the {@code application.yml} file. See {@link tech.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {


    private String defaultPassword;
    private String salt;
    private int  secureRandom;


    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getSecureRandom() {
        return secureRandom;
    }

    public void setSecureRandom(int secureRandom) {
        this.secureRandom = secureRandom;
    }

    public String getDefaultPassword() {
        return defaultPassword;
    }

    public void setDefaultPassword(String defaultPassword) {
        this.defaultPassword = defaultPassword;
    }
}
