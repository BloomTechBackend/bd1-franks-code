package com.amazon.ata.hashingset.partsmanager;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class PartManager {
    // instantiate a HashSet of DeviceParts to keep track of what parts we are currently using
    //       (more about HashSet tomorrow)
    // Since HashSet implement the Set interface, we can define a reference to the interface
    //       and assign it a HashSet object
    private Set<DevicePart> deviceParts = new HashSet<>();  // Elements in a HashSet must have a unique Hash Code

    // Use the Hash Code to determine the index of an element in an array
    // Since Hash Code collisions might occur we will store the elements in an ArrayList in the array
    //                 rather than storing the individual elements in the array

    // Determine the maximum size of the size - 10

    private final int numParts = 10;  // The number of elements in the array as a constant

    // Define an array of ArrayList<DevicePart> - each element will be an Arraylist of DevicePart objects
    //              datatype[]  name  = new datatype[#-elements]
    //    interface/base                    subclass (via extends or implements)
    private List<DevicePart>[]  parts = new ArrayList[numParts];  // elements are set null

    public void addDevicePart(DevicePart devicePart) {
        // Use the HashSet .add() to add the part passed to use to the deviceParts hashSet
        //     .add automagically calls the hashCode() method to determine the Hash Code
        //     .add sometimes also calls the equals() method when there is a Hash Code collision
        boolean isAdded = deviceParts.add(devicePart);

        // To add an element to the array:
        //    1. Find the Hash Code for an object
        //    2. Calculate the index for the array using the Hash Code

        // Use the Math.abs() to be sure the index is not negative - absolute value
        int partIndex = Math.abs(devicePart.hashCode() % numParts); // index based on the Hash Code for object to store

        //    3. Store the object in the array for index determined

        if(parts[partIndex] != null ) {       //  Do we already an object at this index? (Hash Code collision has occured)
           parts[partIndex].add(devicePart);  //     yes - add the object to the ArrayList
        }
        else {                                //     no - instantiate an ArrayList in  the element and add the object
           parts[partIndex] = new ArrayList<DevicePart>();
           parts[partIndex].add(devicePart);
        }
        return; // not required for the method - it's added so we can stop the debugger here
    }

    /**
     * Find and return an object in our array of objects or null if not found
     */
    public DevicePart findPart(DevicePart requestedDevicePart){
        // Instantiate a object to be returned initialized to null
        DevicePart returnedObject = null;

        // Use the Hash Code for the requested object to determine it's index in the array
        int partIndex = Math.abs(requestedDevicePart.hashCode() % numParts);

        // Check to see if the element in the array for the index has an ArrayList() (if not, not objects)
        if(parts[partIndex] != null ) {                          // If there is an ArrayList in the element, search it for the requested object
            if(parts[partIndex].contains(requestedDevicePart)) { //    If we find the requested object
              // .get() of ArrayList to retrieve the object, .get() needs the index of object we want
              //        so we use indexOf() to find the index of the requested object
              returnedObject = parts[partIndex].get(parts[partIndex].indexOf(requestedDevicePart));  // set the returned object to it
            }
            else {
                returnedObject = null; // unnecessary since returnedObject was initialized to null & ony changes if we found an object
            }
        }
        // return the found object
        return returnedObject;
    }


    public void printDeviceParts() {
        for (DevicePart devicePart: deviceParts) {
            System.out.println(devicePart);
        }
    }
}
