package edu.neu.ccs.cs5004.problem1.customer;

import edu.neu.ccs.cs5004.problem1.IProduct;
import edu.neu.ccs.cs5004.problem1.supermarket.IReceipt;

/**
 * ICustomer is a interface representing a customer to a supermarket's
 * online ordering system.
 */
public interface ICustomer {
  /**
   * Add one product to the shopping cart.
   *
   * @param product the product to be added
   */
  void add(IProduct product);

  /**
   * Add multiple product to the shopping cart.
   *
   * @param product the product to be added
   * @param num the number of the product to be added
   */
  void add(IProduct product, int num);

  /**
   * Make an order based on items in the shopping cart to the supermarket.
   *
   * @return the receipt of the order based on items in the shopping cart
   */
  IReceipt makeOrder();

  /**
   * Get the name of the customer.
   *
   * @return the name of the customer
   */
  String getName();

  /**
   * Get the age of the customer.
   *
   * @return the age of the customer
   */
  Integer getAge();
}
