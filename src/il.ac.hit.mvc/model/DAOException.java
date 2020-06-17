package il.ac.hit.mvc.model;

public class DAOException  extends RuntimeException{
    public DAOException() {
    }

    public DAOException(String message, Throwable cause) {
        super("DAO Exception: "+message, cause);
    }

    public DAOException(String message) {
        super(message);
    }
}

