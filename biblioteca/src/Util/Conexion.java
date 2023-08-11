
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    Connection con;
    public Connection getConnection() {
        try {
            String db = "jdbc:mysql://localhost:3306/biblio";
            con = DriverManager.getConnection(db, "root", "");
            return con;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la conexion. " + e.getMessage());
        }
        return null;
    }
}
