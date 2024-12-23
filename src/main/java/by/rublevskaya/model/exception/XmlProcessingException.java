package by.rublevskaya.model.exception;

public class XmlProcessingException extends Exception {
    public XmlProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    public XmlProcessingException(String message) {
        super(message);
    }
}