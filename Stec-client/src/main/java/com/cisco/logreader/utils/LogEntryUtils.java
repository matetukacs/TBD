package com.cisco.logreader.utils;

import com.cisco.logreader.annotations.MissingAnnotationException;
import com.cisco.logreader.annotations.ParsableLogEntry;
import com.cisco.logreader.annotations.WrongAnnotationException;
import com.cisco.logreader.attribute.IndexComparator;
import com.cisco.logreader.logentry.LogEntry;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Pattern;

/**
 *
 * @author matetukacs
 */
public class LogEntryUtils {

     public static boolean lineIsConvertableToLogEntry(String logLine, Class<? extends LogEntry> logEntryType) {

        validate(logEntryType);
        
//        if (!lineContainsRequiredAttributeSeparators(logLine, logEntryType)) {
//            return false;
//        }
        
        int expectedAttributeCount = getFormatAttributeCount(logEntryType);
        
        ArrayList<String> logLineParts = getAttributeValuesFromLogLine(logLine, logEntryType);

        
        if (expectedAttributeCount != logLineParts.size()) {
            return false;
        }
        
        for (String part : logLineParts) {
            Field logAttribute = getAttibuteFieldAtIndex(logEntryType, logLineParts.indexOf(part));

            if (!LogAttributeUtils.textIsConvertableToLogAttribute(part, logAttribute)) {
                return false;
            }
        }
        return true;
    }

    protected static boolean lineContainsRequiredAttributeSeparators(String logLine, Class<? extends LogEntry> logEntryType) {
        
        String[] attributeSeparators = getAttributeSeparators(logEntryType);
        
        for (String separator : attributeSeparators) {
            if (!logLine.contains(separator)) {
                return false;
            }
                
           String literalSeparator = Pattern.quote(separator);
            
           logLine = logLine.replaceFirst(literalSeparator, "");
        }
        return true;
    }
     
    protected static String[] getAttributeSeparators(Class<? extends LogEntry> logEntryType) {
        
        String attributePlaceholder = LogEntry.getLiteralAttributePlaceholder();
                
        String format = getFormat(logEntryType);
        
        String[] attributeSeparators = format.split(attributePlaceholder);
        
        return attributeSeparators;
    }
    
    public static int getFormatAttributeCount(Class<? extends LogEntry> logEntryType) {
        
        String format = getFormat(logEntryType);
        String attributePlaceholder = LogEntry.getAttributePlaceholder();
        
        return format.length() - format.replace(attributePlaceholder, "").length();
    }

    public static ArrayList<String> getAttributeValuesFromLogLine(String logLine, Class<? extends LogEntry> logEntryType) {

        String[] attributeSeparators = getAttributeSeparators(logEntryType);
        
        ArrayList<String> attributeValues = new ArrayList<>();

        String unparsedText = logLine;
        int separatorIndex = 0;

        while (!unparsedText.isEmpty()) {

            String currentAttribute;

            if (separatorIndex < attributeSeparators.length) {
                
                String separator = attributeSeparators[separatorIndex];
                
                String literalSeparator = Pattern.quote(separator);

                String[] lineParts = unparsedText.split(literalSeparator, 2);
                
                if (lineParts.length != 2) {
                    throw new IllegalArgumentException(logLine + "cannot be split into attributes based on the format of " + logEntryType.getName());
                }
                currentAttribute = lineParts[0];
                unparsedText = lineParts[1];
            } else {
                currentAttribute = unparsedText;
                unparsedText = "";
            }
            if (!currentAttribute.isEmpty()) {

                attributeValues.add(currentAttribute);
            }
            separatorIndex++;
        }
        return attributeValues;
    }

    public static Field getAttibuteFieldAtIndex(Class<? extends LogEntry> logEntryType, int index) {
        return getAttributeFields(logEntryType).get(index);
    }

    public static ArrayList<Field> getAttributeFields(Class<? extends LogEntry> logEntryType) {

        ArrayList<Field> attributes = new ArrayList<>();

        Field[] fieldArray = logEntryType.getDeclaredFields();

        ArrayList<Field> fields = new ArrayList<>(Arrays.asList(fieldArray));

        Collections.sort(fields, new IndexComparator());

        for (Field field : fields) {
            if (LogAttributeUtils.fieldIsLogAttribute(field)) {
                attributes.add(field);
            }
        }

        return attributes;
    }

    protected static String getFormat(Class<? extends LogEntry> logEntryType) {

        return getParsableLogEntryAnnotation(logEntryType).format();
    }

    private static ParsableLogEntry getParsableLogEntryAnnotation(Class<? extends LogEntry> logEntryType) {

        return logEntryType.getAnnotation(ParsableLogEntry.class);
    }

    public static void validate(Class<? extends LogEntry> logEntryType) {
        
        if (!logEntryType.isAnnotationPresent(ParsableLogEntry.class)) {
            throw new MissingAnnotationException("Log entry subtype is not parsable. @ParsableLogEntry annotation missing");
        }
        
        ArrayList<Field> attributeFields = getAttributeFields(logEntryType);

        int expectedAttributeCount = getFormatAttributeCount(logEntryType);

        if (attributeFields.size() != expectedAttributeCount) {
            throw new WrongAnnotationException("Number of fields defined by format annotation and actual number of fields are inconsistent");
        }
    }
}