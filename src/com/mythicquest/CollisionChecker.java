package com.mythicquest;

import com.mythicquest.app.GamePanel;
import com.mythicquest.app.TileManager;
import com.mythicquest.engine.Player;
import com.mythicquest.engine.PlayerA;
import com.mythicquest.engine.Sprite;

import java.awt.*;

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
                if (gp.tm.tile[tileNum1].isAPortal ||gp.tm.tile[tileNum2].isAPortal ){
                    for (int i = 0; i < gp.tm.tile.length; i++){
                        if (gp.tm.tile[i] != null && gp.tm.tile[i].tileNumber == 2){
                            gp.tm.loadNewMap(1);

                        }

                    }
                    break;
                }
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


}
