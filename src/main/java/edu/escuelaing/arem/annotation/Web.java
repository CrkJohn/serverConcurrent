package edu.escuelaing.arem.annotation;

import java.lang.annotation.*;



/**
 *  Web represent a new label for methods
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Web{
    String value();   
}