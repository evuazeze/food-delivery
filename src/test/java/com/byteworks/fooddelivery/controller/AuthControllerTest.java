package com.byteworks.fooddelivery.controller;

import com.byteworks.fooddelivery.models.Role;
import com.byteworks.fooddelivery.models.User;
import com.byteworks.fooddelivery.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/generated-docs")
public class AuthControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    private User mockUser;

    @BeforeEach
    public void setUp() {
        mockUser = new User();
        mockUser.setId(1);
        mockUser.setUsername("evuazeze");
//        mockUser.setPassword("1234");
        mockUser.setEmail("evuazeze.emmanuel@gmail.com");
//        Set<Role> roles = new HashSet<>();
//        roles.add(new Role(2, "DEV"));
//        mockUser.setRoles(roles);
    }

    @Test
    @DisplayName("POST /api/v1/auth/register")
    void testRegisterUser() throws Exception {
        // Setup mock service
        String userJson = new ObjectMapper().writeValueAsString(mockUser);

        User postUser = new User();
        postUser.setUsername("evuazeze");
        postUser.setPassword("1234");
        postUser.setEmail("evuazeze.emmanuel@gmail.com");

        doReturn(mockUser).when(userService).registerUser(any());

        mockMvc.perform(post("/api/v1/auth/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(postUser)))

                // Validate the response code and content type
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate the returned fields
                .andExpect(MockMvcResultMatchers.content().json(userJson))

                .andDo(document("/register-user",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
