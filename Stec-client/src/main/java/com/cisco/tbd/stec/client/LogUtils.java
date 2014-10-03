/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cisco.tbd.stec.client;

import com.cisco.logreader.logentry.LogEntry;
import com.cisco.logreader.logentry.LogEntryFactory;

/**
 *
 * @author matetukacs
 */
public class LogUtils {

    public static LogEntry getAttackData(String log) {
        
        LogEntryFactory.setLogEntryTypes(AttackLogEntry.class);
        
        LogEntry logEntry = LogEntryFactory.getLogEntryFromLine(log);
        
        return logEntry;
    }
}
