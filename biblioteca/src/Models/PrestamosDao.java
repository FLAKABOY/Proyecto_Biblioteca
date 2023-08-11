
package Models;

import Util.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PrestamosDao {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public boolean registrar(Prestamos pre) {
        String sql = "INSERT INTO prestamos (id_estudiante, id_libro, cantidad, fecha_prestamo, fecha_devolucion) VALUES (?,?,?,?,?)";
        con = cn.getConnection();
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, pre.getEstudiante());
            ps.setInt(2, pre.getLibro());
            ps.setInt(3, pre.getCantidad());
            ps.setString(4, pre.getFecha_prestamo());
            ps.setString(5, pre.getFecha_dev());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
    }

    public boolean actualizar(int id) {
        boolean res;
        String sql = "UPDATE prestamos SET estado=? WHERE id = ?";
        con = cn.getConnection();
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setInt(2, id);
            ps.execute();
            res = true;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            res = false;
        }
        return res;
    }

    public List Listar(String valor) {
        List<Prestamos> lista = new ArrayList();
        try {
            con = cn.getConnection();
            if ("".equalsIgnoreCase(valor)) {
                String sql = "SELECT p.*, l.id AS id_libro, l.titulo, e.id AS id_est, e.nombre FROM prestamos p INNER JOIN libros l ON l.id = p.id_libro INNER JOIN estudiantes e ON e.id = p.id_estudiante ORDER BY p.id DESC";
                ps = con.prepareStatement(sql);
            } else {
                String sql = "SELECT p.*, l.id AS id_libro, l.titulo, e.id AS id_est, e.nombre FROM prestamos p INNER JOIN libros l ON l.id = p.id_libro INNER JOIN estudiantes e ON e.id = p.id_estudiante WHERE e.nombre LIKE '%" + valor + "%' OR l.titulo LIKE '%" + valor + "%' OR p.fecha_prestamo LIKE '%" + valor + "%'";
                ps = con.prepareStatement(sql);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Prestamos pre = new Prestamos();
                pre.setId(rs.getInt("id"));
                pre.setTitulo(rs.getString("titulo"));
                pre.setNombre_est(rs.getString("nombre"));
                pre.setCantidad(rs.getInt("cantidad"));
                pre.setFecha_prestamo(rs.getString("fecha_prestamo"));
                pre.setFecha_dev(rs.getString("fecha_devolucion"));
                pre.setEstado(rs.getInt("estado"));
                lista.add(pre);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return lista;
    }

}
