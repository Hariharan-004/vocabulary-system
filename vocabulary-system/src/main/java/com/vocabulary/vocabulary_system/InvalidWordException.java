package com.vocabulary.vocabulary_system;

public class InvalidWordException extends RuntimeException{

    public InvalidWordException(String message)
    {
        super(message);
    }

}
