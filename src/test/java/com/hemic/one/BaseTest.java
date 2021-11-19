package com.hemic.one;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.jsonzou.jmockdata.MockConfig;
import com.hemic.one.config.TokenProvider;
import com.hemic.one.service.UserService;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@AutoConfigureMockMvc
@IntegrationTest
@EnableWebMvc
@SpringBootTest(classes = OneApp.class)
public class BaseTest {

    @Autowired
    protected MockMvc mockMvc;

    @Resource
    protected MockConfig mockConfig;

    private    String adminToken;

    @Resource
    private UserService userService;

    @Resource
    private TokenProvider tokenProvider;



    public String getAdminToken() throws JsonProcessingException {
        if(StringUtils.isEmpty(adminToken)){
            adminToken= tokenProvider.createToken(userService.getByUsername("admin"),true);
        }
        return adminToken;
    }

}
