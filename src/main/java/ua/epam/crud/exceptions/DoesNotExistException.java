package ua.epam.crud.exceptions;

public class DoesNotExistException extends UniqueException {
    public DoesNotExistException() {
        super("Required record doesn't exist.");
    }
}