package com.frank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaExpressionsExamples {

    static List<String> names     = new ArrayList<>();

    static List<Integer> numList  = new ArrayList<>();

    public static void main(String[] args) {
        /***************************************************************************************
         * Perform Initial/Setup processing for the application
         **************************************************************************************/
        setup();      // Perform start of main processing

        /***************************************************************************************
         * forEach() List method - preferred over stream().forEach()
         *
         * List forEach() uses the iterator for the class so any order is maintained
         * stream().forEach() the order of the elements is no predictable
         **************************************************************************************/
        System.out.println("\n-------- Display elements in a List ---------");

        // if Lambda expression has only one parameter - no () are needed
        names.forEach(  aName  -> {System.out.println("Name: " + aName);});  // List version of forEach()
     // names.forEach( (aName) -> {System.out.println("Name: " + aName);});  // List version of forEach()
     // names.stream().forEach( (aName) -> {System.out.println("Name: " + aName);});

        /***************************************************************************************
         * stream().filter() Stream Interface function
         **************************************************************************************/
        System.out.println("\n-------- Removing Frank from List ---------");

        // Go through the List and don't copy Frank to teh new List

        List<String> noFrank = names.stream().filter(aName -> {return (!aName.equals("Frank"));})
                                             .collect(Collectors.toList());

        noFrank.forEach( (aName) -> {System.out.println("Name: " + aName);});

        /***************************************************************************************
         * stream().filter() Stream Interface function
         **************************************************************************************/
        System.out.println("\n-------- Find values in ArrayList evenly divisble by 3 ---------");

        System.out.println("Values in numList ArrayList: ");
        numList.forEach( (aNum) -> {System.out.println("Value in List: " + aNum);});

        List<Integer> divBy3 = numList.stream().filter((aNum) -> {return (aNum % 3 == 0 ? true : false); }).collect(Collectors.toList());

        divBy3.forEach( (aNum) -> {System.out.println("\tnumber divisible by 3: " + aNum);});

        /***************************************************************************************
         * stream().map() Stream Interface function
         **************************************************************************************/
        System.out.println("\n-------- Showing first initials   ---------");

        // Go through the list of names and extract only the first letter of each name
        // We will use stream().map() method with a Lambda expression that extracts the first letter
        // Since stream().map() returns a Stream object, we need to convert to a List so we can use it

        List<String> firstInitials = names.stream().map((aName) -> {return (aName.substring(0,1));})
                                                   .collect(Collectors.toList());

        firstInitials.forEach( (aName) -> {System.out.println("Name: " + aName);});

        /***************************************************************************************
         * stream().reduce() Stream Interface function
         **************************************************************************************/
        System.out.println("\n-------- Using stream().reduce to sum an ArrayList   ---------");

        System.out.println("Values in numList ArrayList: ");
        numList.forEach( (aNum) -> {System.out.println("Value in List: " + aNum);});

        //    reduce(init-reducer-value, (reducer, name-of-element) -> {process to modify reducer using element})
        int listTotal = numList.stream().reduce(0, (sum, anElem) -> sum + anElem);
        System.out.println("Sum of elements in ArrayList is: " + listTotal);

        /***************************************************************************************
         * Arrays.reduce() Arrays method
         **************************************************************************************/
        System.out.println("\n-------- Using Arrays.reduce() to sum an array   ---------");

        int numArray[]  = {10, 20, 30,40};
        for(int i=0; i < numArray.length; i++) {
            System.out.println("Array elem " + i + ": " + numArray[i]);
        }

        // The Arrays class provided a stream().reduce() method for a simple array
        int total = Arrays.stream(numArray).reduce(0, (sum, anElem) -> sum + anElem);
        System.out.println("Sum of elements in array is: " + total);


        takeDown();   // Perform end of main() processings
    } // End of main()

    public static void setup() {

        System.out.println("-".repeat(50) + "\nWelcome to Lambda Expressions Example\n" + "-".repeat(50));

        names.add("Frank");
        names.add("Kirk");
        names.add("Spock");
        names.add("Worf");
        names.add("Data");

        numList.add(100);
        numList.add(200);
        numList.add(300);
        numList.add(400);
        numList.add(500);
        numList.add(600);
    }

    public static void takeDown() {
        System.out.println("-".repeat(50) + "\nThanks for visiting Lambda Expressions Example\n" + "-".repeat(50));
    }

} // End if LambdaExpressionsExample class
