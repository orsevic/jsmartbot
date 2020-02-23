package com.jsmartbot.bot.services;

import java.util.Map;
import java.util.UUID;

import com.jsmartbot.auth.api.services.AuthService;
import com.jsmartbot.bot.api.sevices.UserDataService;
import com.jsmartbot.bot.api.utils.Either;
import com.jsmartbot.bot.api.utils.Left;
import com.jsmartbot.bot.dao.UserStateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

//@Primary
//@Service("userDataService")
//public class UserDataServiceStubImpl implements UserDataService {
//    @Autowired
//    private AuthService authService;
//    @Autowired
//    private UserStateDao userStateDao;
//
//    @Override
//    public void set(UUID userId, UUID groupId, String propertyName, String value){
//
//    }
//
//    @Override
//    public UUID set(UUID userId, UUID groupId, String propertyName, Map<String, String> value) {
//        return null;
//    }
//
//    @Override
//    public Either<String, Map<String, String>> get(UUID userId, UUID groupId, String propertyName) {
//        return new Left<>("Joe");
//    }
//
//    @Override
//    public Map<String, Object> getTree(UUID userId, UUID groupId) {
//        return null;
//    }
//
//    @Override
//    public void delete(UUID userId, UUID groupId, String propertyName) {
//
//    }
//
//}
