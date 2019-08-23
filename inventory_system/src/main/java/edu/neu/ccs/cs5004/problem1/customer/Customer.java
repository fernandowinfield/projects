package edu.neu.ccs.cs5004.problem1.customer;

import edu.neu.ccs.cs5004.problem1.IProduct;
import edu.neu.ccs.cs5004.problem1.supermarket.IReceipt;
import edu.neu.ccs.cs5004.problem1.supermarket.ISupermarket;
import edu.neu.ccs.cs5004.problem1.supermarket.Supermarket;
import edu.neu.ccs.cs5004.problem1.supermarket.SupermarketNotCreatedException;

/**
 * ICustomer is a interface representing a customer to a supermarket's online ordering system.
 */
public class Customer implements ICustomer {
  private ISupermarket market;
  private String name;
  private Integer age;
  private ShoppingCart cart;

  /**
   * Create a new Customer instance.
   *
   * @param age the age of the customer
   * @param name the name of the customer
   * @throws SupermarketNotCreatedException a Supermarket instance should be created before
   *     it can have any customer. If the supermarket is not instantiated before constructing
   *     an customer, this exception will be thrown.
   */
  public Customer(String name, Integer age) throws SupermarketNotCreatedException {
    this.name = name;
    this.age = age;
    this.market = Supermarket.getInstance();
    this.cart = new ShoppingCart();
  }

  @Override
  /**
   * {@inheritDoc}.
   */
  public void add(IProduct product) {
    add(product, 1);
  }

  @Override
  /**
   * {@inheritDoc}.
   */
  public void add(IProduct product, int num) {
    try {
      if (!market.checkAvailability(product, 1) == true) {
        throw new InsufficientStockException();
      }
    } catch (InsufficientStockException e) {
      System.out.println(e.getMessage());
      return;
    }
    this.cart.add(product, num);
  }

  @Override
  /**
   * {@inheritDoc}.
   */
  public IReceipt makeOrder() {
    IReceipt receipt = null;
    try {
      if (cart.isEmpty()) {
        throw new EmptyCartException();
      }
      receipt = market.processOrder(this.cart, this);
    } catch (EmptyCartException e) {
      System.out.println(e.getMessage());
      return null;
    }
    return receipt;
  }

  @Override
  /**
   * {@inheritDoc}.
   */
  public String getName() {
    return name;
  }

  @Override
  /**
   * {@inheritDoc}.
   */
  public Integer getAge() {
    return age;
  }
}
