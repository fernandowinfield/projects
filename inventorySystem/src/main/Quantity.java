package inventorySystem;

/**
 * Class represents the quantity of products in store.
 */
public class Quantity {

  private int qty;

  public Quantity(int quantity) {
    this.qty = quantity;
  }

  /**
   * Getter for the quantity of products in store.
   *
   * @return the number of quantity for product.
   */
  public int getQuantity() {
    return qty;
  }

  /**
   * Setter for the number of quantity of products in store.
   *
   * @param quantity the number of products in store.
   */
  public void setQuantity(int quantity) {
    this.qty = quantity;
  }
}
