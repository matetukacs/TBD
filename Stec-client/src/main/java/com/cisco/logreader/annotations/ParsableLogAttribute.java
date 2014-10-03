package com.cisco.logreader.annotations;

import com.cisco.logreader.attribute.validation.AttributeFormatValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author matetukacs
 */
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ParsableLogAttribute {
    
    int index();
    
    Class<? extends AttributeFormatValidator> validator();
}
