package org.example;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.awt.datatransfer.Clipboard;

public class Main {
    public static void main(String[] args) {
        String dirPath = System.getProperty("user.dir");

        File directory = new File(dirPath);

        File[] files = directory.listFiles();

        if (files != null) {
            StringBuilder fileNamesBuilder = new StringBuilder();

            for (int i = 0; i < files.length; i++) {

                if (files[i].getName().contains("FileList")) {
                    continue;
                }

                fileNamesBuilder.append(files[i].getName().split("\\.")[0]).append("\n");
            }

            String fileNames = fileNamesBuilder.toString();

            //Copying text to clipboard
            StringSelection stringSelection = new StringSelection(fileNames);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);

        }
    }
}