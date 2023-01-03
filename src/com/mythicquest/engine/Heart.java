package com.mythicquest.engine;

import com.mythicquest.app.GamePanel;

import javax.swing.*;
import java.io.IOException;

class Heart {
    GamePanel gp;

    public Heart(GamePanel gp) {
        this.gp = gp;

        ImageIcon fullHeart = new ImageIcon("resources/Health/full-heart.png");
        ImageIcon threeQuarterHeart = new ImageIcon("resources/Health/three-quarter-heart.png");
        ImageIcon halfHeart = new ImageIcon("resources/Health/half-heart.png");
        ImageIcon quarterHeart = new ImageIcon("resources/Health/quarter-heart.png");
        ImageIcon emptyHeart = new ImageIcon("resources/Health/empty-heart.png");
    }
}