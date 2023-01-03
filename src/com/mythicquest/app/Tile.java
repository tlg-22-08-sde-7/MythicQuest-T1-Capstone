package com.mythicquest.app;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    public BufferedImage image;
    public boolean collision = false;

    public boolean isAPortal = false;
    public String tileNumber;
    public Rectangle area = new Rectangle(0,0,48,48);



}
