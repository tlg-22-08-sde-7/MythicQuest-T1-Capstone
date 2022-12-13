package com.mythicquest.engine;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextParser {

    // class variables that are used in parseCommand method
    // declared at top level, so it can be reused.
    private static List<String> command;
    private static List<String> objects;

    static {             // First time class is used, it runs the static block.  Populates list in line 18 & 19.
        // Initialize ArrayLists.
        command = new ArrayList<>();
        objects = new ArrayList<>();

        // Read in data from JSON file
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get("resources/JSON/parseCommand.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonObject parser = JsonParser.parseReader(reader).getAsJsonObject();

        // Load data from JSON file into Array List to be used throughout the program.
        JsonObject commands = parser.get("commands").getAsJsonObject();
        JsonArray verbs = commands.get("command").getAsJsonArray();
        JsonArray nouns = commands.get("objects").getAsJsonArray();

        for (int i = 0; i < verbs.size(); i++) {
            command.add(verbs.get(i).getAsString());
        }

        for (int i = 0; i < nouns.size(); i++) {
            objects.add(nouns.get(i).getAsString());
        }
    }

    public static void textParser2(Player player) throws IOException {
        BufferedReader in;
        String input;
        String output;

        in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            do {
                player.statusInfo();
                System.out.println("What is your move ? ");
                input = in.readLine();     // Command to be provided by the user
                output = runCommand(input, player);
                System.out.println(output);
            } while (!"quit".equals(input));
            Dialogue.exitGame(1, in);
        }
    }

    public static String runCommand(String Move, Player player) {
        List<String> wordList;
        String out = "";
        String lowerMove = Move.trim().toLowerCase();

        if (lowerMove.equals("")) {

            out = "You must enter a valid command";
        } else {
            wordList = wordList(lowerMove);
            parseCommand(wordList, player);
        }
        return out;
    }

    public static List<String> wordList(String input) {
        String delims = "[ \t,.;:/?!\"']+";
        List<String> moveList = new ArrayList<>();
        String[] words = input.split(delims); //String split into Array

        for (String word : words) {
            moveList.add(word);
        }
        return moveList;
    }

    public static void parseCommand(List<String> moveInput, Player player) {
        // Initialized to an empty String.
        String verb = "";
        String noun = "";
        // List<String> command = new ArrayList<>(Arrays.asList("take", "drop", "go", "look", "get"));
        // List<String> objects = new ArrayList<>(Arrays.asList("gloves", "googles", "weapon", "table"));

        if (moveInput.size() != 2) {
            System.out.println("Please provide 2 worded commands only");
        } else {
            verb = moveInput.get(0);
            noun = moveInput.get(1);
            if (!command.contains(verb)) {
                System.out.println(verb + " is not a valid verb");
                commandsAvailable();
                System.out.println("");
            }
            if (!objects.contains(noun)) {
                System.out.println(noun + " is not a valid noun");
                commandsAvailable();
                System.out.println("");
            }
        }

        if (verb.equals("help") && noun.equals("commands")) {
           commandsAvailable();
        }
        if(verb.equals("check") && noun.equals("map")) {
            Screens.scenes.printMap();
        }
    }

    public static void commandsAvailable() {
        System.out.println("Valid commands are:");
        for (int i = 0; i < command.size(); i++) {
            System.out.print(command.get(i) + ", ");
        }
    }

}
