
package vista;

import com.toedter.calendar.JDateChooser;
import controlador.Controlador_Familia;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Empleado;

/**
 *
 * @author fernando
 */
public class GestionFamilia extends javax.swing.JDialog {

    private DefaultTableModel modeloTabla = new DefaultTableModel();
    private DefaultComboBoxModel modeloComboVinculo = new DefaultComboBoxModel();
    private DefaultComboBoxModel modeloComboDiscapacidad = new DefaultComboBoxModel();
    private Empleado empleado;

    public GestionFamilia(java.awt.Frame parent, boolean modal) throws SQLException{
        super(parent, modal);
        initComponents();
        txt_Id.setEnabled(false);
        txt_Apellido.setEnabled(false);
        txt_Nombre.setEnabled(false);
        txt_Dni.setEnabled(false);
        txt_IdFamiliar.setEnabled(false);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public void setModeloTabla(DefaultTableModel modeloTabla) {
        this.modeloTabla = modeloTabla;
    }

    public DefaultComboBoxModel getModeloComboVinculo() {
        return modeloComboVinculo;
    }

    public void setModeloComboVinculo(DefaultComboBoxModel modeloComboVinculo) {
        this.modeloComboVinculo = modeloComboVinculo;
    }

    public DefaultComboBoxModel getModeloComboDiscapacidad() {
        return modeloComboDiscapacidad;
    }

    public void setModeloComboDiscapacidad(DefaultComboBoxModel modeloComboDiscapacidad) {
        this.modeloComboDiscapacidad = modeloComboDiscapacidad;
    }

    public JDateChooser getDate_FechaNacimiento() {
        return Date_FechaNacimiento;
    }

    public void setDate_FechaNacimiento(JDateChooser Date_FechaNacimiento) {
        this.Date_FechaNacimiento = Date_FechaNacimiento;
    }

    public JButton getBoton_Buscar() {
        return boton_Buscar;
    }

    public void setBoton_Buscar(JButton boton_Buscar) {
        this.boton_Buscar = boton_Buscar;
    }

    public JButton getBtn_Agregar() {
        return btn_Agregar;
    }

    public void setBtn_Agregar(JButton btn_Agregar) {
        this.btn_Agregar = btn_Agregar;
    }

    public JButton getBtn_Editar() {
        return btn_Editar;
    }

    public void setBtn_Editar(JButton btn_Editar) {
        this.btn_Editar = btn_Editar;
    }

    public JButton getBtn_Eliminar() {
        return btn_Eliminar;
    }

    public void setBtn_Eliminar(JButton btn_Eliminar) {
        this.btn_Eliminar = btn_Eliminar;
    }

    public JButton getBtn_Limpiar() {
        return btn_Limpiar;
    }

    public void setBtn_Limpiar(JButton btn_Limpiar) {
        this.btn_Limpiar = btn_Limpiar;
    }

    public JButton getBtn_Salir() {
        return btn_Salir;
    }

    public void setBtn_Salir(JButton btn_Salir) {
        this.btn_Salir = btn_Salir;
    }

    public JComboBox<String> getCombo_Discapacidad() {
        return combo_Discapacidad;
    }

    public void setCombo_Discapacidad(JComboBox<String> combo_Discapacidad) {
        this.combo_Discapacidad = combo_Discapacidad;
    }

    public JComboBox<String> getCombo_Vinculo() {
        return combo_Vinculo;
    }

    public void setCombo_Vinculo(JComboBox<String> combo_Vinculo) {
        this.combo_Vinculo = combo_Vinculo;
    }

    public JTable getTabla_GrupoFamiliar() {
        return tabla_GrupoFamiliar;
    }

    public void setTabla_GrupoFamiliar(JTable tabla_GrupoFamiliar) {
        this.tabla_GrupoFamiliar = tabla_GrupoFamiliar;
    }

    public JTextField getTxt_Apellido() {
        return txt_Apellido;
    }

    public void setTxt_Apellido(JTextField txt_Apellido) {
        this.txt_Apellido = txt_Apellido;
    }

    public JTextField getTxt_ApellidoFamiliar() {
        return txt_ApellidoFamiliar;
    }

    public void setTxt_ApellidoFamiliar(JTextField txt_ApellidoFamiliar) {
        this.txt_ApellidoFamiliar = txt_ApellidoFamiliar;
    }

    public JTextField getTxt_Dni() {
        return txt_Dni;
    }

    public void setTxt_Dni(JTextField txt_Dni) {
        this.txt_Dni = txt_Dni;
    }

    public JTextField getTxt_Id() {
        return txt_Id;
    }

    public void setTxt_Id(JTextField txt_Id) {
        this.txt_Id = txt_Id;
    }

    public JTextField getTxt_IdFamiliar() {
        return txt_IdFamiliar;
    }

    public void setTxt_IdFamiliar(JTextField txt_IdFamiliar) {
        this.txt_IdFamiliar = txt_IdFamiliar;
    }

    public JTextField getTxt_Nombre() {
        return txt_Nombre;
    }

    public void setTxt_Nombre(JTextField txt_Nombre) {
        this.txt_Nombre = txt_Nombre;
    }

    public JTextField getTxt_NombreFamiliar() {
        return txt_NombreFamiliar;
    }

    public void setTxt_NombreFamiliar(JTextField txt_NombreFamiliar) {
        this.txt_NombreFamiliar = txt_NombreFamiliar;
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_Id = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_Nombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_Dni = new javax.swing.JTextField();
        boton_Buscar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txt_Apellido = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_GrupoFamiliar = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txt_IdFamiliar = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_ApellidoFamiliar = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_NombreFamiliar = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        combo_Discapacidad = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        combo_Vinculo = new javax.swing.JComboBox<>();
        Date_FechaNacimiento = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        btn_Agregar = new javax.swing.JButton();
        btn_Editar = new javax.swing.JButton();
        btn_Eliminar = new javax.swing.JButton();
        btn_Limpiar = new javax.swing.JButton();
        btn_Salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Gestión del Grupo Familiar");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Datos del Empleado"));

