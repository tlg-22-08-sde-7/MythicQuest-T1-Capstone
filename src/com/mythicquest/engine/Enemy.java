package com.mythicquest.engine;

class Enemy {
    private int health = 100;
    private int attackMultiplier;

    public Enemy(int health, int attackMultiplier) {
        this.health = health;
        this.attackMultiplier = attackMultiplier;
    }
    public void attack() {

    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackMultiplier() {
        return attackMultiplier;
    }

    public void setAttackMultiplier(int attackMultiplier) {
        this.attackMultiplier = attackMultiplier;
    }
}