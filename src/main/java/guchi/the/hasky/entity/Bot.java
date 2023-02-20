package guchi.the.hasky.entity;

import guchi.the.hasky.field.Field;

public class Bot {
    private String name;
    private char symbol;

    public Bot() {
        name = "Bot";
        symbol = 'X';
    }

    public Bot(String name, char symbol) {
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
        return "Bot{" +
                "name = '" + name + '\'' +
                ", symbol = " + symbol +
                '}';
    }
    private void deleteMove(Field field, int x, int y) {
        field.fillField(new Point(x, y), ' ');
    }
    private boolean botMove(Field field, int x, int y) {
        Point point = new Point(x, y);
        if (field.isCellEmpty(point)) {
            field.fillField(point, symbol);
            return true;
        }
        return false;
    }
    private boolean playerMove(Field field, int x, int y) {
        Point point = new Point(x, y);
        if (field.isCellEmpty(point)) {
            field.fillField(point, getEnemySymbol(symbol));
            return true;
        }
        return false;
    }
    private char getEnemySymbol(char symbol) {
        return symbol == 'X' ? 'O' : 'X';
    }
    private Point getRndPoint() {
        Point[] points = {new Point(0, 0),
                new Point(0, 2),
                new Point(1, 1),
                new Point(2, 0),
                new Point(2, 2)};

        int rndIndex = (int) (Math.random() * points.length);
        return points[rndIndex];
    }
    private Point[] addPoint(Point[] points, int x, int y) {
        Point[] tempPoints = new Point[points.length + 1];
        for (int i = 0; i < points.length; i++) {
            tempPoints[i] = points[i];
        }
        tempPoints[tempPoints.length - 1] = new Point(x, y);
        points = tempPoints;
        return points;
    }

    public void printMoveInfo() {
        System.out.println(name + " " + symbol + " step.");
    }

    public Point firstMove(Field field) {
        Point point = new Point();
        do {
            point = getRndPoint();
        }
        while (!field.isCellEmpty(point));

        return point;
    }
    public Point possibleWinMove(Point[] points, Field field){
        int index = (int) (Math.random() * points.length);
        return points[index];
    }
    public Point[] possibleWinMoves(Field field) {
        Point[] points = new Point[0];
        for (int i = 0; i < field.getField().length; i++) {
            for (int j = 0; j < field.getField()[i].length; j++) {
                if (field.getField()[i][j] == ' ') {
                    botMove(field, i, j);
                    if (field.isPossibleWin(symbol)) {
                        points = addPoint(points, i, j);
                    }
                    deleteMove(field, i, j);
                }
            }
        }
        return points;
    }
    public Point getPossiblePlayerWin(Field field) {
        for (int i = 0; i < field.getField().length; i++) {
            for (int j = 0; j < field.getField()[i].length; j++) {

                if (field.getField()[i][j] == ' ') {
                    playerMove(field, i, j);

                    if (field.winnerIs(getEnemySymbol(symbol))) {
                        deleteMove(field, i, j);

                        return new Point(i, j);
                    }
                    deleteMove(field, i, j);
                }
            }
        }
        field.printField();
        return null;
    }
    public boolean isItFirstBotMove(Field field) {
        int count = 0;
        for (int i = 0; i < field.getField().length; i++) {
            for (int j = 0; j < field.getField()[i].length; j++) {
                if (field.getField()[i][j] != ' ') count++;
            }
        }
        return count <= 1;
    }

    public Point winnerMove(Field field) {
        for (int i = 0; i < field.getField().length; i++) {
            for (int j = 0; j < field.getField()[i].length; j++) {
                if (field.getField()[i][j] == ' ') {
                    botMove(field, i, j);
                    if (field.winnerIs(getSymbol())) {
                        deleteMove(field, i, j);
                        return new Point(i, j);
                    }
                    deleteMove(field, i, j);
                }
            }
        }
        return null;
    }

    public Point rndMove(Field field) {
        Point[] points = new Point[0];
        for (int i = 0; i < field.getField().length; i++) {
            for (int j = 0; j < field.getField()[i].length; j++) {
                if (field.getField()[i][j] == ' '){
                    points = addPoint(points, i , j);
                }
            }
        }
        int index = (int) (Math.random() * points.length);
        return points[index];
    }

    private Point botPoint(Field field){


        if (isItFirstBotMove(field)){


        return firstMove(field);
        }
        else if (winnerMove(field) != null){


            return winnerMove(field);
        }
        else if (getPossiblePlayerWin(field) != null){


            return getPossiblePlayerWin(field);
        }
        else if (possibleWinMoves(field).length > 0){

           return possibleWinMove(possibleWinMoves(field), field);
        }
        else {
            return rndMove(field);
        }
    }

    public void doMove(Field field){
        field.fillField(botPoint(field), symbol);
    }
}


