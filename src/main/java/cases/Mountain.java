package cases;

import utils.Position;

public class Mountain extends AbstractCase {

    public Mountain(int x, int y) {
        super(new Position(x, y));
    }

    @Override
    public String toString() {
        return "M";
    }
}
