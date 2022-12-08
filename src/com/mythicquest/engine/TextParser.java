package com.mythicquest.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextParser {

    public static void textParser2() throws IOException {
        BufferedReader in;
        String input;
        String output;

        in = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.println("What is your move ? ");
            input = in.readLine();     // Command to be provided by the user
            output = runCommand(input);
            System.out.println(output);
        } while (!"quit".equals(input));

    }

    public static String runCommand(String Move) {
        List<String> wordList;
        String out = "";
        String lowerMove = Move.trim().toLowerCase();

        if (lowerMove.equals("")) {

            out = "You must enter a valid command";
        } else {
            wordList = wordList(lowerMove);
            parseCommand(wordList);
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

    public static void parseCommand(List<String> moveInput) {
        String verb;
        String noun;
        List<String> command = new ArrayList<>(Arrays.asList("take", "drop", "go", "look"));
        List<String> objects = new ArrayList<>(Arrays.asList("gloves", "googles", "weapon", "table"));

        if (moveInput.size() != 2) {
            System.out.println("Please provide 2 worded commands only");
        } else {
            verb = moveInput.get(0);
            noun = moveInput.get(1);
            if (!command.contains(verb)) {
                System.out.println(verb + " is not a valid verb");
            }
            if (!objects.contains(noun)) {
                System.out.println(noun + " is not a valid noun");
            }
        }
    }

}