        jLabel2.setText("Id");

        jLabel3.setText("Nombres");

        jLabel5.setText("Dni");

        boton_Buscar.setText("Buscar");

        jLabel8.setText("Apellido");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(91, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_Nombre)
                    .addComponent(txt_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_Dni, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_Apellido, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addComponent(boton_Buscar)
                .addGap(29, 29, 29))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txt_Apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txt_Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Dni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(13, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(boton_Buscar)
                .addContainerGap())
        );

        tabla_GrupoFamiliar.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_GrupoFamiliar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_GrupoFamiliarMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_GrupoFamiliar);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Datos Generales"));

        jLabel4.setText("Id");

        jLabel6.setText("Apellido");

        jLabel7.setText("Nombre");

        jLabel10.setText("Discapacidad");

        combo_Discapacidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una Opción", "SI", "NO" }));

        jLabel12.setText("Vinculo");

        combo_Vinculo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una Opción", "Cónyuge", "Hijo/a" }));

        Date_FechaNacimiento.setDateFormatString("yyyy/MM/dd");

        jLabel13.setText("Fecha de Nacimiento");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_ApellidoFamiliar)
                    .addComponent(txt_IdFamiliar)
                    .addComponent(Date_FechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                .addGap(43, 43, 43)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_NombreFamiliar)
                    .addComponent(combo_Discapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_Vinculo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_IdFamiliar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(combo_Vinculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txt_NombreFamiliar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txt_ApellidoFamiliar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Date_FechaNacimiento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(combo_Discapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_Agregar.setText("Agregar");
        btn_Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AgregarActionPerformed(evt);
            }
        });

        btn_Editar.setText("Editar");
        btn_Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EditarActionPerformed(evt);
            }
        });

        btn_Eliminar.setText("Eliminar");
        btn_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarActionPerformed(evt);
            }
        });

        btn_Limpiar.setText("Limpiar");
        btn_Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LimpiarActionPerformed(evt);
            }
        });

        btn_Salir.setText("Salir");
        btn_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(262, 262, 262)
                .addComponent(btn_Agregar)
                .addGap(18, 18, 18)
                .addComponent(btn_Editar)
                .addGap(18, 18, 18)
                .addComponent(btn_Eliminar)
                .addGap(18, 18, 18)
                .addComponent(btn_Limpiar)
                .addGap(18, 18, 18)
                .addComponent(btn_Salir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(302, 302, 302))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Agregar)
                    .addComponent(btn_Editar)
                    .addComponent(btn_Eliminar)
                    .addComponent(btn_Limpiar)
                    .addComponent(btn_Salir))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 62, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AgregarActionPerformed
        try {
            Controlador_Familia.AltasFamiliar(this);
        } catch (SQLException ex) {
            Logger.getLogger(GestionFamilia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_AgregarActionPerformed

    private void btn_EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EditarActionPerformed
        try {
            Controlador_Familia.EditarFamiliar(this);
        } catch (SQLException ex) {
            Logger.getLogger(GestionFamilia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_EditarActionPerformed

    private void btn_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarActionPerformed
        try {
            Controlador_Familia.EliminarFamiliar(this);
        } catch (SQLException ex) {
            Logger.getLogger(GestionFamilia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_EliminarActionPerformed

    private void btn_LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LimpiarActionPerformed
        Controlador_Familia.LimpiarCampos(this);
    }//GEN-LAST:event_btn_LimpiarActionPerformed

    private void btn_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalirActionPerformed
        dispose();
        try {
            GestionEmpleado vista = new GestionEmpleado(null, true);
            vista.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(GestionFamilia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_SalirActionPerformed

    private void tabla_GrupoFamiliarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_GrupoFamiliarMouseClicked
        try {
            Controlador_Familia.CargarFamiliar(this);
        } catch (ParseException ex) {
            Logger.getLogger(GestionFamilia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tabla_GrupoFamiliarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Date_FechaNacimiento;
    private javax.swing.JButton boton_Buscar;
    private javax.swing.JButton btn_Agregar;
    private javax.swing.JButton btn_Editar;
    private javax.swing.JButton btn_Eliminar;
    private javax.swing.JButton btn_Limpiar;
    private javax.swing.JButton btn_Salir;
    private javax.swing.JComboBox<String> combo_Discapacidad;
    private javax.swing.JComboBox<String> combo_Vinculo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_GrupoFamiliar;
    private javax.swing.JTextField txt_Apellido;
    private javax.swing.JTextField txt_ApellidoFamiliar;
    private javax.swing.JTextField txt_Dni;
    private javax.swing.JTextField txt_Id;
    private javax.swing.JTextField txt_IdFamiliar;
    private javax.swing.JTextField txt_Nombre;
    private javax.swing.JTextField txt_NombreFamiliar;
    // End of variables declaration//GEN-END:variables
}
