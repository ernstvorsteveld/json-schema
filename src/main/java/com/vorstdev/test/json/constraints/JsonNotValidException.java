package com.vorstdev.test.json.constraints;

public class JsonNotValidException extends RuntimeException {

    public JsonNotValidException(String message) {
        super(message);
    }
}
