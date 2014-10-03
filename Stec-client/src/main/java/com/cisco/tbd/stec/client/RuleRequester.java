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
import java.io.BufferedWriter;
import java.io.Writer;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import org.json.simple.JSONObject;

/**
 *
 * @author matetukacs
 */
public class RuleRequester extends TimerTask {
    
    private int lineCount;

    public static void runWithFrequency(int milis) {
        Timer timer = new Timer();
        timer.schedule(new RuleRequester(), 0, milis);
    }
    private PrintWriter writer;
    private PrintWriter writerRules;
    public void run() {
        try {
            JSONArray newRules; //        System.out.println("Hello from a thread!");
            newRules = ServerConnection.pullNewRules(FileUtils.getLastLineOfFile(Runner.PATH_TO_TIME_STAMP_FILE));
            writer = new PrintWriter("__just_log.txt", "UTF-8");
            writerRules = new PrintWriter("somesome.txt", "UTF-8");
         updateLocalRulesWith(newRules);   
            
        } catch (IOException ex) {
            Logger.getLogger(RuleRequester.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(RuleRequester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
<<<<<<< HEAD
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
        
        
=======
    
    private int rule_counter = 200000;

    public String create_requested_Snort_format(String in_ip)

    {

        //alert tcp any any -> any 12345 (msg:"12345 alert!!!"; sid:13400384;)

        this.rule_counter++;

        String res = "block tcp "+in_ip+" any -> any any (msg:\"Dynamic rule="+rule_counter+"\"; sid:"+rule_counter+";)";

        writer.println("New generated:"  +res);

        writer.flush();

        return res;

>>>>>>> 47ce401403481b9e8837ab41e0b5a9c02f5d1ddd
    }
    
    public void updateLocalRulesWith(JSONArray rules) {
        //create rules in right format and append to right file. possible through a static method in fileutisl. there is example code as well.            //FileWriter fWriter = new FileWriter(Runner.PATH_TO_TIME_STAMP_FILE);
       // String rulesWritten = rules.toString();
        //      rules['timestamp'] - int 
        //      rules['data'] - array
        for (Object object : rules) {
            JSONObject p = (JSONObject) object;
            //System.out.println(object.toString());
            //System.out.println(p.get("ip").toString());
            String temp = create_requested_Snort_format(p.get("ip").toString());
            writerRules.println(temp);
            writerRules.flush();
        }
        /*
        Iterator<String> iterator = rules.iterator();

        while (iterator.hasNext()) {

        //System.out.println(iterator.next().toString());
        }
    
        */
          //  try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("./../log.txt", true)))) {
          //      out.println(output);
          //  } catch (IOException e) {
                //exception handling left as an exercise for the reader
           // }
    }       
}
