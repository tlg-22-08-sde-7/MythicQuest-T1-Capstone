package com.mythicquest;

import org.junit.Assert;

import java.awt.*;

public class TitleScreenTest {

    @org.junit.Test
    public void testTitleScreen() {
        TitleScreen titleScreen = new TitleScreen();
        Assert.assertNotNull(titleScreen);

        Assert.assertEquals(titleScreen.titleFont, new Font("Times New Roman", Font.PLAIN, 90));

        Assert.assertFalse(titleScreen.window.isResizable());
    }
}

