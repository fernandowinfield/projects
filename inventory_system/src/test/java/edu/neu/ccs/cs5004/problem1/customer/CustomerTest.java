package edu.neu.ccs.cs5004.problem1.customer;

import static edu.neu.ccs.cs5004.problem1.supermarket.SupermarketBuilder.*;
import static edu.neu.ccs.cs5004.problem1.supermarket.SupermarketBuilder.inventory;
import static edu.neu.ccs.cs5004.problem1.supermarket.SupermarketBuilder.product1;
import static edu.neu.ccs.cs5004.problem1.supermarket.SupermarketBuilder.product2;
import static edu.neu.ccs.cs5004.problem1.supermarket.SupermarketBuilder.product3;
import static org.junit.Assert.*;

import edu.neu.ccs.cs5004.problem1.supermarket.SupermarketCreatedAgainException;
import edu.neu.ccs.cs5004.problem1.supermarket.SupermarketNotCreatedException;
import edu.neu.ccs.cs5004.problem1.supermarket.SupermarketTest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CustomerTest {
  static ICustomer customer1;
  static ICustomer customer2;
  static String customerName1;
  static String customerName2;
  static Integer age1;
  static Integer age2;

  @BeforeClass
  public static void beforeClassMethod()
      throws SupermarketNotCreatedException, SupermarketCreatedAgainException {
    buildSuperMarket();
  }

  @Before
  public void setUpCustomer() throws SupermarketNotCreatedException  {
    customerName1 = "Kate";
    customerName2 = "Marry";
    age1 = 17;
    age2 = 21;
    customer1 = new Customer(customerName1, age1);
    customer2 = new Customer(customerName2, age2);
  }

  @Test
  public void addOne() {
    customer2.add(product1);
    customer2.add(product2);
    customer2.add(product3);
    customer2.add(product4);
    customer2.add(product5);
    customer2.makeOrder();
    assertTrue(inventory.checkAvailability(product1,productQuantity1 - 1));
    assertTrue(inventory.checkAvailability(product2,productQuantity2 - 1));
    assertTrue(inventory.checkAvailability(product3,productQuantity3 - 3));
    assertTrue(inventory.checkAvailability(product4,productQuantity4 - 4));
    assertTrue(inventory.checkAvailability(product5,productQuantity5 - 5));
    //refill purchased products in the inventory
    inventory.updateStock(product1, -1);
    inventory.updateStock(product2, -1);
    inventory.updateStock(product3, -1);
    inventory.updateStock(product4, -1);
    inventory.updateStock(product5, -1);
  }

  @Test
  public void addOutOfStock() {
    customer2.add(product1, 100);
    customer2.add(product2, 102);
    customer2.add(product3, 100);
    customer2.add(product4, 102);
    customer2.add(product5, 100);
    customer2.add(product6, 102);
    customer2.add(product7, 100);
    customer2.makeOrder();
    assertTrue(inventory.checkAvailability(product1,productQuantity1 - 100));
    assertTrue(inventory.checkAvailability(product3,productQuantity3 - 100));
    assertTrue(inventory.checkAvailability(product5,productQuantity5 - 100));
    assertTrue(inventory.checkAvailability(product7,productQuantity7 - 100));
    //refill purchased products in the inventory
    inventory.updateStock(product1, -100);
    inventory.updateStock(product3, -100);
    inventory.updateStock(product5, -100);
    inventory.updateStock(product7, -100);
  }

  @Test
  public void addMultiple() {
    customer2.add(product1, 1);
    customer2.add(product2, 2);
    customer2.add(product3, 3);
    customer2.add(product4, 4);
    customer2.add(product5, 5);
    customer2.add(product6, 6);
    customer2.add(product7, 100);
    customer2.makeOrder();
    assertTrue(inventory.checkAvailability(product1,productQuantity1 - 1));
    assertTrue(inventory.checkAvailability(product2,productQuantity2 - 2));
    assertTrue(inventory.checkAvailability(product3,productQuantity3 - 3));
    assertTrue(inventory.checkAvailability(product4,productQuantity4 - 4));
    assertTrue(inventory.checkAvailability(product5,productQuantity5 - 5));
    assertTrue(inventory.checkAvailability(product6,productQuantity6 - 6));
    assertTrue(inventory.checkAvailability(product7,productQuantity7 - 100));
    //refill purchased products in the inventory
    inventory.updateStock(product1, -1);
    inventory.updateStock(product2, -2);
    inventory.updateStock(product3, -3);
    inventory.updateStock(product4, -4);
    inventory.updateStock(product5, -5);
    inventory.updateStock(product6, -6);
    inventory.updateStock(product7, -100);
  }

  @Test
  public void makeOrder() throws SupermarketNotCreatedException {
    SupermarketTest test = new SupermarketTest();
    test.processOrder_EmptyOrder();
    test.processOrder_AgeRemoval();
    test.processOrder_OutOfStockRemoval();
    test.processOrder_OutOfStockSubstitution();
  }

  @Test
  public void getName() {
    assertEquals(customerName1, customer1.getName());
    assertEquals(customerName2, customer2.getName());
  }

  @Test
  public void getAge() {
    assertEquals(age1, customer1.getAge());
    assertEquals(age2, customer2.getAge());
  }
}