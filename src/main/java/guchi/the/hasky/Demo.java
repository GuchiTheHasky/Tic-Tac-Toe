package guchi.the.hasky;

import guchi.the.hasky.game.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Demo {
    public static void error(){
        System.out.println("Error, wrong number.");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Game game = new Game();

        boolean isAlive = true;

        do {
            System.out.println("Choose option:");
            int choice = 0;
            do {
                game.printMenu();
                choice = Integer.parseInt(reader.readLine());
                if (choice < 1 || choice > 5) error();
            }
            while (choice < 1 || choice > 5);

            switch (choice) {
                case 1 -> {
                    game.printRules();
                }
                case 2 -> {
                    System.out.println("""
                            < 1 > - "X"
                            < 2 > - "O"
                            < 3 > - Random choose.
                            """);

                    int temp = Integer.parseInt(reader.readLine());

                    if (!game.settings(temp)) error();
                    else {
                        System.out.println("You choose: " + game.getPlayer().getSymbol() + ", good-luck.");
                    }
                }
                case 3 -> {
                    game.playWithPlayer();
                }
                case 4 -> {
                    game.playWithBot();
                }
                case 5 -> {
                    System.out.println("Exit.");
                    isAlive = false;
                }
            }
        }
        while (isAlive);
    }
}
