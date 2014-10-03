package com.cisco.logreader.attribute.validation.defaults;

import com.cisco.logreader.attribute.validation.AttributeFormatValidator;

/**
 *
 * @author matetukacs
 */
public class SimpleTextValidator implements AttributeFormatValidator{

    @Override
    public boolean isValidFormat(String format) {
        return true;
    }
}
