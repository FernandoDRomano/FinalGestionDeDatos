package controlador;

import java.awt.Frame;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.Conexion;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import vista.GestionCliente;
import vista.Principal;

/**
 *
 * @author Fernando
 */
public class Controlador_Cliente {
    
    private static Cliente cliente;
    
    public static void LogicaBotones(GestionCliente vista){
        vista.getBtn_Agregar().setEnabled(true);
        vista.getBtn_Editar().setEnabled(false);
        vista.getBtn_Eliminar().setEnabled(false);
    }
    
    public static void LogicaBotonesInvertir(GestionCliente vista){
        vista.getBtn_Agregar().setEnabled(false);
        vista.getBtn_Editar().setEnabled(true);
        vista.getBtn_Eliminar().setEnabled(true);
    }
    
    public static boolean ValidarCampos(GestionCliente vista){
        boolean bandera = true;
        //VALIDO QUE LOS CAMPOS NO SEAN VACIOS
        String apellido = vista.getTxt_Apellido().getText();
        apellido = apellido.replaceAll(" ", ""); //REEMPLAZO LOS ESPACIO VACIO
        
        String nombre = vista.getTxt_Nombre().getText();
        nombre = nombre.replace(" ", "");
        
        if (apellido.length() == 0) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE COMPLETAR EL APELLIDO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
            
        if (nombre.length() == 0) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE COMPLETAR EL NOMBRE", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        return bandera;
    }
    
    public static void ActualizarCliente(GestionCliente vista) throws SQLException {
        cliente = new Cliente();
        vista.getModelo().setColumnCount(0);
        vista.getModelo().setNumRows(0);
        vista.getModelo().addColumn("Id");
        vista.getModelo().addColumn("Apellido");
        vista.getModelo().addColumn("Nombre");
        vista.getModelo().addColumn("Teléfono");
        ResultSet r = cliente.listarCliente();
        while (r.next()) {
            Object[] fila = new Object[5];
            fila[0] = r.getString("idCliente");
            fila[1] = r.getString("apellido");
            fila[2] = r.getString("nombre");
            fila[3] = r.getString("telefono");
            vista.getModelo().addRow(fila);
        }
        vista.getTabla_Cliente().setModel(vista.getModelo());
        LogicaBotones(vista);
    }

    public static void Mostrar(GestionCliente vista) {
        int fila = vista.getTabla_Cliente().getSelectedRow();
        if(fila > -1){
            LogicaBotonesInvertir(vista);
            vista.getTxt_Id().setEnabled(false);
            vista.getTxt_Id().setText(vista.getTabla_Cliente().getModel().getValueAt(fila, 0).toString());
            vista.getTxt_Nombre().setText(vista.getTabla_Cliente().getModel().getValueAt(fila, 2).toString());
            vista.getTxt_Apellido().setText(vista.getTabla_Cliente().getModel().getValueAt(fila, 1).toString());
            vista.getTxt_Telefono().setText(vista.getTabla_Cliente().getModel().getValueAt(fila, 3).toString());
        }
    }

