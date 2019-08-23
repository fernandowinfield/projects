package inventorySystem;

/**
 * Class represents the Minimum Age for the product.
 */
public class MinimumAge {

  private int minAge;

  public MinimumAge(int minAge) {
    this.minAge = minAge;
  }

  /**
   * Getter for the minimum age for the product name.
   *
   * @return int minimum age for product.
   */
  public int getMinimumAge() {
    return minAge;
  }

  /**
   * Setter for the minimum age for the product.
   *
   * @param minAge for the product.
   */
  public void setMinimumAge(int minAge) {
    this.minAge = minAge;
  }
}