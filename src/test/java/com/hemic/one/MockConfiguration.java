package com.hemic.one;

import com.github.jsonzou.jmockdata.MockConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class MockConfiguration {
    @Bean
    public MockConfig mockConfig(){
     return    new MockConfig()
            // 邮箱
            .subConfig("email")
            .stringRegex("[a-z0-9]{5,15}\\@\\w{3,5}\\.[a-z]{2,3}")
            // 用户名规则
            .subConfig("userName")
            .stringRegex("[a-zA-Z_]{1}[a-z0-9_]{5,15}")

            .globalConfig();
    }
}
