package com.azhar.springbootintegrationtest.service;

public class Calculator {

    public Integer multiply(int a, int b) throws ArithmeticException{
        if(a < 0 || b<0 ){
            throw new ArithmeticException("no negative multiplication.");
        }
        return a * b;
    }
}