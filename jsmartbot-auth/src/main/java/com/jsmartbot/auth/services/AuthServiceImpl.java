package com.jsmartbot.auth.services;

import com.jsmartbot.auth.api.dto.UserDto;
import com.jsmartbot.auth.api.services.AuthService;
import com.jsmartbot.auth.dao.UserDao;
import com.jsmartbot.auth.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.ZoneOffset;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public UserDto findOrCreateTelegramUser(String telegramId, String telegramUserName, String firstName, String lastName) {
        Optional<User> user = userDao.findOneByTelegramId(telegramId);
        if (!user.isPresent()){
            user = Optional.of(new User(telegramId, telegramUserName, firstName, lastName));
            User createdUser = userDao.save(user.get());
            user = Optional.of(createdUser);
        }

        return user.map(entity -> new UserDto(entity.getId(), entity.getTelegramId(), entity.getFirstName(), entity.getLastName(),
                entity.getTelegramUserName(), entity.getLastEntry().toInstant(ZoneOffset.UTC))).orElse(null);
    }
}
