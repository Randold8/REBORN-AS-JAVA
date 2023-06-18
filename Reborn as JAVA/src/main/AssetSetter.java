package main;

import object.obj_door;
import object.obj_key;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    public void setObject() {
        gp.obj[0] = new obj_key();
        gp.obj[0].worldX = 6*gp.tileSize;
        gp.obj[0].worldY = 5*gp.tileSize;

        gp.obj[1] = new obj_key();
        gp.obj[1].worldX = 10*gp.tileSize;
        gp.obj[1].worldY = 5*gp.tileSize;

        gp.obj[2] = new obj_door();
        gp.obj[2].worldX = 15*gp.tileSize;
        gp.obj[2].worldY = 10*gp.tileSize;
    }
}
