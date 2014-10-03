/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cisco.tbd.stec.client;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author matetukacs
 */
public class RuleRequester extends TimerTask {

    public static void runWithFrequency(int milis) {
        Timer timer = new Timer();
        timer.schedule(new RuleRequester(), 0, milis);
    }
    
    public void run() {
        System.out.println("Hello from a thread!");
    }

}