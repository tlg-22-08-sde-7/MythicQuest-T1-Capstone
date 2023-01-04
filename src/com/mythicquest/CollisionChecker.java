package com.mythicquest;

import com.mythicquest.app.GamePanel;
import com.mythicquest.entity.PlayerA;
import com.mythicquest.entity.Sprite;

public class CollisionChecker {
    GamePanel gp;


    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }
    public void checkTile(PlayerA player){
        int leftX = player.getWorldX() + player.solidArea.x;
        int rightX = player.getWorldX() + player.solidArea.x + player.solidArea.width;
        int topY = player.getWorldY() + player.solidArea.y;
        int bottomY = player.getWorldY() + player.solidArea.y + player.solidArea.height;

        int pLeftCol = leftX / gp.getScaledTileSize();
        int pRightCol = rightX/gp.getScaledTileSize();
        int pTopRow = topY / gp.getScaledTileSize();
        int pBottomRow = bottomY/gp.getScaledTileSize();

        int tileNum1, tileNum2;

        switch (player.getDirection()){

            case "up":
                pTopRow = (topY - player.getSpeed()) /gp.getScaledTileSize();
                tileNum1 = gp.tm.mapTileNum[pLeftCol][pTopRow];
                tileNum2 = gp.tm.mapTileNum[pRightCol][pTopRow];
                if (gp.tm.tile[tileNum1].collision || gp.tm.tile[tileNum2].collision){
                    player.collisionOn = true;
                }
                //this checks if the tile is a portal then the tileNumber is used to get new map
//                String tileNumber = null;
//                if (gp.tm.tile[tileNum1].isAPortal ||gp.tm.tile[tileNum2].isAPortal ){
//                    for (int i = 0; i < gp.tm.tile.length; i++){
//                        if (gp.tm.tile[i] != null && gp.tm.tile[i].tileNumber != null ){
//                           // System.out.println("inside: " + gp.tm.tile[tileNum2].tileNumber);
//                            //gp.tm.loadNewMap(Integer.parseInt(gp.tm.tile[i].tileNumber));
//                            //System.out.println(Integer.parseInt(gp.tm.tile[i].tileNumber));
//                             tileNumber = gp.tm.tile[i].tileNumber;
//                           // System.out.println("i inside: " + gp.tm.tile[i].tileNumber);
//                            player.setWorldX(8);
//                            player.setWorldY(8);
//                        }
//                    }
//                    //System.out.println("tileNumer ");
//                    String t2 = "";
//                   // System.out.println("tileNum1: " + tileNum1);
//                   // System.out.println("tileNum2: " + tileNum2);
//                    if (tileNum1 != 0){
//                         t2 = gp.tm.tile[tileNum1].tileNumber;
//                    } else if (tileNum2 != 0){
//                         t2 = gp.tm.tile[tileNum2].tileNumber;
//                    } else {
//                        System.out.println("try again");
//                    }
//                   // int t2 = Integer.parseInt(gp.tm.tile[tileNum2].tileNumber);
//                   // System.out.println("outside: " + tileNumber);
////                    gp.tm.loadNewMap(t2);
//                }
                break;
            case "down":
                pBottomRow = (bottomY - player.getSpeed()) /gp.getScaledTileSize();
                tileNum1 = gp.tm.mapTileNum[pLeftCol][pBottomRow];
                tileNum2 = gp.tm.mapTileNum[pRightCol][pBottomRow];
                if (gp.tm.tile[tileNum1].collision || gp.tm.tile[tileNum2].collision){
                    player.collisionOn = true;
                }
                break;
            case "left":
                pLeftCol = (leftX - player.getSpeed()) /gp.getScaledTileSize();
                tileNum1 = gp.tm.mapTileNum[pLeftCol][pTopRow];
                tileNum2 = gp.tm.mapTileNum[pLeftCol][pBottomRow];
                if (gp.tm.tile[tileNum1].collision || gp.tm.tile[tileNum2].collision){
                    player.collisionOn = true;
                }
                break;
            case "right":
                pRightCol = (rightX + player.getSpeed()) /gp.getScaledTileSize();
                tileNum1 = gp.tm.mapTileNum[pRightCol][pTopRow];
                tileNum2 = gp.tm.mapTileNum[pRightCol][pBottomRow];
                if (gp.tm.tile[tileNum1].collision || gp.tm.tile[tileNum2].collision){
                    player.collisionOn = true;
                }
                break;
        }
    }

    public int checkObject(Sprite sprite, boolean player) {
        int index = 999;
        for (int i=0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null){
                // Get sprite's solid area position
                sprite.solidArea.x = sprite.getWorldX() + sprite.solidArea.x;
                sprite.solidArea.y = sprite.getWorldY() + sprite.solidArea.y;

                // Get object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (sprite.getDirection()) {
                    case "up":
                        sprite.solidArea.y -= sprite.getSpeed();
                        if (sprite.solidArea.intersects(gp.obj[i].solidArea)) {
                            sprite.collisionOn = true;
                        }
                        if (player == true) {
                            index = i;
                        }
                        break;
                    case "down":
                        sprite.solidArea.y += sprite.getSpeed();
                        if (sprite.solidArea.intersects(gp.obj[i].solidArea)) {
                            sprite.collisionOn = true;
                        }
                        if (player == true) {
                            index = i;
                        }
                        break;
                    case "left":
                        sprite.solidArea.x -= sprite.getSpeed();
                        if (sprite.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision == true) {
                                sprite.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        sprite.solidArea.x += sprite.getSpeed();
                        if (sprite.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision == true) {
                                sprite.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                }
                sprite.solidArea.x = sprite.solidAreaDefaultX;
                sprite.solidArea.y = sprite.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }
        return index;
    }

}
