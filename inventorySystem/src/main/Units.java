package inventorySystem;

/**
 * Class represents the number of Units of a Household product.
 */
public class Units {

  private int productUnits;

  public Units(int units) {
    this.productUnits = units;
  }

  /**
   * Getter for the number of units for household product.
   *
   * @return int number of units of household product.
   */
  public int getUnits() {
    return productUnits;
  }

  /**
   * Setter for number of units for household product.
   *
   * @param units the number of household products.
   */
  public void setUnits(int units) {
    this.productUnits = units;
  }
}