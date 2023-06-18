package main;

import object.obj_chest;
import object.obj_door;
import object.obj_key;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    public void setObject() {
        gp.obj[0] = new obj_key();
        gp.obj[0].worldX = 30*gp.tileSize;
        gp.obj[0].worldY = 41*gp.tileSize;

        gp.obj[2] = new obj_door();
        gp.obj[2].worldX = 40*gp.tileSize;
        gp.obj[2].worldY = 24*gp.tileSize;

        gp.obj[3] = new obj_chest();
        gp.obj[2].worldX = 42*gp.tileSize;
        gp.obj[2].worldY = 23*gp.tileSize;
    }
}
