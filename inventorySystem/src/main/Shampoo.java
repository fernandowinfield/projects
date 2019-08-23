package inventorySystem;

/**
 * Class represents the common states and behavior Shampoo.
 */
public class Shampoo extends AHousehold {

  public Shampoo(Manufacturer manufacturer, ProductName productName, Price price, MinimumAge
      minimumAge, Units units) {
    super(manufacturer, productName, price, minimumAge, units);
  }
}
