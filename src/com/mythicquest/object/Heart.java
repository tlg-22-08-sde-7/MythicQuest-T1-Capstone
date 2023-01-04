package com.mythicquest.object;

import com.mythicquest.app.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

public class Heart extends SuperObject {
    GamePanel gp;

    public Heart(GamePanel gp) {
        this.gp = gp;

        name = "Heart";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Health/empty-heart.png"));
            image1 = ImageIO.read(getClass().getResourceAsStream("/Health/quarter-heart.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/Health/half-heart.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/Health/three-quarter-heart.png"));
            image4 = ImageIO.read(getClass().getResourceAsStream("/Health/full-heart.png"));

            image = uTool.scaleImage(image, gp.getScaledTileSize(), gp.getScaledTileSize());
            image1 = uTool.scaleImage(image1, gp.getScaledTileSize(), gp.getScaledTileSize());
            image2 = uTool.scaleImage(image2, gp.getScaledTileSize(), gp.getScaledTileSize());
            image3 = uTool.scaleImage(image3, gp.getScaledTileSize(), gp.getScaledTileSize());
            image4 = uTool.scaleImage(image4, gp.getScaledTileSize(), gp.getScaledTileSize());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}