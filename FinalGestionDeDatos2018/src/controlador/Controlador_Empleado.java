
package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import modelo.Cargo;
import modelo.Domicilio;
import modelo.Empleado;
import modelo.Perfil;
import modelo.Usuario;
import org.apache.commons.codec.digest.DigestUtils;
import vista.EditarEmpleado;
import vista.GestionEmpleado;
import vista.NuevoEmpleado;

/**
 *
 * @author Fernando
 */
public class Controlador_Empleado {
    
    private static Empleado empleado;
    private static Cargo cargo;
    private static Usuario usuario;
    private static Domicilio domicilio;
    private static Perfil perfil;
    
    public static void ActualizarEmpleado(GestionEmpleado vista) throws SQLException {
        empleado = new Empleado();
        vista.getModeloTabla().setColumnCount(0);
        vista.getModeloTabla().setNumRows(0);
        vista.getModeloTabla().addColumn("Id");
        vista.getModeloTabla().addColumn("Apellido");
        vista.getModeloTabla().addColumn("Nombre");
        vista.getModeloTabla().addColumn("Dni");
        vista.getModeloTabla().addColumn("Fecha de Ingreso");
        vista.getModeloTabla().addColumn("Domicilio");
        vista.getModeloTabla().addColumn("Usuario");
        vista.getModeloTabla().addColumn("Cargo");
        vista.getModeloTabla().addColumn("Perfil");
        vista.getModeloTabla().addColumn("Estado");
        ResultSet r = empleado.listarEmpleado();
        while (r.next()) {
            Object[] fila = new Object[10];
            fila[0] = r.getString("idempleado");
            fila[1] = r.getString("apellido");
            fila[2] = r.getString("nombre");
            fila[3] = r.getString("dni");
            fila[4] = r.getString("fechaIngreso");
            fila[5] = r.getString("domicilio");
            fila[6] = r.getString("usuario");
            fila[7] = r.getString("cargo");
            fila[8] = r.getString("perfil");
            fila[9] = r.getString("estado");

            vista.getModeloTabla().addRow(fila);
        }
        vista.getTabla_Empleado().setModel(vista.getModeloTabla());
    }
    
    public static void CargarPerfil(NuevoEmpleado vista) throws SQLException{
        perfil = new Perfil();
        ResultSet r = perfil.listar();
        while(r.next()){
            perfil = new Perfil();
            perfil.setIdPerfil(Integer.valueOf(r.getString("idperfil")));
            perfil.setNombre(r.getString("nombre"));
            vista.getModeloComboPerfil().addElement(perfil);
        }
        vista.getCombo_Perfil().setModel(vista.getModeloComboPerfil());
    }

    public static void CargarCargo(NuevoEmpleado vista) throws SQLException{
        cargo = new Cargo();
        ResultSet r = cargo.listarCargo();
        while(r.next()){
            cargo = new Cargo();
            cargo.setIdCargo(Integer.valueOf(r.getString("idcargo")));
            cargo.setCargo(r.getString("cargo"));
            vista.getModeloComboCargo().addElement(cargo);
        }
        vista.getCombo_Cargo().setModel(vista.getModeloComboCargo());
    }
    
    public static void CargarPerfil(EditarEmpleado vista) throws SQLException{
        perfil = new Perfil();
        ResultSet r = perfil.listar();
        while(r.next()){
            perfil = new Perfil();
            perfil.setIdPerfil(Integer.valueOf(r.getString("idperfil")));
            perfil.setNombre(r.getString("nombre"));
            vista.getModeloComboPerfil().addElement(perfil);
        }
        vista.getCombo_Perfil().setModel(vista.getModeloComboPerfil());
    }

