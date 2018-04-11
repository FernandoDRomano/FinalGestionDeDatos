package vista;

import controlador.Controlador_Concepto;
import controlador.Validar;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fernando
 */
public class GestionConcepto extends javax.swing.JDialog {

    private DefaultTableModel modelo = new DefaultTableModel();
    
    public GestionConcepto(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getTxt_Id().setEnabled(false);
        this.getTxt_Monto().setEnabled(false);
        this.getTxt_Porcentaje().setEnabled(false);
        this.getTxt_Monto().setText("0");
        this.getTxt_Porcentaje().setText("0");
        Controlador_Concepto.ActualizarConcepto(this);
        //VALIDO EL FORMATO DE LOS CAMPOS
        Validar.validarDecimales(txt_Monto);
        Validar.validarDecimales(txt_Porcentaje);
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public JComboBox<String> getCombo_Fijo() {
        return combo_Fijo;
    }

    public void setCombo_Fijo(JComboBox<String> combo_Fijo) {
        this.combo_Fijo = combo_Fijo;
    }

    public JComboBox<String> getCombo_TipoConcepto() {
        return combo_TipoConcepto;
    }

    public void setCombo_TipoConcepto(JComboBox<String> combo_TipoConcepto) {
        this.combo_TipoConcepto = combo_TipoConcepto;
    }

    public JTable getTabla_Concepto() {
        return tabla_Concepto;
    }

    public void setTabla_Concepto(JTable tabla_Concepto) {
        this.tabla_Concepto = tabla_Concepto;
    }

    public JTextField getTxt_Descripcion() {
        return txt_Descripcion;
    }

    public void setTxt_Descripcion(JTextField txt_Descripcion) {
        this.txt_Descripcion = txt_Descripcion;
    }

    public JTextField getTxt_Id() {
        return txt_Id;
    }

    public void setTxt_Id(JTextField txt_Id) {
        this.txt_Id = txt_Id;
    }

    public JTextField getTxt_Monto() {
        return txt_Monto;
    }

    public void setTxt_Monto(JTextField txt_Monto) {
        this.txt_Monto = txt_Monto;
    }

    public JTextField getTxt_Porcentaje() {
        return txt_Porcentaje;
    }

    public void setTxt_Porcentaje(JTextField txt_Porcentaje) {
        this.txt_Porcentaje = txt_Porcentaje;
    }

    public JButton getBoton_Agregar() {
        return boton_Agregar;
    }

    public void setBoton_Agregar(JButton boton_Agregar) {
        this.boton_Agregar = boton_Agregar;
    }

    public JButton getBoton_Editar() {
        return boton_Editar;
    }

    public void setBoton_Editar(JButton boton_Editar) {
        this.boton_Editar = boton_Editar;
    }

    public JButton getBoton_Eliminar() {
        return boton_Eliminar;
    }

    public void setBoton_Eliminar(JButton boton_Eliminar) {
        this.boton_Eliminar = boton_Eliminar;
    }

    public JButton getBoton_Limpiar() {
        return boton_Limpiar;
    }

    public void setBoton_Limpiar(JButton boton_Limpiar) {
        this.boton_Limpiar = boton_Limpiar;
    }

    public JButton getBoton_Salir() {
        return boton_Salir;
    }

    public void setBoton_Salir(JButton boton_Salir) {
        this.boton_Salir = boton_Salir;
    }

    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_Concepto = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_Id = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_Descripcion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        combo_TipoConcepto = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txt_Porcentaje = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        combo_Fijo = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txt_Monto = new javax.swing.JTextField();
        boton_Agregar = new javax.swing.JButton();
        boton_Editar = new javax.swing.JButton();
        boton_Eliminar = new javax.swing.JButton();
        boton_Limpiar = new javax.swing.JButton();
        boton_Salir = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Conceptos");

        jPanel1.setBackground(java.awt.Color.white);

        tabla_Concepto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla_Concepto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_ConceptoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_Concepto);

        jPanel2.setBackground(java.awt.Color.white);
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Datos Generales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        jLabel2.setText("Id");

        jLabel4.setText("Descripción");

        jLabel5.setText("Tipo Concepto");

        combo_TipoConcepto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una Opción", "Haber", "Debe" }));

        jLabel6.setText("Porcentaje");

