package edu.neu.ccs.cs5004.problem1;

import java.util.HashMap;

/**
 * An interface that contains the expected behaviors of a supermarket inventory.
 */
public interface IInventory {

  /**
   * Adds the given stock item to the inventory.
   *
   * @param newItem The stock item to be added to the inventory.
   */
  void addNewStockItem(StockItem newItem);

  /**
   * Gets the total retail value of all the items in the inventory.
   *
   * @return the total retail value.
   */
  Double getRetailValue();

  /**
   * Given a product and the number of items purchased, updates the quantity of such product in
   * stock.
   *
   * @param product The product whose stock will be updated.
   * @param change The number of items that the stock of the product will me changed by.
   */
  void updateStock(IProduct product, Integer change);

  /**
   * Checks if the given product and quantity are in stock in the inventory.
   *
   * @param product the product to be checked.
   * @param quantity the quantity to be checked for.
   * @return true if the product and quantity are in stock. Returns false otherwise.
   */
  Boolean checkAvailability(IProduct product, Integer quantity);


  /**
   * Returns a valid substitute product based on the given original product and quantity.
   *
   * @param original The original product to be substituted.
   * @param quantity The minimum quantity needed to be substituted.
   * @return a valid substitute product or null if none was found.
   */
  IProduct findSubstitute(IProduct original, Integer quantity);


  /**
   * Gets the grocery stock.
   *
   * @return the grocery stock of the inventory.
   */
  HashMap<IProduct, StockItem> getGroceryStock();


  /**
   * Gets the household stock.
   *
   * @return the household stock of the inventory.
   */
  HashMap<IProduct, StockItem> getHouseholdStock();
}
