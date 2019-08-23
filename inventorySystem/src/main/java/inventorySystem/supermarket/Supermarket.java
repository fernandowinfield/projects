package inventorySystem.supermarket;

import inventorySystem.IInventory;
import inventorySystem.IProduct;
import inventorySystem.customer.Customer;
import inventorySystem.customer.ShoppingCart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 * Supermarket is a class representing a supermarket with the inventory and order processing methods
 * of it. This class should and can only be instantiated once and retrieved by
 */
public class Supermarket implements ISupermarket {

  private IInventory inventory;
  private static Supermarket instance;

  /**
   * Construct a new Supermarket instance with the input inventory.
   *
   * @param inventory the new supermarket's inventory.
   */
  private Supermarket(IInventory inventory) {
    this.inventory = inventory;
  }

  /**
   * Get the supermarket instance after it is created.
   *
   * @return the supermarket instance.
   * @throws SupermarketNotCreatedException the exception thrown when there is an attempt to get the
   * only supermarket instance before it has been instantiated.
   */
  public static Supermarket getInstance() throws SupermarketNotCreatedException {
    if (Supermarket.instance == null) {
      throw new SupermarketNotCreatedException();
    }
    return Supermarket.instance;
  }

  /**
   * Built the sole Supermarket instance.
   *
   * @param inventory the inventory of the new supermarket instance.
   * @return a SuperMarket instance that are built here.
   * @throws SupermarketCreatedAgainException the exception thrown when there is an attempt to
   * instantiate supermarket after it has been instantiated.
   */
  protected static ISupermarket builtSupermarket(IInventory inventory)
      throws SupermarketCreatedAgainException {
    if (Supermarket.instance != null) {
      throw new SupermarketCreatedAgainException();
    }
    Supermarket.instance = new Supermarket(inventory);
    return Supermarket.instance;
  }

  @Override
  /**
   * {@inheritDoc}.
   */
  public IReceipt processOrder(ShoppingCart cart, Customer customer) {
    List<IProduct> insufficientItems = new ArrayList<>();
    List<IProduct> removedItems = new ArrayList<>();
    List<IProduct> purchasedItems = new ArrayList<>();
    Double totalPrice = 0.0;
    fulfilOrder(cart, insufficientItems);
    removeAgeRestrictiveProduct(cart, customer.getAge(), removedItems);
    for (Entry<IProduct, Integer> entry : cart.entrySet()) {
      this.inventory.updateStock(entry.getKey(), entry.getValue());
      purchasedItems.add(entry.getKey());
      totalPrice += entry.getKey().getPrice().getPrice() * entry.getValue();
    }
    return new Receipt(insufficientItems, removedItems, purchasedItems, totalPrice);
  }

  /**
   * Helper method to remove age-restrictive products from the cart and record them in the input
   * list removedItems.
   *
   * @param cart cart from which age-restrictive products are removed.
   * @param age the age of the customer making the order.
   * @param removedItems list in which the removed items in this method are added to.
   */
  private void removeAgeRestrictiveProduct(
      ShoppingCart cart,
      Integer age,
      List<IProduct> removedItems) {
    for (Entry<IProduct, Integer> entry : cart.entrySet()) {
      if (entry.getKey().getMinimumAge().getMinimumAge() > age) {
        removedItems.add(entry.getKey());
      }
    }
    for (IProduct product : removedItems) {
      cart.remove(product);
    }
  }

  /**
   * Helper method to substitute out-of-stock products and remove un-substitutable products from the
   * cart and record them in the input list removedItems.
   *
   * @param cart cart from which age-restrictive products are removed.
   * @param insufficientItems list in which the items removed here are added to.
   */
  private void fulfilOrder(ShoppingCart cart, List<IProduct> insufficientItems) {
    ArrayList<IProduct> substitutes = new ArrayList<>();
    for (Entry<IProduct, Integer> entry : cart.entrySet()) {
      if (!inventory.checkAvailability(entry.getKey(), entry.getValue())) {
        recordSubstitute(
            entry.getKey(),
            entry.getValue(),
            insufficientItems,
            substitutes
        );
      }
    }
    conductSubstitution(cart, insufficientItems, substitutes);
  }

  /**
   * Helper that onduct substitution and removal related to out-of-stock situation.
   *
   * @param cart cart of the ordered products.
   * @param insufficientItems list in which the items removed here are added to.
   * @param substitutes list in which the items substituting removed items.
   */
  private void conductSubstitution(
      ShoppingCart cart,
      List<IProduct> insufficientItems,
      ArrayList<IProduct> substitutes
  ) {
    for (IProduct product : insufficientItems) {
      cart.remove(product);
    }
    for (int i = 0; i < substitutes.size() - 1; i += 2) {
      IProduct original = substitutes.get(i);
      IProduct substitute = substitutes.get(i + 1);
      int numSubstitute = cart.getNum(original);
      if (cart.getNum(substitute) != -1) {
        // substitute is already in the cart
        numSubstitute += cart.getNum(substitute);
        if (inventory.checkAvailability(substitute, numSubstitute)) {
          cart.add(substitute, numSubstitute);
        } else {
          insufficientItems.add(substitute);
          cart.remove(substitute);
        }
      } else {
        // substitute is not in the cart
        cart.add(substitute, numSubstitute);
      }
      cart.remove(original);
    }
  }

  /**
   * Helper method to substitute one out-of-stock product, or remove it from the cart and add it in
   * the input list removedItems.
   *
   * @param original the product that is to be replaced.
   * @param num the number of the original product to be replaced/removed.
   * @param insufficientItems list in which the items removed here are added to.
   * @param substitutes list in which the items substituting removed items.
   */
  private void recordSubstitute(
      IProduct original,
      Integer num,
      List<IProduct> insufficientItems,
      List<IProduct> substitutes
  ) {
    IProduct substitute = inventory.findSubstitute(original, num);
    if (substitute == null) {
      insufficientItems.add(original);
    } else {
      substitutes.add(original);
      substitutes.add(substitute);
    }
  }

  @Override
  /**
   * {@inheritDoc}.
   */
  public Boolean checkAvailability(IProduct product, int num) {
    return this.inventory.checkAvailability(product, num);
  }
}
