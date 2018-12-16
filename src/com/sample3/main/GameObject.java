/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample3.main;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Fresh
 */
public abstract class GameObject { //anything in the game like enemies and such
    protected float x, y; //means that it can only be accessed by which object inherits the class
    protected ID id;
    protected float speedX, speedY; // controls the speed in the x and y directions
    
    public GameObject(float x, float y, ID id){ //constructor
        this.x = x;
        this.y = y;
        this.id = id;
    }
    
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public float getX(){
        return x;
    }
    
    public float getY(){
        return y;
    }
    
    public void setId(ID id){
        this.id = id;
    }
    
    public ID getID(){
        return id;
    }
    
    public void setSpeedX(int speedX){
        this.speedX = speedX;
    }
    
    public void setSpeedY(int speedY){
        this.speedY = speedY;
    }
    
    public float getSpeedX(){
        return speedX;
    }
    
    public float getSpeedY(){
        return speedY;
    }
}
