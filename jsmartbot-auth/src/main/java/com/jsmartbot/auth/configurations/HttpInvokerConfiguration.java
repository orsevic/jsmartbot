package com.jsmartbot.auth.configurations;

import com.jsmartbot.auth.api.services.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

@Configuration
public class HttpInvokerConfiguration {
    @Bean(name = "/auth") HttpInvokerServiceExporter accountService(AuthService authService) {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(authService);
        exporter.setServiceInterface( AuthService.class );
        return exporter;
    }
}
