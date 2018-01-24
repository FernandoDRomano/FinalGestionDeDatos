
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Fernando
 */
public class Conexion {
    
    /**Parametros de conexion*/
   static String bd = "sg_bar";
   static String login = "root";
   static String password = "";
   static String url = "jdbc:mysql://localhost/"+bd;
 
   /* 
   
   Esta es la configuracion para conectarse a una base de datos creada en la pagina http://www.freesqldatabase.com 
   con mi cuenta de correo electronico
   
   static String bd = "sql10195055";
   static String login = "sql10195055";
   static String password = "dtptItA9yY";
   static String url = "jdbc:mysql://sql10.freesqldatabase.com/"+bd; 
   
   */
    
   Connection connection = null;
 
   /** Constructor de DbConnection */
   public Conexion() {
      try{
         //obtenemos el driver de para mysql
         Class.forName("com.mysql.jdbc.Driver");
         //obtenemos la conexión
         connection = DriverManager.getConnection(url,login,password);
 
         if (connection!=null){
            System.out.println("Conexión a base de datos "+bd+" OK\n");
         }
      }
      catch(SQLException e){
         System.out.println(e);
      }catch(ClassNotFoundException e){
         System.out.println(e);
      }catch(Exception e){
         System.out.println(e);
      }
   }
   /**Permite retornar la conexión*/
   public Connection getConnection(){
      return connection;
   }
 
   public void desconectar(){
      connection = null;
   }
    
}
