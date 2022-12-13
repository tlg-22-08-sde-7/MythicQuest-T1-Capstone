package com.mythicquest.engine;

import java.util.ArrayList;

class Player {
    private Location location;
    private ArrayList<String > inventory;
    private int healthLevel;

    // ctor
    public Player(Location currentLocation) {
        this.location = currentLocation;
        this.inventory = new ArrayList<>(); // Initialize ArrayList
        this.healthLevel = 100;
    }

    // Add items to inventory
    public void addItem(String itemAdd) {
        inventory.add(itemAdd);
    }

    // Remove items from inventory
        public void removeItem(String itemRemove) {
        inventory.remove(itemRemove);
        }

    // Check items in inventory
        public void checkItem() {
            System.out.println("Current inventory: ");
            for (int i = 0; i < inventory.size(); i++) {
                System.out.println(inventory.get(i));
            }
        }

        public void statusInfo() {
            System.out.println("Current health: " + healthLevel);
            System.out.println("Location: " + location.moreInfo());
            checkItem();
        }

}