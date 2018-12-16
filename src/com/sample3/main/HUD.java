/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample3.main;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Fresh
 */
public class HUD {
    
    public static float health = 100; 
    //it's not good to use static, but we're not gonna have any other health variable
    //handy daw, kay no need to initialize (?)
    
    private int score = 0;
    private int level = 1;
    
    
    public void tick(){
        //health--;
        health = game.clamp(health, 0, 100);
        
        score++;
    }
    
    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillRect(15, 420, 200, 16);
        g.setColor(Color.green);
        g.fillRect(15, 420, (int)health * 2, 16);
        g.setColor(Color.white);
        g.drawRect(15, 420, 200, 16);
        //for now ing ani lang sa, mag import lang file later
        g.drawString("Score: " + score, 15, 395);
        g.drawString("Level: " + level, 15, 410);
    }
    
    public void setScore(int score){
        this.score = score;
    }
    
    public int getScore(){
        return score;
    }
    
    public int getLevel(){
        return level;
    }
    
    public void setLevel(int level){
        this.level = level;
    }
}
