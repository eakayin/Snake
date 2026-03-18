package rushedgames.games;

import java.awt.*;

public class Player extends Entity {
    
    GameScene scene;
    InputHandler input;
    ImageManager images;

    int moveConditionArr[];
    int moveConditionIndex;
    int moveCondition;

    String paintDirection;

    PlayerBody body;
    Entity previousPos;
    String previousDir;
    boolean eating;

    boolean collideBody;

    boolean gameFinished;

    public Player(GameScene scene, InputHandler input){

        this.scene = scene;
        this.input = input;
        images = new ImageManager();

        x = scene.actualTileSize * (scene.maxColumn / 2);
        y = scene.actualTileSize * (scene.maxRow / 2);
        direction = "start";

        moveConditionArr = new int[]{30,15,10,6};
        moveConditionIndex = 0;
        moveCondition = moveConditionArr[moveConditionIndex];

        paintDirection = "start";

        body = new PlayerBody(this);
        previousDir = "start";
        eating = true;

        collideBody = false;

        gameFinished = false;

    }

    public void playerUpdate(){

        if(input.upPressed || input.downPressed || input.leftPressed || input.rightPressed){
            
            if(input.upPressed){
                direction = "up";
            }
            else if(input.downPressed){
                direction = "down";
            }
            else if(input.leftPressed){
                direction = "left";
            }
            else if(input.rightPressed){
                direction = "right";
            }

        }

        if(scene.frameCount % moveCondition == 0){ 

            paintDirection = direction;

            switch (direction) {
                case "start":
                    break;
            
                case "up":
                    if(!previousDir.equals("down") && !previousDir.equals("start")){
                    previousPos = new Entity(x,y);
                    y -= scene.actualTileSize;
                    previousDir = "up";
                    body.bodyUpdate();
                    eating = false;
                    }
                    else{
                        previousPos = new Entity(x,y);
                        y += scene.actualTileSize;
                        previousDir = "down";
                        paintDirection = "down";
                        body.bodyUpdate();
                        eating = false;
                    }
                    break;

                case "down":
                    if(!previousDir.equals("up")){
                    previousPos = new Entity(x,y);
                    y += scene.actualTileSize;
                    previousDir = "down";
                    body.bodyUpdate();
                    eating = false;
                    }
                    else{
                        previousPos = new Entity(x,y);
                        y -= scene.actualTileSize;
                        previousDir = "up";
                        paintDirection = "up";
                        body.bodyUpdate();
                        eating = false;
                    }
                    break;

                case "left":
                    if(!previousDir.equals("right")){
                    previousPos = new Entity(x,y);
                    x -= scene.actualTileSize;
                    previousDir = "left";
                    body.bodyUpdate();
                    eating = false;
                    }
                    else{
                        previousPos = new Entity(x,y);
                        x += scene.actualTileSize;
                        previousDir = "right";
                        paintDirection = "right";
                        body.bodyUpdate();
                        eating = false;
                    }
                    break;

                case "right":
                    if(!previousDir.equals("left")){
                    previousPos = new Entity(x,y);
                    x += scene.actualTileSize;
                    previousDir = "right";
                    body.bodyUpdate();
                    eating = false;
                    }
                    else{
                        previousPos = new Entity(x,y);
                        x -= scene.actualTileSize;
                        previousDir = "left";
                        paintDirection = "left";
                        body.bodyUpdate();
                        eating = false;
                    }
                    break;
            }

            if(scene.apple.x == x && scene.apple.y == y){

                scene.apple.appleEaten();
                body.addBodyPart();
                if(moveConditionIndex < moveConditionArr.length - 1){
                    moveConditionIndex++;
                }
                moveCondition = moveConditionArr[moveConditionIndex];
                scene.frameCount = 0;

            }

            if(body.body.size() == 204){
                gameFinished = true;
            }

            checkCollision();

        }

    }

    public void playerPaint(Graphics g){ 

        switch (paintDirection) {
            case "start":
                g.drawImage(images.head[0], x, y, scene.actualTileSize, scene.actualTileSize, null);
                break;
        
            case "up":
                g.drawImage(images.head[2], x, y, scene.actualTileSize, scene.actualTileSize, null);
                break;

            case "down":
                g.drawImage(images.head[0], x, y, scene.actualTileSize, scene.actualTileSize, null);
                break;

            case "left":
                g.drawImage(images.head[1], x, y, scene.actualTileSize, scene.actualTileSize, null);
                break;

            case "right":
                g.drawImage(images.head[3], x, y, scene.actualTileSize, scene.actualTileSize, null);
                break;
        }
        
        body.bodyPaint(g);
        
    }

    public void checkCollision(){
        for(int i = 0; i <= body.body.size()-1; i++){
            
            if(x == body.body.get(i).x && y == body.body.get(i).y){

                collideBody = true;
                break;

            }
            else if(x < 0 || x >= (scene.actualTileSize * scene.maxColumn) ||
                y < 0 || y >= (scene.actualTileSize * scene.maxRow)){

                collideBody = true;
                break;

            }

        }
    }
    
}
