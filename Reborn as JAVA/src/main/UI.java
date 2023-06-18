package main;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.io.File;
import java.io.IOException;

public class UI {
    GamePanel gp;
    Font earthM1,earthM2;
    public UI(GamePanel gp) throws IOException, FontFormatException {
        this.gp = gp;
        try {
            earthM1 = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("fonts/EarthMommaRegular-ZGrK.ttf"));
            earthM2 = earthM1.deriveFont(20f);
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
    g2.setFont(earthM2);
    g2.setColor(Color.white);
    g2.drawString("Key = "+ gp.player.hasKey, 50, 50);
    }
}
