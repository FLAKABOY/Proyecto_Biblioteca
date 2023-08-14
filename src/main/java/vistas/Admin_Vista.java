/*
 * Rendon Estrada Jorge
 * 04/07/2023
 * Descripcion: Panel de la vista de administrador con conultas a datos
 * especificos de la base de datos
 */

package vistas;

import java.awt.Color;

/**
 *
 * @author Jorge
 */
public class Admin_Vista extends javax.swing.JPanel {

    /** Creates new form Usuarios */
    public Admin_Vista() {
        initComponents();
        //Mando a llamar el metodo del LOOK & FEEL
        IniStyles();
        //
       TextPrompt txtU = new TextPrompt("ID de usuario", txt_idUsuario);
    }
    
    //Metodo que contiene los estilos del LOOK & FEEL
     private void IniStyles(){
         //Estilos de las cajas de texto
         txt_idUsuario.putClientProperty( "JComponent.roundRect", true );
         //Estilo del boton
         btn_adeudoPendiente.putClientProperty( "JButton.buttonType", "roundRect" );
         btn_agregar.putClientProperty( "JButton.buttonType", "roundRect" );
         btn_buscarPorFecha.putClientProperty( "JButton.buttonType", "roundRect" );
         btn_historial.putClientProperty( "JButton.buttonType", "roundRect" );
         btn_prestamoVencido.putClientProperty( "JButton.buttonType", "roundRect" );
         btn_usuarioMasPrestamos.putClientProperty( "JButton.buttonType", "roundRect" );
     }
     
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btn_prestamoVencido = new javax.swing.JButton();
        btn_usuarioMasPrestamos = new javax.swing.JButton();
        btn_adeudoPendiente = new javax.swing.JButton();
        btn_historial = new javax.swing.JButton();
        btn_agregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_busqueda = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jdc_fechaInicio = new com.toedter.calendar.JDateChooser();
        jdc_fechaFin = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btn_buscarPorFecha = new javax.swing.JButton();
        txt_idUsuario = new javax.swing.JTextField();

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setForeground(new java.awt.Color(0, 0, 0));

        jPanel3.setBackground(new java.awt.Color(0, 0, 153));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 51, Short.MAX_VALUE)
        );

        btn_prestamoVencido.setBackground(new java.awt.Color(0, 1, 248));
        btn_prestamoVencido.setForeground(new java.awt.Color(255, 255, 255));
        btn_prestamoVencido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fecha-de-vencimiento.png"))); // NOI18N
        btn_prestamoVencido.setText("Prestamos vencidos");

        btn_usuarioMasPrestamos.setBackground(new java.awt.Color(0, 1, 248));
        btn_usuarioMasPrestamos.setForeground(new java.awt.Color(255, 255, 255));
        btn_usuarioMasPrestamos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usuario_mas_prestamo.png"))); // NOI18N
        btn_usuarioMasPrestamos.setText("Usuario con mas prestamos");

        btn_adeudoPendiente.setBackground(new java.awt.Color(0, 1, 248));
        btn_adeudoPendiente.setForeground(new java.awt.Color(255, 255, 255));
        btn_adeudoPendiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atrasado.png"))); // NOI18N
        btn_adeudoPendiente.setText("Adeudos Pendientes");

        btn_historial.setBackground(new java.awt.Color(0, 1, 248));
        btn_historial.setForeground(new java.awt.Color(255, 255, 255));
        btn_historial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/historial.png"))); // NOI18N
        btn_historial.setText("Historial");

        btn_agregar.setBackground(new java.awt.Color(0, 1, 248));
        btn_agregar.setForeground(new java.awt.Color(255, 255, 255));
        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/agregar.png"))); // NOI18N
        btn_agregar.setText("Añadir");

        tbl_busqueda.setBackground(new java.awt.Color(255, 255, 255));
        tbl_busqueda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbl_busqueda);

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Busqueda por fecha");

        jdc_fechaInicio.setDateFormatString("yyyy/MM/dd");

        jdc_fechaFin.setDateFormatString("yyyy/MM/dd");

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Inicio");

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Fin");

        btn_buscarPorFecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lupa.png"))); // NOI18N
        btn_buscarPorFecha.setText("Buscar");

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 765, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(btn_prestamoVencido)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_usuarioMasPrestamos)
                                .addGap(66, 66, 66)
                                .addComponent(btn_adeudoPendiente)
                                .addGap(56, 56, 56))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(bgLayout.createSequentialGroup()
                                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(bgLayout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jdc_fechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(bgLayout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addGap(18, 18, 18)
                                                .addComponent(jdc_fechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_buscarPorFecha)
                                        .addGap(77, 77, 77)
                                        .addComponent(btn_agregar)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn_historial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_idUsuario))
                                .addGap(110, 110, 110))))))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_prestamoVencido)
                    .addComponent(btn_usuarioMasPrestamos)
                    .addComponent(btn_adeudoPendiente))
                .addGap(18, 18, 18)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jdc_fechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(23, 23, 23)
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jdc_fechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn_buscarPorFecha)
                                    .addComponent(btn_agregar))
                                .addGap(45, 45, 45))))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(btn_historial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_idUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    public javax.swing.JButton btn_adeudoPendiente;
    public javax.swing.JButton btn_agregar;
    public javax.swing.JButton btn_buscarPorFecha;
    public javax.swing.JButton btn_historial;
    public javax.swing.JButton btn_prestamoVencido;
    public javax.swing.JButton btn_usuarioMasPrestamos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public com.toedter.calendar.JDateChooser jdc_fechaFin;
    public com.toedter.calendar.JDateChooser jdc_fechaInicio;
    public javax.swing.JTable tbl_busqueda;
    public javax.swing.JTextField txt_idUsuario;
    // End of variables declaration//GEN-END:variables

}
