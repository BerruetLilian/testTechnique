package exceptetion;

public class MapNotFoundException extends RuntimeException {

    public MapNotFoundException() {
        super("File doesn't contains a map.");
    }

    public MapNotFoundException(String message) {
        super(message);
    }
}
