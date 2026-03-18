package rushedgames.games;

import java.util.*;
import java.awt.*;

public class PlayerBody {

    Player head;
    ImageManager images;
    ArrayList<Entity> body = new ArrayList<>();
    int previousTailx;
    int previousTaily;
    Entity previous;
    Entity current;
    Entity part;

    
    public PlayerBody(Player head){

        this.head = head;
        images = new ImageManager();
        
        body.add(new Entity(head.x, head.y - head.scene.actualTileSize));
        body.get(0).direction = "bodyVertical";
        body.add(new Entity(head.x, head.y - head.scene.actualTileSize * 2));
        body.get(1).direction = "tailD";
        previousTailx = body.get(body.size() - 1).x;
        previousTaily = body.get(body.size() - 1).y;

        previous = new Entity();
        current = new Entity();

    }

    public void bodyUpdate(){

        if(!head.direction.equals("start")){
            
            for(int i = 0; i <= body.size() - 1; i++){

                if(i == 0){

                    previous.x = body.get(i).x;
                    previous.y = body.get(i).y;
                    body.get(i).x = head.previousPos.x;
                    body.get(i).y = head.previousPos.y;
                    
                }
                else if(i > 0 && i < body.size() - 2){

                    current.x = body.get(i).x;
                    current.y = body.get(i).y;
                    body.get(i).x = previous.x;
                    body.get(i).y = previous.y;
                    previous.x = current.x;
                    previous.y = current.y;        
                    
                }
                else{

                    previousTailx = body.get(i).x;
                    previousTaily = body.get(i).y;
                    body.get(i).x = previous.x;
                    body.get(i).y = previous.y;
                    previous.x = previousTailx;
                    previous.y = previousTaily;

                }

            }

            for(int i = 0; i <= body.size() - 1; i++){
                setPartDirection(i);
            }

        }

    }

    public void setPartDirection(int i){

        if(i == 0){

            body.get(i).direction = checkDirection(head, body.get(i), body.get(i + 1));

        }
        else if(i > 0 && i < body.size() - 1){

            body.get(i).direction = checkDirection(body.get(i - 1), body.get(i), body.get(i + 1));

        }
        else{

            Entity tempHead = body.get(i - 1);

            if(tempHead.x < body.get(i).x){
                body.get(i).direction = "tailL";
            }
            else if(tempHead.x == body.get(i).x && tempHead.y < body.get(i).y){
                body.get(i).direction = "tailU";
            }
            else if(tempHead.x == body.get(i).x && tempHead.y > body.get(i).y){
                body.get(i).direction = "tailD";
            }
            else if(tempHead.x > body.get(i).x){
                body.get(i).direction = "tailR";
            }

        }

    }

    public String checkDirection(Entity head, Entity body, Entity tail){

        if(head.x < body.x){

                if(tail.y < body.y){
                    return "cornerLU";
                }
                else if(tail.y == body.y){
                    return "bodyHorizontal";
                }
                else{
                    return "cornerLD";
                }

            }
        else if(head.x == body.x && head.y < body.y){

            if(tail.x > body.x){
                return "cornerUR";
            }
            else if(tail.x == body.x){
                return "bodyVertical";
            }
            else{
                return "cornerLU";
            }

        }
        else if(head.x > body.x){

            if(tail.y > body.y){
                return "cornerDR";
            }
            else if(tail.x < body.x){
                return "bodyHorizontal";
            }
            else{
                return "cornerUR";
            }

        }
        else if(head.x == body.x && head.y > body.y){

            if(tail.x < body.x){
                return "cornerLD";
            }
            else if(tail.x == body.x){
                return "bodyVertical";
            }
            else{
                return "cornerDR";
            }

        }
        
        return "ERROR";

    }

    public void bodyPaint(Graphics g){

        for(int i = 0; i < body.size(); i++){
            
            part = body.get(i);

            switch (part.direction) {
                case "bodyVertical":

                    g.drawImage(images.body[0], part.x, part.y, head.scene.actualTileSize, head.scene.actualTileSize, null);
                    
                    break;
                
                case "bodyHorizontal":
                    
                    g.drawImage(images.body[1], part.x, part.y, head.scene.actualTileSize, head.scene.actualTileSize, null);

                    break;
                
                case "cornerLU":
                    
                    g.drawImage(images.corner[0], part.x, part.y, head.scene.actualTileSize, head.scene.actualTileSize, null);

                    break;

                case "cornerLD":
                    
                    g.drawImage(images.corner[3], part.x, part.y, head.scene.actualTileSize, head.scene.actualTileSize, null);

                    break;

                case "cornerUL":
                    
                    g.drawImage(images.corner[0], part.x, part.y, head.scene.actualTileSize, head.scene.actualTileSize, null);

                    break;

                case "cornerUR":
                    
                    g.drawImage(images.corner[1], part.x, part.y, head.scene.actualTileSize, head.scene.actualTileSize, null);

                    break;

                case "cornerRU":
                    
                    g.drawImage(images.corner[1], part.x, part.y, head.scene.actualTileSize, head.scene.actualTileSize, null);

                    break;

                case "cornerRD":
                    
                    g.drawImage(images.corner[2], part.x, part.y, head.scene.actualTileSize, head.scene.actualTileSize, null);

                    break;

                case "cornerDL":
                    
                    g.drawImage(images.corner[3], part.x, part.y, head.scene.actualTileSize, head.scene.actualTileSize, null);

                    break;

                case "cornerDR":
                    
                    g.drawImage(images.corner[2], part.x, part.y, head.scene.actualTileSize, head.scene.actualTileSize, null);

                    break;

                case "tailL":
                    
                    g.drawImage(images.tail[1], part.x, part.y, head.scene.actualTileSize, head.scene.actualTileSize, null);

                    break;

                case "tailU":
                    
                    g.drawImage(images.tail[2], part.x, part.y, head.scene.actualTileSize, head.scene.actualTileSize, null);

                    break;

                case "tailR":

                    g.drawImage(images.tail[3], part.x, part.y, head.scene.actualTileSize, head.scene.actualTileSize, null);
                    
                    break;

                case "tailD":

                    g.drawImage(images.tail[0], part.x, part.y, head.scene.actualTileSize, head.scene.actualTileSize, null);

                    break;
            
                default:

                    System.out.println("!!! PAINT ERROR !!!");

                    break;
            }

        }

    }

    public void addBodyPart(){

        body.add(new Entity(previousTailx, previousTaily));
        setPartDirection(body.size()-1);
        setPartDirection(body.size()-2);

    }

}
