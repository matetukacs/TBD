/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cisco.tbd.stec.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author matetukacs
 */
public class FileUtils {

    public static int getFileLineCount(String pathToFile) throws FileNotFoundException {
        File file = new File(pathToFile);
        Scanner scanner = new Scanner(file);

        int lineCount = 0;

        while (scanner.hasNextLine()) {
            scanner.nextLine();
            lineCount++;
        }
        
        scanner.close();
        return lineCount;
    }

    public static String getLastLineOfFile(String pathToFile) throws FileNotFoundException {
        
        File file = new File(pathToFile);
        Scanner scanner = new Scanner(file);

        String currentLine = new String();

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
        }
        scanner.close();
        return currentLine;
    }
    
    public static ArrayList<String> getLinesOfFile(String pathToFile) throws FileNotFoundException {
        
        File file = new File(pathToFile);
        
        Scanner scanner = new Scanner(file);
        
        ArrayList lines = new ArrayList<>();

        String currentLine = new String();

        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        
        scanner.close();

        return lines;
    }
    
    public static boolean removeFile(String pathToFile) {
        File file = new File(pathToFile);
        
        return file.delete();
    }
    
    public static void createFileWithText(String pathToFile, String text) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(pathToFile, "UTF-8");
        writer.println(text);
        writer.close();
    }
}
