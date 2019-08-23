package edu.neu.ccs.cs5004.problem1;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuantityTest {

  private Quantity one;

  @Before
  public void setUp() throws Exception {
    one = new Quantity(10);
  }

  @Test
  public void getQuantity() {
    assertEquals(10, one.getQuantity());
  }

  @Test
  public void setQuantity() {
    one.setQuantity(20);
    assertEquals(20, one.getQuantity());
  }
}