package com.cisco.logreader.logentry;

import com.cisco.logreader.annotations.ParsableLogAttribute;
import com.cisco.logreader.annotations.ParsableLogEntry;
import com.cisco.logreader.attribute.defaults.TextAttribute;
import com.cisco.logreader.attribute.validation.defaults.SimpleTextValidator;



/**
 *
 * @author matetukacs
 */

@ParsableLogEntry(format = "?")
public class SimpleTextLogEntry extends LogEntry{

    @ParsableLogAttribute(index = 0, validator = SimpleTextValidator.class)
    private TextAttribute text = new TextAttribute();
    
}
