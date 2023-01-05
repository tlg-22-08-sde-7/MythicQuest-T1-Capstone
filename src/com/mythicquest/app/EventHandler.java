package com.mythicquest.app;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent() {
        if (hit(11, 6, "right")) {
            damage();
        }
    }

    public boolean hit(int eventCol, int eventRow, String direction) {
        boolean hit = false;

        gp.player.solidArea.x = gp.player.getWorldX() + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.getWorldY() + gp.player.solidArea.y;
        eventRect.x = eventCol * gp.getScaledTileSize() + eventRect.x;
        eventRect.y = eventRow * gp.getScaledTileSize() + eventRect.y;

        if (gp.player.solidArea.intersects(eventRect)) {
            if (gp.player.getDirection().contentEquals(direction) || direction.contentEquals("any")) {
                hit = true;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;
    }

    public void damage() {
        gp.player.setLife(gp.player.getLife() - 1);
    }
}