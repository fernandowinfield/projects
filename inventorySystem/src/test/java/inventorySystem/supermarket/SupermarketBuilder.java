package inventorySystem.supermarket;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import inventorySystem.Beer;
import inventorySystem.Cheese;
import inventorySystem.IInventory;
import inventorySystem.IProduct;
import inventorySystem.Inventory;
import inventorySystem.Manufacturer;
import inventorySystem.MinimumAge;
import inventorySystem.PaperTowels;
import inventorySystem.Price;
import inventorySystem.ProductName;
import inventorySystem.Quantity;
import inventorySystem.Salmon;
import inventorySystem.Shampoo;
import inventorySystem.StockItem;
import inventorySystem.Units;
import inventorySystem.Weight;

public class SupermarketBuilder {

  public static ISupermarket supermarket;
  public static IInventory inventory;
  public static IProduct product1;
  public static IProduct product2;
  public static IProduct product3;
  public static IProduct product4;
  public static IProduct product5;
  public static IProduct product6;
  public static IProduct product7;
  public static String productName1;
  public static String productName2;
  public static String productName3;
  public static String productName4;
  public static String productName5;
  public static String productName6;
  public static String productName7;
  public static String manufacturer1;
  public static String manufacturer2;
  public static String manufacturer3;
  public static String manufacturer4;
  public static String manufacturer5;
  public static String manufacturer6;
  public static String manufacturer7;
  public static Double price1;
  public static Double price2;
  public static Double price3;
  public static Double price4;
  public static Double price5;
  public static Double price6;
  public static Double price7;
  public static Integer minAge1;
  public static Integer minAge2;
  public static Integer minAge3;
  public static Integer minAge4;
  public static Integer minAge5;
  public static Integer minAge6;
  public static Integer minAge7;
  public static Double weight1;
  public static Double weight2;
  public static Double weight3;
  public static Double weight4;
  public static Integer unit5;
  public static Integer unit6;
  public static Integer unit7;
  public static Integer productQuantity1;
  public static Integer productQuantity2;
  public static Integer productQuantity3;
  public static Integer productQuantity4;
  public static Integer productQuantity5;
  public static Integer productQuantity6;
  public static Integer productQuantity7;

  public static void buildSuperMarket()
      throws SupermarketCreatedAgainException, SupermarketNotCreatedException {
    try {
      supermarket = Supermarket.getInstance();
    } catch (SupermarketNotCreatedException e) {
      setUp();
      TestBuiltSupermarketAndGetInstance();
      supermarket = Supermarket.getInstance();
    }
  }

  public static void setUp() {
    //products
    productName1 = "Carlsberg Premium";
    productName2 = "Snow Beer";
    productName3 = "Blue Cheese";
    productName4 = "Smoked Salmon";
    productName5 = "Premium Pulp";
    productName6 = "Anti-Dandruff Shampoo";
    productName7 = "Herbal Shampoo";
    manufacturer1 = "Carlsberg";
    manufacturer2 = "Snow";
    manufacturer3 = "SwissFac";
    manufacturer4 = "Oceania";
    manufacturer5 = "Kleenx";
    manufacturer6 = "Head & Shoulder";
    manufacturer7 = "Lux";
    price1 = 20.55;
    price2 = 10.92;
    price3 = 5.53;
    price4 = 30.65;
    price5 = 9.55;
    price6 = 8.57;
    price7 = 12.34;
    minAge1 = 21;
    minAge2 = 21;
    minAge3 = -1;
    minAge4 = -1;
    minAge5 = -1;
    minAge6 = -1;
    minAge7 = -1;
    weight1 = 10.5;
    weight2 = 11.5;
    weight3 = 1.2;
    weight4 = 12.3;
    unit5 = 13;
    unit6 = 1;
    unit7 = 2;
    product1 = new Beer(
        new Manufacturer(manufacturer1),
        new ProductName(productName1),
        new Price(price1),
        new MinimumAge(minAge1),
        new Weight(weight1)
    );
    product2 = new Beer(
        new Manufacturer(manufacturer2),
        new ProductName(productName2),
        new Price(price2),
        new MinimumAge(minAge2),
        new Weight(weight2)
    );
    product3 = new Cheese(
        new Manufacturer(manufacturer3),
        new ProductName(productName3),
        new Price(price3),
        new MinimumAge(minAge3),
        new Weight(weight3)
    );
    product4 = new Salmon(
        new Manufacturer(manufacturer4),
        new ProductName(productName4),
        new Price(price4),
        new MinimumAge(minAge4),
        new Weight(weight4)
    );
    product5 = new PaperTowels(
        new Manufacturer(manufacturer5),
        new ProductName(productName5),
        new Price(price5),
        new MinimumAge(minAge5),
        new Units(unit5)
    );
    product6 = new Shampoo(
        new Manufacturer(manufacturer6),
        new ProductName(productName6),
        new Price(price6),
        new MinimumAge(minAge6),
        new Units(unit6)
    );
    product7 = new Shampoo(
        new Manufacturer(manufacturer7),
        new ProductName(productName7),
        new Price(price7),
        new MinimumAge(minAge7),
        new Units(unit7)
    );
    // Inventory
    productQuantity1 = 100;
    productQuantity2 = 101;
    productQuantity3 = 100;
    productQuantity4 = 100;
    productQuantity5 = 100;
    productQuantity6 = 100;
    productQuantity7 = 100;
    inventory = new Inventory();
    inventory.addNewStockItem(new StockItem(product1, new Quantity(productQuantity1)));
    inventory.addNewStockItem(new StockItem(product2, new Quantity(productQuantity2)));
    inventory.addNewStockItem(new StockItem(product3, new Quantity(productQuantity3)));
    inventory.addNewStockItem(new StockItem(product4, new Quantity(productQuantity4)));
    inventory.addNewStockItem(new StockItem(product5, new Quantity(productQuantity5)));
    inventory.addNewStockItem(new StockItem(product6, new Quantity(productQuantity6)));
    inventory.addNewStockItem(new StockItem(product7, new Quantity(productQuantity7)));
  }

  public static void TestBuiltSupermarketAndGetInstance()
      throws SupermarketNotCreatedException, SupermarketCreatedAgainException {
    SupermarketBuilder test = new SupermarketBuilder();
    test.setUp();
    try {
      Supermarket.getInstance();
      fail("SupermarketNotCreatedException should be thrown.");
    } catch (SupermarketNotCreatedException exception1) {
      supermarket = Supermarket.builtSupermarket(test.inventory);
      assertTrue(supermarket == Supermarket.getInstance());
      try {
        supermarket = Supermarket.builtSupermarket(null);
        fail("SupermarketCreatedAgainException should be thrown.");
      } catch (SupermarketCreatedAgainException exception2) {
        assertTrue(supermarket == Supermarket.getInstance());
        return;
      }
      fail("SupermarketCreatedAgainException should be thrown.");
    }
    fail("SupermarketNotCreatedException should be thrown.");
  }
}