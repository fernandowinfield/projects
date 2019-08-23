package inventorySystem;

/**
 * Represents the basic structure of a Product.
 */
public interface IProduct {

  /**
   * Getter for the Manufacturer of product.
   *
   * @return Manufacturer of product.
   */
  Manufacturer getManufacturer();

  /**
   * Getter for the Product Name of product.
   *
   * @return Product Name of product.
   */
  ProductName getProductName();

  /**
   * Getter for the Print of product.
   *
   * @return Price of the product.
   */
  Price getPrice();

  /**
   * Getter for the Minimum Age of product.
   *
   * @return Minimum Age of product.
   */
  MinimumAge getMinimumAge();
}
