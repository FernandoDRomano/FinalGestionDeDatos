package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Fernando
 */
public class Domicilio {
    
    private int idDomicilio;
    private String calle;
    private String numero;
    private String piso;
    private String departamento;

    public Domicilio(int idDomicilio, String calle, String numero, String piso, String departamento) {
        this.idDomicilio = idDomicilio;
        this.calle = calle;
        this.numero = numero;
        this.piso = piso;
        this.departamento = departamento;
    }

    public Domicilio() {
    }

    public int getIdDomicilio() {
        return idDomicilio;
    }

    public void setIdDomicilio(int idDomicilio) {
        this.idDomicilio = idDomicilio;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Domicilio{" + "idDomicilio=" + idDomicilio + ", calle=" + calle + ", numero=" + numero + ", piso=" + piso + ", departamento=" + departamento + '}';
    }
    
    
    
    //METODOS PARA LAS TRANSACCIONES A LA BASE DE DATOS
    
    public void grabarDomicilio(){
        try {
            Conexion conexion = new Conexion();
            String query = "insert into domicilio (calle, numero, piso, departamento) values (?,?,?,?);";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.setString(1, this.getCalle());
            st.setString(2, this.getNumero());
            st.setString(3, this.getPiso());
            st.setString(4, this.getDepartamento());
            st.execute();
            System.out.println("SE GRABO LA DIRECCIÓN EN LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public String ultimoDomicilio(){
        ResultSet resultado = null;
        String id = "";
         try {
            Conexion conexion = new Conexion();
            String query = "SELECT MAX(iddomicilio) AS id FROM domicilio";
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
    
    public void eliminarDomicilio(){
        try {
            Conexion conexion = new Conexion();
            String query = "delete from domicilio where iddomicilio = " + this.getIdDomicilio()+ ";";
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.execute();
            System.out.println("SE ELIMINO EL DOMICILIO DE LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void editarDomicilio(){
        try {
            Conexion conexion = new Conexion();
            String query = "update domicilio set calle ='"+ this.getCalle()+ "', numero =' " + this.getNumero() + "', piso =' " + this.getPiso() + "', departamento = ' " + this.getDepartamento() + "' where iddomicilio =" + this.getIdDomicilio()+ ";";
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.executeUpdate();
            System.out.println("SE EDITO EL DOMICILIO EN LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /*
    public void eliminarProducto(){
        try {
            Conexion conexion = new Conexion();
            String query = "delete from producto where idproducto = " + this.getIdProducto()+ ";";
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.execute();
            System.out.println("SE ELIMINO EL PRODUCTO DE LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void editarProducto(){
        try {
            Conexion conexion = new Conexion();
            String query = "update producto set descripcion ='"+ this.getDescripcion()+ "', precioCompra =' " + this.getPrecioCompra() + "', precioVenta =' " + this.getPrecioVenta() + "', categoria_idcategoria = ' " + this.getCategoria().getIdCategoria() + "' where idproducto =" + this.getIdProducto()+ ";";
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.executeUpdate();
            System.out.println("SE EDITO EL PRODUCTO EN LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
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
    
}
