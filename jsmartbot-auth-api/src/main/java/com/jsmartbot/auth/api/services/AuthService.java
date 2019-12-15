package com.jsmartbot.auth.api.services;

import com.jsmartbot.auth.api.dto.UserDto;

public interface AuthService {
    UserDto findOrCreateUser(String telegramId);
}
