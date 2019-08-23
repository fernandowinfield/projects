package inventorySystem.supermarket;

import static inventorySystem.supermarket.SupermarketBuilder.*;
import static org.junit.Assert.*;

import inventorySystem.customer.Customer;
import inventorySystem.customer.ICustomer;
import org.junit.BeforeClass;
import org.junit.Test;

public class SupermarketTest {

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

  public static void setUpCustomer() throws SupermarketNotCreatedException {
    customerName1 = "Ken";
    customerName2 = "Marry";
    age1 = 17;
    age2 = 21;
    customer1 = new Customer(customerName1, age1);
    customer2 = new Customer(customerName2, age2);
  }

  @Test
  public void processOrder_AgeRemoval() throws SupermarketNotCreatedException {
    supermarket = Supermarket.getInstance();
    setUpCustomer();
    customer1.add(product1);
    customer1.add(product3, 3);
    IReceipt receipt = customer1.makeOrder();
    assertEquals(1, receipt.getPurchasedItems().size());
    assertEquals(product3, receipt.getPurchasedItems().get(0));
    assertEquals(1, receipt.getRemovedItems().size());
    assertEquals(product1, receipt.getRemovedItems().get(0));
    assertEquals(0, receipt.getInsufficientItems().size());
    assertEquals(Double.valueOf(product3.getPrice().getPrice() * 3), receipt.getTotalPrice());
    //check inventory
    assertTrue(inventory.checkAvailability(product3, 97));
    assertFalse(inventory.checkAvailability(product3, 98));
    //refill purchased products in the inventory
    inventory.updateStock(product3, -3);
  }

  @Test
  public void processOrder_OutOfStockRemoval() throws SupermarketNotCreatedException {
    supermarket = Supermarket.getInstance();
    setUpCustomer();
    customer2.add(product1);
    customer2.add(product3, 101);
    IReceipt receipt = customer2.makeOrder();
    assertEquals(1, receipt.getPurchasedItems().size());
    assertEquals(product1, receipt.getPurchasedItems().get(0));
    assertEquals(0, receipt.getRemovedItems().size());
    assertEquals(1, receipt.getInsufficientItems().size());
    assertEquals(product3, receipt.getInsufficientItems().get(0));
    assertEquals(Double.valueOf(product1.getPrice().getPrice() * 1), receipt.getTotalPrice());
    //check inventory
    assertTrue(inventory.checkAvailability(product1, 99));
    assertFalse(inventory.checkAvailability(product1, 100));
    assertTrue(inventory.checkAvailability(product3, 100));
    assertFalse(inventory.checkAvailability(product3, 101));
    //refill purchased products in the inventory
    inventory.updateStock(product1, -1);
  }

  @Test
  public void processOrder_OutOfStockSubstitution() throws SupermarketNotCreatedException {
    supermarket = Supermarket.getInstance();
    setUpCustomer();
    customer2.add(product1, 101);
    IReceipt receipt = customer2.makeOrder();
    assertEquals(1, receipt.getPurchasedItems().size());
    assertEquals(product2, receipt.getPurchasedItems().get(0));
    assertEquals(0, receipt.getRemovedItems().size());
    assertEquals(0, receipt.getInsufficientItems().size());
    assertEquals(Double.valueOf(product2.getPrice().getPrice() * 101), receipt.getTotalPrice());
    //check inventory
    assertTrue(inventory.checkAvailability(product2, 0));
    assertFalse(inventory.checkAvailability(product2, 1));
    //refill purchased products in the inventory
    inventory.updateStock(product2, -101);
  }

  @Test
  public void processOrder_EmptyOrder() throws SupermarketNotCreatedException {
    supermarket = Supermarket.getInstance();
    setUpCustomer();
    IReceipt receipt = customer1.makeOrder();
    assertNull(receipt);
  }

  @Test
  public void checkAvailability() {
    assertTrue(inventory.checkAvailability(product1, 100));
    assertFalse(inventory.checkAvailability(product1, 101));
    assertTrue(inventory.checkAvailability(product2, 101));
    assertFalse(inventory.checkAvailability(product2, 102));
    assertTrue(inventory.checkAvailability(product3, 100));
    assertFalse(inventory.checkAvailability(product3, 101));
  }
}