/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cisco.tbd.stec.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jettison.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import java.io.BufferedWriter;
import java.io.Writer;
import java.io.FileWriter;
import java.io.PrintWriter;

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
            JSONArray newRules; //        System.out.println("Hello from a thread!");
            newRules = ServerConnection.pullNewRules(FileUtils.getLastLineOfFile(Runner.PATH_TO_TIME_STAMP_FILE));
            
         updateLocalRulesWith(newRules);   
            
        } catch (IOException ex) {
            Logger.getLogger(RuleRequester.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(RuleRequester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateLocalRulesWith(JSONArray rules) {
        //create rules in right format and append to right file. possible throgh a static method in fileutisl. there is example code as well.            //FileWriter fWriter = new FileWriter(Runner.PATH_TO_TIME_STAMP_FILE);
        String rulesWritten = rules.toString();
        int startArrayIndex = 0;
        int endArrayIndex = 0;
        while(endArrayIndex <= rulesWritten.length()) {
            startArrayIndex = rulesWritten.indexOf("{", endArrayIndex) + 1;
            endArrayIndex = rulesWritten.indexOf("}", endArrayIndex);
            String output = rulesWritten.substring(startArrayIndex, endArrayIndex);
            try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("log.txt", true)))) {
                out.println(output);
            } catch (IOException e) {
                //exception handling left as an exercise for the reader
            }
            //fWriter.write(output+"/n");
        }
    }       
}
