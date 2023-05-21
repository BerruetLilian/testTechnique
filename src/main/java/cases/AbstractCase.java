package cases;

import utils.Position;

public abstract class AbstractCase implements Case {

    private final Position position;

    public AbstractCase(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

}
