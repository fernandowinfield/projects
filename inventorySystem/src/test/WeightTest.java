package inventorySystem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WeightTest {

  private Weight one;
  private Weight two;
  private double weightOne;
  private double weightTwo;

  @Before
  public void setUp() throws Exception {
    weightOne = 10.0;
    weightTwo = 20.5;
    one = new Weight(weightOne);
    two = new Weight(weightTwo);
  }

  @Test
  public void getWeight() {
    assertEquals(10.0, one.getWeight(), 0);
    assertEquals(20.5, two.getWeight(), 0);

  }

  @Test
  public void setWeight() {
    one.setWeight(weightTwo);
    assertEquals(20.5, weightTwo, 0);
  }
}
