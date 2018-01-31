package com.astontech.dao.mysql;

import com.astontech.bo.Directory;
import com.astontech.dao.DirectoryDAO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DirectoryDAOImpl extends MySQL implements DirectoryDAO {


    @Override
    public Directory getDirectoryById(int dirId) {
        Connect();
        Directory directory = null;
        try {
            String sp = Procedures.GetDirectory;
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, GET_BY_ID);
            cStmt.setInt(2, dirId);
            ResultSet rs = cStmt.executeQuery();

            if(rs.next()) {
                directory = HydrateObject(rs);
            }
        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }
        return directory;
    }

    @Override
    public List<Directory> getDirectoryList() {
        Connect();
        List<Directory> directoryList = new ArrayList<Directory>();
        try {
            String sp = Procedures.GetDirectory;
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, GET_COLLECTION);
            cStmt.setInt(2, 0);
            ResultSet rs = cStmt.executeQuery();

            while(rs.next()) {
                directoryList.add(HydrateObject(rs));
            }
        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }
        return directoryList;
    }

    @Override
    public int insertDirectory(Directory directory) {
        Connect();
        int id = 0;
        try {
            String sp = Procedures.ExecDirectory;
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, INSERT);
            cStmt.setInt(2, 0);
            cStmt.setString(3, directory.getDirName());
            cStmt.setDouble(4, directory.getDirSize());
            cStmt.setInt(5, directory.getNumberOfFiles());
            cStmt.setString(6, directory.getDirPath());

            ResultSet rs = cStmt.executeQuery();
            if(rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }
        return id;
    }

    @Override
    public boolean updateDirectory(Directory directory) {
        Connect();
        int id = 0;
        try {
            String sp = Procedures.ExecDirectory;
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, UPDATE);
            cStmt.setInt(2, directory.getDirId());
            cStmt.setString(3, directory.getDirName());
            cStmt.setDouble(4, directory.getDirSize());
            cStmt.setInt(5, directory.getNumberOfFiles());
            cStmt.setString(6, directory.getDirPath());

            ResultSet rs = cStmt.executeQuery();
            if(rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }
        return id > 0;
    }

    @Override
    public boolean deleteDirectory(int dirId) {
        Connect();
        int id = 0;
        try {
            String sp = Procedures.ExecDirectory;
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, DELETE);
            cStmt.setInt(2, 0);
            cStmt.setString(3, "");
            cStmt.setDouble(4, 0);
            cStmt.setInt(5, 0);
            cStmt.setString(6, "");

            ResultSet rs = cStmt.executeQuery();
            if(rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }
        return id > 0;
    }


    @Override
    public void truncateDirectory() {
        Connect();

        try {
            String sp = Procedures.ExecDirectory;
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, TRUNCATE);
            cStmt.setInt(2, 0);
            cStmt.setString(3, "");
            cStmt.setDouble(4, 0);
            cStmt.setInt(5, 0);
            cStmt.setString(6, "");

            cStmt.executeQuery();

        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }

    }

    private static Directory HydrateObject(ResultSet rs) throws SQLException {
        Directory directory = new Directory();

        directory.setDirId(rs.getInt(1));
        directory.setDirName(rs.getString(2));
        directory.setDirSize(rs.getDouble(3));
        directory.setNumberOfFiles(rs.getInt(4));
        directory.setDirPath(rs.getString(5));

        return directory;
    }






}





