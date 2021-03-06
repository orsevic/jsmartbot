package com.jsmartbot.bot.services;

import com.jsmartbot.auth.api.dto.UserDto;
import com.jsmartbot.auth.api.services.AuthService;
import com.jsmartbot.bot.api.sevices.UserDataService;
import com.jsmartbot.bot.api.utils.Either;
import com.jsmartbot.bot.dao.UserStateDao;
import com.jsmartbot.bot.entities.UserData;
import com.jsmartbot.bot.entities.UserState;
import org.springframework.beans.factory.annotation.Autowired;

import java.rmi.server.UID;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class UserDataServiceImpl implements UserDataService {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserStateDao userStateDao;

    @Override
    public void set(UUID userId, UUID groupId, String propertyName, String value){

    }

    @Override
    public UUID set(UUID userId, UUID groupId, String propertyName, Map<String, String> value) {
        return null;
    }

    @Override
    public Either<String, Map<String, String>> get(UUID userId, UUID groupId, String propertyName) {
        return null;
    }

    @Override
    public Map<String, Object> getTree(UUID userId, UUID groupId) {
        return null;
    }

    @Override
    public void delete(UUID userId, UUID groupId, String propertyName) {

    }

}
