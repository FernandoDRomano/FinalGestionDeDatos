package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author fernando
 */
public class Localidad {
    
    private int idlocalidad;
    private String nombre;
    private int codigoPostal;
    private Provincia provincia;
    
    public Localidad(){
    }

    public int getIdlocalidad() {
        return idlocalidad;
    }

    public void setIdlocalidad(int idlocalidad) {
        this.idlocalidad = idlocalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
    
    public ResultSet listar(){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "select * from localidad where provincia_idprovincia = '" + this.getProvincia().getIdprovincia() + "' order by codigoPostal ASC;" ;
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
        return this.getNombre() + " (" + this.getCodigoPostal() + ")";
    }
    
}
