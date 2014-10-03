/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cisco.hackit5.demo.stec3;
import org.apache.log4j.Logger;

/**
 *
 * @author ttsatury
 */
public class Cookies {
    static String kookie_salt = "aldjalsjdlkasjdlkasdlkjasdlkjsd123123123";
    static Logger logger = Logger.getLogger(
                      Cookies.class.getName());
    /**
     * 
     * @param in_cookie
     * @param in_email
     * @return 0, if cookie exists or 1 if not
     */
    static int checkForCookies(String in_cookie, String in_email)
    {
        logger.trace("checkForCookies called with in_cookie = " + in_cookie + "&in_email = " + in_email);
        // TODO
        return 0;
    }
    /**
     * 
     * @param in_email
     * @return 0 if success, 1 if failed
     */
    static int generateCookies(String in_email)
    {
        logger.trace("generateCookies called with email" + in_email);
        // TODO
        return 0;
    }
}
