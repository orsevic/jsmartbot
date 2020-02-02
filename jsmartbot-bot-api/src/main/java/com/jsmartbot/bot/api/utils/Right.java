package com.jsmartbot.bot.api.utils;

public class Right<A, B> implements Either<A, B> {
  public final B value;
  public Right(B value) {
    this.value = value;
  }
}