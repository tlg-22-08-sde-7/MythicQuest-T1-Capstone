package com.mythicquest.app;

import com.mythicquest.engine.*;

import java.io.IOException;
import java.util.Scanner;

public class MythicQuestApp {
    // statics
    // private final static boolean DEBUG_MODE = false; // for testing purposes
    private final static String HISTORY_PATH = "data/autosave.txt";

    // fields
    private long startTime = System.currentTimeMillis();

    // initialize world and scanner for user input
    private final Game game = new Game();
    private Scanner scan = new Scanner(System.in);

    public MythicQuestApp() throws IOException {

    }

    public void start() throws Exception {
        welcome();
        play();
        // etc
    }

    private void welcome() throws IOException {
        Screens.startScreen();
    }

    private void play() {
        int quit = 0;

        while (game.getRunning() == true) {
            clearScreen();

            String input = scan.nextLine();  // "quit the game" using Parser
//            String input = TextParser(resp);

            if (input.toLowerCase().contains("quit")) {
                exitGame(quit);
            }

            if (input.toLowerCase().contains("help")) {
                helpScreen();
            }
        }
    }

    private void clearScreen() {
        game.clearConsole();
    }

    private void exitGame(int exitStatus) {

        System.out.println("Oh hey!  Are you wanting exit the game?");
        String exitChoice = scan.nextLine();

        if (exitChoice.toLowerCase().contains("yes")) {
            // Terminate JVM
            // By convention, nonzero status code indicates abnormal termination
            System.out.println("You got it - ending the game now...  See you again soon? :)");
            System.exit(exitStatus);
        }
    }

    private void helpScreen() {
        // call instructions
        // offers help based on keywords
    }



}