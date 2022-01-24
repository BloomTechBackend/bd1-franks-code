package com.frank.exceptions;

// Custom exception class is a subclass of RuntimeException
public class NonNumericInputException extends RuntimeException{
   public NonNumericInputException() {}
   public NonNumericInputException(String aMessage) {
      super(aMessage);              // send message to RuntimeException in case it needs it
      System.out.println(aMessage); // Display message sent when Exception was thrown
   }
}
