package com.ilab.mockito.example;

/**
 * Created by cuijfboy on 15/11/14.
 */
public class StaticExample {
    public static Object publicField;
    private static Object privateField;

    public static void publicMethod(Object arg) {
        System.out.println("Here is StaticExample.publicMethod");
    }

    private static void privateMethod(Object arg) {
        System.out.println("Here is StaticExample.privateMethod");
    }

    public static Object publicMethodThatReturns(Object arg) {
        System.out.println("Here is StaticExample.publicMethodThatReturns");
        return null;
    }

    private static Object privateMethodThatReturns(Object arg) {
        System.out.println("Here is StaticExample.privateMethodThatReturns");
        return null;
    }
}
