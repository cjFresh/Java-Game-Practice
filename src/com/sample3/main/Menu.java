/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample3.main;

import static com.sample3.main.game.HEIGHT;
import com.sample3.main.game.STATE;
import static com.sample3.main.game.WIDTH;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 *
 * @author Fresh
 */
public class Menu extends MouseAdapter{
    
    private game play;
    private Handler handler;
    private HUD display;
    private Random r = new Random();
    
    public Menu(game play, Handler handler, HUD display){
        this.play = play;
        this.handler = handler;
        this.display = display;
    }
    
    public void mousePressed(MouseEvent e){
        int mx = e.getX(); //storing x position
        int my = e.getY(); //storing y position
        
        if(play.gameState == STATE.Menu){
            //PLAY BUTTON 
            if(mouseOver(mx, my, 210, 150, 200, 64)){
                play.gameState = STATE.Game;
                handler.addObject(new Player(game.WIDTH/2-32, game.HEIGHT/2-32, ID.Player, handler));
                handler.clearEnemies();
                handler.addObject(new BasicEnemy(r.nextInt(game.WIDTH), r.nextInt(game.HEIGHT), ID.BasicEnemy, handler));
            }
        
            //HELP BUTTON
            if(mouseOver(mx, my, 210, 250, 200, 64)){
                play.gameState = STATE.Help;
            }
        
            //QUIT BUTTON
            if(mouseOver(mx, my, 210, 350, 200, 64)){
                System.exit(0);
            }
        }
        
        //BACK BUTTON FOR HELP
        if(play.gameState == STATE.Help){
            if(mouseOver(mx, my, 210, 350, 200, 64)){
                play.gameState = STATE.Menu;
                return;
            }
        }
        
        //BACK BUTTON FOR PLAY AGAIN
        if(play.gameState == STATE.End){
            if(mouseOver(mx, my, 210, 350, 200, 64)){
                play.gameState = STATE.Game;
                display.setLevel(1);
                display.setScore(0);
                handler.addObject(new Player(game.WIDTH/2-32, game.HEIGHT/2-32, ID.Player, handler));
                handler.clearEnemies();
                handler.addObject(new BasicEnemy(r.nextInt(game.WIDTH), r.nextInt(game.HEIGHT), ID.BasicEnemy, handler));
            }
        }
    }
    
    public void mouseReleased(MouseEvent e){
    
    }
    
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if(mx > x && mx < x + width){
            if(my > y && my < y + height){
                return true;
            }else return false; 
        }else return false;
    }
    
    public void tick(){
    
    }
    
    public void render(Graphics g){
        if(play.gameState == STATE.Menu){
            Font title = new Font("Digifacewide", 1, 45);
            Font fnt2 = new Font("Candara", 1, 30);
            Font subheading = new Font("consolas", 1, 18);
        
            g.setFont(title);
            g.setColor(Color.magenta);
            g.drawString("Laser Escape", 120, 90);
            g.setFont(subheading);
            g.setColor(Color.magenta);
            g.drawString("Can you escape a barrage of lasers?", 140, 120);
        
            g.setFont(fnt2);
           
            //PLAY
            g.setColor(Color.magenta);
            g.drawRect(210, 150, 200, 64);
            g.setColor(Color.white);
            g.drawString("Play", 280, 190);
            
            //HELP
            g.setColor(Color.magenta);
            g.drawRect(210, 250, 200, 64);
            g.setColor(Color.white);
            g.drawString("Help", 280, 290);
        
            //QUIT
            g.setColor(Color.magenta);
            g.drawRect(210, 350, 200, 64);
            g.setColor(Color.white);
            g.drawString("Quit", 280, 390);
        }else if(play.gameState == STATE.Help){
            Font fnt1 = new Font("Candara", 1, 45);
            Font fnt2 = new Font("Candara", 1, 30);
            
            g.setFont(fnt1);
            g.setColor(Color.white);
            g.drawString("Help", 260, 70);
            
            
            g.setFont(fnt2);
            g.drawString("W - Move UP", 200, 120);
            g.drawString("S - Move DOWN", 200, 150);
            g.drawString("D - Move RIGHT", 200, 180);
            g.drawString("A - Move LEFT", 200, 210);
            
            //BACK TO MENU
            g.setColor(Color.magenta);
            g.drawRect(210, 350, 200, 64);
            g.setColor(Color.white);
            g.drawString("Back", 280, 390);
        }else if(play.gameState == STATE.End){
            Font fnt1 = new Font("Candara", 1, 45);
            Font fnt2 = new Font("Candara", 1, 30);
            
            g.setFont(fnt1);
            g.setColor(Color.white);
            g.drawString("Game Over", 210, 70);
            
            g.setFont(fnt2);
            g.drawString("Your score: "+ display.getScore(), 210, 120);
            //g.drawString("S - Move DOWN", 200, 150);
            //g.drawString("D - Move RIGHT", 200, 180);
            //g.drawString("A - Move LEFT", 200, 210);
            
            //RETRY
            g.setColor(Color.magenta);
            g.drawRect(210, 350, 200, 64);
            g.setColor(Color.white);
            g.drawString("Play Again", 240, 390);
        }
        
    }
    
}
