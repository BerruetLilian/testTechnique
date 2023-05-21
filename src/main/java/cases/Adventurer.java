package cases;

import exceptetion.InvalidMoveException;
import exceptetion.OrientationNotFoundException;
import utils.Board;
import utils.Orientation;
import utils.Position;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Adventurer extends AbstractCase {

    private final String name;

    private Orientation orientation;

    private final Queue<String> moves;

    private int nbOfTreasures = 0;

    public Adventurer(String name, int x, int y, Orientation orientation, String moves) {
        super(new Position(x,y));
        this.name = name;
        this.orientation = orientation;
        this.moves = new LinkedList<>();
        for(char c : moves.toCharArray()){
            this.moves.add(String.valueOf(c));
        }
    }

    //------------------------ MOVEMENT ---------------------

    /**
     * Always call nextMove and verify its null value before calling move
     * @param board context of the current board
     */
    public void move(Board board) {
        var move = Objects.requireNonNull(moves.poll());
        System.out.println(name+" move " + move);
        System.out.println("move is valid ? "+ isMoveValid(move,board));
        if(isMoveValid(move,board)){
            switch (Objects.requireNonNull(move)) {
                case "A" -> moveForward(board);
                case "G" -> turnLeft();
                case "D" -> turnRight();
                default -> throw new InvalidMoveException();
            }
        }
    }

    private boolean isMoveValid(String move, Board board){
        switch (move){
            case "G", "D" -> {
                return true;
            }
            case "A" -> {
                return isMoveForwardValid(board);
            }
            default -> throw new InvalidMoveException();
        }
    }

    private boolean isMoveForwardValid(Board board){
        if(!board.isAMountain(board.whatIsHere(simulateMoveForward()))){
            switch (orientation) {
                case NORTH -> {
                    return getPosition().getX()!=0;
                }
                case SOUTH -> {
                    return getPosition().getX()!=board.getHeight()-1;
                }
                case EAST -> {
                    return getPosition().getY()!=board.getWidth()-1;
                }
                case WEST -> {
                    return getPosition().getY()!=0;
                }
                default -> throw new OrientationNotFoundException();
            }
        } else {
            return false;
        }
    }

    private Position simulateMoveForward(){
        switch (orientation) {
            case NORTH -> {
                return new Position(getPosition().getX()-1, getPosition().getY());
            }
            case SOUTH -> {
                return new Position(getPosition().getX()+1,getPosition().getY());
            }
            case EAST -> {
                return new Position(getPosition().getX(),getPosition().getY()+1);
            }
            case WEST -> {
                return new Position(getPosition().getX(),getPosition().getY()-1);
            }
            default -> throw new OrientationNotFoundException();
        }
    }

    private void turnRight(){
        this.orientation = this.orientation.turnRight();
    }

    private void turnLeft(){
        this.orientation = this.orientation.turnLeft();
    }

    private void moveForward(Board board){
        switch (orientation) {
            case NORTH -> getPosition().decreaseX();
            case SOUTH -> getPosition().increaseX();
            case EAST -> getPosition().increaseY();
            case WEST -> getPosition().decreaseY();
        }
        var actualCase = board.whatIsHere(getPosition());
        if(board.isATreasure(actualCase)){
            foundTreasure((Treasure) actualCase);
        }
    }

    private void foundTreasure(Treasure treasure){
        if(treasure.getQuantity() !=0 ){
            System.out.println(name+" found treasure !");
            treasure.decreaseQuantity();
            nbOfTreasures++;
        }
    }

    //-------- Getter/Setter/toString ----------

    public String getName(){
        return this.name;
    }

    public int getNbOfTreasures() {
        return this.nbOfTreasures;
    }

    @Override
    public String toString() {
        switch (orientation) {
            case NORTH -> {
                return "↑A("+name+")";
            }
            case SOUTH -> {
                return "↓A("+name+")";
            }
            case EAST -> {
                return "→A("+name+")";
            }
            case WEST -> {
                return "←A("+name+")";
            }
            default -> throw new OrientationNotFoundException();
        }
    }

    public String nextMove() {
        return moves.peek();
    }

    public Orientation getOrientation() {
        return orientation;
    }

}
