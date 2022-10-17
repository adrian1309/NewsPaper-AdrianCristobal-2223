package dao.jdbc.impl;

import dao.jdbc.DaoReaderJDBC;

import dao.jdbc.connectionsJDBC.DBConnection;
import jakarta.inject.Inject;
import model.Reader;
import utils.QueryStrings;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DaoReaderJDBCImpl implements DaoReaderJDBC {

    private DBConnection db = null;
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet resultSet = null;

    //@Inject
    public DaoReaderJDBCImpl() {
        db = new DBConnection();
    }


    @Override
    public List<Reader> getAllReaders() {
        List<Reader> listReader = new ArrayList();

        try {

            con = db.getConnection();
            stmt = con.prepareStatement(QueryStrings.GET_ALL_READERS);
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                listReader.add(Reader.builder()
                        .id(resultSet.getInt(1))
                        .name_reader(resultSet.getString(2))
                        .birth_reader(resultSet.getDate(3))
                        .build());
            }
        } catch (Exception ex) {
            Logger.getLogger(DaoReaderJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

           db.closeConnection(con);
        }
        return listReader;
    }

    @Override
    public void addReader(Reader reader) {
        try {
            con = db.getConnection();
            stmt = con.prepareStatement(QueryStrings.ADD_READER);
            stmt.setString(1, reader.getName_reader());
            stmt.setDate(2, (Date) reader.getBirth_reader());
            stmt.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DaoReaderJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnection(con);
        }
    }

    @Override
    public void deleteReader(Reader reader) {
        try {
            con = db.getConnection();
            stmt = con.prepareStatement(QueryStrings.DELETE_READER);
            stmt.setInt(1, reader.getId());
            stmt.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DaoReaderJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnection(con);
        }
    }

    @Override
    public void updateReader(Reader reader) {
        try {
            con = db.getConnection();
            stmt = con.prepareStatement(QueryStrings.UPDATE_READER);
            stmt.setString(1, reader.getName_reader());
            stmt.setDate(2, (Date) reader.getBirth_reader());
            stmt.setInt(3, reader.getId());
            stmt.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DaoReaderJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnection(con);
        }
    }
}
