/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cisco.tbd.stec.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jettison.json.JSONException;
import org.json.simple.JSONArray;
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
            JSONArray newRules = ServerConnection.pullNewRules(FileUtils.getLastLineOfFile(Runner.PATH_TO_TIME_STAMP_FILE));
            
         updateLocalRulesWith(newRules);   
            
        } catch (IOException ex) {
            Logger.getLogger(RuleRequester.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(RuleRequester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private int rule_counter = 200000;
    private PrintWriter writer = new PrintWriter("__just_log.txt", "UTF-8");

    public String create_requested_Snort_format(String in_ip)
    {
        //alert tcp any any -> any 12345 (msg:"12345 alert!!!"; sid:13400384;)
        this.rule_counter++;
        String res = "block tcp "+in_ip+" any -> any any (msg:\"Dynamic rule="+rule_counter+"\"; sid:"+rule_counter+";)";
        writer.println("New generated:"  +res);
        writer.flush();
        return res;
    }
    public void updateLocalRulesWith(JSONArray rules) {
        //create rules in right format and append to right file. possible throgh a static method in fileutisl. there is example code as well.
        
        
    }

}