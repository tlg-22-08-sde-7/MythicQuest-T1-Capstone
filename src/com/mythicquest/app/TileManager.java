package com.mythicquest.app;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxColumns][gp.maxRows];

        getTileImage();
        loadMapOne("resources/tiles/land01.txt");
    }



    private void getTileImage() {

        String grassImagePath = "resources/tiles/grass.png";
        ImageIcon grassImage = new ImageIcon(grassImagePath);
        tile[0] = new Tile();
        tile[0].image = grassImage.getImage();

        String wallImagePath1 = "resources/tiles/wall1.png";
        ImageIcon wall1Image = new ImageIcon(wallImagePath1);
        tile[1] = new Tile();
        tile[1].image = wall1Image.getImage();

        String plantsImagePath1 = "resources/tiles/plants1.png";
        ImageIcon plants1Image = new ImageIcon(plantsImagePath1);
        tile[2] = new Tile();
        tile[2].image = plants1Image.getImage();



    }

    public void loadMapOne(String filePath){
        try {
            InputStream is = new FileInputStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < gp.maxColumns && row < gp.maxRows){
                String line = br.readLine();

                while (col < gp.maxColumns){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxColumns){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }

        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        g2.drawImage(tile[0].image, 0,0,gp.tileSize, gp.tileSize, null);
        //  g2.drawImage(tile[1].image, 48,0,gp.tileSize, gp.tileSize, null);
        //  g2.drawImage(tile[2].image, 96,0,gp.tileSize, gp.tileSize, null);

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxColumns && row < gp.maxRows){
            int titleNum = mapTileNum[col][row];

            g2.drawImage(tile[titleNum].image, x, y,gp.scaledTileSize,gp.scaledTileSize, null);
            col++;
            x+=gp.scaledTileSize;

            if (col == gp.maxColumns){
                col = 0;
                x = 0;
                row++;
                y+=gp.scaledTileSize;
            }
        }
    }
}
