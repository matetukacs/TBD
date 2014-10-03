package com.cisco.logreader.utils;

import com.cisco.logreader.annotations.ParsableLogAttribute;
import com.cisco.logreader.annotations.WrongAnnotationException;
import com.cisco.logreader.attribute.LogAttribute;
import com.cisco.logreader.attribute.validation.AttributeFormatValidator;
import java.lang.reflect.Field;

/**
 *
 * @author matetukacs
 */
public class LogAttributeUtils {
    
    protected static boolean textIsConvertableToLogAttribute(String attributeText, Field attributeField) {
        
        AttributeFormatValidator validator = getLogAttributeValidator(attributeField);
        
        return validator.isValidFormat(attributeText);
    }
    
    protected static AttributeFormatValidator getLogAttributeValidator(Field attributeField) {

        try {
            Class<? extends AttributeFormatValidator> validatorType = getLogAttributeAnnotation(attributeField).validator();
            
            return validatorType.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new IllegalArgumentException("Validator for " + attributeField.getName() + " field cannot be instantiated." + ex.getMessage());
        }
    }
    
    private static ParsableLogAttribute getLogAttributeAnnotation(Field attributeField) {

        return attributeField.getAnnotation(ParsableLogAttribute.class);
    }
    
    public static boolean fieldIsLogAttribute(Field field) {
        if (field.isAnnotationPresent(ParsableLogAttribute.class)) {
            validateLogAttributeField(field);
            return true;
        }
        return false;
    }
    
    private static void validateLogAttributeField(Field attributeField) {
        if (!LogAttribute.class.isAssignableFrom(attributeField.getType())) {
            throw new WrongAnnotationException("Field annotated with @ParsableLogAttribute annotation is invalid. Does not extend LogAttribute interface.");
        }
    }
    
    public static int getAttributeIndex(Field attributeField) {
        return getLogAttributeAnnotation(attributeField).index();
    }
}