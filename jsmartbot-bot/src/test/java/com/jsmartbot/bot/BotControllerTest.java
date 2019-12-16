package com.jsmartbot.bot;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsmartbot.bot.api.dto.AnswerDto;
import com.jsmartbot.bot.api.dto.AnswerQuestionDto;
import com.jsmartbot.bot.api.dto.QuestionDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        classes = BotApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "spring.liquibase.contexts=test,common",
                "spring.liquibase.drop-first=true"
        }
)
@ActiveProfiles(value = {"default", "test", "local"})
public class BotControllerTest {

    protected final Logger log = LoggerFactory.getLogger(getClass());
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webContext;

    @Autowired
    protected ObjectMapper objectMapper;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webContext)
                .build();
    }
    @Test
    public void answerQuestionTest() throws Exception {
        UUID questionId = UUID.randomUUID();
        String questionText = "Which english level do you have?";
        String json = objectMapper.writeValueAsString(new AnswerQuestionDto("1111111", questionId, questionText));

        MvcResult result = mockMvc.perform( request(HttpMethod.POST, "/bot-api/answer-question", json) )
                .andExpect(status().isOk())
                .andReturn();
        QuestionDto question = objectMapper.readValue(result.getResponse().getContentAsString(), QuestionDto.class);
        log.info("Result  {}", question);
        Assert.assertNotNull(question);
    }

    protected MockHttpServletRequestBuilder request(HttpMethod method, String path, String content){
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.request(method, path)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        if(content != null){
            return builder.content(content);
        }

        return builder;
    }
}
