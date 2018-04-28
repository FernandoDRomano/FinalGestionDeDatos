package controlador;

import java.awt.Frame;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.Conexion;
import modelo.Domicilio;
import modelo.Localidad;
import modelo.Provincia;
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
    private static Domicilio domicilio;
    private static Provincia provincia;
    private static Localidad localidad;
    
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
    
    public static void CargarProvincia(GestionCliente vista) throws SQLException{
        provincia = new Provincia();
        ResultSet r = provincia.listar();
        vista.getModeloProvincia().addElement("Seleccione una Opción");
        while(r.next()){
            provincia = new Provincia();
            provincia.setIdprovincia(Integer.valueOf(r.getString("idprovincia")));
            provincia.setNombre(r.getString("nombre"));
            vista.getModeloProvincia().addElement(provincia);
        }
        vista.getCombo_Pronvincia().setModel(vista.getModeloProvincia());
    }
    
    public static void CargarLocalidad(GestionCliente vista) throws SQLException{
        if (!vista.getCombo_Pronvincia().getModel().getSelectedItem().equals("Seleccione una Opción")) {
            provincia = new Provincia();
            provincia = (Provincia) vista.getCombo_Pronvincia().getModel().getSelectedItem();
            localidad = new Localidad();
            localidad.setProvincia(provincia);
            //Limpiar el Combo
            vista.getModeloLocalidad().removeAllElements();
            ResultSet r = localidad.listar();
            vista.getModeloLocalidad().addElement("Seleccione una Opción");
            while(r.next()){
                localidad = new Localidad();
                localidad.setIdlocalidad(Integer.valueOf(r.getString("idlocalidad")));
                localidad.setNombre(r.getString("nombre"));
                localidad.setCodigoPostal(Integer.valueOf(r.getString("codigoPostal")));
                vista.getModeloLocalidad().addElement(localidad);
            }
            vista.getCombo_Localidad().setModel(vista.getModeloLocalidad());
        }else{
            vista.getCombo_Localidad().setSelectedItem("Seleccione una Opción");
        }
        
    }
        
    
    public static boolean ValidarCampos(GestionCliente vista){
        boolean bandera = true;
        //VALIDO QUE LOS CAMPOS NO SEAN VACIOS
        String apellido = vista.getTxt_Apellido().getText();
        apellido = apellido.replaceAll(" ", ""); //REEMPLAZO LOS ESPACIO VACIO
        
        String nombre = vista.getTxt_Nombre().getText();
        nombre = nombre.replace(" ", "");
        
        String calle = vista.getTxt_Calle().getText();
        calle = calle.replace(" ", "");
        
        String numero = vista.getTxt_Numero().getText();
        numero = numero.replace(" ", "");
        
        String localidad = vista.getCombo_Localidad().getSelectedItem().toString();
        
        if (apellido.length() == 0) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE COMPLETAR EL APELLIDO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
            
        if (nombre.length() == 0) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE COMPLETAR EL NOMBRE", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        if (calle.length() == 0) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE COMPLETAR EL NOMBRE DE LA CALLE", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        if (numero.length() == 0) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE COMPLETAR EL NÚMERO DE LA CALLE", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        if (localidad.equals("Seleccione una Opción")) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UNA LOCALIDAD", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
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
        vista.getModelo().addColumn("Domicilio");
        ResultSet r = cliente.listarCliente();
        while (r.next()) {
            Object[] fila = new Object[5];
            fila[0] = r.getString("idCliente");
            fila[1] = r.getString("apellido");
            fila[2] = r.getString("nombre");
            fila[3] = r.getString("telefono");
            fila[4] = r.getString("domicilio");
            vista.getModelo().addRow(fila);
        }
        vista.getTabla_Cliente().setModel(vista.getModelo());
        LogicaBotones(vista);
    }

    public static void Mostrar(GestionCliente vista) throws SQLException {
        int fila = vista.getTabla_Cliente().getSelectedRow();
        if(fila > -1){
            cliente = new Cliente();
            provincia = new Provincia();
            Localidad loc = new Localidad();
            LogicaBotonesInvertir(vista);
            vista.getTxt_Id().setEnabled(false);
            cliente.setIdCliente(Integer.valueOf(vista.getTabla_Cliente().getModel().getValueAt(fila, 0).toString()));
            vista.getTxt_Id().setText(vista.getTabla_Cliente().getModel().getValueAt(fila, 0).toString());
            vista.getTxt_Nombre().setText(vista.getTabla_Cliente().getModel().getValueAt(fila, 2).toString());
            vista.getTxt_Apellido().setText(vista.getTabla_Cliente().getModel().getValueAt(fila, 1).toString());
            vista.getTxt_Telefono().setText(vista.getTabla_Cliente().getModel().getValueAt(fila, 3).toString());
            //BUSCO EL DOMICILIO POR EL ID DEL PROVEEDOR
              ResultSet r = cliente.buscarClienteId();
              //RECORRO Y LOS CARGO EN LA VISTA
              while (r.next()) {                
                vista.getTxt_Calle().setText(r.getString("calle"));
                vista.getTxt_Numero().setText(r.getString("numero"));
                vista.getTxt_Piso().setText(r.getString("piso"));
                vista.getTxt_Departamento().setText(r.getString("departamento"));
                provincia.setIdprovincia(Integer.valueOf(r.getString("idprovincia")));
                provincia.setNombre(r.getString("provincia"));
                loc.setIdlocalidad(Integer.valueOf(r.getString("idlocalidad")));
                loc.setNombre(r.getString("localidad"));
                loc.setCodigoPostal(Integer.valueOf(r.getString("codigoPostal")));
              }
              vista.getCombo_Pronvincia().getModel().setSelectedItem(provincia);
              vista.getCombo_Localidad().getModel().setSelectedItem(loc);
        }
    }

    public static void AltasCliente(GestionCliente vista) throws SQLException {
        if (ValidarCampos(vista)) {
            cliente = new Cliente();
            domicilio = new Domicilio();
            localidad = new Localidad();
            //CAPTURO LOS DATOS DEL DOMICILIO
            domicilio.setCalle(vista.getTxt_Calle().getText());
            domicilio.setNumero(vista.getTxt_Numero().getText());
            domicilio.setPiso(vista.getTxt_Piso().getText());
            domicilio.setDepartamento(vista.getTxt_Departamento().getText());
            //Seteo la Localidad para asignarle al Domicilio
            localidad = (Localidad) vista.getCombo_Localidad().getSelectedItem();
            domicilio.setLocalidad(localidad);
            //GRABO EL DOMICILIO
            domicilio.grabarDomicilio();
            //TRAIGO EL ID DEL DOMICILIO Y LO SETEO AL PROVEEDOR
            domicilio.setIdDomicilio(Integer.valueOf(domicilio.ultimoDomicilio()));
            //SETEO AL CLIENTE
            cliente.setNombre(vista.getTxt_Nombre().getText());
            cliente.setApellido(vista.getTxt_Apellido().getText());
            cliente.setTelefono(Integer.valueOf(vista.getTxt_Telefono().getText()));
            cliente.setDomicilio(domicilio);
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
                    domicilio = new Domicilio();
                    localidad = new Localidad();
                    //SETEO LOS VALORES DEL DOMICILIO Y DEL CLIENTE
                    cliente.setIdCliente(Integer.valueOf(vista.getTxt_Id().getText()));
                    cliente.setNombre(vista.getTxt_Nombre().getText());
                    cliente.setApellido(vista.getTxt_Apellido().getText());
                    cliente.setTelefono(Integer.valueOf(vista.getTxt_Telefono().getText()));
                    domicilio.setCalle(vista.getTxt_Calle().getText());
                    domicilio.setNumero(vista.getTxt_Numero().getText());
                    domicilio.setPiso(vista.getTxt_Piso().getText());
                    domicilio.setDepartamento(vista.getTxt_Departamento().getText());
                    //Seteo la Localidad para asignarle al Domicilio
                    localidad = (Localidad) vista.getCombo_Localidad().getSelectedItem();
                    domicilio.setLocalidad(localidad);
                    //BUSCO EL ID DEL DOMICILIO ASOCIADO AL PROVEEDOR EXISTENTE
                    ResultSet r = cliente.buscarClienteId();
                    while (r.next()) {                
                        domicilio.setIdDomicilio(Integer.parseInt(r.getString("iddomicilio")));
                    }
                    //ACTUALIZO EL PROVEEDOR
                    cliente.editarCliente();
                    //ACTUALIZO EL DOMICILIO
                    domicilio.editarDomicilio();
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
                domicilio = new Domicilio();
                cliente.setIdCliente(Integer.parseInt(vista.getTabla_Cliente().getModel().getValueAt(fila, 0).toString()));
                boolean bandera = cliente.tieneVenta();
                if (bandera == true) {
                    JOptionPane.showMessageDialog(vista, "ERROR: NO SE PUEDE ELIMINAR ÉSTE CLIENTE,\n TIENE VENTAS ASOCIADAS", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                }else {
                    ResultSet r = cliente.buscarClienteId();
                    while (r.next()) {                
                        domicilio.setIdDomicilio(Integer.parseInt(r.getString("iddomicilio")));
                    }
                    cliente.eliminarCliente();
                    domicilio.eliminarDomicilio();
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
        vista.getTxt_Calle().setText("");
        vista.getTxt_Numero().setText("");
        vista.getTxt_Departamento().setText("");
        vista.getTxt_Piso().setText("");
        vista.getCombo_Pronvincia().setSelectedItem("Seleccione una Opción");
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
