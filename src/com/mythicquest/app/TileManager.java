package com.mythicquest.app;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;


    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        //  getTileImage();
        //loadMapOne("resources/tiles/maps/map1.txt");
        // loadMapOne("resources/tiles/maps/test.txt");
        loadNewMap(2);

    }

    public ArrayList<String> getMapsPath() {
        ArrayList<String> maps = new ArrayList<>();
        maps.add("resources/tiles/maps/map1.txt");
        maps.add("resources/tiles/maps/map2.txt");
        maps.add("resources/tiles/maps/land01.txt");
        return maps;
    }


    //function can take in a counter
    //depending on the counter a new map gets called
    public void loadNewMap(int mapNumber) {
        System.out.println(getMapsPath().get(mapNumber).toString());
        loadMap(getMapsPath().get(mapNumber).toString());
        getTileImage(mapNumber);

    }


    private void getTileImage(int num) {
        switch (num) {
            case 0:
                getTileImage1();
                break;
            case 1:
                getTileImages2();
                break;
            default:
                getTileImage1();
                ;
                break;
        }
    }

    private void getTileImage1() {
        ImageIcon tree = new ImageIcon("resources/tiles/SPRITESHEET/floor/tree1.png");
        tile[3] = new Tile();
        tile[3].image = tree.getImage();
        tile[3].collision = true;

        ImageIcon grass1 = new ImageIcon("resources/tiles/SPRITESHEET/grass01.png");
        tile[0] = new Tile();
        tile[0].image = grass1.getImage();

        ImageIcon sandPath = new ImageIcon("resources/tiles/SPRITESHEET/floor/sand1.png");
        tile[1] = new Tile();
        tile[1].image = sandPath.getImage();


        ImageIcon sandPathSide = new ImageIcon("resources/tiles/SPRITESHEET/floor/sand1.png");
        tile[2] = new Tile();
        tile[2].image = sandPathSide.getImage();

        ImageIcon portal = new ImageIcon("resources/tiles/SPRITESHEET/floor/portal.png");
        tile[6] = new Tile();
        tile[6].image = portal.getImage();
        tile[6].collision = true;
        tile[6].isAPortal = true;
        tile[6].tileNumber = 2;

        ImageIcon eight = new ImageIcon("resources/tiles/SPRITESHEET/pondisde4.png");
        tile[4] = new Tile();
        tile[4].image = eight.getImage();

        ImageIcon nine = new ImageIcon("resources/tiles/SPRITESHEET/pondisde4.png");
        tile[5] = new Tile();
        tile[5].image = nine.getImage();

    }

    public void getTileImages2() {
        ImageIcon tree = new ImageIcon("resources/tiles/SPRITESHEET/floor/tree1.png");
        tile[3] = new Tile();
        tile[3].image = tree.getImage();
        tile[3].collision = true;
        tile[3].tileNumber = 3;

        ImageIcon grass1 = new ImageIcon("resources/tiles/SPRITESHEET/grass01.png");
        tile[0] = new Tile();
        tile[0].image = grass1.getImage();

        ImageIcon sandPath = new ImageIcon("resources/tiles/SPRITESHEET/floor/sand1.png");
        tile[1] = new Tile();
        tile[1].image = sandPath.getImage();

        ImageIcon sandPathSide = new ImageIcon("resources/tiles/SPRITESHEET/floor/sand1.png");
        tile[2] = new Tile();
        tile[2].image = sandPathSide.getImage();
//
        ImageIcon portal = new ImageIcon("resources/tiles/SPRITESHEET/floor/portal.png");
        tile[6] = new Tile();
        tile[6].image = portal.getImage();
        tile[6].collision = true;
        tile[6].isAPortal = true;
        tile[6].tileNumber = 1;
//
//        ImageIcon eight = new ImageIcon("resources/tiles/SPRITESHEET/pondisde4.png");
//        tile[4] = new Tile();
//        tile[4].image = eight.getImage();
//
//        ImageIcon nine = new ImageIcon("resources/tiles/SPRITESHEET/pondisde4.png");
//        tile[5] = new Tile();
//        tile[5].image = nine.getImage();
    }


    public void loadMap(String filePath) {
        try {
            InputStream is = new FileInputStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < gp.maxColumns && row < gp.maxRows) {
                String line = br.readLine();

                while (col < gp.maxColumns) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxColumns) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        //  g2.drawImage(tile[0].image, 0,0,gp.tileSize, gp.tileSize, null);
        //  g2.drawImage(tile[1].image, 48,0,gp.tileSize, gp.tileSize, null);
        //  g2.drawImage(tile[2].image, 96,0,gp.tileSize, gp.tileSize, null);

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxColumns && row < gp.maxRows) {
            int titleNum = mapTileNum[col][row];

            g2.drawImage(tile[titleNum].image, x, y, gp.scaledTileSize, gp.scaledTileSize, null);
            col++;
            x += gp.scaledTileSize;

            if (col == gp.maxColumns) {
                col = 0;
                x = 0;
                row++;
                y += gp.scaledTileSize;
            }
        }
    }
}