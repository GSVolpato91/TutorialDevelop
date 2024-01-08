package com.techacademy.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.techacademy.entity.User;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class UserControllerTest2 {
    private MockMvc mockMvc;

    private WebApplicationContext webApplicationContext;
    UserControllerTest2(WebApplicationContext context) {
        this.webApplicationContext = context;
    }

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity()).build();
    }
    @Test
    @DisplayName("Test Get User")
    @WithMockUser
    void testGetUser() throws Exception {
         MvcResult result = mockMvc.perform(get("/user/list"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("userlist"))
            .andExpect(model().hasNoErrors())
            .andExpect(view().name("user/list"))
            .andReturn();

         List<User> userList = (List<User>)result.getModelAndView().getModel().get("userlist");
         //1件
         assertEquals(userList.get(0).getId(), 1);
         assertEquals(userList.get(0).getName(), "キラメキ太郎");
         //2件
         assertEquals(userList.get(1).getId(), 2);
         assertEquals(userList.get(1).getName(), "キラメキ次郎");
         //３件
         assertEquals(userList.get(2).getId(), 3);
         assertEquals(userList.get(2).getName(), "キラメキ花子");
        }
}