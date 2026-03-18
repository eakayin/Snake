package rushedgames.games;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;

public class AppleManager {

    GameScene scene;
    Player player;

    Random randomX;
    Random randomY;
    int x;
    int y;
    boolean insideSnake;
    BufferedImage image;

    public AppleManager(GameScene scene, Player player){

        this.scene = scene;
        this.player = player;
        randomX = new Random();
        randomY = new Random();
        insideSnake = false;

        try{
        image = ImageIO.read(getClass().getResourceAsStream("/apple_sprites/apple.png"));
        }
        catch(IOException e){
            System.out.println("Loading Failed.");
        }

        startAppleGen();

    }

    public void startAppleGen(){

        do{
            insideSnake = false;
            x = randomX.nextInt(scene.maxColumn) * scene.actualTileSize;
            y = randomY.nextInt(scene.maxRow) * scene.actualTileSize; 

            for(int i = 0; i <= player.body.body.size()-1; i++){
                if(x == player.body.body.get(i).x && y == player.body.body.get(i).y || x == scene.player.x && y == scene.player.y){
                    insideSnake = true;
                    break;
                }
            }

        }
        while(x <= 0 && y <= 0 || insideSnake);

    }

    public void appleEaten(){

        do{
            insideSnake = false;
            x = randomX.nextInt(scene.maxColumn) * scene.actualTileSize;
            y = randomY.nextInt(scene.maxRow) * scene.actualTileSize; 

            for(int i = 0; i <= player.body.body.size()-1; i++){
                if(x == player.body.body.get(i).x && y == player.body.body.get(i).y || x == scene.player.x && y == scene.player.y){
                    insideSnake = true;
                    break;
                }
            }

        }
        while(x <= 0 && y <= 0 || insideSnake);

    }

    public void paintApple(Graphics g){

        g.drawImage(image, x, y, scene.actualTileSize, scene.actualTileSize, null);

    }
    
}
