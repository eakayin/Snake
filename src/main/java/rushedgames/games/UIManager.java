package rushedgames.games;

import java.awt.*;

public class UIManager {

    GameScene scene;
    String bodySize;
    String points;
    Font font;

    public UIManager(GameScene scene){
        
        this.scene = scene;
        font = new Font("arial", Font.BOLD, 31);
        points = "Points: 0";
        bodySize = "Bodysize: 0";

    }

    public void updateUI(){

        bodySize = "Bodysize: " + String.valueOf(scene.player.body.body.size());
        points = "Points: " + String.valueOf(scene.player.body.body.size() * 500 - 1000);


    }

    public void paintUI(Graphics g){
    
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString(points, scene.actualTileSize / 2, scene.actualTileSize);
        g.drawString(bodySize, scene.actualTileSize / 2, scene.actualTileSize * 2);

    }
    
}
