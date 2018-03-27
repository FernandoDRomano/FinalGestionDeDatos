
package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Domicilio;
import modelo.Proveedor;
import vista.GestionProveedor;

/**
 *
 * @author Fernando
 */
public class Controlador_Proveedor {
    
    //DECLARO LAS VARIABLES
    private static Proveedor proveedor;
    private static Domicilio domicilio;
 
    //METODOS ABM
    public static void ActualizarProveedor(GestionProveedor vista) throws SQLException {
        proveedor = new Proveedor();
        vista.getModelo().setColumnCount(0);
        vista.getModelo().setNumRows(0);
        vista.getModelo().addColumn("Id");
        vista.getModelo().addColumn("Nombre");
        vista.getModelo().addColumn("CUIT");
        vista.getModelo().addColumn("Razón Social");
        vista.getModelo().addColumn("Telefono");
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
    }
    
    
    public static void AltaProveedor(GestionProveedor vista) throws SQLException{
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
        JOptionPane.showMessageDialog(vista, "PROVEEDOR GRABADO CON EXITO", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
        LimpiarCampos(vista);
        ActualizarProveedor(vista);
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
        }else{
              JOptionPane.showMessageDialog(vista, "DEBE SELECCIONAR UN PROVEEDOR", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void EliminarProveedor(GestionProveedor vista) throws SQLException{
        int fila = vista.getTabla_Proveedores().getSelectedRow();
        if (fila > -1) {
            int opt = JOptionPane.showConfirmDialog(vista, "Esta seguro de Borrar ?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION);
            if (opt == JOptionPane.YES_OPTION) {
               proveedor = new Proveedor();
               domicilio = new Domicilio();
               proveedor.setIdProveedor(Integer.valueOf(vista.getTxt_Id().getText()));
               ResultSet r = proveedor.buscarProveedorId();
               while (r.next()) {                
                   domicilio.setIdDomicilio(Integer.parseInt(r.getString("iddomicilio")));
               }
               //ELIMINO EL PROVEEDOR PRIMERO
               proveedor.eliminarProveedor();
               //ELIMINO POR ULTIMO EL DOMICILIO
               domicilio.eliminarDomicilio();
               //ACTUALIZO Y MANDO UN MSJ DE INFORMACION
               JOptionPane.showMessageDialog(vista, "PROVEEDOR ELIMINADO CON EXITO!", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
               LimpiarCampos(vista);
               ActualizarProveedor(vista);
            }
        }else{
            JOptionPane.showMessageDialog(vista, "DEBE SELECCIONAR UN PROVEEDOR", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void EditarProveedor(GestionProveedor vista) throws SQLException{
        int fila = vista.getTabla_Proveedores().getSelectedRow();
        if (fila > -1) {
            int opt = JOptionPane.showConfirmDialog(vista, "¿Esta seguro de Editar?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION);
            if (opt == JOptionPane.YES_OPTION) {
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
        }else{
            JOptionPane.showMessageDialog(vista, "DEBE SELECCIONAR UN PROVEEDOR", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
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
    }
    
}
