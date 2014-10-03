/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cisco.tbd.stec.client;

import com.cisco.logreader.logentry.LogEntry;
import static com.cisco.tbd.stec.client.FileUtils.getLinesOfFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author matetukacs
 */
public class Runner {

    public static final String PATH_TO_TIME_STAMP_FILE = "./../timestamp.txt";
    public static final String DEVICE_ID = "weiuyrwerywiuery";
    public static final String EXCHANGE_ID = "1";
    
    public static int lastLineCount = 0;
    public static void main(String[] args) throws Exception{
        
        //if (args.length == 0) {
         //   System.err.println("Specify log file location");
        //}
        
        
        
        
        RuleRequester.runWithFrequency(30000);
        
        String filePath = "/var/log/snort/mylog.txt";
        
//        
//        List<String> lines = getLinesOfFile(filePath);
//       
//        for (String line : lines) {
//            ServerConnection.pushDetectedThreat(LogUtils.getAttackData(line));
//        }
        
        while (true) {
            int currentLineCount = FileUtils.getFileLineCount(filePath);
            
            if (currentLineCount != lastLineCount) {
                String lastLine = FileUtils.getLastLineOfFile(filePath);
                if (lastLine.contains("/n")) {
                    LogEntry logEntry = LogUtils.getAttackData(lastLine);
                    System.out.println(logEntry.toString());
                    if (logEntry instanceof AttackLogEntry) {
                        ServerConnection.pushDetectedThreat((AttackLogEntry)logEntry);
                        System.out.println(logEntry.toString());
                    }

                    lastLineCount = currentLineCount;
                }
            }
            
           
        }
        //System.err.println("Specify output folder in mtukuacs/output");
    }
    
}
