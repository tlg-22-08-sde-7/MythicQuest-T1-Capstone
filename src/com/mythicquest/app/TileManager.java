package com.mythicquest.app;

import com.mythicquest.object.Key;
import com.mythicquest.object.SuperObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[20];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/tiles/maps/world01.txt");
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/SPRITESHEET/grass01.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall5.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/SPRITESHEET/water.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road00.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
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
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol * gp.getScaledTileSize(); // Position on the map
            int worldY = worldRow * gp.getScaledTileSize();
            int screenX = worldX - gp.player.getWorldX() + gp.player.screenX; // Where on the screen we draw it
            int screenY = worldY - gp.player.getWorldY() + gp.player.screenY;

            // Improves game performance
            if (worldX + gp.getScaledTileSize() > gp.player.getWorldX() - gp.player.screenX &&
                    worldX - gp.getScaledTileSize() < gp.player.getWorldX() + gp.player.screenX &&
                    worldY + gp.getScaledTileSize() > gp.player.getWorldY()  - gp.player.screenY &&
                    worldY - gp.getScaledTileSize() < gp.player.getWorldY()  + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.getScaledTileSize(), gp.getScaledTileSize(), null);
            }
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

//    public ArrayList<String> getMapsPath() {
//        ArrayList<String> maps = new ArrayList<>();
//        maps.add("resources/tiles/maps/map0.txt");
//        maps.add("resources/tiles/maps/map1.txt");
//        maps.add("resources/tiles/maps/map2.txt");
//        maps.add("resources/tiles/maps/map3.txt");
//        maps.add("resources/tiles/maps/map4.txt");
//        maps.add("resources/tiles/maps/map5.txt");
//        maps.add("resources/tiles/maps/map6.txt");
//        maps.add("resources/tiles/maps/map7.txt");
//        return maps;
//    }


    //function can take in a counter
    //depending on the counter a new map gets called
//    public void loadNewMap(String mapNumber) {
//        int n = Integer.parseInt(mapNumber);
//        System.out.println("# load new: " + mapNumber);
//        System.out.println(getMapsPath().get(n).toString());
//        loadMap(getMapsPath().get(n).toString());
//        getTileImage(n);
//    }

//    private void getTileImage(int num) {
//        switch (num) {
//            case 0:
//                renderTileImagesMap0();
//                break;
//            case 1:
//                renderTileImagesMap1();
//                break;
//            case 2:
//                renderTileImagesMap2();
//                break;
//            case 3:
//                renderTileImagesMap3();
//                break;
//            case 4:
//                renderTileImagesMap4();
//            break;
//            case 5:
//                renderTileImagesMap5();
//                break;
//            case 6:
//                renderTileImagesMap6();
//            break;
//            case 7:
//                renderTileImagesMap7();
//            break;
//            case 8:
//                renderTileImagesMap8();
//            break;
//            default:
//                System.out.println("nah brah");
//               // renderTileImagesMap1()
//                ;
//                break;
//        }
//    }


