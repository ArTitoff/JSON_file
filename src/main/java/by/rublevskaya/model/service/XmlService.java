package by.rublevskaya.model.service;

import by.rublevskaya.model.book.BookWrapper;
import by.rublevskaya.model.exception.XmlProcessingException;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;

public class XmlService {
    public void saveToXml(BookWrapper bookWrapper, String filePath) throws XmlProcessingException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(BookWrapper.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(bookWrapper, new File(filePath));
            System.out.println("XML file saved at: " + filePath);
        } catch (JAXBException e) {
            throw new XmlProcessingException("Error saving to XML file: " + filePath, e);
        }
    }
}
