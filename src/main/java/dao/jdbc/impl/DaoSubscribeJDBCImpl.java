package dao.jdbc.impl;

import dao.jdbc.DaoSubscribeJDBC;
import dao.jdbc.connectionsJDBC.DBConnection;
import model.Reader;
import model.Subscribe;
import utils.QueryStrings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoSubscribeJDBCImpl implements DaoSubscribeJDBC {

    private DBConnection db = null;
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet resultSet = null;

    //@Inject
    public DaoSubscribeJDBCImpl() {
        db = new DBConnection();
    }
    @Override
    public void addSubscribe(Subscribe subscribe) {

    }

    @Override
    public List<Subscribe> getAllSubscribes() {
        List<Subscribe> listSubscribe = new ArrayList();

        try {

            con = db.getConnection();
            stmt = con.prepareStatement(QueryStrings.GET_ALL_SUBSCRIBES);
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                listSubscribe.add(Subscribe.builder()
                        .id_newspaper(resultSet.getInt(1))
                        .id_reader(resultSet.getInt(2))
                        .start_date(resultSet.getDate(3))
                        .end_date(resultSet.getDate(3))
                        .build());
            }
        } catch (Exception ex) {
            Logger.getLogger(DaoSubscribeJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            db.closeConnection(con);
        }
        return listSubscribe;
    }

    @Override
    public void deleteSubscribe(Subscribe subscribe) {
        try {
            con = db.getConnection();
            stmt = con.prepareStatement(QueryStrings.DELETE_SUBSCRIBE);
            stmt.setInt(1, subscribe.getId_newspaper());
            stmt.setInt(2, subscribe.getId_reader());
            stmt.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DaoSubscribeJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnection(con);
        }
    }
}
