package io.locadora.demo.exceptions;

public class ReservaInvalidaException extends RuntimeException {
  public ReservaInvalidaException(String message) {
    super(message);
  }
}