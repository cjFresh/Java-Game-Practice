/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample3.main;

import java.util.Random;

/**
 *
 * @author Fresh
 */
public class Spawn {
    private Handler handler;
    private HUD display;
    private Random r = new Random();
    private int scoreKeep = 0;
    
    public Spawn(Handler handler, HUD display){
        this.handler = handler;
        this.display = display;
    }
    
    public void tick(){
        scoreKeep++;
        
        if(scoreKeep >= 1000){
            scoreKeep = 0;
            display.setLevel(display.getLevel() + 1);
            
            if(display.getLevel() == 2){
                handler.addObject(new SmartEnemy(r.nextInt(game.WIDTH), r.nextInt(game.HEIGHT), ID.SmartEnemy, handler));  
                handler.addObject(new BasicEnemy(r.nextInt(game.WIDTH - 50), r.nextInt(game.HEIGHT - 50), ID.BasicEnemy, handler));
            }else if(display.getLevel() == 3){
                handler.addObject(new BasicEnemy(r.nextInt(game.WIDTH - 50), r.nextInt(game.HEIGHT - 50), ID.BasicEnemy, handler));
                handler.addObject(new BasicEnemy(r.nextInt(game.WIDTH - 50), r.nextInt(game.HEIGHT - 50), ID.BasicEnemy, handler));
            }else if(display.getLevel() == 4){
                handler.addObject(new FastEnemy(r.nextInt(game.WIDTH - 50), r.nextInt(game.HEIGHT - 50), ID.FastEnemy, handler));
            }else if(display.getLevel() == 5){
                handler.addObject(new BasicEnemy(r.nextInt(game.WIDTH - 50), r.nextInt(game.HEIGHT - 50), ID.SmartEnemy, handler));
            }else if(display.getLevel() == 6){
                handler.addObject(new FastEnemy(r.nextInt(game.WIDTH - 50), r.nextInt(game.HEIGHT - 50), ID.FastEnemy, handler));
            }else if(display.getLevel() == 7){
                handler.addObject(new FastEnemy(r.nextInt(game.WIDTH - 50), r.nextInt(game.HEIGHT - 50), ID.FastEnemy, handler));
            }else if(display.getLevel() == 10){
                handler.clearEnemies();
                handler.addObject(new EnemyBoss((game.WIDTH / 2) - 48, -150, ID.EnemyBoss, handler));
            }
        }
    }
}
