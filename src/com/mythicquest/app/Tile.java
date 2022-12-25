package com.mythicquest.app;

import java.awt.*;

public class Tile {
    public int[][] tileData;
    public Image image;
    public boolean collision = false;
    public boolean isAPortal = false;
    public int tileNumber;
    public Rectangle area = new Rectangle(0,0,48,48);



}
