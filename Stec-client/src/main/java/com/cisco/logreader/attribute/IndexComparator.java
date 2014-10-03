package com.cisco.logreader.attribute;

import com.cisco.logreader.annotations.WrongAnnotationException;
import com.cisco.logreader.utils.LogAttributeUtils;
import java.lang.reflect.Field;
import java.util.Comparator;

/**
 *
 * @author matetukacs
 */
public class IndexComparator implements Comparator<Field>{

    @Override
    public int compare(Field attribute1, Field attribute2) {
        
        if (LogAttributeUtils.fieldIsLogAttribute(attribute1) && LogAttributeUtils.fieldIsLogAttribute(attribute2)) {
            int index1 = LogAttributeUtils.getAttributeIndex(attribute1);
            int index2 = LogAttributeUtils.getAttributeIndex(attribute2);
            
            if (index1 == index2) {
                throw new WrongAnnotationException("The two LogAttribute fields being compared are assinged to the same attribute index");
            }
            if (index1 < index2) {
                return -1;
            }
            return 1;
        }
        return 0;
    }
}
