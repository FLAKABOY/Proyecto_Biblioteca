/*
 * Rendon Estrada Jorge
 * Fecha: 24/07/2023
 * Descripcion: Clase que tendra los metodos de conexion y acciones que se
 * realizaran en la Base de Datos
 */
package MVC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;

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
        String consulta = "SELECT idPrestamo, idUsuario, fechaPrestamo, fechaDev, cantLib, adeudo, "
                + "CASE WHEN estado = true THEN 'ACTIVO' ELSE 'INACTIVO' END AS estado "
                + "FROM prestamos WHERE idPrestamo = ?";

        // Obtener el modelo de la tabla
        DefaultTableModel modeloTabla = (DefaultTableModel) tabla.getModel();
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de llenarla con nuevos datos

        try ( Connection conexion = Modelo.conectar();  PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {

            // Establecer el parámetro (id del préstamo) en la consulta
            preparedStatement.setInt(1, idPrestamo);

            try ( ResultSet resultado = preparedStatement.executeQuery()) {
                // Llenar la tabla con el resultado del préstamo buscado
                while (resultado.next()) {
                    // Obtener los valores de cada columna del resultado
                    int idPrestamoEncontrado = resultado.getInt("idPrestamo");
                    int idUsuario = resultado.getInt("idUsuario");
                    Date fechaPrestamo = resultado.getDate("fechaPrestamo");
                    Date fechaDev = resultado.getDate("fechaDev");
                    int cantLib = resultado.getInt("cantLib");
                    float adeudo = resultado.getFloat("adeudo");
                    String estado = resultado.getString("estado");

                    // Crear una fila con los valores obtenidos
                    Object[] fila = new Object[]{
                        idPrestamoEncontrado,
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

    public static JTable buscarPrestamo(JTable tabla, String nombreUsuario) {
        // Definir la consulta SQL para buscar préstamos por el nombre del usuario
        String consulta = "SELECT p.idPrestamo, p.idUsuario, p.fechaPrestamo, p.fechaDev, p.cantLib, p.adeudo, "
                + "CASE WHEN p.estado = true THEN 'ACTIVO' ELSE 'INACTIVO' END AS estado "
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
                // Llenar la tabla con los resultados de la búsqueda
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
                    String consultaInsercion = "INSERT INTO prestamos (idUsuario, cantLibros) VALUES (?, ?)";

                    try ( PreparedStatement preparedStatement = connection.prepareStatement(consultaInsercion)) {

                        preparedStatement.setInt(1, idUser);
                        preparedStatement.setInt(2, cantLibros);

                        // Ejecutar la consulta de inserción
                        int filasInsertadas = preparedStatement.executeUpdate();

                        if (filasInsertadas > 0) {
                            System.out.println("Préstamo generado exitosamente.");
                            JOptionPane.showMessageDialog(null, "PRESTAMO GENERADO EXITOSAMENTE");
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

                        // Resto del código para verificar cantidad de folios y generar folio
                        // ...
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
        String consulta = "SELECT idFolio, idPrestamo, idLibro, idUsuario FROM folio";

        // Obtener el modelo de la tabla
        DefaultTableModel modeloTabla = (DefaultTableModel) tabla.getModel();
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de llenarla con nuevos datos

        try ( Connection conexion = Modelo.conectar();  PreparedStatement preparedStatement = conexion.prepareStatement(consulta);  ResultSet resultado = preparedStatement.executeQuery()) {

            // Llenar la tabla con los resultados
            while (resultado.next()) {
                // Obtener los valores de cada columna del resultado
                int idFolio = resultado.getInt("idFolio");
                int idPrestamo = resultado.getInt("idPrestamo");
                int idLibro = resultado.getInt("idLibro");
                int idUsuario = resultado.getInt("idUsuario");

                // Crear una fila con los valores obtenidos
                Object[] fila = new Object[]{
                    idFolio,
                    idPrestamo,
                    idLibro,
                    idUsuario
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

    //Metodo para buscar los folios vinculados al ID del prestamo
    /**
     *
     * @param tabla
     * @param idPrestamo
     * @param comboBox
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
                    String consulta = "SELECT idFolio, idPrestamo, idLibro, idUsuario FROM folio WHERE idPrestamo = ?";

                    // Obtener el modelo de la tabla
                    DefaultTableModel modeloTabla = (DefaultTableModel) tabla.getModel();
                    modeloTabla.setRowCount(0); // Limpiar la tabla antes de llenarla con nuevos datos

                    try ( PreparedStatement preparedStatement = connection.prepareStatement(consulta)) {
                        // Establecer el parámetro (ID del préstamo) en la consulta
                        preparedStatement.setInt(1, idPrestamo);

                        try ( ResultSet resultado = preparedStatement.executeQuery()) {
                            // Llenar la tabla con los resultados
                            while (resultado.next()) {
                                // Obtener los valores de cada columna del resultado
                                int idFolio = resultado.getInt("idFolio");
                                int idLibro = resultado.getInt("idLibro");
                                int idUsuario = resultado.getInt("idUsuario");

                                // Crear una fila con los valores obtenidos
                                Object[] fila = new Object[]{
                                    idFolio,
                                    idPrestamo,
                                    idLibro,
                                    idUsuario
                                };

                                // Agregar la fila al modelo de la tabla
                                modeloTabla.addRow(fila);

                                // Llenar el JComboBox con el título del libro
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
        // Definir la consulta SQL para buscar folios por título de libro
        String consulta = "CALL BuscarFolioPorTitulo(?)";

        // Obtener el modelo de la tabla
        DefaultTableModel modeloTabla = (DefaultTableModel) tabla.getModel();
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de llenarla con nuevos datos

        try ( Connection conexion = Modelo.conectar();  PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {
            // Establecer el parámetro (título de libro) en la consulta
            preparedStatement.setString(1, tituloLibro);

            try ( ResultSet resultado = preparedStatement.executeQuery()) {
                boolean hayResultados = false;

                // Llenar el ComboBox con los títulos de libros encontrados
                llenarComboBoxLibros(comboBoxLibros, resultado);

                // Llenar la tabla con los resultados
                while (resultado.next()) {
                    hayResultados = true;

                    // Obtener los valores de cada columna del resultado
                    int idFolio = resultado.getInt("idFolio");
                    String titulo = resultado.getString("tituloLibro");
                    int idUsuario = resultado.getInt("idUsuario");
                    String nombreUsuario = resultado.getString("nombreUsuario");
                    String estado = resultado.getString("estado");

                    // Crear una fila con los valores obtenidos
                    Object[] fila = new Object[]{
                        idFolio,
                        titulo,
                        idUsuario,
                        nombreUsuario,
                        estado
                    };

                    // Agregar la fila al modelo de la tabla
                    modeloTabla.addRow(fila);
                }

                if (!hayResultados) {
                    JOptionPane.showMessageDialog(null, "No se encontraron folios para el título de libro proporcionado.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Retornar la tabla con los resultados
        return tabla;
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
    
    //Metodo para actualizar un el estado de un folio
    public static void updateFolio(int idprestamo, String nombreLibro, boolean estado){
        
    }

    //------------------METODOS PARA EL APARTADO DE LIBROS--------------------\\
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
     * @return Sobrecargar el metodo de buscar un libro
     */
    public static JTable buscarLibro(JTable tabla, String nombreLibro) {
        // Definir el nombre del procedimiento almacenado a llamar
        String callProcedure = "CALL BuscarLibrosPorNombre(?)";

        // Obtener el modelo de la tabla
        DefaultTableModel modeloTabla = (DefaultTableModel) tabla.getModel();
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de llenarla con nuevos datos

        try ( Connection conexion = Modelo.conectar();  CallableStatement callableStatement = conexion.prepareCall(callProcedure)) {

            // Establecer el parámetro para el procedimiento almacenado
            callableStatement.setString(1, nombreLibro);

            try ( ResultSet resultado = callableStatement.executeQuery()) {
                while (resultado.next()) {
                    // Obtener los datos de la consulta
                    String titulo = resultado.getString("titulo");
                    String autor = resultado.getString("autor");
                    String genero = resultado.getString("genero");
                    String editorial = resultado.getString("editorial");

                    // Agregar una nueva fila a la tabla con los datos obtenidos
                    modeloTabla.addRow(new Object[]{titulo, autor, genero, editorial});
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Retornar la tabla con los resultados
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
}
