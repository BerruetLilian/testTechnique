package utils;

import exceptetion.OrientationNotFoundException;

public enum Orientation {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public static Orientation parseOrientation(String orientation){
        return switch (orientation) {
            case "N" -> Orientation.NORTH;
            case "S" -> Orientation.SOUTH;
            case "E" -> Orientation.EAST;
            case "O" -> Orientation.WEST;
            default -> throw new IllegalArgumentException();
        };
    }

    public Orientation turnLeft() {
        int newIndex = (this.ordinal() + 3) % 4;
        return Orientation.values()[newIndex];
    }

    public Orientation turnRight() {
        int newIndex = (this.ordinal() + 1) % 4;
        return Orientation.values()[newIndex];
    }

    @Override
    public String toString() {
        switch (this.ordinal()) {
            case 1 -> {
                return "N";
            }
            case 2 -> {
                return "S";
            }
            case 3 -> {
                return "E";
            }
            case 4 -> {
                return "O";
            }
            default -> throw new OrientationNotFoundException();
        }
    }
}