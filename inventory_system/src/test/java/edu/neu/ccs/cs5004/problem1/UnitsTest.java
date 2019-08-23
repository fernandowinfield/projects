package edu.neu.ccs.cs5004.problem1;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitsTest {

  private Units one;

  @Before
  public void setUp() throws Exception {
    one = new Units (10);
  }

  @Test
  public void getUnits() {
    assertEquals(10, one.getUnits());
  }

  @Test
  public void setUnits() {
    one.setUnits(20);
    assertEquals(20, one.getUnits());
  }
}