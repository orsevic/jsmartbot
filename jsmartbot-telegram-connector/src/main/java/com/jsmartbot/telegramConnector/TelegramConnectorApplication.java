package com.jsmartbot.telegramConnector;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class TelegramConnectorApplication {
//    private static String PROXY_USER = "" /* proxy user */;
//    private static String PROXY_PASSWORD = "" /* proxy password */;
    private static String PROXY_USER = "socks_user" /* proxy user */;
    private static String PROXY_PASSWORD = "tele2" /* proxy password */;
    public static void main(String[] args) {

        System.out.println(Arrays.toString(args));
//        if (args.length > 0) {
            //Add this line to initialize bots context
            // Create the Authenticator that will return auth's parameters for proxy authentication
            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(PROXY_USER, PROXY_PASSWORD.toCharArray());
                }
            });
//        }


        ApiContextInitializer.init();

        SpringApplication.run(TelegramConnectorApplication.class, args);
    }
}
