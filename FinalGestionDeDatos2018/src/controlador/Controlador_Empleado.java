
package controlador;

import java.awt.Frame;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import modelo.Cargo;
import modelo.Conexion;
import modelo.Domicilio;
import modelo.Empleado;
import modelo.Perfil;
import modelo.Usuario;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.commons.codec.digest.DigestUtils;
import vista.BuscarEmpleado;
import vista.BuscarEmpleadoLiquidacion;
import vista.EditarEmpleado;
import vista.GestionEmpleado;
import vista.GestionFamilia;
import vista.NuevoEmpleado;
import vista.Principal;

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
    
    public static void LogicaBotones(GestionEmpleado vista){
        vista.getBtn_Agregar().setEnabled(true);
        vista.getBtn_Editar().setEnabled(false);
        vista.getBtn_Eliminar().setEnabled(false);
        vista.getBtn_GrupoFamiliar().setEnabled(false);
    }
    
    public static void LogicaBotonesInvertir(GestionEmpleado vista){
        vista.getBtn_Agregar().setEnabled(false);
        vista.getBtn_Editar().setEnabled(true);
        vista.getBtn_Eliminar().setEnabled(true);
        vista.getBtn_GrupoFamiliar().setEnabled(true);
    }
    
    public static void ActualizarEmpleado(GestionEmpleado vista) throws SQLException {
        empleado = new Empleado();
        vista.getModeloTabla().setColumnCount(0);
        vista.getModeloTabla().setNumRows(0);
        vista.getModeloTabla().addColumn("Id");
        vista.getModeloTabla().addColumn("Apellido");
        vista.getModeloTabla().addColumn("Nombre");
        vista.getModeloTabla().addColumn("DNI");
        vista.getModeloTabla().addColumn("Fecha de Ingreso");
        vista.getModeloTabla().addColumn("Domicilio");
        vista.getModeloTabla().addColumn("Usuario");
        vista.getModeloTabla().addColumn("Cargo");
        vista.getModeloTabla().addColumn("Perfíl");
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
        LogicaBotones(vista);
    }
    
    public static void CargarPerfil(NuevoEmpleado vista) throws SQLException{
        perfil = new Perfil();
        ResultSet r = perfil.listar();
        vista.getModeloComboPerfil().addElement("Seleccione una Opción");
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
        vista.getModeloComboCargo().addElement("Seleccione una Opción");
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
        vista.getModeloComboPerfil().addElement("Seleccione una Opción");
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
        vista.getModeloComboCargo().addElement("Seleccione una Opción");
        while(r.next()){
            cargo = new Cargo();
            cargo.setIdCargo(Integer.valueOf(r.getString("idcargo")));
            cargo.setCargo(r.getString("cargo"));
            vista.getModeloComboCargo().addElement(cargo);
        }
        vista.getCombo_Cargo().setModel(vista.getModeloComboCargo());
    }
    
    public static boolean ValidarCampos(NuevoEmpleado vista){
        boolean bandera = true;
        //VALIDO QUE LOS CAMPOS NO SEAN VACIOS
        String apellido = vista.getTxt_Apellido().getText();
        apellido = apellido.replaceAll(" ", ""); //REEMPLAZO LOS ESPACIO VACIO
        String nombre = vista.getTxt_Nombre().getText();
        nombre = nombre.replaceAll(" ", ""); //REEMPLAZO LOS ESPACIO VACIO
        String dni = vista.getTxt_Dni().getText();
        dni = dni.replaceAll(" ", "");
        String telefono = vista.getTxt_Telefono().getText();
        telefono = telefono.replaceAll(" ", "");
        String estado = vista.getCombo_Estado().getSelectedItem().toString();
        String sexo = vista.getCombo_Sexo().getSelectedItem().toString();
        Date fecha = vista.getDate_Fecha().getDate();
        String calle = vista.getTxt_Calle().getText();
        calle = calle.replace(" ", "");
        String numero = vista.getTxt_Numero().getText();
        numero = numero.replace(" ", "");
        String cargo = vista.getCombo_Cargo().getSelectedItem().toString();
        String usuario = vista.getTxt_Usuario().getText();
        usuario = usuario.replaceAll(" ", "");
        usuario = usuario.replaceAll(" ", "");
        String perfil = vista.getCombo_Perfil().getSelectedItem().toString();
            
        if (estado.equals("Seleccione una Opción")) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UNA OPCIÓN EN EL CAMPO ESTADO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
                    
        if (apellido.length() == 0) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE COMPLETAR El APELLIDO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        if (nombre.length() == 0) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE COMPLETAR El NOMBRE", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        if (dni.length() == 0) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE COMPLETAR El DNI", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        if (telefono.length() == 0) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE COMPLETAR El TELEFONO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        if (fecha ==  null) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE INGRESAR LA FECHA DE INGRESO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        if (sexo.equals("Seleccione una Opción")) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UN SEXO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
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
        
        if (cargo.equals("Seleccione una Opción")) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UN CARGO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        if (numero.length() == 0) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE COMPLETAR EL CAMPO USUARIO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        if (perfil.equals("Seleccione una Opción")) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UN PERFÍL", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        return bandera;
    }
    
    public static boolean ValidarCampos(EditarEmpleado vista){
        boolean bandera = true;
        //VALIDO QUE LOS CAMPOS NO SEAN VACIOS
        String apellido = vista.getTxt_Apellido().getText();
        apellido = apellido.replaceAll(" ", ""); //REEMPLAZO LOS ESPACIO VACIO
        String nombre = vista.getTxt_Nombre().getText();
        nombre = nombre.replaceAll(" ", ""); //REEMPLAZO LOS ESPACIO VACIO
        String dni = vista.getTxt_Dni().getText();
        dni = dni.replaceAll(" ", "");
        String telefono = vista.getTxt_Telefono().getText();
        telefono = telefono.replaceAll(" ", "");
        String estado = vista.getCombo_Estado().getSelectedItem().toString();
        String sexo = vista.getCombo_Sexo().getSelectedItem().toString();
        Date fecha = vista.getDate_Fecha().getDate();
        String calle = vista.getTxt_Calle().getText();
        calle = calle.replace(" ", "");
        String numero = vista.getTxt_Numero().getText();
        numero = numero.replace(" ", "");
        String cargo = vista.getCombo_Cargo().getSelectedItem().toString();
        String usuario = vista.getTxt_Usuario().getText();
        usuario = usuario.replaceAll(" ", "");
        usuario = usuario.replaceAll(" ", "");
        String perfil = vista.getCombo_Perfil().getSelectedItem().toString();
            
        if (estado.equals("Seleccione una Opción")) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UNA OPCIÓN EN EL CAMPO ESTADO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
                    
        if (apellido.length() == 0) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE COMPLETAR El APELLIDO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        if (nombre.length() == 0) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE COMPLETAR El NOMBRE", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        if (dni.length() == 0) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE COMPLETAR El DNI", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        if (telefono.length() == 0) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE COMPLETAR El TELEFONO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        if (fecha ==  null) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE INGRESAR LA FECHA DE INGRESO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        if (sexo.equals("Seleccione una Opción")) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UN SEXO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
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
        
        if (cargo.equals("Seleccione una Opción")) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UN CARGO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        if (numero.length() == 0) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE COMPLETAR EL CAMPO USUARIO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        if (perfil.equals("Seleccione una Opción")) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UN PERFÍL", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        return bandera;
    }
    
    public static void AltasEmpleado(NuevoEmpleado vista) throws SQLException {
        if (ValidarCampos(vista)) {
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
            empleado.setTelefono(Integer.valueOf(vista.getTxt_Telefono().getText()));
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
            JOptionPane.showMessageDialog(vista, "EMPLEADO GRABADO CON ÉXITO", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
            vista.dispose();
            //CARGO LA VENTANA GESTION DE EMPLEADOS
            GestionEmpleado gestion = new GestionEmpleado(null, true);
            gestion.setVisible(true);
        }
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
            empleado.setTelefono(Integer.valueOf(r.getString("telefono")));
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
        vista.getTxt_Telefono().setText(String.valueOf(empleado.getTelefono()));
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
        int opt = JOptionPane.showConfirmDialog(vista, "¿ESTÁ USTED SEGURO DE MODIFICAR ESTE EMPLEADO?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (opt == JOptionPane.YES_OPTION) {
            if (ValidarCampos(vista)) {
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
                //Ya no se puede modificar la contraseña desde aqui!! 
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
                empleado.setTelefono(Integer.valueOf(vista.getTxt_Telefono().getText()));
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
                JOptionPane.showMessageDialog(vista, "EMPLEADO MODIFICADO CON ÉXITO", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
                vista.dispose();
                //CARGO LA VENTANA GESTION DE EMPLEADOS
                GestionEmpleado gestion = new GestionEmpleado(null, true);
                gestion.setVisible(true);
            }
            
        }
    }
    
    public static void EliminarEmpleado(GestionEmpleado vista) throws SQLException {
        int fila = vista.getTabla_Empleado().getSelectedRow();
        if (fila > -1) {
            int opt = JOptionPane.showConfirmDialog(vista, "¿ESTÁ USTED SEGURO DE ELIMINAR ESTE EMPLEADO?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (opt == JOptionPane.YES_OPTION) {
                empleado = new Empleado();
                usuario = new Usuario();
                domicilio = new Domicilio();
                //Capturo el Id del empleado
                empleado.setIdEmpleado(Integer.parseInt(vista.getTabla_Empleado().getModel().getValueAt(fila, 0).toString()));
                boolean bandera = empleado.tieneVentaCompraLiquidacion();
                System.out.println("VALOR DE LA BANDERA: " + bandera);
                if (bandera == true) {
                    JOptionPane.showMessageDialog(vista, "NO SE PUEDE ELIMINAR ÉSTE EMPLEADO,\n TIENE PEDIDOS-VENTAS-LIQUIDACIONES ASOCIADAS", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                    //SI NO SE PUEDE ELIMINAR, VALIDO SI NO SE LO PUEDE PONER EN ESTADO INACTIVO
                    int opt1 = JOptionPane.showConfirmDialog(vista, "¿DESEA PONER AL EMPLEADO EN ESTADO INACTIVO?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                    if (opt1 == JOptionPane.YES_OPTION) {
                        empleado.borradoLogicoEmpleado();
                        JOptionPane.showMessageDialog(vista, "SE CAMBIÓ EL ESTADO DEL EMPLEADO", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
                        ActualizarEmpleado(vista);
                    }
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
                    JOptionPane.showMessageDialog(vista, "EMPLEADO ELIMINADO CON ÉXITO", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
                    ActualizarEmpleado(vista);
                }
            }
        } else {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UN EMPLEADO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //METODO QUE SE UTILIZARA EN EL GRUPO FAMILIAR PARA BUSCAR EL EMPLEADO 
    
    public static void ventanaBuscarEmpleado(GestionFamilia vista) throws SQLException{
        BuscarEmpleado ventana = new BuscarEmpleado(null, true, vista);
        ventana.setVisible(true);
    }
    
    public static void CargarTodosLosEmpleados(BuscarEmpleado vista) throws SQLException {
        empleado = new Empleado();
        vista.getModelo().setColumnCount(0);
        vista.getModelo().setNumRows(0);
        vista.getModelo().addColumn("Id");
        vista.getModelo().addColumn("Apellido");
        vista.getModelo().addColumn("Nombre");
        vista.getModelo().addColumn("DNI");
        ResultSet r = empleado.listarEmpleadoActivo();
        while (r.next()) {
            Object[] fila = new Object[4];
            fila[0] = r.getString("idempleado");
            fila[1] = r.getString("apellido");
            fila[2] = r.getString("nombre");
            fila[3] = r.getString("dni");
            vista.getModelo().addRow(fila);
        }

        vista.getTabla_Empleado().setModel(vista.getModelo());
        
        //declaramos un arreglo de enteros con los anchos que deseamos
        //para nuestra tabla
        int[] anchos = {30, 200, 200, 80};
        //hacemos un bucle FOR desde cero hasta la cantidad de columnas
        //de nuestra tabla
        for(int i = 0; i < vista.getTabla_Empleado().getColumnCount(); i++) {
        //Sacamos el modelo de columnas de nuestra tabla
        //luego obtenemos la columna en la posicion "i"
        //invocamos el metodo setPreferrefWidth para ajustar el ancho
        //y le damos el valor del entero que esta en el arreglo en la posicion "i"
        vista.getTabla_Empleado().getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        
    }
    
    public static void CargarTodosLosEmpleados(BuscarEmpleado vista, String cadena) throws SQLException {
        empleado = new Empleado();
        vista.getModelo().setColumnCount(0);
        vista.getModelo().setNumRows(0);
        vista.getModelo().addColumn("Id");
        vista.getModelo().addColumn("Apellido");
        vista.getModelo().addColumn("Nombre");
        vista.getModelo().addColumn("DNI");
        ResultSet r = empleado.listarEmpleadoActivo(cadena);
        while (r.next()) {
            Object[] fila = new Object[4];
            fila[0] = r.getString("idempleado");
            fila[1] = r.getString("apellido");
            fila[2] = r.getString("nombre");
            fila[3] = r.getString("dni");
            vista.getModelo().addRow(fila);
        }

        vista.getTabla_Empleado().setModel(vista.getModelo());
        
        //declaramos un arreglo de enteros con los anchos que deseamos
        //para nuestra tabla
        int[] anchos = {30, 200, 200, 80};
        //hacemos un bucle FOR desde cero hasta la cantidad de columnas
        //de nuestra tabla
        for(int i = 0; i < vista.getTabla_Empleado().getColumnCount(); i++) {
        //Sacamos el modelo de columnas de nuestra tabla
        //luego obtenemos la columna en la posicion "i"
        //invocamos el metodo setPreferrefWidth para ajustar el ancho
        //y le damos el valor del entero que esta en el arreglo en la posicion "i"
        vista.getTabla_Empleado().getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        
    }

    public static void CargarTodosLosEmpleados(BuscarEmpleadoLiquidacion vista, String cadena) throws SQLException {
        empleado = new Empleado();
        vista.getModelo().setColumnCount(0);
        vista.getModelo().setNumRows(0);
        vista.getModelo().addColumn("Id");
        vista.getModelo().addColumn("Apellido");
        vista.getModelo().addColumn("Nombre");
        vista.getModelo().addColumn("DNI");
        ResultSet r = empleado.listarEmpleadoActivo(cadena);
        while (r.next()) {
            Object[] fila = new Object[4];
            fila[0] = r.getString("idempleado");
            fila[1] = r.getString("apellido");
            fila[2] = r.getString("nombre");
            fila[3] = r.getString("dni");
            vista.getModelo().addRow(fila);
        }

        vista.getTabla_Empleado().setModel(vista.getModelo());
        
        //declaramos un arreglo de enteros con los anchos que deseamos
        //para nuestra tabla
        int[] anchos = {30, 200, 200, 80};
        //hacemos un bucle FOR desde cero hasta la cantidad de columnas
        //de nuestra tabla
        for(int i = 0; i < vista.getTabla_Empleado().getColumnCount(); i++) {
        //Sacamos el modelo de columnas de nuestra tabla
        //luego obtenemos la columna en la posicion "i"
        //invocamos el metodo setPreferrefWidth para ajustar el ancho
        //y le damos el valor del entero que esta en el arreglo en la posicion "i"
        vista.getTabla_Empleado().getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        
    }

    
    public static void agregarEmpleadoAlGrupoFamiliar(BuscarEmpleado vista, GestionFamilia gestion) throws SQLException{
        int fila = vista.getTabla_Empleado().getSelectedRow();
        if (fila > -1) {
            //saco los valores
            String id = vista.getTabla_Empleado().getModel().getValueAt(fila, 0).toString();
            String apellido = vista.getTabla_Empleado().getModel().getValueAt(fila, 1).toString();
            String nombre = vista.getTabla_Empleado().getModel().getValueAt(fila, 2).toString();
            String dni = vista.getTabla_Empleado().getModel().getValueAt(fila, 3).toString();
 
            //Los cargo en la vista
            gestion.getTxt_Id().setText(id);
            gestion.getTxt_Apellido().setText(apellido);
            gestion.getTxt_Nombre().setText(nombre);
            gestion.getTxt_Dni().setText(dni);
            //UNA VES CARGADO EL EMPLEADO EN EL FORM DE LA GESTION DE FAMILIA, ACTUALIZO SUS FAMILIARES EN LA TABLA
            Controlador_Familia.ActualizarFamilia(gestion);
            vista.setVisible(false);
        }else{
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UN EMPLEADO", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    
    public static void BuscarEmpleado(GestionEmpleado vista, String cadena) throws SQLException{
        empleado = new Empleado();
        vista.getModeloTabla().setColumnCount(0);
        vista.getModeloTabla().setNumRows(0);
        vista.getModeloTabla().addColumn("Id");
        vista.getModeloTabla().addColumn("Apellido");
        vista.getModeloTabla().addColumn("Nombre");
        vista.getModeloTabla().addColumn("DNI");
        vista.getModeloTabla().addColumn("Fecha de Ingreso");
        vista.getModeloTabla().addColumn("Domicilio");
        vista.getModeloTabla().addColumn("Usuario");
        vista.getModeloTabla().addColumn("Cargo");
        vista.getModeloTabla().addColumn("Perfíl");
        vista.getModeloTabla().addColumn("Estado");
        ResultSet r = empleado.buscarEmpleado(cadena);
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
        LogicaBotones(vista);
    }
    
    public static void imprimirEmpleados(Principal vista) throws SQLException, ClassNotFoundException{
        int opt = JOptionPane.showConfirmDialog(vista, "¿DESEA IMPRIMIR EL REPORTE DE EMPLEADOS?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opt == JOptionPane.YES_OPTION) {
            Conexion conexion = new Conexion();
        
        //Metodo para Exportar
            try {
                JOptionPane.showMessageDialog(null, "ESTO PUEDE TARDAR UNOS SEGUNDOS,\nESPERE POR FAVOR", "ESTAMOS GENERANDO EL REPORTE", JOptionPane.WARNING_MESSAGE);
                String rutaInforme = "/home/fernando/NetBeansProjects/FinalGestionDeDatos/FinalGestionDeDatos2018/src/reportes/Empleados.jasper";
                JasperPrint informe = JasperFillManager.fillReport(rutaInforme, null, conexion.getConnection());
                JasperViewer ventanavisor = new JasperViewer(informe, false);
                JOptionPane.showMessageDialog(null, "DOCUMENTO EXPORTADO EXITOSAMENTE!", "GUARDADO EXITOSO!", JOptionPane.INFORMATION_MESSAGE);
                ventanavisor.setTitle("EMPLEADOS");
                ventanavisor.setExtendedState(Frame.MAXIMIZED_BOTH);
                ventanavisor.setVisible(true);
            } catch (Exception e) {//Catch del metodo exportar
                JOptionPane.showMessageDialog(null, "ERROR AL EXPORTAR DOCUMENTO");
            }//Fin de del metodo Exportar      
        }
    }

    
}

