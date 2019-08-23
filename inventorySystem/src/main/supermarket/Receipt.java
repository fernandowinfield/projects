package inventorySystem.supermarket;

import inventorySystem.IProduct;

import java.util.List;

/**
 * IReceipt is a interface representing a receipt.
 */
public final class Receipt implements IReceipt {

  private List<IProduct> insufficientItems;
  private List<IProduct> removedItems;
  private List<IProduct> purchasedItems;
  private Double totalPrice;

  /**
   * Construct a new receipt instance.
   *
   * @param insufficientItems the list of out-of-stock items removed.
   * @param removedItems the list of age-restrictive items removed.
   * @param purchasedItems the list of items purchased successfully.
   * @param totalPrice the total price of the items purchased successfully.
   */
  public Receipt(
      List<IProduct> insufficientItems,
      List<IProduct> removedItems,
      List<IProduct> purchasedItems,
      Double totalPrice) {
    this.insufficientItems = insufficientItems;
    this.removedItems = removedItems;
    this.purchasedItems = purchasedItems;
    this.totalPrice = totalPrice;
  }

  @Override
  /**
   * {@inheritDoc}.
   */
  public List<IProduct> getInsufficientItems() {
    return insufficientItems;
  }

  @Override
  /**
   * {@inheritDoc}.
   */
  public List<IProduct> getRemovedItems() {
    return removedItems;
  }

  @Override
  /**
   * {@inheritDoc}.
   */
  public List<IProduct> getPurchasedItems() {
    return purchasedItems;
  }

  @Override
  /**
   * {@inheritDoc}.
   */
  public Double getTotalPrice() {
    return totalPrice;
  }
}
