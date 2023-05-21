import exceptetion.AdventurerNotFoundException;
import exceptetion.InvalidMoveException;
import exceptetion.MapNotFoundException;
import org.junit.jupiter.api.Test;
import utils.FileParser;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameTest {

    @Test
    public void fileWithoutMap() throws FileNotFoundException {
       String INPUT_FILE_PATH = "test/inputWithoutMap.txt";
       FileParser.loadFile(INPUT_FILE_PATH);
       assertThrows(MapNotFoundException.class, FileParser::parseBoard);
    }

    @Test
    public void fileWithoutAdventurer() throws FileNotFoundException {
        String INPUT_FILE_PATH = "test/inputWithoutAdventurer.txt";
        FileParser.loadFile(INPUT_FILE_PATH);
        assertThrows(AdventurerNotFoundException.class, FileParser::parseBoard);
    }

    @Test
    public void fileWithWrongMove() throws IOException {
        String INPUT_FILE_PATH = "test/inputWithWrongMove.txt";
        FileParser.loadFile(INPUT_FILE_PATH);
        var board = FileParser.parseBoard();
        assertThrows(InvalidMoveException.class, () -> board.getAdventurer().move(board));
    }

}
