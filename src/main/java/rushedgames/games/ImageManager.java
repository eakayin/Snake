package rushedgames.games;

import java.awt.geom.AffineTransform;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;


public class ImageManager {

    BufferedImage headImage;
    BufferedImage bodyImage;
    BufferedImage cornerImage;
    BufferedImage tailImage;
    BufferedImage[] head ;
    BufferedImage[] body;
    BufferedImage[] corner;
    BufferedImage[] tail;
    AffineTransform af;
    AffineTransformOp op;

    public ImageManager(){

        head = new BufferedImage[4];
        body = new BufferedImage[2];
        corner = new BufferedImage[4];
        tail = new BufferedImage[4];
        
        loadImages();

    }

    public void loadImages(){

        try{

        headImage = ImageIO.read(getClass().getResourceAsStream("/snake_sprites/head.png"));
        bodyImage = ImageIO.read(getClass().getResourceAsStream("/snake_sprites/body.png"));
        cornerImage = ImageIO.read(getClass().getResourceAsStream("/snake_sprites/corner.png"));
        tailImage = ImageIO.read(getClass().getResourceAsStream("/snake_sprites/tail.png"));
        
        }
        catch(IOException e){System.out.println("Loading Images failed.");}

        rotateImages();

    }

    public void rotateImages(){

        af = AffineTransform.getRotateInstance(Math.toRadians(90), 16, 16);
        op = new AffineTransformOp(af, AffineTransformOp.TYPE_BICUBIC);

        for(int i = 0; i <= 3; i++){

            if(i == 0){

                head[i] = headImage;
                corner[i] = cornerImage;
                tail[i] = tailImage;

            }
            else{

                head[i] = op.filter(head[i-1],null);
                corner[i] = op.filter(corner[i-1],null);
                tail[i] = op.filter(tail[i-1],null);

            }

        }

        body[0] = bodyImage;
        body[1] = op.filter(body[0], null);
        
    }

    /*  Methode die, die gedrehten Sprites malt.

        public void testPaint(Graphics g){
    
           int x = 0;
           int y = 0;

           for(int i = 0; i <= 3; i++){

               g.drawImage(head[i], x, y, 32, 32, null);
               x += 32;
               g.drawImage(corner[i], x, y, 32, 32, null);
               x += 32;
               g.drawImage(tail[i], x, y, 32, 32, null);
               y += 32;
               x = 0;

           }

           g.drawImage(body[0], x, y, 32, 32, null);
           x += 32;
           g.drawImage(body[1], x , y, 32, 32, null);

       }
    */
}
