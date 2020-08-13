package il.ac.hit.mvc.controller;

public class MVCException extends RuntimeException{
    /**
     * MVCException class to throw uniq exceptions
     */
    public MVCException() {
    }

    public MVCException(String message, Throwable cause) {
        super(message, cause);
    }

    public MVCException(String message) {
        super(message);
    }
}