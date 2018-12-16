/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample3.main;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;
/**
 *
 * @author Fresh
 */
public class Window extends Canvas{
    
    private static final long serialVersionUID = -240840600533728354L;
    
    public Window(int width, int height, String title, game play){
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //the x button to exit
        frame.setResizable(false); //can we resize window? false = no
        frame.setLocationRelativeTo(null); // not really needed, but if not null it will start on top left
        frame.add(play); //adding game class into the fram
        frame.setVisible(true); //set to true so that window is visible
        play.start(); //runs start method on Game class
    }
}
