package Models;

import Util.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class LibrosDao {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public boolean registrar(Libros lb) {
        String sql = "INSERT INTO libros (titulo, id_autor, id_editorial, id_materia, cantidad, num_pag, anio_edicion) VALUES (?,?,?,?,?,?,?)";
        con = cn.getConnection();
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, lb.getTitulo());
            ps.setInt(2, lb.getAutor());
            ps.setInt(3, lb.getEditorial());
            ps.setInt(4, lb.getMateria());
            ps.setInt(5, lb.getCantidad());
            ps.setInt(6, lb.getNum_pag());
            ps.setInt(7, lb.getAnio_edicion());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
    }

    public boolean actualizar(Libros lb) {
        boolean res;
        String sql = "UPDATE libros SET titulo=?, id_autor=?, id_editorial=?, id_materia=?, cantidad=?, num_pag=?, anio_edicion=? WHERE id = ?";
        con = cn.getConnection();
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, lb.getTitulo());
            ps.setInt(2, lb.getAutor());
            ps.setInt(3, lb.getEditorial());
            ps.setInt(4, lb.getMateria());
            ps.setInt(5, lb.getCantidad());
            ps.setInt(6, lb.getNum_pag());
            ps.setInt(7, lb.getAnio_edicion());
            ps.setInt(8, lb.getId());
            ps.execute();
            res = true;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            res = false;
        }
        return res;
    }

    public List Listar(String valor) {
        List<Libros> lista = new ArrayList();
        try {
            con = cn.getConnection();
            if ("".equalsIgnoreCase(valor)) {
                String sql = "SELECT l.*, e.id AS id_editorial, e.editorial, a.id AS id_autor, a.autor, m.id AS id_materia, m.materia FROM libros l INNER JOIN editorial e ON e.id = l.id_editorial INNER JOIN autor a ON a.id = l.id_autor INNER JOIN materias m ON m.id = l.id_materia ORDER BY l.id DESC";
                ps = con.prepareStatement(sql);
            } else {
                String sql = "SELECT l.*, e.id AS id_editorial, e.editorial, a.id AS id_autor, a.autor, m.id AS id_materia, m.materia FROM libros l INNER JOIN editorial e ON e.id = l.id_editorial INNER JOIN autor a ON a.id = l.id_autor INNER JOIN materias m ON m.id = l.id_materia WHERE l.titulo LIKE '%" + valor + "%' OR e.editorial LIKE '%" + valor + "%' OR m.materia LIKE '%" + valor + "%'";
                ps = con.prepareStatement(sql);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Libros lb = new Libros();
                lb.setId(rs.getInt("id"));
                lb.setTitulo(rs.getString("titulo"));
                lb.setAutor(rs.getInt("id_autor"));
                lb.setEditorial(rs.getInt("id_editorial"));
                lb.setMateria(rs.getInt("id_materia"));
                lb.setCantidad(rs.getInt("cantidad"));
                lb.setNum_pag(rs.getInt("num_pag"));
                lb.setAnio_edicion(rs.getInt("anio_edicion"));
                lb.setAutor_nombre(rs.getString("autor"));
                lb.setEditorial_nombre(rs.getString("editorial"));
                lb.setMateria_nombre(rs.getString("materia"));
                lista.add(lb);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return lista;
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM libros WHERE id = ?";
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

    public Combo getAutor(String valor) {
        Combo lb = new Combo();
        String sql = "SELECT * FROM autor WHERE autor = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, valor);
            rs = ps.executeQuery();
            if (rs.next()) {
                lb.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return lb;
    }
    public Combo getEditorial(String valor) {
        Combo lb = new Combo();
        String sql = "SELECT * FROM editorial WHERE editorial = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, valor);
            rs = ps.executeQuery();
            if (rs.next()) {
                lb.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return lb;
    }
    public Combo getMateria(String valor) {
        Combo lb = new Combo();
        String sql = "SELECT * FROM materias WHERE materia = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, valor);
            rs = ps.executeQuery();
            if (rs.next()) {
                lb.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return lb;
    }
}
