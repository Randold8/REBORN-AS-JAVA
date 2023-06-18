package main;

import object.obj_key;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UI {
    GamePanel gp;
    Font earthM1,earthM2;
    BufferedImage keyImage;
    public UI(GamePanel gp) throws IOException, FontFormatException {
        this.gp = gp;
        obj_key key = new obj_key();
        keyImage = key.image;
        try {
            earthM1 = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("fonts/EarthMommaRegular-ZGrK.ttf"));
            earthM2 = earthM1.deriveFont(22f);
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
    g2.setFont(earthM2);
    g2.setColor(Color.white);
    g2.drawImage(keyImage,gp.tileSize/2,gp.tileSize/2,gp.tileSize,gp.tileSize,null);
    g2.drawString("x = "+ gp.player.hasKey, 68, 65);
    }
}