    public static void CargarCargo(EditarEmpleado vista) throws SQLException{
        cargo = new Cargo();
        ResultSet r = cargo.listarCargo();
        while(r.next()){
            cargo = new Cargo();
            cargo.setIdCargo(Integer.valueOf(r.getString("idcargo")));
            cargo.setCargo(r.getString("cargo"));
            vista.getModeloComboCargo().addElement(cargo);
        }
        vista.getCombo_Cargo().setModel(vista.getModeloComboCargo());
    }
    
    public static void AltasEmpleado(NuevoEmpleado vista) {
        empleado = new Empleado();
        usuario = new Usuario();
        domicilio = new Domicilio();
        perfil = new Perfil();
        //Seteo los Valores del Domicilio
        domicilio.setCalle(vista.getTxt_Calle().getText());
        domicilio.setNumero(vista.getTxt_Numero().getText());
        domicilio.setPiso(vista.getTxt_Piso().getText());
        domicilio.setDepartamento(vista.getTxt_Departamento().getText());
        //Seteo los valores del Usuario
        usuario.setNombreUsuario(vista.getTxt_Usuario().getText());
        usuario.setClave(vista.getTxt_Dni().getText()); //AL CREARSE EL USUARIO SE LE ASIGNARA COMO CONTRASEÑA EL DNI
        /*
            ENCRIPTAR CON SHA1
            Aquí estamos haciendo uso de la clase DigestUtils y su método sha1Hex(), 
            el cuál recibe un parámetro que es la cadena a encriptar y retorna una cadena que ya está encriptada.
        */
        String textoSinEncriptar = vista.getTxt_Dni().getText(); //AL CREARSE EL USUARIO SE LE ASIGNARA COMO CONTRASEÑA EL DNI
        String textoEncriptadoConSHA=DigestUtils.sha1Hex(textoSinEncriptar); //SE ENCRIPTA LA CONTRASEÑA
        System.out.println("Texto Encriptado con SHA : "+textoEncriptadoConSHA);
        usuario.setClave(textoEncriptadoConSHA); //SE ASIGNA AL EMPLEADO
        //Capturo el perfil y el Cargo
        cargo = (Cargo) vista.getCombo_Cargo().getModel().getSelectedItem();
        perfil = (Perfil) vista.getCombo_Perfil().getModel().getSelectedItem();
        //Le asigno el perfil al usuario
        usuario.setPerfil(perfil);
        //Seteo los valores del Empleado
        empleado.setApellido(vista.getTxt_Apellido().getText());
        empleado.setNombre(vista.getTxt_Nombre().getText());
        empleado.setDni(Integer.valueOf(vista.getTxt_Dni().getText()));
        empleado.setTelefono(vista.getTxt_Telefono().getText());
        //Tratamiento de la fecha de Ingreso
        String fecha = new SimpleDateFormat("yyyy/MM/dd").format(vista.getDate_Fecha().getDate());
        empleado.setFechaIngreso(fecha);
        //Fin del tratamiento
        empleado.setSexo(vista.getCombo_Sexo().getSelectedItem().toString());
        empleado.setEstado(vista.getCombo_Estado().getSelectedItem().toString());
        //Empiezo a Grabar
        // 1) Grabo Domicilio y Traigo su Id para setearlo
        domicilio.grabarDomicilio();
        domicilio.setIdDomicilio(Integer.parseInt(domicilio.ultimoDomicilio()));
        // 2) Grabo Usuario y Traigo su Id para setearlo
        usuario.grabarUsuario();
        usuario.setIdUsuario(Integer.parseInt(usuario.ultimoUsuario()));
        // 3) Seteo los objetos asociados al Empleado (Domicilio - Usuario - Cargo)
        empleado.setDomicilio(domicilio);
        empleado.setUsuario(usuario);
        empleado.setCargo(cargo);
        empleado.grabarEmpleado();
        JOptionPane.showMessageDialog(vista, "EMPLEADO GRABADO CON EXITO", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void MostrarEmpleado(EditarEmpleado vista) throws SQLException, ParseException {
        empleado = new Empleado();
        cargo = new Cargo();
        usuario = new Usuario();
        perfil = new Perfil();
        domicilio = new Domicilio();
        
        //CAPTURO EL ID DEL EMPLEADO DE LA VISTA, QUE FUE PASADO DE LA VISTA GESTION DE EMPLEADOS
        empleado.setIdEmpleado(vista.getEmpleado().getIdEmpleado());
        
        //BUSCO EL EMPLEADO
        ResultSet r = empleado.buscarEmpleadoId();
        while(r.next()){
            empleado.setApellido(r.getString("apellido"));
            empleado.setNombre(r.getString("nombre"));
            empleado.setDni(Integer.valueOf(r.getString("dni")));
            empleado.setTelefono(r.getString("telefono"));
            empleado.setFechaIngreso(r.getString("fechaIngreso"));
            empleado.setSexo(r.getString("sexo"));
            empleado.setEstado(r.getString("estado"));
            usuario.setIdUsuario(Integer.valueOf(r.getString("idusuario")));
            usuario.setNombreUsuario(r.getString("nombreUsuario"));
            perfil.setIdPerfil(Integer.valueOf(r.getString("idperfil")));
            perfil.setNombre(r.getString("perfil"));
            cargo.setIdCargo(Integer.valueOf(r.getString("idcargo")));
            cargo.setCargo(r.getString("cargo"));
            domicilio.setIdDomicilio(Integer.valueOf(r.getString("iddomicilio")));
            domicilio.setCalle(r.getString("calle"));
            domicilio.setNumero(r.getString("numero"));
            domicilio.setPiso(r.getString("piso"));
            domicilio.setDepartamento(r.getString("departamento"));
        }
        

        //Cargo los campos del Formulario
        vista.getTxt_Id().setText(String.valueOf(empleado.getIdEmpleado()));
        vista.getTxt_Apellido().setText(empleado.getApellido());
        vista.getTxt_Nombre().setText(empleado.getNombre());
        vista.getTxt_Dni().setText(String.valueOf(empleado.getDni()));
        vista.getTxt_Telefono().setText(empleado.getTelefono());
        vista.getCombo_Sexo().getModel().setSelectedItem(empleado.getSexo());
        //vista.getDate_Fecha().setDateFormatString(string);
        //Tratamiento de la Fecha
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        String fechaEmpleado = empleado.getFechaIngreso();
        Date fecha = null;
        fecha = formatoDelTexto.parse(fechaEmpleado);
        vista.getDate_Fecha().setDate(fecha);
        //Fin del Tratamiento
        vista.getCombo_Estado().getModel().setSelectedItem(empleado.getEstado());
        vista.getTxt_Calle().setText(domicilio.getCalle());
        vista.getTxt_Numero().setText(domicilio.getNumero());
        vista.getTxt_Piso().setText(domicilio.getPiso());
        vista.getTxt_Departamento().setText(domicilio.getDepartamento());
        vista.getCombo_Cargo().getModel().setSelectedItem(cargo);
        vista.getCombo_Perfil().getModel().setSelectedItem(perfil);
        vista.getTxt_Usuario().setText(usuario.getNombreUsuario());
        
    }
    
    public static void EditarEmpleado(EditarEmpleado vista) throws SQLException{
        int opt = JOptionPane.showConfirmDialog(vista, "¿Estas Seguro de Modificar?", "Mensaje de Alerta", JOptionPane.YES_NO_OPTION);
        if (opt == JOptionPane.YES_OPTION) {
            empleado = new Empleado();
            usuario = new Usuario();
            domicilio = new Domicilio();
            perfil = new Perfil();
            //Seteo los Valores del Domicilio
            domicilio.setCalle(vista.getTxt_Calle().getText());
            domicilio.setNumero(vista.getTxt_Numero().getText());
            domicilio.setPiso(vista.getTxt_Piso().getText());
            domicilio.setDepartamento(vista.getTxt_Departamento().getText());
            //Seteo los valores del Usuario
            usuario.setNombreUsuario(vista.getTxt_Usuario().getText());
            usuario.setClave(vista.getTxt_Dni().getText()); //AL CREARSE EL USUARIO SE LE ASIGNARA COMO CONTRASEÑA EL DNI
            //Capturo el perfil y el Cargo
            cargo = (Cargo) vista.getCombo_Cargo().getModel().getSelectedItem();
            perfil = (Perfil) vista.getCombo_Perfil().getModel().getSelectedItem();
            //Le asigno el perfil al usuario
            usuario.setPerfil(perfil);
            //Seteo los valores del Empleado
            empleado.setIdEmpleado(Integer.valueOf(vista.getTxt_Id().getText()));
            empleado.setApellido(vista.getTxt_Apellido().getText());
            empleado.setNombre(vista.getTxt_Nombre().getText());
            empleado.setDni(Integer.valueOf(vista.getTxt_Dni().getText()));
            empleado.setTelefono(vista.getTxt_Telefono().getText());
            //Tratamiento de la fecha de Ingreso
            String fecha = new SimpleDateFormat("yyyy/MM/dd").format(vista.getDate_Fecha().getDate());
            empleado.setFechaIngreso(fecha);
            //Fin del tratamiento
            empleado.setSexo(vista.getCombo_Sexo().getSelectedItem().toString());
            empleado.setEstado(vista.getCombo_Estado().getSelectedItem().toString());
            
            //Traigo los datos originales del usuario para sacarle el ID del Domicilio y del Usuario
            ResultSet r = empleado.buscarEmpleadoId();
            while(r.next()){
                usuario.setIdUsuario(Integer.valueOf(r.getString("idusuario")));
                domicilio.setIdDomicilio(Integer.valueOf(r.getString("iddomicilio")));
            }
            //Empiezo a Editar
            // 1) Edito el Domicilio
            domicilio.editarDomicilio();
            // 2) Edito el Usuario 
            usuario.editarUsuario();
            // 3) Seteo los objetos asociados al Empleado (Cargo)
            empleado.setCargo(cargo);
            empleado.editarEmpleado();
            JOptionPane.showMessageDialog(vista, "EMPLEADO MODIFICADO CON EXITO", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public static void EliminarEmpleado(GestionEmpleado vista) throws SQLException {
        int fila = vista.getTabla_Empleado().getSelectedRow();
        if (fila > -1) {
            int opt = JOptionPane.showConfirmDialog(vista, "Esta seguro de Borrar ?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION);
            if (opt == JOptionPane.YES_OPTION) {
                empleado = new Empleado();
                usuario = new Usuario();
                domicilio = new Domicilio();
                //Capturo el Id del empleado
                empleado.setIdEmpleado(Integer.parseInt(vista.getTabla_Empleado().getModel().getValueAt(fila, 0).toString()));
                boolean bandera = empleado.tieneVentaCompra();
                if (bandera == true) {
                    JOptionPane.showMessageDialog(vista, "NO SE PUEDE ELIMINAR EL EMPLEADO TIENE VENTAS ASOCIADAS", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    ResultSet r = empleado.buscarEmpleadoId();
                    while(r.next()){
                        usuario.setIdUsuario(Integer.valueOf(r.getString("idusuario")));
                        domicilio.setIdDomicilio(Integer.parseInt(r.getString("iddomicilio")));
                    }
                    
                    //Empiezo a Eliminar
                    // 1) Primero Elimino el Grupo Familiar
                        empleado.eliminarGrupoFamiliar();
                    // 2) Segundo Elimino el Empleado
                        empleado.eliminarEmpleado();
                    // 3) Eliminar el Usuario
                        usuario.eliminarUsuario();
                    // 4) Elimino el Domicilio
                        domicilio.eliminarDomicilio();
                        
                    ActualizarEmpleado(vista);
      
                }
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un Producto", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}

