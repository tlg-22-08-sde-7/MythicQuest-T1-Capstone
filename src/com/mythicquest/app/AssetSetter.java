package com.mythicquest.app;

//import com.mythicquest.entity.Minion;      // TODO uncomment this line when troubleshooting
// Minion class
import com.mythicquest.object.Chest;
import com.mythicquest.object.Door;
import com.mythicquest.object.Key;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        // Coordinates on world map
        gp.obj[0] = new Key();
        gp.obj[0].worldX = 23 * gp.getScaledTileSize();
        gp.obj[0].worldY = 7 * gp.getScaledTileSize();

//        gp.obj[1] = new Door();
//        gp.obj[1].worldX = 10 * gp.getScaledTileSize();
//        gp.obj[1].worldY = 11 * gp.getScaledTileSize();

        gp.obj[2] = new Chest();
        gp.obj[2].worldX = 10 * gp.getScaledTileSize();
        gp.obj[2].worldY = 8 * gp.getScaledTileSize();

        gp.obj[3] = new Door();
        gp.obj[3].worldX = 39 * gp.getScaledTileSize();
        gp.obj[3].worldY = 27 * gp.getScaledTileSize();

        gp.obj[4] = new com.mythicquest.object.SuperObject();
        gp.obj[4].worldX = 11 * gp.getScaledTileSize();
        gp.obj[4].worldY = 30 * gp.getScaledTileSize();

    }
}