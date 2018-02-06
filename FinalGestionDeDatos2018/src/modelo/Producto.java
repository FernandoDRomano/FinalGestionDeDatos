package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Fernando
 */
public class Producto {
    
    private int idProducto;
    private String descripcion; 
    private int stock;
    private double precioCompra;
    private double precioVenta;
    private Categoria categoria;

    public Producto(int idProducto, String descripcion, int stock, double precioCompra, double precioVenta, Categoria categoria) {
        this.idProducto = idProducto;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.categoria = categoria;
    }

    public Producto() {
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    //METODOS PARA LAS TRANSACCIONES A LA BASE DE DATOS
    
    public void grabarProducto(){
        try {
            Conexion conexion = new Conexion();
            String query = "insert into producto (descripcion, stock, precioCompra, precioVenta, categoria_idcategoria) values (?,?,?,?,?);";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.setString(1, this.getDescripcion());
            st.setInt(2, this.getStock());
            st.setDouble(3, this.getPrecioCompra());
            st.setDouble(4, this.getPrecioVenta());
            st.setInt(5, this.getCategoria().getIdCategoria());
            st.execute();
            System.out.println("SE GRABO EL PRODUCTO EN LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
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
    
    
    
    
}
