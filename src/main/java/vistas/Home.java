/*
 * Rendon Estrada Jorge 11/05/2023
 * Descripcion: Frame principal del programa
 */
package vistas;

import java.awt.Color;

/**
 *
 * @author FLAKABOY
 */
public class Home extends javax.swing.JPanel {

    /**
     * Creates new form Home
     */
    public Home() {
        initComponents();
        this.IniStyles();
    }
    
    //Metodo que contiene los estilos del LOOK & FEEL
    private void IniStyles(){
        titulo.putClientProperty( "FlatLaf.style", "font: bold $h3.regular.font"  );
        titulo.setForeground(Color.black);
        text1.putClientProperty("FlatLaf.styleClass", "large");
        text1.setForeground(Color.black);
        text2.putClientProperty("FlatLaf.styleClass", "large");
        text2.setForeground(Color.black);
        text3.putClientProperty("FlatLaf.styleClass", "large");
        text3.setForeground(Color.black);
        text4.putClientProperty("FlatLaf.styleClass", "large");
        text4.setForeground(Color.black);
        text5.putClientProperty("FlatLaf.styleClass", "large");
        text5.setForeground(Color.black);
        text6.putClientProperty("FlatLaf.styleClass", "large");
        text6.setForeground(Color.black);
        text7.putClientProperty("FlatLaf.styleClass", "large");
        text7.setForeground(Color.black);
        text8.putClientProperty("FlatLaf.styleClass", "large");
        text8.setForeground(Color.black);
        text9.putClientProperty("FlatLaf.styleClass", "large");
        text9.setForeground(Color.black);
        text10.putClientProperty("FlatLaf.styleClass", "large");
        text10.setForeground(Color.black);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        text1 = new javax.swing.JLabel();
        text2 = new javax.swing.JLabel();
        text3 = new javax.swing.JLabel();
        text4 = new javax.swing.JLabel();
        text5 = new javax.swing.JLabel();
        text6 = new javax.swing.JLabel();
        text7 = new javax.swing.JLabel();
        text8 = new javax.swing.JLabel();
        text9 = new javax.swing.JLabel();
        text10 = new javax.swing.JLabel();
        img = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(780, 472));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg.setBackground(new java.awt.Color(255, 255, 255));

        titulo.setForeground(new java.awt.Color(0, 0, 0));
        titulo.setText("BIENVENIDOS");

        text1.setForeground(new java.awt.Color(0, 0, 0));
        text1.setText("Sistema de administración para una Biblioteca. Tenga el control");

        text2.setForeground(new java.awt.Color(0, 0, 0));
        text2.setText("y administrelo de una manera más óptima y sencilla");

        text3.setForeground(new java.awt.Color(0, 0, 0));
        text3.setText("Este software te permitira llevar un control más completo de su");

        text4.setForeground(new java.awt.Color(0, 0, 0));
        text4.setText("Biblioteca tendras funcionalidades especificas como las siguientes");

        text5.setForeground(new java.awt.Color(0, 0, 0));
        text5.setText("1.-Registro de los Usuarios y Libros Nuevos");

        text6.setForeground(new java.awt.Color(0, 0, 0));
        text6.setText("2.-Edicion de los Usuarios y Libros existentes");

        text7.setForeground(new java.awt.Color(0, 0, 0));
        text7.setText("3.-Eliminar registros");

        text8.setForeground(new java.awt.Color(0, 0, 0));
        text8.setText("4.-Préstamos");

        text9.setForeground(new java.awt.Color(0, 0, 0));
        text9.setText("5.-Devoluciones");

        text10.setForeground(new java.awt.Color(0, 0, 0));
        text10.setText("6.-Seccion de reportes de acciones en el sistema");

        img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/libro_homeP.png"))); // NOI18N

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(text1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(113, 113, 113))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(text10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(81, 81, 81)))
                        .addGap(413, 413, 413))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(text4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(text7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(125, 125, 125))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(text5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(136, 136, 136))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(text6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(125, 125, 125))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(text8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(305, 305, 305))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(text9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(292, 292, 292))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(text2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(text3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(21, 21, 21)))
                        .addGap(26, 26, 26)
                        .addComponent(img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(107, 107, 107))))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(titulo, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(text1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(text2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(32, 32, 32)
                        .addComponent(text3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(img, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(text10, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addGap(93, 93, 93))
        );

        add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JLabel img;
    private javax.swing.JLabel text1;
    private javax.swing.JLabel text10;
    private javax.swing.JLabel text2;
    private javax.swing.JLabel text3;
    private javax.swing.JLabel text4;
    private javax.swing.JLabel text5;
    private javax.swing.JLabel text6;
    private javax.swing.JLabel text7;
    private javax.swing.JLabel text8;
    private javax.swing.JLabel text9;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
