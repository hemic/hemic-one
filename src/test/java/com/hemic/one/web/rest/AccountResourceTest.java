package com.hemic.one.web.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.github.jsonzou.jmockdata.JMockData;
import com.hemic.common.jwt.JWTFilter;
import com.hemic.one.BaseTest;
import com.hemic.one.web.rest.vm.UserVm;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class AccountResourceTest extends BaseTest {


    @Test
    void registerAccount() throws Exception {
        UserVm userVm= JMockData.mock(UserVm.class, mockConfig);
        mockMvc.perform(
                post("/api/account/register").content(JsonMapper.builder().build().writeValueAsString(userVm)).header(JWTFilter.AUTHORIZATION_HEADER,"Bearer "+getAdminToken()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception{
        UserVm userVm= JMockData.mock(UserVm.class, mockConfig);
        String username=userVm.getUsername();
        mockMvc.perform(
                post("/api/account/register").content(JsonMapper.builder().build().writeValueAsString(userVm)).header(JWTFilter.AUTHORIZATION_HEADER,"Bearer "+getAdminToken()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        userVm=JMockData.mock(UserVm.class, mockConfig);
        mockMvc.perform(
                put("/api/account/"+username).content(JsonMapper.builder().build().writeValueAsString(userVm)).header(JWTFilter.AUTHORIZATION_HEADER,"Bearer "+getAdminToken()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

    }

    @Test
    void delete() throws Exception{
        UserVm userVm= JMockData.mock(UserVm.class, mockConfig);
        String username=userVm.getUsername();
        mockMvc.perform(
                post("/api/account/register").content(JsonMapper.builder().build().writeValueAsString(userVm)).header(JWTFilter.AUTHORIZATION_HEADER,"Bearer "+getAdminToken()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        userVm=JMockData.mock(UserVm.class, mockConfig);
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/account/"+username).content(JsonMapper.builder().build().writeValueAsString(userVm)).header(JWTFilter.AUTHORIZATION_HEADER,"Bearer "+getAdminToken()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
