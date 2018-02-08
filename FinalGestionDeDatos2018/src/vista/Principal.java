
package vista;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import org.jdesktop.swingx.JXLabel;

/**
 *
 * @author fernando
 */
public class Principal extends javax.swing.JFrame {

   
    public Principal() {
        initComponents();
        MenuGestiones();
        this.setExtendedState(MAXIMIZED_BOTH);
    }

    
    public final void MenuGestiones(){
        Icon IconoEmpleado = new ImageIcon(ClassLoader.getSystemResource("imagenes/Empleado.png"));
        final JXLabel labelEmpleado = new JXLabel("Gestión de Empleados", IconoEmpleado, JXLabel.LEFT);
        
        labelEmpleado.addMouseListener(new MouseAdapter() {
            
            public void MouseClicked(MouseEvent e) throws SQLException{
                GestionEmpleado vista = new GestionEmpleado(null, true);
                vista.setVisible(true);
            }
            
            
        
        });
    
        
        MenuGestion.add(labelEmpleado);
    }
    
    public final void MenuVentas(){
    
    }
    
    public final void MenuCompras(){
    
    }
    
    public final void MenuReportes(){
    
    }
    
    public final void MenuSistema(){
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCPanel1 = new com.bolivia.panel.JCPanel();
        jPanel3 = new javax.swing.JPanel();
        jXTaskPaneContainer2 = new org.jdesktop.swingx.JXTaskPaneContainer();
        MenuGestion = new org.jdesktop.swingx.JXTaskPane();
        MenuVentas = new org.jdesktop.swingx.JXTaskPane();
        MenuCompras = new org.jdesktop.swingx.JXTaskPane();
        MenuReportes = new org.jdesktop.swingx.JXTaskPane();
        MenuSistema = new org.jdesktop.swingx.JXTaskPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jCPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setPreferredSize(new java.awt.Dimension(300, 420));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.swingx.VerticalLayout verticalLayout2 = new org.jdesktop.swingx.VerticalLayout();
        verticalLayout2.setGap(14);
        jXTaskPaneContainer2.setLayout(verticalLayout2);

        MenuGestion.setScrollOnExpand(true);
        MenuGestion.setSpecial(true);
        MenuGestion.setTitle("Gestión");
        jXTaskPaneContainer2.add(MenuGestion);

        MenuVentas.setScrollOnExpand(true);
        MenuVentas.setSpecial(true);
        MenuVentas.setTitle("Ventas");
        jXTaskPaneContainer2.add(MenuVentas);

        MenuCompras.setName(""); // NOI18N
        MenuCompras.setScrollOnExpand(true);
        MenuCompras.setSpecial(true);
        MenuCompras.setTitle("Compras");
        jXTaskPaneContainer2.add(MenuCompras);

        MenuReportes.setScrollOnExpand(true);
        MenuReportes.setSpecial(true);
        MenuReportes.setTitle("Reportes");
        jXTaskPaneContainer2.add(MenuReportes);

        MenuSistema.setScrollOnExpand(true);
        MenuSistema.setSpecial(true);
        MenuSistema.setTitle("Sistema");
        jXTaskPaneContainer2.add(MenuSistema);

        jPanel3.add(jXTaskPaneContainer2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 280, 400));

        jCPanel1.add(jPanel3, java.awt.BorderLayout.LINE_START);

        getContentPane().add(jCPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXTaskPane MenuCompras;
    private org.jdesktop.swingx.JXTaskPane MenuGestion;
    private org.jdesktop.swingx.JXTaskPane MenuReportes;
    private org.jdesktop.swingx.JXTaskPane MenuSistema;
    private org.jdesktop.swingx.JXTaskPane MenuVentas;
    private com.bolivia.panel.JCPanel jCPanel1;
    private javax.swing.JPanel jPanel3;
    private org.jdesktop.swingx.JXTaskPaneContainer jXTaskPaneContainer2;
    // End of variables declaration//GEN-END:variables
}
