package edu.neu.ccs.cs5004.problem1;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BeerTest {
  private Beer beer;
  private Manufacturer manufacturerBeer;
  private ProductName productNameBeer;
  private Price priceBeer;
  private MinimumAge ageBeer;
  private Weight weightBeer;

  @Before
  public void setUp() throws Exception {
    manufacturerBeer = new Manufacturer("Rainier");
    productNameBeer = new ProductName("Beer");
    priceBeer = new Price(9.99);
    ageBeer = new MinimumAge(21);
    weightBeer = new Weight(5.0);
    beer = new Beer(manufacturerBeer, productNameBeer, priceBeer, ageBeer,
        weightBeer);
  }

  @Test
  public void getWeight() {
    assertEquals(5.0, beer.getWeight().getWeight(), 0);
  }

  @Test
  public void getManufacturer() {
    assertEquals("Rainier", beer.getManufacturer().getManufacturer());
  }

  @Test
  public void getProductName() {
    assertEquals("Beer", beer.getProductName().getProductName());
  }

  @Test
  public void getPrice() {
    assertEquals(9.99, beer.getPrice().getPrice(), 0);
  }

  @Test
  public void getMinimumAge() {
    assertEquals(21, beer.getMinimumAge().getMinimumAge());
  }
}