package edu.neu.ccs.cs5004.problem1.supermarket;

import edu.neu.ccs.cs5004.problem1.IProduct;
import edu.neu.ccs.cs5004.problem1.customer.Customer;
import edu.neu.ccs.cs5004.problem1.customer.ShoppingCart;

/**
 * ISupermarket is a interface representing a supermarket.
 */
public interface ISupermarket {
  /**
   * Make an order based on items in the shopping cart to the supermarket.
   *
   * @param cart the cart with all the ordered products and number of the products.
   * @param customer the customer that placed the order.
   * @return the receipt of the order based on items in the shopping cart.
   */
  IReceipt processOrder(ShoppingCart cart, Customer customer);

  /**
   * check if the interested product is available.
   *
   * @param num the number of items requested
   * @param product the product requested
   * @return the truth value that the number of the requested product is available
   *     in the supermarket's inventory
   */
  Boolean checkAvailability(IProduct product, int num);
}
