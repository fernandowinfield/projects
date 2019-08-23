package edu.neu.ccs.cs5004.problem1.supermarket;

/**
 * SupermarketNotCreatedException represents exceptions when the there
 * is an attempt to instantiate supermarket after it has been instantiated.
 */
public class SupermarketCreatedAgainException extends Exception {
  /**
   * Creates a new InsufficientStockException instance.
   */
  public SupermarketCreatedAgainException() {
    super("Supermarket is not instantiated or ready to receive order.");
  }
}