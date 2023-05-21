package utils;

import cases.Adventurer;
import cases.Case;
import cases.Mountain;
import cases.Treasure;
import exceptetion.AdventurerNotFoundException;
import exceptetion.MapNotFoundException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.List;

public class FileParser {

    private static String fileContent;

    public static void loadFile(String filePath) throws FileNotFoundException {

        var classLoader = FileParser.class.getClassLoader();

        var inputStream = classLoader.getResourceAsStream(filePath);

        if (inputStream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                var stringJoiner = new StringJoiner("\n");
                String line;
                while ((line = reader.readLine()) != null) {
                    stringJoiner.add(line);
                }
                 fileContent = stringJoiner.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new FileNotFoundException("File not found: " + filePath);
        }
    }

    public static Board parseBoard() throws IOException {
        if(fileContent == null){
            throw new IOException();
        }
        for(var line : fileContent.split("\n")){
            if(line.startsWith("C")){
                var param = line.split(" - ");
                var board = new Board(Integer.parseInt(param[1]),Integer.parseInt(param[2]));
                board.fillMap(parseCases());
                board.setAdventurer(parseAdventurer());
                return board;
            }
        }
        throw new MapNotFoundException();
    }

    private static List<Case> parseCases() throws IOException {
        if(fileContent == null){
            throw new IOException();
        }
        var cases = new ArrayList<Case>();
        for(var line : fileContent.split("\n")){
            if(line.startsWith("M")){
                var param = line.split(" - ");
                cases.add(new Mountain(Integer.parseInt(param[2]),Integer.parseInt(param[1])));
            }
            if(line.startsWith("T")){
                var param = line.split(" - ");
                cases.add(new Treasure(Integer.parseInt(param[2]),Integer.parseInt(param[1]),Integer.parseInt(param[3])));
            }
        }
        return cases;
    }

    private static Adventurer parseAdventurer() throws IOException {
        if(fileContent == null){
            throw new IOException();
        }
        for(var line : fileContent.split("\n")){
            if(line.startsWith("A")) {
                var param = line.split(" - ");
                return new Adventurer(param[1], Integer.parseInt(param[3]), Integer.parseInt(param[2]), Orientation.parseOrientation(param[4]), param[5]);
            }
        }
        throw new AdventurerNotFoundException();
    }

}
