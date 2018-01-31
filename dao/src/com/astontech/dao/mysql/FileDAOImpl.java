package com.astontech.dao.mysql;


import com.astontech.bo.File;
import com.astontech.dao.FileDAO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FileDAOImpl extends MySQL implements FileDAO {

    @Override
    public File getFileById(int fileId) {
        Connect();
        File file = null;
        try {
            String sp = Procedures.GetFile;
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, GET_BY_ID);
            cStmt.setInt(2, fileId);
            ResultSet rs = cStmt.executeQuery();

            if(rs.next()) {
                file = HydrateObject(rs);
            }
        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }
        return file;
    }

    @Override
    public List<File> getFileList() {
        Connect();
        List<File> fileList = new ArrayList<File>();
        try {
            String sp = Procedures.GetFile;
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, GET_COLLECTION);
            cStmt.setInt(2, 0);
            ResultSet rs = cStmt.executeQuery();

            while(rs.next()) {
                fileList.add(HydrateObject(rs));
            }
        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }
        return fileList;
    }

    @Override
    public int insertFile(File file) {
        Connect();
        int id = 0;
        try {
            String sp = Procedures.ExecFile;
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, INSERT);
            cStmt.setInt(2, 0);
            cStmt.setString(3, file.getFileName());
            cStmt.setString(4, file.getFileType());
            cStmt.setDouble(5, file.getFileSize());
            cStmt.setString(6, file.getFilePath());
            cStmt.setInt(7, file.getDirId());

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
    public boolean updateFile(File file) {
        Connect();
        int id = 0;
        try {
            String sp = Procedures.ExecFile;
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, UPDATE);
            cStmt.setInt(2, file.getFileId());
            cStmt.setString(3, file.getFileName());
            cStmt.setString(4, file.getFileType());
            cStmt.setDouble(5, file.getFileSize());
            cStmt.setString(6, file.getFilePath());
            cStmt.setInt(7, file.getDirId());

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
    public boolean deleteFile(int fileId) {
        Connect();
        int id = 0;
        try {
            String sp = Procedures.ExecFile;
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, DELETE);
            cStmt.setInt(2, fileId);
            cStmt.setString(3, "");
            cStmt.setString(4, "");
            cStmt.setDouble(5, 0);
            cStmt.setString(6, "");
            cStmt.setInt(7, 0);

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
    public void truncateFile() {
        Connect();

        try {
            String sp = Procedures.ExecFile;
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, TRUNCATE);
            cStmt.setInt(2, 0);
            cStmt.setString(3, "");
            cStmt.setString(4, "");
            cStmt.setDouble(5, 0);
            cStmt.setString(6, "");
            cStmt.setInt(7, 0);

            cStmt.executeQuery();

        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }

    }

    private static File HydrateObject(ResultSet rs) throws SQLException {

        File file = new File();
        file.setFileId(rs.getInt(1));
        file.setFileName(rs.getString(2));
        file.setFileType(rs.getString(3));
        file.setFileSize(rs.getDouble(4));
        file.setFilePath(rs.getString(5));
        file.setDirId(rs.getInt(6));

        return file;
    }


}
