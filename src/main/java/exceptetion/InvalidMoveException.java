package exceptetion;

public class InvalidMoveException extends RuntimeException {

    public InvalidMoveException() {
        super("Move is invalid.");
    }

    public InvalidMoveException(String message) {
        super(message);
    }
}
