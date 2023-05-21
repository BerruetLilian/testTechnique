package exceptetion;

public class AdventurerNotFoundException extends RuntimeException {

    public AdventurerNotFoundException() {
        super("File doesn't contains an adventurer.");
    }

    public AdventurerNotFoundException(String message) {
        super(message);
    }
}
