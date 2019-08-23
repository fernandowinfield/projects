package edu.neu.ccs.cs5004.problem1;

import java.util.Objects;

/**
 * Abstract class Product encodes common states and behaviors among different supermarket products.
 */
public abstract class Product implements IProduct {

  protected Manufacturer manufacturer;
  protected ProductName productName;
  protected Price price;
  protected MinimumAge minimumAge;

  /**
   * Creates an abstract product.
   *
   * @param manufacturer the manufacturer of the product.
   * @param productName the name of the product.
   * @param price the price of the product.
   * @param minimumAge the minimum age of product.
   */
  public Product(Manufacturer manufacturer, ProductName productName,
      Price price, MinimumAge minimumAge) {
    this.manufacturer = manufacturer;
    this.productName = productName;
    this.price = price;
    this.minimumAge = minimumAge;
  }

  @Override
  public Manufacturer getManufacturer() {
    return manufacturer;
  }

  @Override
  public ProductName getProductName() {
    return productName;
  }

  @Override
  public Price getPrice() {
    return price;
  }

  @Override
  public MinimumAge getMinimumAge() {
    return minimumAge;
  }

  @Override
  public boolean equals(Object item) {
    if (this == item) {
      return true;
    }
    if (item == null || getClass() != item.getClass()) {
      return false;
    }
    Product product = (Product) item;
    return Objects.equals(manufacturer, product.manufacturer)
        && Objects.equals(productName, product.productName)
        && Objects.equals(price, product.price)
        && Objects.equals(minimumAge, product.minimumAge);
  }

  @Override
  public int hashCode() {
    return Objects.hash(manufacturer, productName, price, minimumAge);
  }
}
