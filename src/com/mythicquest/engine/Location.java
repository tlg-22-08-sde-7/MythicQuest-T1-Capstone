package com.mythicquest.engine;

import java.util.ArrayList;

public class Location {

    private String name;
    private ArrayList<String> items;
    private int x;
    private int y;
    private Directions directions;
    private String description;

    // ctor.  Will be called to initialize instance variables with data.
    public Location(String name, ArrayList<String> items, int x, int y, String description) {
        this.name = name;
        this.items = items;
        this.x = x;
        this.y = y;
        this.description = description;
    }

    public Location(){

    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void removeItem(String  item){
        getItems().remove(item);
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Directions getDirections() {
        return directions;
    }

    public void setDirections(Directions directions) {
        this.directions = directions;
    }

    class Directions {
        String north;
        String east;
        String south;
        String west;
    }
    public String toString() {
        return name;
    }

    public String moreInfo() {
        return name + ", x:" + x + ", y:" + y;
    }
}