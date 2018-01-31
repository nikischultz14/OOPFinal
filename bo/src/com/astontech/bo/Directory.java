package com.astontech.bo;

public class Directory {

    private int DirId;
    private String DirName;
    private double DirSize;
    private int NumberOfFiles;
    private String DirPath;


//region Constructors
    public Directory() {

    }

    public Directory(String dirName, double dirSize) {
        DirName = dirName;
        DirSize = dirSize;
    }

    public Directory(int dirId, int numberOfFiles) {
        DirId = dirId;
        NumberOfFiles = numberOfFiles;
    }


    //endregion

    public static void test() {}

//region Getters and Setters
    public int getDirId() {
        return DirId;
    }

    public void setDirId(int dirId) {
        DirId = dirId;
    }

    public String getDirName() {
        return DirName;
    }

    public void setDirName(String dirName) {
        DirName = dirName;
    }

    public double getDirSize() {
        return DirSize;
    }

    public void setDirSize(double dirSize) {
        DirSize = dirSize;
    }

    public int getNumberOfFiles() {
        return NumberOfFiles;
    }

    public void setNumberOfFiles(int numberOfFiles) {
        NumberOfFiles = numberOfFiles;
    }

    public String getDirPath() {
        return DirPath;
    }

    public void setDirPath(String dirPath) {
        DirPath = dirPath;
    }
//endregion


}
