package com.devsuperior.demo.controllers;

import tools.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.test.json.JsonCompareMode;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class ProductControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String obtainAccessToken(String username, String password) throws Exception {
        String body = mockMvc.perform(post("/oauth2/token")
                .with(httpBasic("myclientid", "myclientsecret"))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("grant_type", "password")
                .param("username", username)
                .param("password", password))
                .andReturn()
                .getResponse()
                .getContentAsString();

        return objectMapper.readTree(body).get("access_token").asText();
    }

    @Test
    void findAllShouldReturnSeededProducts() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [{"id": 1, "name": "TV"}, {"id": 2, "name": "Computer"}]
                        """, JsonCompareMode.STRICT));
    }

    @Test
    void findByIdShouldReturn401WhenNotAuthenticated() throws Exception {
        mockMvc.perform(get("/products/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void findByIdShouldReturnProduct1WhenAuthenticated() throws Exception {
        String token = obtainAccessToken("alex@gmail.com", "123456");

        mockMvc.perform(get("/products/1")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\": 1, \"name\": \"TV\"}", JsonCompareMode.STRICT));
    }

    @Test
    void findByIdShouldReturnProduct2WhenAuthenticated() throws Exception {
        String token = obtainAccessToken("alex@gmail.com", "123456");

        mockMvc.perform(get("/products/2")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\": 2, \"name\": \"Computer\"}", JsonCompareMode.STRICT));
    }

    @Test
    void insertShouldReturn401WhenNotAuthenticated() throws Exception {
        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Tablet\"}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void insertShouldReturn403WhenOperatorLogged() throws Exception {
        String token = obtainAccessToken("alex@gmail.com", "123456");

        mockMvc.perform(post("/products")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Tablet\"}"))
                .andExpect(status().isForbidden());
    }

    @Test
    void insertShouldReturnCreatedProductWhenAdminLogged() throws Exception {
        String token = obtainAccessToken("maria@gmail.com", "123456");

        mockMvc.perform(post("/products")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Tablet\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"name\": \"Tablet\"}", JsonCompareMode.LENIENT));
    }
}
