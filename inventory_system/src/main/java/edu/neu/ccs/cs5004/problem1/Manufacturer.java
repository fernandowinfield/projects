package edu.neu.ccs.cs5004.problem1;

/**
 * Class represents the manufacturer of products.
 */
public class Manufacturer {

  private String productManufacturer;

  public Manufacturer(String manufacturer) {
    this.productManufacturer = manufacturer;
  }

  /**
   * Getter for the name of the manufacturer.
   *
   * @return Name of manufacturer.
   */
  public String getManufacturer() {
    return productManufacturer;
  }

  /**
   * Setter for the name of the manufacturer.
   *
   * @param manufacturer the name of manufacturer.
   */
  public void setManufacturer(String manufacturer) {
    this.productManufacturer = manufacturer;
  }
}
