
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Fernando
 */
public class Categoria {
 
    private int idCategoria;
    private String nombre;

    public Categoria(int idCategoria, String nombre) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
    }

    public Categoria() {
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    //METODOS PARA LAS TRANSACCIONES A LA BASE DE DATOS
    
    public void grabarCategoria(){
        try {
            Conexion conexion = new Conexion();
            String query = "insert into categoria (nombre) values (?);";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.setString(1, this.getNombre());
            st.execute();
            System.out.println("SE GRABO LA CATEGORIA EN LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void eliminarCategoria(){
        try {
            Conexion conexion = new Conexion();
            String query = "delete from categoria where idcategoria = " + this.getIdCategoria()+ ";";
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.execute();
            System.out.println("SE ELIMINO LA CATEGORIA DE LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void editarCategoria(){
        try {
            Conexion conexion = new Conexion();
            String query = "update categoria set nombre ='"+ this.getNombre() + "' where idcategoria =" + this.getIdCategoria()+ ";";
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.executeUpdate();
            System.out.println("SE EDITO LA CATEGORIA EN LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public ResultSet listarCategoria(){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "select * from categoria;";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
    public ResultSet buscarCategoria(String cadena){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "select * from categoria where nombre like '" + cadena + "%' or idcategoria like'" + cadena + "%' ;";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }

    public boolean tieneProductos() {
        boolean bandera = false;
        ResultSet r = null;
        int resultado = 0;
        try {
            Conexion conexion = new Conexion();
            String query = "select count(*) as resultado from producto where producto.categoria_idcategoria = " + this.getIdCategoria() + " ;";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            r = st.executeQuery();
            conexion.desconectar();
            
            //VERIFICO QUE NO TENGA EMPLEADOS ASOCIADOS AL CARGO
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

    @Override
    public String toString() {
        return this.getNombre();
    }

    
    
    
}
