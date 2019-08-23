package inventorySystem;

import java.util.Objects;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShampooTest {

  private Shampoo shampoo;
  private Manufacturer manufacturerShampoo;
  private ProductName productNameShampoo;
  private Price priceShampoo;
  private MinimumAge ageShampoo;
  private Units unitsShampoo;
  private Shampoo secondShampoo;
  private Shampoo thirdShampoo;
  private Manufacturer manufacturerShampooThird;
  private ProductName productNameThirdShampoo;
  private Price priceThirdShampoo;
  private MinimumAge ageThirdShampoo;

  private Beer beer;
  private Manufacturer manufacturerBeer;
  private ProductName productNameBeer;
  private Price priceBeer;
  private MinimumAge ageBeer;
  private Weight weightBeer;


  @Before
  public void setUp() throws Exception {
    manufacturerShampoo = new Manufacturer("Dove");
    productNameShampoo = new ProductName("Shampoo");
    priceShampoo = new Price(20.0);
    ageShampoo = new MinimumAge(0);
    unitsShampoo = new Units(10);
    shampoo = new Shampoo(manufacturerShampoo, productNameShampoo, priceShampoo,
        ageShampoo, unitsShampoo);
    manufacturerShampooThird = new Manufacturer("Not Dove");
    productNameThirdShampoo = new ProductName("Not Shampoo");
    priceThirdShampoo = new Price(100.0);
    ageThirdShampoo = new MinimumAge(100);
    secondShampoo = new Shampoo(manufacturerShampoo, productNameShampoo, priceShampoo,
        ageShampoo, unitsShampoo);

    manufacturerBeer = new Manufacturer("Rainier");
    productNameBeer = new ProductName("Beer");
    priceBeer = new Price(9.99);
    ageBeer = new MinimumAge(21);
    weightBeer = new Weight(5.0);
    beer = new Beer(manufacturerBeer, productNameBeer, priceBeer, ageBeer,
        weightBeer);
  }


  @Test
  public void getUnits() {
    assertEquals(10, shampoo.getUnits().getUnits());

  }

  @Test
  public void getManufacturer() {
    assertEquals("Dove", shampoo.getManufacturer().getManufacturer());
  }

  @Test
  public void getProductName() {
    assertEquals("Shampoo", shampoo.getProductName().getProductName());
  }

  @Test
  public void getPrice() {
    assertEquals(20.0, shampoo.getPrice().getPrice(), 0);
  }

  @Test
  public void getMinimumAge() {
    assertEquals(0, shampoo.getMinimumAge().getMinimumAge());
  }

  @Test
  public void equals() {
    thirdShampoo = new Shampoo(manufacturerShampooThird, productNameThirdShampoo, priceThirdShampoo,
        ageThirdShampoo, unitsShampoo);
    assertTrue(shampoo.equals(shampoo));
    assertTrue(secondShampoo.equals(shampoo));
    assertFalse(thirdShampoo.equals(shampoo));
    thirdShampoo = new Shampoo(manufacturerShampoo, productNameThirdShampoo, priceThirdShampoo,
        ageThirdShampoo, unitsShampoo);
    assertFalse(thirdShampoo.equals(shampoo));
    thirdShampoo = new Shampoo(manufacturerShampoo, productNameShampoo, priceThirdShampoo,
        ageThirdShampoo, unitsShampoo);
    assertFalse(thirdShampoo.equals(shampoo));
    thirdShampoo = new Shampoo(manufacturerShampoo, productNameShampoo, priceShampoo,
        ageThirdShampoo, unitsShampoo);
    assertFalse(thirdShampoo.equals(shampoo));
    assertFalse(shampoo.equals(null));
    assertFalse(beer.equals(shampoo));
  }

  @Test
  public void testHashCode() {
    assertEquals(Objects.hash(manufacturerShampoo, productNameShampoo, priceShampoo, ageShampoo),
        shampoo.hashCode());
    assertNotEquals(Objects.hash(manufacturerShampoo, productNameShampoo, priceShampoo),
        shampoo.hashCode());
  }
}