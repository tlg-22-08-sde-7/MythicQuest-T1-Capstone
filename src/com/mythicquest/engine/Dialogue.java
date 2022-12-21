package com.mythicquest.engine;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Dialogue {

    // Used Buffered Reader to re-use it, from line 23 (in MysticQuestApp)
    public static void exitGame(int exitStatus, BufferedReader scan) throws IOException {
        if (scan == null) {
            scan = new BufferedReader(new InputStreamReader(System.in));
        }
        Reader reader = Files.newBufferedReader(Paths.get("resources/JSON/exitGame.json"));
        JsonObject parser = JsonParser.parseReader(reader).getAsJsonObject();

        // System.out.println("Oh hey!  Are you wanting to exit the game?");
        System.out.println(parser.get("confirmExit").getAsString());

        String exitChoice = scan.readLine();

        if (exitChoice.toLowerCase().contains("yes")) {
            // Terminate JVM
            // By convention, nonzero status code indicates abnormal termination
            System.out.println("You got it - ending the game now...  See you again soon? :)");
            System.exit(exitStatus);
        }
    }
}


