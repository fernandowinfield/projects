package edu.neu.ccs.cs5004.problem1;


/**
 * Class represents the common states and behavior Beer.
 */
public class Beer extends AGrocery {

  public Beer(Manufacturer manufacturer, ProductName productName, Price price,
      MinimumAge minimumAge, Weight weight) {
    super(manufacturer, productName, price, minimumAge, weight);
  }
}
