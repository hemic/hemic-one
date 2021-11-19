package com.hemic.one.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.AesBytesEncryptor;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;
import tech.jhipster.config.JHipsterProperties;

@Configuration
public class EncoderConfiguration {


    private final JHipsterProperties jHipsterProperties;

    private final ApplicationProperties applicationProperties;

    public EncoderConfiguration(ApplicationProperties applicationProperties,JHipsterProperties jHipsterProperties){
        this.applicationProperties=applicationProperties;
        this.jHipsterProperties=jHipsterProperties;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public BytesEncryptor bytesEncryptor(){
        return new AesBytesEncryptor(jHipsterProperties.getSecurity().getAuthentication().getJwt().getBase64Secret(), applicationProperties.getSalt(), KeyGenerators.secureRandom(applicationProperties.getSecureRandom()));
    }



}
