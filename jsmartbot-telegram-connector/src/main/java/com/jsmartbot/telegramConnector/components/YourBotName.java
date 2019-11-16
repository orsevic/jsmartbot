package com.jsmartbot.telegramConnector.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

  @Component
  public class YourBotName extends TelegramLongPollingBot {

    @Value("${telegram.bot.name:NAME}")
    private String botName;

    @Value("${telegram.bot.token:TOKEN}")
    private String botToken;

    public YourBotName(DefaultBotOptions options) {
      super(options);
    }

    @Override
    public void onUpdateReceived(Update update) {
      try {
        if (update.hasMessage()) {
          Message message = update.getMessage();
          if (message.hasText() || message.hasLocation()) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.enableMarkdown(true);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            sendMessage.setText("TEST");

              execute(sendMessage);
          }
        }
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }

    @Override
    public String getBotUsername() {
      return botName;
    }

    @Override
    public String getBotToken() {
      return botToken;
    }
  //Bot body.
  }