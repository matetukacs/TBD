package com.cisco.logreader.annotations;

/**
 *
 * @author matetukacs
 */
public class MissingAnnotationException extends RuntimeException{
    
    public MissingAnnotationException(String message) {
        super(message);
    }
}