    public static void AltasCliente(GestionCliente vista) throws SQLException {
        if (ValidarCampos(vista)) {
            cliente = new Cliente();
            cliente.setNombre(vista.getTxt_Nombre().getText());
            cliente.setApellido(vista.getTxt_Apellido().getText());
            cliente.setTelefono(Integer.valueOf(vista.getTxt_Telefono().getText()));
            System.out.println("AGREGANDO CLIENTE");
            cliente.grabarCliente();
            JOptionPane.showMessageDialog(vista, "CLIENTE GRABADO CON ÉXITO", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
            ActualizarCliente(vista);
            LimpiarCampos(vista);      
        }
    }

    public static void EditarCliente(GestionCliente vista) throws SQLException {
        int fila = vista.getTabla_Cliente().getSelectedRow();
        if (fila >-1) {
            int opt = JOptionPane.showConfirmDialog(vista, "¿ESTÁ USTED SEGURO DE MODIFICAR ÉSTE CLIENTE?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (opt == JOptionPane.YES_OPTION) {
                if (ValidarCampos(vista)) {
                    cliente = new Cliente();
                    cliente.setIdCliente(Integer.valueOf(vista.getTxt_Id().getText()));
                    cliente.setNombre(vista.getTxt_Nombre().getText());
                    cliente.setApellido(vista.getTxt_Apellido().getText());
                    cliente.setTelefono(Integer.valueOf(vista.getTxt_Telefono().getText()));
                    System.out.println("MODIFICANDO CLIENTE");
                    cliente.editarCliente();
                    JOptionPane.showMessageDialog(vista, "CLIENTE MODIFICADO CON ÉXITO", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
                    ActualizarCliente(vista);
                    LimpiarCampos(vista);
                }
                
            }
        }else{
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UN CLIENTE", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void EliminarCliente(GestionCliente vista) throws SQLException {
        int fila = vista.getTabla_Cliente().getSelectedRow();
        if (fila > -1) {
            int opt = JOptionPane.showConfirmDialog(vista, "¿ESTÁ USTED SEGURO DE ELIMINAR ÉSTE CLIENTE?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (opt == JOptionPane.YES_OPTION) {
                cliente = new Cliente();
                cliente.setIdCliente(Integer.parseInt(vista.getTabla_Cliente().getModel().getValueAt(fila, 0).toString()));
                boolean bandera = cliente.tieneVenta();
                if (bandera == true) {
                    JOptionPane.showMessageDialog(vista, "ERROR: NO SE PUEDE ELIMINAR ÉSTE CLIENTE,\n TIENE VENTAS ASOCIADAS", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                }else {
                    cliente.eliminarCliente();
                    JOptionPane.showMessageDialog(vista, "CLIENTE ELIMINADO CON ÉXITO", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
                    ActualizarCliente(vista);
                    LimpiarCampos(vista);
                }
            }
        } else {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UN CLIENTE", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void LimpiarVentana(GestionCliente vista){
        LimpiarCampos(vista);
        LogicaBotones(vista);
    }
    
    public static void LimpiarCampos(GestionCliente vista){
        vista.getTxt_Apellido().setText("");
        vista.getTxt_Id().setText("");
        vista.getTxt_Nombre().setText("");
        vista.getTxt_Telefono().setText("");
    }
    
    public static void imprimirClientes(Principal vista) throws SQLException, ClassNotFoundException{
        int opt = JOptionPane.showConfirmDialog(vista, "¿DESEA IMPRIMIR EL REPORTE DE CLIENTES?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opt == JOptionPane.YES_OPTION) {
            Conexion conexion = new Conexion();
        
        //Metodo para Exportar
            try {
                JOptionPane.showMessageDialog(null, "ESTO PUEDE TARDAR UNOS SEGUNDOS,\nESPERE POR FAVOR", "ESTAMOS GENERANDO EL REPORTE", JOptionPane.WARNING_MESSAGE);
                String rutaInforme = "/home/fernando/NetBeansProjects/FinalGestionDeDatos/FinalGestionDeDatos2018/src/reportes/Clientes.jasper";
                JasperPrint informe = JasperFillManager.fillReport(rutaInforme, null, conexion.getConnection());
                JasperViewer ventanavisor = new JasperViewer(informe, false);
                JOptionPane.showMessageDialog(null, "DOCUMENTO EXPORTADO EXITOSAMENTE!", "GUARDADO EXITOSO!", JOptionPane.INFORMATION_MESSAGE);
                ventanavisor.setTitle("CLIENTES");
                ventanavisor.setExtendedState(Frame.MAXIMIZED_BOTH);
                ventanavisor.setVisible(true);
            } catch (Exception e) {//Catch del metodo exportar
                JOptionPane.showMessageDialog(null, "ERROR AL EXPORTAR DOCUMENTO");
            }//Fin de del metodo Exportar      
        }
    }

    
}
