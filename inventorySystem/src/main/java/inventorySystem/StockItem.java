package inventorySystem;

/**
 * Class represents the Stock Item of a product and quantity in the store.
 */
public class StockItem implements IStockItem {

  private IProduct product;
  private Quantity quantity;

  public StockItem(IProduct product, Quantity quantity) {
    this.product = product;
    this.quantity = quantity;
  }

  @Override
  public IProduct getProduct() {
    return this.product;
  }

  @Override
  public Quantity getQuantity() {
    return this.quantity;
  }

  @Override
  public Boolean checkStock(int purchase) {
    return (this.quantity.getQuantity() >= purchase);
  }

  @Override
  public void updateQuantity(int purchased) {
    // Assuming that checkStock has been called prior to this method.
    int newQuantity;
    // Calculate the difference between stock and purchased.
    newQuantity = this.quantity.getQuantity() - purchased;
    // Set new quantity for Stock Item.
    this.quantity.setQuantity(newQuantity);
  }

  /**
   * Returns the total retail value of the stock item (price of item * quantity in stock).
   *
   * @return the total retail value of the stock item.
   */
  @Override
  public Double getRetailValue() {
    return this.product.getPrice().getPrice() * this.quantity.getQuantity();
  }

}
