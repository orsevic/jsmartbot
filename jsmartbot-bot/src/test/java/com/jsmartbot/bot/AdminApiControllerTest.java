package com.jsmartbot.bot;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsmartbot.bot.api.dto.Question;
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

import java.util.List;
import java.util.UUID;

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
public class AdminApiControllerTest {
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
    public void addTest() throws Exception {
        String json = objectMapper.writeValueAsString(new Question(UUID.randomUUID(),"eqeqe"));

        MvcResult result = mockMvc.perform( request(HttpMethod.PUT, "/admin-api/add", json) )
                .andExpect(status().isOk())
                .andReturn();
        List<Question> question = objectMapper.readValue(result.getResponse().getContentAsString(),
                                                        new TypeReference<List<Question>>(){});
        log.info("Result  {}", question);
    }

    @Test
    public void listTest() throws Exception {

        MvcResult result = mockMvc.perform( request(HttpMethod.GET, "/admin-api/list", null) )
                .andExpect(status().isOk())
                .andReturn();

        List<Question> questions = objectMapper.readValue(result.getResponse().getContentAsString(),
                new TypeReference<List<Question>>(){});
        log.info("Result  {}", questions);

        Assert.assertNotNull("List of questions has to have question about name",
                questions.stream().filter(question -> question.text.contains("your name")).findAny().orElse(null));
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
