package com.github.berrouz.errors;

/**
 * ArgumentError is Error which thrown when not all arguments are provided
 */

public class ArgumentError extends Error {
    public ArgumentError(String message){
        super(message);
    }
}