//    private void renderTileImagesMap0() {
//
//        ImageIcon bones = new ImageIcon("resources/tiles/1bones.png");
//        tile[3] = new Tile();
//        tile[3].image = bones.getImage();
//
//        ImageIcon nine = new ImageIcon("resources/tiles/3bones.png");
//        tile[5] = new Tile();
//        tile[5].image = nine.getImage();
//        ImageIcon nine1 = new ImageIcon("resources/tiles/7bones.png");
//        tile[7] = new Tile();
//        tile[7].image = nine1.getImage();
//        ImageIcon grass1 = new ImageIcon("resources/tiles/grasslg.png");
//        tile[0] = new Tile();
//        tile[0].image = grass1.getImage();
//        ImageIcon sign1 = new ImageIcon("resources/tiles/signL.png");
//        tile[9] = new Tile();
//        tile[9].collision = true;
//        tile[9].image = sign1.getImage();
//        ImageIcon sign2 = new ImageIcon("resources/tiles/signR.png");
//        tile[10] = new Tile();
//        tile[10].image = sign2.getImage();
//        tile[10].collision = true;
//        ImageIcon sign3 = new ImageIcon("resources/tiles/signBR.png");
//        tile[11] = new Tile();
//        tile[11].image = sign3.getImage();
//        tile[11].collision = true;
//        ImageIcon sign4 = new ImageIcon("resources/tiles/signR2.png");
//        tile[12] = new Tile();
//        tile[12].image = sign4.getImage();
//        tile[12].collision = true;
//
//        ImageIcon wall = new ImageIcon("resources/tiles/wall5.png");
//        tile[1] = new Tile();
//        tile[1].image = wall.getImage();
//        tile[1].collision = true;
//
//        ImageIcon sandPathSide = new ImageIcon("resources/tiles/SPRITESHEET/floor/sand1.png");
//        tile[2] = new Tile();
//        tile[2].image = sandPathSide.getImage();

        //this tile is the portal it a boolean isAPortal and a tileNumber which is the map # that is called
//        ImageIcon portal = new ImageIcon("resources/tiles/SPRITESHEET/floor/portal.png");
//        tile[6] = new Tile();
//        tile[6].image = portal.getImage();
//        tile[6].collision = true;
//        tile[6].isAPortal = true;
//        tile[6].tileNumber = "1";
//
//
//        ImageIcon eight = new ImageIcon("resources/tiles/SPRITESHEET/bones2.png");
//        tile[4] = new Tile();
//        tile[4].image = eight.getImage();
//        ImageIcon eight1 = new ImageIcon("resources/tiles/SPRITESHEET/floor/signonfloor.png");
//        tile[8] = new Tile();
//        tile[8].image = eight1.getImage();
//        tile[8].collision = true;
//    }

//    public void renderTileImagesMap1() {
//        ImageIcon tree = new ImageIcon("resources/tiles/crowtop.png");
//        tile[3] = new Tile();
//        tile[3].image = tree.getImage();
//        tile[3].collision = true;
//        ImageIcon tree1 = new ImageIcon("resources/tiles/crowtopright.png");
//        tile[5] = new Tile();
//        tile[5].image = tree1.getImage();
//        tile[5].collision = true;
//        ImageIcon tree2 = new ImageIcon("resources/tiles/crowb.png");
//        tile[4] = new Tile();
//        tile[4].image = tree2.getImage();
//        tile[4].collision = true;
//
//        ImageIcon grass1 = new ImageIcon("resources/tiles/SPRITESHEET/grass01.png");
//        tile[0] = new Tile();
//        tile[0].image = grass1.getImage();
//
//        ImageIcon sandPath = new ImageIcon("resources/tiles/s1.png");
//        tile[1] = new Tile();
//        tile[1].image = sandPath.getImage();
//
//        ImageIcon sandPathSide = new ImageIcon("resources/tiles/flowers.png");
//        tile[2] = new Tile();
//        tile[2].image = sandPathSide.getImage();
//        ImageIcon sandPathSide1 = new ImageIcon("resources/tiles/SPRITESHEET/floor/rocks.png");
//        tile[8] = new Tile();
//        tile[8].image = sandPathSide1.getImage();
//        ImageIcon sandPathSide2 = new ImageIcon("resources/tiles/s4.png");
//        tile[9] = new Tile();
//        tile[9].image = sandPathSide2.getImage();
//        ImageIcon sandPathSide3 = new ImageIcon("resources/tiles/smiddle.png");
//        tile[10] = new Tile();
//        tile[10].image = sandPathSide3.getImage();
//        ImageIcon sandPathSide4 = new ImageIcon("resources/tiles/sbottom.png");
//        tile[11] = new Tile();
//        tile[11].image = sandPathSide4.getImage();


//    ImageIcon sandPathSide1 = new ImageIcon("resources/tiles/dirtLD.png");
//        tile[8] = new Tile();
//        tile[8].image = sandPathSide1.getImage();
        //returns player to previous map
