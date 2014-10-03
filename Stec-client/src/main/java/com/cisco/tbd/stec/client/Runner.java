/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cisco.tbd.stec.client;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author matetukacs
 */
public class Runner {

    public static String currentLastLine = "";
    public static void main(String[] args) throws Exception{
        
//        if (args.length == 0) {
//            System.err.println("Specify log file location");
//        }
//        
//        File file = new File(args[0]);
        
        
        File file = new File("./../log.txt");
        
        Scanner scanner = new Scanner(file);
        
        while (true) {
            System.err.println(scanner.nextLine());
        }
        //System.err.println("Specify output folder in mtukuacs/output");
    }
}