        txt_Porcentaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_PorcentajeKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_PorcentajeKeyReleased(evt);
            }
        });

        jLabel7.setText("Fijo");

        combo_Fijo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una Opción", "SI", "NO" }));
        combo_Fijo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combo_FijoItemStateChanged(evt);
            }
        });
        combo_Fijo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                combo_FijoMouseClicked(evt);
            }
        });

        jLabel8.setText("Monto");

        txt_Monto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_MontoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel5))
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(594, 594, 594))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(combo_TipoConcepto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_Porcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_Monto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(combo_Fijo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(109, 109, 109))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txt_Descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_Descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(combo_TipoConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(combo_Fijo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_Porcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txt_Monto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        boton_Agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/btn_agregar.png"))); // NOI18N
        boton_Agregar.setText("Agregar");
        boton_Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_AgregarActionPerformed(evt);
            }
        });

        boton_Editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/btn_editar.png"))); // NOI18N
        boton_Editar.setText("Editar");
        boton_Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_EditarActionPerformed(evt);
            }
        });

        boton_Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/btn_eliminar.png"))); // NOI18N
        boton_Eliminar.setText("Eliminar");
        boton_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_EliminarActionPerformed(evt);
            }
        });

        boton_Limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/btn_limpiar.png"))); // NOI18N
        boton_Limpiar.setText("Limpiar");
        boton_Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_LimpiarActionPerformed(evt);
            }
        });

        boton_Salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/btn_salir.png"))); // NOI18N
        boton_Salir.setText("Salir");
        boton_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_SalirActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setBackground(java.awt.Color.blue);
        jTextField1.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        jTextField1.setForeground(java.awt.Color.white);
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("Lista de Conceptos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(boton_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(boton_Editar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(boton_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(boton_Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(boton_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boton_Editar)
                    .addComponent(boton_Agregar)
                    .addComponent(boton_Eliminar)
                    .addComponent(boton_Limpiar)
                    .addComponent(boton_Salir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_AgregarActionPerformed
        try {
            Controlador_Concepto.AltaConcepto(this);
        } catch (SQLException ex) {
            Logger.getLogger(GestionConcepto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_boton_AgregarActionPerformed

    private void boton_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_SalirActionPerformed
        dispose();
    }//GEN-LAST:event_boton_SalirActionPerformed

    private void combo_FijoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combo_FijoMouseClicked
        
        
    }//GEN-LAST:event_combo_FijoMouseClicked

    private void combo_FijoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combo_FijoItemStateChanged
        this.getTxt_Monto().setEnabled(false);
        this.getTxt_Porcentaje().setEnabled(false);
        this.getTxt_Monto().setText("0");
        this.getTxt_Porcentaje().setText("0");
        if (!combo_Fijo.getModel().getSelectedItem().equals("Seleccione una Opción")) {
            if(combo_Fijo.getModel().getSelectedItem().equals("SI")){
                txt_Monto.setText("");
                txt_Monto.setEnabled(true);
            }else{
                txt_Porcentaje.setText("");
                txt_Porcentaje.setEnabled(true);
            }
        }
    }//GEN-LAST:event_combo_FijoItemStateChanged

    private void tabla_ConceptoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_ConceptoMouseClicked
        Controlador_Concepto.MostrarConcepto(this);
    }//GEN-LAST:event_tabla_ConceptoMouseClicked

    private void boton_LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_LimpiarActionPerformed
        Controlador_Concepto.LimpiarCampos(this);
        this.getTxt_Id().setText("");
    }//GEN-LAST:event_boton_LimpiarActionPerformed

    private void boton_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_EliminarActionPerformed
        try {
            Controlador_Concepto.EliminarConcepto(this);
        } catch (SQLException ex) {
            Logger.getLogger(GestionConcepto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_boton_EliminarActionPerformed

    private void boton_EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_EditarActionPerformed
        try {
            Controlador_Concepto.EditarConcepto(this);
        } catch (SQLException ex) {
            Logger.getLogger(GestionConcepto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_boton_EditarActionPerformed

    private void txt_PorcentajeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PorcentajeKeyTyped
        //PARA VALIDAR SOLO EL INGRESO DE NUMEROS Y UN PUNTO
//        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {  
//            evt.consume();
//        }
//        
//        if (evt.getKeyChar() == '.' && txt_Monto.getText().contains(".")) {
//            evt.consume();
//        }
        
    }//GEN-LAST:event_txt_PorcentajeKeyTyped

    private void txt_MontoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_MontoKeyTyped
        //PARA VALIDAR SOLO EL INGRESO DE NUMEROS Y UN PUNTO
//        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {  
//            evt.consume();
//        }
//        
//        if (evt.getKeyChar() == '.' && txt_Monto.getText().contains(".")) {
//            evt.consume();
//        }
    }//GEN-LAST:event_txt_MontoKeyTyped

    private void txt_PorcentajeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PorcentajeKeyReleased

    }//GEN-LAST:event_txt_PorcentajeKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GestionConcepto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionConcepto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionConcepto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionConcepto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GestionConcepto dialog = new GestionConcepto(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(GestionConcepto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_Agregar;
    private javax.swing.JButton boton_Editar;
    private javax.swing.JButton boton_Eliminar;
    private javax.swing.JButton boton_Limpiar;
    private javax.swing.JButton boton_Salir;
    private javax.swing.JComboBox<String> combo_Fijo;
    private javax.swing.JComboBox<String> combo_TipoConcepto;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tabla_Concepto;
    private javax.swing.JTextField txt_Descripcion;
    private javax.swing.JTextField txt_Id;
    private javax.swing.JTextField txt_Monto;
    private javax.swing.JTextField txt_Porcentaje;
    // End of variables declaration//GEN-END:variables
}
