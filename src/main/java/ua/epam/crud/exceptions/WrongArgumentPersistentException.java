package ua.epam.crud.exceptions;

public class WrongArgumentPersistentException extends UniqueException {
    public WrongArgumentPersistentException(String message) {
        super("Wrong argument: " + message);
    }
}
