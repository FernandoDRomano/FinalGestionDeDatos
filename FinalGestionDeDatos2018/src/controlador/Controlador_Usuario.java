
package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Empleado;
import modelo.Perfil;
import modelo.Usuario;
import org.apache.commons.codec.digest.DigestUtils;
import vista.CambiarContraseña;
import vista.Login;
import vista.Principal;

/**
 *
 * @author Fernando
 */
public class Controlador_Usuario {
    
    private static Usuario usuario;
    private static Empleado empleado;
    private static Perfil perfil;
    private static Principal principal;
    
    public static void Login(Login vista) throws SQLException{
        perfil = new Perfil();
        usuario = new Usuario();
        empleado = new Empleado();
        //Seteo los valores del usuario
        usuario.setNombreUsuario(vista.getTxt_Usuario().getText());
        //capturo la contraseña para condificarla y luego setearla al usuario
        String valorPass = new String(vista.getTxt_Clave().getPassword());
        /*
            ENCRIPTAR CON SHA1
            Aquí estamos haciendo uso de la clase DigestUtils y su método sha1Hex(), 
            el cuál recibe un parámetro que es la cadena a encriptar y retorna una cadena que ya está encriptada.
        */
        String textoEncriptadoConSHA=DigestUtils.sha1Hex(valorPass); //SE ENCRIPTA LA CONTRASEÑA
        System.out.println("Texto Encriptado con SHA : "+textoEncriptadoConSHA);
        usuario.setClave(textoEncriptadoConSHA); //SE ASIGNA AL USUARIO
        
        //Consulto en la BD
        ResultSet r = usuario.login();
        boolean bandera = false;
        while(r.next()){
            bandera = true;
            usuario.setIdUsuario(Integer.valueOf(r.getString("usuario.idusuario")));
            perfil.setNombre(r.getString("perfil.nombre"));
            empleado.setApellido(r.getString("empleado.apellido"));
            empleado.setNombre(r.getString("empleado.nombre"));
            empleado.setIdEmpleado(Integer.valueOf(r.getString("empleado.idempleado")));
            System.out.println(empleado);
            
        }
        
        if (bandera == true) {
            vista.dispose();
            principal = new Principal();
            //Seteo los valores del empleado, seran importantes al pasarlo a las otras ventanas
            principal.getEmpleado().setIdEmpleado(empleado.getIdEmpleado());
            principal.getEmpleado().setNombre(empleado.getNombre());
            principal.getEmpleado().setApellido(empleado.getApellido());
            //Seteo el Label del Form Principal
            principal.getLabel_Empleado().setText(empleado.getApellido() + ", " + empleado.getNombre());
            if (perfil.getNombre().equals("Administrador")) {
                principal.setVisible(true);
                JOptionPane.showMessageDialog(null, "Bienvenido " + empleado.getApellido() + ", " + empleado.getNombre(), "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
            }else{                
                principal.getMenu_LiquidacionSueldo().setVisible(false);
                principal.getMenu_Reporte().setVisible(false);
                //Oculto las Opciones del Menu de Gestion
                principal.getLabelCategoria().setVisible(false);
                principal.getLabelCargo().setVisible(false);
                principal.getLabelConceptos().setVisible(false);
                principal.getLabelEmpleado().setVisible(false);
                principal.getLabelFamilia().setVisible(false);
                principal.getLabelProveedor().setVisible(false);
                principal.getLabelProducto().setVisible(false);
                principal.getLabelListarVentas().setVisible(false);
                principal.getLabelListarVentasxEmpleado().setVisible(false);
                principal.getMenu_Venta().setCollapsed(false);
                principal.getMenu_Compra().setVisible(false);
                principal.setVisible(true);
                JOptionPane.showMessageDialog(null, "Bienvenido " + empleado.getApellido() + ", " + empleado.getNombre(), "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
            }
            
            principal.getUsuario().setIdUsuario(usuario.getIdUsuario());
            principal.getUsuario().setClave(usuario.getClave());
            
            
        }else{
            vista.getTxt_Usuario().setText("");
            vista.getTxt_Clave().setText("");
            JOptionPane.showMessageDialog(vista, "ERROR: USUARIO O CONTRASEÑA INCORRECTA", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            vista.getTxt_Usuario().requestFocus();
        }
    }
    
    public static void cambiarCotraseña(CambiarContraseña vista){
        usuario = new Usuario();
        
        String actual = new String(vista.getTxt_Actual().getPassword());
        String nueva = new String(vista.getTxt_Nueva().getPassword());
        String repetir = new String(vista.getTxt_Repetir().getPassword());
        
        /*
            ENCRIPTAR CON SHA1
            Aquí estamos haciendo uso de la clase DigestUtils y su método sha1Hex(), 
            el cuál recibe un parámetro que es la cadena a encriptar y retorna una cadena que ya está encriptada.
        */
        String actualEncriptado=DigestUtils.sha1Hex(actual); //SE ENCRIPTA LA CONTRASEÑA
        System.out.println("CONTRASEÑA DEL USUARIO LOGUEADO: "+vista.getUsuario().getClave());
        System.out.println("CONTRASEÑA ACTUAL ENCRIPTADA : "+actualEncriptado);
        
        //COMPARO SI LA CONTRASEÑA ACTUAL ES IGUAL A LA DEL USUARIO LOGUEADO
        if (vista.getUsuario().getClave().equals(actualEncriptado)) {
            System.out.println("LA CLAVE DEL USUARIO LOGUEADO CON LA INGRESADA ES IGUAL");
            if (nueva.equals(repetir)) {
                usuario.setIdUsuario(vista.getUsuario().getIdUsuario());
                
                /*
                    ENCRIPTAR CON SHA1
                    Aquí estamos haciendo uso de la clase DigestUtils y su método sha1Hex(), 
                    el cuál recibe un parámetro que es la cadena a encriptar y retorna una cadena que ya está encriptada.
                */
                String textoEncriptadoConSHA=DigestUtils.sha1Hex(nueva); //SE ENCRIPTA LA CONTRASEÑA
                System.out.println("Texto Encriptado con SHA : "+textoEncriptadoConSHA);
                usuario.setClave(textoEncriptadoConSHA); //SE ASIGNA AL USUARIO
                
                usuario.cambiarContraseña();
                JOptionPane.showMessageDialog(null, "CONTRASEÑA ACTUALIZADA", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
                vista.dispose();
            }else{
                vista.getTxt_Actual().setText("");
                vista.getTxt_Nueva().setText("");
                vista.getTxt_Repetir().setText("");
                JOptionPane.showMessageDialog(vista, "ERROR: CONTRASEÑA NUEVA INCORRECTA", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                }
        }else{
                vista.getTxt_Actual().setText("");
                vista.getTxt_Nueva().setText("");
                vista.getTxt_Repetir().setText("");
                JOptionPane.showMessageDialog(vista, "ERROR: CONTRASEÑA ACTUAL NO COINCIDE", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
