
package vista;

import com.toedter.calendar.JDateChooser;
import controlador.Controlador_Venta;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Venta;

/**
 *
 * @author fernando
 */
public class ListarVenta extends javax.swing.JDialog {

    
    public DefaultTableModel modeloDetalles = new DefaultTableModel();
    public DefaultTableModel modeloVentas = new DefaultTableModel();
    //Este objeto empleado es util para capturar su nombre e ID para trabajarlo en el controlador
    public Venta venta;
    
    public ListarVenta(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        venta = new Venta();
        //No los hago visible a los label que representan el numero de ventas y el total 
        this.getLabel_NumeroVenta().setVisible(false);
        this.getLabel_TotalVenta().setVisible(false);
        Controlador_Venta.cargarVentas(this);
        //Inicializo las Columnas del Modelo Ventas
        modeloDetalles.setColumnCount(0);
        modeloDetalles.setNumRows(0);
        modeloDetalles.addColumn("Id Producto");
        modeloDetalles.addColumn("Descripcion");
        modeloDetalles.addColumn("Precio");
        modeloDetalles.addColumn("Cantidad");
        modeloDetalles.addColumn("Sub Total");
        tabla_Detalles.setModel(modeloDetalles);
        }

    public JButton getBtn_Exportar() {
        return btn_Exportar;
    }

    public void setBtn_Exportar(JButton btn_Exportar) {
        this.btn_Exportar = btn_Exportar;
    }

    public DefaultTableModel getModeloDetalles() {
        return modeloDetalles;
    }

    public void setModeloDetalles(DefaultTableModel modeloDetalles) {
        this.modeloDetalles = modeloDetalles;
    }

    public DefaultTableModel getModeloVentas() {
        return modeloVentas;
    }

