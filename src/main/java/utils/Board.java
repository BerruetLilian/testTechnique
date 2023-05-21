package utils;

import cases.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Board {

    private final int width;

    private final int height;

    private final Case[][] map;

    private Adventurer adventurer;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new Case[height][width];
        for(int x = 0; x < this.map.length; x++){
            for(int y = 0; y < this.map[0].length; y++){
                this.map[x][y] = new EmptyCase(x,y);
            }
        }
    }

    public void fillMap(List<Case> cases){
        for(var actualCase: cases){
            var position = actualCase.getPosition();
            this.map[position.getX()][position.getY()] = actualCase;
        }
    }

    public Case whatIsHere(Position position){
        return this.map[position.getX()][position.getY()];
    }

    public boolean isATreasure(Case actualCase){
        return actualCase.getClass() == Treasure.class;
    }

    public boolean isAMountain(Case actualCase){
        return actualCase.getClass() == Mountain.class;
    }

    //Getter/Setter

    public void setAdventurer(Adventurer adventurer){
        this.adventurer = adventurer;
    }

    public Adventurer getAdventurer(){
        return this.adventurer;
    }

    //Display/Output

    private String[][] convertMapToString(Adventurer adventurer){
        var mapString = new String[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                mapString[i][j] = map[i][j].toString();
            }
        }

        var position = adventurer.getPosition();
        mapString[position.getX()][position.getY()] = adventurer.toString();

        return mapString;
    }

    private int[] calculateColumnWidths(String[][] array) {
        int[] columnWidths = new int[array[0].length];

        for (String[] row : array) {
            for (int i = 0; i < row.length; i++) {
                int cellLength = row[i].length();
                if (cellLength > columnWidths[i]) {
                    columnWidths[i] = cellLength;
                }
            }
        }

        return columnWidths;
    }

    private String addPadding(int count) {
        return " ".repeat(Math.max(0, count));
    }

    public void display(Adventurer adventurer) {
        System.out.println("------------MAP---------------");
        var mapString = convertMapToString(adventurer);
        int[] columnWidths = calculateColumnWidths(mapString);

        for (String[] row : mapString) {
            for (int i = 0; i < row.length; i++) {
                String cellValue = row[i];
                int padding = columnWidths[i] - cellValue.length();
                System.out.print(cellValue + addPadding(padding + 1));
            }
            System.out.println();
        }
        System.out.println("------------------------------");
    }

    public void generateOutput(String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("C - "+width+" - "+height+"\n");
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    var actualCase = map[i][j];
                    if(isAMountain(actualCase)){
                        var position = actualCase.getPosition();
                        writer.write("M - "+position.getY()+" - "+position.getX()+"\n");
                    }
                    if(isATreasure(actualCase)){
                        var treasure = (Treasure) actualCase;
                        if(treasure.getQuantity() != 0){
                            var position = treasure.getPosition();
                            writer.write("T - "+position.getY()+" - "+position.getX()+" - "+treasure.getQuantity()+"\n");
                        }
                    }
                }
            }
            writer.write("A - "+adventurer.getName()+" - "+adventurer.getPosition().getY()+" - "+adventurer.getPosition().getX()+" - "+adventurer.getOrientation()+" - "+adventurer.getNbOfTreasures());

            System.out.println("File has been generated.");
        }
    }

}
