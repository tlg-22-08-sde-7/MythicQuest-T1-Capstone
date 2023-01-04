package com.mythicquest.object;

import com.mythicquest.app.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

class Heart extends SuperObject {
    GamePanel gp;

    public Heart(GamePanel gp) {
        this.gp = gp;

        name = "Heart";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("resources/Health/full-heart.png"));
            image1 = ImageIO.read(getClass().getResourceAsStream("resources/Health/three-quarter-heart.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("resources/Health/half-heart.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("resources/Health/quarter-heart.png"));
            image4 = ImageIO.read(getClass().getResourceAsStream("resources/Health/empty-heart.png"));
//            ImageIcon fullHeart = new ImageIcon("resources/Health/full-heart.png");
//            ImageIcon threeQuarterHeart = new ImageIcon("resources/Health/three-quarter-heart.png");
//            ImageIcon halfHeart = new ImageIcon("resources/Health/half-heart.png");
//            ImageIcon quarterHeart = new ImageIcon("resources/Health/quarter-heart.png");
//            ImageIcon emptyHeart = new ImageIcon("resources/Health/empty-heart.png");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}