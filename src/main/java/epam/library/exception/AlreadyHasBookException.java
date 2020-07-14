package epam.library.exception;

public class AlreadyHasBookException extends Exception {
    public String toString() {
        return "The reader already has this book.";
    }
}
