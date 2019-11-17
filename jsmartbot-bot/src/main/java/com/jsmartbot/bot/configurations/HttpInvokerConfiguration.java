package com.jsmartbot.bot.configurations;

import com.jsmartbot.bot.api.sevices.BotService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

/**
 * @author sergeyorlov
 **/
@Configuration
public class HttpInvokerConfiguration {
    @Bean(name = "/bot") HttpInvokerServiceExporter accountService(BotService botService) {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(botService);
        exporter.setServiceInterface( BotService.class );
        return exporter;
    }
}
