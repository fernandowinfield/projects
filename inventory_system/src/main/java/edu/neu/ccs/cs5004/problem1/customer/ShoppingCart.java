package edu.neu.ccs.cs5004.problem1.customer;

import edu.neu.ccs.cs5004.problem1.IProduct;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

/**
 * IShoppingCart is a interface representing a shopping cart with a hashmap
 * storing info on the products and number of them.
 */
public class ShoppingCart implements IShoppingCart {
  HashMap<IProduct, Integer> cart;

  /**
   * Construct a new empty shopping cart.
   */
  public ShoppingCart() {
    this.cart = new HashMap<>();
  }

  @Override
  /**
   * {@inheritDoc}.
   */
  public void add(IProduct product, int num) {
    int newNum = num + cart.getOrDefault(product, 0);
    cart.put(product, newNum);
  }

  @Override
  /**
   * {@inheritDoc}.
   */
  public void remove(IProduct product) {
    cart.remove(product);
  }

  @Override
  /**
   * {@inheritDoc}.
   */
  public Double getTotalCost() {
    Double cost = 0.0;
    for (Entry<IProduct, Integer> entry : cart.entrySet()) {
      cost += entry.getKey().getPrice().getPrice() * entry.getValue();
    }
    return cost;
  }

  @Override
  /**
   * {@inheritDoc}.
   */
  public boolean isEmpty() {
    return this.cart.isEmpty();
  }

  @Override
  /**
   * {@inheritDoc}.
   */
  public Set<Entry<IProduct, Integer>> entrySet() {
    return cart.entrySet();
  }

  @Override
  /**
   * {@inheritDoc}.
   */
  public Integer getNum(IProduct product) {
    return cart.getOrDefault(product, -1);
  }

}
