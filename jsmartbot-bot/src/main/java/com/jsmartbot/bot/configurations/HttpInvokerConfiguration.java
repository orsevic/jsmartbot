package com.jsmartbot.bot.configurations;

import com.jsmartbot.auth.api.services.AuthService;
import com.jsmartbot.bot.api.sevices.BotService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

/**
 * @author sergeyorlov
 **/
@Configuration
public class HttpInvokerConfiguration {
    @Value("${jsmartbot.auth.service.url:http://localhost:8083/auth}")
    private String authServiceUrl;

    @Bean(name = "/bot") HttpInvokerServiceExporter botServiceExporter(BotService botService) {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(botService);
        exporter.setServiceInterface( BotService.class );
        return exporter;
    }
    @Bean
    public HttpInvokerProxyFactoryBean authServiceInvoker() {
        HttpInvokerProxyFactoryBean invoker = new HttpInvokerProxyFactoryBean();
        invoker.setServiceUrl(authServiceUrl);
        invoker.setServiceInterface(AuthService.class);
        return invoker;
    }
}
