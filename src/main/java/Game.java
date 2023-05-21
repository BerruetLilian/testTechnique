import utils.FileParser;

import java.io.*;

public class Game {

    private static final String INPUT_FILE_PATH = "input/input.txt";
    private static final String OUTPUT_FILE_PATH = "C:/dev/testTechnique/src/main/resources/output/output.txt";

    public static void main(String[] args) throws IOException {
        FileParser.loadFile(INPUT_FILE_PATH);
        var board = FileParser.parseBoard();
        var player = board.getAdventurer();
        var nextMove = player.nextMove();
        while(nextMove != null) {
            System.out.println("----------START TURN----------\n");
            board.display(player);
            player.move(board);
            nextMove = player.nextMove();
            System.out.println("----------END TURN------------\n");
        }
        System.out.println(player.getName() + " got " + player.getNbOfTreasures() +" treasures.");
        board.generateOutput(OUTPUT_FILE_PATH);
    }

}
