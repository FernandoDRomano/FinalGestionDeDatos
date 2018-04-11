package controlador;

import java.awt.Frame;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import modelo.Cargo;
import modelo.Conexion;
import modelo.Venta;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import vista.GestionCargo;
import vista.NuevaVenta;
import vista.Principal;

/**
 *
 * @author Fernando
 */
public class Controlador_Cargo {
    
    private static Cargo cargo;
    
    public static void LogicaBotones(GestionCargo vista){
        vista.getBtn_Agregar().setEnabled(true);
        vista.getBtn_Editar().setEnabled(false);
        vista.getBtn_Eliminar().setEnabled(false);
    }
    
    public static void LogicaBotonesInvertir(GestionCargo vista){
        vista.getBtn_Agregar().setEnabled(false);
        vista.getBtn_Editar().setEnabled(true);
        vista.getBtn_Eliminar().setEnabled(true);
    }
    
    public static boolean ValidarCampos(GestionCargo vista){
        boolean bandera = true;
        //VALIDO QUE LOS CAMPOS NO SEAN VACIOS
        String nombre = vista.getTxt_Nombre().getText();
        nombre = nombre.replace(" ", "");

        String basico = vista.getTxt_Basico().getText();
        basico = basico.replace(" ", "");
            
        if (nombre.length() == 0) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE COMPLETAR EL NOMBRE DEL CARGO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        if (basico.length() == 0) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE COMPLETAR EL SUELDO BÁSICO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        return bandera;
    }
    
    public static void ActualizarCargo(GestionCargo vista) throws SQLException {
        cargo = new Cargo();
        vista.getModelo().setColumnCount(0);
        vista.getModelo().setNumRows(0);
        vista.getModelo().addColumn("Id");
        vista.getModelo().addColumn("Nombre");
        vista.getModelo().addColumn("Básico");
        ResultSet r = cargo.listarCargo();
        while (r.next()) {
            Object[] fila = new Object[3];
            fila[0] = r.getString("idcargo");
            fila[1] = r.getString("cargo");
            fila[2] = r.getString("basico");
            vista.getModelo().addRow(fila);
        }
        vista.getTabla_Cargo().setModel(vista.getModelo());
        LogicaBotones(vista);
    }

    public static void Mostrar(GestionCargo vista) {
        int fila = vista.getTabla_Cargo().getSelectedRow();
        if(fila > -1){
            LogicaBotonesInvertir(vista);
            vista.getTxt_Id().setEnabled(false);
            vista.getTxt_Id().setText(vista.getTabla_Cargo().getModel().getValueAt(fila, 0).toString());
            vista.getTxt_Nombre().setText(vista.getTabla_Cargo().getModel().getValueAt(fila, 1).toString());
            vista.getTxt_Basico().setText(vista.getTabla_Cargo().getModel().getValueAt(fila, 2).toString());
        }
    }

    public static void AltasCargo(GestionCargo vista) throws SQLException {
        if (ValidarCampos(vista)) {
            cargo = new Cargo();
            cargo.setCargo(vista.getTxt_Nombre().getText());
            cargo.setBasico(Double.parseDouble(vista.getTxt_Basico().getText()));
            System.out.println("AGREGANDO CARGO");
            cargo.grabarCargo();
            JOptionPane.showMessageDialog(vista, "CARGO GRABADO CON ÉXITO", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
            ActualizarCargo(vista);
            LimpiarCampos(vista);  
        }
            
    }

    public static void EditarCargo(GestionCargo vista) throws SQLException {
        int fila = vista.getTabla_Cargo().getSelectedRow();
        if (fila >-1) {
            int opt = JOptionPane.showConfirmDialog(vista, "¿ESTÁ USTED SEGURO DE MODIFICAR ÉSTE CARGO?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (opt == JOptionPane.YES_OPTION) {
                if (ValidarCampos(vista)) {
                    cargo = new Cargo();
                    cargo.setIdCargo(Integer.valueOf(vista.getTxt_Id().getText()));
                    cargo.setCargo(vista.getTxt_Nombre().getText());
                    cargo.setBasico(Double.parseDouble(vista.getTxt_Basico().getText()));
                    System.out.println("MODIFICANDO CARGO");
                    cargo.editarCargo();
                    JOptionPane.showMessageDialog(vista, "CARGO MODIFICADO CON ÉXITO", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
                    ActualizarCargo(vista);
                    LimpiarCampos(vista);
                }
                
            }
        }else{
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UN CARGO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void EliminarCargo(GestionCargo vista) throws SQLException {
        int fila = vista.getTabla_Cargo().getSelectedRow();
        if (fila > -1) {
            int opt = JOptionPane.showConfirmDialog(vista, "¿ESTÁ USTED SEGURO DE ELIMINAR ÉSTE CARGO?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (opt == JOptionPane.YES_OPTION) {
                cargo = new Cargo();
                cargo.setIdCargo(Integer.parseInt(vista.getTabla_Cargo().getModel().getValueAt(fila, 0).toString()));
                //VALIDO QUE EL CARGO A ELIMINAR NO TENGA EMPLEADOS YA ASOCIADOS
                boolean bandera = cargo.tieneEmpleados();
                if (bandera == true) {
                    JOptionPane.showMessageDialog(vista, "ERROR: NO SE PUEDE ELIMINAR ÉSTE CARGO,\n TIENE EMPLEADOS ASOCIADOS", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    cargo.eliminarCargo();
                    JOptionPane.showMessageDialog(vista, "CARGO ELIMINADO CON ÉXITO", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
                    ActualizarCargo(vista);
                    LimpiarCampos(vista);
                }
            }
        } else {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UN CARGO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public static void LimpiarCampos(GestionCargo vista){
        vista.getTxt_Id().setText("");
        vista.getTxt_Nombre().setText("");
        vista.getTxt_Basico().setText("");
        LogicaBotones(vista);
    }
    
    public static void imprimirCargos(Principal vista) throws SQLException, ClassNotFoundException{
        int opt = JOptionPane.showConfirmDialog(vista, "¿DESEA IMPRIMIR EL REPORTE DE CARGOS?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opt == JOptionPane.YES_OPTION) {
            Conexion conexion = new Conexion();
        
        //Metodo para Exportar
            try {
                JOptionPane.showMessageDialog(null, "ESTO PUEDE TARDAR UNOS SEGUNDOS,\nESPERE POR FAVOR", "ESTAMOS GENERANDO EL REPORTE", JOptionPane.WARNING_MESSAGE);
                String rutaInforme = "/home/fernando/NetBeansProjects/FinalGestionDeDatos/FinalGestionDeDatos2018/src/reportes/Cargos.jasper";
                JasperPrint informe = JasperFillManager.fillReport(rutaInforme, null, conexion.getConnection());
                JasperViewer ventanavisor = new JasperViewer(informe, false);
                JOptionPane.showMessageDialog(null, "DOCUMENTO EXPORTADO EXITOSAMENTE!", "GUARDADO EXITOSO!", JOptionPane.INFORMATION_MESSAGE);
                ventanavisor.setTitle("CARGOS");
                ventanavisor.setExtendedState(Frame.MAXIMIZED_BOTH);
                ventanavisor.setVisible(true);
            } catch (Exception e) {//Catch del metodo exportar
                JOptionPane.showMessageDialog(null, "ERROR AL EXPORTAR DOCUMENTO");
            }//Fin de del metodo Exportar      
        }
    }
    
}
