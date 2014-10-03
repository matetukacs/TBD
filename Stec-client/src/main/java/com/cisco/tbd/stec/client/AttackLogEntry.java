/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cisco.tbd.stec.client;

import com.cisco.logreader.logentry.LogEntry;
import com.cisco.logreader.annotations.ParsableLogAttribute;
import com.cisco.logreader.annotations.ParsableLogEntry;
import com.cisco.logreader.attribute.defaults.TextAttribute;
import com.cisco.logreader.attribute.validation.defaults.SimpleTextValidator;

/**
 *
 * @author matetukacs
 */
@ParsableLogEntry(format = "?[**]?[**] [Priority: ?]?} ? -> ?")
public class AttackLogEntry extends LogEntry{

    @ParsableLogAttribute(index = 0, validator = SimpleTextValidator.class) 
    TextAttribute logPart1 = new TextAttribute();
    
    @ParsableLogAttribute(index = 1, validator = SimpleTextValidator.class) 
    TextAttribute descr = new TextAttribute();
    
    @ParsableLogAttribute(index = 2, validator = SimpleTextValidator.class) 
    TextAttribute priorityLevel = new TextAttribute();
    
    @ParsableLogAttribute(index = 3, validator = SimpleTextValidator.class) 
    TextAttribute logPart3 = new TextAttribute();
    
    @ParsableLogAttribute(index = 4, validator = SimpleTextValidator.class)
    TextAttribute attackIp = new TextAttribute();
    
    @ParsableLogAttribute(index = 5, validator = SimpleTextValidator.class) 
    TextAttribute logPart4 = new TextAttribute();
    
    public String getAttackIp() {
        return attackIp.toString();
    }

    public String getPriorityLevel() {
        return priorityLevel.toString();
    }
    
    public String getDescr() {
        return descr.toString();
    }
}
