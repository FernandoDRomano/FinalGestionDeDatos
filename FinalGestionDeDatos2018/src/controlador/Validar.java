package controlador;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author fernando
 */
public class Validar {
    
    public static void validarSoloLetras(JTextField campo, int limite){
        
        campo.addKeyListener(new KeyListener(){
 
            @Override
            public void keyTyped(KeyEvent e){
                //SI EL LIMITE ES 0 NO SE TENDRA EN CUENTA LA CANTIDAD
                //SIRVE PARA INDICAR QUE NO IMPORTA LA CANTIDAD
                if (limite > 0) {
                    //SI EL LIMITE ES MAYOR A 0, ENTONCES VALIDO LA CANTIDAD
                    if (campo.getText().length()== limite){
                        e.consume();
                        JOptionPane.showMessageDialog(null, "ERROR: MÁXIMO " + limite + " LETRAS", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                
                //SI SE INGRESA UN DIGITO NO SE TENDRA EN CUENTA
                if (Character.isDigit(e.getKeyChar()) && (e.getKeyChar() != (char)KeyEvent.VK_BACK_SPACE)) {  
                    e.consume();
                    JOptionPane.showMessageDialog(null, "ERROR: SOLO SE PERMITEN LETRAS", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            public void keyPressed(KeyEvent arg0) {
            }

            public void keyReleased(KeyEvent arg0) {
            }
        
        });
        
    }
    
    
    public static void validarSoloNumeros(JTextField campo, int limite){
        
        campo.addKeyListener(new KeyListener(){
 
            @Override
            public void keyTyped(KeyEvent e){
                //SI EL LIMITE ES 0 NO SE TENDRA EN CUENTA LA CANTIDAD
                //SIRVE PARA INDICAR QUE NO IMPORTA LA CANTIDAD
                if (limite > 0) {
                    //SI EL LIMITE ES MAYOR A 0, ENTONCES VALIDO LA CANTIDAD
                    if (campo.getText().length()== limite){
                        e.consume();
                        JOptionPane.showMessageDialog(null, "ERROR: MÁXIMO " + limite + " NÚMEROS", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                
                //SI SE INGRESA UN CARACTER DIFERENTE A UN DIGITO NO SE TENDRA EN CUENTA
                if (!Character.isDigit(e.getKeyChar()) && (e.getKeyChar() != (char)KeyEvent.VK_BACK_SPACE)) {  
                    e.consume();
                    JOptionPane.showMessageDialog(null, "ERROR: SOLO SE PERMITEN NÚMEROS", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            public void keyPressed(KeyEvent arg0) {
            }

            public void keyReleased(KeyEvent arg0) {
            }
        
        });
        
    }
    
    public static void validarDecimales(JTextField campo){
        
        campo.addKeyListener(new KeyListener(){
 
            @Override
            public void keyTyped(KeyEvent e){ 
                //SI SE INGRESA UN CARACTER DIFERENTE A UN DIGITO O A UN PUNTO NO SE TENDRA EN CUENTA
                if (!Character.isDigit(e.getKeyChar()) && e.getKeyChar() != '.' && (e.getKeyChar() != (char)KeyEvent.VK_BACK_SPACE)) {  
                    e.consume();
                    JOptionPane.showMessageDialog(null, "ERROR: SÓLO SE PERMITEN NÚMEROS DECÍMALES", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                }

                if (e.getKeyChar() == '.' && campo.getText().contains(".")) {
                    e.consume();
                    JOptionPane.showMessageDialog(null, "ERROR: YA CONTIENE UN PUNTO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                }
                
            }

            public void keyPressed(KeyEvent arg0) {
            }

            public void keyReleased(KeyEvent arg0) {
            }
        
        });
        
    }
    
    public static void validarLongitud(JTextField campo, int limite){
        
        campo.addKeyListener(new KeyListener(){
 
            @Override
            public void keyTyped(KeyEvent e){

                    if (campo.getText().length()== limite){
                        e.consume();
                        JOptionPane.showMessageDialog(null, "ERROR: MÁXIMO " + limite + " CARACTERES", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                    }
               
            }

            public void keyPressed(KeyEvent arg0) {
            }

            public void keyReleased(KeyEvent arg0) {
            }
        
        });
        
    }
    
}
