package com.jsmartbot.telegramConnector.configurations;

import com.jsmartbot.bot.api.sevices.BotService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

/**
 * @author sergeyorlov
 **/
@Configuration
public class BotServiceConfiguration {
    @Bean
    public HttpInvokerProxyFactoryBean invoker() {
        HttpInvokerProxyFactoryBean invoker = new HttpInvokerProxyFactoryBean();
        invoker.setServiceUrl("http://localhost:8081/bot");
        invoker.setServiceInterface(BotService.class);
        return invoker;
    }
}
