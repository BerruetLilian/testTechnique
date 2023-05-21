package utils;

public class Position {

    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void increaseX(){
        this.x += 1;
    }

    public void increaseY(){
        this.y += 1;
    }

    public void decreaseX(){
        this.x -= 1;
    }

    public void decreaseY(){
        this.y -= 1;
    }

    @Override
    public String toString() {
        return "Position(" + x + "," + y + ")";
    }
}
