package com.mythicquest;

import com.mythicquest.app.MythicQuestApp;

/*
 * This class is the client class for Mythic Quest. Its sole purpose is to launch the game.
 */

public class MythicQuestMain {

    // TODO: Handle exception before it gets to the main class
    public static void main(String[] args) throws Exception {
        // Create an instance of MythicQuestApp
        MythicQuestApp app = new MythicQuestApp();

        // Call the start method to run the game
        app.start();
    }
}                   