package com.jsmartbot.bot.configurations;

import com.jsmartbot.auth.api.services.AuthService;
import com.jsmartbot.bot.api.sevices.BotService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

/**
 * @author sergeyorlov
 **/
@Configuration
public class HttpInvokerConfiguration {
    @Bean(name = "/bot") HttpInvokerServiceExporter botServiceExporter(BotService botService) {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(botService);
        exporter.setServiceInterface( BotService.class );
        return exporter;
    }
    @Bean
    public HttpInvokerProxyFactoryBean authServiceInvoker() {
        HttpInvokerProxyFactoryBean invoker = new HttpInvokerProxyFactoryBean();
        invoker.setServiceUrl("http://localhost:8083/auth");
        invoker.setServiceInterface(AuthService.class);
        return invoker;
    }
}
