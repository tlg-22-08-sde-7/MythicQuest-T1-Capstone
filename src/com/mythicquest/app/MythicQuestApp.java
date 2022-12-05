package com.mythicquest.app;

import java.io.IOException;
import com.mythicquest.engine.Screens;

public class MythicQuestApp {

    public MythicQuestApp() throws IOException {

    }

    public void execute() throws Exception {
        welcome();
        //
        //
        //

    }

    private void welcome() throws IOException {
        Screens.startScreen();
    }

}