/*
 * Rendon Estrada Jorge
 * 04/07/2023
 * Descripcion: Panel de Prestamo que se muestra en el Dashboard 
 * Contiene la tabla de los prestamos y los botones con las acciones
 */

package vistas;


/**
 *
 * @author Jorge
 */
public class Prestamo extends javax.swing.JPanel {

    /** Creates new form Usuarios */
    public Prestamo() {
        initComponents();
        IniStyles();
        TextPrompt busq = new TextPrompt("INGRESE EL ID DEL PRESTAMO", txt_busqueda);
    }
    
    //Metodos
    
    ///Metodo que contiene los estilos del LOOK & FEEL
    private void IniStyles(){
        //Hacer los text area redondeados
        txt_busqueda.putClientProperty( "JComponent.roundRect", true );
        //Hacer el boton con bordes redondeados
        btn_Buscar.putClientProperty( "JButton.buttonType", "roundRect" );
        btn_Nuevo.putClientProperty( "JButton.buttonType", "roundRect" );
        btn_Eliminar.putClientProperty( "JButton.buttonType", "roundRect" );   
        btn_Folio.putClientProperty( "JButton.buttonType", "roundRect" );   
    }
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txt_busqueda = new javax.swing.JTextField();
        btn_Buscar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Prestamos = new javax.swing.JTable();
        btn_Eliminar = new javax.swing.JButton();
        btn_Nuevo = new javax.swing.JButton();
        btn_Folio = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txt_busqueda.setBackground(new java.awt.Color(255, 255, 255));
        txt_busqueda.setForeground(new java.awt.Color(0, 0, 0));

        btn_Buscar.setBackground(new java.awt.Color(0, 1, 248));
        btn_Buscar.setForeground(new java.awt.Color(255, 255, 255));
        btn_Buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buscar.png"))); // NOI18N
        btn_Buscar.setText("Buscar");

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

        tbl_Prestamos.setBackground(new java.awt.Color(255, 255, 255));
        tbl_Prestamos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "CANTIDAD", "FECHA PRESTAMO", "FECHA DEV", "ADEUDO", "ID USUARIO"
            }
        ));
        jScrollPane1.setViewportView(tbl_Prestamos);

        btn_Eliminar.setBackground(new java.awt.Color(0, 1, 248));
        btn_Eliminar.setForeground(new java.awt.Color(255, 255, 255));
        btn_Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/borrar24px.png"))); // NOI18N
        btn_Eliminar.setText("Eliminar");

        btn_Nuevo.setBackground(new java.awt.Color(0, 1, 248));
        btn_Nuevo.setForeground(new java.awt.Color(255, 255, 255));
        btn_Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/libro24px.png"))); // NOI18N
        btn_Nuevo.setText("Nuevo");

        btn_Folio.setBackground(new java.awt.Color(0, 1, 248));
        btn_Folio.setForeground(new java.awt.Color(255, 255, 255));
        btn_Folio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/agregar-archivo.png"))); // NOI18N
        btn_Folio.setText("FOLIO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(txt_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(btn_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(223, 223, 223)
                .addComponent(btn_Eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(62, 62, 62)
                .addComponent(btn_Nuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(32, 32, 32)
                .addComponent(btn_Folio)
                .addGap(178, 178, 178))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Buscar)
                    .addComponent(txt_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Folio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_Eliminar)
                        .addComponent(btn_Nuevo)))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_Buscar;
    public javax.swing.JButton btn_Eliminar;
    public javax.swing.JButton btn_Folio;
    public javax.swing.JButton btn_Nuevo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tbl_Prestamos;
    public javax.swing.JTextField txt_busqueda;
    // End of variables declaration//GEN-END:variables

}
