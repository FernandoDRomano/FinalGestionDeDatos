package controlador;

import java.awt.Frame;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Concepto;
import modelo.Conexion;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import vista.GestionConcepto;
import vista.Principal;

/**
 *
 * @author fernando
 */
public class Controlador_Concepto {
    
    public static Concepto concepto;
    
    public static void LogicaBotones(GestionConcepto vista){
        vista.getBoton_Agregar().setEnabled(true);
        vista.getBoton_Editar().setEnabled(false);
        vista.getBoton_Eliminar().setEnabled(false);
        vista.getBoton_Limpiar().setEnabled(false);
    }
    
    public static void LogicaBotonesInvertir(GestionConcepto vista){
        vista.getBoton_Agregar().setEnabled(false);
        vista.getBoton_Editar().setEnabled(true);
        vista.getBoton_Eliminar().setEnabled(true);
        vista.getBoton_Limpiar().setEnabled(true);
    }
    
    public static boolean ValidarCampos(GestionConcepto vista){
        boolean bandera = true;
        //VALIDO QUE LOS CAMPOS NO SEAN VACIOS
        String descripcion = vista.getTxt_Descripcion().getText();
        descripcion = descripcion.replaceAll(" ", ""); //REEMPLAZO LOS ESPACIO VACIO
        String tipo = vista.getCombo_TipoConcepto().getSelectedItem().toString();
        String fijo = vista.getCombo_Fijo().getSelectedItem().toString();
        String porcentaje = vista.getTxt_Porcentaje().getText();
        porcentaje = porcentaje.replace(" ", "");
        String monto = vista.getTxt_Monto().getText();
        monto = monto.replace(" ", "");
        
        if (descripcion.length() == 0) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE COMPLETAR LA DESCRIPCIÓN", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
            
        if (tipo.equals("Seleccione una Opción")) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UNA OPCIÓN EN EL CAMPO TIPO DE CONCEPTO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
                
        if (fijo.equals("Seleccione una Opción")) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UNA OPCIÓN EN EL CAMPO FIJO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }else{
            if (fijo.equals("SI")) {
                if (monto.length() == 0) {
                    JOptionPane.showMessageDialog(vista, "ERROR: DEBE COMPLETAR EL MONTO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                    bandera = false;
                }
            }else{
                if (porcentaje.length() == 0) {
                    JOptionPane.showMessageDialog(vista, "ERROR: DEBE COMPLETAR EL PORCENTAJE", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                    bandera = false;
                }
            }
        }
        
        return bandera;
    }
    
    public static void ActualizarConcepto(GestionConcepto vista) throws SQLException {
        concepto = new Concepto();
        vista.getModelo().setColumnCount(0);
        vista.getModelo().setNumRows(0);
        vista.getModelo().addColumn("Id");
        vista.getModelo().addColumn("Descripción");
        vista.getModelo().addColumn("Tipo de Concepto");
        vista.getModelo().addColumn("Fijo");
        vista.getModelo().addColumn("Porcentaje");
        vista.getModelo().addColumn("Monto");
        ResultSet r = concepto.listarConceptos();
        while (r.next()) {
            Object[] fila = new Object[6];
            fila[0] = r.getString("idconcepto");
            fila[1] = r.getString("descripcion");
            fila[2] = r.getString("tipoConcepto");
            fila[3] = r.getString("fijo");
            fila[4] = r.getString("porcentaje");
            fila[5] = r.getString("monto");
            vista.getModelo().addRow(fila);
        }
        vista.getTabla_Concepto().setModel(vista.getModelo());
        LogicaBotones(vista);
    }
    
    public static void AltaConcepto(GestionConcepto vista) throws SQLException{
        if (ValidarCampos(vista)) {
           concepto = new Concepto();
            //SETEO LOS VALORES DEL CONCEPTO
            concepto.setDescripcion(vista.getTxt_Descripcion().getText());
            concepto.setTipoConcepto(vista.getCombo_TipoConcepto().getModel().getSelectedItem().toString());
            concepto.setFijo(vista.getCombo_Fijo().getModel().getSelectedItem().toString());
            concepto.setPorcentaje(Double.parseDouble(vista.getTxt_Porcentaje().getText()));
            concepto.setMonto(Double.parseDouble(vista.getTxt_Monto().getText()));
            //GRABO
            concepto.grabarConcepto();
            JOptionPane.showMessageDialog(vista, "CONCEPTO GRABADO CON ÉXITO", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
            LimpiarCampos(vista);
            ActualizarConcepto(vista); 
        }
        
    }
    
    public static void MostrarConcepto(GestionConcepto vista){
        int fila = vista.getTabla_Concepto().getSelectedRow();
        if (fila > -1) {
            vista.getTxt_Descripcion().setEnabled(true);
            vista.getCombo_Fijo().setEnabled(true);
            vista.getCombo_TipoConcepto().setEnabled(true);
            //VALIDO QUE LOS CONCEPTOS CON PRIVILEGIOS (ID <= 7) NO SE PUEDAN EDITAR TODOS LOS CAMPOS
            if (fila >= 0 && fila <= 5) {//DEL 1 AL 6 SON LOS DEL GRUPO FAMILIAR Y EL BASICO
                vista.getTxt_Id().setText(vista.getTabla_Concepto().getModel().getValueAt(fila, 0).toString());
                vista.getTxt_Descripcion().setText(vista.getTabla_Concepto().getModel().getValueAt(fila, 1).toString());
                vista.getCombo_TipoConcepto().setSelectedItem(vista.getTabla_Concepto().getModel().getValueAt(fila, 2).toString());
                vista.getCombo_Fijo().setSelectedItem(vista.getTabla_Concepto().getModel().getValueAt(fila, 3).toString());
                vista.getTxt_Porcentaje().setText(vista.getTabla_Concepto().getModel().getValueAt(fila, 4).toString());
                vista.getTxt_Monto().setText(vista.getTabla_Concepto().getModel().getValueAt(fila, 5).toString());
                LogicaBotonesInvertir(vista);
                vista.getTxt_Descripcion().setEnabled(false);
                vista.getCombo_Fijo().setEnabled(false);
                vista.getCombo_TipoConcepto().setEnabled(false);
                vista.getTxt_Porcentaje().setEnabled(false);
            }
            
            if (fila == 6) {//EL 7 ES DE LA ANTIGUEDAD
                vista.getTxt_Id().setText(vista.getTabla_Concepto().getModel().getValueAt(fila, 0).toString());
                vista.getTxt_Descripcion().setText(vista.getTabla_Concepto().getModel().getValueAt(fila, 1).toString());
                vista.getCombo_TipoConcepto().setSelectedItem(vista.getTabla_Concepto().getModel().getValueAt(fila, 2).toString());
                vista.getCombo_Fijo().setSelectedItem(vista.getTabla_Concepto().getModel().getValueAt(fila, 3).toString());
                vista.getTxt_Porcentaje().setText(vista.getTabla_Concepto().getModel().getValueAt(fila, 4).toString());
                vista.getTxt_Monto().setText(vista.getTabla_Concepto().getModel().getValueAt(fila, 5).toString());
                LogicaBotonesInvertir(vista);
                vista.getTxt_Descripcion().setEnabled(false);
                vista.getCombo_Fijo().setEnabled(false);
                vista.getCombo_TipoConcepto().setEnabled(false);
            }
            
            vista.getTxt_Id().setText(vista.getTabla_Concepto().getModel().getValueAt(fila, 0).toString());
            vista.getTxt_Descripcion().setText(vista.getTabla_Concepto().getModel().getValueAt(fila, 1).toString());
            vista.getCombo_TipoConcepto().setSelectedItem(vista.getTabla_Concepto().getModel().getValueAt(fila, 2).toString());
            vista.getCombo_Fijo().setSelectedItem(vista.getTabla_Concepto().getModel().getValueAt(fila, 3).toString());
            vista.getTxt_Porcentaje().setText(vista.getTabla_Concepto().getModel().getValueAt(fila, 4).toString());
            vista.getTxt_Monto().setText(vista.getTabla_Concepto().getModel().getValueAt(fila, 5).toString());
            LogicaBotonesInvertir(vista);
            
            
        }else{
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UN CONCEPTO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void EliminarConcepto(GestionConcepto vista) throws SQLException{
        int fila = vista.getTabla_Concepto().getSelectedRow();
        if (fila > -1) {
            int opt = JOptionPane.showConfirmDialog(vista, "¿ESTÁ USTED SEGURO DE ELIMINAR ÉSTE CONCEPTO?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (opt == JOptionPane.YES_OPTION) {
                concepto = new Concepto();
                concepto.setIdConcepto(Integer.valueOf(vista.getTxt_Id().getText()));
                //VALIDO QUE EL CONCEPTO SELECCIONADO NO SEA UNO DE LOS PRIMEROS 7 QUE TIENEN REGLAS ESPECIALES EN EL SISTEMA
                if (concepto.getIdConcepto()<= 7) {
                    JOptionPane.showMessageDialog(vista, "ERROR: NO SE PUEDE ELIMINAR ÉSTE CONCEPTO, \n TIENE PRIVILEGIOS EN EL SISTEMA", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    //VALIDO QUE EL CONCEPTO NO TENGA LINEAS DE LIQUIDACION ASOCIADAS
                    boolean bandera = concepto.tieneLiquidaciones();
                    if (bandera == true) {
                        JOptionPane.showMessageDialog(vista, "ERROR: NO SE PUEDE ELIMINAR ÉSTE CONCEPTO, \n TIENE LIQUIDACIONES ASOCIADAS", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                    }else{
                        concepto.eliminarConcepto();
                        JOptionPane.showMessageDialog(vista, "CONCEPTO ELIMINADO CON ÉXITO", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
                        LimpiarCampos(vista);
                        ActualizarConcepto(vista);
                    }
                    
                }
            }
        }else{
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UN CONCEPTO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void EditarConcepto(GestionConcepto vista) throws SQLException{
        int fila = vista.getTabla_Concepto().getSelectedRow();
        if (fila > -1) {
            int opt = JOptionPane.showConfirmDialog(vista, "¿ESTÁ USTED SEGURO DE MODIFICAR ÉSTE CONCEPTO?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (opt == JOptionPane.YES_OPTION) {
                if (ValidarCampos(vista)) {
                concepto = new Concepto();
                //SETEO LOS VALORES
                concepto.setIdConcepto(Integer.valueOf(vista.getTxt_Id().getText()));
                concepto.setDescripcion(vista.getTxt_Descripcion().getText());
                concepto.setTipoConcepto(vista.getCombo_TipoConcepto().getModel().getSelectedItem().toString());
                concepto.setFijo(vista.getCombo_Fijo().getModel().getSelectedItem().toString());
                concepto.setPorcentaje(Double.parseDouble(vista.getTxt_Porcentaje().getText()));
                concepto.setMonto(Double.parseDouble(vista.getTxt_Monto().getText()));
                //EDITO
                concepto.editarConcepto();
                JOptionPane.showMessageDialog(vista, "CONCEPTO MODIFICADO CON ÉXITO", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
                LimpiarCampos(vista);
                ActualizarConcepto(vista);
                }
            }
        }else{
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UN CONCEPTO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void LimpiarCampos(GestionConcepto vista){
        vista.getTxt_Descripcion().setText("");
        vista.getTxt_Monto().setText("0");
        vista.getTxt_Porcentaje().setText("0");
        vista.getCombo_TipoConcepto().setSelectedItem("Seleccione una Opción");
        vista.getCombo_Fijo().setSelectedItem("Seleccione una Opción");
        vista.getTxt_Id().setText("");
        LogicaBotones(vista);
    }
    
        public static void imprimirConceptos(Principal vista) throws SQLException, ClassNotFoundException{
        int opt = JOptionPane.showConfirmDialog(vista, "¿DESEA IMPRIMIR EL REPORTE DE CONCEPTOS?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opt == JOptionPane.YES_OPTION) {
            Conexion conexion = new Conexion();
        
        //Metodo para Exportar
            try {
                JOptionPane.showMessageDialog(null, "ESTO PUEDE TARDAR UNOS SEGUNDOS,\nESPERE POR FAVOR", "ESTAMOS GENERANDO EL REPORTE", JOptionPane.WARNING_MESSAGE);
                String rutaInforme = "/home/fernando/NetBeansProjects/FinalGestionDeDatos/FinalGestionDeDatos2018/src/reportes/Conceptos.jasper";
                JasperPrint informe = JasperFillManager.fillReport(rutaInforme, null, conexion.getConnection());
                JasperViewer ventanavisor = new JasperViewer(informe, false);
                JOptionPane.showMessageDialog(null, "DOCUMENTO EXPORTADO EXITOSAMENTE!", "GUARDADO EXITOSO!", JOptionPane.INFORMATION_MESSAGE);
                ventanavisor.setTitle("CONCEPTOS");
                ventanavisor.setExtendedState(Frame.MAXIMIZED_BOTH);
                ventanavisor.setVisible(true);
            } catch (Exception e) {//Catch del metodo exportar
                JOptionPane.showMessageDialog(null, "ERROR AL EXPORTAR DOCUMENTO");
            }//Fin de del metodo Exportar      
        }
    }

    
}
