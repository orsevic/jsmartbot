package com.jsmartbot.bot.entities;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class UserData {
    @Id
    private UUID id;
    private String userId;
    private UUID parentId;
    private UUID userProperty;
    private String value;

    public UserData(){
    }

    public UserData(String userId, UUID parentId, UUID userProperty, String value){
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.parentId = parentId;
        this.userProperty = userProperty;
        this.value = value;
    }


}
