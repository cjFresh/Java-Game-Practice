/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample3.main;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author Fresh
 */
public class Player extends GameObject{
    
    Random r = new Random();
    Handler handler;
    
    public Player(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        //setX(100);
        //speedX = 1;
    }
    
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 32, 32);
    }
    
    public void tick(){
        x += speedX;
        y += speedY;
        x = game.clamp((int)x, 0, game.WIDTH - 38);
        y = game.clamp((int)y, 0, game.HEIGHT - 60);
        collision();
    }
    
    private void collision(){
        int ctr;
        for(ctr = 0; ctr < handler.object.size(); ctr++){
            GameObject tempObject = handler.object.get(ctr); 
            if(tempObject.getID() == ID.BasicEnemy || tempObject.getID() == ID.FastEnemy || tempObject.getID() == ID.SmartEnemy || tempObject.getID() == ID.EnemyBoss){ //if tempObject kay enemy then iya i-perform ang action below
                if(getBounds().intersects(tempObject.getBounds())){
                    // ^ if player nga getBounds() mu intersect with enemy (tempObject) nga getBounds
                    // it will perform this action
                    //collision (hit)
                    HUD.health -= 2; //enemies delivery 2 HP damage
                }
            }
        }
    }
    
    public void render(Graphics g){
        
        Graphics2D g2d = (Graphics2D) g; //casting a method, I don't understand this part that much
        
        g.setColor(Color.green);
        g2d.draw(getBounds());
        
        g.setColor(Color.white);
        g.fillRect((int)x, (int)y, 32, 32);
    }
    
}
