package com.astontech.bo;

public class File {

    private int FileId;
    private String FileName;
    private String FileType;
    private double FileSize;
    private String FilePath;
    private int DirId;


//region Constructors
    public File() {

    }

//endregion




//region Getters and Setters
    public int getFileId() {
        return FileId;
    }

    public void setFileId(int fileId) {
        FileId = fileId;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getFileType() {
        return FileType;
    }

    public void setFileType(String fileType) {
        FileType = fileType;
    }

    public double getFileSize() {
        return FileSize;
    }

    public void setFileSize(double fileSize) {
        FileSize = fileSize;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }

    public int getDirId() {
        return DirId;
    }

    public void setDirId(int dirId) {
        DirId = dirId;
    }
//endregion


}
