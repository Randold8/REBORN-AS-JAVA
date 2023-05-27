package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    //НАСТРОЙКИ ЭКРАНА
    final int originalTileSize = 16; //16 на 16 тайлы
    final int scale = 3;

    final int TileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }
}
