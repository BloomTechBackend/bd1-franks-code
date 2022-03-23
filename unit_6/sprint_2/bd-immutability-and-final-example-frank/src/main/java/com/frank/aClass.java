package com.frank;

import java.util.Arrays;

// final on a variable indicates it cannot me changed once set (constant)
// final on a class indicates the class cannot be a superclass - it's behavior cannot be modified
// final on a method indicates it cannot be overridden (by subclass)


public class aClass {
        private int[] anArray;
        public aClass(int[] intArray) {
        //        anArray = intArray;  // member variable reference should be set tp the parameter reference
        // Use defensive copy to be sure we have our own copy of any references passed to use
        anArray = Arrays.copyOf(intArray, intArray.length);
        }

        // final on a method indicates it cannot be overridden by a subclass
        public final void setElement(int elemNum, int value) {
                anArray[elemNum] = value;
        }

        public void showClass() {
                System.out.println(("\nContents of array in aClass: "));
                for (int i = 0; i < anArray.length; i++) {
                        System.out.println("Element " + i + ": " + anArray[i]);
                }
        }
}
