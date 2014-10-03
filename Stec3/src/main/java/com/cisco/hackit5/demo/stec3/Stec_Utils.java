/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cisco.hackit5.demo.stec3;
import java.security.SecureRandom;
import java.util.Random;

/**
 *
 * @author ttsatury
 */
public class Stec_Utils {
    private SecureRandom random = new SecureRandom();
    static String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
    private static Random rng = new Random();
    static String generateRandomString(int in_length)
    {
        char[] text = new char[in_length];
        for (int i = 0; i < in_length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }
}
