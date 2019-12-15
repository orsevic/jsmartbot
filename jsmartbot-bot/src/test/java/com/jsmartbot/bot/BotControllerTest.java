package com.jsmartbot.bot;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jsmartbot.bot.api.dto.AnswerDto;
import com.jsmartbot.bot.api.dto.QuestionDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;

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
@ActiveProfiles(value = {"default", "test"})
public class BotControllerTest {
    @Test
    public void addTest() throws Exception {
        UUID questionId = UUID.randomUUID();
        String questionText = "Which english level do you have?";
        String json = objectMapper.writeValueAsString(new QuestionDto(questionId, questionText,
                Collections.singletonList(new AnswerDto(UUID.randomUUID(), "intermediate"))));

        MvcResult result = mockMvc.perform( request(HttpMethod.PUT, "/admin-api/add", json) )
                .andExpect(status().isOk())
                .andReturn();
        List<QuestionDto> questions = objectMapper.readValue(result.getResponse().getContentAsString(),
                new TypeReference<List<QuestionDto>>(){});
        log.info("Result  {}", questions);
        Assert.assertEquals(1, questions.size());
        Assert.assertEquals(questionId, questions.get(0).getId());
    }
}
