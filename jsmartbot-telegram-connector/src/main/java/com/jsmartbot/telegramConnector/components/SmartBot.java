package com.jsmartbot.telegramConnector.components;

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

import java.util.Collections;
import java.util.UUID;

@Component
  public class SmartBot extends TelegramLongPollingBot {
  protected final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${telegram.bot.name:NAME}")
    private String botName;

    @Value("${telegram.bot.token:TOKEN}")
    private String botToken;

//    @Autowired
//    private BotService botService;

    public SmartBot(DefaultBotOptions options) {
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
//            PhraseDto nextQuestion = botService.answerQuestion(chatId, null, message.getText());
//            sendQuestionWithAnswers(chatId, update, nextQuestion);

            SendMessage sendMessage = new SendMessage();
            sendMessage.enableMarkdown(true);
            sendMessage.setChatId(chatId);
//    sendMessage.set
            sendMessage.setText("TEST message");
            InlineKeyboardMarkup answerButtons = new InlineKeyboardMarkup();

              InlineKeyboardButton answerButton = new InlineKeyboardButton();
              answerButton.setText("1 кнопка");
              answerButton.setCallbackData("1");
              answerButtons.getKeyboard().add(Collections.singletonList(answerButton));

            InlineKeyboardButton answerButton2 = new InlineKeyboardButton();
            answerButton2.setText("2 кнопка");
              answerButton2.setCallbackData("2");
            answerButtons.getKeyboard().add(Collections.singletonList(answerButton2));

            sendMessage.setReplyMarkup(answerButtons);
            execute(sendMessage);
          }
        }
        if (update.hasCallbackQuery()) {
          CallbackQuery callbackQuery = update.getCallbackQuery();

          String chatId = String.valueOf(callbackQuery.getMessage().getChatId());
//          PhraseDto nextQuestion = botService.answerQuestion(chatId, UUID.fromString(callbackQuery.getData()), null);
//          sendQuestionWithAnswers(chatId, update, nextQuestion);

        }

      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }

  private void sendQuestionWithAnswers(String chatId, Update update, PhraseDto nextQuestion) throws org.telegram.telegrambots.meta.exceptions.TelegramApiException {
    SendMessage sendMessage = new SendMessage();
    sendMessage.enableMarkdown(true);
    sendMessage.setChatId(chatId);
//    sendMessage.set
    sendMessage.setText(nextQuestion.getText());

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