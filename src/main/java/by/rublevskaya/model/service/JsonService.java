package by.rublevskaya.model.service;

import by.rublevskaya.model.book.Book;
import by.rublevskaya.model.exception.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonService {
    public List<Book> readFromJson(String filePath) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(filePath), new TypeReference<List<Book>>() {});
        } catch (IOException e) {
            throw new JsonProcessingException("Error reading JSON file: " + filePath, e);
        }
    }
}
