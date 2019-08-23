package inventorySystem.customer;

import static inventorySystem.supermarket.SupermarketBuilder.*;
import static org.junit.Assert.*;

import inventorySystem.supermarket.SupermarketCreatedAgainException;
import inventorySystem.supermarket.SupermarketNotCreatedException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ShoppingCartTest {

  ShoppingCart cart;

  @BeforeClass
  public static void beforeClassMethod()
      throws SupermarketNotCreatedException, SupermarketCreatedAgainException {
    buildSuperMarket();
  }

  @Before
  public void setUp() {
    cart = new ShoppingCart();
    cart.add(product1, productQuantity1);
    cart.add(product2, productQuantity2);
    cart.add(product3, productQuantity3);
    cart.add(product4, productQuantity4);
    cart.add(product5, productQuantity5);
  }

  @Test
  public void add() {
    assertEquals(cart.getNum(product1), productQuantity1);
    assertEquals(cart.getNum(product2), productQuantity2);
    assertEquals(cart.getNum(product3), productQuantity3);
    assertEquals(cart.getNum(product4), productQuantity4);
    assertEquals(cart.getNum(product5), productQuantity5);
    assertEquals(cart.getNum(product6), Integer.valueOf(-1));
  }

  @Test
  public void remove() {
    cart.remove(product1);
    cart.remove(product3);
    cart.remove(product5);
    assertEquals(cart.getNum(product1), Integer.valueOf(-1));
    assertEquals(cart.getNum(product2), productQuantity2);
    assertEquals(cart.getNum(product3), Integer.valueOf(-1));
    assertEquals(cart.getNum(product4), productQuantity4);
    assertEquals(cart.getNum(product5), Integer.valueOf(-1));
    assertEquals(cart.getNum(product6), Integer.valueOf(-1));
  }

  @Test
  public void getTotalCost() {
    Double expectedValue
        = product1.getPrice().getPrice() * productQuantity1
        + product2.getPrice().getPrice() * productQuantity2
        + product3.getPrice().getPrice() * productQuantity3
        + product4.getPrice().getPrice() * productQuantity4
        + product5.getPrice().getPrice() * productQuantity5;
    assertEquals(expectedValue, cart.getTotalCost());
  }

  @Test
  public void isEmpty() {
    ShoppingCart cart2 = new ShoppingCart();
    assertFalse(cart.isEmpty());
    assertTrue(cart2.isEmpty());
  }
}