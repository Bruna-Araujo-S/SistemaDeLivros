package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionSQL {

    String url = "jdbc:mysql://localhost:3306/Sistema_de_Livros";
    String usuario = "root";
    String senha = "user";
    
    private Connection dbconn = null;
    private Statement sqlmgr = null;

    public void OpenDatabase() {
        try {
            dbconn = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conectado com sucesso em: " + url);
            sqlmgr = dbconn.createStatement();
        } catch (Exception Error) {
            System.out.println("Error on Connect: " + Error.getMessage());
        }
    }

    public void CloseDatabase() {
        try {
            if (sqlmgr != null) {
                sqlmgr.close();
            }
        } catch (SQLException e) {
            System.out.println("Error while closing statement: " + e.getMessage());
        }
    
        try {
            if (dbconn != null) {
                dbconn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error while closing connection: " + e.getMessage());
        }
    }
    
    public ResultSet ExecutaQuery(String sql) {
        try {
            Statement stmt = dbconn.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void ExecutaUpdate(String sql) throws SQLException {
        try (Statement stmt = dbconn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }
    
}
