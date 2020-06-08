package Utilities;

public class MyDiaryException extends Exception{
    public MyDiaryException() {
    }

    public MyDiaryException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyDiaryException(String message) {
        super(message);
    }
}
