package inventorySystem;


/**
 * Class represents the Product Name of products.
 */
public class ProductName {

  private String productsName;

  public ProductName(String productName) {
    this.productsName = productName;
  }

  /**
   * Getter for the name of the product name.
   *
   * @return Name of product.
   */
  public String getProductName() {
    return productsName;
  }

  /**
   * Setter for the name of the product.
   *
   * @param productName the name of product.
   */
  public void setProductName(String productName) {
    this.productsName = productName;
  }
}