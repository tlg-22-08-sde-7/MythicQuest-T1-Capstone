package com.mythicquest.app;

import com.mythicquest.object.Key;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        // Coordinates on world map
        gp.obj[0] = new Key();
        gp.obj[0].worldX = 24 * gp.getScaledTileSize();
        gp.obj[0].worldY = 20 * gp.getScaledTileSize();
    }
}