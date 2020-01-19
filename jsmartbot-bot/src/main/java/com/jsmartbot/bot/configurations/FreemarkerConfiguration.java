package com.jsmartbot.bot.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sergeyorlov
 **/
@Configuration
public class FreemarkerConfiguration {
    @Bean
    public FreemarkerEngine freemarkerEngine() {
        return new FreemarkerEngine();
    }
}
