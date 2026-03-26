package rushedgames.games;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GameScene extends JPanel implements Runnable{
    
    int tileSize;
    int scale;
    int actualTileSize;
    int maxRow;
    int maxColumn;
    BufferedImage background;

    int FPS;
    int frameCount;
    int drawnFrames;
    Thread gameLoop;

    InputHandler input;
    Player player;

    AppleManager apple;

    double startTime;
    double currentTime;
    double delta;
    double drawIntervall;
    double remainingTime;
    long sleepBuffer;
    
    Font gameEnd;
    UIManager UI;
    
    public GameScene(){

        tileSize = 16;
        scale = 3;
        actualTileSize = tileSize * scale;
        maxRow = 12;
        maxColumn = 17;
        
        try{
            background = ImageIO.read(getClass().getResourceAsStream("/map.png"));
        }
        catch(IOException e){System.out.println("Loading Map failed.");}


        FPS = 30;
        frameCount = 1;
        drawnFrames = 0;
        gameLoop = new Thread(this);

        input = new InputHandler();
        player = new Player(this, input);

        apple = new AppleManager(this, player);

        delta = 0;
        drawIntervall = (double) 1 / FPS;
        sleepBuffer = 5000000;

        UI = new UIManager(this);

        gameEnd = new Font("arial", Font.BOLD, 41);

        this.setPreferredSize(new Dimension(actualTileSize * maxColumn, actualTileSize * maxRow));
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setBackground(Color.BLACK);
        this.setOpaque(true);
        this.addKeyListener(input);

        gameLoop.start();
        
    }

    public void run(){

        startTime = System.nanoTime();

        while(gameLoop != null){

            currentTime = System.nanoTime();
            delta += ((currentTime - startTime) / 1000000000);
            startTime = currentTime;


            if(delta >= drawIntervall){

                frameCount++;
                update();
                repaint();
                delta -= drawIntervall;

                if(frameCount == FPS){
                    frameCount = 0;
                }

            }
            else{

                remainingTime = (drawIntervall - delta) * 1000000000;

                if(remainingTime > sleepBuffer){

                    try{
                        Thread.sleep(1);
                    }
                    catch(InterruptedException e){
                        System.out.println("THREAD FEHLER");
                    }
                }

            }
            
        }

    }

    public void update(){

        if(!player.collideBody && !player.gameFinished){
            player.playerUpdate();
        }

        UI.updateUI();

    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(background, 0, 0, maxColumn * actualTileSize, maxRow * actualTileSize, null);
      
        player.playerPaint(g2D);

        apple.paintApple(g2D);

        UI.paintUI(g2D);

        if(player.collideBody){

            g2D.setColor(Color.BLACK);
            g2D.fillRect(actualTileSize * (maxColumn * 1/4), actualTileSize * (maxRow / 3), actualTileSize * 9, actualTileSize * 4);
            g2D.setColor(Color.GRAY);
            g2D.fillRect(actualTileSize * (maxColumn * 1/4) + 6, actualTileSize * (maxRow / 3) + 6, actualTileSize * 9 - 12, actualTileSize * 4 - 12);
            g2D.setFont(gameEnd);
            g2D.setColor(Color.BLACK);
            g2D.drawString("GAME OVER", ((actualTileSize * maxColumn) - 214 - actualTileSize / 2) / 2, actualTileSize * 6 + actualTileSize / 2 - 8);
            g2D.setColor(Color.RED);
            g2D.drawString("GAME OVER", ((actualTileSize * maxColumn) - 219 - actualTileSize / 2) / 2, actualTileSize * 6 + actualTileSize / 2 - 10);
            
        }
        else if(player.gameFinished){

            g2D.setColor(Color.BLACK);
            g2D.fillRect(actualTileSize * (maxColumn * 1/4), actualTileSize * (maxRow / 3), actualTileSize * 9, actualTileSize * 4);
            g2D.setColor(Color.GRAY);
            g2D.fillRect(actualTileSize * (maxColumn * 1/4) + 6, actualTileSize * (maxRow / 3) + 6, actualTileSize * 9 - 12, actualTileSize * 4 - 12);
            g2D.setFont(gameEnd);
            g2D.setColor(Color.BLACK);
            g2D.drawString("YOU WON", ((actualTileSize * maxColumn) - 180 - actualTileSize / 2) / 2, actualTileSize * 6 + actualTileSize / 2 - 8);
            g2D.setColor(Color.GREEN);
            g2D.drawString("YOU WON", ((actualTileSize * maxColumn) - 185 - actualTileSize / 2) / 2, actualTileSize * 6 + actualTileSize / 2 - 10);

        }

    } 

}
