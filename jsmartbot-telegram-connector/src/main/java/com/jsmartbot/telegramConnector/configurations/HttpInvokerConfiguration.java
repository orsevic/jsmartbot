package com.jsmartbot.telegramConnector.configurations;

import com.jsmartbot.auth.api.services.AuthService;
import com.jsmartbot.bot.api.sevices.BotService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

/**
 * @author sergeyorlov
 **/
@Configuration
public class HttpInvokerConfiguration {
    @Value("${jsmartbot.bot.service.url:http://localhost:8081/bot}")
    private String botServiceUrl;

    @Value("${jsmartbot.auth.service.url:http://localhost:8083/auth}")
    private String authServiceUrl;

    @Bean
    public HttpInvokerProxyFactoryBean invoker() {
        HttpInvokerProxyFactoryBean invoker = new HttpInvokerProxyFactoryBean();
        invoker.setServiceUrl(botServiceUrl);
        invoker.setServiceInterface(BotService.class);
        return invoker;
    }
    @Bean
    public HttpInvokerProxyFactoryBean authServiceInvoker() {
        HttpInvokerProxyFactoryBean invoker = new HttpInvokerProxyFactoryBean();
        invoker.setServiceUrl(authServiceUrl);
        invoker.setServiceInterface(AuthService.class);
        return invoker;
    }
}
