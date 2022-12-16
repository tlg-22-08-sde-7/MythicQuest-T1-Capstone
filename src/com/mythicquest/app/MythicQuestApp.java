package com.mythicquest.app;

import com.apps.util.Console;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mythicquest.engine.*;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/*
 * The class serves as the game controller. It contains all the methods that will be used to control gameplay.
 */

public class MythicQuestApp {
    // statics
    // private final static boolean DEBUG_MODE = false; // for testing purposes
    private final static String HISTORY_PATH = "data/autosave.txt";

    // fields
    private long startTime = System.currentTimeMillis();

    // initialize world and scanner for user input
    private final Game game = new Game();
    private Scanner scan =   new Scanner(System.in);
    //             creates a BufferedReader    Fetches the URL location of the file
    Reader reader = Files.newBufferedReader(Paths.get("resources/JSON/exitGame.json"));
    JsonObject parser = JsonParser.parseReader(reader).getAsJsonObject();

    public MythicQuestApp() throws IOException {

    }

    public void start() throws Exception {
        Console.clear();
        welcome();
        play();
        // etc
    }

    private void welcome() throws IOException {
        Screens.startScreen();
    }

    private void play() {
        int quit = 0;

        while (game.getRunning()) {
            clearScreen();

            String input = scan.nextLine();  // "quit the game" using Parser
//            String input = TextParser(resp);

            if (input.toLowerCase().contains("quit")) {
                exitGame(quit);
            }

            if (input.toLowerCase().contains("help")) {
                // C ->> took out on 12-12-2022 @ 12:18 PM.
                // helpScreen();
                TextParser.commandsAvailable();
            }
        }
    }

    private void clearScreen() {
        game.clearConsole();
    }

    private void exitGame(int exitStatus) {

        // System.out.println("Oh hey!  Are you wanting to exit the game?");
        // C (12-11-2022 @ 8:14 AM) ->> took out the below "sout" to test, and it works.
        // System.out.println(parser.get("confirmExit").getAsString());

        String exitChoice = scan.nextLine();

        if (exitChoice.toLowerCase().contains("yes")) {
            // Terminate JVM
            // By convention, nonzero status code indicates abnormal termination
            // C (12-11-2022 @ 8:11 AM) ->> took out the below "sout" to test, and it works.
            // System.out.println("You got it - ending the game now...  See you again soon? :)");
            System.exit(exitStatus);
        }
    }

    private void helpScreen() {
        // call instructions
        // offers help based on keywords
    }
}