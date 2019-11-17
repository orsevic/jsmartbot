package com.jsmartbot.bot.configurations;

import com.jsmartbot.bot.services.BotService;
import com.jsmartbot.bot.services.BotServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

/**
 * @author sergeyorlov
 **/
@Configuration
public class HttpInvokerConfiguration {
    @Bean(name = "/bot") HttpInvokerServiceExporter accountService() {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService( new BotServiceImpl() );
        exporter.setServiceInterface( BotService.class );
        return exporter;
    }
}
