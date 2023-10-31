/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controller;

import java.io.IOException;
import java.util.List;
import model.Config;

/**
 *
 * @author MINH TUAN
 */
public class App {

    HandleConFig h = new HandleConFig();

    public void run() {
        Config config = new Config();

        // Function 2: Create file config if not existed
        if (config.getCOPY_FOLDER() == null || config.getPATH() == null || config.getDATA_TYPE() == null) {
            try {
                h.createFileConfig(config);
            } catch (IOException e) {
                System.out.println("An error occurred while creating the config file: " + e.getMessage());
                return;
            }
        }

        try {
            // Function 1: Read file config.properties
            h.readFileConfig(config);
        } catch (Exception e) {
            System.out.println("An error occurred while reading the config file: " + e.getMessage());
            return;
        }

        // Function 3: Verify file config
        try {
            h.checkConfig(config);
        } catch (Exception e) {
            System.out.println("An error occurred while checking the config: " + e.getMessage());
            return;
        }

        // Function 4: Perform copy task if the file config is valid
        List<String> copiedFiles = h.copyFile(config);
        if (copiedFiles.size() > 0) {
            System.out.println("Copied files:");
            for (String file : copiedFiles) {
                System.out.println(file);
            }
        } else {
            System.out.println("No files were copied.");
        }
    }
}
