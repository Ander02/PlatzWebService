package com.platz.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author 15153766
 */
public class ContarLinhas {

    public static void main(String[] args) throws FileNotFoundException {
        //Verificando o número de linhas no projeto
        final String folderPath = "C:\\Users\\15153766\\Documents\\NetBeansProjects\\PlatzWebService\\PlatzWebService\\src";

        long totalLineCount = 0;
        final List<File> folderList = new LinkedList<>();
        folderList.add(new File(folderPath));
        while (!folderList.isEmpty()) {
            final File folder = folderList.remove(0);
            if (folder.isDirectory() && folder.exists()) {
                System.out.println("Scanning " + folder.getName());
                final File[] fileList = folder.listFiles();
                for (final File file : fileList) {
                    if (file.isDirectory()) {
                        folderList.add(file);
                    } else if (file.getName().endsWith(".java")
                            || file.getName().endsWith(".xml")
                            || file.getName().endsWith(".css")
                            || file.getName().endsWith(".js")
                            || file.getName().endsWith(".jsp")) {
                        long lineCount = 0;
                        final Scanner scanner = new Scanner(file);
                        while (scanner.hasNextLine()) {
                            scanner.nextLine();
                            lineCount++;
                        }
                        totalLineCount += lineCount;
                        final String lineCountString;
                        if (lineCount > 99999) {
                            lineCountString = "" + lineCount;
                        } else {
                            final String temp = ("     " + lineCount);
                            lineCountString = temp.substring(temp.length() - 5);
                        }
                        System.out.println(lineCountString + " lines in " + file.getName());
                    }
                }
            }
        }
        System.out.println("Scan Complete: " + totalLineCount + " lines total");
    }
}
