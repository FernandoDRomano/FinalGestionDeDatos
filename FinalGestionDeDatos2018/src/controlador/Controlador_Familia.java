package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import modelo.Empleado;
import modelo.Familia;
import vista.GestionEmpleado;
import vista.GestionFamilia;

/**
 *
 * @author fernando
 */
public class Controlador_Familia {
    
    private static Empleado empleado;
    private static Familia familia;
    private static GestionFamilia vistaFamilia;
    
    public static void CargarEmpleado(GestionEmpleado vista) throws SQLException{
        empleado = new Empleado();
        empleado.setIdEmpleado(vista.getEmpleado().getIdEmpleado());
        ResultSet r = empleado.buscarEmpleadoId();
        while(r.next()){
            empleado.setApellido(r.getString("apellido"));
            empleado.setNombre(r.getString("nombre"));
            empleado.setDni(Integer.valueOf(r.getString("dni")));
        }
                
        vistaFamilia = new GestionFamilia(null, true);
        vistaFamilia.getTxt_Id().setText(String.valueOf(empleado.getIdEmpleado()));
        vistaFamilia.getTxt_Apellido().setText(empleado.getApellido());
        vistaFamilia.getTxt_Nombre().setText(empleado.getNombre());
        vistaFamilia.getTxt_Dni().setText(String.valueOf(empleado.getDni()));
        ActualizarFamilia(vistaFamilia);
        LogicaBotones(vistaFamilia);
        vistaFamilia.setVisible(true);
        
        
    }
    
    public static void ActualizarFamilia(GestionFamilia vista) throws SQLException {
        familia = new Familia();
        empleado = new Empleado();
        empleado.setIdEmpleado(Integer.valueOf(vista.getTxt_Id().getText()));
        System.out.println("METODO ACTUALIZAR CON EMPLEADO " + empleado);
        familia.setEmpleado(empleado);
        vista.getModeloTabla().setColumnCount(0);
        vista.getModeloTabla().setNumRows(0);
        vista.getModeloTabla().addColumn("Id");
        vista.getModeloTabla().addColumn("Apellido");
        vista.getModeloTabla().addColumn("Nombre");
        vista.getModeloTabla().addColumn("Dni");
        vista.getModeloTabla().addColumn("Vinculo");
        vista.getModeloTabla().addColumn("Fecha de Nacimiento");
        vista.getModeloTabla().addColumn("Discapacidad");
        vista.getModeloTabla().addColumn("Escolaridad");
        ResultSet r = familia.listarFamiliar();
        while (r.next()) {
            Object[] fila = new Object[8];
            fila[0] = r.getString("idfamilia");
            fila[1] = r.getString("apellido");
            fila[2] = r.getString("nombre");
            fila[3] = r.getString("dni");
            fila[4] = r.getString("vinculo");
            fila[5] = r.getString("fechaNacimiento");
            fila[6] = r.getString("discapacidad");
            fila[7] = r.getString("escolaridad");
            vista.getModeloTabla().addRow(fila);
        }
        vista.getTabla_GrupoFamiliar().setModel(vista.getModeloTabla());
        LogicaBotones(vista);
    }
    
