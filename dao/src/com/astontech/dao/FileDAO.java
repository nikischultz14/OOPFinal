package com.astontech.dao;


import com.astontech.bo.File;

import java.util.List;

public interface FileDAO {

    //get methods
    public File getFileById(int fileId);
    public List<File> getFileList();

    //execute methods
    public int insertFile(File file);
    public boolean updateFile(File file);
    public boolean deleteFile(int fileId);
    public void truncateFile();


}
