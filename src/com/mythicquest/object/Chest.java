package com.mythicquest.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Chest extends SuperObject {
    public Chest() {
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}