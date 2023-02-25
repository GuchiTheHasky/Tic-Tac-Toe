package guchi.the.hasky.entity;

import guchi.the.hasky.field.Field;

import java.io.BufferedReader;
import java.io.IOException;

public class Player {
    private String name;
    private char symbol;
    public Player() {
        name = "Player 1 ";
        symbol = 'O';
    }
    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "Player {" +
                "symbol = " + symbol +
                ", name = '" + name + '\'' +
                '}';
    }


    public void printMoveInfo(){
        System.out.println(name + " " + symbol + " step.");
    }
    private boolean isValidCoordinate(int coordinate){
        return (coordinate < 0 || coordinate > 2);
    }
    public void doMove(Field field, BufferedReader reader) throws IOException {
        System.out.println("Input first coordinate:");

        int x = 0;
        int y = 0;
        do {
            do {
                try {
                    x = Integer.parseInt(reader.readLine());
                }
                catch (NumberFormatException e) {
                    System.out.println("Numbers only");;
                }
                if ((isValidCoordinate(x))) {
                    System.out.println("Error, wrong number.");
                }
            }
            while (isValidCoordinate(x));
            System.out.println("Input second coordinate:");

            do {
                try {
                    y = Integer.parseInt(reader.readLine());
                }
                catch (NumberFormatException e) {
                    System.out.println("Numbers only");;
                }
                if ((isValidCoordinate(y))) {
                    System.out.println("Error, wrong number.");
                }
            }
            while (isValidCoordinate(y));

            if (!field.isCellEmpty(new Point(x, y))){
                System.out.println("The cell is taken.");
            }
        }
        while (!field.isCellEmpty(new Point(x, y)));

        field.fillField(new Point(x, y), getSymbol());
    }

}
