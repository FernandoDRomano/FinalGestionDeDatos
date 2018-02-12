package controlador; 
import java.sql.SQLException;
import vista.Login;

/**
 *
 * @author Fernando
 */
public class Sistema {
    
    public static void main(String[] args) throws SQLException{
        Login vista = new Login(null, true);
        vista.setVisible(true);
    }
}
