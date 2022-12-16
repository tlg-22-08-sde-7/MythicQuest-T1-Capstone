package com.mythicquest.engine;

import com.apps.util.Console;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Scanner;

public class Screens {

    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String RESET = "\u001B[0m";
    public static final String WHITE = "\u001B[37m";
    public static final String BLACK = "\u001B[30m";

    public static String instructTitle;
    public static String instructHeader;
    public static String instructBody;

    public static String winBanner;
    public static String lostBanner;

    public static Scenes scenes;    // Bringing in Scenes as static

    static {
        try {
            winBanner = Files.readString(Path.of("resources/Banners/endWin.txt")) ;
            lostBanner = Files.readString(Path.of("resources/Banners/endLost.txt"));
            scenes = new Scenes();  // Create an Instance of Scenes (aka the map)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //this calls splash screen (banners)
    public static void startScreen() throws IOException {
        welcomeBanner();
        getUserInput();
        chooseScreen();
    }

    public static void getUserInput() {
        System.out.println("                                       Choose an option:" + RESET);
        Console.blankLines(1);
        System.out.println(CYAN + "                      Play Mythic Quest now" + PURPLE + "   |   Read the Game Instructions" + RESET);
        Console.blankLines(1);
    }

    public static void welcomeBanner() throws IOException {
        String welcome = Files.readString(Path.of("resources/Banners/welcomeTo.txt"));
        String banner = Files.readString(Path.of("resources/Banners/welcomeBanner.txt"));

        System.out.print(GREEN + welcome + RESET);
        Console.pause(1000);
        System.out.print(YELLOW + banner + RESET);
        Console.blankLines(2);
    }

    public static void chooseScreen() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println(CYAN + "                (type 'Play game' to begin)" + PURPLE + "       (type 'Read' to see instructions)" + RESET);

        String menu = scan.nextLine();

        if (menu.toLowerCase().contains("play")) {
            System.out.println("\nLet's get started!" + RESET);
            System.out.println("If you want to see the Map, type 'check map'");
            System.out.println("If you need help, type 'help commands'\n");

            // Sets the starting location and creates an instance of a Player
            Location startingPoint = scenes.getLocations()[0][0];
            Player player = new Player(startingPoint);

            // go to screen with the first position of the HERO
            TextParser.textParser2(player);
        }

        //handles if a user wants to see instructions
        if (menu.toLowerCase().contains("read")) {
            try {
                instructions();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(menu.toLowerCase().contains("help")) {
        TextParser.commandsAvailable();
        }
    }

    public static void instructions() throws IOException {
        instructTitle = Files.readString(Path.of("resources/Banners/instructionsTitle.txt"));
        instructHeader = Files.readString(Path.of("resources/Banners/instructionsHeading.txt"));
        instructBody = Files.readString(Path.of("resources/Banners/instructionsBody.txt"));

        System.out.println(BLUE + instructTitle + RESET);
        Console.pause(1000);
        Console.blankLines(2);
        System.out.println(YELLOW + instructHeader + RESET);
        Console.pause(1000);
        Console.blankLines(2);
        System.out.println(GREEN + instructBody + RESET);
        Console.pause(20000);
        Console.blankLines(2);
        System.out.println(PURPLE + "Hope that helped.  Now let's play!" + RESET);
        Console.blankLines(2);
        chooseScreen();
    }
}