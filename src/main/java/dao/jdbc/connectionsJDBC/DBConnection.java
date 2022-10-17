package dao.jdbc.connectionsJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    Connection myConnection = null;

    public Connection getConnection() {
        try {
            myConnection = DriverManager.getConnection("jdbc:mysql://dam2.mysql.iesquevedo.es:3335/adriancristobal-newspaper", "root", "quevedo2dam");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return myConnection;
    }

    public void closeConnection(Connection myConnection) {
        if (myConnection != null) {
            try {
                myConnection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
