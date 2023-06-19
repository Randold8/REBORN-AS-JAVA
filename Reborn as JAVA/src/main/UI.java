package main;

import environment.Env;
import object.obj_key;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Env env;
    Graphics2D g2;
    BufferedImage darkFilter;
    Font earthM1,earthM2,earthM3;
    public boolean gameFinished = false;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    public UI(GamePanel gp) throws IOException, FontFormatException {
        this.gp = gp;
        this.env = gp.env;
        obj_key key = new obj_key();
        keyImage = key.image;
        try {
            earthM1 = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("fonts/EarthMommaRegular-ZGrK.ttf"));
            earthM2 = earthM1.deriveFont(22f);
            earthM3 = earthM1.deriveFont(34f);
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
    }
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        if (gp.gameState == gp.playState) {
            if (gameFinished == true) {
                g2.setFont(earthM3);
                g2.setColor(Color.yellow);
                String text;
                int textLen;
                int x;
                int y;
                text = "You got the treasure!";
                textLen = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                x = gp.screenWidth / 2 - textLen / 2;
                y = gp.screenHeight / 2 - (gp.tileSize * 3);
                g2.drawString(text, x, y);

                text = "Your time is: " + dFormat.format(playTime) + "!";
                textLen = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                x = gp.screenWidth / 2 - textLen / 2;
                y = gp.screenHeight / 2 + (gp.tileSize * 4);
                g2.drawString(text, x, y);
            } else {
                g2.setFont(earthM2);
                g2.setColor(Color.white);
                g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
                g2.drawString("x = " + gp.player.hasKey, 68, 65);
                playTime += (double) 1 / 60;
                g2.drawString("Time:" + dFormat.format(playTime), gp.tileSize * 14, 65);

                if (messageOn == true) {
                    g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);

                    messageCounter++;

                    if (messageCounter > 120) {
                        messageCounter = 0;
                        messageOn = false;
                    }
                }
            }
        }
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }

    }
    public void drawPauseScreen() {
        env.draw(g2);
        g2.setFont(earthM3);
        g2.setColor(Color.red);
        String text = "PAUSED";
        int x;
        int y = gp.screenHeight/2;
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        x = gp.screenWidth/2 - length/2;
        g2.drawString(text,x,y);

    }
}
