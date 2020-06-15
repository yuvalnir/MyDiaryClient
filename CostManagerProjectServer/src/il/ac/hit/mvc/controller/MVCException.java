package il.ac.hit.mvc.controller;

public class MVCException extends RuntimeException{
    public MVCException() {
    }

    public MVCException(String message, Throwable cause) {
        super(message, cause);
    }

    public MVCException(String message) {
        super(message);
    }
}