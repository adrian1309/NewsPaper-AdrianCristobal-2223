package dao.jdbc.impl;

import dao.jdbc.DaoReaderJDBC;

import dao.jdbc.connectionsJDBC.DBConnection;

import io.vavr.control.Either;
import model.Newspaper;
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
    public Either<Integer, List<Reader>> getAll() {
        List<Reader> listReader = new ArrayList();
        int result = -1;
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
            return Either.right(listReader);
        } catch (Exception ex) {
            Logger.getLogger(DaoReaderJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
            return Either.left(result);
        } finally {

           db.closeConnection(con);
        }
    }

    @Override
    public int add(Reader reader) {
        int result = 0;
        try {
            con = db.getConnection();
            stmt = con.prepareStatement(QueryStrings.ADD_READER);
            stmt.setString(1, reader.getName_reader());
            stmt.setDate(2, (Date) reader.getBirth_reader());
            stmt.executeUpdate();
            result = 1;
        } catch (Exception ex) {
            Logger.getLogger(DaoReaderJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
            result = -1;
        } finally {
            db.closeConnection(con);
        }
        return result;
    }

    @Override
    public int delete(Reader reader) {
        int result = 0;
        try {
            con = db.getConnection();
            stmt = con.prepareStatement(QueryStrings.DELETE_READER);
            stmt.setInt(1, reader.getId());
            stmt.executeUpdate();
            result = 1;
        } catch (Exception ex) {
            Logger.getLogger(DaoReaderJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
            result = -1;
        } finally {
            db.closeConnection(con);
        }
        return result;
    }

    @Override
    public int update(Reader reader) {
        int result = 0;
        try {
            con = db.getConnection();
            stmt = con.prepareStatement(QueryStrings.UPDATE_READER);
            stmt.setString(1, reader.getName_reader());
            stmt.setDate(2, (Date) reader.getBirth_reader());
            stmt.setInt(3, reader.getId());
            stmt.executeUpdate();
            result = 1;
        } catch (Exception ex) {
            Logger.getLogger(DaoReaderJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
            result = -1;
        } finally {
            db.closeConnection(con);
        }
        return result;
    }

    @Override
    public List<Reader> getReadersSubscribeNewspaper(Newspaper newspaper) {
        return null;
    }

    @Override
    public List<Reader> getReadersOfArticleType(String type) {
        return null;
    }

    @Override
    public void addReadArticle(int idReader, int idArticle, int rating) {

    }

    @Override
    public String getOlderSubscribersOfNewspaper(Newspaper selectedItem) {
        return null;
    }

    @Override
    public void addSubscriber(int idNewspaper, int idReader) {

    }

}
