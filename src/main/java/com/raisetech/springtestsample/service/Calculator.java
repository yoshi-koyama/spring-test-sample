package com.raisetech.springtestsample.service;

public class Calculator {

    public int add(int left, int right) {
        return left + right;
    }

    public int subtract(int left, int right) {
        return left - right;
    }

    public int multiple(int left, int right) {
        return left * right;
    }

    public int divide(int left, int right) throws Exception{
        if (right == 0) {
            throw new Exception("0で割ることはできません");
        }
        return left / right;
    }
}
