package ua.epam.task_7.exceptions;

public class DoesNotExistException extends Exception {
    public DoesNotExistException() {
        super("Required record doesn't exist.");
    }
}