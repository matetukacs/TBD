/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cisco.tbd.stec.client;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jettison.json.JSONException;
import org.json.simple.parser.ParseException;

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
        try {
            //        System.out.println("Hello from a thread!");
            ServerConnection.pullNewRules("TimeStamp");
        } catch (IOException ex) {
            Logger.getLogger(RuleRequester.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(RuleRequester.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(RuleRequester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateLocalRulesWith(ArrayList<String> rules) {
        //create rules in right format and append to right file. possible throgh a static method in fileutisl. there is example code as well. 
    }

}