package guchi.the.hasky.game;

import guchi.the.hasky.entity.Bot;
import guchi.the.hasky.entity.Player;
import guchi.the.hasky.field.Field;

import java.awt.desktop.SystemSleepListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private Bot bot;
    private Player player;
    private Field field;


    public Game() {
        field = new Field();
        bot = new Bot();
        player = new Player();
    }

    public Bot getBot() {
        return bot;
    }

    public void setBot(Bot bot) {
        this.bot = bot;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Field getField() {
        return field;
    }


    public void printMenu() {
        System.out.println("""
                << 1 >> Rules
                << 2 >> Settings
                << 3 >> Multiplayer battle
                << 4 >> Play with bot
                << 5 >> Exit game
                """);
    }

    public void printRules() {
        System.out.println("""
                1. Choose your side;
                2. Do your first doStep;
                3. Fight your opponent;
                4. Don't hurt anyone;
                5. Repeat point four.
                """);
    }

    private void printDrawInfo() {
        System.out.println("It's a draw.");
    }

    private void printWinInfo(Object obj) {
        System.out.println("Winner is: " + obj.toString());
    }

    public boolean settings(int value) {
        int rnd = 0;
        if (value == 3) {
            rnd = ThreadLocalRandom.current().nextInt(1, 4);
            value = rnd;
        }
        if (value == 1) {
            player.setSymbol('X');
            bot.setSymbol('O');
            return true;
        } else if (value == 2) {
            player.setSymbol('O');
            bot.setSymbol('X');
            return true;
        }
        return false;
    }

    private char getSymbol(char symbol){
        return symbol == 'O' ? 'X' : 'O';
    }

    private boolean whoseTurn() {
        return player.getSymbol() == 'X';
    }

    public void playWithPlayer() throws IOException {
        field = new Field();
        field.printField();
        Player secondPlayer = new Player("Player 2 ", getSymbol(player.getSymbol()));
        boolean whosePlayerTurn = whoseTurn();

        while (!field.winnerIs(player.getSymbol()) &&
                !field.winnerIs(secondPlayer.getSymbol()) &&
                !field.isDraw()) {
            if (whosePlayerTurn) {
                player.printMoveInfo();
                player.doMove(field, reader);
                whosePlayerTurn = false;
            } else {
                secondPlayer.printMoveInfo();
                secondPlayer.doMove(field, reader);
                whosePlayerTurn = true;
            }
            if (field.winnerIs(player.getSymbol())) {
                printWinInfo(player);
            } else if (field.winnerIs(secondPlayer.getSymbol())) {
                printWinInfo(secondPlayer);
            } else if (field.isDraw()) {
                printDrawInfo();
            }
            field.printField();
        }
    }

    public void playWithBot() throws IOException {
        field = new Field();
        field.printField();
        boolean whosePlayerTurn = whoseTurn();

        while (!field.winnerIs(bot.getSymbol()) &&
                !field.winnerIs(player.getSymbol()) &&
                !field.isDraw()) {

            if (!whosePlayerTurn) {
                bot.printMoveInfo();
                bot.doMove(field);
            } else {
                player.printMoveInfo();
                player.doMove(field, reader);
            }

            field.printField();

            if (field.winnerIs(bot.getSymbol())) {
                printWinInfo(bot);
            } else if (field.winnerIs(player.getSymbol())) {
                printWinInfo(player);
            }

            if (field.isDraw() && !field.winnerIs(bot.getSymbol())) {
                printDrawInfo();
            }

            whosePlayerTurn = !whosePlayerTurn;
        }
    }
}









