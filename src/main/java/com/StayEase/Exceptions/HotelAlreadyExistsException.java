package com.StayEase.Exceptions;


public class HotelAlreadyExistsException extends RuntimeException {
    public HotelAlreadyExistsException(String message) {
        super(message);
    }
}