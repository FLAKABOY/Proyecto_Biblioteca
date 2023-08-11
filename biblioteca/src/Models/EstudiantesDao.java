
package Models;

import Util.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class EstudiantesDao {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    public boolean registrar(Estudiantes est) {
        String sql = "INSERT INTO estudiantes (documento, codigo, nombre, telefono, carrera) VALUES (?,?,?,?,?)";
        con = cn.getConnection();
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, est.getDocumento());
            ps.setString(2, est.getCodigo());
            ps.setString(3, est.getNombre());
            ps.setString(4, est.getTelefono());
            ps.setString(5, est.getCarrera());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
    }

    public boolean actualizar(Estudiantes est) {
        boolean res;
        String sql = "UPDATE estudiantes SET documento=?, codigo=?, nombre=?, telefono=?, carrera=? WHERE id = ?";
        con = cn.getConnection();
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, est.getDocumento());
            ps.setString(2, est.getCodigo());
            ps.setString(3, est.getNombre());
            ps.setString(4, est.getTelefono());
            ps.setString(5, est.getCarrera());
            ps.setInt(6, est.getId());
            ps.execute();
            res = true;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            res = false;
        }
        return res;
    }

    public List Listar(String valor) {
        List<Estudiantes> lista = new ArrayList();
        try {
            con = cn.getConnection();
            if ("".equalsIgnoreCase(valor)) {
                String sql = "SELECT * FROM estudiantes ORDER BY id DESC";
                ps = con.prepareStatement(sql);
            } else {
                String sql = "SELECT * FROM estudiantes WHERE nombre LIKE '%" + valor + "%'";
                ps = con.prepareStatement(sql);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Estudiantes est = new Estudiantes();
                est.setId(rs.getInt("id"));
                est.setDocumento(rs.getString("documento"));
                est.setCodigo(rs.getString("codigo"));
                est.setNombre(rs.getString("nombre"));
                est.setTelefono(rs.getString("telefono"));
                est.setCarrera(rs.getString("carrera"));
                lista.add(est);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return lista;
    }

    public boolean eliminar(int id) {

        String sql = "DELETE FROM estudiantes WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
}
