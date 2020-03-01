package com.jsmartbot.telegramConnector.components;

import com.jsmartbot.auth.api.dto.UserDto;
import com.jsmartbot.auth.api.services.AuthService;
import com.jsmartbot.bot.api.dto.PhraseDto;
import com.jsmartbot.bot.api.sevices.BotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Collections;
import java.util.UUID;

@Component
  public class YourBotName extends TelegramLongPollingBot {
  protected final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${telegram.bot.name:NAME}")
    private String botName;

    @Value("${telegram.bot.token:TOKEN}")
    private String botToken;

    @Autowired
    private AuthService authService;

    @Autowired
    private BotService botService;

    public YourBotName(DefaultBotOptions options) {
      super(options);
    }

    @Override
    public void onUpdateReceived(Update update) {
      try {
        log.info("message {}", update);

        if (update.hasMessage()) {
          Message message = update.getMessage();
          if (message.hasText() || message.hasLocation()) {
            String chatId = String.valueOf(update.getMessage().getChatId());
            UserDto user = authService.findOrCreateTelegramUser(
                    chatId,
                    update.getMessage().getFrom().getUserName(),
                    update.getMessage().getFrom().getFirstName(),
                    update.getMessage().getFrom().getLastName()
            );
            PhraseDto nextQuestion = botService.answerQuestion(user.getId(), null, message.getText());
            sendQuestionWithAnswers(chatId, update, nextQuestion);
          }
        }
        if (update.hasCallbackQuery()) {
          CallbackQuery callbackQuery = update.getCallbackQuery();

          String chatId = String.valueOf(callbackQuery.getMessage().getChatId());
          UserDto user = authService.findOrCreateTelegramUser(
                  chatId,
                  callbackQuery.getFrom().getUserName(),
                  callbackQuery.getFrom().getFirstName(),
                  callbackQuery.getFrom().getLastName()
          );
          PhraseDto nextQuestion = botService.answerQuestion(user.getId(), UUID.fromString(callbackQuery.getData()), null);
          sendQuestionWithAnswers(chatId, update, nextQuestion);

        }

      } catch (Exception e) {
        log.error(e.getMessage(), e);
      }
    }

  private void sendQuestionWithAnswers(String chatId, Update update, PhraseDto nextQuestion) throws TelegramApiException {
    SendMessage sendMessage = new SendMessage();
    sendMessage.enableMarkdown(true);
    sendMessage.setChatId(chatId);
//    sendMessage.set
//    sendMessage.setParseMode("markdown");
//    sendMessage.enableMarkdown(true);
    sendMessage.setText(nextQuestion.getText() + " [inline mention of a user](tg://user?id=173091625)");

    InlineKeyboardMarkup answerButtons = new InlineKeyboardMarkup();
    nextQuestion.getAnswers().forEach(answer -> {
      InlineKeyboardButton answerButton = new InlineKeyboardButton();
      answerButton.setText(answer.getText());
      answerButton.setCallbackData(answer.getId().toString());
      answerButtons.getKeyboard().add(Collections.singletonList(answerButton));
    });


//            ReplyKeyboardMarkup test = new ReplyKeyboardMarkup();
//            KeyboardRow row = new KeyboardRow();
//            KeyboardButton testButton = new KeyboardButton();
////            testButton.set
//            row.add(testButton);
//            test.setKeyboard(Collections.singletonList(row));
    sendMessage.setReplyMarkup(answerButtons);


    execute(sendMessage);

//    SendContact sendContact = new SendContact();
//    sendContact.setChatId(chatId);
//    sendContact.setPhoneNumber("9268505108");
//    sendContact.setFirstName("asas");
//    SendMessage sendContact = new SendMessage();
//    sendMessage.enableMarkdown(true);
//    sendMessage.setChatId(chatId);
//    sendMessage.
//    execute(sendContact);
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