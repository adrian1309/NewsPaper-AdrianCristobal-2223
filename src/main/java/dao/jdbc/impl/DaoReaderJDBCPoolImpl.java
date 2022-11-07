package dao.jdbc.impl;

import dao.jdbc.DaoReaderJDBC;
import dao.jdbc.connectionsJDBC.DBConnectionPool;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Newspaper;
import model.Reader;
import utils.QueryStrings;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoReaderJDBCPoolImpl implements DaoReaderJDBC {

    private Connection con = null;
    private Statement stmt = null;
    private ResultSet resultSet = null;
    private PreparedStatement pstmt = null;
    private PreparedStatement pstmt2 = null;
    private PreparedStatement pstmt3 = null;

    private DBConnectionPool pool;

    @Inject
    public DaoReaderJDBCPoolImpl(DBConnectionPool pool) {
        this.pool = pool;
    }



    @Override
    public Either<Integer, List<Reader>> getAll() {
        List<Reader> listReader = new ArrayList<>();
        int result = -1;
        try {
            con = pool.getConnection();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            resultSet = stmt.executeQuery(QueryStrings.GET_ALL_READERS);

            while (resultSet.next()) {
                listReader.add(Reader.builder()
                        .id(resultSet.getInt(1))
                        .name_reader(resultSet.getString(2))
                        .birth_reader(resultSet.getDate(3))
                        .build());
            }
            return Either.right(listReader);
        } catch (SQLException ex) {
            Logger.getLogger(DaoReaderJDBCPoolImpl.class.getName()).log(Level.SEVERE, null, ex);
            return Either.left(result);
        }finally {
            pool.closeConnection(con);
        }
    }

    @Override
    public int add(Reader reader) {
        int result = 0;
        try {
            con = pool.getConnection();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pstmt = con.prepareStatement(QueryStrings.ADD_READER);
            pstmt.setString(1, reader.getName_reader());
            pstmt.setDate(2, (Date) reader.getBirth_reader());
            pstmt.executeUpdate();
            result = 1;
        } catch (Exception ex) {
            Logger.getLogger(DaoReaderJDBCPoolImpl.class.getName()).log(Level.SEVERE, null, ex);
            result = -1;
        } finally {
            pool.closeConnection(con);
        }
        return result;
    }

    @Override
    public int delete(Reader reader) {
        int result = 0;
        try {
            con = pool.getConnection();
            con.setAutoCommit(false);
            pstmt3 = con.prepareStatement(QueryStrings.DELETE_READARTICLE_BY_ID_READER);
            pstmt3.setInt(1, reader.getId());
            pstmt3.executeUpdate();
            pstmt2 = con.prepareStatement(QueryStrings.DELETE_SUBSCRIBE_BY_ID_READER);
            pstmt2.setInt(1, reader.getId());
            pstmt2.executeUpdate();
            pstmt = con.prepareStatement(QueryStrings.DELETE_READER);
            pstmt.setInt(1, reader.getId());
            pstmt.executeUpdate();
            con.commit();
        } catch (Exception ex) {
            Logger.getLogger(DaoReaderJDBCPoolImpl.class.getName()).log(Level.SEVERE, null, ex);
            result = -1;
        } finally {
            pool.closeConnection(con);
        }
        return result;
    }

    @Override
    public int update(Reader reader) {
        int result = 0;
        try {
            con = pool.getConnection();
            pstmt = con.prepareStatement(QueryStrings.UPDATE_READER);
            pstmt.setString(1, reader.getName_reader());
            pstmt.setDate(2, (Date) reader.getBirth_reader());
            pstmt.setInt(3, reader.getId());
            pstmt.executeUpdate();
            result = 1;
        } catch (Exception ex) {
            Logger.getLogger(DaoReaderJDBCPoolImpl.class.getName()).log(Level.SEVERE, null, ex);
            result = -1;
        } finally {
            pool.closeConnection(con);
        }
        return result;
    }

    @Override
    public List<Reader> getReadersSubscribeNewspaper(Newspaper newspaper) {
        List<Reader> listReader = new ArrayList<>();
        try {
            con = pool.getConnection();
            pstmt = con.prepareStatement(QueryStrings.GET_READERS_BY_NEWSPAPER);
            pstmt.setInt(1, newspaper.getId());
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                listReader.add(Reader.builder()
                        .id(resultSet.getInt(1))
                        .name_reader(resultSet.getString(2))
                        .birth_reader(resultSet.getDate(3))
                        .build());
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoReaderJDBCPoolImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.closeConnection(con);
        }
        return listReader;
    }

    @Override
    public List<Reader> getReadersOfArticleType(String type) {
        List<Reader> listReader = new ArrayList<>();
        try {
            con = pool.getConnection();
            pstmt = con.prepareStatement(QueryStrings.GET_READERS_OF_ARTICLE_TYPE);
            pstmt.setString(1, type);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                listReader.add(Reader.builder()
                        .id(resultSet.getInt(1))
                        .name_reader(resultSet.getString(2))
                        .birth_reader(resultSet.getDate(3))
                        .build());
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoReaderJDBCPoolImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.closeConnection(con);
        }
        return listReader;
    }

    @Override
    public void addReadArticle(int idReader, int idArticle, int ranking) {
        try {
            con = pool.getConnection();
            pstmt = con.prepareStatement(QueryStrings.ADD_READ_ARTICLE);
            pstmt.setInt(1, idReader);
            pstmt.setInt(2, idArticle);
            pstmt.setInt(3, ranking);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoReaderJDBCPoolImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.closeConnection(con);
        }
    }

    @Override
    public String getOlderSubscribersOfNewspaper(Newspaper newspaper) {
        StringBuilder result = new StringBuilder();
        try {
            con = pool.getConnection();
            pstmt = con.prepareStatement(QueryStrings.GET_NAME_OF_100_OLDEST_SUBSCRIPTORS_OF_A_NEWSPAPER);
            pstmt.setInt(1, newspaper.getId());
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                result.append(name).append("\n");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoReaderJDBCPoolImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.closeConnection(con);
        }
        return result.toString();
    }

    @Override
    public void addSubscriber(int idNewspaper, int idReader) {
        try {
            con = pool.getConnection();
            pstmt = con.prepareStatement(QueryStrings.ADD_SUBSCRIBER);
            pstmt.setInt(1, idNewspaper);
            pstmt.setInt(2, idReader);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoReaderJDBCPoolImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.closeConnection(con);
        }
    }


}
