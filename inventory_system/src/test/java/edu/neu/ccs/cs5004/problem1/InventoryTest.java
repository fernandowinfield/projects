package edu.neu.ccs.cs5004.problem1;

import static org.junit.Assert.*;

import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

/**
 * A test class that unit tests the Inventory class.
 */
public class InventoryTest {

  private IInventory emptyInventory;
  private IInventory inventory1;
  private IInventory inventory2;
  private IProduct manchego;
  private StockItem stockManchego;
  private IProduct shampoo;
  private StockItem stockShampoo;
  private IProduct oaxaca;
  private StockItem stockOaxaca;
  private IProduct otherShampoo;
  private StockItem stockOtherShampoo;

  @Before
  public void setUp() {
    emptyInventory = new Inventory();
    inventory1 = new Inventory();
    manchego = new Cheese(new Manufacturer("Chalet"), new ProductName("Manchego cheese"), new
        Price(2.50), new MinimumAge(0), new Weight(4.55));
    stockManchego = new StockItem(manchego, new Quantity(20));
    inventory1.getGroceryStock().put(manchego, stockManchego);
    shampoo = new Shampoo(new Manufacturer("Herbal Essences"), new
        ProductName("chamomile shampoo"), new Price(4.0), new MinimumAge(0), new Units(1));
    stockShampoo = new StockItem(shampoo, new Quantity(8));
    oaxaca = new Cheese(new Manufacturer("La Joya"), new ProductName("oaxaca cheese"), new
        Price(1.19), new MinimumAge(0), new Weight(6.22));
    stockOaxaca = new StockItem(oaxaca, new Quantity(24));
    otherShampoo = new Shampoo(new Manufacturer("Dove Men"), new
        ProductName("shampoo"), new Price(2.35), new MinimumAge(0), new Units(1));
    stockOtherShampoo = new StockItem(otherShampoo, new Quantity(100));
    inventory2 = new Inventory();
    inventory2.getHouseholdStock().put(otherShampoo, stockOtherShampoo);

  }

  @Test
  public void addNewStockItem() {
    emptyInventory.addNewStockItem(stockManchego);
    assertEquals(emptyInventory, inventory1);
  }

  @Test
  public void getRetailValue() {
    inventory1.getHouseholdStock().put(shampoo, stockShampoo);
    assertEquals(new Double(82.0), inventory1.getRetailValue());
  }

  @Test
  public void updateStock() {
    inventory1.updateStock(manchego, 5);
    assertEquals(15,
        inventory1.getGroceryStock().get(manchego).getQuantity().getQuantity());
  }

  @Test
  public void checkAvailability() {
    assertTrue(inventory1.checkAvailability(manchego, 13));
    assertFalse(inventory1.checkAvailability(manchego, 23));
  }

  @Test
  public void findSubstitute() {
    inventory1.getGroceryStock().put(oaxaca, stockOaxaca);
    assertEquals(oaxaca, inventory1.findSubstitute(manchego, 24));
    assertEquals(otherShampoo, inventory2.findSubstitute(shampoo, 25));
  }

  @Test
  public void equals() {
    assertEquals(emptyInventory, emptyInventory);
    assertNotEquals(inventory1, emptyInventory);
    assertNotEquals(inventory1, null);
    emptyInventory.getGroceryStock().put(manchego, stockManchego);
    assertEquals(inventory1, emptyInventory);
  }

  @Test
  public void getGroceryStock() {
    HashMap<IProduct, StockItem> stockMap = new HashMap<>();
    stockMap.put(manchego, stockManchego);
    assertEquals(stockMap, inventory1.getGroceryStock());
  }

  @Test
  public void getHouseholdStock() {
    inventory1.addNewStockItem(stockShampoo);
    HashMap<IProduct, StockItem> stockMap = new HashMap<>();
    stockMap.put(shampoo, stockShampoo);
    assertEquals(stockMap, inventory1.getHouseholdStock());
  }
}