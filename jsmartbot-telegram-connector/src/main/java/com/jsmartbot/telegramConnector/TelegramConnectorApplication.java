package com.jsmartbot.telegramConnector;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class TelegramConnectorApplication {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(args));


        ApiContextInitializer.init();

        SpringApplication.run(TelegramConnectorApplication.class, args);
    }
}
