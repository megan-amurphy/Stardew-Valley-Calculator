package org.mmurphy.sdvcalculator.exception;

public class CropNotFoundException extends RuntimeException {
  public CropNotFoundException(String message) {
    super(message);
  }
}
