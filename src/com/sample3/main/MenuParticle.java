/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample3.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author Fresh
 */
public class MenuParticle extends GameObject{
    private Handler handler;
    Random r = new Random();
    
    /*private int red = r.nextInt(255);
    private int green = r.nextInt(255);
    private int blue = r.nextInt(255);*/
    
    private Color col;
    int dir = 0;
    
    public MenuParticle(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        dir = r.nextInt(2);
        if(dir == 0){
            speedX = 2;
            speedY = 4;
        }else if(dir == 1){
            speedX = 4;
            speedY = 2;
        }
        
        col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
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
       handler.addObject(new Trail((int)x, (int)y, ID.Trail, col, 16, 16, 0.02f, handler));
    }
    
    public void render(Graphics g){
        g.setColor(col);
        g.fillRect((int)x, (int)y, 16, 16);
    }
}
