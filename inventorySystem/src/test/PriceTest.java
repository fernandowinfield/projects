package inventorySystem;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PriceTest {


  private Price one;
  private Price two;
  private double priceOne;
  private double priceTwo;

  @Before
  public void setUp() throws Exception {
    priceOne = 20.0;
    priceTwo = 30.0;
    one = new Price(priceOne);
    two = new Price(priceTwo);
  }

  @Test
  public void getWeight() {
    assertEquals(20.0, one.getPrice(), 0);
    assertEquals(30.0, two.getPrice(), 0);

  }

  @Test
  public void setWeight() {
    one.setPrice(priceTwo);
    assertEquals(30, priceTwo, 0);
  }
}
