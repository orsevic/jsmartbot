package com.jsmartbot.bot.services;

import com.jsmartbot.auth.api.dto.UserDto;
import com.jsmartbot.auth.api.services.AuthService;
import com.jsmartbot.bot.dao.UserStateDao;
import com.jsmartbot.bot.entities.UserData;
import com.jsmartbot.bot.entities.UserState;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

public class UserDataServiceImpl {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserStateDao userStateDao;

    public void set(String userId, UUID groupId, String propertyName, String value){

    }

}
