/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cisco.tbd.stec.client;

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

    public static int lastLineCount = 0;
    public static void main(String[] args) throws Exception{
        
        if (args.length == 0) {
            System.err.println("Specify log file location");
        }
        
        
        
        
        RuleRequester.runWithFrequency(5000);
        
        String filePath = args[0];
        
        
        List<String> lines = getLinesOfFile(filePath);
       
        for (String line : lines) {
            System.out.println(line);
        }
        
        while (true) {
            int currentLineCount = FileUtils.getFileLineCount(filePath);
            
            if (currentLineCount != lastLineCount) {
                
                ServerConnection.p
                
                lastLineCount = currentLineCount;
            }
            
           
        }
        //System.err.println("Specify output folder in mtukuacs/output");
    }
    
}
