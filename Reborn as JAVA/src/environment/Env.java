package environment;

import main.GamePanel;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Env {
    GamePanel gp;
    BufferedImage darkFilter;
    public Env(GamePanel gp) {
        this.gp = gp;
        darkFilter = new BufferedImage(gp.screenWidth,gp.screenHeight,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D)darkFilter.getGraphics();
        Area screenArea = new Area(new Rectangle2D.Double(0,0,gp.screenWidth,gp.screenHeight));
        g2.setColor(new Color(0,0,0,0.7f));
        g2.fill(screenArea);
        g2.dispose();
    }
    public void draw(Graphics2D g2) {
        g2.drawImage(darkFilter,0,0,null);
    }
}
