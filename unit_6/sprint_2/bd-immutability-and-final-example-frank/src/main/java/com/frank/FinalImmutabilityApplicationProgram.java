package com.frank;

public class FinalImmutabilityApplicationProgram {

        private  static int nums[] = {1, 2, 3, 4};

        public static void main(String[] args) {
                System.out.println("Hello from main()");

                showArray();

                aClass anObject = new aClass(nums);

                anObject.showClass();

                nums[0] = 9999; // Changing a value in the original array
                                //       is also reflected in the object we passed to aClass
                                // violated Encapsulation rules for aClass object
                                // use defensive copy technique to avoid this issue

                showArray();
                anObject.showClass();

                anObject.setElement(3,1492);
                showArray(); // display the array defined in main

        }

        public static void showArray() {
                System.out.println(("\nContents of array in main(): "));
                for (int i = 0; i < nums.length; i++) {
                        System.out.println("Element " + i + ": " + nums[i]);
                }
        }

}
