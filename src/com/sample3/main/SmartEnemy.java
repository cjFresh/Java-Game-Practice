/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample3.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Fresh
 */
public class SmartEnemy extends GameObject{
    private Handler handler;
    private GameObject player;
    
    public SmartEnemy(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        int i;
        
        for(i = 0; i < handler.object.size(); i++){
            if(handler.object.get(i).getID() == ID.Player) player = handler.object.get(i);
        }
        
        //speedX = 5;
        //speedY = 5;
    }
    
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 16, 16);
    }
    
    public void tick(){ 
       x += speedX;
       y += speedY;
       
       float diffX = x - player.getX() - 8; //difference of x axis
       float diffY = y - player.getY() - 8; //difference of y axis
       float distance = (float) Math.sqrt((x-player.getX()) * (x-player.getX()) + (y-player.getY()) * (y-player.getY()));
     
       speedX = (float) ((-1.0/distance) * diffX);
       speedY = (float) ((-1.0/distance) * diffY);
       
       if(y <= 0 || y >= game.HEIGHT - 32){
           speedY *= -1;
       }
       if(x <= 0 || x >= game.WIDTH - 16){
           speedX *= -1;
       }
       handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.magenta, 16, 16, 0.02f, handler));
    }
    
    public void render(Graphics g){
        g.setColor(Color.magenta);
        g.fillRect((int)x, (int)y, 16, 16);
    }
}