    public static void AltasFamiliar(GestionFamilia vista) throws SQLException{
        empleado = new Empleado();
        familia = new Familia();
        
        familia.setApellido(vista.getTxt_ApellidoFamiliar().getText());
        familia.setNombre(vista.getTxt_NombreFamiliar().getText());
        familia.setDni(Integer.parseInt(vista.getTxt_DniFamiliar().getText()));
        familia.setVinculo(vista.getCombo_Vinculo().getSelectedItem().toString());
        familia.setEscolaridad(vista.getCombo_Escolaridad().getSelectedItem().toString());
        familia.setDiscapacidad(vista.getCombo_Discapacidad().getSelectedItem().toString());
        //Tratamiento de la fecha de Ingreso
        String fecha = new SimpleDateFormat("yyyy/MM/dd").format(vista.getDate_FechaNacimiento().getDate());
        familia.setFechaNacimiento(fecha);
        //Fin del tratamiento
        
        empleado.setIdEmpleado(Integer.valueOf(vista.getTxt_Id().getText()));
        familia.setEmpleado(empleado);
        familia.grabarFamiliar();
        JOptionPane.showMessageDialog(vista, "FAMILIAR GRABADO CON EXITO", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
        ActualizarFamilia(vista);
        LimpiarCampos(vista);
    }
    
    public static void EditarFamiliar(GestionFamilia vista) throws SQLException{
        familia = new Familia();
        empleado = new Empleado();
        int opt = JOptionPane.showConfirmDialog(vista, "¿Estas Seguro de Modificar?", "Mensaje de Alerta", JOptionPane.YES_NO_OPTION);
        if (opt == JOptionPane.YES_OPTION) {
            familia.setIdFamilia(Integer.parseInt(vista.getTxt_IdFamiliar().getText()));
            familia.setApellido(vista.getTxt_ApellidoFamiliar().getText());
            familia.setNombre(vista.getTxt_NombreFamiliar().getText());
            familia.setDni(Integer.parseInt(vista.getTxt_DniFamiliar().getText()));
            familia.setVinculo(vista.getCombo_Vinculo().getSelectedItem().toString());
            familia.setEscolaridad(vista.getCombo_Escolaridad().getSelectedItem().toString());
            familia.setDiscapacidad(vista.getCombo_Discapacidad().getSelectedItem().toString());
            //Tratamiento de la fecha de Ingreso
            String fecha = new SimpleDateFormat("yyyy/MM/dd").format(vista.getDate_FechaNacimiento().getDate());
            familia.setFechaNacimiento(fecha);
            //Fin del tratamiento
            familia.editarFamiliar();
            JOptionPane.showMessageDialog(vista, "FAMILIAR MODIFICADO CON EXITO", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
            ActualizarFamilia(vista);
            LimpiarCampos(vista);
        }
    }
    
    public static void EliminarFamiliar(GestionFamilia vista) throws SQLException{
        familia = new Familia();
        empleado = new Empleado();
        int opt = JOptionPane.showConfirmDialog(vista, "¿Estas Seguro de Eliminarlo?", "Mensaje de Alerta", JOptionPane.YES_NO_OPTION);
        if (opt == JOptionPane.YES_OPTION) {
            familia.setIdFamilia(Integer.parseInt(vista.getTxt_IdFamiliar().getText()));
            familia.eliminarFamiliar();
            JOptionPane.showMessageDialog(vista, "FAMILIAR ELIMINADO CON EXITO", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
            ActualizarFamilia(vista);
            LimpiarCampos(vista);
        }
    }
    
    public static void CargarFamiliar(GestionFamilia vista) throws ParseException{
        int fila = vista.getTabla_GrupoFamiliar().getSelectedRow();
        if (fila > -1) {
            LogicaBotonesInvertir(vista);
            vista.getTxt_IdFamiliar().setText(vista.getTabla_GrupoFamiliar().getModel().getValueAt(fila, 0).toString());
            vista.getTxt_ApellidoFamiliar().setText(vista.getTabla_GrupoFamiliar().getModel().getValueAt(fila, 1).toString());
            vista.getTxt_NombreFamiliar().setText(vista.getTabla_GrupoFamiliar().getModel().getValueAt(fila, 2).toString());
            vista.getTxt_DniFamiliar().setText(vista.getTabla_GrupoFamiliar().getModel().getValueAt(fila, 3).toString());
            vista.getCombo_Vinculo().getModel().setSelectedItem(vista.getTabla_GrupoFamiliar().getModel().getValueAt(fila, 4).toString());
            vista.getCombo_Discapacidad().getModel().setSelectedItem(vista.getTabla_GrupoFamiliar().getModel().getValueAt(fila, 6).toString());
            vista.getCombo_Escolaridad().getModel().setSelectedItem(vista.getTabla_GrupoFamiliar().getModel().getValueAt(fila, 7).toString());
            //Tratamiento de la Fecha
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
            String fechaEmpleado = vista.getTabla_GrupoFamiliar().getModel().getValueAt(fila, 5).toString();
            Date fecha = null;
            fecha = formatoDelTexto.parse(fechaEmpleado);
            vista.getDate_FechaNacimiento().setDate(fecha);
            //Fin del Tratamiento
        }
    }
    
    public static void LimpiarCampos(GestionFamilia vista){
        vista.getTxt_ApellidoFamiliar().setText("");
        vista.getTxt_NombreFamiliar().setText("");
        vista.getTxt_DniFamiliar().setText("");
        vista.getCombo_Escolaridad().getModel().setSelectedItem("Seleccione una Opción");
        vista.getCombo_Vinculo().getModel().setSelectedItem("Seleccione una Opción");
        vista.getCombo_Discapacidad().getModel().setSelectedItem("Seleccione una Opción");
        vista.getDate_FechaNacimiento().setCalendar(null);
    }
    
    public static void LogicaBotones(GestionFamilia vista){
        vista.getBtn_Agregar().setEnabled(true);
        vista.getBtn_Editar().setEnabled(false);
        vista.getBtn_Eliminar().setEnabled(false);
    }
    
    public static void LogicaBotonesInvertir(GestionFamilia vista){
        vista.getBtn_Agregar().setEnabled(false);
        vista.getBtn_Editar().setEnabled(true);
        vista.getBtn_Eliminar().setEnabled(true);
    }
    
    
}
