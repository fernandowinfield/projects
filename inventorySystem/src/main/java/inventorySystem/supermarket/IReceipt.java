package inventorySystem.supermarket;

import inventorySystem.IProduct;

import java.util.List;

/**
 * IReceipt is a class representing a supermarket receipt with its details: the list of out-of-stock
 * items that were removed, the list of age-restrictive items that were removed, the list of
 * successfully purchased items, and the total price.
 */
public interface IReceipt {

  /**
   * Get the list of items removed from the customer's order because they are out of stock during
   * the order processing by the Supermarket.
   *
   * @return the list of out-of-stock items that were removed.
   */
  List<IProduct> getInsufficientItems();

  /**
   * Get the list of items removed from the customer's order because the customer's age disallows
   * he/she from buying.
   *
   * @return the list of age-restrictive items that were removed.
   */
  List<IProduct> getRemovedItems();

  /**
   * Get the list of the successfully purchased items in the customer's order.
   *
   * @return the list of successfully purchased items.
   */
  List<IProduct> getPurchasedItems();

  /**
   * Get the total price of all the successfully purchased items.
   *
   * @return the list of out-of-stock items that were removed.
   */
  Double getTotalPrice();
}
