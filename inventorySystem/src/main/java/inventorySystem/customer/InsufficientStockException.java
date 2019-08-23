package inventorySystem.customer;

/**
 * InsufficientStockException represents exceptions when a customer is adding product that has is
 * not enough stock.
 */
public class InsufficientStockException extends Exception {

  /**
   * Creates a new InsufficientStockException instance.
   */
  public InsufficientStockException() {
    super("Adding product to chart failed : Insufficient stock.");
  }
}