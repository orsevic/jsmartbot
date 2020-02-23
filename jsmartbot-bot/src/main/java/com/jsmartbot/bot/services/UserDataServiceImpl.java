package com.jsmartbot.bot.services;

import com.jsmartbot.auth.api.dto.UserDto;
import com.jsmartbot.auth.api.services.AuthService;
import com.jsmartbot.bot.api.sevices.UserDataService;
import com.jsmartbot.bot.api.utils.Either;
import com.jsmartbot.bot.api.utils.Left;
import com.jsmartbot.bot.dao.UserDataDao;
import com.jsmartbot.bot.dao.UserPropertyDao;
import com.jsmartbot.bot.dao.UserStateDao;
import com.jsmartbot.bot.entities.UserData;
import com.jsmartbot.bot.entities.UserProperty;
import com.jsmartbot.bot.entities.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.server.UID;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.Tuple;
import javax.transaction.NotSupportedException;

@Service
public class UserDataServiceImpl implements UserDataService {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserStateDao userStateDao;
    @Autowired
    private UserPropertyDao userPropertyDao;
    @Autowired
    private UserDataDao userDataDao;

    @Override
    public void set(UUID userId, UUID groupId, String propertyName, String value){
        //TODO groupId is not supported yet
        UserProperty property =
                userPropertyDao.findOneByNameAndParentId(propertyName, null).orElseThrow(() -> new IllegalArgumentException("Can not find property"));
        Optional<UserData> userData = userDataDao.findOneByUserIdAndPropertyIdAndParentId(userId, property.getId(), groupId);

        if (!userData.isPresent()) {
            userData = Optional.of(new UserData(userId, groupId, property.getId(), value));
            userDataDao.save(userData.get());
            return;
        }
        userData.get().setValue(value);
    }

    @Override
    public UUID set(UUID userId, UUID groupId, String propertyName, Map<String, String> value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Either<String, Map<String, String>> get(UUID userId, UUID groupId, String propertyName) {
        //TODO groupId is not supported yet
        UserProperty property =
                userPropertyDao.findOneByNameAndParentId(propertyName, null).orElseThrow(() -> new IllegalArgumentException("Can not find property"));
        Optional<UserData> userData = userDataDao.findOneByUserIdAndPropertyIdAndParentId(userId, property.getId(), groupId);
        //TODO now it is working only with strings and not group
        return new Left(userData.map(UserData::getValue).orElse(null));
    }

    @Override
    public Map<String, Object> getTree(UUID userId, UUID groupId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(UUID userId, UUID groupId, String propertyName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<UUID> findUsersByProperty(UUID groupId, String propertyName, String value) {
        //TODO groupId is not supported yet
        UserProperty property =
                userPropertyDao.findOneByNameAndParentId(propertyName, null).orElseThrow(() -> new IllegalArgumentException("Can not find property"));
        return userDataDao.findUsersByPropertyValue(property.getId(), value);
    }
}
