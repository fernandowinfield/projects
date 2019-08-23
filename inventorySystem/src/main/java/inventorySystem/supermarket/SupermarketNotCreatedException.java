package inventorySystem.supermarket;

/**
 * SupermarketNotCreatedException represents exceptions when a customer is making order from the
 * supermarket when it is not instantiated.
 */
public class SupermarketNotCreatedException extends Exception {

  /**
   * Creates a new InsufficientStockException instance.
   */
  public SupermarketNotCreatedException() {
    super("Supermarket is not instantiated or ready to receive order.");
  }
}