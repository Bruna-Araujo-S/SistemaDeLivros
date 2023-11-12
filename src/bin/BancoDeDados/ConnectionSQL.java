package bin.BancoDeDados;

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
    
    public int ExecutaQuery(String sql) {
        try {
            return sqlmgr.executeUpdate(sql);
        } catch (Exception Error) {
            System.out.println("Error on Connect: " + Error.getMessage());
        }
        return -1;
    }

    public ResultSet ExecutaQuerySelect(String sql) {
        try {
            return sqlmgr.executeQuery(sql);
        } catch (Exception Error) {
            System.out.println("Error on Connect: " + Error.getMessage());
        }
        return null;
    }
}
