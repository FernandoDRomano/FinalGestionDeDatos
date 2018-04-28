package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Fernando
 */
public class Cliente {
     
    private int idCliente;
    private String apellido;
    private String nombre;
    private int telefono;
    private Domicilio domicilio;

    public Cliente() {
    }

    public Cliente(int idCliente, String apellido, String nombre, int telefono) {
        this.idCliente = idCliente;
        this.apellido = apellido;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }
    
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    

    
    //METODOS PARA LAS TRANSACCIONES A LA BASE DE DATOS
    
    public void grabarCliente(){
        try {
            Conexion conexion = new Conexion();
            String query = "insert into cliente (apellido, nombre, telefono, domicilio_iddomicilio) values (?,?,?,?);";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.setString(1, this.getApellido());
            st.setString(2, this.getNombre());
            st.setInt(3, this.getTelefono());
            st.setInt(4, this.getDomicilio().getIdDomicilio());
            st.execute();
            System.out.println("SE GRABO EL CLIENTE EN LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void eliminarCliente(){
        try {
            Conexion conexion = new Conexion();
            String query = "delete from cliente where idCliente = " + this.getIdCliente()+ ";";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.execute();
            System.out.println("SE ELIMINO EL CLIENTE DE LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void editarCliente(){
        try {
            Conexion conexion = new Conexion();
            String query = "update cliente set apellido ='" + this.getApellido() + "', nombre ='"+ this.getNombre() + "', telefono= '" + this.getTelefono() + "' where idCliente =" + this.getIdCliente()+ ";";
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.executeUpdate();
            System.out.println("SE EDITO EL CLIENTE EN LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public ResultSet listarCliente(){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "select cliente.idcliente, cliente.nombre, cliente.apellido, cliente.telefono, concat(domicilio.calle, ' ',domicilio.numero) as domicilio\n" +
                           "from cliente\n" +
                           "inner join domicilio on domicilio.iddomicilio = cliente.domicilio_iddomicilio;";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
    public ResultSet buscarCliente(String cadena){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "select * from cliente where apellido like '" + cadena + "%' or nombre like '" + cadena + "%' or idCliente like'" + cadena + "%' ;";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
    public ResultSet buscarClienteId(){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "select domicilio.iddomicilio, domicilio.calle, domicilio.numero, domicilio.piso, domicilio.departamento, \n" +
                            "provincia.idprovincia, provincia.nombre as provincia, \n" +
                            "localidad.idlocalidad, localidad.nombre as localidad, localidad.codigoPostal,\n" +
                            "cliente.idcliente, cliente.nombre as clienteNombre, cliente.apellido as clienteApellido, cliente.telefono \n" +
                            "from cliente\n" +
                            "inner join domicilio on domicilio.iddomicilio = cliente.domicilio_iddomicilio\n" +
                            "inner join localidad on localidad.idlocalidad = domicilio.localidad_idlocalidad\n" +
                            "inner join provincia on provincia.idprovincia = localidad.provincia_idprovincia\n" +
                            "where cliente.idcliente = " + this.getIdCliente()+ " ;";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
    public boolean tieneVenta() {
        boolean bandera = false;
        ResultSet r = null;
        int resultado = 0;
        try {
            Conexion conexion = new Conexion();
            String query = "select count(*) as resultado from venta where venta.cliente_idcliente  =  " + this.getIdCliente()+ " ;";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            r = st.executeQuery();
            conexion.desconectar();
            
            //VERIFICO QUE NO TENGA VENTAS ASOCIADAS AL CLIENTE
            while (r.next()) {                
                resultado = Integer.valueOf(r.getString("resultado"));
            }
            
            if (resultado > 0) {
                bandera = true;
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return bandera;
    }
}
