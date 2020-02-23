package com.jsmartbot.bot.api.utils;

public interface Either<A, B>{
    public A getLeft();
    public B getRight();
    public Object getValue();
};

