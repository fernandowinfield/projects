package inventorySystem.customer;

/**
 * InsufficientStockException represents exceptions when a customer is adding product that has is
 * not enough stock.
 */
public class EmptyCartException extends Exception {

  /**
   * Creates a new InsufficientStockException instance.
   */
  public EmptyCartException() {
    super("The action is not allowed when the cart is empty.");
  }
}