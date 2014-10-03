package com.cisco.logreader.attribute.defaults;

import com.cisco.logreader.attribute.LogAttribute;
import com.cisco.logreader.attribute.LogAttribute;

/**
 *
 * @author matetukacs
 */
public class TextAttribute implements LogAttribute {

    private String value;
    @Override
    public void set(String value) {
        this.value = value;
    }
 
    @Override
    public String toString() {
        return value;
    }
}
