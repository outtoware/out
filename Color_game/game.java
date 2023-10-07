/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

import java.awt.Color;
import java.awt.List;
import static java.lang.Boolean.FALSE;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Rafael
 */
public class game extends Thread {

    private int on = 1;
    private static ArrayList<Integer> colors;
    private int times = 1;
    int randomNumber;
    private Random random;

    //panels
    JPanel pRed;
    JPanel pBlue;
    JPanel pGreen;
    JPanel pYellow;
    private frame frame;

    private final Object lock = new Object();
    private boolean isBlocked = false;

    public void blockThread() {
        isBlocked = true;
    }

    public void unblockThread() {
        synchronized (lock) {
            isBlocked = false;
            lock.notify();
        }
    }
                                   
    public game(frame aThis, JPanel jPanel1, JPanel jPanel2, JPanel jPanel3, JPanel jPanel4) {
        frame = aThis;
        pRed = jPanel1;
        pBlue = jPanel2;
        pGreen = jPanel3;
        pYellow = jPanel4;
        colors = new ArrayList<>();
        random = new Random();
    }

    @Override
    public void run() {
        System.out.print("ola");
        while (true) {
            
            // Your thread's task goes here

            // Check if the thread should be blocked
            if (!isBlocked) {
                synchronized (lock) {
                    try {
                        
            
                        
                        
                        
                        frame.canClick=0;
                        randomNumber = random.nextInt(4);
                        colors.add(randomNumber);
                        System.out.println(colors.get(times - 1));
                        times++;

                        int nColors = colors.size();

                        reset_colors();
                        for (int index = 0; index < nColors; index++) {
                            reset_colors();
                            try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
                            if (colors.get(index) == 0) {
                                pRed.setBackground(Color.decode("#ff0000"));
                            } else if (colors.get(index) == 1) {
                                pGreen.setBackground(Color.decode("#00ff00"));
                            } else if (colors.get(index) == 2) {
                                pYellow.setBackground(Color.decode("#ffff00"));
                            } else {
                                pBlue.setBackground(Color.decode("#0080ff"));
                            }
                            try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
                        }
                        reset_colors();
                      
                        try {frame.updateList(colors);} catch (InterruptedException ex) {Logger.getLogger(game.class.getName()).log(Level.SEVERE, null, ex);}
                    
                        lock.wait(); // Block the thread until unblocked
                    } catch (InterruptedException e) {e.printStackTrace();}
                }
            }else{
                try {lock.wait();} catch (InterruptedException ex) {Logger.getLogger(game.class.getName()).log(Level.SEVERE, null, ex);}
}
        }
    }

    public void reset_colors() {
        pRed.setBackground(Color.decode("#800000"));
        pBlue.setBackground(Color.decode("#004080"));
        pGreen.setBackground(Color.decode("#008000"));
        pYellow.setBackground(Color.decode("#808000"));

    }
    


    public ArrayList<Integer> getColors() {
        return colors;
    }

}
