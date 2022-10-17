package JDBCConnection;

import java.sql.*;

public class App {

    public static void main(String[] args) {
        Connection myConnection = null;

        try{
            myConnection = DriverManager.getConnection("jdbc:mysql://dam2.mysql.iesquevedo.es:3335/adriancristobal-newspaper", "root", "quevedo2dam");
            System.out.println("Connection OK");

            Statement stmt = myConnection.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM newspaper");

            while (rs.next()){
                System.out.println(rs.getInt("id") + " | " + rs.getString("name_newspaper") + " | " + rs.getDate("release_date"));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (myConnection != null) {
            try {
                myConnection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
