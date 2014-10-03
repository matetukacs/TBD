package com.cisco.logreader.logentry;

import com.cisco.logreader.utils.LogEntryUtils;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 *
 * @author matetukacs
 */
public class LogEntryFactory {

    private static  Set<Class<? extends LogEntry>> logEntryTypes = new HashSet<>();
    
    
    
    public static <T extends LogEntry> T getLogEntryFromLine(String logLine) {
        
        for (Class<? extends LogEntry> logEntryType : getLogEntryTypes()) {

            if (LogEntryUtils.lineIsConvertableToLogEntry(logLine, logEntryType)) {
                
                return (T) createLogEntry(logEntryType, logLine);
            }
        }
        return (T) createLogEntry(SimpleTextLogEntry.class, logLine);
    }

    private static <T extends LogEntry> T createLogEntry(Class<T> logEntryType, String logLine) {
        try {
            T logEntry = logEntryType.newInstance();
            logEntry.setFromText(logLine);
            
            return logEntry;
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new IllegalArgumentException(logEntryType.getName() + " cannot be instantiated." + ex.getMessage());
        }
    }
    
    public static void setLogEntryTypes(Class<? extends LogEntry>... entryTypes) {
        
        for (Class<? extends LogEntry> logEntryType :  entryTypes) {
        
            LogEntryUtils.validate(logEntryType);
            logEntryTypes.add(logEntryType);
        }
    }
    
    public static void setLogEntryTypes(Collection<Class<? extends LogEntry>> entryTypes) {
        logEntryTypes.addAll(logEntryTypes);
    }
    
    public static Set<Class<? extends LogEntry>> getLogEntryTypes() {
        return logEntryTypes;
    }
}
