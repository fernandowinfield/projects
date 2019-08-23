package edu.neu.ccs.cs5004.problem1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StockItemTest {

  private StockItem shampooStock;
  private StockItem beerStock;
  private Shampoo shampoo;
  private Beer beer;
  private Quantity shampooQuantity;
  private Manufacturer manufacturerShampoo;
  private ProductName productNameShampoo;
  private Price priceShampoo;
  private MinimumAge ageShampoo;
  private Units unitsShampoo;
  private Quantity beerQuantity;
  private Manufacturer manufacturerBeer;
  private ProductName productNameBeer;
  private Price priceBeer;
  private MinimumAge ageBeer;
  private Weight weightBeer;

  @Before
  public void setUp() throws Exception {
    manufacturerShampoo = new Manufacturer("Dove");
    productNameShampoo = new ProductName("Shampoo");
    priceShampoo = new Price(10.0);
    ageShampoo = new MinimumAge(0);
    unitsShampoo = new Units(5);
    shampoo = new Shampoo(manufacturerShampoo, productNameShampoo, priceShampoo,
        ageShampoo, unitsShampoo);
    shampooQuantity = new Quantity(100);
    shampooStock = new StockItem(shampoo, shampooQuantity);

    manufacturerBeer = new Manufacturer("Rainier");
    productNameBeer = new ProductName("Beer");
    priceBeer = new Price(5.0);
    ageBeer = new MinimumAge(21);
    weightBeer = new Weight(20.0);
    beer = new Beer(manufacturerBeer, productNameBeer, priceBeer, ageBeer,
        weightBeer);
    beerQuantity = new Quantity(50);
    beerStock = new StockItem(beer, beerQuantity);
  }

  @Test
  public void getProduct() {
    assertEquals(shampoo, shampooStock.getProduct());
    assertEquals(beer, beerStock.getProduct());
  }

  @Test
  public void getQuantity() {
    assertEquals(100, shampooStock.getQuantity().getQuantity());
    assertEquals(50, beerStock.getQuantity().getQuantity());
  }

  @Test
  public void checkStock() {
    assertTrue(shampooStock.checkStock(10));
    assertFalse(shampooStock.checkStock(200));
    assertTrue(beerStock.checkStock(30));
    assertFalse(beerStock.checkStock(100));
  }

  @Test
  public void updateQuantity() {
    shampooStock.updateQuantity(30);
    assertEquals(70, shampooStock.getQuantity().getQuantity());
    beerStock.updateQuantity(50);
    assertEquals(0, beerStock.getQuantity().getQuantity());
  }

  @Test
  public void getRetailValue() {
    assertEquals(1000.0, shampooStock.getRetailValue(), 0);
    assertEquals(250.0, beerStock.getRetailValue(),0);
  }
}