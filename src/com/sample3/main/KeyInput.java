/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample3.main;

import com.sample3.main.game.STATE;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Fresh
 */
public class KeyInput extends KeyAdapter{
    
    private Handler handler;
    private boolean[] keyDown = new boolean[4];
    game play;
    
    
    public KeyInput(Handler handler, game play){
        this.handler = handler;
        this.play = play;
        keyDown[0] = false; //W
        keyDown[1] = false; //S
        keyDown[2] = false; //D
        keyDown[3] = false; //A
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode(); //when we press a key, it's going to display an ASCII number value 
        int i;
        //System.out.println(key); //for checking purposes
        for (i = 0; i < handler.object.size(); i++){ //loop through the object to manage controls
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getID() == ID.Player){
                //key events for player
                if(key == KeyEvent.VK_W){
                    tempObject.setSpeedY(-5); //Move Up
                    keyDown[0] = true;
                }
                if(key == KeyEvent.VK_S){
                    tempObject.setSpeedY(5); //Move Down
                    keyDown[1] = true;
                }
                if(key == KeyEvent.VK_A){
                    tempObject.setSpeedX(-5); //Move Left
                    keyDown[2] = true;
                }
                if(key == KeyEvent.VK_D){
                    tempObject.setSpeedX(5); //Move Right
                    keyDown[3] = true;
                }
            }
        }
        
        if(key == KeyEvent.VK_P){
            if(play.gameState == STATE.Game){
                if(game.paused){
                    game.paused = false;
                }else{
                    game.paused = true;
                }
            }
        }
        if(key == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }
    }
    
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        int i;
        //System.out.println(key); //for checking purposes
        for (i = 0; i < handler.object.size(); i++){ //loop through the object to manage controls
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getID() == ID.Player){
                //key events for player
                if(key == KeyEvent.VK_W){
                    //tempObject.setSpeedY(0); //Move Up
                    keyDown[0] = false;
                }
                if(key == KeyEvent.VK_S){
                    //tempObject.setSpeedY(0); //Move Down
                    keyDown[1] = false;
                }
                if(key == KeyEvent.VK_A){
                    //tempObject.setSpeedX(0); //Move Left
                    keyDown[2] = false;
                }
                if(key == KeyEvent.VK_D){
                    //tempObject.setSpeedX(0); //Move Right
                    keyDown[3] = false;
                }
                
                //vertical movement
                if(!keyDown[0] && !keyDown[1]){
                    tempObject.setSpeedY(0);
                }
                //horizontal movement
                if(!keyDown[2] && !keyDown[3]){
                    tempObject.setSpeedX(0);
                }
            }
        }
    }
}
