package vista;

import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import controlador.Controlador_Liquidacion;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fernando
 */
public class ListarLiquidaciones extends javax.swing.JDialog {

    private DefaultTableModel modeloEmpleado = new DefaultTableModel();
    private DefaultTableModel modeloLiquidacion = new DefaultTableModel();
    private DefaultTableModel modeloDetalle = new DefaultTableModel();
    
    public ListarLiquidaciones(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        //CARGARE POR DEFECTO EL MODELO DE LAS LIQUIDACIONES
        this.getModeloLiquidacion().setColumnCount(0);
        this.getModeloLiquidacion().setNumRows(0);
        this.getModeloLiquidacion().addColumn("Id Liquidación");
        this.getModeloLiquidacion().addColumn("Periodo");
        this.getModeloLiquidacion().addColumn("Total de Haberes");
        this.getModeloLiquidacion().addColumn("Total de Descuentos");
        this.getModeloLiquidacion().addColumn("Sueldo Neto");
        this.getTabla_Liquidacion().setModel(modeloDetalle);
        //CARGARE POR DEFECTO EL MODELO DE LOS DETALLES
        this.getModeloDetalle().setColumnCount(0);
        this.getModeloDetalle().setNumRows(0);
        this.getModeloDetalle().addColumn("Concepto");
        this.getModeloDetalle().addColumn("Tipo");
        this.getModeloDetalle().addColumn("Fijo");
        this.getModeloDetalle().addColumn("Porcentaje");
        this.getModeloDetalle().addColumn("Monto");
        this.getModeloDetalle().addColumn("Cantidad");
        this.getModeloDetalle().addColumn("Total");
        this.getTabla_Detalle().setModel(modeloDetalle);
        Controlador_Liquidacion.listarEmpleadosLiquidacion(this);
    }

    public JButton getBtn_Exportar() {
        return btn_Exportar;
    }

    public void setBtn_Exportar(JButton btn_Exportar) {
        this.btn_Exportar = btn_Exportar;
    }
    
    public DefaultTableModel getModeloEmpleado() {
        return modeloEmpleado;
    }

    public void setModeloEmpleado(DefaultTableModel modeloEmpleado) {
        this.modeloEmpleado = modeloEmpleado;
    }

    public DefaultTableModel getModeloLiquidacion() {
        return modeloLiquidacion;
    }

    public void setModeloLiquidacion(DefaultTableModel modeloLiquidacion) {
        this.modeloLiquidacion = modeloLiquidacion;
    }

    public DefaultTableModel getModeloDetalle() {
        return modeloDetalle;
    }

    public void setModeloDetalle(DefaultTableModel modeloDetalle) {
        this.modeloDetalle = modeloDetalle;
    }

    public JTable getTabla_Detalle() {
        return tabla_Detalle;
    }

    public void setTabla_Detalle(JTable tabla_Detalle) {
        this.tabla_Detalle = tabla_Detalle;
    }

    public JTable getTabla_Empleados() {
        return tabla_Empleados;
    }

    public void setTabla_Empleados(JTable tabla_Empleados) {
        this.tabla_Empleados = tabla_Empleados;
    }

    public JTable getTabla_Liquidacion() {
        return tabla_Liquidacion;
    }

    public void setTabla_Liquidacion(JTable tabla_Liquidacion) {
        this.tabla_Liquidacion = tabla_Liquidacion;
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
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_Empleados = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txt_Buscar = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabla_Liquidacion = new javax.swing.JTable();
        btn_Exportar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_Detalle = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Listado de Liquidaciones");

        jPanel1.setBackground(java.awt.Color.white);

        jPanel3.setBackground(java.awt.Color.white);
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Empleados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        tabla_Empleados.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_Empleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_EmpleadosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabla_Empleados);
        if (tabla_Empleados.getColumnModel().getColumnCount() > 0) {
            tabla_Empleados.getColumnModel().getColumn(0).setHeaderValue("Title 1");
            tabla_Empleados.getColumnModel().getColumn(1).setHeaderValue("Title 2");
            tabla_Empleados.getColumnModel().getColumn(2).setHeaderValue("Title 3");
            tabla_Empleados.getColumnModel().getColumn(3).setHeaderValue("Title 4");
        }

        jLabel1.setText("Buscar");

        txt_Buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_BuscarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txt_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(java.awt.Color.white);
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Liquidaciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        tabla_Liquidacion.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_Liquidacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_LiquidacionMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabla_Liquidacion);

        btn_Exportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/PDF.png"))); // NOI18N
        btn_Exportar.setText("Exportar PDF");
        btn_Exportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ExportarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 926, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_Exportar, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(btn_Exportar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(java.awt.Color.white);
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Detalles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        tabla_Detalle.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabla_Detalle);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabla_EmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_EmpleadosMouseClicked
        try {
            Controlador_Liquidacion.listarLiquidacionesDeEmpleado(this);
        } catch (SQLException ex) {
            Logger.getLogger(ListarLiquidaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tabla_EmpleadosMouseClicked

    private void tabla_LiquidacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_LiquidacionMouseClicked
        try {
            Controlador_Liquidacion.CargarDetalleLiquidacion(this);
        } catch (SQLException ex) {
            Logger.getLogger(ListarLiquidaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tabla_LiquidacionMouseClicked

    private void txt_BuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_BuscarKeyReleased
        try {
            String cadena = txt_Buscar.getText();
            Controlador_Liquidacion.filtrarXempleado(this, cadena);
        } catch (SQLException ex) {
            Logger.getLogger(ListarLiquidaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txt_BuscarKeyReleased

    private void btn_ExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ExportarActionPerformed
        try {
            Controlador_Liquidacion.exportarPDF(this);
        } catch (SQLException ex) {
            Logger.getLogger(ListarLiquidaciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListarLiquidaciones.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(ListarLiquidaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListarLiquidaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListarLiquidaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListarLiquidaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ListarLiquidaciones dialog = new ListarLiquidaciones(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(ListarLiquidaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Exportar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tabla_Detalle;
    private javax.swing.JTable tabla_Empleados;
    private javax.swing.JTable tabla_Liquidacion;
    private javax.swing.JTextField txt_Buscar;
    // End of variables declaration//GEN-END:variables
}
