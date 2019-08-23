package inventorySystem;

/**
 * Class represents the Weight (in ounces) of a Grocery product.
 */
public class Weight {

  private Double productWeight;

  public Weight(Double weight) {
    this.productWeight = weight;
  }

  /**
   * Getter for the weight of grocery product.
   *
   * @return int weight of grocery product.
   */
  public Double getWeight() {
    return productWeight;
  }

  /**
   * Setter for the weight of grocery product.
   *
   * @param weight for the grocery product.
   */
  public void setWeight(Double weight) {
    this.productWeight = weight;
  }
}