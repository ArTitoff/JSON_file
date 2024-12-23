package by.rublevskaya.model.parser;

import by.rublevskaya.model.book.Book;
import by.rublevskaya.model.exception.XmlProcessingException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XmlParserService {
    public Book findBookWithMostPages(String xmlFilePath) throws XmlProcessingException {
        File xmlFile = new File(xmlFilePath);
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            NodeList bookNodes = document.getElementsByTagName("book");
            int maxPages = 0;
            Book largestBook = null;

            for (int i = 0; i < bookNodes.getLength(); i++) {
                Element bookElem = (Element) bookNodes.item(i);

                int pages = Integer.parseInt(bookElem.getElementsByTagName("pages").item(0).getTextContent());
                if (pages > maxPages) {
                    maxPages = pages;
                    largestBook = extractBookFromElement(bookElem);
                }
            }

            if (largestBook == null) {
                throw new XmlProcessingException("No books found in the XML file.");
            }
            return largestBook;

        } catch (Exception e) {
            throw new XmlProcessingException("Error parsing XML file: " + xmlFilePath, e);
        }
    }

    private Book extractBookFromElement(Element bookElem) {
        Book book = new Book();
        book.setTitle(bookElem.getElementsByTagName("title").item(0).getTextContent());
        book.setAuthor(bookElem.getElementsByTagName("author").item(0).getTextContent());
        book.setYear(Integer.parseInt(bookElem.getElementsByTagName("year").item(0).getTextContent()));
        book.setPages(Integer.parseInt(bookElem.getElementsByTagName("pages").item(0).getTextContent()));
        book.setGenre(bookElem.getElementsByTagName("genre").item(0).getTextContent());
        return book;
    }
}
