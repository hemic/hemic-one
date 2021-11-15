package com.hemic.common.config;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.problem.jackson.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

/**
 * @author jhipster
 * jhipster additional Configuration for jackson
 * jackson的额外配置
 */
@Configuration
public class JacksonConfiguration {

    /**
     * Support for Java date and time API.
     *
     * @return the corresponding Jackson module.
     */
    @Bean
    public JavaTimeModule javaTimeModule() {
        return new JavaTimeModule();
    }

    @Bean
    public Jdk8Module jdk8TimeModule() {
        return new Jdk8Module();
    }

    /**
     * Support for Hibernate types in Jackson.
     *
     * @param
     * @return com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module
     * @author jhipster
     * @date 2021/11/9
     */
    @Bean
    public Hibernate5Module hibernate5Module() {
        return new Hibernate5Module();
    }

    /**
     * Module for serialization/deserialization of RFC7807 Problem.
     *
     * @param
     * @return org.zalando.problem.jackson.ProblemModule
     * @author jhipster
     * @date 2021/11/9
     */
    @Bean
    public ProblemModule problemModule() {
        return new ProblemModule();
    }


    @Bean
    /**
     * Module for serialization/deserialization of ConstraintViolationProblem.
     * @author jhipster
     * @date 2021/11/9
     * @param
     * @return org.zalando.problem.violations.ConstraintViolationProblemModule
     */
    public ConstraintViolationProblemModule constraintViolationProblemModule() {
        return new ConstraintViolationProblemModule();
    }
}
