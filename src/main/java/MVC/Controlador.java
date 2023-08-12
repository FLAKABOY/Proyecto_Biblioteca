/*
 * Rendon Estrada Jorge
 * Fecha: 24/07/2023
 * Descripcion: clase controladora para Modelo,Vista,Controlador
 * Se coloca la funcionalidad de los Botones en esta clase, asi como
 * la llamada a los metodos de la BD, se instacian todos los paneles y se
 * deben poner los atributos de los paneles (Botones,Cajas de texto,etc)
 * con el modificador de accceso public para poder acceder a ellos desde
 * esta clase
 */
package MVC;

import MVC.Modelo.Libro;
import MVC.Modelo.Usuario;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
//Importar el JFrame principal
import pantallas.Dashjboard;
//Importar todos los Paneles de vistas
import vistas.*;

/**
 *
 * @author Jorge
 */
//Se crea la clase y le implementamos la interface 
public class Controlador implements ActionListener, KeyListener {

    //Atributos
    private Modelo modelo;
    private Dashjboard vista;
    //Declarar todo lo de usuarios
    private Usuarios usuariosPanel;
    private Usuarios_Nuevo usuarioN;
    private Usuarios_Borrar usuarioB;
    private Usuarios_Editar usuarioE;

    //Declarar todo lo de prestamos
    private Prestamo prestamoPanel;
    private Prestamo_Nuevo prestamoNuevo;
    private Prestamo_Nuevo_Datos prestamoNuevoDatos;
    private Prestamo_Nuevo_Generar prestamoNuevoGenerar;
    private Prestamo_Nuevo_Vincular prestamoNuevoVincular;
    private Prestamo_Eliminar prestamoEliminar;

    //Declarar todo lo de devoluciones
    private Devoluciones devolucionesPanel;
    private Devoluciones_Editar devolucionesEditar;

    //Declarar todo lo de Libros
    private Libros librosPanel;
    private Libros_Nuevos librosNuevos;
    private Libros_Editar librosEditar;
    private Libros_Borrar librosBorrar;

    //Constructor
    /*Se colocan el contructor de parametros y se le pasan los parametros
    correspondientes, el Modelo se encargara de todo el funcionamiento y 
    operaciones de la Base de Datos y vista sera todo lo visual que se le
    mostrara al usuario*/
    public Controlador(Modelo modelo, Dashjboard vista) {
        vista.setVisible(true);
        this.modelo = modelo;
        this.vista = vista;

        //Crear el objeto del panel de los usuarios
        this.usuariosPanel = new Usuarios(); // Crear la instancia del panel Usuarios
        this.usuarioN = new Usuarios_Nuevo();
        this.usuarioB = new Usuarios_Borrar();
        this.usuarioE = new Usuarios_Editar();

        //Crear los objetos de los paneles de Prestamos
        this.prestamoPanel = new Prestamo();
        this.prestamoNuevo = new Prestamo_Nuevo();
        this.prestamoNuevoDatos = new Prestamo_Nuevo_Datos();
        this.prestamoNuevoGenerar = new Prestamo_Nuevo_Generar();
        this.prestamoNuevoVincular = new Prestamo_Nuevo_Vincular();
        this.prestamoEliminar = new Prestamo_Eliminar();

        //Crear los objetos del panel de Devoluciones
        this.devolucionesPanel = new Devoluciones();
        this.devolucionesEditar = new Devoluciones_Editar();

        //Cear los objetos de los paneles de Libros
        this.librosPanel = new Libros();
        this.librosNuevos = new Libros_Nuevos();
        this.librosEditar = new Libros_Editar();
        this.librosBorrar = new Libros_Borrar();

        //Botones del Dashjboard
        this.vista.btn_home.addActionListener(this);
        this.vista.btn_users.addActionListener(this);
        this.vista.btn_prestamos.addActionListener(this);
        this.vista.btn_devoluciones.addActionListener(this);
        this.vista.btn_libros.addActionListener(this);
        this.vista.btn_reportes.addActionListener(this);

        // Botones del panel Usuarios
        this.usuariosPanel.btn_Borrar.addActionListener(this);
        this.usuariosPanel.btn_Nuevo.addActionListener(this);
        this.usuariosPanel.btn_Editar.addActionListener(this);
        this.usuariosPanel.btn_Buscar.addActionListener(this);

        //Boton del panel usuario nuevo
        this.usuarioN.btn_Guardar.addActionListener(this);

        //Boton del panel Usuario Borrar
        this.usuarioB.btn_Borrar.addActionListener(this);
        this.usuarioB.btn_Bname.addActionListener(this);
        this.usuarioB.btn_Bid.addActionListener(this);
        //Botones del usuario editar
        this.usuarioE.btn_Guardar.addActionListener(this);
        this.usuarioE.btn_Bname.addActionListener(this);
        this.usuarioE.btn_Bid.addActionListener(this);

        //Botones del panel de prestamos
        this.prestamoPanel.btn_Nuevo.addActionListener(this);
        this.prestamoPanel.btn_Eliminar.addActionListener(this);
        this.prestamoPanel.btn_Buscar.addActionListener(this);
        this.prestamoPanel.btn_Folio.addActionListener(this);

        //Botones del panel de Prestamo Nuevo
        this.prestamoNuevo.btn_Nuevo.addActionListener(this);
        this.prestamoNuevo.btn_BuscarN.addActionListener(this);
        this.prestamoNuevo.btn_Buscar.addActionListener(this);

        //Botones del panel de prestamo Para generar el prestamo
        this.prestamoNuevoDatos.btn_Nuevo.addActionListener(this);
        this.prestamoNuevoDatos.btn_Nom.addActionListener(this);
        this.prestamoNuevoDatos.btn_BuscarId.addActionListener(this);

        //Botones del panel para vincular los libros con el prestamo generado
        this.prestamoNuevoGenerar.btn_Nuevo.addActionListener(this);
        this.prestamoNuevoGenerar.btn_BuscarId.addActionListener(this);
        this.prestamoNuevoGenerar.btn_Nom.addActionListener(this);
        this.prestamoNuevoGenerar.btn_regresar.addActionListener(this);

        /*Botones del panel para vincular con folio en caso de no completarlo
        en el proceso normal*/
        this.prestamoNuevoVincular.btn_BuscarId.addActionListener(this);
        this.prestamoNuevoVincular.btn_Nom.addActionListener(this);
        this.prestamoNuevoVincular.btn_Vincular.addActionListener(this);
        this.prestamoNuevoVincular.btn_regresar.addActionListener(this);

        //Botodes para el panel de Devoluciones
        this.devolucionesPanel.btn_Editar.addActionListener(this);
        this.devolucionesPanel.btn_Buscar.addActionListener(this);

        //Botones para el panel de Devoluciones Editar
        this.devolucionesEditar.btn_BuscarFolio.addActionListener(this);
        this.devolucionesEditar.btn_BuscarNombreLibro.addActionListener(this);
        this.devolucionesEditar.btn_Guardar.addActionListener(this);

        //Botones para el panel de Libros
        this.librosPanel.btn_Nuevo.addActionListener(this);
        this.librosPanel.btn_Editar.addActionListener(this);
        this.librosPanel.btn_Borrar.addActionListener(this);
        this.librosPanel.btn_Buscar.addActionListener(this);

        //Botones para el panel de Libro Nuevo
        this.librosNuevos.btn_Guardar.addActionListener(this);

        //Botones para el panel de Libros Editar
        this.librosEditar.btn_Update.addActionListener(this);
        this.librosEditar.btn_Bname.addActionListener(this);
        this.librosEditar.btn_Bid.addActionListener(this);

        //Botones para el panel de Libros Borrar
        this.librosBorrar.btn_Eliminar.addActionListener(this);
        this.librosBorrar.btn_Bid.addActionListener(this);
        this.librosBorrar.btn_Bname.addActionListener(this);

        /*En este apartado se agregaran los keyListener para limitar
        la cantidad de caracteres a ingresar en los JTextField o el tipo de
        caracteres que se ingresaran en los mismos(Numericos o Letras)*/
        //keyListener para los TextField de el Panel de usuario
        this.usuariosPanel.txt_busqueda.addKeyListener(this);

        //KeyListener para el panel de Usuarios Nuevos
        this.usuarioN.txt_Nombre.addKeyListener(this);
        this.usuarioN.txt_Direct.addKeyListener(this);
        this.usuarioN.txt_Tel.addKeyListener(this);

        //keyListener para los TextField de el panel de Usuario Editar
        this.usuarioE.txt_Bid.addKeyListener(this);
        this.usuarioE.txt_Bname.addKeyListener(this);
        this.usuarioE.txt_Direct.addKeyListener(this);
        this.usuarioE.txt_Nombre.addKeyListener(this);
        this.usuarioE.txt_Tel.addKeyListener(this);

        //KeyListener para los TextField de el panel de Usuarios Borrar
        this.usuarioB.txt_Bid.addKeyListener(this);
        this.usuarioB.txt_Bname.addKeyListener(this);

        //KeyListener para los TextField de el panel de prestamos
        this.prestamoPanel.txt_busqueda.addKeyListener(this);

        //KeyListener para los TextField de el panel de Prestamos Nuevos
        this.prestamoNuevo.txt_Id.addKeyListener(this);
        this.prestamoNuevo.txt_BuscarN.addKeyListener(this);

        //KeyListener para los TextField de el panel de Prestamos Nuevo Datos
        this.prestamoNuevoDatos.txt_Id.addKeyListener(this);
        this.prestamoNuevoDatos.txt_Nom.addKeyListener(this);
        //this.prestamoNuevoDatos.txt_cantLib.addKeyListener(this);
        this.prestamoNuevoDatos.txt_idUser.addKeyListener(this);

        //KeyListener para los TextField de el panel de Prestamos Nuevos Generar
        this.prestamoNuevoGenerar.txt_Id.addKeyListener(this);
        this.prestamoNuevoGenerar.txt_Nom.addKeyListener(this);

        //KeyListener para los TextField de el panel de Prestamos Nuevos Vincular
        this.prestamoNuevoVincular.txt_Id.addKeyListener(this);
        this.prestamoNuevoVincular.txt_Nom.addKeyListener(this);
        this.prestamoNuevoVincular.txt_idPrestamo.addKeyListener(this);

        //KeyListener para los TextField de el panel de Prestamo Eliminar
        this.prestamoEliminar.txt_Id.addKeyListener(this);
        this.prestamoEliminar.txt_Id_Prestamo.addKeyListener(this);

        //KeyListener para los TextField de el panel de Devoluciones
        this.devolucionesPanel.txt_busqueda.addKeyListener(this);

        //KeyListener para los TextField de el panel de Devoluciones Editar
        this.devolucionesEditar.txt_IdPrestamo.addKeyListener(this);
        this.devolucionesEditar.txt_Nombrelibro.addKeyListener(this);

        //KeyListener para los TextField de el panel de Libros
        this.librosPanel.txt_busqueda.addKeyListener(this);

        //KeyListener para los TextField de el panel de Libros Nuevos
        this.librosNuevos.txt_Titulo.addKeyListener(this);
        this.librosNuevos.txt_cantLib.addKeyListener(this);

        //KeyListener para los TextField de el panel de Libros Editar
        this.librosEditar.txt_Bid.addKeyListener(this);
        this.librosEditar.txt_Bname.addKeyListener(this);
        this.librosEditar.txt_Titulo.addKeyListener(this);

        //KeyListener para los TextField de el panel de Libros Borrar
        this.librosBorrar.txt_Bid.addKeyListener(this);
        this.librosBorrar.txt_Bname.addKeyListener(this);

    }

