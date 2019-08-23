package inventorySystem;

/**
 * Abstract class AHousehold encodes common states and behaviors of different household products.
 */
public abstract class AHousehold extends Product {

  protected Units units;

  public AHousehold(Manufacturer manufacturer, ProductName productName,
      Price price, MinimumAge minimumAge, Units units) {
    super(manufacturer, productName, price, minimumAge);
    this.units = units;
  }

  /**
   * Getter for the Units of Household product.
   *
   * @return the Units of product.
   */
  public Units getUnits() {
    return units;
  }

}
