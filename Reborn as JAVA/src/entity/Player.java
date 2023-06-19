package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    double speedBuildup = 0;
    double speedLoss = 1;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 12;
        solidArea.y = 20;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 28;
        solidArea.height = 28;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {
        worldX = gp.tileSize*41;
        worldY = gp.tileSize*18;
        maxSpeed = 4;
        speed = 0;
        direction = "down";
    }
    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }

    }
    public void update() {
        if(keyH.upPressed == 1||keyH.downPressed == 1||keyH.rightPressed == 1||keyH.leftPressed == 1) {
            if (keyH.upPressed == 1) {
                direction = "up";
            } else if (keyH.downPressed == 1) {
                direction = "down";
            } else if (keyH.leftPressed == 1) {
                direction = "left";
            } else if (keyH.rightPressed == 1) {
                direction = "right";
            }

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
            if (speed < maxSpeed) {
                speedBuildup += 0.1;
                speed += Math.floor(speedBuildup);
                if (speedBuildup >= 1) {
                    speedBuildup = 0;
                }
                speedLoss = 1;
            }

        } else {
            if (speed > 0) {
                speedLoss += (float)1/speed;
                speed -= Math.floor(speedLoss);
                if (speedLoss > 1) {
                    speedLoss = 0;
                }
                if (speed < 0) {
                    speed = 0;
                }
                speedBuildup = 1;
            }
        }

        collisionOn = false;
        gp.cChecker.checkTile(this);
        int objIndex = gp.cChecker.checkObject(this,true);
        pickUpObject(objIndex);

        if (!collisionOn) {
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "right" -> worldX += speed;
                case "left" -> worldX -= speed;
            }
        }

    }
    public void pickUpObject(int i) {
        if(i != -1) {
            String objectName = gp.obj[i].name;
            switch(objectName) {
                case "key":
                    gp.playSE(1,0f);
                    hasKey ++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Got a key!");
                    break;
                case "door":
                    if(hasKey > 0) {
                        gp.playSE(2,0f);
                        gp.obj[i] = null;
                        hasKey --;
                    } else {
                        gp.ui.showMessage("You need a key!");
                    }
                    break;
                case "chest":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.obj[i] = null;
                    gp.playMusic(4,0f);
                    break;
                case "boots":
                    gp.ui.showMessage("Max speed increased!");
                    gp.playSE(5,0f);
                    gp.obj[i] = null;
                    maxSpeed += 2;
            }
        }
    }
    public void draw( Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
            }
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }
}
