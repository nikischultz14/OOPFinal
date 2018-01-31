package com.astontech.dao;

import com.astontech.bo.Directory;

import java.util.List;

public interface DirectoryDAO {

    //get methods
    public Directory getDirectoryById(int dirId);
    public List<Directory> getDirectoryList();


    //execute methods
    public int insertDirectory(Directory directory);
    public boolean updateDirectory(Directory directory);
    public boolean deleteDirectory(int dirId);
    public void truncateDirectory();
}
