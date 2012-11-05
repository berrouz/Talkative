package com.github.berrouz.errors;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 05.11.12
 * Time: 07:43
 * To change this template use File | Settings | File Templates.
 */
public class ArgumentError extends Error {
    public ArgumentError(String message){
        super(message);
    }
}
