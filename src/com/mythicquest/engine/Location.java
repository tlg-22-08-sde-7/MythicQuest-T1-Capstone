package com.mythicquest.engine;

class Location {

    private String name;
    private Directions directions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}