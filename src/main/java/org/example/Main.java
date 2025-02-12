package org.example;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.awt.datatransfer.Clipboard;

public class Main {
    static int innerDotCounter = 0;

    public static void main(String[] args) {
        String dirPath = System.getProperty("user.dir");

        File directory = new File(dirPath);

        File[] files = directory.listFiles();

        if (files != null) {
            StringBuilder fileNamesBuilder = new StringBuilder();
            int dotCounter;

            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                dotCounter = 0;

                if (fileName.contains("FileList")) {
                    continue;
                }

                for (int k = 0; k < fileName.length(); k++) {
                    if (fileName.charAt(k) == '.') {
                        dotCounter++;
                    }

                    if (k == 0 && fileName.charAt(k) == '.') {
                        fileNamesBuilder.append(fileName);
                    }

                }

                if (dotCounter == 1) {
                    fileNamesBuilder.append(fileName.split("\\.")[0]).append("\n");
                } else if (dotCounter > 1) {
                    for (int j = 0; j < fileName.length(); j++) {
                        if (innerDotCounter(fileName) == dotCounter) {
                            fileNamesBuilder.append(fileName.split("\\.")[j]).append("\n");
                            innerDotCounter = 0;
                            break;
                        }

                        fileNamesBuilder.append(fileName.split("\\.")[j]).append(".");
                    }
                }
            }

            String fileNames = fileNamesBuilder.toString();

            //Copying text to clipboard
            StringSelection stringSelection = new StringSelection(fileNames);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        }
    }
    
    public static int innerDotCounter(String fileName) {
        for (int i = 0; i < fileName.length(); i++) {
            if (fileName.charAt(i) == '.') {
                innerDotCounter++;
                break;
            }
        }
        return innerDotCounter;
    }
}