
package controlador;

import java.awt.Frame;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Conexion;
import modelo.Domicilio;
import modelo.Proveedor;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import vista.GestionProveedor;
import vista.Principal;

/**
 *
 * @author Fernando
 */
public class Controlador_Proveedor {
    
    //DECLARO LAS VARIABLES
    private static Proveedor proveedor;
    private static Domicilio domicilio;
    
    public static void LogicaBotones(GestionProveedor vista){
        vista.getBtn_Agregar().setEnabled(true);
        vista.getBtn_Editar().setEnabled(false);
        vista.getBtn_Eliminar().setEnabled(false);
        vista.getBtn_Limpiar().setEnabled(false);
    }
    
    public static void LogicaBotonesInvertir(GestionProveedor vista){
        vista.getBtn_Agregar().setEnabled(false);
        vista.getBtn_Editar().setEnabled(true);
        vista.getBtn_Eliminar().setEnabled(true);
        vista.getBtn_Limpiar().setEnabled(true);
    }
    
    public static boolean ValidarCampos(GestionProveedor vista){
        boolean bandera = true;
        //VALIDO QUE LOS CAMPOS NO SEAN VACIOS
        String nombre = vista.getTxt_Nombre().getText();
        nombre = nombre.replaceAll(" ", ""); //REEMPLAZO LOS ESPACIO VACIO
        String razon = vista.getCombo_RazonSocial().getSelectedItem().toString();
        String cuit = vista.getTxt_Cuit().getText();
        cuit = cuit.replaceAll(" ", "");
        String telefono = vista.getTxt_Telefono().getText();
        telefono = telefono.replace(" ", "");
        String calle = vista.getTxt_Calle().getText();
        calle = calle.replace(" ", "");
        String numero = vista.getTxt_Numero().getText();
        numero = numero.replace(" ", "");
        
        if (nombre.length() == 0) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE COMPLETAR EL NOMBRE", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        if (cuit.length() == 0) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE COMPLETAR EL CUIT", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        if (telefono.length() == 0) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE COMPLETAR EL TELÉFONO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        if (razon.equals("Seleccione una Opción")) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UNA OPCIÓN EN EL CAMPO RAZÓN SOCIAL", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
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
        
        return bandera;
    }

 
    //METODOS ABM
    public static void ActualizarProveedor(GestionProveedor vista) throws SQLException {
        proveedor = new Proveedor();
        vista.getModelo().setColumnCount(0);
        vista.getModelo().setNumRows(0);
        vista.getModelo().addColumn("Id");
        vista.getModelo().addColumn("Nombre");
        vista.getModelo().addColumn("CUIT");
        vista.getModelo().addColumn("Razón Social");
        vista.getModelo().addColumn("Teléfono");
        vista.getModelo().addColumn("Correo");
        vista.getModelo().addColumn("Domicilio");
        ResultSet r = proveedor.listarProveedor();
        while (r.next()) {
            Object[] fila = new Object[7];
            fila[0] = r.getString("idproveedor");
            fila[1] = r.getString("nombre");
            fila[2] = r.getString("cuit");
            fila[3] = r.getString("razonSocial");
            fila[4] = r.getString("telefono");
            fila[5] = r.getString("correo");
            fila[6] = r.getString("domicilio");

            vista.getModelo().addRow(fila);
        }
        vista.getTabla_Proveedores().setModel(vista.getModelo());
        LogicaBotones(vista);
    }
    
    
    public static void AltaProveedor(GestionProveedor vista) throws SQLException{
        if (ValidarCampos(vista)) {
            //INSTANCIO LOS OBJETOS A UTILIZAR
            proveedor = new Proveedor();
            domicilio = new Domicilio();

            //CAPTURO LOS DATOS DEL PROVEEDOR
            proveedor.setNombre(vista.getTxt_Nombre().getText());
            proveedor.setCuit(Long.valueOf(vista.getTxt_Cuit().getText()));
            proveedor.setCorreo(vista.getTxt_Correo().getText());
            proveedor.setRazonSocial((String) vista.getCombo_RazonSocial().getModel().getSelectedItem());
            proveedor.setTelefono(Integer.valueOf(vista.getTxt_Telefono().getText()));
            //CAPTURO LOS DATOS DEL DOMICILIO
            domicilio.setCalle(vista.getTxt_Calle().getText());
            domicilio.setNumero(vista.getTxt_Numero().getText());
            domicilio.setPiso(vista.getTxt_Piso().getText());
            domicilio.setDepartamento(vista.getTxt_Departamento().getText());
            //GRABO EL DOMICILIO
            domicilio.grabarDomicilio();
            //TRAIGO EL ID DEL DOMICILIO Y LO SETEO AL PROVEEDOR
            domicilio.setIdDomicilio(Integer.valueOf(domicilio.ultimoDomicilio()));
            proveedor.setDomicilio(domicilio);
            //GRABO EL PROVEEDOR
            proveedor.grabarProveedor();
            JOptionPane.showMessageDialog(vista, "PROVEEDOR GRABADO CON ÉXITO", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
            LimpiarCampos(vista);
            ActualizarProveedor(vista);
        }
        
    }
    
    public static void MostrarProveedor(GestionProveedor vista) throws SQLException{
        int fila = vista.getTabla_Proveedores().getSelectedRow();
        if (fila > -1) {
              proveedor = new Proveedor();
              //SOLO SETEO EL ID PARA TRAER LOS DATOS DEL DOMICILIO LUEGO
              proveedor.setIdProveedor(Integer.valueOf(vista.getTabla_Proveedores().getModel().getValueAt(fila, 0).toString()));
              vista.getTxt_Id().setText(vista.getTabla_Proveedores().getModel().getValueAt(fila, 0).toString());
              vista.getTxt_Nombre().setText(vista.getTabla_Proveedores().getModel().getValueAt(fila, 1).toString());
              vista.getTxt_Cuit().setText(vista.getTabla_Proveedores().getModel().getValueAt(fila, 2).toString());
              vista.getCombo_RazonSocial().setSelectedItem(vista.getTabla_Proveedores().getModel().getValueAt(fila, 3).toString());
              vista.getTxt_Telefono().setText(vista.getTabla_Proveedores().getModel().getValueAt(fila, 4).toString());
              vista.getTxt_Correo().setText(vista.getTabla_Proveedores().getModel().getValueAt(fila, 5).toString());
              //BUSCO EL DOMICILIO POR EL ID DEL PROVEEDOR
              ResultSet r = proveedor.buscarProveedorId();
              //RECORRO Y LOS CARGO EN LA VISTA
              while (r.next()) {                
                vista.getTxt_Calle().setText(r.getString("calle"));
                vista.getTxt_Numero().setText(r.getString("numero"));
                vista.getTxt_Piso().setText(r.getString("piso"));
                vista.getTxt_Departamento().setText(r.getString("departamento"));
              }
              LogicaBotonesInvertir(vista);
        }else{
              JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UN PROVEEDOR", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void EliminarProveedor(GestionProveedor vista) throws SQLException{
        int fila = vista.getTabla_Proveedores().getSelectedRow();
        if (fila > -1) {
            int opt = JOptionPane.showConfirmDialog(vista, "¿ESTÁ USTED SEGURO DE ELIMINAR ÉSTE PROVEEDOR?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (opt == JOptionPane.YES_OPTION) {
               proveedor = new Proveedor();
               domicilio = new Domicilio();
               proveedor.setIdProveedor(Integer.valueOf(vista.getTxt_Id().getText()));
               //VALIDO QUE EL PROVEEDOR NO TENGA PEDIDOS ASOCIADOS
               boolean bandera = proveedor.tienePedidos();
               if (bandera == true) {
                    JOptionPane.showMessageDialog(vista, "ERROR: NO SE PUEDE ELIMINAR ÉSTE PROVEEDOR,\n TIENE PEDIDOS ASOCIADOS", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
               }else{
                    ResultSet r = proveedor.buscarProveedorId();
                    while (r.next()) {                
                        domicilio.setIdDomicilio(Integer.parseInt(r.getString("iddomicilio")));
                    }
                    //ELIMINO EL PROVEEDOR PRIMERO
                    proveedor.eliminarProveedor();
                    //ELIMINO POR ULTIMO EL DOMICILIO
                    domicilio.eliminarDomicilio();
                    //ACTUALIZO Y MANDO UN MSJ DE INFORMACION
                    JOptionPane.showMessageDialog(vista, "PROVEEDOR ELIMINADO CON ÉXITO!", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
                    LimpiarCampos(vista);
                    ActualizarProveedor(vista);
               }
            }
        }else{
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UN PROVEEDOR", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void EditarProveedor(GestionProveedor vista) throws SQLException{
        int fila = vista.getTabla_Proveedores().getSelectedRow();
        if (fila > -1) {
            int opt = JOptionPane.showConfirmDialog(vista, "¿ESTÁ USTED SEGURO DE MODIFICAR ÉSTE PROVEEDOR?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (opt == JOptionPane.YES_OPTION) {
                if (ValidarCampos(vista)) {
                    proveedor = new Proveedor();
                    domicilio = new Domicilio();
                    proveedor.setIdProveedor(Integer.valueOf(vista.getTxt_Id().getText()));
                    //CAPTURO LOS DATOS DEL PROVEEDOR Y DEL DOMICILIO
                    proveedor.setNombre(vista.getTxt_Nombre().getText());
                    proveedor.setRazonSocial(vista.getCombo_RazonSocial().getModel().getSelectedItem().toString());
                    proveedor.setTelefono(Integer.valueOf(vista.getTxt_Telefono().getText()));
                    proveedor.setCorreo(vista.getTxt_Correo().getText());
                    proveedor.setCuit(Long.valueOf(vista.getTxt_Cuit().getText()));
                    domicilio.setCalle(vista.getTxt_Calle().getText());
                    domicilio.setNumero(vista.getTxt_Numero().getText());
                    domicilio.setPiso(vista.getTxt_Piso().getText());
                    domicilio.setDepartamento(vista.getTxt_Departamento().getText());
                    //BUSCO EL ID DEL DOMICILIO ASOCIADO AL PROVEEDOR EXISTENTE
                    ResultSet r = proveedor.buscarProveedorId();
                    while (r.next()) {                
                        domicilio.setIdDomicilio(Integer.parseInt(r.getString("iddomicilio")));
                    }
                    //ACTUALIZO EL PROVEEDOR
                    proveedor.editarProveedor();
                    //ACTUALIZO EL DOMICILIO
                    domicilio.editarDomicilio();
                    //ACTUALIZO Y MANDO UN MSJ DE INFORMACION
                    JOptionPane.showMessageDialog(vista, "PROVEEDOR MODIFICADO CON EXITO!", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
                    LimpiarCampos(vista);
                    ActualizarProveedor(vista);
                }
               
            }
        }else{
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UN PROVEEDOR", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void LimpiarCampos(GestionProveedor vista){
        vista.getTxt_Id().setText("");
        vista.getTxt_Calle().setText("");
        vista.getTxt_Correo().setText("");
        vista.getTxt_Cuit().setText("");
        vista.getTxt_Departamento().setText("");
        vista.getTxt_Nombre().setText("");
        vista.getTxt_Numero().setText("");
        vista.getTxt_Piso().setText("");
        vista.getTxt_Telefono().setText("");
        vista.getCombo_RazonSocial().setSelectedItem("Seleccione una Opción");
        LogicaBotones(vista);
    }
    
    public static void imprimirProveedores(Principal vista) throws SQLException, ClassNotFoundException{
        int opt = JOptionPane.showConfirmDialog(vista, "¿DESEA IMPRIMIR EL REPORTE DE PROVEEDORES?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opt == JOptionPane.YES_OPTION) {
            Conexion conexion = new Conexion();
        
        //Metodo para Exportar
            try {
                JOptionPane.showMessageDialog(null, "ESTO PUEDE TARDAR UNOS SEGUNDOS,\nESPERE POR FAVOR", "ESTAMOS GENERANDO EL REPORTE", JOptionPane.WARNING_MESSAGE);
                String rutaInforme = "/home/fernando/NetBeansProjects/FinalGestionDeDatos/FinalGestionDeDatos2018/src/reportes/Proveedores.jasper";
                JasperPrint informe = JasperFillManager.fillReport(rutaInforme, null, conexion.getConnection());
                JasperViewer ventanavisor = new JasperViewer(informe, false);
                JOptionPane.showMessageDialog(null, "DOCUMENTO EXPORTADO EXITOSAMENTE!", "GUARDADO EXITOSO!", JOptionPane.INFORMATION_MESSAGE);
                ventanavisor.setTitle("PROVEEDORES");
                ventanavisor.setExtendedState(Frame.MAXIMIZED_BOTH);
                ventanavisor.setVisible(true);
                conexion.desconectar();
            } catch (Exception e) {//Catch del metodo exportar
                JOptionPane.showMessageDialog(null, "ERROR AL EXPORTAR DOCUMENTO");
                System.out.println(e);
            }//Fin de del metodo Exportar      
        }
    }

    
}
