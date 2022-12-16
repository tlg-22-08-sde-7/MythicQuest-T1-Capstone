package com.mythicquest.engine;

import java.util.ArrayList;

class Player {
    private Location location;
    private ArrayList<String> inventory;
    private int healthLevel;

    // ctor
    public Player(Location currentLocation) {
        this.location = currentLocation;
        this.inventory = new ArrayList<>(); // Initialize ArrayList
        this.healthLevel = 100;
    }

    // Fight enemies
    public void fight(Enemy enemy) {
        while (getHealthLevel() > 0 && enemy.getHealth() > 0)
        {
            enemy.setHealth(enemy.getHealth() - attack());
            setHealthLevel(getHealthLevel() - enemy.attack());
        }
        if (getHealthLevel() > 0) {
            System.out.println("Player wins");
        }
        else {
            System.out.println("Enemy wins");
        }
    }

    private int attack() {
        return (int) (Math.random() * 10) + 1;
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
        System.out.println();
        System.out.println("Current health: " + healthLevel);
        System.out.println("Location: " + location.moreInfo());
        System.out.println("Description: " + location.getDescription());
        System.out.println("Items available: " + location.getItems());
        checkItem();

        System.out.println();

    }

    public Location getLocation() {
        return location;
    }

    public ArrayList<String> getInventory() {
        return inventory;
    }

    public int getHealthLevel() {
        return healthLevel;
    }

    public void setHealthLevel(int healthLevel) {
        this.healthLevel = healthLevel;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}