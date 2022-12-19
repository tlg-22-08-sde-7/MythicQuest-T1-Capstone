package com.mythicquest.engine;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

class Inventory {

    // WIll return an inventory of objects
    // Stores state of inventory in inventory.json
    ArrayList<String> randItems;
    ArrayList<String> armorItems;
    ArrayList<String> pharmacyItems;

    // ctor
    // read in contents of inventory json, and populate ArrayList
    public Inventory() {

        // initialize ArrayList for values
        randItems = new ArrayList<>();
        armorItems = new ArrayList<>();
        pharmacyItems = new ArrayList<>();

        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get("resources/JSON/inventory.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonObject parser = JsonParser.parseReader(reader).getAsJsonObject();
        JsonObject categories = parser.get("categories").getAsJsonObject();
        JsonArray randItemsArray = categories.get("rand-items").getAsJsonArray();
        JsonArray armorItemsArray = categories.get("armor-items").getAsJsonArray();
        JsonArray pharmacyItemsArray = categories.get("pharmacy-items").getAsJsonArray();

        for (int i = 0; i < randItemsArray.size(); i++) {
            randItems.add(randItemsArray.get(i).getAsString());
        }

        for (int i = 0; i < armorItemsArray.size(); i++) {
            armorItems.add(armorItemsArray.get(i).getAsString());
        }

        for (int i = 0; i < pharmacyItemsArray.size(); i++) {
            pharmacyItems.add(pharmacyItemsArray.get(i).getAsString());
        }
        System.out.println(randItems);
        System.out.println(armorItems);
        System.out.println(pharmacyItems);
    }
}