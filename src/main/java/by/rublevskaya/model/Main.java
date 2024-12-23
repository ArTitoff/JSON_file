package by.rublevskaya.model;

import by.rublevskaya.model.book.Book;
import by.rublevskaya.model.book.BookWrapper;
import by.rublevskaya.model.exception.JsonProcessingException;
import by.rublevskaya.model.exception.XmlProcessingException;
import by.rublevskaya.model.service.JsonService;
import by.rublevskaya.model.parser.XmlParserService;
import by.rublevskaya.model.service.XmlService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String jsonFilePath = "books.json";
        String xmlFilePath = "books.xml";

        JsonService jsonService = new JsonService();
        XmlService xmlService = new XmlService();
        XmlParserService xmlParserService = new XmlParserService();

        try {
            System.out.println("Reading books from JSON file: " + jsonFilePath);
            List<Book> books = jsonService.readFromJson(jsonFilePath);

            System.out.println("\nList of books loaded from JSON:");
            for (Book book : books) {
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Year: " + book.getYear());
                System.out.println("Pages: " + book.getPages());
                System.out.println("Genre: " + book.getGenre());
                System.out.println("------------------");
            }

            BookWrapper bookWrapper = new BookWrapper();
            bookWrapper.setBooks(books);
            System.out.println("Saving books to XML file: " + xmlFilePath);
            xmlService.saveToXml(bookWrapper, xmlFilePath);

            Book largestBook = xmlParserService.findBookWithMostPages(xmlFilePath);
            System.out.println("\nBook with the most pages:");
            System.out.println(largestBook.toString());

        } catch (JsonProcessingException | XmlProcessingException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}