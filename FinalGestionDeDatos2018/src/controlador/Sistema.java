package controlador; 
import java.sql.SQLException;
import vista.GestionCliente;

/**
 *
 * @author Fernando
 */
public class Sistema {
    
    public static void main(String[] args) throws SQLException{
        
        GestionCliente vista = new GestionCliente(null, true);
        vista.setVisible(true);
    }
}
