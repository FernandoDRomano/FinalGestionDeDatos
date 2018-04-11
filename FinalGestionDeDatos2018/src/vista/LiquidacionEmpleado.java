package vista;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import controlador.Controlador_Liquidacion;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fernando
 */
public class LiquidacionEmpleado extends javax.swing.JDialog {

    private DefaultTableModel modeloFamilia = new DefaultTableModel();
    private DefaultTableModel modeloConcepto = new DefaultTableModel();
    private int optMes = 0;
    private int optAño = 0;
    
    public LiquidacionEmpleado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        //BLOQUEO LOS COMPONENTES DE LA VISTA
        txt_ID.setEditable(false);
        txt_Nombre.setEditable(false);
        txt_Dni.setEditable(false);
        txt_Domicilio.setEditable(false);
        txt_Antiguedad.setEditable(false);
        txt_Haberes.setEditable(false);
        txt_Cargo.setEditable(false);
        txt_Descuentos.setEditable(false);
        txt_SueldoBasico.setEditable(false);
        txt_SueldoNeto.setEditable(false);
        date_fechaIngreso.setEnabled(false);
        //CARGARE POR DEFECTO EL MODELO DE LA FAMILIA
        modeloFamilia.setColumnCount(0);
        modeloFamilia.setNumRows(0);
        modeloFamilia.addColumn("Id");
        modeloFamilia.addColumn("Nombre");
        modeloFamilia.addColumn("Vinculo");
        modeloFamilia.addColumn("Discapacidad");
        modeloFamilia.addColumn("Escolaridad");
        tabla_Familia.setModel(modeloFamilia);
        //CARGARE POR DEFECTO EL MODELO DE LOS CONCEPTOS AGREGADOS
        modeloConcepto.setColumnCount(0);
        modeloConcepto.setNumRows(0);
        modeloConcepto.addColumn("Id");
        modeloConcepto.addColumn("Concepto");
        modeloConcepto.addColumn("Tipo");
        modeloConcepto.addColumn("Fijo");
        modeloConcepto.addColumn("Porcentaje");
        modeloConcepto.addColumn("Monto");
        modeloConcepto.addColumn("Cantidad");
        modeloConcepto.addColumn("Total");
        tabla_Conceptos.setModel(modeloConcepto);
        Controlador_Liquidacion.TamañoDeLasColumnasDeLosConceptos(this);
    }

    public DefaultTableModel getModeloFamilia() {
        return modeloFamilia;
    }

    public void setModeloFamilia(DefaultTableModel modeloFamilia) {
        this.modeloFamilia = modeloFamilia;
    }

    public DefaultTableModel getModeloConcepto() {
        return modeloConcepto;
    }

    public void setModeloConcepto(DefaultTableModel modeloConcepto) {
        this.modeloConcepto = modeloConcepto;
    }

    public JYearChooser getDate_Año() {
        return date_Año;
    }

    public void setDate_Año(JYearChooser date_Año) {
        this.date_Año = date_Año;
    }

    public JMonthChooser getDate_Mes() {
        return date_Mes;
    }

    public void setDate_Mes(JMonthChooser date_Mes) {
        this.date_Mes = date_Mes;
    }

    public JDateChooser getDate_fechaIngreso() {
        return date_fechaIngreso;
    }

    public void setDate_fechaIngreso(JDateChooser date_fechaIngreso) {
        this.date_fechaIngreso = date_fechaIngreso;
    }

    public JTable getTabla_Conceptos() {
        return tabla_Conceptos;
    }

    public void setTabla_Conceptos(JTable tabla_Conceptos) {
        this.tabla_Conceptos = tabla_Conceptos;
    }

    public JTable getTabla_Familia() {
        return tabla_Familia;
    }

    public void setTabla_Familia(JTable tabla_Familia) {
        this.tabla_Familia = tabla_Familia;
    }

    public JTextField getTxt_Antiguedad() {
        return txt_Antiguedad;
    }

    public void setTxt_Antiguedad(JTextField txt_Antiguedad) {
        this.txt_Antiguedad = txt_Antiguedad;
    }

    public JTextField getTxt_Cargo() {
        return txt_Cargo;
    }

    public void setTxt_Cargo(JTextField txt_Cargo) {
        this.txt_Cargo = txt_Cargo;
    }

    public JTextField getTxt_Descuentos() {
        return txt_Descuentos;
    }

    public void setTxt_Descuentos(JTextField txt_Descuentos) {
        this.txt_Descuentos = txt_Descuentos;
    }

    public JTextField getTxt_Dni() {
        return txt_Dni;
    }

    public void setTxt_Dni(JTextField txt_Dni) {
        this.txt_Dni = txt_Dni;
    }

    public JTextField getTxt_Domicilio() {
        return txt_Domicilio;
    }

    public void setTxt_Domicilio(JTextField txt_Domicilio) {
        this.txt_Domicilio = txt_Domicilio;
    }

    public JTextField getTxt_Haberes() {
        return txt_Haberes;
    }

    public void setTxt_Haberes(JTextField txt_Haberes) {
        this.txt_Haberes = txt_Haberes;
    }

    public JTextField getTxt_ID() {
        return txt_ID;
    }

    public void setTxt_ID(JTextField txt_ID) {
        this.txt_ID = txt_ID;
    }

    public JTextField getTxt_Nombre() {
        return txt_Nombre;
    }

    public void setTxt_Nombre(JTextField txt_Nombre) {
        this.txt_Nombre = txt_Nombre;
    }

    public JTextField getTxt_SueldoBasico() {
        return txt_SueldoBasico;
    }

    public void setTxt_SueldoBasico(JTextField txt_SueldoBasico) {
        this.txt_SueldoBasico = txt_SueldoBasico;
    }

    public JTextField getTxt_SueldoNeto() {
        return txt_SueldoNeto;
    }

    public void setTxt_SueldoNeto(JTextField txt_SueldoNeto) {
        this.txt_SueldoNeto = txt_SueldoNeto;
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_ID = new javax.swing.JTextField();
        txt_Nombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_Domicilio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_Dni = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_Familia = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        date_fechaIngreso = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        txt_Cargo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_SueldoBasico = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        btn_Buscar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_Conceptos = new javax.swing.JTable();
        btn_Agregar = new javax.swing.JButton();
        btn_Quitar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        date_Mes = new com.toedter.calendar.JMonthChooser();
        date_Año = new com.toedter.calendar.JYearChooser();
        jLabel10 = new javax.swing.JLabel();
        txt_Antiguedad = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txt_Haberes = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txt_Descuentos = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_SueldoNeto = new javax.swing.JTextField();
        btn_Generar = new javax.swing.JButton();
        btn_Cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Liquidación de Sueldo");

        jPanel1.setBackground(java.awt.Color.white);

        jPanel2.setBackground(java.awt.Color.white);
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Datos del Empleado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Id");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 39, -1, -1));

        jLabel3.setText("Nombre");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 76, -1, -1));

        txt_ID.setEditable(false);
        txt_ID.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txt_IDPropertyChange(evt);
            }
        });
        jPanel2.add(txt_ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 34, 85, -1));
        jPanel2.add(txt_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 71, 255, -1));

        jLabel4.setText("Domicilio");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 175, -1, -1));
        jPanel2.add(txt_Domicilio, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 170, 253, -1));

        jLabel5.setText("DNI");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(488, 76, -1, -1));
        jPanel2.add(txt_Dni, new org.netbeans.lib.awtextra.AbsoluteConstraints(525, 71, 140, -1));

        tabla_Familia.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabla_Familia);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(689, 65, 496, 132));

        jLabel6.setText("Fecha de Ingreso");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 137, -1, 27));

        date_fechaIngreso.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jPanel2.add(date_fechaIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 137, 188, -1));

        jLabel9.setText("Cargo");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 109, -1, -1));
        jPanel2.add(txt_Cargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 104, 255, -1));

        jLabel11.setText("Sueldo Básico");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(417, 109, -1, -1));
        jPanel2.add(txt_SueldoBasico, new org.netbeans.lib.awtextra.AbsoluteConstraints(525, 104, 140, -1));

        jTextField2.setEditable(false);
        jTextField2.setBackground(java.awt.Color.blue);
        jTextField2.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        jTextField2.setForeground(java.awt.Color.white);
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("Grupo Familiar");
        jPanel2.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(689, 29, 496, -1));

        btn_Buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/btn_buscar.png"))); // NOI18N
        btn_Buscar.setText("Buscar");
        btn_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BuscarActionPerformed(evt);
            }
        });
        jPanel2.add(btn_Buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 29, 164, -1));

        jPanel3.setBackground(java.awt.Color.white);
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Detalle de la Liquidación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        tabla_Conceptos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tabla_Conceptos);

        btn_Agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/btn_++.png"))); // NOI18N
        btn_Agregar.setText("Agregar");
        btn_Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AgregarActionPerformed(evt);
            }
        });

        btn_Quitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/btn_x.png"))); // NOI18N
        btn_Quitar.setText("Quitar");
        btn_Quitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_QuitarActionPerformed(evt);
            }
        });

        jLabel1.setText("Periodo");

        date_Mes.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                date_MesPropertyChange(evt);
            }
        });

        date_Año.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                date_AñoPropertyChange(evt);
            }
        });

        jLabel10.setText("Antiguedad (Años)");

        jTextField1.setEditable(false);
        jTextField1.setBackground(java.awt.Color.blue);
        jTextField1.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        jTextField1.setForeground(java.awt.Color.white);
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("Lista de Conceptos Agregados");

        jLabel15.setText("Total de Haberes");

        txt_Haberes.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N

        jLabel14.setText("Total de Descuentos");

        txt_Descuentos.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N

        jLabel13.setText("Sueldo Neto");

        txt_SueldoNeto.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(date_Mes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(date_Año, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_Antiguedad, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                        .addGap(610, 610, 610))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(14, 14, 14)
                                .addComponent(txt_Haberes, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel14)
                                .addGap(14, 14, 14)
                                .addComponent(txt_Descuentos, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(txt_SueldoNeto, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE)
                                .addComponent(jTextField1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_Agregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_Quitar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(18, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(date_Mes, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                        .addComponent(date_Año, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txt_Antiguedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btn_Agregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Quitar)
                        .addGap(59, 59, 59)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txt_Haberes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txt_Descuentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txt_SueldoNeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_Generar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/btn_agregar.png"))); // NOI18N
        btn_Generar.setText("Generar");
        btn_Generar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GenerarActionPerformed(evt);
            }
        });

        btn_Cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/btn_salir.png"))); // NOI18N
        btn_Cancelar.setText("Cancelar");
        btn_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(476, 476, 476)
                .addComponent(btn_Generar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Generar)
                    .addComponent(btn_Cancelar))
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

    private void btn_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BuscarActionPerformed
        try {
            Controlador_Liquidacion.cargarFormBusquedaEmpleado(this);
        } catch (SQLException ex) {
            Logger.getLogger(LiquidacionEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_BuscarActionPerformed

    private void btn_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AgregarActionPerformed
        try {
            Controlador_Liquidacion.CargarFormBusquedaConcepto(this);
        } catch (SQLException ex) {
            Logger.getLogger(LiquidacionEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_AgregarActionPerformed

    private void btn_QuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_QuitarActionPerformed
        Controlador_Liquidacion.QuitarConcepto(this);
    }//GEN-LAST:event_btn_QuitarActionPerformed

    private void btn_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btn_CancelarActionPerformed

    private void btn_GenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GenerarActionPerformed
        try {
            try {
                Controlador_Liquidacion.AltaLiquidacion(this);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LiquidacionEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LiquidacionEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_GenerarActionPerformed

    private void date_MesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_date_MesPropertyChange

        if (optMes == 0) {
            optMes ++;
        }else{
            try {
                String fecha = "";
                fecha = ""+date_Año.getYear() + "/" + (date_Mes.getMonth() + 1) + "/01";
                System.out.println(fecha);
                Controlador_Liquidacion.calcularAntiguedad(this, fecha);
            } catch (SQLException ex) {
                Logger.getLogger(LiquidacionEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_date_MesPropertyChange

    private void date_AñoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_date_AñoPropertyChange
        
        if (optAño == 0) {
            optAño ++;
        }else{
            try {
                String fecha = "";
                fecha = ""+date_Año.getYear() + "/" + (date_Mes.getMonth() + 1) + "/01";
                System.out.println(fecha);
                Controlador_Liquidacion.calcularAntiguedad(this, fecha);
            } catch (SQLException ex) {
                Logger.getLogger(LiquidacionEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_date_AñoPropertyChange

    private void txt_IDPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txt_IDPropertyChange
     
    }//GEN-LAST:event_txt_IDPropertyChange

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
            java.util.logging.Logger.getLogger(LiquidacionEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LiquidacionEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LiquidacionEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LiquidacionEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LiquidacionEmpleado dialog = new LiquidacionEmpleado(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Agregar;
    private javax.swing.JButton btn_Buscar;
    private javax.swing.JButton btn_Cancelar;
    private javax.swing.JButton btn_Generar;
    private javax.swing.JButton btn_Quitar;
    private com.toedter.calendar.JYearChooser date_Año;
    private com.toedter.calendar.JMonthChooser date_Mes;
    private com.toedter.calendar.JDateChooser date_fechaIngreso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTable tabla_Conceptos;
    private javax.swing.JTable tabla_Familia;
    private javax.swing.JTextField txt_Antiguedad;
    private javax.swing.JTextField txt_Cargo;
    private javax.swing.JTextField txt_Descuentos;
    private javax.swing.JTextField txt_Dni;
    private javax.swing.JTextField txt_Domicilio;
    private javax.swing.JTextField txt_Haberes;
    private javax.swing.JTextField txt_ID;
    private javax.swing.JTextField txt_Nombre;
    private javax.swing.JTextField txt_SueldoBasico;
    private javax.swing.JTextField txt_SueldoNeto;
    // End of variables declaration//GEN-END:variables
}
