package com.jsmartbot.bot.api.sevices;

import com.jsmartbot.bot.api.utils.Either;

import java.util.Map;
import java.util.UUID;

public interface UserDataService {
    /**
     *
     * @param userId
     * @param groupId необязательное. Если не указано, записывается в корень. Идентификатор значения UserDataId
     * @param propertyName
     * @param value
     */
    public void set(UUID userId, UUID groupId, String propertyName, String value);

    /**
     * Добавление группы
     * @param groupId необязательное. Если не указано, записывается в корень. Идентификатор значения UserDataId
     * @param propertyName
     * @param value
     */
    public UUID set(UUID userId, UUID groupId, String propertyName, Map<String, String> value);

    /**
     *
     * @param groupId необязательное. Идентификатор значения UserDataId. Если не указан,
     * @param propertyName необязательное.
     * @return
     */
    public Either<String, Map<String, String>> get(UUID userId, UUID groupId, String propertyName);

    /**
     *
     * @param groupId
     * @return
     */
    public Map<String, Object> getTree(UUID userId, UUID groupId);

    public void delete(UUID userId, UUID groupId, String propertyName);
}
