package com.jsmartbot.bot.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sergeyorlov
 **/
@Configuration
public class JavaScriptConfiguration {

    @Bean
    public JavaScriptEngine javaScriptEngine () {
        return new JavaScriptEngine();
    }

}
