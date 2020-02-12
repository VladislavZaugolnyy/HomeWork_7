package ua.epam.crud.exceptions;

public class UniqueException extends Exception{
    public UniqueException(String message) {
        super(message);
    }

    public UniqueException(Throwable cause) {
        super(cause);
    }
}
