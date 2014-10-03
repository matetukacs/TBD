/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cisco.logreader;

import com.cisco.logreader.logentry.LogEntry;
import com.cisco.logreader.logentry.LogEntryFactory;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author matetukacs
 */
public class LogParser {

    public static ArrayList<LogEntry> parseLog(String logText) {
        
        ArrayList<LogEntry> logEntries = new ArrayList<>();
        
        Scanner scanner = new Scanner(logText);
        while (scanner.hasNextLine()) {
          String logLine = scanner.nextLine();
          
          LogEntry logEntry = LogEntryFactory.getLogEntryFromLine(logLine);
          
          logEntries.add(logEntry);
        }
        scanner.close();
        
        return logEntries;
    }
}
