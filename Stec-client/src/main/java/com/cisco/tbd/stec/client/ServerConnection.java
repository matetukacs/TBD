/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cisco.tbd.stec.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
//import org.codehaus.jettison.json.JSONException;
//import org.codehaus.jettison.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author matetukacs
 */
public class ServerConnection {

    public static void pushDetectedThreat(AttackLogEntry logEntry) throws IOException {

        String request = "http://10.154.244.56/stec/insert_threats.php";
        HttpClient client = new HttpClient();

        PostMethod method = new PostMethod(request);

    // Add POST parameters
        method.addParameter("token", Runner.DEVICE_ID);

        method.addParameter("exchange", Runner.EXCHANGE_ID);

        method.addParameter("ip", logEntry.getAttackIp());

        method.addParameter("descr", logEntry.toString());

        method.addParameter("type", "dos");

        method.addParameter("level", logEntry.getPriorityLevel());

    // Send POST request
        int statusCode = client.executeMethod(method);
//
//        InputStream rstream = null;
//
//        rstream = method.getResponseBodyAsStream();
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(rstream));
//
//        String line;
//
//        while ((line = br.readLine()) != null) {
//
//            System.out.println(line);
//
//        }

//        br.close();
        // Get the response body

    }

    public static JSONArray pullNewRules(String timeStamp) throws IOException, ParseException {

        String request = "http://10.154.244.56/stec/get_threats.php?token=weiuyrwerywiuery&exchange=1&from="+timeStamp;

        HttpClient client = new HttpClient();

        GetMethod method = new GetMethod(request);

        int statusCode = client.executeMethod(method);

        InputStream rstream = null;

        rstream = method.getResponseBodyAsStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(rstream));

        
        JSONObject json = (JSONObject)new JSONParser().parse(br.readLine());
        //System.out.println(json.toString());
        String newTimeStamp = json.get("timestamp").toString();
        JSONArray rules = (JSONArray) json.get("data");
        
        
        br.close();
        
        if (FileUtils.removeFile(Runner.PATH_TO_TIME_STAMP_FILE)) {
            FileUtils.createFileWithText(Runner.PATH_TO_TIME_STAMP_FILE, newTimeStamp);
        }

        return rules;
    }
}
