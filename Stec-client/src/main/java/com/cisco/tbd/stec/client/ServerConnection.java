/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cisco.tbd.stec.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.http.HttpResponse;
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
public class ServerConnection {
    
    public static void pushDetectedThreat(String clientIpAddress, String attackerIpAddress, String deviceKey, int exchangeId) throws IOException, JSONException {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://10.154.244.56");
        
        JSONObject json = new JSONObject();
        json.put("clientIp", clientIpAddress);
        json.put("attackerIp", attackerIpAddress);
        json.put("deviceKey", deviceKey);
        json.put("exchangeId", exchangeId);
        
        
        post.setEntity(new StringEntity(json.toString()));
        
        HttpResponse response = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
        }
    }
    
    public static ArrayList<String> pullNewRules(String timeStamp) throws IOException {
        
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://10.154.244.56");
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        while ((line = rd.readLine()) != null) {
          System.out.println(line);
        }
        
        if (FileUtils.removeFile("./../timestamp.txt")) {
            FileUtils.createFileWithText("./../timestamp.txt", timeStamp);
        }
        
        return null;
    }
}
