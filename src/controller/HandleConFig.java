/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.*;
import java.util.*;
import model.Config;

/**
 *
 * @author MINH TUAN
 */
public class HandleConFig {

    public static void readFileConfig(Config config) throws Exception {
        Properties properties = new Properties();

        try (FileInputStream input = new FileInputStream("config.properties")) {
            properties.load(input);

            // Read the properties and update the Config object if they exist.
            String copyFolder = properties.getProperty("COPY_FOLDER");
            if (copyFolder != null && !copyFolder.isEmpty()) {
                config.setCOPY_FOLDER(copyFolder);
            }

            String dataType = properties.getProperty("DATA_TYPE");
            if (dataType != null && !dataType.isEmpty()) {
                config.setCOPY_FOLDER(copyFolder);
            }

            String path = properties.getProperty("PATH");
            if (path != null && !path.isEmpty()) {
                config.setPATH(path);
            }
        } catch (IOException e) {
            // Handle the exception if the config file doesn't exist.
            throw new Exception("Config file not found or unable to read.");
        }

    }

    public static String convertToDoubleBackslashes(String path) {
        return path.replace("\\", "\\\\");
    }

    public static void createFileConfig(Config config) throws IOException {
        try (FileWriter writer = new FileWriter("config.properties")) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter Copy Folder: ");
            String folder = scanner.nextLine();
            String convertedPath = convertToDoubleBackslashes(folder);
            config.setCOPY_FOLDER(convertedPath);
            writer.write("COPY_FOLDER=" + config.getCOPY_FOLDER() + "\n");

            System.out.print("Enter Data Type: ");
            String data = scanner.nextLine();
            config.setDATA_TYPE(data);
            writer.write("DATA_TYPE=" + config.getDATA_TYPE() + "\n");

            System.out.print("Enter Path: ");
            String path = scanner.nextLine();
            config.setPATH(path);
            writer.write("Path=" + config.getPATH() + "\n");
        }
    }

    public static void checkConfig(Config config) throws Exception {
        if (config.getCOPY_FOLDER() == null || config.getCOPY_FOLDER().isEmpty()) {
            throw new Exception("COPY_FOLDER is missing or empty in the configuration.");
        }

        if (config.getPATH() == null || config.getPATH().isEmpty()) {
            throw new Exception("PATH is missing or empty in the configuration.");
        }

        if (config.getDATA_TYPE() == null || config.getDATA_TYPE().isEmpty()) {
            throw new Exception("DATA_TYPE is missing or empty in the configuration.");
        }
    }

    public static List<String> copyFile(Config config) {
        List<String> copiedFiles = new ArrayList<>();

        File sourceFolder = new File(config.getCOPY_FOLDER());
        File destinationFolder = new File(config.getPATH());

        // Check if the source folder exists and is a directory.
        if (!sourceFolder.exists() || !sourceFolder.isDirectory()) {
            System.out.println("Source folder doesn't exist or is not a directory.");
            return copiedFiles;
        }

        // Create the destination folder if it doesn't exist.
        if (!destinationFolder.exists()) {
            destinationFolder.mkdirs();
        }

        // Split the DATA_TYPE string into individual file extensions.
        String[] dataTypes = config.getDATA_TYPE().split(",");

        // Iterate through the source folder to find files matching the specified extensions.
        for (File file : sourceFolder.listFiles()) {
            if (file.isFile()) {
                for (String dataType : dataTypes) {
                    if (file.getName().endsWith(dataType.trim())) {
                        File destinationFile = new File(destinationFolder, file.getName());

                        try {
                            // Copy the file to the destination folder.
                            java.nio.file.Files.copy(file.toPath(), destinationFile.toPath());
                            copiedFiles.add(destinationFile.getAbsolutePath());
                        } catch (IOException e) {
                            System.out.println("Error copying file: " + e.getMessage());
                        }
                    }
                }
            }
        }

        return copiedFiles;
    }

}
