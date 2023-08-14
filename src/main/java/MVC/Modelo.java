/*
 * Rendon Estrada Jorge
 * Fecha: 24/07/2023
 * Descripcion: Clase que tendra los metodos de conexion y acciones que se
 * realizaran en la Base de Datos
 */
package MVC;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Jorge
 */
public class Modelo {

    //Se realiza la clase para poder Establecer la conexion con la base de datos
    public static Connection conectar() {
        Connection connection = null;

        try {
            // Paso 1: Registrar el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Paso 2: Establecer la conexión
            String url = "jdbc:mysql://localhost:3306/biblioteca";
            String usuario = "root";
            String contrasena = "";
            connection = DriverManager.getConnection(url, usuario, contrasena);

            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error en la conexión");
            e.printStackTrace();
            // Manejo de excepciones
        }

        return connection;
    }

    //Metodo para cerrar conexion a la BD
    public static void desconectar(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al desconectar");
                e.printStackTrace();
                // Manejo de excepciones
            }
        }
    }

    //Metodo para mostrar la de los Usuarios que esten activos
    public static JTable mostrarUsers(JTable tabla) {
        String consulta = "SELECT * FROM usuarios WHERE estado = true"; // Consulta para obtener todos los usuarios

        DefaultTableModel modeloTabla = (DefaultTableModel) tabla.getModel();

        // Limpiar la tabla antes de llenarla con nuevos datos
        modeloTabla.setRowCount(0);

        try ( Connection conexion = Modelo.conectar();  Statement statement = conexion.createStatement();  ResultSet resultado = statement.executeQuery(consulta)) {

            // Obtener metadatos de la consulta
            int columnas = resultado.getMetaData().getColumnCount();
            String[] nombresColumnas = new String[columnas];
            for (int i = 0; i < columnas; i++) {
                nombresColumnas[i] = resultado.getMetaData().getColumnName(i + 1);
            }

            // Establecer nombres de columnas al modelo
            modeloTabla.setColumnIdentifiers(nombresColumnas);

            // Llenar la tabla con los resultados
            while (resultado.next()) {
                Object[] fila = new Object[columnas];
                for (int i = 0; i < columnas; i++) {
                    if (nombresColumnas[i].equalsIgnoreCase("estado")) {
                        boolean estado = resultado.getBoolean(i + 1);
                        fila[i] = estado ? "ACTIVO" : "INACTIVO";
                    } else {
                        fila[i] = resultado.getObject(i + 1);
                    }
                }
                modeloTabla.addRow(fila);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones en caso de algún error en la base de datos
            JOptionPane.showMessageDialog(null, "ERROR AL CARGAR LA TABLA");
        }

        return tabla;
    }

    //Metodo para buscar el usuario por nombre
    /**
     *
     * @param tabla
     * @param nombre
     * @return
     */
    public static JTable buscarUser(JTable tabla, String nombre) {
        String consulta = "SELECT * FROM usuarios WHERE nombre LIKE ?"; // Consulta con la cláusula LIKE

        DefaultTableModel modeloTabla = (DefaultTableModel) tabla.getModel();
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de llenarla con nuevos datos

        try ( Connection conexion = Modelo.conectar();  PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {

            // Establecer el parámetro para la consulta con LIKE
            preparedStatement.setString(1, "%" + nombre + "%");

            // Obtener resultados de la consulta
            try ( ResultSet resultado = preparedStatement.executeQuery()) {
                // Obtener metadatos de la consulta
                int columnas = resultado.getMetaData().getColumnCount();
                String[] nombresColumnas = new String[columnas];
                for (int i = 0; i < columnas; i++) {
                    nombresColumnas[i] = resultado.getMetaData().getColumnName(i + 1);
                }

                // Establecer nombres de columnas al modelo
                modeloTabla.setColumnIdentifiers(nombresColumnas);

                // Llenar la tabla con los resultados
                while (resultado.next()) {
                    Object[] fila = new Object[columnas];
                    for (int i = 0; i < columnas; i++) {
                        if (nombresColumnas[i].equalsIgnoreCase("estado")) {
                            boolean estado = resultado.getBoolean(i + 1);
                            fila[i] = estado ? "ACTIVO" : "INACTIVO";
                        } else {
                            fila[i] = resultado.getObject(i + 1);
                        }
                    }
                    modeloTabla.addRow(fila);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones en caso de algún error en la base de datos
            JOptionPane.showMessageDialog(null, "NO EXISTEN SIMILITUDES CON ALGUN USUARIO");
        }

        return tabla;
    }

    //Buscar usuario por su ID
    /**
     * Sobrecargar el metodo
     *
     * @param tabla
     * @param idUsuario
     * @return
     */
    public static JTable buscarUser(JTable tabla, int idUsuario) {
        String consulta = "SELECT * FROM usuarios WHERE idUsuario = ?"; // Consulta para buscar por ID

        DefaultTableModel modeloTabla = (DefaultTableModel) tabla.getModel();
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de llenarla con nuevos datos

        try ( Connection conexion = Modelo.conectar();  PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {

            // Establecer el parámetro para la consulta
            preparedStatement.setInt(1, idUsuario);

            // Obtener resultados de la consulta
            try ( ResultSet resultado = preparedStatement.executeQuery()) {
                int columnas = resultado.getMetaData().getColumnCount();
                String[] nombresColumnas = new String[columnas];
                for (int i = 0; i < columnas; i++) {
                    nombresColumnas[i] = resultado.getMetaData().getColumnName(i + 1);
                }

                modeloTabla.setColumnIdentifiers(nombresColumnas);

                // Llenar la tabla con los resultados
                while (resultado.next()) {
                    Object[] fila = new Object[columnas];
                    for (int i = 0; i < columnas; i++) {
                        if (nombresColumnas[i].equalsIgnoreCase("estado")) {
                            boolean estado = resultado.getBoolean(i + 1);
                            fila[i] = estado ? "ACTIVO" : "INACTIVO";
                        } else {
                            fila[i] = resultado.getObject(i + 1);
                        }
                    }
                    modeloTabla.addRow(fila);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "NO EXISTEN SIMILITUDES CON ALGUN USUARIO");
        }

        return tabla;
    }

    //Metodo para dar de Alta a un nuevo Usuario
    public static void altaUsuario(String nombre, String direc, String telef) {
        //Se prepara el comando SQL
        String sql = "INSERT INTO usuarios (nombre,direccion,telefono) VALUES (?,?,?)";


        /*Se declaran los recursos que utilizara el metodo para que al terminar
         el bloque try estos se cierren automaticamente (Pricipalmente la conexion)*/
        try ( Connection con = Modelo.conectar();//Establece la conexion
                  PreparedStatement comando = con.prepareStatement(sql)) {//Comando SQL

            // Establecer los valores de los parámetros en el PreparedStatement
            comando.setString(1, nombre);
            comando.setString(2, direc);
            comando.setString(3, telef);

            // Ejecutar la consulta de inserción y obtener el número de filas afectadas
            int filasInsertadas = comando.executeUpdate();

            // Verificar si se insertaron filas correctamente y mostrar un mensaje
            if (filasInsertadas > 0) {
                JOptionPane.showMessageDialog(null, "USUARIO AGREGADO CORRECTAMENTE");
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL REGISTRAR EL USUARIO");
            }

            //La conexion se cierra automaticamente debido al try-with-resources.
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERROR:" + e);
        }
    }

    //Metodo para Llenar los campos de editar usuario
    public static Usuario datosUser(int idUser) {
        String consulta = "SELECT nombre, direccion, telefono, estado FROM usuarios WHERE idUsuario = ?";

        try ( Connection conexion = conectar();  PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {

            // Establecer el parámetro para la consulta
            preparedStatement.setInt(1, idUser);

            try ( ResultSet resultado = preparedStatement.executeQuery()) {
                if (resultado.next()) {
                    String nombre = resultado.getString("nombre");
                    String direccion = resultado.getString("direccion");
                    String telefono = resultado.getString("telefono");
                    boolean estado = resultado.getBoolean("estado");

                    return new Usuario(idUser, nombre, direccion, telefono, estado);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones en caso de algún error en la base de datos
            JOptionPane.showMessageDialog(null, "Error:" + e);
        }

        return null; // Retorna null si no se encontró el usuario con el ID proporcionado
    }

    /**
     *
     * @param nombre
     * @return sobrecarga del metodo
     */
    public static Usuario datosUser(String nombre) {
        String consulta = "SELECT idUsuario, nombre, direccion, telefono, estado FROM usuarios WHERE nombre LIKE ?";

        try ( Connection conexion = conectar();  PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {

            // Establecer el parámetro para la consulta utilizando LIKE con el nombre de usuario
            preparedStatement.setString(1, "%" + nombre + "%");

            try ( ResultSet resultado = preparedStatement.executeQuery()) {
                if (resultado.next()) {
                    int idUsuario = resultado.getInt("idUsuario");
                    String nombreU = resultado.getString("nombre");
                    String direccion = resultado.getString("direccion");
                    String telefono = resultado.getString("telefono");
                    boolean estado = resultado.getBoolean("estado");

                    //Retorna un objeto usuario con los datos
                    return new Usuario(idUsuario, nombreU, direccion, telefono, estado);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones en caso de algún error en la base de datos
            JOptionPane.showMessageDialog(null, "Error:" + e);
        }

        return null; // Retorna null si no se encontró el usuario con el nombre proporcionado
    }

    //Actualizar los datos de algun usuario
    public static void updateUser(int idUsuario, String nombre, String direccion, String telefono, String estadoStr) {
        // Convertir el estadoStr a un valor booleano
        boolean estado = estadoStr.equalsIgnoreCase("ACTIVO");

        String consulta = "UPDATE usuarios SET nombre = ?, direccion = ?, telefono = ?, estado = ? WHERE idUsuario = ?";

        try ( Connection conexion = conectar();  PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {

            // Establecer los parámetros para la consulta de actualización
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, direccion);
            preparedStatement.setString(3, telefono);
            preparedStatement.setBoolean(4, estado);
            preparedStatement.setInt(5, idUsuario);

            // Ejecutar la consulta de actualización
            int filasActualizadas = preparedStatement.executeUpdate();

            if (filasActualizadas > 0) {
                System.out.println("Usuario actualizado correctamente.");
            } else {
                System.out.println("No se pudo actualizar el usuario.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones en caso de algún error en la base de datos
        }
    }

    //Hacer el metodo para hacer la eliminacion logica
    public static void logicDelete(int idUser) {
        // Consulta para obtener el estado actual del usuario por su ID
        String consultaBuscar = "SELECT estado FROM usuarios WHERE idUsuario = ?";

        // Consulta para actualizar el estado del usuario por su ID
        String consultaActualizar = "UPDATE usuarios SET estado = ? WHERE idUsuario = ?";

        try ( Connection conexion = Modelo.conectar();  PreparedStatement preparedStatementBuscar = conexion.prepareStatement(consultaBuscar);  PreparedStatement preparedStatementActualizar = conexion.prepareStatement(consultaActualizar)) {

            // Consultar el estado actual del usuario
            preparedStatementBuscar.setInt(1, idUser);
            try ( ResultSet resultado = preparedStatementBuscar.executeQuery()) {
                if (resultado.next()) {
                    boolean estadoActual = resultado.getBoolean("estado");
                    if (estadoActual) {
                        // Si el estado es "true", actualizar a "false"
                        preparedStatementActualizar.setBoolean(1, false); // Establecer el nuevo estado
                        preparedStatementActualizar.setInt(2, idUser); // Establecer el ID del usuario

                        int filasActualizadas = preparedStatementActualizar.executeUpdate();

                        if (filasActualizadas > 0) {
                            System.out.println("Usuario eliminado lógicamente correctamente.");
                            JOptionPane.showMessageDialog(null, "USUARIO ELIMINADO CORRECTAMENTE");
                        } else {
                            System.out.println("No se pudo eliminar lógicamente el usuario.");
                            JOptionPane.showMessageDialog(null, "NO SE PUDO ELIMINAR EL USUARIO");
                        }
                    } else {
                        // Si el estado ya es "false", mostrar un mensaje
                        System.out.println("El usuario ya está inactivo (eliminado lógicamente).");
                        JOptionPane.showMessageDialog(null, "EL ESTADO DEL USUARIO YA ES INACTIVO");
                    }
                } else {
                    System.out.println("Usuario no encontrado.");
                    JOptionPane.showMessageDialog(null, "USUARIO NO ENCONTRADO");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones en caso de algún error en la base de datos
        }
    }

    //----------------METODOS PARA EL APARTADO DE PRESTAMOS-------------------\\
    public static JTable mostrarPrestamos(JTable tabla) {
        // Definir la consulta SQL para obtener los préstamos activos
        String consulta = "SELECT idPrestamo, idUsuario, fechaPrestamo, fechaDev, cantLib, adeudo, "
                + "CASE WHEN estado = true THEN 'ACTIVO' ELSE 'INACTIVO' END AS estado "
                + "FROM prestamos";

        // Obtener el modelo de la tabla
        DefaultTableModel modeloTabla = (DefaultTableModel) tabla.getModel();
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de llenarla con nuevos datos

        try ( Connection conexion = Modelo.conectar();  PreparedStatement preparedStatement = conexion.prepareStatement(consulta);  ResultSet resultado = preparedStatement.executeQuery()) {

            // Llenar la tabla con los resultados
            while (resultado.next()) {
                // Obtener los valores de cada columna del resultado
                int idPrestamo = resultado.getInt("idPrestamo");
                int idUsuario = resultado.getInt("idUsuario");
                Date fechaPrestamo = resultado.getDate("fechaPrestamo");
                Date fechaDev = resultado.getDate("fechaDev");
                int cantLib = resultado.getInt("cantLib");
                float adeudo = resultado.getFloat("adeudo");
                String estado = resultado.getString("estado");

                // Crear una fila con los valores obtenidos
                Object[] fila = new Object[]{
                    idPrestamo,
                    idUsuario,
                    fechaPrestamo,
                    fechaDev,
                    cantLib,
                    adeudo,
                    estado
                };

                // Agregar la fila al modelo de la tabla
                modeloTabla.addRow(fila);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Retornar la tabla con los resultados
        return tabla;
    }

    /**
     *
     * @param tabla
     * @param idPrestamo
     * @return
     */
    public static JTable buscarPrestamo(JTable tabla, int idPrestamo) {
        // Definir la consulta SQL para buscar el préstamo por su ID
        String consulta = "SELECT idPrestamo, idUsuario, fechaPrestamo, fechaDev, cantLib, adeudo, estado "
                + "FROM prestamos WHERE idPrestamo = ?";

        // Obtener el modelo de la tabla
        DefaultTableModel modeloTabla = (DefaultTableModel) tabla.getModel();
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de llenarla con nuevos datos

        try ( Connection conexion = Modelo.conectar();  PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {

            // Establecer el parámetro (id del préstamo) en la consulta
            preparedStatement.setInt(1, idPrestamo);

            try ( ResultSet resultado = preparedStatement.executeQuery()) {
                // Obtener metadatos de la consulta
                ResultSetMetaData metaData = resultado.getMetaData();
                int columnas = metaData.getColumnCount();
                String[] nombresColumnas = new String[columnas];
                for (int i = 0; i < columnas; i++) {
                    nombresColumnas[i] = metaData.getColumnName(i + 1);
                }

                // Establecer nombres de columnas al modelo
                modeloTabla.setColumnIdentifiers(nombresColumnas);

                // Llenar la tabla con el resultado del préstamo buscado
                while (resultado.next()) {
                    Object[] fila = new Object[columnas];
                    for (int i = 0; i < columnas; i++) {
                        if (nombresColumnas[i].equalsIgnoreCase("estado")) {
                            boolean estado = resultado.getBoolean(i + 1);
                            fila[i] = estado ? "ACTIVO" : "INACTIVO";
                        } else {
                            fila[i] = resultado.getObject(i + 1);
                        }
                    }
                    modeloTabla.addRow(fila);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Retornar la tabla con los resultados
        return tabla;
    }

    public static JTable buscarPrestamo(JTable tabla, String nombreUsuario) {
        // Definir la consulta SQL para buscar préstamos por el nombre del usuario
        String consulta = "SELECT p.idPrestamo, p.idUsuario, p.fechaPrestamo, p.fechaDev, p.cantLib, p.adeudo, "
                + "p.estado "
                + "FROM prestamos AS p "
                + "INNER JOIN usuarios AS u ON p.idUsuario = u.idUsuario "
                + "WHERE u.nombre LIKE ?";

        // Obtener el modelo de la tabla
        DefaultTableModel modeloTabla = (DefaultTableModel) tabla.getModel();
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de llenarla con nuevos datos

        try ( Connection conexion = Modelo.conectar();  PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {

            // Establecer el parámetro (nombre de usuario) en la consulta
            preparedStatement.setString(1, "%" + nombreUsuario + "%");

            try ( ResultSet resultado = preparedStatement.executeQuery()) {
                // Obtener metadatos de la consulta
                ResultSetMetaData metaData = resultado.getMetaData();
                int columnas = metaData.getColumnCount();
                String[] nombresColumnas = new String[columnas];
                for (int i = 0; i < columnas; i++) {
                    nombresColumnas[i] = metaData.getColumnName(i + 1);
                }

                // Establecer nombres de columnas al modelo
                modeloTabla.setColumnIdentifiers(nombresColumnas);

                // Llenar la tabla con los resultados de la búsqueda
                while (resultado.next()) {
                    Object[] fila = new Object[columnas];
                    for (int i = 0; i < columnas; i++) {
                        if (nombresColumnas[i].equalsIgnoreCase("estado")) {
                            boolean estado = resultado.getBoolean(i + 1);
                            fila[i] = estado ? "ACTIVO" : "INACTIVO";
                        } else {
                            fila[i] = resultado.getObject(i + 1);
                        }
                    }
                    modeloTabla.addRow(fila);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Retornar la tabla con los resultados
        return tabla;
    }

    public static JTable buscarPrestamoIdUsuario(JTable tabla, int idUsuario) {
        // Definir la consulta SQL para buscar los préstamos asociados al ID de usuario
        String consulta = "SELECT prestamos.idPrestamo, prestamos.idUsuario, prestamos.fechaPrestamo, prestamos.fechaDev, prestamos.cantLib, prestamos.adeudo, "
                + "CASE WHEN prestamos.estado = true THEN 'ACTIVO' ELSE 'INACTIVO' END AS estado "
                + "FROM prestamos "
                + "INNER JOIN usuarios ON prestamos.idUsuario = usuarios.idUsuario "
                + "WHERE prestamos.idUsuario = ?";

        // Obtener el modelo de la tabla
        DefaultTableModel modeloTabla = (DefaultTableModel) tabla.getModel();
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de llenarla con nuevos datos

        try ( Connection conexion = Modelo.conectar();  PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {

            // Establecer el parámetro (ID del usuario) en la consulta
            preparedStatement.setInt(1, idUsuario);

            try ( ResultSet resultado = preparedStatement.executeQuery()) {

                // Llenar la tabla con los resultados
                while (resultado.next()) {
                    // Obtener los valores de cada columna del resultado
                    int idPrestamo = resultado.getInt("idPrestamo");
                    Date fechaPrestamo = resultado.getDate("fechaPrestamo");
                    Date fechaDev = resultado.getDate("fechaDev");
                    int cantLib = resultado.getInt("cantLib");
                    float adeudo = resultado.getFloat("adeudo");
                    String estado = resultado.getString("estado");

                    // Crear una fila con los valores obtenidos
                    Object[] fila = new Object[]{
                        idPrestamo,
                        idUsuario,
                        fechaPrestamo,
                        fechaDev,
                        cantLib,
                        adeudo,
                        estado
                    };

                    // Agregar la fila al modelo de la tabla
                    modeloTabla.addRow(fila);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Retornar la tabla con los resultados
        return tabla;
    }

    /**
     * Genera un préstamo asociado a un usuario y una cantidad de libros.
     *
     * @param idUser ID del usuario.
     * @param cantLibros Cantidad de libros.
     *
     * Este metodo verifica si el usuario existe y está activo utilizando la
     * función SQL usuarioExiste, Si el usuario existe y está activo, se procede
     * a generar el préstamo. Si no existe o su estado es inactivo, se muestra
     * un mensaje correspondiente.
     */
    public static void generarPrestamo(int idUser, int cantLibros) {
        // Verificar si el usuario existe y está activo utilizando la función almacenada
        String usuarioExisteQuery = "SELECT usuarioExiste(?)";

        try ( Connection connection = Modelo.conectar();  PreparedStatement usuarioExisteStatement = connection.prepareStatement(usuarioExisteQuery)) {

            // Establecer el parámetro para la función almacenada
            usuarioExisteStatement.setInt(1, idUser);

            try ( ResultSet usuarioExisteResult = usuarioExisteStatement.executeQuery()) {
                usuarioExisteResult.next();

                // Obtener el resultado de la función almacenada
                boolean usuarioExiste = usuarioExisteResult.getBoolean(1);

                if (usuarioExiste) {
                    // Generar la consulta de inserción para la tabla de préstamos
                    String consultaInsercion = "INSERT INTO prestamos (idUsuario, cantLib) VALUES (?, ?)";

                    try ( PreparedStatement preparedStatement = connection.prepareStatement(consultaInsercion)) {

                        preparedStatement.setInt(1, idUser);
                        preparedStatement.setInt(2, cantLibros);

                        // Ejecutar la consulta de inserción
                        int filasInsertadas = preparedStatement.executeUpdate();

                        if (filasInsertadas > 0) {
                            int idPrestamoGenerado = obtenerLastInsertIdPrestamo(); // Obtener el ID del último préstamo insertado
                            String mensaje = "Préstamo número " + idPrestamoGenerado + " generado exitosamente.";
                            System.out.println(mensaje);
                            JOptionPane.showMessageDialog(null, mensaje);
                        } else {
                            System.out.println("No se pudo generar el préstamo.");
                            JOptionPane.showMessageDialog(null, "ERROR AL GENERAR EL PRESTAMO");
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                        // Manejo de excepciones en caso de algún error en la base de datos
                    }

                } else {
                    System.out.println("El ID de usuario no existe o su estado es INACTIVO. No se pudo generar el préstamo.");
                    JOptionPane.showMessageDialog(null, "El usuario no existe o su estado es INACTIVO");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones en caso de algún error en la base de datos
        }
    }

    public static void generarFolio(int idLibro) {
        // Declarar la variable para el último ID de préstamo
        int ultimoIdPrestamo = -1; // Valor predeterminado en caso de que no se pueda obtener

        // Verificar si el libro existe utilizando la función almacenada
        String libroExisteQuery = "SELECT libroExiste(?)";

        try ( Connection connection = Modelo.conectar();  PreparedStatement libroExisteStatement = connection.prepareStatement(libroExisteQuery)) {

            // Establecer el parámetro para la función almacenada
            libroExisteStatement.setInt(1, idLibro);

            try ( ResultSet libroExisteResult = libroExisteStatement.executeQuery()) {
                libroExisteResult.next();

                // Obtener el resultado de la función almacenada
                boolean libroExiste = libroExisteResult.getBoolean(1);

                if (libroExiste) {
                    // Verificar el estado del libro
                    String obtenerEstadoLibroQuery = "SELECT estado FROM libros WHERE idLibro = ?";
                    boolean estadoLibro = false;

                    try ( PreparedStatement obtenerEstadoLibroStatement = connection.prepareStatement(obtenerEstadoLibroQuery)) {
                        obtenerEstadoLibroStatement.setInt(1, idLibro);

                        try ( ResultSet obtenerEstadoLibroResult = obtenerEstadoLibroStatement.executeQuery()) {
                            obtenerEstadoLibroResult.next();
                            estadoLibro = obtenerEstadoLibroResult.getBoolean("estado");
                        }

                    }

                    // Verificar si el libro está activo
                    if (!estadoLibro) {
                        JOptionPane.showMessageDialog(null, "El libro no está activo.");
                        System.out.println("El libro no está activo.");
                        return; // Salir del método si el libro no está activo
                    }

                    // Verificar la cantidad disponible de libros
                    String query = "SELECT cantDisp FROM libros WHERE idLibro = ?";
                    int cantidadDisponible = 0;

                    try ( PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                        // Establecer el parámetro (id del libro) en la consulta
                        preparedStatement.setInt(1, idLibro);

                        // Ejecutar la consulta y obtener el resultado
                        try ( ResultSet resultSet = preparedStatement.executeQuery()) {
                            if (resultSet.next()) {
                                // Obtener la cantidad disponible de libros desde el resultado
                                cantidadDisponible = resultSet.getInt("cantDisp");
                            }
                        }

                        // Verificar si hay libros disponibles para prestar
                        if (cantidadDisponible <= 0) {
                            JOptionPane.showMessageDialog(null, "No hay libros disponibles para prestar.");
                            System.out.println("No hay libros disponibles para prestar.");
                            return; // Salir del método si no hay libros disponibles
                        }

                        // Obtener la cantidad de folios disponibles utilizando la función almacenada
                        int foliosDisponibles = obtenerFoliosDisponibles(obtenerLastInsertIdPrestamo());
                        System.out.println("Ultimo id:" + foliosDisponibles);

                        // Insertar el folio en la tabla folio si hay libros disponibles y todo es válido
                        if (cantidadDisponible > 0 && foliosDisponibles > 0) {
                            // Obtener el último ID insertado en la tabla prestamo
                            int lastInsertedIdPrestamo = obtenerLastInsertIdPrestamo();

                            // Obtener el idUsuario del último registro insertado en prestamos
                            int idUsuario = -1; // Valor predeterminado en caso de que no se pueda obtener

                            try ( Connection conexion = conectar()) {
                                // Crear una consulta para obtener el último idPrestamo insertado en prestamos
                                String obtenerUltimoIdPrestamoQuery = "SELECT MAX(idPrestamo) AS ultimoIdPrestamo FROM prestamos";

                                try ( PreparedStatement obtenerUltimoIdPrestamoStatement = conexion.prepareStatement(obtenerUltimoIdPrestamoQuery);  ResultSet obtenerUltimoIdPrestamoResult = obtenerUltimoIdPrestamoStatement.executeQuery()) {

                                    if (obtenerUltimoIdPrestamoResult.next()) {
                                        ultimoIdPrestamo = obtenerUltimoIdPrestamoResult.getInt("ultimoIdPrestamo");

                                        // Crear una consulta para obtener el idUsuario del último registro insertado en prestamos
                                        String obtenerIdUsuarioQuery = "SELECT idUsuario FROM prestamos WHERE idPrestamo = ?";

                                        try ( PreparedStatement obtenerIdUsuarioStatement = conexion.prepareStatement(obtenerIdUsuarioQuery)) {
                                            obtenerIdUsuarioStatement.setInt(1, ultimoIdPrestamo);

                                            try ( ResultSet obtenerIdUsuarioResult = obtenerIdUsuarioStatement.executeQuery()) {
                                                if (obtenerIdUsuarioResult.next()) {
                                                    idUsuario = obtenerIdUsuarioResult.getInt("idUsuario");
                                                } else {
                                                    throw new SQLException("No se pudo obtener un idUsuario válido del último registro insertado en prestamos");
                                                }
                                            }
                                        }
                                    }
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                                // Manejo de excepciones en caso de algún error en la base de datos
                            }

                            // Insertar el folio en la tabla folio
                            String insertFolioQuery = "INSERT INTO folio (idPrestamo, idLibro, idUsuario, estado) VALUES (?, ?, ?, ?)";

                            try ( PreparedStatement insertFolioStatement = connection.prepareStatement(insertFolioQuery)) {
                                // Establecer los parámetros en la consulta
                                insertFolioStatement.setInt(1, ultimoIdPrestamo);
                                insertFolioStatement.setInt(2, idLibro);
                                insertFolioStatement.setInt(3, idUsuario); // Obtener el idUsuario (puede variar según tu lógica)
                                insertFolioStatement.setBoolean(4, estadoLibro); // Estado inicial del folio

                                // Ejecutar la inserción
                                System.out.println(insertFolioStatement);
                                insertFolioStatement.executeUpdate();

                                // Restar uno a la cantidad disponible de libros y de folios
                                cantidadDisponible--;
                                foliosDisponibles--;

                                JOptionPane.showMessageDialog(null, "Folio generado exitosamente");

                                //(resto del código para mostrar mensajes o realizar acciones)
                            } catch (SQLException e) {
                                e.printStackTrace();
                                // Manejo de excepciones en caso de algún error en la base de datos
                            }
                        }

                        //(resto del código para verificar cantidad de folios y generar folio)
                    } catch (SQLException e) {
                        e.printStackTrace();
                        // Manejo de excepciones en caso de algún error en la base de datos
                    }

                } else {
                    // Si el libro no existe
                    System.out.println("El libro no existe. No se pudo generar el folio.");
                    JOptionPane.showMessageDialog(null, "El libro no existe.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones en caso de algún error en la base de datos
        }
    }

    // Función para obtener la cantidad de folios disponibles
    // Función para obtener la cantidad de folios disponibles basada en el idPrestamo
    public static int obtenerFoliosDisponibles(int idPrestamo) {
        String obtenerCantidadFoliosQuery = "SELECT obtenerCantidadFolios(?)";
        int foliosDisponibles = 0;

        try ( Connection connection = Modelo.conectar();  PreparedStatement obtenerCantidadFoliosStatement = connection.prepareStatement(obtenerCantidadFoliosQuery)) {

            obtenerCantidadFoliosStatement.setInt(1, idPrestamo);

            try ( ResultSet obtenerCantidadFoliosResult = obtenerCantidadFoliosStatement.executeQuery()) {
                if (obtenerCantidadFoliosResult.next()) {
                    foliosDisponibles = obtenerCantidadFoliosResult.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones en caso de algún error en la base de datos
        }

        return foliosDisponibles;
    }

    // Metodo para obtener el último ID insertado en la tabla prestamo
    public static int obtenerLastInsertIdPrestamo() {
        int lastInsertedIdPrestamo = 0;
        String query = "SELECT obtenerUltimoIdInsertado() AS ultimoIdPrestamo";

        try ( Connection connection = Modelo.conectar();  PreparedStatement statement = connection.prepareStatement(query);  ResultSet result = statement.executeQuery()) {

            if (result.next()) {
                lastInsertedIdPrestamo = result.getInt("ultimoIdPrestamo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones en caso de algún error en la base de datos
        }

        return lastInsertedIdPrestamo;
    }

    /**
     * Genera un folio asociado a un préstamo y un libro. Estamos sobrecargando
     * el metodo
     *
     * @param idPrestamo ID del préstamo.
     * @param idLibro ID del libro.
     */
    public static void generarFolio(int idPrestamo, int idLibro) {
        // Verificar si existe el préstamo y el libro utilizando las funciones SQL
        String prestamoExisteQuery = "SELECT prestamoExiste(?)";
        String libroExisteQuery = "SELECT libroExiste(?)";

        try ( Connection connection = Modelo.conectar();  PreparedStatement prestamoExisteStatement = connection.prepareStatement(prestamoExisteQuery);  PreparedStatement libroExisteStatement = connection.prepareStatement(libroExisteQuery)) {

            // Establecer los parámetros para las funciones SQL
            prestamoExisteStatement.setInt(1, idPrestamo);
            libroExisteStatement.setInt(1, idLibro);

            try ( ResultSet prestamoExisteResult = prestamoExisteStatement.executeQuery();  ResultSet libroExisteResult = libroExisteStatement.executeQuery()) {

                prestamoExisteResult.next();
                libroExisteResult.next();

                // Obtener los resultados de las funciones SQL
                boolean prestamoExiste = prestamoExisteResult.getBoolean(1);
                boolean libroExiste = libroExisteResult.getBoolean(1);

                if (!prestamoExiste) {
                    JOptionPane.showMessageDialog(null, "El préstamo no existe.");
                    System.out.println("El préstamo no existe.");
                } else if (!libroExiste) {
                    JOptionPane.showMessageDialog(null, "El libro no existe.");
                    System.out.println("El libro no existe.");
                } else {
                    // Verificar el estado del préstamo y el libro
                    String obtenerEstadoPrestamoQuery = "SELECT estado FROM prestamos WHERE idPrestamo = ?";
                    String obtenerEstadoLibroQuery = "SELECT estado FROM libros WHERE idLibro = ?";

                    boolean estadoPrestamo = false;
                    boolean estadoLibro = false;

                    try ( PreparedStatement obtenerEstadoPrestamoStatement = connection.prepareStatement(obtenerEstadoPrestamoQuery);  PreparedStatement obtenerEstadoLibroStatement = connection.prepareStatement(obtenerEstadoLibroQuery)) {

                        obtenerEstadoPrestamoStatement.setInt(1, idPrestamo);
                        obtenerEstadoLibroStatement.setInt(1, idLibro);

                        try ( ResultSet obtenerEstadoPrestamoResult = obtenerEstadoPrestamoStatement.executeQuery();  ResultSet obtenerEstadoLibroResult = obtenerEstadoLibroStatement.executeQuery()) {

                            obtenerEstadoPrestamoResult.next();
                            obtenerEstadoLibroResult.next();

                            estadoPrestamo = obtenerEstadoPrestamoResult.getBoolean(1);
                            estadoLibro = obtenerEstadoLibroResult.getBoolean(1);
                        }
                    }

                    if (!estadoPrestamo) {
                        JOptionPane.showMessageDialog(null, "El préstamo no está activo.");
                        System.out.println("El préstamo no está activo.");
                    } else if (!estadoLibro) {
                        JOptionPane.showMessageDialog(null, "El libro no está activo.");
                        System.out.println("El libro no está activo.");
                    } else {
                        // Verificar la cantidad de folios relacionados con el préstamo
                        String obtenerCantidadFoliosQuery = "SELECT obtenerCantidadFolios(?)";

                        try ( PreparedStatement obtenerCantidadFoliosStatement = connection.prepareStatement(obtenerCantidadFoliosQuery)) {
                            obtenerCantidadFoliosStatement.setInt(1, idPrestamo);

                            try ( ResultSet obtenerCantidadFoliosResult = obtenerCantidadFoliosStatement.executeQuery()) {
                                obtenerCantidadFoliosResult.next();
                                int cantidadFolios = obtenerCantidadFoliosResult.getInt(1);

                                // Obtener la cantidad permitida de folios para el préstamo
                                String obtenerCantidadPermitidaQuery = "SELECT obtenerCantidadPermitida(?)";

                                try ( PreparedStatement obtenerCantidadPermitidaStatement = connection.prepareStatement(obtenerCantidadPermitidaQuery)) {
                                    obtenerCantidadPermitidaStatement.setInt(1, idPrestamo);

                                    try ( ResultSet obtenerCantidadPermitidaResult = obtenerCantidadPermitidaStatement.executeQuery()) {
                                        obtenerCantidadPermitidaResult.next();
                                        int cantidadPermitida = obtenerCantidadPermitidaResult.getInt(1);

                                        if (cantidadFolios < cantidadPermitida) {
                                            // Ahora podemos insertar en la tabla de folio utilizando los datos obtenidos
                                            String insertQuery = "INSERT INTO folio (idPrestamo, idLibro, idUsuario) VALUES (?, ?, ?)";
                                            try ( PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                                                // Establecer los valores para el insert en folio
                                                insertStatement.setInt(1, idPrestamo);
                                                insertStatement.setInt(2, idLibro);

                                                // Obtener el ID del usuario del préstamo
                                                String obtenerIdUsuarioPrestamoQuery = "SELECT obtenerDatosPrestamo_IDUsuario(?)";

                                                try ( PreparedStatement obtenerIdUsuarioPrestamoStatement = connection.prepareStatement(obtenerIdUsuarioPrestamoQuery)) {
                                                    obtenerIdUsuarioPrestamoStatement.setInt(1, idPrestamo);

                                                    try ( ResultSet obtenerIdUsuarioPrestamoResult = obtenerIdUsuarioPrestamoStatement.executeQuery()) {
                                                        obtenerIdUsuarioPrestamoResult.next();
                                                        int idUsuario = obtenerIdUsuarioPrestamoResult.getInt(1);

                                                        // Establecer el valor del ID de usuario
                                                        insertStatement.setInt(3, idUsuario);

                                                        insertStatement.executeUpdate();

                                                        JOptionPane.showMessageDialog(null, "Folio generado exitosamente.");
                                                        System.out.println("Folio generado exitosamente.");
                                                    }
                                                }
                                            }
                                        } else {
                                            // Si se ha excedido el límite de folios para el préstamo
                                            JOptionPane.showMessageDialog(null, "Se ha excedido el límite de folios para este préstamo.");
                                            System.out.println("Se ha excedido el límite de folios para este préstamo.");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERROR EN LA CONSULTA DE LA BASE DE DATOS");
            // Manejo de excepciones en caso de algún error en la base de datos
        }
    }

    /*Este método primero consulta el estado actual del préstamo con el ID proporcionado
    Si el estado es false, muestra un mensaje de que el préstamo ya está inactivo
    Si el estado es true, ejecuta una actualización para cambiar el estado a false
    y muestra mensajes correspondientes sobre el éxito o la falta de éxito de la operación
    Si no se encuentra el préstamo, muestra un mensaje de que el préstamo no existe.*/
    public static void borrarPrestamoLogic(int idPrestamo) {
        // Consulta para obtener el estado actual del préstamo
        String consultaEstado = "SELECT estado FROM prestamos WHERE idPrestamo = ?";

        // Consulta para actualizar el estado del préstamo a false
        String actualizarEstadoQuery = "UPDATE prestamos SET estado = false WHERE idPrestamo = ?";

        try ( Connection conexion = Modelo.conectar();  PreparedStatement consultaEstadoStatement = conexion.prepareStatement(consultaEstado);  PreparedStatement actualizarEstadoStatement = conexion.prepareStatement(actualizarEstadoQuery)) {

            // Establecer el parámetro (ID del préstamo) en las consultas
            consultaEstadoStatement.setInt(1, idPrestamo);
            actualizarEstadoStatement.setInt(1, idPrestamo);

            try ( ResultSet estadoResultado = consultaEstadoStatement.executeQuery()) {
                if (estadoResultado.next()) {
                    boolean estadoActual = estadoResultado.getBoolean("estado");

                    if (!estadoActual) {
                        // Mostrar mensaje si el préstamo ya está inactivo
                        JOptionPane.showMessageDialog(null, "El préstamo ya está inactivo.");
                    } else {
                        // Ejecutar la actualización del estado
                        int filasActualizadas = actualizarEstadoStatement.executeUpdate();

                        if (filasActualizadas > 0) {
                            // Mostrar mensaje si la inactivación fue exitosa
                            JOptionPane.showMessageDialog(null, "Préstamo inactivado exitosamente.");
                        } else {
                            // Mostrar mensaje si la inactivación no tuvo éxito
                            JOptionPane.showMessageDialog(null, "No se pudo inactivar el préstamo.");
                        }
                    }
                } else {
                    // Mostrar mensaje si el préstamo no existe
                    JOptionPane.showMessageDialog(null, "El préstamo no existe.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //----------------METODOS PARA EL APARTADO DE DEVOLUCIONES----------------\\
    //Metodo para mostrar todos los folios vinculados al ID del prestamo
    public static JTable mostrarFolios(JTable tabla) {
        // Definir la consulta SQL para obtener todos los folios
        String consulta = "SELECT idFolio, idPrestamo, idLibro, idUsuario, estado FROM folio";

        // Obtener el modelo de la tabla
        DefaultTableModel modeloTabla = (DefaultTableModel) tabla.getModel();
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de llenarla con nuevos datos

        try ( Connection conexion = Modelo.conectar();  PreparedStatement preparedStatement = conexion.prepareStatement(consulta);  ResultSet resultado = preparedStatement.executeQuery()) {

            // Obtener metadatos de la consulta
            ResultSetMetaData metaData = resultado.getMetaData();
            int columnas = metaData.getColumnCount();
            String[] nombresColumnas = new String[columnas];
            for (int i = 0; i < columnas; i++) {
                nombresColumnas[i] = metaData.getColumnName(i + 1);
            }

            // Establecer nombres de columnas al modelo
            modeloTabla.setColumnIdentifiers(nombresColumnas);

            // Llenar la tabla con los resultados
            while (resultado.next()) {
                Object[] fila = new Object[columnas];
                for (int i = 0; i < columnas; i++) {
                    if (nombresColumnas[i].equalsIgnoreCase("estado")) {
                        boolean estado = resultado.getBoolean(i + 1);
                        fila[i] = estado ? "PENDIENTE" : "ENTREGADO";
                    } else {
                        fila[i] = resultado.getObject(i + 1);
                    }
                }
                modeloTabla.addRow(fila);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Retornar la tabla con los resultados
        return tabla;
    }

    //Metodo para buscar los folios vinculados al ID del prestamo
    /**
     *
     * @param tabla A LLENAR
     * @param idPrestamo Con el que hara la consulta
     * @param comboBox Que llenara con los libros ligados a ese prestamo
     * @return Tabla con los folios
     */
    public static JTable buscarFolios(JTable tabla, int idPrestamo, JComboBox<String> comboBox) {
        // Verificar si el préstamo existe utilizando la función SQL
        String prestamoExisteQuery = "SELECT prestamoExiste(?)";

        try ( Connection connection = Modelo.conectar();  PreparedStatement prestamoExisteStatement = connection.prepareStatement(prestamoExisteQuery)) {

            // Establecer el parámetro para la función SQL
            prestamoExisteStatement.setInt(1, idPrestamo);

            try ( ResultSet prestamoExisteResult = prestamoExisteStatement.executeQuery()) {
                prestamoExisteResult.next();

                // Obtener el resultado de la función SQL
                boolean prestamoExiste = prestamoExisteResult.getBoolean(1);

                if (!prestamoExiste) {
                    JOptionPane.showMessageDialog(null, "El préstamo no existe.");
                    System.out.println("El préstamo no existe.");
                } else {
                    // Realizar consulta para obtener los folios vinculados al ID del préstamo
                    String consulta = "SELECT * FROM folio WHERE idPrestamo = ?";

                    // Obtener el modelo de la tabla
                    DefaultTableModel modeloTabla = (DefaultTableModel) tabla.getModel();
                    modeloTabla.setRowCount(0); // Limpiar la tabla antes de llenarla con nuevos datos

                    try ( PreparedStatement preparedStatement = connection.prepareStatement(consulta)) {
                        // Establecer el parámetro (ID del préstamo) en la consulta
                        preparedStatement.setInt(1, idPrestamo);

                        try ( ResultSet resultado = preparedStatement.executeQuery()) {
                            // Obtener metadatos de la consulta
                            ResultSetMetaData metaData = resultado.getMetaData();
                            int columnas = metaData.getColumnCount();
                            String[] nombresColumnas = new String[columnas];
                            for (int i = 0; i < columnas; i++) {
                                nombresColumnas[i] = metaData.getColumnName(i + 1);
                            }

                            // Establecer nombres de columnas al modelo
                            modeloTabla.setColumnIdentifiers(nombresColumnas);

                            // Llenar la tabla con los resultados
                            while (resultado.next()) {
                                Object[] fila = new Object[columnas];
                                for (int i = 0; i < columnas; i++) {
                                    if (nombresColumnas[i].equalsIgnoreCase("estado")) {
                                        boolean estado = resultado.getBoolean(i + 1);
                                        fila[i] = estado ? "PENDIENTE" : "ENTREGADO";
                                    } else {
                                        fila[i] = resultado.getObject(i + 1);
                                    }
                                }
                                modeloTabla.addRow(fila);

                                // Llenar el JComboBox con el título del libro
                                int idLibro = resultado.getInt("idLibro");
                                String tituloLibro = obtenerTituloLibro(idLibro); // Función para obtener el título
                                comboBox.addItem(tituloLibro);
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Retornar la tabla con los resultados
        return tabla;
    }

    /**
     * Obtiene el título de un libro a partir de su ID.
     *
     * @param idLibro El ID del libro del cual se desea obtener el título.
     * @return El título del libro correspondiente al ID, o null si no se
     * encuentra.
     */
    public static String obtenerTituloLibro(int idLibro) {
        String tituloLibro = null;

        // Consulta SQL para obtener el título del libro a partir del ID
        String consulta = "SELECT titulo FROM libros WHERE idLibro = ?";

        try ( Connection connection = Modelo.conectar();  PreparedStatement preparedStatement = connection.prepareStatement(consulta)) {

            // Establecer el parámetro (ID del libro) en la consulta
            preparedStatement.setInt(1, idLibro);

            try ( ResultSet resultado = preparedStatement.executeQuery()) {
                // Verificar si se encontró un resultado
                if (resultado.next()) {
                    // Obtener el título del libro desde el resultado
                    tituloLibro = resultado.getString("titulo");
                }
            }

        } catch (SQLException e) {
            // Manejo de excepciones en caso de error en la base de datos
            e.printStackTrace();
        }

        // Retornar el título del libro (puede ser null si no se encontró)
        return tituloLibro;
    }

    public static JTable buscarFolio(JTable tabla, String tituloLibro, JComboBox<String> comboBoxLibros) {
        String consulta = "{CALL BuscarFolioPorTitulo(?)}";

        try ( Connection conexion = Modelo.conectar();  CallableStatement callableStatement = conexion.prepareCall(consulta)) {
            callableStatement.setString(1, tituloLibro);

            try ( ResultSet resultado = callableStatement.executeQuery()) {
                DefaultTableModel modeloTabla = (DefaultTableModel) tabla.getModel();
                modeloTabla.setRowCount(0); // Limpiar la tabla antes de llenarla con nuevos datos

                // Obtener metadatos de la consulta
                ResultSetMetaData metaData = resultado.getMetaData();
                int columnas = metaData.getColumnCount();
                String[] nombresColumnas = new String[columnas];
                for (int i = 0; i < columnas; i++) {
                    nombresColumnas[i] = metaData.getColumnName(i + 1);
                }

                // Establecer nombres de columnas al modelo de la tabla
                modeloTabla.setColumnIdentifiers(nombresColumnas);

                // Llenar la tabla con los resultados
                while (resultado.next()) {
                    Object[] fila = new Object[columnas];
                    for (int i = 0; i < columnas; i++) {
                        if (nombresColumnas[i].equalsIgnoreCase("estado")) {
                            boolean estado = resultado.getBoolean(i + 1);
                            fila[i] = estado ? "PENDIENTE" : "ENTREGADO";
                        } else {
                            fila[i] = resultado.getObject(i + 1);
                        }
                    }
                    modeloTabla.addRow(fila);
                }

                // Llenar el ComboBox con los títulos de libros encontrados
                //llenarComboBoxLibros(comboBoxLibros, resultado);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tabla;
    }

    public static void llenarComboBoxFolioPorTitulo(JComboBox<String> comboBoxLibros, String tituloLibro) {
        String consulta = "{CALL BuscarFolioPorTitulo(?)}";

        // Usar un conjunto (Set) para almacenar títulos únicos
        Set<String> titulosUnicos = new HashSet<String>();  // Corrección aquí

        try ( Connection conexion = Modelo.conectar();  CallableStatement statement = conexion.prepareCall(consulta)) {
            statement.setString(1, tituloLibro);
            ResultSet resultado = statement.executeQuery();

            while (resultado.next()) {
                String titulo = resultado.getString("tituloLibro");
                titulosUnicos.add(titulo); // Agregar título al conjunto
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Limpiar el comboBox y agregar los títulos únicos
        comboBoxLibros.removeAllItems();
        for (String titulo : titulosUnicos) {
            comboBoxLibros.addItem(titulo);
        }
    }

    public static DefaultTableModel llenarModeloTabla(ResultSet resultado) throws SQLException {
        DefaultTableModel modeloTabla = new DefaultTableModel();

        // Obtener metadatos de la consulta
        ResultSetMetaData metaData = resultado.getMetaData();
        int columnas = metaData.getColumnCount();
        String[] nombresColumnas = new String[columnas];
        for (int i = 0; i < columnas; i++) {
            nombresColumnas[i] = metaData.getColumnName(i + 1);
        }

        // Establecer nombres de columnas al modelo
        modeloTabla.setColumnIdentifiers(nombresColumnas);

        // Llenar el modelo de tabla con los resultados
        while (resultado.next()) {
            Object[] fila = new Object[columnas];
            for (int i = 0; i < columnas; i++) {
                if (nombresColumnas[i].equalsIgnoreCase("estado")) {
                    boolean estado = resultado.getBoolean(i + 1);
                    fila[i] = estado ? "ACTIVO" : "INACTIVO";
                } else {
                    fila[i] = resultado.getObject(i + 1);
                }
            }
            modeloTabla.addRow(fila);
        }

        return modeloTabla;
    }

    public static void llenarComboBoxLibros(JComboBox<String> comboBoxLibros, ResultSet resultado) throws SQLException {
        // Limpiar el ComboBox antes de llenarlo
        comboBoxLibros.removeAllItems();

        // Llenar el ComboBox con los títulos de libros encontrados
        while (resultado.next()) {
            String titulo = resultado.getString("tituloLibro");
            comboBoxLibros.addItem(titulo);
        }
    }

    // Metodo para actualizar el estado de un folio
    public static void updateFolio(int idPrestamo, String nombreLibro, boolean estado) {
        // Verificar si el préstamo existe utilizando la función SQL
        String prestamoExisteQuery = "SELECT prestamoExiste(?)";

        try ( Connection connection = Modelo.conectar();  PreparedStatement prestamoExisteStatement = connection.prepareStatement(prestamoExisteQuery)) {

            // Establecer el parámetro para la función SQL
            prestamoExisteStatement.setInt(1, idPrestamo);

            try ( ResultSet prestamoExisteResult = prestamoExisteStatement.executeQuery()) {
                prestamoExisteResult.next();

                // Obtener el resultado de la función SQL
                boolean prestamoExiste = prestamoExisteResult.getBoolean(1);

                if (!prestamoExiste) {
                    JOptionPane.showMessageDialog(null, "El préstamo no existe.");
                    System.out.println("El préstamo no existe.");
                } else {
                    // Realizar consulta para obtener los folios vinculados al ID del préstamo y al nombre del libro
                    String consulta = "SELECT idFolio FROM folio WHERE idPrestamo = ? AND idLibro IN (SELECT idLibro FROM libros WHERE titulo = ?)";

                    try ( PreparedStatement preparedStatement = connection.prepareStatement(consulta)) {
                        // Establecer los parámetros (ID del préstamo y nombre del libro) en la consulta
                        preparedStatement.setInt(1, idPrestamo);
                        preparedStatement.setString(2, nombreLibro);

                        try ( ResultSet resultado = preparedStatement.executeQuery()) {
                            // Recorrer los resultados y actualizar el estado de los folios
                            while (resultado.next()) {
                                int idFolio = resultado.getInt("idFolio");

                                // Verificar el estado actual del folio antes de la actualización
                                String verificarEstadoQuery = "SELECT estado FROM folio WHERE idFolio = ?";
                                try ( PreparedStatement verificarEstadoStatement = connection.prepareStatement(verificarEstadoQuery)) {
                                    verificarEstadoStatement.setInt(1, idFolio);
                                    try ( ResultSet estadoResult = verificarEstadoStatement.executeQuery()) {
                                        estadoResult.next();
                                        boolean estadoActual = estadoResult.getBoolean("estado");

                                        // Verificar si el estado actual es diferente al nuevo estado
                                        if (estadoActual != estado) {
                                            // Actualizar el estado del folio
                                            String updateQuery = "UPDATE folio SET estado = ? WHERE idFolio = ?";
                                            try ( PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                                                updateStatement.setBoolean(1, estado);
                                                updateStatement.setInt(2, idFolio);
                                                updateStatement.executeUpdate();

                                                // Ajustar la cantidad disponible en libros según el estado nuevo y actual
                                                String obtenerIdLibroQuery = "SELECT idLibro FROM libros WHERE titulo = ?";
                                                try ( PreparedStatement obtenerIdLibroStatement = connection.prepareStatement(obtenerIdLibroQuery)) {
                                                    obtenerIdLibroStatement.setString(1, nombreLibro);
                                                    try ( ResultSet idLibroResult = obtenerIdLibroStatement.executeQuery()) {
                                                        idLibroResult.next();
                                                        int idLibro = idLibroResult.getInt("idLibro");

                                                        String actualizarCantDispQuery = "UPDATE libros SET cantDisp = cantDisp "
                                                                + (estado ? "- 1" : "+ 1") + " WHERE idLibro = ?";
                                                        try ( PreparedStatement actualizarCantDispStatement = connection.prepareStatement(actualizarCantDispQuery)) {
                                                            actualizarCantDispStatement.setInt(1, idLibro);
                                                            actualizarCantDispStatement.executeUpdate();
                                                        }
                                                    }
                                                }

                                                JOptionPane.showMessageDialog(null, "Estado del folio actualizado exitosamente.");
                                                System.out.println("Estado del folio actualizado exitosamente.");
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "El estado del folio ya es " + (estado ? "Pendiente" : "Entregado"));
                                            System.out.println("El estado del folio ya es " + (estado ? "Pendiente" : "Entregado"));
                                        }
                                    }
                                }
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //------------------METODOS PARA EL APARTADO DE LIBROS--------------------\\

    /**
     * Llena la tabla con los registros de la tabla de libros, reemplazando las
     * llaves foraneas por los textos vinculados.
     *
     * @param tabla La tabla en la que se mostrarán los resultados.
     * @return La tabla llena con los resultados.
     */
    public static JTable mostrarLibros(JTable tabla) {
        // Definir la consulta SQL para llamar al procedimiento almacenado
        String consulta = "CALL MostrarLibros()";
        // Obtener el modelo de la tabla
        DefaultTableModel modeloTabla = (DefaultTableModel) tabla.getModel();
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de llenarla con nuevos datos

        try ( Connection conexion = Modelo.conectar();  CallableStatement callableStatement = conexion.prepareCall(consulta)) {

            try ( ResultSet resultado = callableStatement.executeQuery()) {
                // Llenar la tabla con los resultados
                while (resultado.next()) {
                    // Obtener los valores de cada columna del resultado
                    int idLibro = resultado.getInt("idLibro");
                    String titulo = resultado.getString("titulo");
                    String autor = resultado.getString("autor");
                    String genero = resultado.getString("genero");
                    String editorial = resultado.getString("editorial");
                    int cantDisp = resultado.getInt("cantDisp");
                    String estado = resultado.getString("estado");

                    // Crear una fila con los valores obtenidos
                    Object[] fila = new Object[]{
                        idLibro,
                        titulo,
                        autor,
                        genero,
                        editorial,
                        cantDisp,
                        estado
                    };

                    // Agregar la fila al modelo de la tabla
                    modeloTabla.addRow(fila);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Retornar la tabla con los resultados
        return tabla;
    }

    //Metodo que busca el libro por ID
    public static JTable buscarLibro(JTable tabla, int idLibro) {
        // Definimos la sentencia para llamar al procedimiento almacenado BuscarLibroPorID
        String callProcedure = "CALL BuscarLibroPorID(?)";

        DefaultTableModel modeloTabla = (DefaultTableModel) tabla.getModel();
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de llenarla con nuevos datos

        try ( Connection conexion = Modelo.conectar();  CallableStatement callableStatement = conexion.prepareCall(callProcedure)) {

            // Establecemos el parámetro del procedimiento almacenado con el ID del libro a buscar
            callableStatement.setInt(1, idLibro);

            try ( ResultSet resultado = callableStatement.executeQuery()) {
                while (resultado.next()) {
                    // Obtenemos los datos del resultado de la consulta
                    int id = resultado.getInt("idLibro");
                    String titulo = resultado.getString("titulo");
                    String autor = resultado.getString("autor");
                    String genero = resultado.getString("genero");
                    String editorial = resultado.getString("editorial");

                    // Agregamos los datos a la tabla
                    modeloTabla.addRow(new Object[]{id, titulo, autor, genero, editorial});
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones en caso de algún error en la base de datos
        }

        return tabla;
    }

    /**
     *
     * @param tabla
     * @param nombreLibro
     * @return La tabla con los resultados Sobrecargar el metodo de buscar un
     * libro
     */
    public static JTable buscarLibro(JTable tabla, String nombreLibro) {
        // Definir el nombre del procedimiento almacenado a llamar
        String callProcedure = "{CALL BuscarLibrosPorNombre(?)}";

        // Obtener el modelo de la tabla
        DefaultTableModel modeloTabla = (DefaultTableModel) tabla.getModel();
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de llenarla con nuevos datos

        try ( Connection conexion = Modelo.conectar();  CallableStatement callableStatement = conexion.prepareCall(callProcedure)) {

            // Establecer el parámetro para el procedimiento almacenado
            callableStatement.setString(1, nombreLibro);

            try ( ResultSet resultado = callableStatement.executeQuery()) {
                while (resultado.next()) {
                    // Obtener los datos de la consulta
                    int idLibro = resultado.getInt("idLibro");
                    String titulo = resultado.getString("titulo");
                    String autor = resultado.getString("autor"); // Utilizar el alias definido en el procedimiento
                    String genero = resultado.getString("genero"); // Utilizar el alias definido en el procedimiento
                    String editorial = resultado.getString("editorial"); // Utilizar el alias definido en el procedimiento

                    // Agregar una nueva fila a la tabla con los datos obtenidos
                    modeloTabla.addRow(new Object[]{idLibro, titulo, autor, genero, editorial});
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se encontraron similitudes");
        }

        return tabla;
    }

    // Método para llenar un JComboBox con géneros
    /**
     *
     * @param comboBox que se llenara con los generos registrados en nuestra BD
     */
    public static void llenarComboBoxGeneros(JComboBox<String> comboBox) {
        // Limpiar el combo box antes de llenarlo nuevamente
        //comboBox.removeAllItems();

        // Consulta SQL para obtener los géneros ordenados alfabéticamente
        String consulta = "SELECT genero FROM genero ORDER BY genero ASC";

        try ( Connection conexion = Modelo.conectar();  Statement statement = conexion.createStatement();  ResultSet resultado = statement.executeQuery(consulta)) {

            // Llenar el combo box con los géneros obtenidos
            while (resultado.next()) {
                String genero = resultado.getString("genero");
                comboBox.addItem(genero);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones en caso de algún error en la base de datos
        }
    }

    /**
     *
     * @param comboBox que se llenara con el nombre de los autores en la base de
     * datos
     */
    public static void llenarComboBoxAutores(JComboBox<String> comboBox) {
        // Limpiar el combo box antes de llenarlo nuevamente
        //comboBox.removeAllItems();

        // Consulta SQL para obtener los nombres de los autores ordenados alfabéticamente
        String consulta = "SELECT nombre FROM autor ORDER BY nombre ASC";

        try ( Connection conexion = Modelo.conectar();  Statement statement = conexion.createStatement();  ResultSet resultado = statement.executeQuery(consulta)) {

            // Llenar el combo box con los nombres de autores obtenidos
            while (resultado.next()) {
                String autor = resultado.getString("nombre");
                comboBox.addItem(autor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones en caso de algún error en la base de datos
        }
    }

    /**
     *
     * @param comboBox que se llenara con las editoriales registradas
     */
    public static void llenarComboBoxEditoriales(JComboBox<String> comboBox) {
        // Limpiar el combo box antes de llenarlo nuevamente
        //comboBox.removeAllItems();

        // Consulta SQL para obtener los nombres de las editoriales ordenados alfabéticamente
        String consulta = "SELECT editorial FROM editorial ORDER BY editorial ASC";

        try ( Connection conexion = Modelo.conectar();  Statement statement = conexion.createStatement();  ResultSet resultado = statement.executeQuery(consulta)) {

            // Llenar el combo box con los nombres de editoriales obtenidos
            while (resultado.next()) {
                String editorial = resultado.getString("editorial");
                comboBox.addItem(editorial);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones en caso de algún error en la base de datos
        }
    }

    //Metodo para comprobar si existe  dentro del combobox
    public static boolean comboContainsItem(JComboBox comboBox, String item) {
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            if (comboBox.getItemAt(i).equals(item)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Este metodo realiza un registro en la tabla libros, utilizando las
     * funciones SQL para definidas en nuestra BD para obtener los ID's que
     * corresponden a cada campo
     *
     * @param titulo titulo de libro que se guaradara
     * @param autor Nombre de autor que utilizara la funcion SQL definisa para
     * obtener el ID
     * @param genero Genero que utilizara la funcion SQL definisa para obtener
     * el ID
     * @param editorial Editorial que utilizara la funcion SQL definisa para
     * obtener el ID
     * @param cantDisp Numero de libros disponibles que se registraran para ese
     * ejemplar
     */
    public static void altaLibro(String titulo, String autor, String genero, String editorial, int cantDisp) {
        // Consulta SQL para insertar un nuevo libro con los IDs de autor, género y editorial
        String insertQuery = "INSERT INTO libros (titulo, idAutor, idGenero, idEditorial, cantDisp) "
                + "VALUES (?, obtenerIdAutor(?), obtenerIdGenero(?), obtenerIdEditorial(?), ?)";

        try ( Connection connection = Modelo.conectar();  PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {

            // Establecer los parámetros en la consulta
            insertStatement.setString(1, titulo);
            insertStatement.setString(2, autor);
            insertStatement.setString(3, genero);
            insertStatement.setString(4, editorial);
            insertStatement.setInt(5, cantDisp);

            // Ejecutar la consulta
            insertStatement.executeUpdate();

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(null, "Libro insertado exitosamente.");
            System.out.println("Libro insertado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones en caso de algún error en la base de datos
        }
    }

    //Datos para llenar los campos mediante el id de del libro
    public static Libro datosLibro(int idLibro) {
        // Consulta SQL para obtener los datos del libro por ID
        String consulta = "SELECT idLibro, titulo, idAutor, idGenero, idEditorial, cantDisp, estado FROM libros WHERE idLibro = ?";

        try ( Connection conexion = Modelo.conectar();  PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {
            // Establecer el parámetro (ID del libro) en la consulta
            preparedStatement.setInt(1, idLibro);

            try ( ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("idLibro");
                    String titulo = resultSet.getString("titulo");
                    int idAutor = resultSet.getInt("idAutor");
                    int idGenero = resultSet.getInt("idGenero");
                    int idEditorial = resultSet.getInt("idEditorial");
                    int cantDisp = resultSet.getInt("cantDisp");
                    boolean estado = resultSet.getBoolean("estado");

                    Libro libro = new Libro(id,
                            obtenerNombreAutorPorID(idAutor),
                            obtenerNombreGeneroPorID(idGenero),
                            obtenerNombreEditorialPorID(idEditorial), titulo, cantDisp, estado);
                    return libro;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Si no se encontró ningún libro con el ID proporcionado
    }

    public static Libro datosLibros(String tituloLibro) {
        // Consulta SQL para obtener los datos del libro por título
        String consulta = "SELECT idLibro, titulo, idAutor, idGenero, idEditorial, cantDisp, estado FROM libros WHERE titulo LIKE ? LIMIT 1";

        try ( Connection conexion = Modelo.conectar();  PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {
            // Establecer el parámetro (título del libro) en la consulta
            preparedStatement.setString(1, "%" + tituloLibro + "%");

            try ( ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("idLibro");
                    String titulo = resultSet.getString("titulo");
                    int idAutor = resultSet.getInt("idAutor");
                    int idGenero = resultSet.getInt("idGenero");
                    int idEditorial = resultSet.getInt("idEditorial");
                    int cantDisp = resultSet.getInt("cantDisp");
                    boolean estado = resultSet.getBoolean("estado");

                    Libro libro = new Libro(id,
                            obtenerNombreAutorPorID(idAutor),
                            obtenerNombreGeneroPorID(idGenero),
                            obtenerNombreEditorialPorID(idEditorial),
                            titulo, cantDisp, estado);
                    return libro;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Si no se encontró ningún libro con el título proporcionado
    }

    public static String obtenerNombreAutorPorID(int idAutor) {
        String nombreAutor = "";

        String consulta = "SELECT nombre FROM autor WHERE idAutor = ?";

        try ( Connection conexion = conectar();  PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {
            preparedStatement.setInt(1, idAutor);

            try ( ResultSet resultado = preparedStatement.executeQuery()) {
                if (resultado.next()) {
                    nombreAutor = resultado.getString("nombre");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombreAutor;
    }

    public static String obtenerNombreGeneroPorID(int idGenero) {
        String nombreGenero = "";

        String consulta = "SELECT genero FROM genero WHERE idGenero = ?";

        try ( Connection conexion = conectar();  PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {
            preparedStatement.setInt(1, idGenero);

            try ( ResultSet resultado = preparedStatement.executeQuery()) {
                if (resultado.next()) {
                    nombreGenero = resultado.getString("genero");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombreGenero;
    }

    public static String obtenerNombreEditorialPorID(int idEditorial) {
        String nombreEditorial = "";

        String consulta = "SELECT editorial FROM editorial WHERE idEditorial = ?";

        try ( Connection conexion = conectar();  PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {
            preparedStatement.setInt(1, idEditorial);

            try ( ResultSet resultado = preparedStatement.executeQuery()) {
                if (resultado.next()) {
                    nombreEditorial = resultado.getString("editorial");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombreEditorial;
    }

    //Metodo para actualizar un libro
    public static void updateLibro(int idLibro, String titulo, String autor, String genero, String editorial, String estado) {
        boolean estadoLibro = estado.equalsIgnoreCase("activo");

        // Consulta SQL para obtener los IDs de autor, género y editorial utilizando las funciones SQL
        String obtenerIdAutorQuery = "SELECT obtenerIdAutor(?)";
        String obtenerIdGeneroQuery = "SELECT obtenerIdGenero(?)";
        String obtenerIdEditorialQuery = "SELECT obtenerIdEditorial(?)";

        int idAutor = 0;
        int idGenero = 0;
        int idEditorial = 0;

        try ( Connection conexion = Modelo.conectar();  PreparedStatement obtenerIdAutorStatement = conexion.prepareStatement(obtenerIdAutorQuery);  PreparedStatement obtenerIdGeneroStatement = conexion.prepareStatement(obtenerIdGeneroQuery);  PreparedStatement obtenerIdEditorialStatement = conexion.prepareStatement(obtenerIdEditorialQuery)) {

            // Establecer el parámetro (nombre) en las consultas
            obtenerIdAutorStatement.setString(1, autor);
            obtenerIdGeneroStatement.setString(1, genero);
            obtenerIdEditorialStatement.setString(1, editorial);

            // Ejecutar las consultas y obtener los IDs correspondientes
            try ( ResultSet obtenerIdAutorResult = obtenerIdAutorStatement.executeQuery();  ResultSet obtenerIdGeneroResult = obtenerIdGeneroStatement.executeQuery();  ResultSet obtenerIdEditorialResult = obtenerIdEditorialStatement.executeQuery()) {

                if (obtenerIdAutorResult.next()) {
                    idAutor = obtenerIdAutorResult.getInt(1);
                }

                if (obtenerIdGeneroResult.next()) {
                    idGenero = obtenerIdGeneroResult.getInt(1);
                }

                if (obtenerIdEditorialResult.next()) {
                    idEditorial = obtenerIdEditorialResult.getInt(1);
                }
            }

            // Consulta SQL para actualizar los datos del libro
            String updateQuery = "UPDATE libros SET idAutor = ?, idGenero = ?, idEditorial = ?, estado = ?, titulo = ? WHERE idLibro = ?";

            try ( PreparedStatement updateStatement = conexion.prepareStatement(updateQuery)) {
                // Establecer los parámetros en la consulta
                updateStatement.setInt(1, idAutor);
                updateStatement.setInt(2, idGenero);
                updateStatement.setInt(3, idEditorial);
                updateStatement.setBoolean(4, estadoLibro);
                updateStatement.setString(5, titulo);
                updateStatement.setInt(6, idLibro);

                // Ejecutar la actualización
                updateStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Metodo para la eliminacion logica de un libro
    public static void logicDeleteLibro(int idLibro) {
        // Definir las consultas SQL necesarias
        String obtenerEstadoQuery = "SELECT estado FROM libros WHERE idLibro = ?";
        String updateEstadoQuery = "UPDATE libros SET estado = ? WHERE idLibro = ?";

        try ( Connection connection = Modelo.conectar();  PreparedStatement obtenerEstadoStatement = connection.prepareStatement(obtenerEstadoQuery);  PreparedStatement updateEstadoStatement = connection.prepareStatement(updateEstadoQuery)) {

            // Establecer el parámetro (id del libro) en la consulta para obtener el estado actual
            obtenerEstadoStatement.setInt(1, idLibro);

            try ( ResultSet obtenerEstadoResult = obtenerEstadoStatement.executeQuery()) {
                if (obtenerEstadoResult.next()) {
                    // Obtener el estado actual del libro
                    boolean estadoActual = obtenerEstadoResult.getBoolean("estado");

                    if (!estadoActual) {
                        // Si el estado es inactivo, mostrar un mensaje informativo
                        JOptionPane.showMessageDialog(null, "El libro ya está inactivo.");
                    } else {
                        // Si el estado es activo, proceder a cambiarlo a inactivo (false)
                        updateEstadoStatement.setBoolean(1, false); // Nuevo estado: inactivo
                        updateEstadoStatement.setInt(2, idLibro); // Para el libro con el id dado
                        updateEstadoStatement.executeUpdate(); // Ejecutar la actualización en la base de datos

                        // Mostrar mensaje de éxito
                        JOptionPane.showMessageDialog(null, "El libro se ha marcado como inactivo exitosamente.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones en caso de algún error en la base de datos
        }
    }

    // Método para validar usuario y contraseña para ingresar
    public static boolean logIn(String usuario, char[] pass) {
        // Encripta la contraseña ingresada usando el método hashPassword
        String hashedPass = hashPassword(pass);

        // Consulta SQL para verificar la existencia del usuario y contraseña en la tabla admins
        String query = "SELECT COUNT(*) AS count FROM admins WHERE usuario = ? AND contra = ?";

        try ( Connection connection = Modelo.conectar();  PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Establecer los valores de los parámetros en la consulta preparada
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, hashedPass);

            System.out.println(preparedStatement);
            try ( ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    // Retorna true si se encontró el usuario y contraseña
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Retorna false si ocurrió un error o no se encontró el usuario
        return false;
    }

    public static String hashPassword(char[] pass) {
        try {
            // Inicializa el objeto MessageDigest con el algoritmo SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Convierte los caracteres en el arreglo a bytes utilizando UTF-8
            byte[] passBytes = new String(pass).getBytes(StandardCharsets.UTF_8);

            // Calcula el hash de los bytes de la contraseña
            byte[] hash = digest.digest(passBytes);

            // Convierte el hash en una representación hexadecimal
            StringBuilder hashedPassword = new StringBuilder();
            for (byte b : hash) {
                hashedPassword.append(String.format("%02x", b));
            }

            // Retorna la contraseña encriptada
            return hashedPassword.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // Retorna null si ocurrió un error
        return null;
    }

    //Mostrar prestamos vencidos
    public static JTable mostrarPrestamosVencidos(JTable tabla) {
        // Crear el modelo de la tabla con las columnas necesarias
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID Prestamo");
        modeloTabla.addColumn("Fecha Devolución");
        modeloTabla.addColumn("Monto Adeudo");

        // Llamar al procedimiento almacenado para obtener los préstamos vencidos
        String procedimiento = "CALL MostrarPrestamosVencidos()";

        try ( Connection connection = Modelo.conectar();  PreparedStatement preparedStatement = connection.prepareStatement(procedimiento);  ResultSet resultSet = preparedStatement.executeQuery()) {

            // Iterar a través de los resultados y agregarlos a la tabla
            while (resultSet.next()) {
                int idPrestamo = resultSet.getInt("idPrestamo");
                String fechaDev = resultSet.getString("fechaDev");
                double montoAdeudo = resultSet.getDouble("adeudo");
                modeloTabla.addRow(new Object[]{idPrestamo, fechaDev, montoAdeudo});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones en caso de algún error en la base de datos
        }

        // Asignar el modelo de la tabla y retornarla
        tabla.setModel(modeloTabla);
        return tabla;
    }

    //Mostrar ususario con mas prestamos
    public static JTable mostrarUsuarioConMasPrestamos(JTable tabla) {
        // Crear el modelo de la tabla con las columnas necesarias
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Usuario");
        modeloTabla.addColumn("Número de Prestamos");

        // Llamar al procedimiento almacenado para obtener el usuario con más préstamos
        String procedimiento = "CALL UsuarioConMasPrestamos()";

        try ( Connection connection = Modelo.conectar();
                PreparedStatement preparedStatement = connection.prepareStatement(procedimiento);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            // Obtener el resultado del procedimiento (debe ser solo una fila)
            if (resultSet.next()) {
                String usuario = resultSet.getString("Usuario");
                int numeroPrestamos = resultSet.getInt("Numero_Prestamos");
                modeloTabla.addRow(new Object[]{usuario, numeroPrestamos});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones en caso de algún error en la base de datos
        }

        // Asignar el modelo de la tabla y retornarla
        tabla.setModel(modeloTabla);
        return tabla;
    }

    //Subclase para los usuarios
    public static class Usuario {

        private int idUsuario;
        private String nombre;
        private String direccion;
        private String telefono;
        private boolean estado;

        public Usuario() {

        }

        public Usuario(int idUsuario, String nombre, String direccion, String telefono, boolean estado) {

            this.idUsuario = idUsuario;
            this.nombre = nombre;
            this.direccion = direccion;
            this.telefono = telefono;
            this.estado = estado;
        }

        // Getters y setters 
        public int getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(int idUsuario) {
            this.idUsuario = idUsuario;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        public boolean getEstado() {
            return estado;
        }

        public void setEstado(boolean estado) {
            this.estado = estado;
        }

    }

    public static class Libro {
// Propiedades del libro

        private static int id;
        private static String autor;
        private static String genero;
        private static String editorial;
        private static String titulo;
        private static int cantDisp;
        private static boolean estado;

        // Constructor
        public Libro() {

        }

        public Libro(int id, String autor, String genero, String editorial, String titulo, int cantDisp, boolean estado) {
            Libro.id = id;
            Libro.autor = autor;
            Libro.genero = genero;
            Libro.editorial = editorial;
            Libro.titulo = titulo;
            Libro.cantDisp = cantDisp;
            Libro.estado = estado;
        }

        // Getters y setters
        public static int getId() {
            return id;
        }

        public static void setId(int id) {
            Libro.id = id;
        }

        public static String getAutor() {
            return autor;
        }

        public static void setAutor(String autor) {
            Libro.autor = autor;
        }

        public static String getGenero() {
            return genero;
        }

        public static void setGenero(String genero) {
            Libro.genero = genero;
        }

        public static String getEditorial() {
            return editorial;
        }

        public static void setEditorial(String editorial) {
            Libro.editorial = editorial;
        }

        public static String getTitulo() {
            return titulo;
        }

        public static void setTitulo(String titulo) {
            Libro.titulo = titulo;
        }

        public static int getCantDisp() {
            return cantDisp;
        }

        public static void setCantDisp(int cantDisp) {
            Libro.cantDisp = cantDisp;
        }

        public static boolean getEstado() {
            return estado;
        }

        public static void setEstado(boolean estado) {
            Libro.estado = estado;
        }

    }

}
