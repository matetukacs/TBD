package com.cisco.logreader.logentry;

import com.cisco.logreader.attribute.LogAttribute;
import com.cisco.logreader.utils.LogEntryUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *
 * @author matetukacs
 */
public abstract class LogEntry{

    //subclass formats cannot contain * or ? symbols.
    private static String attributePlaceholder = "?";
    
    protected void setFromText(String logText) {
        //String[] attributeSeparators = LogEntryUtils.getAttributeSeparators(getClass());

        ArrayList<String> attributeValues = LogEntryUtils.getAttributeValuesFromLogLine(logText, getClass());

        ArrayList<LogAttribute> attributes = getLogAttributes();

        for (LogAttribute attribute : attributes) {
            int index = attributes.indexOf(attribute);
            try {
                attribute.set(attributeValues.get(index));
            } catch (NullPointerException ex) {
                throw new RuntimeException(" attribute was not initialized before attempt to set from log text.");
            }

        }
    }

    private ArrayList<LogAttribute> getLogAttributes() {

        ArrayList<LogAttribute> logAttributes = new ArrayList<>();

        for (Field field : LogEntryUtils.getAttributeFields(getClass())) {

            try {
                field.setAccessible(true);
                logAttributes.add((LogAttribute) field.get(this));
            } catch (IllegalAccessException ex) {
                throw new IllegalArgumentException("Field " + field.getName() + " not accessible for " + getClass().getName() + " instance.");
            }

        }

        return logAttributes;
    }
    
    public static void setAttributePlaceholder(String placeholder) {
        attributePlaceholder = placeholder;
    }
    
    public static String getAttributePlaceholder() {
        return attributePlaceholder;
    }
    
    public static String getLiteralAttributePlaceholder() {
        return Pattern.quote(attributePlaceholder);
    }

    @Override
    public String toString() {

        String stringForm = new String();
        
        for (LogAttribute attribute : getLogAttributes()) {
            stringForm += ", " +attribute.toString();
        }
        
        return stringForm;
    }
}
