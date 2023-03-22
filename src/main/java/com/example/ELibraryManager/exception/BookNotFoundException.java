package com.example.ELibraryManager.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String msg) {
        super(msg);
    }
}
