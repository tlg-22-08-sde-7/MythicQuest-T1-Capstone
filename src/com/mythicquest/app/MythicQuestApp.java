package com.mythicquest.app;

import java.io.IOException;
import com.mythicquest.engine.Screens;

public class MythicQuestApp {

    public MythicQuestApp() throws IOException {

    }

    public void start() throws Exception {
        welcome();
        // play();
        // etc
    }

    private void welcome() throws IOException {
        Screens.startScreen();
    }

}