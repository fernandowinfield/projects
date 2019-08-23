package inventorySystem.supermarket;

import static inventorySystem.supermarket.SupermarketBuilder.*;
import static inventorySystem.supermarket.SupermarketBuilder.inventory;
import static inventorySystem.supermarket.SupermarketBuilder.product1;
import static inventorySystem.supermarket.SupermarketBuilder.product2;
import static inventorySystem.supermarket.SupermarketBuilder.product3;
import static org.junit.Assert.*;

import inventorySystem.IProduct;
import inventorySystem.customer.Customer;
import inventorySystem.customer.ICustomer;
import java.util.List;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class ReceiptTest {

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
  public void setUpCustomer() throws SupermarketNotCreatedException {
    customerName1 = "Kenneth";
    customerName2 = "Marry";
    age1 = 17;
    age2 = 21;
    customer1 = new Customer(customerName1, age1);
    customer2 = new Customer(customerName2, age2);
  }

  public boolean ListContains(List<IProduct> products, IProduct target) {
    for (IProduct product : products) {
      if (product == target) {
        return true;
      }
    }
    return false;
  }

  @Test
  public void getInsufficientItems() {
    customer2.add(product1);
    customer2.add(product3, 101);
    IReceipt receipt = customer2.makeOrder();
    assertEquals(1, receipt.getInsufficientItems().size());
    assertEquals(product3, receipt.getInsufficientItems().get(0));
    assertEquals(Double.valueOf(product1.getPrice().getPrice() * 1), receipt.getTotalPrice());
    //refill purchased products in the inventory
    inventory.updateStock(product1, -1);
  }

  @Test
  public void getRemovedItems() {
    customer1.add(product1, 100);
    customer1.add(product2, 100);
    customer1.add(product3, 100);
    customer1.add(product4, 100);
    customer1.add(product5, 100);
    customer1.add(product6, 100);
    customer1.add(product7, 100);
    IReceipt receipt = customer1.makeOrder();
    assertEquals(2, receipt.getRemovedItems().size());
    assertTrue(ListContains(receipt.getRemovedItems(), product1));
    assertTrue(ListContains(receipt.getRemovedItems(), product2));
    //refill purchased products in the inventory
    inventory.updateStock(product3, -100);
    inventory.updateStock(product4, -100);
    inventory.updateStock(product5, -100);
    inventory.updateStock(product6, -100);
    inventory.updateStock(product7, -100);
  }

  @Test
  public void getPurchasedItems() {
    customer1.add(product1, 100);
    customer1.add(product2, 100);
    customer1.add(product3, 200);
    customer1.add(product4, 100);
    customer1.add(product5, 100);
    customer1.add(product6, 100);
    customer1.add(product7, 200);
    IReceipt receipt = customer1.makeOrder();
    assertEquals(3, receipt.getPurchasedItems().size());
    assertTrue(ListContains(receipt.getPurchasedItems(), product4));
    assertTrue(ListContains(receipt.getPurchasedItems(), product5));
    assertTrue(ListContains(receipt.getPurchasedItems(), product6));
    //refill purchased products in the inventory
    inventory.updateStock(product4, -100);
    inventory.updateStock(product5, -100);
    inventory.updateStock(product6, -100);
  }

  @Test
  public void getTotalPrice() throws SupermarketNotCreatedException {
    setUpCustomer();
    customer1.add(product1, 1);
    customer1.add(product2, 2);
    customer1.add(product3, 3);
    customer1.add(product4, 4);
    customer1.add(product5, 5);
    customer1.add(product6, 200);
    customer1.add(product7, 101);
    IReceipt receipt = customer1.makeOrder();
    Double expectedValue
        = product3.getPrice().getPrice() * 3
        + product4.getPrice().getPrice() * 4
        + product5.getPrice().getPrice() * 5;
    assertEquals(expectedValue, receipt.getTotalPrice());
    //refill purchased products in the inventory
    inventory.updateStock(product3, -3);
    inventory.updateStock(product4, -4);
    inventory.updateStock(product5, -5);
  }
}