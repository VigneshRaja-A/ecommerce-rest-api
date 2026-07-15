package com.example.E_Commerce.exception;

public class ProductAlreadyExistsException extends RuntimeException
{
    public ProductAlreadyExistsException(String message)
    {
        super(message);
    }
}