//        ImageIcon returnPortal = new ImageIcon("resources/tiles/SPRITESHEET/floor/portal.png");
//        tile[7] = new Tile();
//        tile[7].image = returnPortal.getImage();
//        tile[7].collision = true;
//        tile[7].isAPortal = true;
//        tile[7].tileNumber = "0";
//
//        ImageIcon portal = new ImageIcon("resources/tiles/SPRITESHEET/floor/portal.png");
//        tile[6] = new Tile();
//        tile[6].image = portal.getImage();
//        tile[6].collision = true;
//        tile[6].isAPortal = true;
//        tile[6].tileNumber = "2";

//
//        ImageIcon eight = new ImageIcon("resources/tiles/SPRITESHEET/bones2.png");
//        tile[4] = new Tile();
//        tile[4].image = eight.getImage();
//
//
//        ImageIcon nine = new ImageIcon("resources/tiles/3bones.png");
//        tile[5] = new Tile();
//        tile[5].image = nine.getImage();
//
//        ImageIcon eight = new ImageIcon("resources/tiles/SPRITESHEET/pondisde4.png");
//        tile[4] = new Tile();
//        tile[4].image = eight.getImage();
//
//        ImageIcon nine = new ImageIcon("resources/tiles/SPRITESHEET/pondisde4.png");
//        tile[5] = new Tile();
//        tile[5].image = nine.getImage();
//    }
//
//    public void renderTileImagesMap2(){
//        ImageIcon grass1 = new ImageIcon("resources/tiles/grasslg.png");
//        tile[0] = new Tile();
//        tile[0].image = grass1.getImage();
//
//        ImageIcon tree = new ImageIcon("resources/tiles/SPRITESHEET/floor/tree1.png");
//        tile[1] = new Tile();
//        tile[1].image = tree.getImage();
//        tile[1].collision = true;
//
//        ImageIcon portal = new ImageIcon("resources/tiles/SPRITESHEET/floor/portal.png");
//        tile[6] = new Tile();
//        tile[6].image = portal.getImage();
//        tile[6].collision = true;
//        tile[6].isAPortal = true;
//        tile[6].tileNumber = "3";
//
//        ImageIcon returnPortal = new ImageIcon("resources/tiles/SPRITESHEET/floor/portal.png");
//        tile[7] = new Tile();
//        tile[7].image = returnPortal.getImage();
//        tile[7].collision = true;
//        tile[7].isAPortal = true;
//        tile[7].tileNumber = "1";
//
//        ImageIcon wall = new ImageIcon("resources/tiles/purpleWall.png");
//        tile[3] = new Tile();
//        tile[3].image = wall.getImage();
//
//    }

