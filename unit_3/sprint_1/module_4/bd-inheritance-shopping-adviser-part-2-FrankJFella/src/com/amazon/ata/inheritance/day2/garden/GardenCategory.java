package com.amazon.ata.inheritance.day2.garden;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates how the garden category might use the Flower hierarchy to help customers
 * search for and purchase flowers.
 */
public class GardenCategory {
    private List<Flower> catalog;

    /**
     * Constructor accepting a List of Flowers in the catalog.
     *
     * @param catalog the List of Flowers in the catalog.
     */
    public GardenCategory(List<Flower> catalog) {
        this.catalog = new ArrayList<>(catalog);
    }

    /**
     * Checks to see if the provided flower is contained in the garden catalog.
     *
     * @param flower  The flower to check the catalog for.
     * @return true if the flower is contained in the catalog, false otherwise
     *
     * .contains() for ArrayList will return true or false depending if the argument is in the ArrayList
     * .contains() uses a .equals for the class to determine if the object has what argument is looking for
     *
     * .contains(FlowerObject) - look for a .equals in the Flower class
     *                           there is no .equals in the FLower class,
     *                                  so it looks for a .equals in Flower super class (Object class .equals)
     *                                  the Object .equals does not know how to compare objects as its subclass
     *                                      so it compares the references
     *                                  (We don't want it to use the Object classes .equals)
     */
    public boolean searchCatalog(Flower flower) {
        return catalog.contains(flower);
    }

    /**
     * Fulfills the customer's purchase of a particular flower and sends care instructions.
     *
     * @param flower The flower that the customer is purchasing.
     */
    public void purchase(Flower flower) {
        // Assume there's some financial and account magic here
        // And then...
        flower.sendCareInstructions();

    }
}
