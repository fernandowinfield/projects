package edu.neu.ccs.cs5004.problem1.customer;

import edu.neu.ccs.cs5004.problem1.IProduct;

import java.util.Map.Entry;
import java.util.Set;

/**
 * IShoppingCart is a interface representing a shopping cart.
 */
public interface IShoppingCart {
  /**
   * Add a number of products to the shopping cart.
   *
   * @param product the product to be added.
   * @param num the number of the product to be added.
   */
  void add(IProduct product, int num);

  /**
   * remove a product to the shopping cart entirely.
   *
   * @param product the product to be removed.
   */
  void remove(IProduct product);

  /**
   * Get the total cost of the product.
   *
   * @return the total cost.
   */
  Double getTotalCost();

  /**
   * Check if the cart is empty and return the truth value of the result.
   *
   * @return the truth value of the cart being empty.
   */
  boolean isEmpty();

  /**
   * Get a entrySet of all the cart products and number of them  as class Entry.
   *
   * @return the set view of the key value pairs as class Entry.
   */
  Set<Entry<IProduct, Integer>> entrySet();

  /**
   * Get the number of the input product, or -1 if there is null.
   *
   * @param product the product to be searched.
   * @return the number of the product.
   */
  Integer getNum(IProduct product);
}
