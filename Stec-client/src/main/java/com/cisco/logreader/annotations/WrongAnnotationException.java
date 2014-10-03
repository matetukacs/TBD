package com.cisco.logreader.annotations;

/**
 *
 * @author matetukacs
 */
public class WrongAnnotationException extends RuntimeException{
    
    public WrongAnnotationException(String message) {
        super(message);
    }
}
