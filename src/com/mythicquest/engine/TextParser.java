package com.mythicquest.engine;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class TextParser {

    // class variables that are used in parseCommand method
    // declared at top level, so it can be reused.

    private static HashMap<String, List<?>> verbsMap;
    private static Gson gson;                               // not instance field, it's static



    // First time class is used, it runs the static block.  Populates list in line 18 & 19.
    public static Map getVerbsMap() {

        //TODO: , rename this to populateVerbsMap
        // and make public getter that
        // returns private field
        // to test it

        // Read in data from JSON file
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get("resources/JSON/parseCommand.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        gson = new Gson();
        verbsMap = new HashMap<>();      // populates it
        verbsMap = gson.fromJson(reader, verbsMap.getClass());    // returns it to confirm
        //  JsonObject parser = JsonParser.parseReader(reader).getAsJsonObject();

        return verbsMap;
    }

    public static void textParser(Player player) throws IOException {
        BufferedReader in;
        String input;
        String output;

        in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            do {
                player.statusInfo();
                System.out.println("What is your move ? ");
                input = in.readLine();     // Command to be provided by the user
                //takes user input and calls runCommand function
                output = runCommand(input, player);
                System.out.println(output);
            } while (!"quit".equals(input));
            Dialogue.exitGame(1, in);
        }
    }

    public static String runCommand(String Move, Player player) throws IOException {
        List<String> wordList; // reference to wordList, declares it
        String out = "";       // empty string, what length of this string, yet not null
        System.out.println("Move: " + Move);  // TODO: if Move not equal to null.. etc
        String lowerMove = Move.trim().toLowerCase(); // TODO: fix this if string is null... null
        // Pointer exception

xc          if ("help".equals(lowerMove)) {
            commandsAvailable();
        }

        if (lowerMove.equals("")) {
            out = "You must enter a valid command";
        } else {
            //sends userinput to wordlist which removes random characters
            wordList = wordList(lowerMove);
            //then it calls parseCommand which executes verb/noun
            parseCommand(wordList, player);
        }
        return out;
    }
    //

    //removes special characters from user input
    public static List<String> wordList(String input) {
        String delims = "[ \t,.;:/?!\"']+";
        List<String> moveList = new ArrayList<>();
        String[] words = input.split(delims); //String split into Array
        String verb = words[0];

        getVerbsMap();                // populating the map ... TODO: what if returned map and map
        List<String> action = new ArrayList<>();

        if (verbsMap.containsKey(verb.toLowerCase(Locale.ROOT))) {
            moveList.add(verb);
        }
        for (Map.Entry<String, List<?>> entry : verbsMap.entrySet()) {
            for (Object synonyms : entry.getValue()) {
                if (synonyms.equals(verb.toLowerCase(Locale.ROOT))) {
                    moveList.add(entry.getKey());
                }
            }
        }
        if (words.length == 2 ) {
            moveList.add(words[1]);
        }

        return moveList;
    }


    public static void parseCommand(List<String> moveInput, Player player) throws IOException {
        if (moveInput.equals("quit") || moveInput.equals("q")){
            quitGame();
        }
        if (moveInput.size() == 2){
            route(moveInput, player);
        }
    }


    public static void route(List<String> userInput, Player player) throws IOException {
        String verb = userInput.get(0);
        String noun = userInput.get(1);

        switch (verb){
            case "quit":
            case "q":
                quitGame();
            case "go":
                Player.movePlayer(player, noun);
                break;
            case "get":
                player.addItem(noun, player);
                break;
            case "look":
                Screens.scenes.printMap();
                break;
            case "drop":
                player.removeItem(noun, player);
                break;
            case "eat":
            case "drink":
                player.consumeItem(player, noun);
                break;
            case "help":
                //
                break;
            default:
                System.out.println("That is not a valid command");
                break;
        }
    }

    public static void quitGame(){
        System.out.println("You have exited Mythic Quest. Thanks for playing");
        System.exit(0);
    }
                         // TODO: how would we do a unit test on TextParser class


    public static void commandsAvailable() {
        System.out.println();
        System.out.println("As you play this game, please remember that commands require both a verb and a noun");
        System.out.println();
        System.out.println("Valid commands are:");       // not accessible in a unit test
        getVerbsMap();                                   // verbsMap is private
        System.out.println(verbsMap.keySet());
    }


}
