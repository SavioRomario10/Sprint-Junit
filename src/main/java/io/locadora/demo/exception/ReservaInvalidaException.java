package io.locadora.demo.exception;

public class ReservaInvalidaException extends RuntimeException {
  public ReservaInvalidaException(String message) {
    super(message);
  }
}