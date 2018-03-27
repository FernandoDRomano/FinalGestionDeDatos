package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Fernando
 */
public class Usuario {
   
   private int idUsuario;
   private String nombreUsuario;
   private String clave;
   private Perfil perfil;

    public Usuario(int idUsuario, String nombreUsuario, String clave, Perfil perfil) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.clave = clave;
        this.perfil = perfil;
    }

    public Usuario() {
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nombreUsuario=" + nombreUsuario + ", clave=" + clave + ", perfil=" + perfil + '}';
    }
    
   
    //METODOS PARA LAS TRANSACCIONES A LA BASE DE DATOS
    
    public void grabarUsuario(){
        try {
            Conexion conexion = new Conexion();
            String query = "insert into usuario (nombreUsuario, clave, perfil_idperfil) values (?,?,?);";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.setString(1, this.getNombreUsuario());
            st.setString(2, this.getClave());
            st.setInt(3, this.getPerfil().getIdPerfil());
            st.execute();
            System.out.println("SE GRABO EL USUARIO EN LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public String ultimoUsuario(){
        ResultSet resultado = null;
        String id = "";
         try {
            Conexion conexion = new Conexion();
            String query = "SELECT MAX(idusuario) AS id FROM usuario";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            while(resultado.next()){
                id = resultado.getString("id");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return id;
    }
    
    public void eliminarUsuario(){
        try {
            Conexion conexion = new Conexion();
            String query = "delete from usuario where idusuario = " + this.getIdUsuario()+ ";";
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.execute();
            System.out.println("SE ELIMINO EL USUARIO DE LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void editarUsuario(){
        try {
            Conexion conexion = new Conexion();
            String query = "update usuario set nombreUsuario ='"+ this.getNombreUsuario()+ "', perfil_idperfil = '" + this.getPerfil().getIdPerfil() + "' where idusuario =" + this.getIdUsuario()+ " ;";
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.executeUpdate();
            System.out.println("SE EDITO EL USUARIO EN LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /*
    
    
    
    
    public ResultSet listarProducto(){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "SELECT idproducto, descripcion, stock, precioCompra, precioVenta, categoria.idcategoria, categoria.nombre as categoria\n" +
                           "FROM producto\n" +
                           "INNER JOIN categoria ON producto.categoria_idcategoria = categoria.idcategoria;";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
    public ResultSet buscarProducto(String cadena){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "select * from producto where descripcion like '" + cadena + "%' or idproducto like'" + cadena + "%' ;";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }

    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", descripcion=" + descripcion + ", stock=" + stock + ", precioCompra=" + precioCompra + ", precioVenta=" + precioVenta + ", categoria=" + categoria + '}';
    }

    public boolean tieneCompraVenta() {
        return false;
    }

    public ResultSet buscarProductoId(String cadena) {
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "SELECT idproducto, descripcion, stock, precioCompra, precioVenta, categoria.idcategoria, categoria.nombre as categoria\n" +
                           "FROM producto\n" +
                           "INNER JOIN categoria ON producto.categoria_idcategoria = categoria.idcategoria " +
                           "WHERE idproducto = " + cadena;
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }

    */

    public ResultSet login() {
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "SELECT usuario.idusuario, usuario.nombreUsuario, perfil.nombre, empleado.apellido, empleado.nombre, empleado.idempleado, empleado.estado\n" +
                           "FROM usuario\n" +
                           "INNER JOIN perfil ON perfil.idperfil = usuario.perfil_idperfil\n" +
                           "INNER JOIN empleado ON empleado.usuario_idusuario = usuario.idusuario\n" +
                           "WHERE usuario.nombreUsuario = '" + this.getNombreUsuario() + "' AND usuario.clave = '" + this.getClave()+"' AND empleado.estado = 'Activo';";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            System.out.println(query);
            resultado = st.executeQuery();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return resultado;
    }
    
    public void cambiarContraseña(){
        try {
            Conexion conexion = new Conexion();
            String query = "update usuario set clave = '" + this.getClave() + "' where idusuario =" + this.getIdUsuario()+ ";";
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.executeUpdate();
            System.out.println("SE EDITO LA CONTRASEÑA DEL USUARIO EN LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
   
}
