package com.example.lab1;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Test2 {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private MockMvc mockMvc;
    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    @Order(1)
    public void deposit1() throws Exception {
        this.mockMvc.perform(post("/deposit?mount=1000")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.mount").value(1000))
                .andExpect(jsonPath("$.type").value("deposit"));
    }


    @Test
    @Order(2)
    public void interest() throws Exception {
        this.mockMvc.perform(post("/interest?percentage=10")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.mount").value(100))
                .andExpect(jsonPath("$.type").value("interest"));
    }


    @Test
    @Order(3)
    public void deposit2() throws Exception {
        this.mockMvc.perform(post("/deposit?mount=1000")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.mount").value(1000))
                .andExpect(jsonPath("$.type").value("deposit"));
    }

    @Test
    @Order(4)
    public void balance() throws Exception {
        this.mockMvc.perform(get("/balance")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.balance").value(2100));
    }

    @AfterAll
    public void tearDown() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "transaction");
    }

}