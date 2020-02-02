package com.jsmartbot.bot.api.utils;

public class Left<A, B> implements Either<A, B> {
  public final A value;
  public Left(A value) {
    this.value = value;
  }
}