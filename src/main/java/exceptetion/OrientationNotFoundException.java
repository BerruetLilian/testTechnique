package exceptetion;

public class OrientationNotFoundException extends RuntimeException {

    public OrientationNotFoundException() {
        super("Current Orientation doesn't exist.");
    }

    public OrientationNotFoundException(String message) {
        super(message);
    }

}
