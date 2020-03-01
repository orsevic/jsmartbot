package com.jsmartbot.auth.api.services;

import com.jsmartbot.auth.api.dto.UserDto;

public interface AuthService {
    UserDto findOrCreateTelegramUser(String telegramId, String telegramUserName, String firstName, String lastName);
}
