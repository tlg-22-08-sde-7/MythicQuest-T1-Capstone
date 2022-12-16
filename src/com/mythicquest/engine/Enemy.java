package com.mythicquest.engine;

class Enemy {
    private int health = 100;

    public Enemy() {
    }

    public int attack() {
        return (int) (Math.random() * 10) + 1;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}