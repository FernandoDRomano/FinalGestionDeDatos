package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author fernando
 */
public class Provincia {
    
    private int idprovincia;
    private String nombre;

    public Provincia() {
    }

    public int getIdprovincia() {
        return idprovincia;
    }

    public void setIdprovincia(int idprovincia) {
        this.idprovincia = idprovincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public ResultSet listar(){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "select * from provincia order by nombre ASC;";
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
        return this.getNombre();
    }
    
    
    
}
