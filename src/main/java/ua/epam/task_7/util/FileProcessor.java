package ua.epam.task_7.util;

import ua.epam.task_7.exceptions.FileProcessingException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FileProcessor {
    private String fileName;

    public FileProcessor(String fileName) {
        this.fileName = fileName;
    }

    public List<String> readFile() throws FileProcessingException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                lines.add(s);
            }
        } catch (IOException e) {
            throw new FileProcessingException("Can`t read file " + fileName);
        }
        return lines;
    }

    public void writeToFile(Collection<String> collection) throws FileProcessingException {
        try (PrintWriter printWriter = new PrintWriter(fileName)) {
            for (String string : collection) {
                printWriter.println(string);
            }
        } catch (IOException e) {
            throw new FileProcessingException("Can`t write to file " + fileName);
        }
    }
}