//    private void renderTileImagesMap3() {
//        ImageIcon grass1 = new ImageIcon("resources/tiles/grasslg.png");
//        tile[0] = new Tile();
//        tile[0].image = grass1.getImage();
//
//        ImageIcon tentLeft = new ImageIcon("resources/tiles/tenttopleft.png");
//        tile[2] = new Tile();
//        tile[2].image = tentLeft.getImage();
//      //  tile[2].collision = true;
//        ImageIcon tentLeft2 = new ImageIcon("resources/tiles/tentbleft.png");
//        tile[3] = new Tile();
//        tile[3].image = tentLeft2.getImage();
//        tile[3].collision = true;
//        ImageIcon tentRight = new ImageIcon("resources/tiles/tenttopright.png");
//        tile[4] = new Tile();
//        tile[4].image = tentRight.getImage();
//        //tile[4].collision = true;
//        ImageIcon tentRight2 = new ImageIcon("resources/tiles/tentbright.png");
//        tile[5] = new Tile();
//        tile[5].image = tentRight2.getImage();
//        tile[5].collision = true;
//
//        ImageIcon postTop = new ImageIcon("resources/tiles/postleft.png");
//        tile[8] = new Tile();
//        tile[8].image = postTop.getImage();
//        //tile[8].collision = true;
//        ImageIcon postBottom = new ImageIcon("resources/tiles/postB.png");
//        tile[9] = new Tile();
//        tile[9].image = postBottom.getImage();
//        //tile[9].collision = true;
//
//
//        ImageIcon portal = new ImageIcon("resources/tiles/SPRITESHEET/floor/portal.png");
//        tile[6] = new Tile();
//        tile[6].image = portal.getImage();
//        tile[6].collision = true;
//        tile[6].isAPortal = true;
//        tile[6].tileNumber = "4";
//
//        ImageIcon returnPortal = new ImageIcon("resources/tiles/SPRITESHEET/floor/portal.png");
//        tile[7] = new Tile();
//        tile[7].image = returnPortal.getImage();
//        tile[7].collision = true;
//        tile[7].isAPortal = true;
//        tile[7].tileNumber = "2";
//    }
//
//    private void renderTileImagesMap4() {
//        ImageIcon grass1 = new ImageIcon("resources/tiles/grasslg.png");
//        tile[0] = new Tile();
//        tile[0].image = grass1.getImage();
//
//        ImageIcon rocks = new ImageIcon("resources/tiles/SPRITESHEET/floor/rocks.png");
//        tile[1] = new Tile();
//        tile[1].image = rocks.getImage();
//
//        ImageIcon portal = new ImageIcon("resources/tiles/SPRITESHEET/floor/portal.png");
//        tile[6] = new Tile();
//        tile[6].image = portal.getImage();
//        tile[6].collision = true;
//        tile[6].isAPortal = true;
//        tile[6].tileNumber = "5";
//
//        ImageIcon returnPortal = new ImageIcon("resources/tiles/SPRITESHEET/floor/portal.png");
//        tile[7] = new Tile();
//        tile[7].image = returnPortal.getImage();
//        tile[7].collision = true;
//        tile[7].isAPortal = true;
//        tile[7].tileNumber = "3";
//    }
//
//    private void renderTileImagesMap5() {
//        ImageIcon grass1 = new ImageIcon("resources/tiles/grasslg.png");
//        tile[0] = new Tile();
//        tile[0].image = grass1.getImage();
//
//
//        ImageIcon portal = new ImageIcon("resources/tiles/SPRITESHEET/floor/portal.png");
//        tile[6] = new Tile();
//        tile[6].image = portal.getImage();
//        tile[6].collision = true;
//        tile[6].isAPortal = true;
//        tile[6].tileNumber = "6";
//
//        ImageIcon returnPortal = new ImageIcon("resources/tiles/SPRITESHEET/floor/portal.png");
//        tile[7] = new Tile();
//        tile[7].image = returnPortal.getImage();
//        tile[7].collision = true;
//        tile[7].isAPortal = true;
//        tile[7].tileNumber = "4";
//    }
//    private void renderTileImagesMap6() {
//        ImageIcon grass1 = new ImageIcon("resources/tiles/grasslg.png");
//        tile[0] = new Tile();
//        tile[0].image = grass1.getImage();
//
//        ImageIcon building1 = new ImageIcon("resources/tiles/b1.png");
//        tile[1] = new Tile();
//        tile[1].image = building1.getImage();
//        tile[1].collision = true;
//        ImageIcon building2 = new ImageIcon("resources/tiles/b2.png");
//        tile[2] = new Tile();
//        tile[2].image = building2.getImage();
//        tile[2].collision = true;
//
//
//
//        ImageIcon building3 = new ImageIcon("resources/tiles/b3.png");
//        tile[3] = new Tile();
//        tile[3].image = building3.getImage();
//        tile[3].collision = true;
//        ImageIcon building4 = new ImageIcon("resources/tiles/b4.png");
//        tile[4] = new Tile();
//        tile[4].image = building4.getImage();
//        tile[4].collision = true;
//        ImageIcon building5 = new ImageIcon("resources/tiles/b5.png");
//        tile[5] = new Tile();
//        tile[5].image = building5.getImage();
//        tile[5].collision = true;
//        ImageIcon building6 = new ImageIcon("resources/tiles/b8.png");
//        tile[8] = new Tile();
//        tile[8].image = building6.getImage();
//        tile[8].collision = true;
//        ImageIcon building7 = new ImageIcon("resources/tiles/b7.png");
//        tile[9] = new Tile();
//        tile[9].image = building7.getImage();
//        tile[9].collision = true;
//        ImageIcon building8 = new ImageIcon("resources/tiles/b88.png");
//        tile[10] = new Tile();
//        tile[10].image = building8.getImage();
//        tile[10].collision = true;
//        ImageIcon building9 = new ImageIcon("resources/tiles/b9.png");
//        tile[11] = new Tile();
//        tile[11].image = building9.getImage();
//        tile[11].collision = true;
//        ImageIcon building10 = new ImageIcon("resources/tiles/bBottom.png");
//        tile[12] = new Tile();
//        tile[12].image = building10.getImage();
//        tile[12].collision = true;
//        ImageIcon portal = new ImageIcon("resources/tiles/bdoor.png");
//        tile[13] = new Tile();
//        tile[13].image = portal.getImage();
//        tile[13].collision = true;
//        tile[13].isAPortal = true;
//        tile[13].tileNumber = "7";
//        ImageIcon statute1 = new ImageIcon("resources/tiles/stoneTop.png");
//        tile[14] = new Tile();
//        tile[14].image = statute1.getImage();
//        tile[14].collision = true;
//        ImageIcon statute2 = new ImageIcon("resources/tiles/stoneBottom.png");
//        tile[15] = new Tile();
//        tile[15].image = statute2.getImage();
//        tile[15].collision = true;

