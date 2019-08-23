package edu.neu.ccs.cs5004.problem1;

/**
 * Abstract class AGrocery encodes common states and behaviors of different grocery products.
 */
public abstract class AGrocery extends Product {

  protected Weight weight;

  public AGrocery(Manufacturer manufacturer, ProductName productName,
      Price price, MinimumAge minimumAge, Weight weight) {
    super(manufacturer, productName, price, minimumAge);
    this.weight = weight;
  }

  /**
   * Getter for the weight of the Grocery product.
   *
   * @return the Weight of the product.
   */
  public Weight getWeight() {
    return weight;
  }

}
