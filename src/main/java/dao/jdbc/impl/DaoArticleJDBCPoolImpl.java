package dao.jdbc.impl;

import dao.jdbc.DaoArticleJDBC;
import dao.jdbc.connectionsJDBC.DBConnectionPool;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Article;
import model.Reader;
import utils.QueryStrings;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoArticleJDBCPoolImpl implements DaoArticleJDBC {

    private Connection con = null;
    private Statement stmt = null;
    private ResultSet resultSet = null;
    private PreparedStatement pstmt = null;
    private PreparedStatement pstmt2 = null;

    private DBConnectionPool pool;

    @Inject
    public DaoArticleJDBCPoolImpl(DBConnectionPool pool) {
        this.pool = pool;
    }


    @Override
    public Either<Integer, List<Article>> getAll() {
        List<Article> listArticle = new ArrayList<>();
        int result = -1;
        try {
            con = pool.getConnection();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            resultSet = stmt.executeQuery(QueryStrings.GET_ALL_ARTICLES);

            while (resultSet.next()) {
                listArticle.add(Article.builder()
                        .id(resultSet.getInt(1))
                        .name_article(resultSet.getString(2))
                        .id_type(resultSet.getInt(3))
                        .id_newspaper(resultSet.getInt(4))
                        .build());
            }
            return Either.right(listArticle);
        } catch (SQLException ex) {
            Logger.getLogger(DaoArticleJDBCPoolImpl.class.getName()).log(Level.SEVERE, null, ex);
            return Either.left(result);
        }finally {
            pool.closeConnection(con);
        }
    }

    @Override
    public String getDescriptionAndNumberOfReadersOfEachArticle(Article article) {
        String descriptionAndNumberReaders = "";
        try {
            con = pool.getConnection();
            pstmt = con.prepareStatement(QueryStrings.GET_DESCRIPTION_AND_NUMBER_READERS_OF_EACH_ARTICLE);
            pstmt.setInt(1, article.getId());
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                String description = resultSet.getString(1);
                int numberReaders = resultSet.getInt(2);
                descriptionAndNumberReaders = "Type: " + description + "\n" + "Number of readers: " + numberReaders;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoArticleJDBCPoolImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.closeConnection(con);
        }
        return descriptionAndNumberReaders;
    }
}

