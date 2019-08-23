package inventorySystem;

/**
 * Represents the basic structure of a Stock Item.
 */
public interface IStockItem {

  /**
   * Getter the the Stock Item's product.
   *
   * @return the product of the stock.
   */
  IProduct getProduct();

  /**
   * Getter for the Stock Item's quantity (in store).
   *
   * @return the Quantity object for the product.
   */
  Quantity getQuantity();

  /**
   * Checks to see if there is enough stock in store to make the purchase.
   *
   * @param purchase the amount of product to be purchased.
   * @return Boolean true if there is enough stock, false otherwise.
   */
  Boolean checkStock(int purchase);

  /**
   * Updates the quantity of the Stock Item if a purchase is made.
   *
   * @param purchased the amount of product that was purchased.
   */
  void updateQuantity(int purchased);


  /**
   * Returns the total retail value of the stock item (price of item * quantity in stock).
   *
   * @return the total retail value of the stock item.
   */
  Double getRetailValue();

}
