package ua.epam.crud.exceptions;

public class AlreadyExistsException extends UniqueException {
    public AlreadyExistsException() {
        super("Already exists");
    }
}
