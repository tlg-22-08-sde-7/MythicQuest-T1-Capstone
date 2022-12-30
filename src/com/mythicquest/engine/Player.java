package com.mythicquest.engine;

import java.io.IOException;
import java.util.ArrayList;

import com.mythicquest.app.GamePanel;
import com.mythicquest.app.KeyHandler;
import com.mythicquest.entity.Sprite;

import javax.swing.ImageIcon;
import java.awt.Graphics2D;

public class Player extends Sprite {
    private Location location;
    private ArrayList<String> inventory;
    private int healthLevel;

    GamePanel gp;
    KeyHandler keyH;

    // ctor
    public Player(Location currentLocation) {
        this.location = currentLocation;
        this.inventory = new ArrayList<>(); // Initialize ArrayList
        this.healthLevel = 100;
    }

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getImage();
    }

    public void setDefaultValues() {
        setWorldX(100);
        setWorldY(100);
        setSpeed(4);
    }

    public void getImage() {
        String playerImagePath = "resources/Sprites/pixil-frame-0.png";
        ImageIcon player = new ImageIcon(playerImagePath);
        setPlayerImage(player.getImage());
    }

    public void update() {
        if (keyH.up) {
            setWorldY(getWorldY() - getSpeed());
        } else if (keyH.down) {
            setWorldY(getWorldY() + getSpeed());
        } else if (keyH.left) {
            setWorldX(getWorldX() - getSpeed());
        } else if (keyH.right) {
            setWorldX(getWorldX() + getSpeed());
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(getPlayerImage(), getWorldX(), getWorldY(), gp.getScaledTileSize(), gp.getScaledTileSize(), null);
    }

    // Fight enemies
    public boolean fight(Enemy enemy) {
        boolean playerWins = false;

        while (getHealthLevel() > 0 && enemy.getHealth() > 0) {
            enemy.setHealth(enemy.getHealth() - attack());
            setHealthLevel(getHealthLevel() - enemy.attack());
        }
        if (getHealthLevel() > 0) {
            playerWins = true;
        }
        return playerWins;
    }

    private int attack() {
        return (int) (Math.random() * 10) + 1;
    }

    // Add items to inventory
    public void addItem(String item, Player player) {
        if (player.getLocation().getItems().contains(item) && !inventory.contains(item)) {
            inventory.add(item);
            getLocation().getItems().remove(item);
        } else if (inventory.contains(item)) {
            System.out.println(item + " is already in your inventory.");
        }
    }

    // Remove items from inventory
    public void removeItem(String item, Player player) {
        if (player.getInventory().contains(item)) {
            inventory.remove(item);
        } else {
            System.out.println(item + " is not in your inventory");
        }
    }

    // Check items in inventory
    public void checkItem() {
        System.out.println("Current inventory: ");
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println(inventory.get(i));
        }
    }

    //player can eat and drink food items
    public void consumeItem(Player player, String randItems) {
        // only if it contains these specific items:  "broccoli", "avocado", "carrots", "steak", "2-hr-energy", "5-hr-energy", "athletic-green-juice"
        if (player.getInventory().contains(randItems)) {
            if (randItems.equalsIgnoreCase("broccoli") || randItems.equals("avocado") || randItems.equals(
                    "carrots") || randItems.equals("steak")) {
                setHealthLevel(getHealthLevel() + 10);
                removeItem(randItems, player);
                System.out.println("Health level increased by 10");
            } else if (randItems.equals("2-hr-energy") || randItems.equals("5-hr-energy") || randItems.equals("athletic-green-juice")  || randItems.equals ("wild-bluerries")) {
                setHealthLevel(getHealthLevel() + 20);
                removeItem(randItems, player);
                System.out.println("Health level increased by 20");
            } else if (randItems.equals("adult-beverage") || randItems.equals("dodgy-plant") || randItems.equals("bag-of-brownies") || randItems.equals("bag-of-donuts")) {
                setHealthLevel(getHealthLevel() - 10);
                removeItem(randItems, player);
                System.out.println("Health level decreased by 10");
            } else if (randItems.equals("Spiked-Coffee")){
                setHealthLevel(getHealthLevel() - 3);
                removeItem(randItems, player);
                System.out.println("Health level decreased by 3");
            }             else if (randItems.equals("chicken-gumbo")) {
                setHealthLevel(getHealthLevel() + 35);
                removeItem(randItems, player);
                System.out.println("Health level increased by 35!!!!!");
            }
        } else {
            System.out.println("You do not have this item in your inventory");
        }
    }
    

    public void addHealth(Player player) {
        player.setHealthLevel(player.getHealthLevel() + 10);
        System.out.println("Health level is now " + player.getHealthLevel());
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


    //move player to new location
    static void movePlayer(Player player, String noun) throws IOException {
        Location newLocation = null;
        if (noun.equals("north")) {
            newLocation = Screens.scenes.goToLocation(player.getLocation(), Direction.NORTH);
            if (newLocation == null) {
                System.out.println("You can't go North.  Please choose another direction");
            } else {
                player.setLocation(newLocation);
            }
        } else if (noun.equals("east")) {
            newLocation = Screens.scenes.goToLocation(player.getLocation(), Direction.EAST);
            if (newLocation == null) {
                System.out.println("You can't go East.  Please choose another direction");
            } else {
                player.setLocation(newLocation);
                if ("cave".equals(newLocation.getName())) {
                    Enemy boss = new Enemy();
                    Screens.endingOutcome(player.fight(boss));
                    System.exit(0);
                }
            }
        } else if (noun.equals("south")) {
            newLocation = Screens.scenes.goToLocation(player.getLocation(), Direction.SOUTH);
            if (newLocation == null) {
                System.out.println("You can't go South.  Please choose another direction");
            } else {
                player.setLocation(newLocation);
                if ("cave".equals(newLocation.getName())) {
                    Enemy boss = new Enemy();
                    Screens.endingOutcome(player.fight(boss));
                    System.exit(0);
                }
            }
        } else if (noun.equals("west")) {
            newLocation = Screens.scenes.goToLocation(player.getLocation(), Direction.WEST);
            if (newLocation == null) {
                System.out.println("You can't go West.  Please choose another direction");
            } else {
                player.setLocation(newLocation);
            }
        } else System.out.println("You have LOST your way !");
    }
}