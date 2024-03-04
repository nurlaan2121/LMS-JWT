package lms.exceptions;

public class NotFound extends RuntimeException {
    public NotFound(Long message) {
        super(" With id: " + message  + "  not found");
    }
}

