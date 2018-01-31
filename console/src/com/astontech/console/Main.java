package com.astontech.console;


import Common.Helpers.StringHelper;
import com.astontech.dao.DirectoryDAO;
import com.astontech.dao.FileDAO;
import com.astontech.dao.mysql.DirectoryDAOImpl;
import com.astontech.dao.mysql.FileDAOImpl;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;
import com.astontech.bo.File;
import com.astontech.bo.Directory;


public class Main {

    final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        StartMenu();
        MainMenu();
    }


    private static void StartMenu() {

        //Prompt the user for a starting directory
        Scanner reader = new Scanner(System.in);

        System.out.println("Which directory would you like to start in?:");
        String userInput = reader.nextLine();
        java.io.File files = new java.io.File(userInput);

        if (!files.isDirectory()) {
            if (!files.exists())
                System.out.println("There is no such directory!");
            else
                System.out.println("That file does not exist");
        } else {
            System.out.println("Files in directory \"" + files + "\":");
            RecursiveDirectory( files, 1);

        }
    }



    private static void RecursiveDirectory(java.io.File dir, int newId) {

        //Recursively collect data and store in appropriate entities
        try {
            java.io.File[] files = dir.listFiles();
            for (java.io.File file : files) {
                String fileName = file.getName();
                String filePath = file.getPath();
                Long fileSize = file.length();


                if (file.isDirectory()) {
                    //recursion happens here
                    logger.info("directory: " + file.getCanonicalPath());
                        //insert
                        logger.info(fileName);
                        com.astontech.bo.Directory dirInsert = new com.astontech.bo.Directory();
                        dirInsert.setDirName(fileName);
                        dirInsert.setDirSize(fileSize);
                        dirInsert.setNumberOfFiles(file.listFiles().length);
                        dirInsert.setDirPath(filePath);
                        DirectoryDAO directoryDAO = new DirectoryDAOImpl();
                        newId = directoryDAO.insertDirectory(dirInsert);
//                newId++;
                    RecursiveDirectory(file, newId);

                } else {
                    logger.info("file: " + file.getCanonicalPath());
                        //insert
                        logger.info(fileName);
                        com.astontech.bo.File fileInsert = new com.astontech.bo.File();
                        fileInsert.setFileName(fileName);
                        fileInsert.setFileType(StringHelper.getFileExtension(file.getName()));
                        fileInsert.setFileSize(fileSize);
                        fileInsert.setFilePath(filePath);
                        fileInsert.setDirId(newId);
                        FileDAO fileDAO = new FileDAOImpl();
                        fileDAO.insertFile(fileInsert);
                }
            }
        } catch (IOException ioEx) {
            logger.error(ioEx);
        }
    }


    //Display a menu with options
    private static void MainMenu() {
        Scanner option = new Scanner(System.in);
        int choice = 0;
        while (choice != 6) {
            System.out.println("What would you like to do next?");
            System.out.println("1. Display Directory with Most Files.");
            System.out.println("2. Display Directory Largest in Size.");
            System.out.println("3. Display 5 Largest Files in Size.");
            System.out.println("4. Display All Files of a Certain Type.");
            System.out.println("5. Clear the DB and Start Over.");
            System.out.println("6. Exit.");
            System.out.println("Please enter your choice: ");

            choice = option.nextInt();
            FileDAO fileDAO = new FileDAOImpl();
            DirectoryDAO directoryDAO = new DirectoryDAOImpl();

            switch (choice) {
            //region Case 1
                case 1:
                    Connection conn3 = mySqlTest();
                    try {
                        Statement statement = conn3.createStatement();
                        String sql = "select DirId, DirName, NumberOfFiles from Directory ORDER BY NumberOfFiles DESC LIMIT 1";

                        ResultSet rs = statement.executeQuery(sql);
                        while(rs.next()) {
                            int dirId = rs.getInt(1);
                            String dirName = rs.getString(2);
                            int numberOfFiles = rs.getInt(3);

                            System.out.println("DirId: " + dirId + " " + "Directory Name: " + dirName + " " +
                                    "Number Of Files: " + numberOfFiles);
                        }

                        conn3.close();


                    } catch (SQLException sqlEx) {
                        logger.error(sqlEx);
                    }

                    // most files
                    break;
                    //endregion

            //region Case 2
                case 2:
                    Connection conn2 = mySqlTest();
                    try {
                        Statement statement = conn2.createStatement();
                        String sql = "select DirId, DirName, DirSize from Directory ORDER BY DirSize DESC LIMIT 1";

                        ResultSet rs = statement.executeQuery(sql);
                        while(rs.next()) {
                            int dirId = rs.getInt(1);
                            String dirName = rs.getString(2);
                            Double dirSize = rs.getDouble(3);

                            System.out.println("DirId: " + dirId + " " + "Directory Name: " + dirName + " " +
                                    "Directory Size: " + dirSize);
                        }

                        conn2.close();


                    } catch (SQLException sqlEx) {
                        logger.error(sqlEx);
                    }


                   //largest directory in size
                    break;
                //endregion

            //region Case 3
                    case 3:
                    Connection conn = mySqlTest();
                    try {
                        Statement statement = conn.createStatement();
                        String sql = "select FileId, FileName, FileSize from File ORDER BY FileSize DESC LIMIT 5";

                        ResultSet rs = statement.executeQuery(sql);
                        while(rs.next()) {
                            int fileId = rs.getInt(1);
                            String fileName = rs.getString(2);
                            Double fileSize = rs.getDouble(3);

                            System.out.println("FileId: " + fileId + " " + "File Name: " + fileName + " " + "File Size: " + fileSize);
                        }

                        conn.close();


                    } catch (SQLException sqlEx) {
                        logger.error(sqlEx);
                    }

                    //five largest in size
                    break;
                    //endregion

            //region Case 4
                case 4:
                    Scanner type = new Scanner(System.in);
                    System.out.println("Which Type?");
                    String userType = type.nextLine();

                    Connection conn4 = mySqlTest();
                    try {
                        Statement statement = conn4.createStatement();
                        String sql = "select FileId, FileName, FileType from File WHERE FileType LIKE '%" + userType + "%'";

                        ResultSet rs = statement.executeQuery(sql);
                        while(rs.next()) {
                            int fileId = rs.getInt(1);
                            String fileName = rs.getString(2);
                            String fileType = rs.getString(3);

                            System.out.println("FileId: " + fileId + " " + "File Name: " + fileName + " " + "File Type: " + fileType);
                        }

                        conn4.close();


                    } catch (SQLException sqlEx) {
                        logger.error(sqlEx);
                    }



                    //all files of certain type
                    break;

                    //endregion

            // region Case 5
                case 5:
                    fileDAO.truncateFile();

                    directoryDAO.truncateDirectory();


                        System.out.println("Database has been cleared");

                        StartMenu();
                    //db cleared and start over
                    break;
                    //endregion

            //region Case 6
                case 6:
                    System.out.println("Now exiting"); //exit
                    return;

                    //endregion

            //region Default
                default:
                    System.out.println("Invalid option. Please try again");
                    break;

                    //endregion
            }

        }


    }



private static Connection mySqlTest() {

    String dbHost = "localhost";
    String dbName = "OOPFinal";
    String dbUser = "consoleUser";
    String dbPassword = "Test2323";
    String useSSL = "false";
    String procBod = "true";



    try {
        Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException ex) {
        logger.error("MySQL Driver not found! " + ex);
        return null;
    }

    logger.info("MySQL Driver registered.");

    Connection connection = null;

    try{
        connection = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":3306/" + dbName + "?useSSL=" + useSSL + "&noAccessToProcedureBodies=" + procBod, dbUser, dbPassword);
    } catch (SQLException ex) {
        logger.error("Connection failed!" + ex);
        return null;
    }

    if(connection != null) {
        logger.info("Successfully connected to MySQL database");
        return connection;
    } else {
        logger.info("Connection failed!");
        return null;
    }

}

}
