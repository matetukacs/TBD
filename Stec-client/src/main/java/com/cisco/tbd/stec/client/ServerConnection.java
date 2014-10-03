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
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author matetukacs
 */
public class ServerConnection {

    public static void pushDetectedThreat(AttackData attackData) throws IOException, JSONException {
        //DefaultHttpClient client = new DefaultHttpClient();
//        HttpPost post = new HttpPost("http://10.154.244.56/updatethreats");
//        //Example: $res['data'] = $my_mysql_wr->insert_threats("weiuyrwerywiuery", 1, "1.2.3.4, ""this is deme", "dos", 3);
//        JSONObject json = new JSONObject();
//        json.put("attackerIp", attackData.getAttackIp());
//        json.put("deviceKey", "adaskjasd");
//        json.put("exchangeId", 1);
//        
//        
//        post.setEntity(new StringEntity(json.toString()));
//        
//        HttpResponse response = client.execute(post);
//        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//        String line = "";
//        while ((line = rd.readLine()) != null) {
//            System.out.println(line);
//        }

        String request = "http://10.154.244.56/stec/insert_threats.php";
        HttpClient client = new HttpClient();

        PostMethod method = new PostMethod(request);

    // Add POST parameters
        method.addParameter("token", "weiuyrwerywiuery");

        method.addParameter("exchange", "1");

        method.addParameter("ip", "10");

        method.addParameter("descr", "An attack");

        method.addParameter("type", "dos");

        method.addParameter("level", "10");

    // Send POST request
        int statusCode = client.executeMethod(method);

        InputStream rstream = null;

        rstream = method.getResponseBodyAsStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(rstream));

        String line;

        while ((line = br.readLine()) != null) {

            System.out.println(line);

        }

        br.close();
        // Get the response body

    }

    public static ArrayList<String> pullNewRules(String timeStamp) throws IOException {

        String request = "http://10.154.244.56/stec/get_threats.php";

        HttpClient client = new HttpClient();

        GetMethod method = new GetMethod(request);

        //method.addRequestHeader("from", timeStamp);
		// Send GET request
        int statusCode = client.executeMethod(method);

        InputStream rstream = null;

        rstream = method.getResponseBodyAsStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(rstream));

        String line;

        while ((line = br.readLine()) != null) {

            System.out.println(line);

        }

        br.close();
//        HttpClient client = new DefaultHttpClient();
//        HttpGet request = new HttpGet("http://10.154.244.56:8080/Stec3/services/threats/demo");
//        HttpResponse response = client.execute(request);
//        BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
//        String line = "";
//        while ((line = rd.readLine()) != null) {
//          System.out.println(line);
//        }
//        
//        if (FileUtils.removeFile("./../timestamp.txt")) {
//            FileUtils.createFileWithText("./../timestamp.txt", timeStamp);
//        }

        return null;
    }
}
