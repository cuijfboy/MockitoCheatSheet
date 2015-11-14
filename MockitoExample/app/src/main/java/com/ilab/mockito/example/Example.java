package com.ilab.mockito.example;

/**
 * Created by cuijfboy on 15/11/14.
 */
public class Example {

    public Object publicFiled;
    private Object privateField;

    public Example(Object privateField) {
        System.out.println("Here is Example.Constructor with one argument");
    }

    public void publicMethod(Object arg) {
        System.out.println("Here is Example.publicMethod");
    }

    private void privateMethod(Object arg) {
        System.out.println("Here is Example.publicMethod");

    }

    public Object publicMethodThatReturns(Object arg) {
        System.out.println("Here is Example.publicMethodThatReturns");
        return null;
    }

    private Object privateMethodThatReturns(Object arg) {
        System.out.println("Here is Example.privateMethodThatReturns");
        return null;
    }

}
