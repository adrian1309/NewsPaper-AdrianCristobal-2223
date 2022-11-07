package dao.jdbc.impl;

import dao.jdbc.DaoNewspaperJDBC;
import dao.jdbc.connectionsJDBC.DBConnectionPool;
import io.vavr.control.Either;
import jakarta.errores.ApiError;
import jakarta.inject.Inject;
import model.Newspaper;
import model.Reader;
import utils.QueryStrings;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoNewspaperJDBCPoolImpl implements DaoNewspaperJDBC {

    private Connection con = null;
    private Statement stmt = null;
    private ResultSet resultSet = null;
    private PreparedStatement pstmt = null;
    private PreparedStatement pstmt2 = null;

    private DBConnectionPool pool;

    @Inject
    public DaoNewspaperJDBCPoolImpl(DBConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public Either<Integer, List<Newspaper>> getAll() {
        List<Newspaper> listNewspaper = new ArrayList<>();
        int result = -1;
        try {
            con = pool.getConnection();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            resultSet = stmt.executeQuery(QueryStrings.GET_ALL_NEWSPAPERS);

            while (resultSet.next()) {
                listNewspaper.add(Newspaper.builder()
                        .id(resultSet.getInt(1))
                        .name_newspaper(resultSet.getString(2))
                        .release_date(resultSet.getDate(3).toLocalDate())
                        .build());
            }
            return Either.right(listNewspaper);
        } catch (SQLException ex) {
            Logger.getLogger(DaoNewspaperJDBCPoolImpl.class.getName()).log(Level.SEVERE, null, ex);
            return Either.left(result);
        }finally {
            pool.closeConnection(con);
        }
    }

    @Override
    public Either<ApiError, Newspaper> getOne(String id) {
        try {
            Newspaper newspaper = null;
            con = pool.getConnection();
            pstmt = con.prepareStatement(QueryStrings.GET_ONE_NEWSPAPER_BY_ID);
            pstmt.setInt(1, Integer.parseInt(id));
            resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                newspaper = Newspaper.builder()
                        .id(resultSet.getInt(1))
                        .name_newspaper(resultSet.getString(2))
                        .release_date(resultSet.getDate(3).toLocalDate())
                        .build();
            }

            if (newspaper == null) {
                return Either.left(new ApiError("Newspaper not found"));
            }else {
                return Either.right(newspaper);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoArticleJDBCPoolImpl.class.getName()).log(Level.SEVERE, null, ex);
            return Either.left(new ApiError("Error en la base de datos"));

        } finally {
            pool.closeConnection(con);
        }
    }
}