    //Metodo para colocar el panel
    //Metodo para poner el panel correspondiente
    private JPanel vista(JPanel p) {
        //780,472
        /*
        Se tiene el problema que no se logra adaptar el JPanel importado al
        JPanel de la vista principal lo cual deja al programa con mala estetica
        al no poder lograr hacerlo responsivo. (Maestra ayudeme)
         */
        p.setSize(790, 460);
        p.setLocation(0, 0);

        vista.content.removeAll();
        vista.content.add(p, BorderLayout.CENTER);
        vista.content.revalidate();
        vista.content.repaint();
        return vista.content;
    }

    //Metodo para los botones de las vistas y colocar los paneles
    /**
     *
     * @param evento
     */
    @Override
    public void actionPerformed(ActionEvent evento) {
        //Se coloca la funcionalidad de los botones principales     de la vista
        //Para colocar el panel de Home
        if (vista.btn_home == evento.getSource()) {
            try {
                vista.content = vista(new Home());
                limpiarTexto();

            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
            //Para colocar el panel principal de los usuarios 
        } else if (vista.btn_users == evento.getSource()) {
            try {
                limpiarTexto();
                //Colocar el panel de Usuarios
                vista.content = vista(usuariosPanel);
                //Llenar la tabla de usuarios
                Modelo.mostrarUsers(usuariosPanel.tbl_users);
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        } //Para colocar el Panel de prestamos
        else if (vista.btn_prestamos == evento.getSource()) {
            try {
                limpiarTexto();
                vista.content = vista(prestamoPanel);
                prestamoPanel.tbl_Prestamos = Modelo.mostrarPrestamos(prestamoPanel.tbl_Prestamos);
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        } //Para colocar el panel de devoluciones
        else if (vista.btn_devoluciones == evento.getSource()) {
            try {
                limpiarTexto();
                vista.content = vista(devolucionesPanel);
                devolucionesPanel.tbl_Devoluciones = Modelo.mostrarFolios(devolucionesPanel.tbl_Devoluciones);
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        } //Para colocar el panel de Libros
        else if (vista.btn_libros == evento.getSource()) {
            try {
                limpiarTexto();
                vista.content = vista(librosPanel);
                librosPanel.tbl_Libros = Modelo.mostrarLibros(librosPanel.tbl_Libros);
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        } //Para colocar el panel de reportes
        else if (vista.btn_reportes == evento.getSource()) {//Hace falta el panel de reportes
            try {
                limpiarTexto();
                //vista.content = vista(new Usuarios());
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        }

        //Apatado para la configuracion de toda la funcionalidad de Usuarios
        //Hacer los botones de el panel de Usuarios\\
        //Agregar
        if (usuariosPanel.btn_Nuevo == evento.getSource()) {
            try {
                //Mostrar panel de usuario nuevo
                vista.content = vista(usuarioN);
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        } else if (usuariosPanel.btn_Borrar == evento.getSource()) {
            try {
                //Mostrar panel de Borrar usuario
                vista.content = vista(usuarioB);
                Modelo.mostrarUsers(usuarioB.tbl_users_borrar);
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }

        } else if (usuariosPanel.btn_Editar == evento.getSource()) {
            try {
                //Mostrar panel de editar usuario
                vista.content = vista(usuarioE);
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        } else if (usuariosPanel.btn_Buscar == evento.getSource()) {
            try {//Mandar a llamar a modelo con el metodo de buscar usuario
                Modelo.buscarUser(usuariosPanel.tbl_users, usuariosPanel.txt_busqueda.getText());

            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        }

        //Apatado para la configuracion de toda la funcionalidad de Usuarios Nuevos
        //Hacer los botones de el panel de Usuarios Nuevos\\
        if (usuarioN.btn_Guardar == evento.getSource()) {
            try {
                //Mandar a llamar el objeto modelo con el metodo de alta de usuario
                //Validar que los campos contengan datos para insertar los datos
                if (!usuarioN.txt_Nombre.getText().isEmpty() && !usuarioN.txt_Direct.getText().isEmpty() && !usuarioN.txt_Tel.getText().isEmpty()) {
                    Modelo.altaUsuario(usuarioN.txt_Nombre.getText(),
                            usuarioN.txt_Direct.getText(),
                            usuarioN.txt_Tel.getText());
                    //Volver a la panel principal de usuarios
                    vista.content = vista(usuariosPanel);
                    //Limpiar campos
                    limpiarTexto();
                } else {
                    JOptionPane.showMessageDialog(null, "FAVOR DE LLENAR LOS CAMPOS CORRESPONDIENTES");
                }

            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        }

        //Apatado para la configuracion de toda la funcionalidad de Usuarios Borrar
        //Hacer los botones de el panel de Usuarios Borrar\\
        if (usuarioB.btn_Borrar == evento.getSource()) {
            try {//Llamar al modelo con el metodo de eliminacion logica
                if (!usuarioB.txt_Bid.getText().isEmpty()) {
                    Modelo.logicDelete(Integer.parseInt(usuarioB.txt_Bid.getText()));
                    //Volver al panel principal de usuarios
                    vista.content = vista(usuariosPanel);
                    Modelo.mostrarUsers(usuariosPanel.tbl_users);
                } else {
                    JOptionPane.showMessageDialog(null, "FAVOR DE INGRESAR UN ID PARA EL PROCESO");
                }
                //Limpiar los campos
                limpiarTexto();
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        } else if (usuarioB.btn_Bname == evento.getSource()) {
            try {//Mandar a llamar a modelo con el metodo Buscar Usuario por ID
                usuarioB.tbl_users_borrar = Modelo.buscarUser(usuarioB.tbl_users_borrar, usuarioB.txt_Bname.getText());

            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        } else if (usuarioB.btn_Bid == evento.getSource()) {
            try { //Mandar a llamar a modelo con el metodo Buscar Usuario por ID
                usuarioB.tbl_users_borrar = Modelo.buscarUser(usuarioB.tbl_users_borrar, Integer.parseInt(usuarioB.txt_Bid.getText()));

            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        }

        //Apatado para la configuracion de toda la funcionalidad de Usuarios Editar
        //Hacer los botones de el panel de Usuarios Editar\\
        if (usuarioE.btn_Guardar == evento.getSource()) {
            try {
                //Validar que los campos no sean vacios
                if (!usuarioE.txt_Nombre.getText().isEmpty() && !usuarioE.txt_Direct.getText().isEmpty() && !usuarioE.txt_Tel.getText().isEmpty()) {
                    //Llamar al modelo con el metodo de GuardarUsuario
                    Modelo.updateUser(Integer.parseInt(usuarioE.txt_Bid.getText()),
                            usuarioE.txt_Nombre.getText(),
                            usuarioE.txt_Direct.getText(),
                            usuarioE.txt_Tel.getText(),
                            usuarioE.cb_estado.getSelectedItem().toString());

                    //Hacemos que la tabla de usuarios se actualice
                    Modelo.mostrarUsers(usuariosPanel.tbl_users);
                    //Limpiar campos
                    limpiarTexto();
                    //Volver al panel principal de usuarios
                    vista.content = vista(usuariosPanel);
                } else {
                    JOptionPane.showMessageDialog(null, "LOS CAMPOS NO PUEDEN SER VACIOS");
                }

            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        } else if (usuarioE.btn_Bname == evento.getSource()) {
            try {//Mandar a llamar al modelo con el metodo de buscar Usuario por Nombre
                colocarDatosUserName(new Usuario());

            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "NO EXISTEN SIMILITUDES CON EL USUARIO INGRESADO");
            }
        } else if (usuarioE.btn_Bid == evento.getSource()) {
            try {//Mandar a llamar al modelo con el metodo Buscar Usuario por ID
                //Se pasa como parametro un objeto de la sublcase Usuario
                colocarDatosUserId(new Usuario());

            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "EL ID INGRESADO NO EXISTE");
            }
        }

        //***************************PRESTAMOS********************************\\
        /*Apatado para la configuracion de toda la funcionalidad de Prestamos*/
        //Hacer los botones de el panel de Prestamos\\
        if (prestamoPanel.btn_Nuevo == evento.getSource()) {
            try {
                //Mostrar el panel para poder generar el registro de prestamos
                vista.content = vista(prestamoNuevo);
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        } else if (prestamoPanel.btn_Eliminar == evento.getSource()) {
            try {
                //Mostrar el panel para poder eliminar un prestamo
                vista.content = vista(prestamoEliminar);
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        } else if (prestamoPanel.btn_Buscar == evento.getSource()) {
            try {
                //Llamar a modelo con el metodo para hacer la busqueda por ID
                prestamoPanel.tbl_Prestamos = Modelo.buscarPrestamo(prestamoPanel.tbl_Prestamos,
                        Integer.parseInt(prestamoPanel.txt_busqueda.getText()));
            } catch (RuntimeException e) {

            }
        } else if (prestamoPanel.btn_Folio == evento.getSource()) {
            try {
                vista.content = vista(prestamoNuevoVincular);
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        }

        /*Apatado para la configuracion el panel de Prestamo Nuevo */
        //Hacer los botones de el panel de Prestamos Nuevos\\
        if (prestamoNuevo.btn_Nuevo == evento.getSource()) {
            try {
                //Mostrar el panel para llenar los datos del prestamo
                vista.content = vista(prestamoNuevoDatos);
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        } else if (prestamoNuevo.btn_BuscarN == evento.getSource()) {
            try {
                //Llamar a modelo con el metodo de buscar por Nombre
                prestamoNuevo.tbl_Prestamo_Nuevo = Modelo.buscarPrestamo(prestamoNuevo.tbl_Prestamo_Nuevo,
                        prestamoNuevo.txt_BuscarN.getText());
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        } else if (prestamoNuevo.btn_Buscar == evento.getSource()) {
            try {
                //Llamar a modelo con el metodo de buscar prestamo por ID
                prestamoNuevo.tbl_Prestamo_Nuevo = Modelo.buscarPrestamo(prestamoNuevo.tbl_Prestamo_Nuevo,
                        Integer.parseInt(prestamoNuevo.txt_Id.getText()));
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        }

        /*Apatado para la configuracion el panel para Llenar los datos */
        //Hacer los botones de el panel de Prestamos Nuevo Datos\\
        if (prestamoNuevoDatos.btn_Nuevo == evento.getSource()) {
            try {
                //Validar que los campos no sean vacios
                if (!prestamoNuevoDatos.txt_idUser.getText().isEmpty() && !prestamoNuevoDatos.cb_cantlib.getSelectedItem().toString().isEmpty()) {
                    //Convertir los datos entero
                    Modelo.generarPrestamo(Integer.parseInt(prestamoNuevoDatos.txt_idUser.getText()),
                            Integer.parseInt(prestamoNuevoDatos.cb_cantlib.getSelectedItem().toString()));
                    //Pasar al panel para Generrar folio y relacionarlo con prestamo
                    vista.content = vista(prestamoNuevoGenerar);
                } else {
                    JOptionPane.showMessageDialog(null, "FAVOR DE INGRESAR EL ID Y LA CANTIDAD DE LIBROS PARA GENERAR EL REGISTRO ");
                }
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        } else if (prestamoNuevoDatos.btn_BuscarId == evento.getSource()) {
            //Mandar a llamar el modelo con el metodo de buscar un prestamo con ID
            Modelo.buscarUser(prestamoNuevoDatos.tbl_Prestamo_Nuevo_Datos,
                    Integer.parseInt(prestamoNuevoDatos.txt_Id.getText()));
        } else if (prestamoNuevoDatos.btn_Nom == evento.getSource()) {
            //Mandatr a llamar a modelo con el metodo buscar prestamo
            Modelo.buscarUser(prestamoNuevoDatos.tbl_Prestamo_Nuevo_Datos,
                    prestamoNuevoDatos.txt_Nom.getText());
        }

        /*Apatado para la configuracion el panel para Ingresar datos a la tabla folio */
        //Hacer los botones de el panel de Prestamos Nuevo Generar\\
        if (prestamoNuevoGenerar.btn_Nuevo == evento.getSource()) {
            try {
                //Mandar a llamar al metodo para ingresar datos en la tabla de folio
                //Validar que el campo no este vacio
                if (!prestamoNuevoGenerar.txt_Id.getText().isEmpty()) {
                    Modelo.generarFolio(Integer.parseInt(prestamoNuevoGenerar.txt_Id.getText()));
                } else {
                    JOptionPane.showMessageDialog(null, "FAVOR DE INGRESAR EL ID DEL LIBRO");
                }

            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        } else if (prestamoNuevoGenerar.btn_BuscarId == evento.getSource()) {
            try {
                //Mandar a llamar al metodo para buscar libro por ID
                prestamoNuevoGenerar.tbl_Prestamo_Nuevo_Generar = Modelo.buscarLibro(prestamoNuevoGenerar.tbl_Prestamo_Nuevo_Generar,
                        Integer.parseInt(prestamoNuevoGenerar.txt_Id.getText()));

            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        } else if (prestamoNuevoGenerar.btn_Nom == evento.getSource()) {
            try {
                //Mandar a llamar al metodo de buscar libro por nombre
                prestamoNuevoGenerar.tbl_Prestamo_Nuevo_Generar = Modelo.buscarLibro(prestamoNuevoGenerar.tbl_Prestamo_Nuevo_Generar,
                        prestamoNuevoGenerar.txt_Nom.getText());
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        } else if (prestamoNuevoGenerar.btn_regresar == evento.getSource()) {
            try {
                //Se regresa a la pantalla principal de prestamos
                prestamoPanel.tbl_Prestamos = Modelo.mostrarPrestamos(prestamoPanel.tbl_Prestamos);
                vista.content = vista(prestamoPanel);
                limpiarTexto();
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        }

        /*Apatado para la configuracion el panel para Ingresar datos a la tabla
        folio en caso de no llenarlos de la manera convencional */
        if (prestamoNuevoVincular.btn_BuscarId == evento.getSource()) {
            try {
                //Mandar a llamar al metodo para buscar libro por ID
                prestamoNuevoVincular.tbl_Prestamo_Nuevo_Vincular = Modelo.buscarLibro(prestamoNuevoVincular.tbl_Prestamo_Nuevo_Vincular,
                        Integer.parseInt(prestamoNuevoVincular.txt_Id.getText()));

            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        } else if (prestamoNuevoVincular.btn_Nom == evento.getSource()) {
            try {
                //Mandar a llamar al metodo de buscar libro por nombre
                prestamoNuevoVincular.tbl_Prestamo_Nuevo_Vincular = Modelo.buscarLibro(prestamoNuevoVincular.tbl_Prestamo_Nuevo_Vincular,
                        prestamoNuevoVincular.txt_Nom.getText());
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        } else if (prestamoNuevoVincular.btn_Vincular == evento.getSource()) {
            try {
                //Validar que los campos no esten vacios
                if (!prestamoNuevoVincular.txt_idPrestamo.getText().isEmpty() && !prestamoNuevoVincular.txt_Id.getText().isEmpty()) {
                    Modelo.generarFolio(Integer.parseInt(prestamoNuevoVincular.txt_idPrestamo.getText()),
                            Integer.parseInt(prestamoNuevoVincular.txt_Id.getText()));
                } else {
                    JOptionPane.showMessageDialog(null, "Favor de ingresar el ID del prestamo y el ID del libro");
                }
            } catch (RuntimeException e) {

            }
        } else if (prestamoNuevoVincular.btn_regresar == evento.getSource()) {
            try {
                //Se regresa a la pantalla principal de prestamos
                prestamoPanel.tbl_Prestamos = Modelo.mostrarPrestamos(prestamoPanel.tbl_Prestamos);
                vista.content = vista(prestamoPanel);
                limpiarTexto();
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        }

        //Apartado donde se configura lo de ELIMINAR UN PRESTAMO
        if (prestamoEliminar.btn_Eliminar == evento.getSource()) {
            try {
                //Validar que el campo no este vacio
                if (!prestamoEliminar.txt_Id_Prestamo.getText().isEmpty()) {
                    //Llamada a Modelo con el metodo de eliminacion logica

                }
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }

        } else if (prestamoEliminar.btn_BuscarId == evento.getSource()) {
            try {
                //Llamada a Modelo con el metodo de buscar prestamo por ID del usuario
                prestamoEliminar.tbl_Prestamos_Eliminar = Modelo.buscarPrestamoIdUsuario(prestamoEliminar.tbl_Prestamos_Eliminar,
                        Integer.parseInt(prestamoEliminar.txt_Id.getText()));
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        } else if (prestamoEliminar.btn_Id2 == evento.getSource()) {
            try {
                //Llamada a Modelo con el metodo de buscar prestamo por ID del prestamo
                prestamoEliminar.tbl_Prestamos_Eliminar = Modelo.buscarPrestamo(prestamoEliminar.tbl_Prestamos_Eliminar,
                        Integer.parseInt(prestamoEliminar.txt_Id_Prestamo.getText()));

            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        }

        /*Apatado para la configuracion del panel principal de Devoluciones */
        //Hacer los botones de el panel de Devoluciones\\
        if (devolucionesPanel.btn_Editar == evento.getSource()) {
            try {
                //Mostrar el panel de Devoluciones editar
                vista.content = vista(devolucionesEditar);
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        } else if (devolucionesPanel.btn_Buscar == evento.getSource()) {
            try {
                if (!devolucionesPanel.txt_busqueda.getText().isEmpty()) {
                    //Llamar al modelo con el metodo de buscar folios vinculados al ID del prestamo
                    devolucionesPanel.tbl_Devoluciones = Modelo.buscarFolios(devolucionesPanel.tbl_Devoluciones,
                            Integer.parseInt(devolucionesPanel.txt_busqueda.getText()),
                            devolucionesEditar.cbx_Libro);
                } else {
                    Modelo.mostrarFolios(devolucionesPanel.tbl_Devoluciones);
                }

            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        }

        /*Apatado para la configuracion del panel principal de Devoluciones Editar */
        //Hacer los botones de el panel de Devoluciones Editar\\
        if (devolucionesEditar.btn_Guardar == evento.getSource()) {
            try {
                //Validar que los campos necesarios para actualizar no sean vacios
                if (!devolucionesEditar.cbx_Libro.getSelectedItem().toString().isEmpty() && (devolucionesEditar.rb_Entregado.isSelected() || devolucionesEditar.rb_Pendiente.isSelected())) {
                    if (devolucionesEditar.rb_Entregado.isSelected()) {
                        Modelo.updateFolio(Integer.parseInt(devolucionesEditar.txt_IdPrestamo.getText()),
                                devolucionesEditar.cbx_Libro.getSelectedItem().toString(),
                                false);

                    } else if (devolucionesEditar.rb_Pendiente.isSelected()) {
                        // Acciones a realizar cuando el botón rb_Pendiente está seleccionado
                        Modelo.updateFolio(Integer.parseInt(devolucionesEditar.txt_IdPrestamo.getText()),
                                devolucionesEditar.cbx_Libro.getSelectedItem().toString(),
                                true);

                    }
                }
                //Llamar a modelo con el metodo de actualizar el estado de un folio

                //Regresar al panle principal de devoluciones
                vista.content = vista(devolucionesPanel);
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        } else if (devolucionesEditar.btn_BuscarNombreLibro == evento.getSource()) {
            try {
                //Llamar a modelo para buscar en la tabla folio por nombre del libro
                devolucionesEditar.tbl_Devoluciones_Editar = Modelo.buscarFolio(
                        devolucionesEditar.tbl_Devoluciones_Editar,
                        devolucionesEditar.txt_Nombrelibro.getText(),
                        devolucionesEditar.cbx_Libro);
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        } else if (devolucionesEditar.btn_BuscarFolio == evento.getSource()) {
            try {
                //Llamar a modelo con el metodo de buscar folio por Id de prestamo
                devolucionesEditar.tbl_Devoluciones_Editar = Modelo.buscarFolios(
                        devolucionesEditar.tbl_Devoluciones_Editar,
                        Integer.parseInt(devolucionesEditar.txt_IdPrestamo.getText()),
                        devolucionesEditar.cbx_Libro);
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        }

        /*Apatado para la configuracion del panel principal de Libros*/
        //Hacer los botones de el panel de Libros\\
        if (librosPanel.btn_Nuevo == evento.getSource()) {
            try {
                //Mandar a llamar al panel de libro nuevo
                vista.content = vista(librosNuevos);
                //Llenar los Jcombo box de el panel con lo registrado en la BD
                Modelo.llenarComboBoxGeneros(librosNuevos.cb_genero);
                Modelo.llenarComboBoxAutores(librosNuevos.cb_autor);
                Modelo.llenarComboBoxEditoriales(librosNuevos.cb_editorial);
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        } else if (librosPanel.btn_Editar == evento.getSource()) {
            try {
                //Mandar a llamar al panel de libro nuevo
                vista.content = vista(librosEditar);
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        } else if (librosPanel.btn_Borrar == evento.getSource()) {
            try {
                if (vista.btn_libros == evento.getSource()) {
                    vista.content = vista(librosBorrar);
                }
                //Mandar a llamar al panel de libro Borrar
                vista.content = vista(librosBorrar);
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        } else if (librosPanel.btn_Buscar == evento.getSource()) {
            try {
                //Mandar a llamar el metodo de buscar el libro por el nombre
                librosPanel.tbl_Libros = Modelo.buscarLibro(librosPanel.tbl_Libros,
                        librosPanel.txt_busqueda.getText());
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        }

        //Apartado para el panel de Libros_Nuevos
        if (librosNuevos.btn_Guardar == evento.getSource()) {
            try {
                if (!librosNuevos.txt_Titulo.getText().isEmpty() && !librosNuevos.txt_cantLib.getText().isEmpty()) {
                    //Mandar a llamar el metodo Insertar el nuevo libro
                    Modelo.altaLibro(librosNuevos.txt_Titulo.getText(),
                            librosNuevos.cb_autor.getSelectedItem().toString(),
                            librosNuevos.cb_genero.getSelectedItem().toString(),
                            librosNuevos.cb_editorial.getSelectedItem().toString(),
                            Integer.parseInt(librosNuevos.txt_cantLib.getText()));

                    //Volver actualizar la tabla del panel de libros con el Nuevo regirto generado
                    librosPanel.tbl_Libros = Modelo.mostrarLibros(librosPanel.tbl_Libros);
                    //Limpiar todos los campos utilizados
                    limpiarTexto();
                    //Volver al panel principal de libros
                    vista.content = vista(librosPanel);
                } else {
                    JOptionPane.showMessageDialog(null, "FAVOR DE LLENAR LOS CAMPOS CORRESPONDIENTES");
                }

            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        }

        //Apartado para el panel de Libros_Editar
        if (librosEditar.btn_Bid == evento.getSource()) {
            try {
                //Mandar a llamar el metodo de buscar el libro por ID
                colocarDatosLibroId(new Libro());
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        } else if (librosEditar.btn_Bname == evento.getSource()) {
            try {
                //Mandar a llamar el metodo de buscar el libro por el nombre
                colocarDatosLibroTitulo(new Libro());
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        } else if (librosEditar.btn_Update == evento.getSource()) {
            try {
                //Comprobar que los campos no esten vacios
                if (!librosEditar.txt_Bid.getText().isEmpty() && !librosEditar.txt_Titulo.getText().isEmpty() && !librosEditar.cb_autor.getSelectedItem().toString().isEmpty() && !librosEditar.cb_genero.getSelectedItem().toString().isEmpty() && !librosEditar.cb_estado.getSelectedItem().toString().isEmpty() && !librosEditar.cb_editorial.getSelectedItem().toString().isEmpty()) {
                    //Mandar a llamar el metodo de actualizar los datos del libro
                    Modelo.updateLibro(Integer.parseInt(librosEditar.txt_Bid.getText()),
                            librosEditar.txt_Titulo.getText(),
                            librosEditar.cb_autor.getSelectedItem().toString(),
                            librosEditar.cb_genero.getSelectedItem().toString(),
                            librosEditar.cb_editorial.getSelectedItem().toString(),
                            librosEditar.cb_estado.getSelectedItem().toString());
                    
                    //Actualizar la tabla con los datos
                    librosPanel.tbl_Libros = Modelo.mostrarLibros(librosPanel.tbl_Libros);
                    //Volver a al panel de libros
                    vista.content = vista(librosPanel);

                } else {
                    JOptionPane.showMessageDialog(null, "Los campos a actualizar no pueden ser vacios.");
                }

            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        }

        //Apartado para el panel de Libros_Borrar
        if (librosBorrar.btn_Bid == evento.getSource()) {
            try {
                //Mandar a llamar el metodo de buscar el libro por ID
                librosBorrar.tbl_Libros_Borrar = Modelo.buscarLibro(librosBorrar.tbl_Libros_Borrar, Integer.parseInt(librosBorrar.txt_Bid.getText()));
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        } else if (librosBorrar.btn_Bname == evento.getSource()) {
            try {
                //Mandar a llamar el metodo de buscar el libro por el nombre
                librosBorrar.tbl_Libros_Borrar = Modelo.buscarLibro(librosBorrar.tbl_Libros_Borrar, librosBorrar.txt_Bname.getText());

            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        } else if (librosBorrar.btn_Eliminar == evento.getSource()) {
            try {
                //Mandar a llamar el metodo de eliminacion logica de un libro
                if (!librosBorrar.txt_Bid.getText().isEmpty()) {

                } else {
                    JOptionPane.showMessageDialog(null, "FAVOR DE INGRESAR EL ID DEL LIBRO");
                }
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "ERROR GENERAL FAVOR DE LLAMAR AL ESPECIALISTA");
            }
        }

        /*Apatado para la configuracion del panel de Reportes*/
        //Hacer los botones de el panel de Reportes\\
    }//Llave del metodo actionPerformed

    /*Sobreescribir todos los metodos los metodos abtractosos de la interface
    KeyListener de los cuales solo usaremos keyTyped*/
    @Override
    public void keyTyped(KeyEvent evento) {
        //Variable que se utiliza para las validaciones
        char c = evento.getKeyChar();
        //Aqui se colocara la validacion de caracteres del panel de usuarios
        if (usuariosPanel.txt_busqueda == evento.getSource()) {
            /*La primera condicion es para que no supere el limite de
            caracteres que permite la Base de Datos*/
            if (usuariosPanel.txt_busqueda.getText().length() >= 50) {
                evento.consume();
            } /*Esta segunda condicion es para validar que solamente se puedan
            ingresar letras y numeros, asi evitamos caracteres especiales y 
            se protege de alguna SQL Injection*/ else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' ')) {
                evento.consume();
            }
        }

        //Aqui se colocara la validacion de caracteres del panel de usuarios Nuevos
        if (usuarioN.txt_Nombre == evento.getSource()) {
            /*La primera condicion es para que no supere el limite de
            caracteres que permite la Base de Datos*/
            if (usuarioN.txt_Nombre.getText().length() >= 50) {
                evento.consume();
            } /*Esta segunda condicion es para validar que solamente se puedan
            ingresar letras y numeros, asi evitamos caracteres especiales y 
            se protege de alguna SQL Injection*/ else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' ')) {
                evento.consume();
            }
        }

        if (usuarioN.txt_Direct == evento.getSource()) {
            /*La primera condicion es para que no supere el limite de
            caracteres que permite la Base de Datos*/
            if (usuarioN.txt_Direct.getText().length() >= 100) {
                evento.consume();
            } /*Esta segunda condicion es para validar que solamente se puedan
            ingresar letras y numeros, asi evitamos caracteres especiales y 
            se protege de alguna SQL Injection*/ else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '0' || c > '9') && (c != ' ') && (c != '#') && (c != '.')) {
                evento.consume();
            }
        }

        if (usuarioN.txt_Tel == evento.getSource()) {
            /*La primera condicion es para que no supere el limite de
            caracteres que permite la Base de Datos*/
            if (usuarioN.txt_Tel.getText().length() >= 10) {
                evento.consume();
            } /*Esta segunda condicion es para validar que solamente se puedan
            ingresar letras y numeros, asi evitamos caracteres especiales y 
            se protege de alguna SQL Injection*/ else if (c < '0' || c > '9') {
                evento.consume();
            }
        }
        //**Aqui se colocara la validacion de caracteres del panel de Usuarios Editar**//
        if (usuarioE.txt_Bid == evento.getSource()) {
            if (c < '0' || c > '9') {
                evento.consume();
            }
        }

        if (usuarioE.txt_Bname == evento.getSource()) {
            if (usuarioE.txt_Bname.getText().length() >= 50) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' ')) {
                evento.consume();
            }
        }

        if (usuarioE.txt_Direct == evento.getSource()) {
            if (usuarioE.txt_Direct.getText().length() >= 100) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '0' || c > '9') && (c != ' ')) {
                evento.consume();
            }
        }

        if (usuarioE.txt_Nombre == evento.getSource()) {
            if (usuarioE.txt_Nombre.getText().length() >= 50) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' ')) {
                evento.consume();
            }
        }

        if (usuarioE.txt_Tel == evento.getSource()) {
            if (usuarioE.txt_Tel.getText().length() >= 10) {
                evento.consume();
            } else if (c < '0' || c > '9') {
                evento.consume();
            }
        }

        //**Aqui se colocara la validacion de caracteres del panel de Usuarios Borrar**//
        if (usuarioB.txt_Bid == evento.getSource()) {
            if (c < '0' || c > '9') {
                evento.consume();
            }
        }

        if (usuarioB.txt_Bname == evento.getSource()) {
            if (usuarioB.txt_Bname.getText().length() >= 50) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' ')) {
                evento.consume();
            }
        }

        //**Aqui se colocara la validacion de caracteres de los paneles de Prestamos**//
        //Panel principal de prestamos\\
        if (prestamoPanel.txt_busqueda == evento.getSource()) {
            if (prestamoPanel.txt_busqueda.getText().length() >= 100) {
                evento.consume();
            } else if (c < '0' || c > '9') {
                evento.consume();
            }
        }

        //Panel de prestamo_Nuevo\\
        if (prestamoNuevo.txt_Id == evento.getSource()) {
            if (c < '0' || c > '9') {
                evento.consume();
            }
        }

        if (prestamoNuevo.txt_BuscarN == evento.getSource()) {
            if (prestamoNuevo.txt_BuscarN.getText().length() >= 50) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' ')) {
                evento.consume();
            }
        }

        //Panel de prestamo_Nuevo_Datos\\
        //Caja de texto de busqueda del id del libro
        if (prestamoNuevoDatos.txt_Id == evento.getSource()) {
            if (c < '0' || c > '9') {
                evento.consume();
            }
        }
        //Caja de texto de busqueda del nombre del libro
        if (prestamoNuevoDatos.txt_Nom == evento.getSource()) {
            if (prestamoNuevoDatos.txt_Nom.getText().length() >= 100) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' ')) {
                evento.consume();
            }
        }
        //Caja de texto para el id del usuario
        if (prestamoNuevoDatos.txt_idUser == evento.getSource()) {
            if (c < '0' || c > '9') {
                evento.consume();
            }
        }

        //Panel de prestamo_Nuevo_Datos_Generar\\
        //Caja de texto para ingresar el id del libro a buscar
        if (prestamoNuevoGenerar.txt_Id == evento.getSource()) {
            if (c < '0' || c > '9') {
                evento.consume();
            }
        }
        //Caja de texto para ingresar el titulo del libro a buscar
        if (prestamoNuevoGenerar.txt_Nom == evento.getSource()) {
            if (prestamoNuevoGenerar.txt_Nom.getText().length() >= 100) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' ')) {
                evento.consume();
            }
        }
        //Caja de texto para el panel de vincular los libros con el prestamo
        //Texto para el id del prestamo
        if (prestamoNuevoVincular.txt_idPrestamo == evento.getSource()) {
            if (c < '0' || c > '9') {
                evento.consume();
            }
        }

        //caja de texto para buscar libro por id
        if (prestamoNuevoVincular.txt_Id == evento.getSource()) {
            if (c < '0' || c > '9') {
                evento.consume();
            }
        }

        //Caja de texto para buscar libro por nombre
        if (prestamoNuevoVincular.txt_Nom == evento.getSource()) {
            if (prestamoNuevoVincular.txt_Nom.getText().length() >= 100) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '0' || c > '9') && (c != ' ')) {
                evento.consume();
            }
        }

        //Caja de texto para el panel de Eliminar prestamo
        if (prestamoEliminar.txt_Id == evento.getSource()) {
            if (c < '0' || c > '9') {
                evento.consume();
            }
        }

        if (prestamoEliminar.txt_Id_Prestamo == evento.getSource()) {
            if (c < '0' || c > '9') {
                evento.consume();
            }
        }

        //**Aqui se colocara la validacion de caracteres de los paneles de Devoluciones**//
        //Panel de Devoluciones
        if (devolucionesPanel.txt_busqueda == evento.getSource()) {
            if (c < '0' || c > '9') {
                evento.consume();
            }
        }

        //Panel de devoluciones editar
        //Caja de texto para buscar por Idprestamo
        if (devolucionesEditar.txt_IdPrestamo == evento.getSource()) {
            if (c < '0' || c > '9') {
                evento.consume();
            }
        }

        //Caja de texto para buscar el nombre del Usuario
        if (devolucionesEditar.txt_Nombrelibro == evento.getSource()) {
            if (librosPanel.txt_busqueda.getText().length() >= 100) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '0' || c > '9') && (c != ' ')) {
                evento.consume();
            }
        }

        //**Aqui se colocara la validacion de caracteres de los paneles de Libros**//
        //Panel principal de libros\\
        if (librosPanel.txt_busqueda == evento.getSource()) {
            if (librosPanel.txt_busqueda.getText().length() >= 100) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '0' || c > '9') && (c != ' ')) {
                evento.consume();
            }
        }

        //Panel de Libros nuevos\\
        if (librosNuevos.txt_Titulo == evento.getSource()) {
            if (librosNuevos.txt_Titulo.getText().length() >= 100) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '0' || c > '9') && (c != ' ')) {
                evento.consume();
            }
        }

        //Caja de texto de para la cantidad de libros
        if (librosNuevos.txt_cantLib == evento.getSource()) {
            if (c < '0' || c > '9') {
                evento.consume();
            }
        }

        //Panel de Libros Editar\\
        //Caja de texto que busca el libro por su ID
        if (librosEditar.txt_Bid == evento.getSource()) {
            if (c < '0' || c > '9') {
                evento.consume();
            }
        }

        //Caja de texto que busca el libro por su nombre
        if (librosEditar.txt_Bname == evento.getSource()) {
            if (librosEditar.txt_Bname.getText().length() >= 100) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '0' || c > '9') && (c != ' ')) {
                evento.consume();
            }
        }

        //Caja de texto donde se ingresara el titulo editado para guardar
        if (librosEditar.txt_Titulo == evento.getSource()) {
            if (librosEditar.txt_Titulo.getText().length() >= 100) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '0' || c > '9') && (c != ' ')) {
                evento.consume();
            }
        }

        //Panel de libros borrar\\
        //Caja de texto que busca por ID
        if (librosBorrar.txt_Bid == evento.getSource()) {
            if (c < '0' || c > '9') {
                evento.consume();
            }
        }

        //Caja de texto que busca por nombre del libro
        if (librosBorrar.txt_Bname == evento.getSource()) {
            if (librosBorrar.txt_Bname.getText().length() >= 100) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '0' || c > '9') && (c != ' ')) {
                evento.consume();
            }
        }

    }//Llave del metodo keyTyped

    @Override
    public void keyPressed(KeyEvent e) {
    }

    /*Este metodo se utilizaria para colocar todos los caracteres en las cajas
    de texto mayusculas pero se elimino la idea por los siguients motivos:
    1.-Presentación y Legibilidad
    2.-Conversión al Mostrar Datos*/
    @Override
    public void keyReleased(KeyEvent evento) {
//        if(usuariosPanel.txt_busqueda == evento.getSource()){
//            usuariosPanel.txt_busqueda.setText(usuariosPanel.txt_busqueda.getText().toUpperCase());
//        }
    }

    /*Metodo que se utilizara para limpiar todas las cajas de texto de los paneles
    esto ayudara a mas comodidad y evitar fallos para los usuarios*/
    public void limpiarTexto() {
        //Apartado de usuarios
        usuariosPanel.txt_busqueda.setText("");

        usuarioN.txt_Nombre.setText("");
        usuarioN.txt_Direct.setText("");
        usuarioN.txt_Tel.setText("");

        usuarioE.txt_Bid.setText("");
        usuarioE.txt_Bname.setText("");
        usuarioE.txt_Direct.setText("");
        usuarioE.txt_Nombre.setText("");
        usuarioE.txt_Tel.setText("");
        usuarioE.cb_estado.removeAllItems();

        usuarioB.txt_Bid.setText("");
        usuarioB.txt_Bname.setText("");

        //Apartado de prestamo
        prestamoPanel.txt_busqueda.setText("");

        prestamoNuevo.txt_BuscarN.setText("");
        prestamoNuevo.txt_Id.setText("");

        prestamoNuevoDatos.txt_Id.setText("");
        prestamoNuevoDatos.txt_Nom.setText("");
        prestamoNuevoDatos.txt_idUser.setText("");
        //Limpiar la tabla
        DefaultTableModel modeloTabla = (DefaultTableModel) prestamoNuevoDatos.tbl_Prestamo_Nuevo_Datos.getModel();
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de llenarla con nuevos datos

        prestamoNuevoGenerar.txt_Id.setText("");
        prestamoNuevoGenerar.txt_Nom.setText("");

        prestamoEliminar.txt_Id.setText("");
        prestamoEliminar.txt_Id_Prestamo.setText("");

        //Apartado de devoluciones
        devolucionesPanel.txt_busqueda.setText("");

        devolucionesEditar.txt_IdPrestamo.setText("");
        devolucionesEditar.txt_Nombrelibro.setText("");

        //Apartado de libros
        librosPanel.txt_busqueda.setText("");

        librosNuevos.txt_Titulo.setText("");

        librosEditar.txt_Bid.setText("");
        librosEditar.txt_Bname.setText("");
        librosEditar.txt_Titulo.setText("");

        librosBorrar.txt_Bid.setText("");
        librosBorrar.txt_Bname.setText("");

        //Limpiar los jCoboBox
        librosNuevos.cb_autor.removeAllItems();
        librosNuevos.cb_editorial.removeAllItems();
        librosNuevos.cb_genero.removeAllItems();

        librosEditar.cb_autor.removeAllItems();
        librosEditar.cb_editorial.removeAllItems();
        librosEditar.cb_genero.removeAllItems();
    }//Fin del metodo de limpiar los campos

    //Metodo para llenar los campos de editar usuario y sea mas facil la modificacion
    public void colocarDatosUserId(Usuario usuario) {
        usuario = Modelo.datosUser(Integer.parseInt(usuarioE.txt_Bid.getText()));

        //Colocar los datos del usuario
        usuarioE.txt_Nombre.setText(usuario.getNombre());
        usuarioE.txt_Direct.setText(usuario.getDireccion());
        usuarioE.txt_Tel.setText(usuario.getTelefono());
        //En caso de que se cumpla una condicion se llenara el comboBox
        if (usuario.getEstado()) {
            usuarioE.cb_estado.insertItemAt("ACTIVO", 0);
            usuarioE.cb_estado.insertItemAt("INACTIVO", 1);
            usuarioE.cb_estado.setSelectedItem("ACTIVO");
        } else {
            usuarioE.cb_estado.insertItemAt("INACTIVO", 0);
            usuarioE.cb_estado.insertItemAt("ACTIVO", 1);
            usuarioE.cb_estado.setSelectedItem("INACTIVO");
        }
    }

    public void colocarDatosUserName(Usuario usuario) {
        usuario = Modelo.datosUser(usuarioE.txt_Bname.getText());

        //Colocar los datos del usuario
        usuarioE.txt_Bid.setText(Integer.toString(usuario.getIdUsuario()));
        usuarioE.txt_Nombre.setText(usuario.getNombre());
        usuarioE.txt_Direct.setText(usuario.getDireccion());
        usuarioE.txt_Tel.setText(usuario.getTelefono());
        //En caso de que se cumpla una condicion se llenara el comboBox
        if (usuario.getEstado()) {
            usuarioE.cb_estado.insertItemAt("ACTIVO", 0);
            usuarioE.cb_estado.insertItemAt("INACTIVO", 1);
            usuarioE.cb_estado.setSelectedItem("ACTIVO");
        } else {
            usuarioE.cb_estado.insertItemAt("INACTIVO", 0);
            usuarioE.cb_estado.insertItemAt("ACTIVO", 1);
            usuarioE.cb_estado.setSelectedItem("INACTIVO");
        }
    }

    //Metodo para llenar los campos de editar Libro y sea mas facil la modificacion
    public void colocarDatosLibroId(Libro libro) {
        //Vaciar los combo box
        librosEditar.cb_genero.removeAllItems();
        librosEditar.cb_autor.removeAllItems();
        librosEditar.cb_editorial.removeAllItems();
        librosEditar.cb_estado.removeAllItems();

        //Se obtienen los valores del libro
        libro = Modelo.datosLibro(Integer.parseInt(librosEditar.txt_Bid.getText()));

        //Colocar datos de del libro
        librosEditar.cb_genero.insertItemAt(libro.getGenero(), 0);
        librosEditar.cb_autor.insertItemAt(libro.getAutor(), 0);
        librosEditar.cb_editorial.insertItemAt(libro.getEditorial(), 0);

        //Mostrar el valor en los Combo Box
        librosEditar.cb_genero.setSelectedItem(libro.getGenero());
        librosEditar.cb_autor.setSelectedItem(libro.getAutor());
        librosEditar.cb_editorial.setSelectedItem(libro.getEditorial());

        //Colocar el titulo del libro en la caja de texto
        librosEditar.txt_Titulo.setText(libro.getTitulo());
        //Colocar el id para el UPDATE
        librosEditar.txt_Bid.setText(Integer.toString(libro.getId()));

        //En caso de que se cumpla una condicion se llenara el comboBox
        if (libro.getEstado()) {
            librosEditar.cb_estado.insertItemAt("ACTIVO", 0);
            librosEditar.cb_estado.insertItemAt("INACTIVO", 1);
            librosEditar.cb_estado.setSelectedItem("ACTIVO");
        } else {
            librosEditar.cb_estado.insertItemAt("INACTIVO", 0);
            librosEditar.cb_estado.insertItemAt("ACTIVO", 1);
            librosEditar.cb_estado.setSelectedItem("INACTIVO");
        }
        //Llenar los combo box con el resto de los datos para facilitar la
        //Edicion del registro
        Modelo.llenarComboBoxAutores(librosEditar.cb_autor);
        Modelo.llenarComboBoxEditoriales(librosEditar.cb_editorial);
        Modelo.llenarComboBoxGeneros(librosEditar.cb_genero);
    }

    public void colocarDatosLibroTitulo(Libro libro) {
        //Vaciar los combo box
        librosEditar.cb_genero.removeAllItems();
        librosEditar.cb_autor.removeAllItems();
        librosEditar.cb_editorial.removeAllItems();
        librosEditar.cb_estado.removeAllItems();

        //Se obtienen los valores del libro
        libro = Modelo.datosLibros(librosEditar.txt_Bname.getText());

        //Colocar datos de del libro
        librosEditar.cb_genero.insertItemAt(libro.getGenero(), 0);
        librosEditar.cb_autor.insertItemAt(libro.getAutor(), 0);
        librosEditar.cb_editorial.insertItemAt(libro.getEditorial(), 0);

        //Mostrar el valor en los Combo Box
        librosEditar.cb_genero.setSelectedItem(libro.getGenero());
        librosEditar.cb_autor.setSelectedItem(libro.getAutor());
        librosEditar.cb_editorial.setSelectedItem(libro.getEditorial());

        //Colocar el titulo del libro en la caja de texto
        librosEditar.txt_Titulo.setText(libro.getTitulo());
        //Colocar el id para el UPDATE
        librosEditar.txt_Bid.setText(Integer.toString(libro.getId()));
        
        //En caso de que se cumpla una condicion se llenara el comboBox
        if (libro.getEstado()) {
            librosEditar.cb_estado.insertItemAt("ACTIVO", 0);
            librosEditar.cb_estado.insertItemAt("INACTIVO", 1);
            librosEditar.cb_estado.setSelectedItem("ACTIVO");
        } else {
            librosEditar.cb_estado.insertItemAt("INACTIVO", 0);
            librosEditar.cb_estado.insertItemAt("ACTIVO", 1);
            librosEditar.cb_estado.setSelectedItem("INACTIVO");
        }
        //Llenar los combo box con el resto de los datos para facilitar la
        //Edicion del registro
        Modelo.llenarComboBoxAutores(librosEditar.cb_autor);
        Modelo.llenarComboBoxEditoriales(librosEditar.cb_editorial);
        Modelo.llenarComboBoxGeneros(librosEditar.cb_genero);
    }

}//Llave de la clase Controlador
