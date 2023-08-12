/*
 * Rendon Estrada Jorge
 * 04/07/2023
 * Descripcion: Panel de Usuarios_Nuevo que se muestra en el Dashboard 
 * Contiene las acciones para editar un libro
 */

package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author Jorge
 */
public class Libros_Editar extends javax.swing.JPanel {

    /** Creates new form Usuarios */
    public Libros_Editar() {
        initComponents();
        //Mando a llamar el metodo del LOOK & FEEL
        IniStyles();
        //colocar place holder
        TextPrompt bnom = new TextPrompt("Ingrese el titulo del libro", txt_Bname);
        TextPrompt bid = new TextPrompt("Ingrese el ID del libro", txt_Bid);
        
    }
    
    //Metodo que contiene los estilos del LOOK & FEEL
     private void IniStyles(){
        lbl_Tit.putClientProperty("FlatLaf.styleClass", "large");
        lbl_Tit.setForeground(Color.black);
        lbl_Autor.putClientProperty("FlatLaf.styleClass", "large");
        lbl_Autor.setForeground(Color.black);
        lbl_Idioma.putClientProperty("FlatLaf.styleClass", "large");
        lbl_Idioma.setForeground(Color.black);
        //Hacer los Combobox redondeados
        cb_autor.putClientProperty( "JComponent.roundRect", true );
        cb_editorial.putClientProperty( "JComponent.roundRect", true );
        cb_genero.putClientProperty( "JComponent.roundRect", true );
        //Hacer los TextField Redondeados
        txt_Bname.putClientProperty( "JComponent.roundRect", true );
        txt_Bid.putClientProperty( "JComponent.roundRect", true );
        txt_Titulo.putClientProperty( "JComponent.roundRect", true );
        //Hacer el boton con bordes redondeados
        btn_Update.putClientProperty( "JButton.buttonType", "roundRect" );
        btn_Bname.putClientProperty( "JButton.buttonType", "roundRect" );
        btn_Bid.putClientProperty( "JButton.buttonType", "roundRect" );
        
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
        txt_Bname = new javax.swing.JTextField();
        btn_Bname = new javax.swing.JButton();
        txt_Bid = new javax.swing.JTextField();
        btn_Bid = new javax.swing.JButton();
        lbl_Tit = new javax.swing.JLabel();
        txt_Titulo = new javax.swing.JTextField();
        lbl_Idioma = new javax.swing.JLabel();
        lbl_Autor = new javax.swing.JLabel();
        btn_Update = new javax.swing.JButton();
        img = new javax.swing.JLabel();
        cb_genero = new javax.swing.JComboBox<>();
        cb_autor = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        cb_editorial = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cb_estado = new javax.swing.JComboBox<>();

        bg.setBackground(new java.awt.Color(255, 255, 255));

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

        txt_Bname.setForeground(new java.awt.Color(0, 0, 0));

        btn_Bname.setBackground(new java.awt.Color(0, 1, 248));
        btn_Bname.setForeground(new java.awt.Color(255, 255, 255));
        btn_Bname.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buscar.png"))); // NOI18N
        btn_Bname.setText("Buscar");

        txt_Bid.setForeground(new java.awt.Color(0, 0, 0));

        btn_Bid.setBackground(new java.awt.Color(0, 1, 248));
        btn_Bid.setForeground(new java.awt.Color(255, 255, 255));
        btn_Bid.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buscar.png"))); // NOI18N
        btn_Bid.setText("Buscar");

        lbl_Tit.setForeground(new java.awt.Color(0, 0, 0));
        lbl_Tit.setText("Titulo del libro:");

        txt_Titulo.setForeground(new java.awt.Color(0, 0, 0));

        lbl_Idioma.setForeground(new java.awt.Color(0, 0, 0));
        lbl_Idioma.setText("Genero:");

        lbl_Autor.setForeground(new java.awt.Color(0, 0, 0));
        lbl_Autor.setText("Autor:");

        btn_Update.setBackground(new java.awt.Color(0, 1, 248));
        btn_Update.setForeground(new java.awt.Color(255, 255, 255));
        btn_Update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/salvar.png"))); // NOI18N
        btn_Update.setText("GUARDAR");

        img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/editar-codigo.png"))); // NOI18N

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Editorial:");

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Estado");

        cb_estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "ACTIVO", "INACTIVO" }));

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(bgLayout.createSequentialGroup()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, bgLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txt_Bname, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Bname, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                        .addComponent(txt_Bid, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Bid))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(lbl_Tit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(txt_Titulo, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(bgLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbl_Autor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(bgLayout.createSequentialGroup()
                                                .addComponent(lbl_Idioma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(8, 8, 8))))
                                    .addComponent(cb_genero, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cb_autor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cb_editorial, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(cb_estado, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(139, 139, 139))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                                .addComponent(img)
                                .addGap(19, 19, 19))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                                .addComponent(btn_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(100, 100, 100)))))
                .addGap(81, 81, 81))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Bid, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_Bname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_Bid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_Bname, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(img)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Update))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(lbl_Tit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_Idioma)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_genero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_Autor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_autor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_editorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
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
    public javax.swing.JButton btn_Bid;
    public javax.swing.JButton btn_Bname;
    public javax.swing.JButton btn_Update;
    public javax.swing.JComboBox<String> cb_autor;
    public javax.swing.JComboBox<String> cb_editorial;
    public javax.swing.JComboBox<String> cb_estado;
    public javax.swing.JComboBox<String> cb_genero;
    private javax.swing.JLabel img;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbl_Autor;
    private javax.swing.JLabel lbl_Idioma;
    private javax.swing.JLabel lbl_Tit;
    public javax.swing.JTextField txt_Bid;
    public javax.swing.JTextField txt_Bname;
    public javax.swing.JTextField txt_Titulo;
    // End of variables declaration//GEN-END:variables

}