//        ImageIcon portal = new ImageIcon("resources/tiles/SPRITESHEET/floor/portal.png");
//        tile[6] = new Tile();
//        tile[6].image = portal.getImage();
//        tile[6].collision = true;
//        tile[6].isAPortal = true;
//        tile[6].tileNumber = "7";

//        ImageIcon returnPortal = new ImageIcon("resources/tiles/SPRITESHEET/floor/portal.png");
//        tile[7] = new Tile();
//        tile[7].image = returnPortal.getImage();
//        tile[7].collision = true;
//        tile[7].isAPortal = true;
//        tile[7].tileNumber = "5";
//    }
//    private void renderTileImagesMap7() {
//        ImageIcon grass1 = new ImageIcon("resources/tiles/grasslg.png");
//        tile[0] = new Tile();
//        tile[0].image = grass1.getImage();
//
//
//        ImageIcon portal = new ImageIcon("resources/tiles/SPRITESHEET/floor/portal.png");
//        tile[6] = new Tile();
//        tile[6].image = portal.getImage();
//        tile[6].collision = true;
//        tile[6].isAPortal = true;
//        tile[6].tileNumber = "7";
//
//        ImageIcon returnPortal = new ImageIcon("resources/tiles/SPRITESHEET/floor/portal.png");
//        tile[7] = new Tile();
//        tile[7].image = returnPortal.getImage();
//        tile[7].collision = true;
//        tile[7].isAPortal = true;
//        tile[7].tileNumber = "6";
//    }
//    private void renderTileImagesMap8() {
//        ImageIcon grass1 = new ImageIcon("resources/tiles/grasslg.png");
//        tile[0] = new Tile();
//        tile[0].image = grass1.getImage();


//        ImageIcon portal = new ImageIcon("resources/tiles/SPRITESHEET/floor/portal.png");
//        tile[6] = new Tile();
//        tile[6].image = portal.getImage();
//        tile[6].collision = true;
//        tile[6].isAPortal = true;
//        tile[6].tileNumber = "8";

//        ImageIcon returnPortal = new ImageIcon("resources/tiles/SPRITESHEET/floor/portal.png");
//        tile[7] = new Tile();
//        tile[7].image = returnPortal.getImage();
//        tile[7].collision = true;
//        tile[7].isAPortal = true;
//        tile[7].tileNumber = "7";
//    }
}