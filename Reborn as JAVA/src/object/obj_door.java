package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class obj_door extends SuperObject{
    public obj_door() {
        name = "door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
