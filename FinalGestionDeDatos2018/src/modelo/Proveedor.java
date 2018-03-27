package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Fernando
 */
public class Proveedor {
    
    //ATRIBUTOS
    private int idProveedor;
    private String nombre;
    private String razonSocial;
    private long cuit;
    private int telefono;
    private String correo;
    private Domicilio domicilio;

    public Proveedor() {
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }    

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public long getCuit() {
        return cuit;
    }

    public void setCuit(long cuit) {
        this.cuit = cuit;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    //METODOS PARA LAS TRANSACCIONES A LA BASE DE DATOS
    
    public void grabarProveedor(){
        try {
            Conexion conexion = new Conexion();
            String query = "insert into proveedor (nombre, razonSocial, cuit, telefono, correo, domicilio_iddomicilio) values (?,?,?,?,?,?);";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.setString(1, this.getNombre());
            st.setString(2, this.getRazonSocial());
            st.setLong(3, this.getCuit());
            st.setInt(4, this.getTelefono());
            st.setString(5, this.getCorreo());
            st.setInt(6, this.getDomicilio().getIdDomicilio());
            st.execute();
            System.out.println("SE GRABO EL PROVEEDOR EN LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void eliminarProveedor(){
        try {
            Conexion conexion = new Conexion();
            String query = "delete from proveedor where idproveedor = " + this.getIdProveedor()+ ";";
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.execute();
            System.out.println("SE ELIMINO EL PROVEEDOR DE LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void editarProveedor(){
        try {
            Conexion conexion = new Conexion();
            String query = "update proveedor set nombre = '" + this.getNombre()+ "', razonSocial = '" + this.getRazonSocial()+ "', cuit = '" + this.getCuit() + "', telefono = '" + this.getTelefono() + "' , correo = '"  + this.getCorreo()+ "' where idproveedor =" + this.getIdProveedor()+ ";";
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.executeUpdate();
            System.out.println("SE EDITO EL PROVEEDOR EN LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public ResultSet listarProveedor(){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "select proveedor.idproveedor, proveedor.nombre, proveedor.cuit, proveedor.razonSocial, proveedor.telefono, proveedor.correo, concat(domicilio.calle, ' ',domicilio.numero) as domicilio\n" +
                           "from proveedor\n" +
                           "inner join domicilio on domicilio.iddomicilio = proveedor.domicilio_iddomicilio;";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    

    public ResultSet buscarProveedorId() {
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "select proveedor.idproveedor, proveedor.nombre, proveedor.cuit, proveedor.razonSocial, proveedor.telefono, proveedor.correo, domicilio.iddomicilio, domicilio.calle, domicilio.numero, domicilio.piso, domicilio.departamento\n" +
                           "from proveedor\n" +
                           "inner join domicilio on domicilio.iddomicilio = proveedor.domicilio_iddomicilio\n" +
                           "where proveedor.idproveedor = " + this.getIdProveedor() + " ;";
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
        return "Proveedor{" + "idProveedor=" + idProveedor + ", nombre=" + nombre + ", razonSocial=" + razonSocial + ", cuit=" + cuit + ", telefono=" + telefono + ", correo=" + correo + ", domicilio=" + domicilio + '}';
    }

    
    
}
