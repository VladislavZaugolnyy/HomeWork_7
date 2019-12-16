package ua.epam.task_7.exceptions;

public class RecordDoesNotExistException extends Exception {
    public RecordDoesNotExistException() {
        super("Record doesn't exist");
    }
}