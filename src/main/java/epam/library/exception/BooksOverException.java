package epam.library.exception;

public class BooksOverException extends Exception {
    public String toString() {
        return "The books are over.";
    }
}
