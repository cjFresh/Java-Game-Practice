/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample3.main;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;
/**
 *
 * @author Fresh
 */
public class game extends Canvas implements Runnable{
    
    private static final long serialVersionUID = 1550691097823471818L;
    
    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
   
    private Thread thread; //not really recommended, but this is a single-threaded game
    private boolean running = false; //see run method
    public static boolean paused = false;
    private Random r;
    private Handler handler;
    private HUD display;
    private Spawn spawner;
    private Menu menu;
    
    public enum STATE{
        Menu,
        Help,
        Game,
        End
    };
    
    public static STATE gameState = STATE.Menu;
    
    public game(){
        int i;
        
        handler = new Handler();
        display = new HUD();
        menu = new Menu(this, handler, display);
        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(menu);
        
        new Window(WIDTH, HEIGHT, "Laser Escape", this);

        spawner = new Spawn(handler, display);
        r = new Random();
        
        if(gameState == STATE.Game){
            handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
            handler.addObject(new BasicEnemy(r.nextInt(game.WIDTH), r.nextInt(game.HEIGHT), ID.BasicEnemy, handler));
        }else{
            for(i = 0; i < 10; i++){
                handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
            }
        }
    }
    
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    
    public synchronized void stop(){
        try{
            thread.join(); //killing off threads
            running = false;
        }catch(Exception e){
            e.printStackTrace(); //runs an error in the console
        }
    }
    
    public void run(){ //GAME LOOP, one of the popular ones. Copy-pasted only.
        this.requestFocus(); //does not make you click the screen before you play
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running){
                render();
                frames++;
            }
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                //System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }
    
    private void tick(){ //Tick means we update the game logic before we render
        if(gameState == STATE.Game){
            if(!paused){
                handler.tick();
                display.tick();
                spawner.tick();
            
                if(display.health <= 0){
                    display.health = 100;
                    gameState = STATE.End;
                    handler.clearEnemies();
                    for(int i = 0; i < 10; i++){
                        handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
                    }
                }
            } 
        }else if(gameState == STATE.Menu || gameState == STATE.End){
            handler.tick();
            menu.tick();
        }
    }
    
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3); //not recommended to go over 3
            return;
        }
        Graphics g = bs.getDrawGraphics();
        
        g.setColor(Color.black);
        g.fillRect((int)0, (int)0, WIDTH, HEIGHT);
        
        handler.render(g);
        
        if(paused){
            g.setColor(Color.white);
            g.drawString("PAUSED", 100, 100);
        }
        
        if(gameState == STATE.Game){
            display.render(g);
        }else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End){
            menu.render(g);
        }
        
        
        g.dispose();
        bs.show();
    }
    
    public static float clamp(float var, float min, float max){
        if(var >= max){
            return var = max;
        }else if (var <= min){
            return var = min;
        }else{
            return var;
        }
    }
    
    public static void main(String args[]){
        new game();
    }
}
