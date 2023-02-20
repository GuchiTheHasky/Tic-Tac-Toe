package guchi.the.hasky.field;

import guchi.the.hasky.entity.Point;
import java.util.Arrays;

public class Field {

    private char[][] field;

    public Field() {
        this.field = new char[3][3];
        clearField();

    }

    public char[][] getField() {
        return field;
    }

    public void setField(char[][] field) {
        this.field = field;
    }
    private char getEnemySymbol(char symbol) {
        if (symbol == 'X') {
            return 'O';
        }
        return symbol;
    }

    private void clearField() {
        for (char[] symbol : field) {
            Arrays.fill(symbol, ' ');
        }
    }

    public void fillField(Point point, char symbol) {
        field[point.getX()][point.getY()] = symbol;

    }

    public void printField() {
        for (char[] cells : field) {
            System.out.println("---------");
            for (char symbol : cells) {
                System.out.print("|" + symbol + "|");
            }
            System.out.println();
        }
        System.out.println("---------");

    }

    public boolean isDraw(){
        for (char[] chars : field) {
            for (char symbol : chars) {
                if (symbol == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isCellEmpty(Point point) {
        return field[point.getX()][point.getY()] == ' ';
    }

    public boolean winnerIs(char symbol) {

        int diagonalLeft = 0;
        int diagonalRight = 0;
        for (int i = 0; i < field.length; i++) {
            int horizontal = 0;
            int vertical = 0;

            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == symbol) {
                    horizontal++;
                }
                if (field[j][i] == symbol) {
                    vertical++;
                }
                if (field[i][j] == symbol && i == j) {
                    diagonalLeft++;
                }
                if (field[i][j] == symbol && i + j == field.length - 1) {
                    diagonalRight++;
                }
            }
            if (horizontal == field.length ||
                    vertical == field.length ||
                    diagonalLeft == field.length ||
                    diagonalRight == field.length) return true;
        }
        return false;
    }

    public boolean isPossibleWin(char symbol) {

        int diagonalLeft = 0;
        int diagonalRight = 0;
        int horizontal = 0;
        int vertical = 0;
        for (int i = 0; i < field.length; i++) {
            horizontal = 0;
            vertical = 0;
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == symbol) {
                    horizontal++;
                } else if (field[i][j] == getEnemySymbol(symbol)) {
                    horizontal--;
                }
                if (field[j][i] == symbol) {
                    vertical++;
                } else if (field[j][i] == getEnemySymbol(symbol)) {
                    vertical--;
                }
                if (field[i][j] == symbol && i == j) {
                    diagonalLeft++;
                } else if ((field[i][j] == getEnemySymbol(symbol)) && i == j) {
                    diagonalLeft--;
                }
                if (field[i][j] == symbol && i + j == field.length - 1) {
                    diagonalRight++;
                } else if ((field[i][j] == getEnemySymbol(symbol)) && i + j == field.length - 1) {
                    diagonalRight--;
                }
            }
        }
        return ((horizontal == field.length - 1) ||
                (vertical == field.length - 1) ||
                (diagonalLeft == field.length - 1) ||
                (diagonalRight == field.length - 1));
    }
}