package com.challenge.price.domain.exceptions;

public class PriceNotFoundException extends RuntimeException {
  public PriceNotFoundException(String message) {
    super(message);
  }
}