    public void setModeloVentas(DefaultTableModel modeloVentas) {
        this.modeloVentas = modeloVentas;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public JDateChooser getDate_FechaFin() {
        return date_FechaFin;
    }

    public void setDate_FechaFin(JDateChooser date_FechaFin) {
        this.date_FechaFin = date_FechaFin;
    }

    public JDateChooser getDate_FechaInicio() {
        return date_FechaInicio;
    }

    public void setDate_FechaInicio(JDateChooser date_FechaInicio) {
        this.date_FechaInicio = date_FechaInicio;
    }

    public JLabel getLabel_NumeroVenta() {
        return label_NumeroVenta;
    }

    public void setLabel_NumeroVenta(JLabel label_NumeroVenta) {
        this.label_NumeroVenta = label_NumeroVenta;
    }

    public JLabel getLabel_TotalVenta() {
        return label_TotalVenta;
    }

    public void setLabel_TotalVenta(JLabel label_TotalVenta) {
        this.label_TotalVenta = label_TotalVenta;
    }

    public JTable getTabla_Detalles() {
        return tabla_Detalles;
    }

    public void setTabla_Detalles(JTable tabla_Detalles) {
        this.tabla_Detalles = tabla_Detalles;
    }

    public JTable getTabla_Ventas() {
        return tabla_Ventas;
    }

    public void setTabla_Ventas(JTable tabla_Ventas) {
        this.tabla_Ventas = tabla_Ventas;
    }

    public JTextField getTxt_Buscar() {
        return txt_Buscar;
    }

    public void setTxt_Buscar(JTextField txt_Buscar) {
        this.txt_Buscar = txt_Buscar;
    }

    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_Detalles = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_Ventas = new javax.swing.JTable();
        btn_Limpiar = new javax.swing.JButton();
        btn_Filtrar = new javax.swing.JButton();
        date_FechaFin = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        date_FechaInicio = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        label_TotalVenta = new javax.swing.JLabel();
        label_NumeroVenta = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txt_Buscar = new javax.swing.JTextField();
        btn_Exportar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Listado de Ventas");

        jPanel1.setBackground(java.awt.Color.white);

        jPanel2.setBackground(java.awt.Color.white);
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Detalle de la Venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        tabla_Detalles.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabla_Detalles);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(java.awt.Color.white);
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Listado de Ventas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        tabla_Ventas.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_Ventas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_VentasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabla_Ventas);

        btn_Limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/btn_limpiar.png"))); // NOI18N
        btn_Limpiar.setText("Limpiar");
        btn_Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LimpiarActionPerformed(evt);
            }
        });

        btn_Filtrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/btn_Buscar.png"))); // NOI18N
        btn_Filtrar.setText("Filtrar");
        btn_Filtrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_FiltrarActionPerformed(evt);
            }
        });

        jLabel5.setText("Fecha de Fin");

        jLabel4.setText("Fecha de Inicio");

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel7.setText("Monto Total ($): ");

        label_TotalVenta.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        label_TotalVenta.setForeground(java.awt.Color.red);
        label_TotalVenta.setText("jLabel8");

        label_NumeroVenta.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        label_NumeroVenta.setForeground(java.awt.Color.red);
        label_NumeroVenta.setText("jLabel7");

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel6.setText("Numero de Ventas:");

        jLabel1.setText("Buscar");

        txt_Buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_BuscarKeyReleased(evt);
            }
        });

        btn_Exportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/PDF.png"))); // NOI18N
        btn_Exportar.setText("Exportar PDF");
        btn_Exportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ExportarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(4, 4, 4)
                        .addComponent(txt_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel4)
                        .addGap(2, 2, 2)
                        .addComponent(date_FechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(3, 3, 3)
                        .addComponent(date_FechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Filtrar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_NumeroVenta)
                        .addGap(91, 91, 91)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_TotalVenta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Exportar, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_Filtrar)
                        .addComponent(btn_Limpiar))
                    .addComponent(date_FechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(date_FechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(label_NumeroVenta)
                    .addComponent(jLabel7)
                    .addComponent(label_TotalVenta)
                    .addComponent(btn_Exportar))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabla_VentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_VentasMouseClicked
        try {
            int fila = tabla_Ventas.getSelectedRow();
            if (fila > -1) {
                int id = Integer.parseInt(tabla_Ventas.getModel().getValueAt(fila, 0).toString());
                venta.setIdVenta(id);
                System.out.println("ID VENTA: " + venta.getIdVenta());
                Controlador_Venta.detallesDeVenta(this);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListarVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tabla_VentasMouseClicked

    private void btn_FiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_FiltrarActionPerformed
        try {
            Controlador_Venta.filtrarFechaVentas(this);
        } catch (SQLException ex) {
            Logger.getLogger(ListarVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_FiltrarActionPerformed

    private void btn_LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LimpiarActionPerformed
        Controlador_Venta.LimpiarFechas(this);
    }//GEN-LAST:event_btn_LimpiarActionPerformed

    private void txt_BuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_BuscarKeyReleased
        String cadena = txt_Buscar.getText();
        try {
            Controlador_Venta.filtrarVentas(this, cadena);
        } catch (SQLException ex) {
            Logger.getLogger(ListarVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txt_BuscarKeyReleased

    private void btn_ExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ExportarActionPerformed
        try {
            Controlador_Venta.exportarPDF(this);
        } catch (SQLException ex) {
            Logger.getLogger(ListarVenta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListarVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_ExportarActionPerformed

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
            java.util.logging.Logger.getLogger(ListarVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListarVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListarVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListarVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ListarVenta dialog = new ListarVenta(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(ListarVenta.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Exportar;
    private javax.swing.JButton btn_Filtrar;
    private javax.swing.JButton btn_Limpiar;
    private com.toedter.calendar.JDateChooser date_FechaFin;
    private com.toedter.calendar.JDateChooser date_FechaInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label_NumeroVenta;
    private javax.swing.JLabel label_TotalVenta;
    private javax.swing.JTable tabla_Detalles;
    private javax.swing.JTable tabla_Ventas;
    private javax.swing.JTextField txt_Buscar;
    // End of variables declaration//GEN-END:variables
}
