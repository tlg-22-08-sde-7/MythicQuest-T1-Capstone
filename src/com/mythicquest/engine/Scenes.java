package com.mythicquest.engine;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

// Class loads all locations from map.JSON, and manages them in a 2D Array.
class Scenes {

    private Location[][] locations;

    // ctor.  Read file from JSON.
    public Scenes() {
        // Initializa grid with a 6 X 7 Array.  6 rows and 7 columns
        locations = new Location[6][7];

        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get("resources/JSON/map.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonObject parser = JsonParser.parseReader(reader).getAsJsonObject();
        for (int z = 0; z < 6; z++) {
            JsonArray row = parser.get("y" + z).getAsJsonArray();

            // looping through each column element found in this row.
            for (int i = 0; i < row.size(); i++) {
                JsonObject locationJSON =  row.get(i).getAsJsonObject();
                String name = locationJSON.get("name").getAsString();
                int x = Integer.parseInt(locationJSON.get("x").getAsString());
                int y = Integer.parseInt(locationJSON.get("y").getAsString());
                ArrayList<String> items = new ArrayList<>();
                JsonArray itemsArray = locationJSON.get("items").getAsJsonArray();

                // Add items to the items ArrayList in line 41.
                for (int j = 0; j < itemsArray.size(); j++) {
                    items.add(itemsArray.get(j).getAsString());
                }

                // Create a location object from data
                Location location = new Location(name, items, x, y);
                locations [y][x] = location;
            }
        }
    }

    public void printLocations() {
        for (int i = 0; i < 6; i++) {
            System.out.println(Arrays.toString(locations[i]));
        }
    }

    public Location goToLocation(Location current, Direction direction) {
        // determine if cell exists in direction / grid and return
        int currentColumn = current.getX();
        int currentRow = current.getY();
        if(direction == Direction.NORTH) {
            if((currentRow - 1) < 0)
                return null;
            else {
                return locations[currentRow - 1][currentColumn];
            }
        }
        else if (direction == Direction.EAST) {
            if((currentColumn + 1) > 6)
                return null;
            else {
                return locations[currentRow][currentColumn + 1];
            }
        }
        else if (direction == Direction.SOUTH) {
            if ((currentRow + 1) > 5)
                return null;
            else {
                return locations[currentRow + 1][currentColumn];
            }
        }
        else if (direction == Direction.WEST) {
            if ((currentColumn - 1) < 0)
                return null;
            else {
                return locations[currentRow][currentColumn - 1];
            }
        }
        return null;
    }

    public void printMap() {
        for (int i = 0; i < 99; i++) {
            System.out.print("_");
        }
        System.out.println();
        for (int row = 0; row < 6; row++) {
            System.out.print("|");
            for (int column = 0; column < locations[row].length; column++) {
                System.out.printf("   %-10s|", locations[row][column]);
            }
            System.out.println();
        }
        for (int i = 0; i < 99; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scenes scenes = new Scenes();
        scenes.printMap();
        // scenes.printLocations();
        Location startingPoint = scenes.getLocations()[2][0];
        Location nextPoint = scenes.goToLocation(startingPoint, Direction.EAST);
        System.out.println(nextPoint);
    }

    public Location[][] getLocations() {
        return locations;
    }
}