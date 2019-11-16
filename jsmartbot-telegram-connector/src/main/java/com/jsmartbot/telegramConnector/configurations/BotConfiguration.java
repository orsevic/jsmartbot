package com.jsmartbot.telegramConnector.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

/**
 * @author sergeyorlov
 **/
@Configuration
public class BotConfiguration {
    @Value("${telegram.proxy.enabled:false}")
    private boolean proxyEnabled;

    @Value("${telegram.proxy.host:127.0.0.1}")
    private String proxyHost;

    @Value("${telegram.proxy.port:8080}")
    private int proxyPort;
    @Bean
    public DefaultBotOptions botOptions() {

        DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
        if (proxyEnabled) {
            botOptions.setProxyHost(proxyHost);
            botOptions.setProxyPort(proxyPort);
            // Select proxy type: [HTTP|SOCKS4|SOCKS5] (default: NO_PROXY)
            botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);
        }
        return botOptions;
    }
}
