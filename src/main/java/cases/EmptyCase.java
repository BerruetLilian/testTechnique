package cases;

import utils.Position;

public class EmptyCase extends AbstractCase{

    public EmptyCase(int x, int y) {
        super(new Position(x, y));
    }

    @Override
    public String toString() {
        return ".";
    }

}
