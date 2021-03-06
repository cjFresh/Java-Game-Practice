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
public class FastEnemy extends GameObject{
    private Handler handler;
    
    public FastEnemy(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        speedX = 2;
        speedY = 15;
    }
    
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 16, 16);
    }
    
    public void tick(){ 
       x += speedX;
       y += speedY;
       
       if(y <= 0 || y >= game.HEIGHT - 32){
           speedY *= -1;
       }
       if(x <= 0 || x >= game.WIDTH - 16){
           speedX *= -1;
       }
       handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.cyan, 16, 16, 0.03f, handler));
    }
    
    public void render(Graphics g){
        g.setColor(Color.cyan);
        g.fillRect((int)x, (int)y, 16, 16);
    }
}
