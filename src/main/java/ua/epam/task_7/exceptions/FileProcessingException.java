package ua.epam.task_7.exceptions;

public class FileProcessingException extends Exception {
    public FileProcessingException(String massage) {
        super("Can`t process file " + massage);
    }
}
