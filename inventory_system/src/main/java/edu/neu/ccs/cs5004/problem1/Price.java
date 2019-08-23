package edu.neu.ccs.cs5004.problem1;


/**
 * Class represents the Price of products.
 */
public class Price {

  private Double productPrice;

  public Price(Double price) {
    this.productPrice = price;
  }

  /**
   * Getter for the price of the product.
   *
   * @return Price of product.
   */
  public Double getPrice() {
    return productPrice;
  }

  /**
   * Setter for the price of the product.
   *
   * @param price the price of product.
   */
  public void setPrice(Double price) {
    this.productPrice = price;
  }
}