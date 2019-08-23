package inventorySystem;

import java.util.HashMap;
import java.util.Objects;


/**
 * A class that represents the inventory of a supermarket's stock.
 */
public class Inventory implements IInventory {

  private HashMap<IProduct, StockItem> groceryStock;
  private HashMap<IProduct, StockItem> householdStock;

  /**
   * Creates an inventory that keeps track of the items in stock.
   */
  public Inventory() {
    this.groceryStock = new HashMap<>();
    this.householdStock = new HashMap<>();

  }

  /**
   * Adds the given stock item to the inventory.
   *
   * @param newItem The stock item to be added to the inventory.
   */
  @Override
  public void addNewStockItem(StockItem newItem) {
    getStock(newItem.getProduct()).put(newItem.getProduct(), newItem);
  }

  /**
   * Gets the total retail value of all the items in the inventory.
   *
   * @return the total retail value.
   */
  @Override
  public Double getRetailValue() {
    Double retailValue = 0.0;
    for (IProduct key : groceryStock.keySet()) {
      retailValue += groceryStock.get(key).getRetailValue();
    }
    for (IProduct key : householdStock.keySet()) {
      retailValue += householdStock.get(key).getRetailValue();
    }
    return retailValue;
  }

  /**
   * Given a product and the number of items purchased, updates the quantity of such product in
   * stock.
   *
   * @param product The product whose stock will be updated.
   * @param change The number of items that the stock of the product will me changed by.
   */
  @Override
  public void updateStock(IProduct product, Integer change) {
    getStock(product).get(product).updateQuantity(change);
  }

  /**
   * Checks if the given product and quantity are in stock in the inventory.
   *
   * @param product the product to be checked.
   * @param quantity the quantity to be checked for.
   * @return true if the product and quantity are in stock. Returns false otherwise.
   */
  @Override
  public Boolean checkAvailability(IProduct product, Integer quantity) {
    return getStock(product).get(product).getQuantity().getQuantity() >= quantity;
  }

  /**
   * Returns a valid substitute product based on the given original product and quantity.
   *
   * @param original The original product to be substituted.
   * @param quantity The minimum quantity needed to be substituted.
   * @return a valid substitute product or null if none was found.
   */
  public IProduct findSubstitute(IProduct original, Integer quantity) {
    HashMap<IProduct, StockItem> stock;
    stock = getStock(original);
    for (IProduct key : stock.keySet()) {
      if (areSameProductType(original, key)
          && checkQuantity(quantity, stock.get(key))
          && checkPrice(original, key)) {
        if (stock.equals(this.groceryStock)) {
          if (checkWeight((AGrocery) original, (AGrocery) key)) {
            return key;
          }
        } else {
          if (checkUnits((AHousehold) original, (AHousehold) key)) {
            return key;
          }
        }
      }
    }
    return null;
  }

  /**
   * Returns true if the the original product and the potential substitute are the same product
   * type. Returns false otherwise.
   *
   * @param original the original product to be substituted.
   * @param potential the potential product substitute.
   * @return true if products are same type, false otherwise.
   */
  private Boolean areSameProductType(IProduct original, IProduct potential) {
    return original.getClass() == potential.getClass();
  }

  /**
   * Checks if the quantity of the potential product substitute is enough to fulfill the required
   * quantity.
   *
   * @param requiredQuantity the required quantity.
   * @param item the item whose quantity will be checked.
   * @return true if quantity in stock in enough, false otherwise
   */
  private Boolean checkQuantity(Integer requiredQuantity, IStockItem item) {
    return requiredQuantity <= item.getQuantity().getQuantity();
  }

  /**
   * Checks if the price of the potential product substitute is less than or equal to the original.
   *
   * @param original the original product to be substituted.
   * @param potential the potential product substitute.
   * @return true if the price meets the substitution requirement, false otherwise.
   */
  private Boolean checkPrice(IProduct original, IProduct potential) {
    return original.getPrice().getPrice() >= potential.getPrice().getPrice();
  }

  /**
   * Checks if the weight of the potential grocery product substitute is greater than or equal to
   * the original.
   *
   * @param original the original grocery product to be substituted.
   * @param potential the potential grocery product substitute.
   * @return true if the weight meets the substitution requirement, false otherwise.
   */
  private Boolean checkWeight(AGrocery original, AGrocery potential) {
    return original.getWeight().getWeight() <= potential.getWeight().getWeight();
  }

  /**
   * Checks if the units in package of the potential household product substitute are greater than
   * or equal to the original.
   *
   * @param original the original household product to be substituted.
   * @param potential the potential household product substitute.
   * @return true if the units meet the substitution requirement, false otherwise.
   */
  private Boolean checkUnits(AHousehold original, AHousehold potential) {
    return original.getUnits().getUnits() <= potential.getUnits().getUnits();
  }

  /**
   * Returns the stock of the type of the given product.
   *
   * @param product The product that belongs to the inventory.
   * @return the stock that contains the type of the given product.
   */
  private HashMap<IProduct, StockItem> getStock(IProduct product) {
    if (product instanceof AGrocery) {
      return this.groceryStock;
    }
    if (product instanceof AHousehold) {
      return this.householdStock;
    }
    return null;
  }

  /**
   * Gets the grocery stock.
   *
   * @return the grocery stock of the inventory.
   */
  @Override
  public HashMap<IProduct, StockItem> getGroceryStock() {
    return this.groceryStock;
  }

  /**
   * Gets the household stock.
   *
   * @return the household stock of the inventory.
   */
  @Override
  public HashMap<IProduct, StockItem> getHouseholdStock() {
    return this.householdStock;
  }

  /**
   * Checks if the inventory is equal to the given object.
   *
   * @param obj the object to be seen if it's equal.
   * @return true if the inventory is equal to the object, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Inventory inventory = (Inventory) obj;
    return Objects.equals(groceryStock, inventory.groceryStock)
        && Objects.equals(householdStock, inventory.householdStock);
  }

  /**
   * Generates the hashcode value of the inventory.
   *
   * @return the hashcode value of the inventory.
   */
  @Override
  public int hashCode() {
    return Objects.hash(groceryStock, householdStock);
  }
}
