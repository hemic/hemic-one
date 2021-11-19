package com.hemic.one.web.rest;

import static com.hemic.one.web.rest.UserJWTControllerT.TEST_USER_LOGIN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.github.jsonzou.jmockdata.JMockData;
import com.hemic.one.BaseTest;
import com.hemic.one.config.ApplicationProperties;
import com.hemic.one.service.UserService;
import com.hemic.one.web.rest.vm.LoginVM;
import com.hemic.one.web.rest.vm.UserVm;
import javax.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;


@WithMockUser(value = TEST_USER_LOGIN)
public class UserJWTControllerT extends BaseTest {

    static final String TEST_USER_LOGIN = "test";


    @Resource
    UserService userService;

    @Resource
    ApplicationProperties applicationProperties;


    @Test
    void testAuthenticatedUser() throws Exception {
        UserVm userVm = JMockData.mock(UserVm.class, mockConfig);
        String id = userService.create(userVm);
        Assertions.assertNotNull(id);
        LoginVM loginVM = new LoginVM();
        loginVM.setUsername(userVm.getUsername());
        loginVM.setPassword(applicationProperties.getDefaultPassword());
        mockMvc.perform(
                post("/api/jwt/authenticate").content(JsonMapper.builder().build().writeValueAsString(loginVM)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void testAuthenticatedException() throws Exception {

        LoginVM loginVM = JMockData.mock(LoginVM.class, mockConfig);
        mockMvc.perform(
                post("/api/jwt/authenticate").content(JsonMapper.builder().build().writeValueAsString(loginVM)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized())
            .andReturn();


    }

}
