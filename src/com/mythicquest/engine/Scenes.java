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
import java.util.Random;

// Class loads all locations from map.JSON, and manages them in a 2D Array

class Scenes {

    private Location[][] locations;

    // ctor.  Read file from JSON.
    public Scenes() {
        // Initializa grid with a 6 X 7 Array.  6 rows and 7 columns
        locations = new Location[6][7];

        // Map
        Reader reader = null;

        Reader readerHills = null;
        Reader readerBridge = null;
        Reader readerForest = null;
        Reader readerMountains = null;
        Reader readerPlains = null;
        Reader readerRiver = null;
        Reader readerTown = null;
        Reader readerVillage2 = null;
        Reader readerFields = null;
        Reader readerRoad = null;

        //reads json files and saves it to variables
        try {
            reader = Files.newBufferedReader(Paths.get("resources/JSON/map.json"));
            readerHills = Files.newBufferedReader(Paths.get("resources/JSON/terrainDescriptions/hills.json"));
            readerBridge = Files.newBufferedReader(Paths.get("resources/JSON/terrainDescriptions/bridge.json"));
            readerForest = Files.newBufferedReader(Paths.get("resources/JSON/terrainDescriptions/forests.json"));
            readerMountains = Files.newBufferedReader(Paths.get("resources/JSON/terrainDescriptions/mountains.json"));
            readerPlains = Files.newBufferedReader(Paths.get("resources/JSON/terrainDescriptions/plains.json"));
            readerRiver = Files.newBufferedReader(Paths.get("resources/JSON/terrainDescriptions/river.json"));
            readerTown = Files.newBufferedReader(Paths.get("resources/JSON/terrainDescriptions/town.json"));
            readerVillage2 = Files.newBufferedReader(Paths.get("resources/JSON/terrainDescriptions/villages.json"));
            readerFields = Files.newBufferedReader(Paths.get("resources/JSON/terrainDescriptions/fields.json"));
            readerRoad = Files.newBufferedReader(Paths.get("resources/JSON/terrainDescriptions/roads.json"));


        } catch (IOException e) {
            e.printStackTrace();
        }

        // parses map
        JsonObject parser = JsonParser.parseReader(reader).getAsJsonObject();

        JsonArray parserHills = JsonParser.parseReader(readerHills).getAsJsonArray();
        JsonArray parserBridge = JsonParser.parseReader(readerBridge).getAsJsonArray();
        JsonArray parserForest = JsonParser.parseReader(readerForest).getAsJsonArray();
        JsonArray parserMountains = JsonParser.parseReader(readerMountains).getAsJsonArray();
        JsonArray parserPlains = JsonParser.parseReader(readerPlains).getAsJsonArray();
        JsonArray parserRiver = JsonParser.parseReader(readerRiver).getAsJsonArray();
        JsonArray parserTown = JsonParser.parseReader(readerTown).getAsJsonArray();
        JsonArray parserVillages = JsonParser.parseReader(readerVillage2).getAsJsonArray();
        JsonArray parserFields = JsonParser.parseReader(readerFields).getAsJsonArray();
        JsonArray parserRoad = JsonParser.parseReader(readerRoad).getAsJsonArray();

        //  WIll generate random number based on size of Array.
        Random random = new Random();

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


                String description = "";
                switch (name) {
                    case "hills": {
                        description = parserHills.get(random.nextInt(parserHills.size())).getAsString();
                        break;
                    }
                    case "bridge": {
                        description = parserBridge.get(random.nextInt(parserBridge.size())).getAsString();
                        break;
                    }
                    case "forest": {
                        description = parserForest.get(random.nextInt(parserForest.size())).getAsString();
                        break;
                    }
                    case "mountains": {
                        description = parserMountains.get(random.nextInt(parserMountains.size())).getAsString();
                        break;
                    }
                    case "plains": {
                        description = parserPlains.get(random.nextInt(parserPlains.size())).getAsString();
                        break;
                    }
                    case "river": {
                        description = parserRiver.get(random.nextInt(parserRiver.size())).getAsString();
                        break;
                    }
                    case "town": {
                        description = parserTown.get(random.nextInt(parserTown.size())).getAsString();
                        break;
                    }
                    case "village1": {
                        description = parserVillages.get(random.nextInt(parserVillages.size())).getAsString();
                        break;
                    }
                    case "village2": {
                        description = parserVillages.get(random.nextInt(parserVillages.size())).getAsString();
                        break;
                    }
                    case "fields": {
                        description = parserFields.get(random.nextInt(parserFields.size())).getAsString();
                        break;
                    }
                    case "road": {
                        description = parserRoad.get(random.nextInt(parserRoad.size())).getAsString();
                        break;
                    }
                    default: {
                        description = "You have found a cave !";
                    }
                }
                // ctor
                // Create a location object from data
                Location location = new Location(name, items, x, y, description);
                locations [y][x] = location;
            }
        }
    }


    public void printLocations() {
        for (int i = 0; i < 6; i++) {
            System.out.println(Arrays.toString(locations[i]));
        }
    }

    //this function allows the player to move
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
        int rowCount = 0;

        System.out.printf("%13s", "X-0");
        for (int i = 1; i < 7; i++) {
            System.out.printf("%14s", "X-" + i);
        }
        System.out.println();
        for (int i = 0; i < 104; i++) {
            System.out.print("_");
        }
        System.out.println();
        for (int row = 0; row < 6; row++) {
            System.out.print("Y-" + row + "  |");
            for (int column = 0; column < locations[row].length; column++) {
                System.out.printf("   %-10s|", locations[row][column]);
            }
            System.out.println();
            //rowCount++;
        }
        for (int i = 0; i < 104; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    // this was probably for testing. It can be commented out.
//    public static void main(String[] args) {
//        Scenes scenes = new Scenes();
//        scenes.printMap();
//        //scenes.printLocations();
//        Location startingPoint = scenes.getLocations()[2][0];
//        Location nextPoint = scenes.goToLocation(startingPoint, Direction.EAST);
//        System.out.println(nextPoint);
//    }

    public Location[][] getLocations() {
        return locations;
    }
}