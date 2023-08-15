/*
 * Rendon Estrada Jorge
 * 04/07/2023
 * Descripcion: Panel de administrador que contiene botones para añadir 
 */

package vistas;

import java.awt.Color;

/**
 *
 * @author Jorge
 */
public class Agregar extends javax.swing.JPanel {

    /** Creates new form Usuarios */
    public Agregar() {
        initComponents();
        //Mando a llamar el metodo del LOOK & FEEL
        IniStyles();
        //
        
    }
    
    //Metodo que contiene los estilos del LOOK & FEEL
     private void IniStyles(){
         //Estilo del boton
         btn_Admin.putClientProperty( "JButton.buttonType", "roundRect" );
         btn_Autor.putClientProperty( "JButton.buttonType", "roundRect" );
         btn_Editorial.putClientProperty( "JButton.buttonType", "roundRect" );
         btn_Genero.putClientProperty( "JButton.buttonType", "roundRect" );
         btn_Regresar.putClientProperty( "JButton.buttonType", "roundRect" );
         btn_Edit.putClientProperty( "JButton.buttonType", "roundRect" );
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
        btn_Admin = new javax.swing.JButton();
        btn_Autor = new javax.swing.JButton();
        btn_Editorial = new javax.swing.JButton();
        btn_Genero = new javax.swing.JButton();
        btn_Regresar = new javax.swing.JButton();
        btn_Edit = new javax.swing.JButton();

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

        btn_Admin.setBackground(new java.awt.Color(0, 1, 248));
        btn_Admin.setForeground(new java.awt.Color(255, 255, 255));
        btn_Admin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/administrador.png"))); // NOI18N
        btn_Admin.setText("Administrador");

        btn_Autor.setBackground(new java.awt.Color(0, 1, 248));
        btn_Autor.setForeground(new java.awt.Color(255, 255, 255));
        btn_Autor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autor.png"))); // NOI18N
        btn_Autor.setText("Autor");

        btn_Editorial.setBackground(new java.awt.Color(0, 1, 248));
        btn_Editorial.setForeground(new java.awt.Color(255, 255, 255));
        btn_Editorial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/diseno-editorial.png"))); // NOI18N
        btn_Editorial.setText("Editorial");

        btn_Genero.setBackground(new java.awt.Color(0, 1, 248));
        btn_Genero.setForeground(new java.awt.Color(255, 255, 255));
        btn_Genero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/generos.png"))); // NOI18N
        btn_Genero.setText("Genero");

        btn_Regresar.setBackground(new java.awt.Color(0, 1, 248));
        btn_Regresar.setForeground(new java.awt.Color(255, 255, 255));
        btn_Regresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/volver.png"))); // NOI18N
        btn_Regresar.setText("Regresar");

        btn_Edit.setBackground(new java.awt.Color(0, 1, 248));
        btn_Edit.setForeground(new java.awt.Color(255, 255, 255));
        btn_Edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usuario24px.png"))); // NOI18N
        btn_Edit.setText("Editar");

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Regresar)
                .addGap(355, 355, 355))
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_Edit)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(btn_Admin)
                        .addGap(29, 29, 29)
                        .addComponent(btn_Autor)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(btn_Editorial)
                .addGap(88, 88, 88)
                .addComponent(btn_Genero)
                .addGap(79, 79, 79))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106)
                .addComponent(btn_Edit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Admin)
                    .addComponent(btn_Autor)
                    .addComponent(btn_Editorial)
                    .addComponent(btn_Genero))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(btn_Regresar)
                .addGap(144, 144, 144))
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
    public javax.swing.JButton btn_Admin;
    public javax.swing.JButton btn_Autor;
    public javax.swing.JButton btn_Edit;
    public javax.swing.JButton btn_Editorial;
    public javax.swing.JButton btn_Genero;
    public javax.swing.JButton btn_Regresar;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables

}
