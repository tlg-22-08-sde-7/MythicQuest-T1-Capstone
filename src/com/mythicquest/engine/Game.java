package com.mythicquest.engine;

import com.apps.util.Console;

public class Game {
    // fields
    Boolean running = true;

    //  constructors


    // business methods
    public void clearConsole() {
        Console.clear();
    }

    // accessor methods
    public Boolean getRunning() {
        return running;
    }

    public void setRunning(Boolean running) {
        this.running = running;
    }

}