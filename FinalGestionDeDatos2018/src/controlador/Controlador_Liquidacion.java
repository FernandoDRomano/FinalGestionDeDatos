package controlador;

import java.awt.Frame;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.Cargo;
import modelo.Concepto;
import modelo.Domicilio;
import modelo.Empleado;
import modelo.Familia;
import modelo.LineaLiquidacion;
import modelo.Liquidacion;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import vista.BuscarConcepto;
import vista.BuscarConceptoLQT;
import vista.BuscarEmpleadoLiquidacion;
import vista.LiquidacionEmpleado;
import vista.LiquidacionTodos;
import vista.ListarLiquidaciones;

/**
 *
 * @author fernando
 */
public class Controlador_Liquidacion {
    
    private static Liquidacion liquidacion;
    private static LineaLiquidacion linea;
    private static Concepto concepto;
    private static Empleado empleado;
    private static Familia familia;
    private static Domicilio domicilio;
    private static Cargo cargo;
    
    //METODO PARA CARGAR TODOS LOS EMPLEADOS EN EL FORM DE BUSQUEDA
    public static void cargarFormBusquedaEmpleado(LiquidacionEmpleado vista) throws SQLException{
        BuscarEmpleadoLiquidacion buscar = new BuscarEmpleadoLiquidacion(null, true, vista);
        buscar.setVisible(true);
    }
    
    public static void CargarTodosLosEmpleados(BuscarEmpleadoLiquidacion vista) throws SQLException {
        empleado = new Empleado();
        vista.getModelo().setColumnCount(0);
        vista.getModelo().setNumRows(0);
        vista.getModelo().addColumn("Id");
        vista.getModelo().addColumn("Apellido");
        vista.getModelo().addColumn("Nombre");
        vista.getModelo().addColumn("DNI");
        ResultSet r = empleado.listarEmpleadoActivo();
        while (r.next()) {
            Object[] fila = new Object[4];
            fila[0] = r.getString("idempleado");
            fila[1] = r.getString("apellido");
            fila[2] = r.getString("nombre");
            fila[3] = r.getString("dni");
            vista.getModelo().addRow(fila);
        }

        vista.getTabla_Empleado().setModel(vista.getModelo());
        
        int[] anchos = {30, 200, 200, 80};
        for(int i = 0; i < vista.getTabla_Empleado().getColumnCount(); i++) {
            vista.getTabla_Empleado().getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        
    }
    
    public static void AgregarEmpleadoAliquidacion(BuscarEmpleadoLiquidacion vista, LiquidacionEmpleado vistaL) throws SQLException, ParseException{
        int fila = vista.getTabla_Empleado().getSelectedRow();
        //PREGUNTO SI SE ISO CLICK EN ALGUNA FILA
        if (fila > -1) {
            //INSTANCIO LOS OBJETOS A UTILIZAR
            empleado = new Empleado();
            familia = new Familia();
            domicilio = new Domicilio();
            //SETEO EL ID DEL EMPLEADO
            empleado.setIdEmpleado(Integer.valueOf(vista.getTabla_Empleado().getModel().getValueAt(fila, 0).toString()));
            //LE ASIGNO EL EMPLEADO A LA FAMILIA
            familia.setEmpleado(empleado);
            
            //BUSCO LOS FAMILIARES Y TRAIGO TODOS LOS DATOS DEL EMPLEADO PARA CARGARLO EN LA LIQUIDACION
            ResultSet r = empleado.buscarEmpleadoId();            
            while(r.next()) {                
                vistaL.getTxt_ID().setText(r.getString("idempleado"));
                vistaL.getTxt_Nombre().setText(r.getString("apellido") + " ," + r.getString("nombre"));
                vistaL.getTxt_Dni().setText(r.getString("dni"));
                //TRATAMIENTO DE LA FECHA DE INGRESO
                SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
                String fechaEmpleado = r.getString("fechaIngreso");
                Date fecha = null;
                fecha = formatoDelTexto.parse(fechaEmpleado);
                vistaL.getDate_fechaIngreso().setDate(fecha);
                //FIN DEL TRATAMIENTO
                vistaL.getTxt_Domicilio().setText(r.getString("calle") + " " + r.getString("numero"));
                vistaL.getTxt_Cargo().setText(r.getString("cargo"));
                vistaL.getTxt_SueldoBasico().setText(r.getString("basico"));
                vistaL.getTxt_Antiguedad().setText(r.getString("antiguedad"));
            }
            
            IniciarModeloFamilia(vistaL);
            ResultSet f = familia.listarFamiliar();
        
            while (f.next()) {
                Object[] filaE = new Object[5];
                filaE[0] = f.getString("idfamilia");
                filaE[1] = f.getString("apellido") + " ," + f.getString("nombre");
                filaE[2] = f.getString("vinculo");
                filaE[3] = f.getString("discapacidad");
                filaE[4] = f.getString("escolaridad");
                vistaL.getModeloFamilia().addRow(filaE);
            }
        vistaL.getTabla_Familia().setModel(vistaL.getModeloFamilia());
        
        int[] anchos = {25, 200, 100, 100, 100};
        for(int i = 0; i < vistaL.getTabla_Familia().getColumnCount(); i++) {
            vistaL.getTabla_Familia().getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        
        //CIERRO EL FORM DE BUSQUEDA
        vista.setVisible(false);
        
        int opt = JOptionPane.showConfirmDialog(vista, "¿DESEA USTED CARGAR AUTOMÁTICAMENTE LOS CONCEPTOS \nRELACIONADOS CON EL GRUPO FAMILIAR?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (opt == JOptionPane.YES_OPTION) {
            //REALIZA LOS CALCULOS AUTOMATICOS DE LA FAMILIA
            ResultSet x = familia.listarFamiliar();
            CargarConceptosDeLaFamiliaAutomaticamente(vistaL, x);
            CalcularTotales(vistaL);
        }
        
            CalcularTotales(vistaL);
            
        }else{
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UN EMPLEADO", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
    }
    
    
    //METODOS PARA BUSCAR Y CARGAR LOS CONCEPTOS
    public static void CargarFormBusquedaConcepto(LiquidacionEmpleado vista) throws SQLException{
        if (vista.getTxt_ID().getText().length() == 0) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UN EMPLEADO", "ERROR", JOptionPane.ERROR_MESSAGE);
        }else{
            BuscarConcepto buscar = new BuscarConcepto(null, true, vista);
            buscar.setVisible(true);
        }
    }
    
    public static void CargarTodosLosConceptos(BuscarConcepto vista) throws SQLException{
        concepto = new Concepto();
        vista.getModelo().setColumnCount(0);
        vista.getModelo().setNumRows(0);
        vista.getModelo().addColumn("Id");
        vista.getModelo().addColumn("Descripción");
        vista.getModelo().addColumn("Tipo de Concepto");
        vista.getModelo().addColumn("Fijo");
        vista.getModelo().addColumn("Porcentaje");
        vista.getModelo().addColumn("Monto");
        ResultSet r = concepto.listarConceptos();
        while (r.next()) {
            Object[] fila = new Object[6];
            fila[0] = r.getString("idconcepto");
            fila[1] = r.getString("descripcion");
            fila[2] = r.getString("tipoConcepto");
            fila[3] = r.getString("fijo");
            fila[4] = r.getString("porcentaje");
            fila[5] = r.getString("monto");
            vista.getModelo().addRow(fila);
        }
        vista.getTabla_Concepto().setModel(vista.getModelo());
    }
    
    public static void CargarTodosLosConceptos(BuscarConcepto vista, String cadena) throws SQLException{
        concepto = new Concepto();
        vista.getModelo().setColumnCount(0);
        vista.getModelo().setNumRows(0);
        vista.getModelo().addColumn("Id");
        vista.getModelo().addColumn("Descripción");
        vista.getModelo().addColumn("Tipo de Concepto");
        vista.getModelo().addColumn("Fijo");
        vista.getModelo().addColumn("Porcentaje");
        vista.getModelo().addColumn("Monto");
        ResultSet r = concepto.buscarConcepto(cadena);
        while (r.next()) {
            Object[] fila = new Object[6];
            fila[0] = r.getString("idconcepto");
            fila[1] = r.getString("descripcion");
            fila[2] = r.getString("tipoConcepto");
            fila[3] = r.getString("fijo");
            fila[4] = r.getString("porcentaje");
            fila[5] = r.getString("monto");
            vista.getModelo().addRow(fila);
        }
        vista.getTabla_Concepto().setModel(vista.getModelo());
    }
    
    public static void calcularAntiguedad(LiquidacionEmpleado vista, String fecha) throws SQLException{
        empleado = new Empleado();
        if (!vista.getTxt_ID().getText().equals("")) {
            empleado.setIdEmpleado(Integer.valueOf(vista.getTxt_ID().getText()));
            String antiguedad = "";
            ResultSet r = empleado.calcularAntiguedad(fecha);
            while (r.next()) {            
                antiguedad = r.getString("antiguedad");
            }
            vista.getTxt_Antiguedad().setText(antiguedad);
        }
        
    }
    
    public static void AgregarConcepto(BuscarConcepto vista, LiquidacionEmpleado vistaL){
        int fila = vista.getTabla_Concepto().getSelectedRow();
        if (fila > -1) {
            //OBTENGO LOS VALORES DE LA TABLA
            String id = vista.getTabla_Concepto().getModel().getValueAt(fila, 0).toString();
            String descripcion = vista.getTabla_Concepto().getModel().getValueAt(fila, 1).toString();
            String tipo = vista.getTabla_Concepto().getModel().getValueAt(fila, 2).toString();
            String fijo = vista.getTabla_Concepto().getModel().getValueAt(fila, 3).toString();
            double porcentaje = Double.parseDouble(vista.getTabla_Concepto().getModel().getValueAt(fila, 4).toString());
            double monto = Double.valueOf(vista.getTabla_Concepto().getModel().getValueAt(fila, 5).toString());
            int cantidad = Integer.valueOf(vista.getTxt_Cantidad().getText());
            //VERIFICO SI TIENE UN MONTO FIJO O NO, CASO CONTRARIO CALCULO EL MONTO
            
            if (fijo.equals("SI")) {
                //double basico = Double.valueOf(vistaL.getTxt_SueldoBasico().getText());
                double total = monto * cantidad;
                //COMO EL ES FIJO EL MONTO NO TIENE PORCENTAJE
                String filaE [] = {id, descripcion, tipo, fijo, "--------------", String.valueOf(monto), String.valueOf(cantidad), String.valueOf(total)};
                vistaL.getModeloConcepto().addRow(filaE);
                vistaL.getTabla_Conceptos().setModel(vistaL.getModeloConcepto());
                CalcularTotales(vistaL);
            }else{
                //COMO LA ANTIGUEDAD NO ES FIJO, VALIDO PRIMERO QUE NO SEA EL CONCEPTO DE ANTIGUEDAD DEBIDO A QUE TIENE UNA FORMULA DIFERENTE AL OTRO TIPO DE PORCENTAJE
                if (id.equals("7")) {//ID 7 ES ANTIGUEDAD   
                    double basico = Double.valueOf(vistaL.getTxt_SueldoBasico().getText());
                    //LA ANTIGUEDAD SE CALCULARA COMO BASICO * PORCENTAJE * ANTIGUEDAD
                    int antiguedad = Integer.valueOf(vistaL.getTxt_Antiguedad().getText());
                    double total = basico * (porcentaje / 100) * antiguedad;
                    String filaE [] = {id, descripcion, tipo, fijo, String.valueOf(porcentaje), "--------------", String.valueOf(cantidad), String.valueOf(total)};
                    vistaL.getModeloConcepto().addRow(filaE);
                    vistaL.getTabla_Conceptos().setModel(vistaL.getModeloConcepto());
                    CalcularTotales(vistaL);
                    //CUANDO AGREGUE EL CONCEPTO DE ANTIGUEDAD YA NO SE PODRA CAMBIAR EL PERIODO
                    vistaL.getDate_Año().setEnabled(false);
                    vistaL.getDate_Mes().setEnabled(false);
                }else{
                    //SI NO ES EL CONCEPTO DE ANTIGUEDAD LOS DEMAS LOS CALCULO COMO EL PORCENTAJE DEL BASICO
                    double basico = Double.valueOf(vistaL.getTxt_SueldoBasico().getText());
                    double total = ((basico * porcentaje) / 100) * cantidad;
                    //COMO NO ES FIJO NO TIENE MONTO FIJO
                    String filaE [] = {id, descripcion, tipo, fijo, String.valueOf(porcentaje), "--------------", String.valueOf(cantidad), String.valueOf(total)};
                    vistaL.getModeloConcepto().addRow(filaE);
                    vistaL.getTabla_Conceptos().setModel(vistaL.getModeloConcepto());
                    CalcularTotales(vistaL);
                }
            }
            
            //DESPUES DE CARGAR EL CONCEPTO EN LA LIQUIDACION CIERRO LA VENTANA DE BUSQUEDA
            vista.dispose();
            
        }else{
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UN CONCEPTO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void QuitarConcepto(LiquidacionEmpleado vista){
        int fila = vista.getTabla_Conceptos().getSelectedRow();
        if (fila > -1) {
            int opt = JOptionPane.showConfirmDialog(vista, "¿ESTÁ SEGURO DE QUITAR ESTE CONCEPTO?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (opt == JOptionPane.YES_OPTION) {
                //ELIMINO LA FILA DEL MODELO
                vista.getModeloConcepto().removeRow(fila);
                CalcularTotales(vista);
            } 
        }else{
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UN CONCEPTO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void CargarConceptosDeLaFamiliaAutomaticamente(LiquidacionEmpleado vista, ResultSet familia) throws SQLException{
        int matrimonio = 0;
        int hijosDiscapacidad = 0;
        int primaria = 0;
        int secundaria = 0;
        int universidad = 0;
        double total = 0; // SIRVE PARA CALCULAR EL TOTAL DEL CONCEPTO = MONTO * CANTIDAD
        
        //REINICIO EL MODELO DE LOS CONCEPTOS
        IniciarModeloConcepto(vista);
        
        while (familia.next()) {     
            String vinculo = familia.getString("vinculo");
            String discapacidad = familia.getString("discapacidad");
            String escolaridad = familia.getString("escolaridad");
            
            //PREGUNTO PRIMERO POR EL VINCULO
            if (vinculo.equals("Esposo/a")) {
                matrimonio ++;
            }else{
                if (vinculo.equals("Hijo/a")) {
                    //SI ES UN HIJ@ PREGUNTO SI TIENE ESCOLARIDAD
                    if (escolaridad.equals("Primaria")) {
                        primaria ++;
                    }
                    
                    if (escolaridad.equals("Secundaria")) {
                        secundaria ++;
                    }
                    
                    if (escolaridad.equals("Universidad")) {
                        universidad ++;
                    }
                    
                    if (discapacidad.equals("Si")) {
                        hijosDiscapacidad ++;
                    }
                }
            }
            
            System.out.println("Vinculo: " + vinculo + " - Escolaridad: " + escolaridad + " - Discapacidad: " + discapacidad);
            
        }//FIN DEL CICLO WHILE
        
        System.out.println("Total de Cónyuge:" + matrimonio);
        System.out.println("Total de Escolaridad Primaria:" + primaria);
        System.out.println("Total de Escolaridad Secundaria:" + secundaria);
        System.out.println("Total de Escolaridad Universidad:" + universidad);
        System.out.println("Total de Hijos con Discapacidad:" + hijosDiscapacidad);
        
        //ENTRO A BUSCAR LOS CONCEPTOS PARA APLICAR LOS RESULTADOS OBTENIDOS
        concepto = new Concepto();
        ResultSet c = concepto.listarConceptos();
        while (c.next()) {            
              String id = c.getString("idconcepto");
              String descripcion = c.getString("descripcion");
              String tipo = c.getString("tipoConcepto");
              String fijo = c.getString("fijo");
              String porcentaje = c.getString("porcentaje");
              double monto = Double.valueOf(c.getString("monto"));
              
              if (id.equals("1")) { //EL ID 1 ES DEL SUELDO BASICO
                      // COMO EL CONCEPTO DEL BASICO TIENE UN 100% DE PORCENTAJE DEL BASICO REALIZO EL SIGUIENTE CALCULO  
                      total = (Double.valueOf(porcentaje) * Double.valueOf(vista.getTxt_SueldoBasico().getText()))/100;
                      String fila[] = {id, descripcion, tipo, fijo, porcentaje , "--------------", String.valueOf("1"), String.valueOf(total)};
                      vista.getModeloConcepto().addRow(fila);
              }
              
              if (id.equals("2")){//EL ID 2 ES Asignación Familiar por Ayuda Escolar primaria
                  if (primaria > 0) {
                      total = monto * primaria;
                      String fila[] = {id, descripcion, tipo, fijo, "--------------",  String.valueOf(monto), String.valueOf(primaria), String.valueOf(total)};
                      vista.getModeloConcepto().addRow(fila);
                  }
              }
              
              if (id.equals("3")){//EL ID 3 ES Asignación Familiar por Ayuda Escolar Secundaria
                  if (secundaria > 0) {
                      total = monto * secundaria;
                      String fila[] = {id, descripcion, tipo, fijo, "--------------", String.valueOf(monto), String.valueOf(secundaria), String.valueOf(total)};
                      vista.getModeloConcepto().addRow(fila);
                  }
              }
              
              if (id.equals("4")) {//EL ID 4 ES Asignación Familiar por Ayuda Escolar Universitaria
                  if (universidad > 0) {
                      total = monto * universidad;
                      String fila[] = {id, descripcion, tipo, fijo, "--------------", String.valueOf(monto), String.valueOf(universidad), String.valueOf(total)};
                      vista.getModeloConcepto().addRow(fila);
                  }   
              }
              
              if (id.equals("5")) {//EL ID 2 ES Asignación Familiar por Matrimonio
                  if (matrimonio > 0) {
                     total = monto * matrimonio;
                     String fila[] = {id, descripcion, tipo, fijo, "--------------", String.valueOf(monto), String.valueOf(matrimonio), String.valueOf(total)};
                     vista.getModeloConcepto().addRow(fila); 
                  }
              }
              
              if (id.equals("6")) {////EL ID 2 ES Asignación Familiar por Hijo con Discapacidad
                  if (hijosDiscapacidad > 0) {
                     total = monto * hijosDiscapacidad;
                     String fila[] = {id, descripcion, tipo, fijo, "--------------", String.valueOf(monto), String.valueOf(hijosDiscapacidad), String.valueOf(total)};
                     vista.getModeloConcepto().addRow(fila);   
                  }
              }
                            
              
        }//FIN DEL CICLO WHILE DE CONCEPTOS
        
        vista.getTabla_Conceptos().setModel(vista.getModeloConcepto());
        TamañoDeLasColumnasDeLosConceptos(vista);    
    }
    
    public static void CalcularTotales(LiquidacionEmpleado vista){
        double totalHaberes = 0;
        double totalDescuentos = 0;
        double totalNeto = 0;
        
        for (int i = 0; i < vista.getTabla_Conceptos().getRowCount(); i++) {
            //OBTENGO EL TIPO DE LA FILA i
            String tipo = vista.getTabla_Conceptos().getModel().getValueAt(i, 2).toString();
            if (tipo.equals("Haber")) {
                totalHaberes += Double.valueOf(vista.getTabla_Conceptos().getModel().getValueAt(i, 7).toString());
            }else{
                totalDescuentos += Double.valueOf(vista.getTabla_Conceptos().getModel().getValueAt(i, 7).toString());
            }
        }
        
        totalNeto = totalHaberes - totalDescuentos;
        vista.getTxt_Haberes().setText("" + totalHaberes);
        vista.getTxt_Descuentos().setText("" + totalDescuentos);
        vista.getTxt_SueldoNeto().setText("" + totalNeto);
    }
    
    public static void TamañoDeLasColumnasDeLosConceptos(LiquidacionEmpleado vista){
        int[] anchos = {20, 280, 50, 50, 65, 50, 50, 50};
        for(int i = 0; i < vista.getTabla_Conceptos().getColumnCount(); i++) {
            vista.getTabla_Conceptos().getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
    }
    
    public static boolean ValidarCampos(LiquidacionEmpleado vista){
        boolean bandera = true;
        //VALIDO QUE LOS CAMPOS NO SEAN VACIOS
        String id = vista.getTxt_ID().getText();
        id = id.replace(" ", "");

        String sueldoNeto = vista.getTxt_SueldoNeto().getText();
        sueldoNeto = sueldoNeto.replaceAll(" ", "");
            
        if (id.length() == 0) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UN EMPLEADO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        if (sueldoNeto.length() == 0 || sueldoNeto.equals("0.0")) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR LOS CONCEPTOS PARA LA LIQUIDACIÓN", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        return bandera;
    }
    
    // METODOS ABM
    public static void AltaLiquidacion(LiquidacionEmpleado vista) throws SQLException, ClassNotFoundException{
        if (ValidarCampos(vista)) {
            empleado = new Empleado();
            liquidacion = new Liquidacion();
            linea = new LineaLiquidacion();
            concepto = new Concepto();

            //SETEO LOS VALORES
            empleado.setIdEmpleado(Integer.valueOf(vista.getTxt_ID().getText()));

            liquidacion.setAño(vista.getDate_Año().getYear());
            //COMO ME DEVUELVE MES-1 LE SUMO 1 CON UNA VARIABLE, NO LO TOMA DIRECTAMENTE
            int mes = 1 + Integer.valueOf(vista.getDate_Mes().getMonth());
            liquidacion.setMes(mes);
            liquidacion.setTotalHaberes(Double.valueOf(vista.getTxt_Haberes().getText()));
            liquidacion.setTotalDescuentos(Double.valueOf(vista.getTxt_Descuentos().getText()));
            liquidacion.setSueldoNeto(Double.valueOf(vista.getTxt_SueldoNeto().getText()));
            liquidacion.setEmpleado(empleado);

            //TERMINAR DE HACER EL ALTA
            liquidacion.grabarLiquidacion();
            //TRAIGO EL MAXIMO ID
            liquidacion.setIdLiquidacion(liquidacion.maxId());

            for (int i = 0; i < vista.getModeloConcepto().getRowCount(); i++) {
                //INSTANCIO UN NUEVO CONCEPTO 
                concepto = new Concepto();
                //SETEO EL ID DEL CONCEPTO
                concepto.setIdConcepto(Integer.valueOf(vista.getTabla_Conceptos().getModel().getValueAt(i, 0).toString()));
                //INSTANCIO UNA NUEVA LINEA_LIQUIDACION
                linea = new LineaLiquidacion();
                //SETEO LOS VALORES DE LA LINEA_LIQUIDACION
                linea.setCantidad(Integer.valueOf(vista.getTabla_Conceptos().getModel().getValueAt(i, 6).toString()));
                linea.setMonto(Double.valueOf(vista.getTabla_Conceptos().getModel().getValueAt(i, 7).toString()));
                linea.setConcepto(concepto);
                linea.setLiquidacion(liquidacion);
                //GRABO LAS LINEAS
                linea.grabarLineaLiquidacion();
            }

            JOptionPane.showMessageDialog(vista, "LIQUIDACIÓN GRABADA CON EXITO!", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
            String fecha = "01/"+liquidacion.getMes()+"/"+liquidacion.getAño();
            imprimirLiquidacion(liquidacion);
            LimpiarPantalla(vista);
        }
        
    }
    
    public static void LimpiarPantalla(LiquidacionEmpleado vista){
        vista.getTxt_Antiguedad().setText("");
        vista.getTxt_Cargo().setText("");
        vista.getTxt_Descuentos().setText("");
        vista.getTxt_Dni().setText("");
        vista.getTxt_Domicilio().setText("");
        vista.getTxt_Haberes().setText("");
        vista.getTxt_ID().setText("");
        vista.getTxt_Nombre().setText("");
        vista.getTxt_SueldoBasico().setText("");
        vista.getTxt_SueldoNeto().setText("");
        vista.getDate_fechaIngreso().setDate(null);
        IniciarModeloConcepto(vista);
        IniciarModeloFamilia(vista);
    }
    
    public static void IniciarModeloConcepto(LiquidacionEmpleado vista){
        vista.getModeloConcepto().setColumnCount(0);
        vista.getModeloConcepto().setNumRows(0);
        vista.getModeloConcepto().addColumn("Id");
        vista.getModeloConcepto().addColumn("Concepto");
        vista.getModeloConcepto().addColumn("Tipo");
        vista.getModeloConcepto().addColumn("Fijo");
        vista.getModeloConcepto().addColumn("Porcentaje");
        vista.getModeloConcepto().addColumn("Monto");
        vista.getModeloConcepto().addColumn("Cantidad");
        vista.getModeloConcepto().addColumn("Total");
    }
    
    public static void IniciarModeloFamilia(LiquidacionEmpleado vista){
        vista.getModeloFamilia().setColumnCount(0);
        vista.getModeloFamilia().setNumRows(0);
        vista.getModeloFamilia().addColumn("Id");
        vista.getModeloFamilia().addColumn("Nombre");
        vista.getModeloFamilia().addColumn("Vínculo");
        vista.getModeloFamilia().addColumn("Discapacidad");
        vista.getModeloFamilia().addColumn("Escolaridad");
    }
    
/*
    METODOS PARA LIQUIDAR A TODOS LOS EMPLEADOS DE UNA SOLA VEZ
*/
    
    public static void CargarFormBusquedaConcepto(LiquidacionTodos vista) throws SQLException{
        BuscarConceptoLQT buscar = new BuscarConceptoLQT(null, true, vista);
        buscar.setVisible(true);
    }
    
    public static void CargarTodosLosConceptos(BuscarConceptoLQT vista) throws SQLException{
        concepto = new Concepto();
        vista.getModelo().setColumnCount(0);
        vista.getModelo().setNumRows(0);
        vista.getModelo().addColumn("Id");
        vista.getModelo().addColumn("Descripción");
        vista.getModelo().addColumn("Tipo de Concepto");
        vista.getModelo().addColumn("Fijo");
        vista.getModelo().addColumn("Porcentaje");
        vista.getModelo().addColumn("Monto");
        ResultSet r = concepto.listarConceptos();
        while (r.next()) {
            Object[] fila = new Object[6];
            fila[0] = r.getString("idconcepto");
            fila[1] = r.getString("descripcion");
            fila[2] = r.getString("tipoConcepto");
            fila[3] = r.getString("fijo");
            fila[4] = r.getString("porcentaje");
            fila[5] = r.getString("monto");
            vista.getModelo().addRow(fila);
        }
        vista.getTabla_Concepto().setModel(vista.getModelo());
    }
    
    public static void CargarTodosLosConceptos(BuscarConceptoLQT vista, String cadena) throws SQLException{
        concepto = new Concepto();
        vista.getModelo().setColumnCount(0);
        vista.getModelo().setNumRows(0);
        vista.getModelo().addColumn("Id");
        vista.getModelo().addColumn("Descripción");
        vista.getModelo().addColumn("Tipo de Concepto");
        vista.getModelo().addColumn("Fijo");
        vista.getModelo().addColumn("Porcentaje");
        vista.getModelo().addColumn("Monto");
        ResultSet r = concepto.buscarConcepto(cadena);
        while (r.next()) {
            Object[] fila = new Object[6];
            fila[0] = r.getString("idconcepto");
            fila[1] = r.getString("descripcion");
            fila[2] = r.getString("tipoConcepto");
            fila[3] = r.getString("fijo");
            fila[4] = r.getString("porcentaje");
            fila[5] = r.getString("monto");
            vista.getModelo().addRow(fila);
        }
        vista.getTabla_Concepto().setModel(vista.getModelo());
    }
    
    public static void AgregarConcepto(BuscarConceptoLQT vista, LiquidacionTodos vistaL){
        int fila = vista.getTabla_Concepto().getSelectedRow();
        if (fila > -1) {
            //OBTENGO LOS VALORES DE LA TABLA
            String id = vista.getTabla_Concepto().getModel().getValueAt(fila, 0).toString();
            String descripcion = vista.getTabla_Concepto().getModel().getValueAt(fila, 1).toString();
            String tipo = vista.getTabla_Concepto().getModel().getValueAt(fila, 2).toString();
            String fijo = vista.getTabla_Concepto().getModel().getValueAt(fila, 3).toString();
            String porcentaje = vista.getTabla_Concepto().getModel().getValueAt(fila, 4).toString();
            String monto = vista.getTabla_Concepto().getModel().getValueAt(fila, 5).toString();

                String filaE [] = {id, descripcion, tipo, fijo, porcentaje, monto};
                vistaL.getModeloConcepto().addRow(filaE);
                vistaL.getTabla_Conceptos().setModel(vistaL.getModeloConcepto());
            
            vista.dispose();
            
        }else{
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UN CONCEPTO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void QuitarConcepto(LiquidacionTodos vista){
        int fila = vista.getTabla_Conceptos().getSelectedRow();
        if (fila > -1) {
            int opt = JOptionPane.showConfirmDialog(vista, "¿ESTÁ SEGURO DE QUITAR ÉSTE CONCEPTO?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (opt == JOptionPane.YES_OPTION) {
                //ELIMINO LA FILA DEL MODELO
                vista.getModeloConcepto().removeRow(fila);
            } 
        }else{
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UN CONCEPTO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static int calcularAntiguedad(String fecha, int id) throws SQLException{
        empleado = new Empleado();
        empleado.setIdEmpleado(id);
        String antiguedad = "";
        ResultSet r = empleado.calcularAntiguedad(fecha);
        while (r.next()) {            
            antiguedad = r.getString("antiguedad");
        }
        
        return Integer.valueOf(antiguedad);
    }
    
    public static boolean ValidarCampos(LiquidacionTodos vista){
        boolean bandera = true;
        int cantidad = vista.getTabla_Conceptos().getRowCount();
        if (cantidad == 0) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE CARGAR LOS CONCEPTOS PARA REALIZAR LA LIQUIDACIÓN", "Mensaje de Información", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        return bandera;
    }
    
    public static void LiquidarTodos(LiquidacionTodos vista) throws SQLException, ClassNotFoundException, JRException, FileNotFoundException{
        if (ValidarCampos(vista)) {
            int opt = JOptionPane.showConfirmDialog(vista, "¿ESTÁ USTED SEGURO DE REALIZAR LA LIQUIDACIÓN A TODOS LOS EMPLEADOS?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (opt == JOptionPane.YES_OPTION) {
                int op = JOptionPane.showConfirmDialog(vista, "¿DESEA GENERAR LOS COMPROBANTES DE LAS LIQUIDACIONES DE SUELDO?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (op == JOptionPane.YES_OPTION) {
                    
                //ABRIR CUADRO DE DIALOGO PARA GUARDAR EL ARCHIVO 
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("todos los archivos *.PDF", "pdf", "PDF"));//filtro para ver solo archivos .pdf
                int seleccion = fileChooser.showSaveDialog(null);
                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    
                    //INSTANCIO UN OBJETO EMPLEADO PARA BUSCAR TODOS LOS EMPLEADOS DE LA BD
                    empleado = new Empleado();
                    //BUSCO LOS EMPLEADOS Y RECORRO
                    ResultSet e = empleado.listarEmpleadoActivo();
                    int cantidadEmpleado = 0;

                    JOptionPane.showMessageDialog(vista, "¡ESPERE UN MOMENTO POR FAVOR, \nÉSTE PROCESO PUEDE DEMORAR UNOS SEGUNDOS!", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);

                    while(e.next()){
                        //DEFINO LAS VARIABLES PARA REALIZAR LOS CALCULOS
                        double totalHaberes = 0;
                        double totalDescuentos = 0;
                        double sueldoNeto = 0;

                        empleado.setIdEmpleado(Integer.valueOf(e.getString("idempleado")));
                        empleado.setNombre(e.getString("apellido") + ", " + e.getString("nombre"));
                        System.out.println("EMPLEADO ID: " + empleado.getIdEmpleado());

                        //INSTANCIO UN OBJETO DEL TIPO FAMILIA, PARA SABER SI TIENE FAMILIARES
                        familia = new Familia();
                        familia.setEmpleado(empleado);
                        ResultSet r = familia.listarFamiliar();

                        //VARIABLES A UTILIZAR PARA CALCULAR LA SITUACION DEL GRUPO FAMILIAR
                        int matrimonio = 0;
                        int hijosDiscapacidad = 0;
                        int primaria = 0;
                        int secundaria = 0;
                        int universidad = 0;
                        while (r.next()) {     
                            String vinculo = r.getString("vinculo");
                            String discapacidad = r.getString("discapacidad");
                            String escolaridad = r.getString("escolaridad");

                            //PREGUNTO PRIMERO POR EL VINCULO
                            if (vinculo.equals("Esposo/a")) {
                                matrimonio ++;
                            }else{
                                if (vinculo.equals("Hijo/a")) {
                                    //SI ES UN HIJ@ PREGUNTO SI TIENE ESCOLARIDAD
                                    if (escolaridad.equals("Primaria")) {
                                        primaria ++;
                                    }

                                    if (escolaridad.equals("Secundaria")) {
                                        secundaria ++;
                                    }

                                    if (escolaridad.equals("Universidad")) {
                                        universidad ++;
                                    }

                                    if (discapacidad.equals("Si")) {
                                        hijosDiscapacidad ++;
                                    }
                                }
                            }

                            System.out.println("Vinculo: " + vinculo + " - Escolaridad: " + escolaridad + " - Discapacidad: " + discapacidad);

                        }//FIN DEL CICLO WHILE




                        //POR CADA EMPLEADO BUSCO SU BASICO Y LE ASIGNO AL CARGO
                        cargo = new Cargo();
                        cargo.setBasico(Double.valueOf(e.getString("basico")));

                        System.out.println("BASICO DEL EMPLEADO: " + cargo.getBasico());

                        //POR CADA EMPLEADO REALIZO LA LIQUIDACION
                        liquidacion = new Liquidacion();

                        //RECORRO EL MODELO DE LA TABLA CONCENTO DE LA VISTA
                        for (int i = 0; i < vista.getModeloConcepto().getRowCount(); i++) {
                            //INSTANCION UN OBJETO DE TIPO CONCEPTO Y SETEO SUS VALORES CON LA TABLA
                            concepto = new Concepto();
                            concepto.setIdConcepto(Integer.valueOf(vista.getTabla_Conceptos().getModel().getValueAt(i, 0).toString()));
                            concepto.setDescripcion(vista.getTabla_Conceptos().getModel().getValueAt(i, 1).toString());
                            concepto.setTipoConcepto(vista.getTabla_Conceptos().getModel().getValueAt(i, 2).toString());
                            concepto.setFijo(vista.getTabla_Conceptos().getModel().getValueAt(i, 3).toString());
                            concepto.setPorcentaje(Double.valueOf(vista.getTabla_Conceptos().getModel().getValueAt(i, 4).toString()));
                            concepto.setMonto(Double.valueOf(vista.getTabla_Conceptos().getModel().getValueAt(i, 5).toString()));
                            //INSTANCIO UNA LINEA DE LIQUIDACION
                            linea = new LineaLiquidacion();

                            //EMPIEZO EL PROCESO PARA CALCULAR LOS MONTOS

                            switch (concepto.getIdConcepto()) {
                            //EL CONCEPTO BASICO NO LO TOMARE AQUI SINO EN EL DEFAULT DEBIDO A QUE ES OTRO TIPO DE CALCULO SIMPLE
                            case 2://EL ID 2 ES LA AYUDA POR PRIMARIA
                                 if (primaria > 0) {
                                     linea.setConcepto(concepto);
                                     linea.setCantidad(primaria);
                                     linea.setMonto(concepto.getMonto() * linea.getCantidad());
                                     totalHaberes += linea.getMonto();
                                     liquidacion.agregarLinea(linea);
                                 }
                                 break;
                            case 3://EL ID 3 ES LA AYUDA POR SECUNDARIA
                                 if (secundaria > 0) {
                                    linea.setConcepto(concepto);
                                    linea.setCantidad(secundaria);
                                    linea.setMonto(concepto.getMonto() * linea.getCantidad());
                                    totalHaberes += linea.getMonto();
                                    liquidacion.agregarLinea(linea);
                                 }
                                 break;
                            case 4://EL ID 4 ES LA AYUDA POR UNIVERSIDAD
                                 if (universidad > 0) {
                                    linea.setConcepto(concepto);
                                    linea.setCantidad(universidad);
                                    linea.setMonto(concepto.getMonto() * linea.getCantidad());
                                    totalHaberes += linea.getMonto();
                                    liquidacion.agregarLinea(linea);
                                 }
                                 break;
                            case 5://EL ID 5 ES DE MATRIMONIO
                                 if (matrimonio > 0) {
                                    linea.setConcepto(concepto);
                                    linea.setCantidad(matrimonio);
                                    linea.setMonto(concepto.getMonto() * linea.getCantidad());
                                    totalHaberes += linea.getMonto();
                                    liquidacion.agregarLinea(linea);
                                 }
                                 break;
                            case 6://EL ID 6 ES DE AYUDA POR HIJOS CON DISCAPACIDAD
                                 if (hijosDiscapacidad > 0) {
                                    linea.setConcepto(concepto);
                                    linea.setCantidad(hijosDiscapacidad);
                                    linea.setMonto(concepto.getMonto() * linea.getCantidad());
                                    totalHaberes += linea.getMonto();
                                    liquidacion.agregarLinea(linea);
                                 }
                                 break;
                            case 7:
                                 linea.setConcepto(concepto);
                                 linea.setCantidad(1);
                                 //CALCULO LA ANTIGUEDAD
                                 int mes = vista.getDate_Mes().getMonth();
                                 String fecha = vista.getDate_Año().getYear() + "/" + ((mes + 1)) + "/01";
                                 int antiguedad = calcularAntiguedad(fecha, empleado.getIdEmpleado());
                                 linea.setMonto(((concepto.getPorcentaje() * cargo.getBasico() * antiguedad) / 100));
                                 totalHaberes += linea.getMonto();
                                 liquidacion.agregarLinea(linea);
                                 break;
                            default:
                                //PRIMERO ME FIJO SI EL CONCEPTO ES FIJO O NO
                                if (concepto.getFijo().equals("SI")) {
                                    //SI EL CONCEPTO ES FIJO ME PREGUNTO DE QUE TIPO ES: HABER - DEBE
                                    //SI EL CONCEPTO ES DE HABER, LE SUMO AL TOTAL DE HABERES
                                    if (concepto.getTipoConcepto().equals("Haber")) {
                                        linea.setConcepto(concepto);
                                        linea.setCantidad(1);
                                        linea.setMonto(concepto.getMonto());
                                        totalHaberes += linea.getMonto();
                                        liquidacion.agregarLinea(linea);
                                    }else{
                                    //SI NO ES DE HABER LE SUMO A LOS DESCUENTOS
                                        linea.setConcepto(concepto);
                                        linea.setCantidad(1);
                                        linea.setMonto(concepto.getMonto());
                                        totalDescuentos += linea.getMonto();
                                        liquidacion.agregarLinea(linea);
                                    }

                                //COMO EL CONCEPTO NO ES FIJO CALCULO EL MONTO, SEGUN SU PORCENTAJE
                                }else{
                                    //PREGUNTO SI EL CONCEPTO ES DEL TIPO HABER O DESCUENTO
                                    if (concepto.getTipoConcepto().equals("Haber")) {
                                        //SI ES DEL TIPO HABER LE SUMO AL TOTAL DE HABERES CALCULANDO
                                        linea.setConcepto(concepto);
                                        linea.setCantidad(1);
                                        linea.setMonto(((concepto.getPorcentaje() * cargo.getBasico()) / 100));
                                        totalHaberes += linea.getMonto();
                                        liquidacion.agregarLinea(linea);
                                    }else{
                                        linea.setConcepto(concepto);
                                        linea.setCantidad(1);
                                        linea.setMonto(((concepto.getPorcentaje() * cargo.getBasico()) / 100));
                                        totalDescuentos += linea.getMonto();
                                        liquidacion.agregarLinea(linea);
                                    }
                                }

                                 break; //FIN DEL SWITCH CASE
                            }


                        } //FIN DEL FOR DEL MODELO

                        //UNA VES OBTENIDO LOS TOTALES CALCULO LAS DIFERENCIAS
                        sueldoNeto = totalHaberes - totalDescuentos;

                        System.out.println("Total de Haberes: " + totalHaberes);
                        System.out.println("Total de Descuentos: " + totalDescuentos);
                        System.out.println("Sueldo Neto: " + sueldoNeto);

                        System.out.println();

                        //GRABO PRIMERO LA LIQUIDACION
                        liquidacion.setEmpleado(empleado);
                        liquidacion.setAño(vista.getDate_Año().getYear());
                        liquidacion.setMes((vista.getDate_Mes().getMonth() + 1));
                        liquidacion.setTotalHaberes(totalHaberes);
                        liquidacion.setTotalDescuentos(totalDescuentos);
                        liquidacion.setSueldoNeto(sueldoNeto);
                        liquidacion.grabarLiquidacion();

                        //TRAIGO EL ID DE LA ULTIMA LIQUIDACION Y LO SETEO
                        liquidacion.setIdLiquidacion(liquidacion.maxId());

                        //RECORRO LAS LINEAS DE LIQUIDACION Y LAS GRABO
                        for (int i = 0; i < liquidacion.getLinea().size(); i++) {
                            //SETEO LA LIQUIDACION PRIMERO
                            liquidacion.getLinea().get(i).setLiquidacion(liquidacion);
                            //GRABO LAS LINEAS DE LIQUIDACION
                            liquidacion.getLinea().get(i).grabarLineaLiquidacion();
                        }

                        ResultSet l = liquidacion.filtrarLiquidacionId();
                        while (l.next()) {
                            String idEmpleado = l.getString("idempleado");
                            String nombre = l.getString("empleado");
                            String idLiquidacion = l.getString("idliquidacion");
                            String periodo = l.getString("periodo");
                            String TotalDeHaberes = l.getString("totalHaberes");
                            String TotalDescuento = l.getString("totalDescuentos");
                            String sueldoN = l.getString("sueldoNeto");

                            String fila [] = {idEmpleado, nombre, idLiquidacion, periodo, TotalDeHaberes, TotalDescuento, sueldoN};
                            vista.getModeloLiquidacion().addRow(fila);
                            vista.getTabla_Liquidacion().setModel(vista.getModeloLiquidacion());
                         }

                        guardarReporte(fileChooser);

                        cantidadEmpleado ++;
                    }//FIN DEL CICLO WHILE

                JOptionPane.showMessageDialog(vista, "SE REALIZARON CON EXITO " + cantidadEmpleado + " LIQUIDACIONES DE SUELDO", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
                
                }
                    
                }else{
                    //SI NO QUIERE GENERAR LOS COMPROBANTES
                        //INSTANCIO UN OBJETO EMPLEADO PARA BUSCAR TODOS LOS EMPLEADOS DE LA BD
                        empleado = new Empleado();
                        //BUSCO LOS EMPLEADOS Y RECORRO
                        ResultSet e = empleado.listarEmpleadoActivo();
                        int cantidadEmpleado = 0;

                        JOptionPane.showMessageDialog(vista, "¡ESPERE UN MOMENTO POR FAVOR, \nÉSTE PROCESO PUEDE DEMORAR UNOS SEGUNDOS!", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);

                        while(e.next()){
                            //DEFINO LAS VARIABLES PARA REALIZAR LOS CALCULOS
                            double totalHaberes = 0;
                            double totalDescuentos = 0;
                            double sueldoNeto = 0;

                            empleado.setIdEmpleado(Integer.valueOf(e.getString("idempleado")));
                            empleado.setNombre(e.getString("apellido") + ", " + e.getString("nombre"));
                            System.out.println("EMPLEADO ID: " + empleado.getIdEmpleado());

                            //INSTANCIO UN OBJETO DEL TIPO FAMILIA, PARA SABER SI TIENE FAMILIARES
                            familia = new Familia();
                            familia.setEmpleado(empleado);
                            ResultSet r = familia.listarFamiliar();

                            //VARIABLES A UTILIZAR PARA CALCULAR LA SITUACION DEL GRUPO FAMILIAR
                            int matrimonio = 0;
                            int hijosDiscapacidad = 0;
                            int primaria = 0;
                            int secundaria = 0;
                            int universidad = 0;
                            while (r.next()) {     
                                String vinculo = r.getString("vinculo");
                                String discapacidad = r.getString("discapacidad");
                                String escolaridad = r.getString("escolaridad");

                                //PREGUNTO PRIMERO POR EL VINCULO
                                if (vinculo.equals("Esposo/a")) {
                                    matrimonio ++;
                                }else{
                                    if (vinculo.equals("Hijo/a")) {
                                        //SI ES UN HIJ@ PREGUNTO SI TIENE ESCOLARIDAD
                                        if (escolaridad.equals("Primaria")) {
                                            primaria ++;
                                        }

                                        if (escolaridad.equals("Secundaria")) {
                                            secundaria ++;
                                        }

                                        if (escolaridad.equals("Universidad")) {
                                            universidad ++;
                                        }

                                        if (discapacidad.equals("Si")) {
                                            hijosDiscapacidad ++;
                                        }
                                    }
                                }

                                System.out.println("Vinculo: " + vinculo + " - Escolaridad: " + escolaridad + " - Discapacidad: " + discapacidad);

                            }//FIN DEL CICLO WHILE




                            //POR CADA EMPLEADO BUSCO SU BASICO Y LE ASIGNO AL CARGO
                            cargo = new Cargo();
                            cargo.setBasico(Double.valueOf(e.getString("basico")));

                            System.out.println("BASICO DEL EMPLEADO: " + cargo.getBasico());

                            //POR CADA EMPLEADO REALIZO LA LIQUIDACION
                            liquidacion = new Liquidacion();

                            //RECORRO EL MODELO DE LA TABLA CONCENTO DE LA VISTA
                            for (int i = 0; i < vista.getModeloConcepto().getRowCount(); i++) {
                                //INSTANCION UN OBJETO DE TIPO CONCEPTO Y SETEO SUS VALORES CON LA TABLA
                                concepto = new Concepto();
                                concepto.setIdConcepto(Integer.valueOf(vista.getTabla_Conceptos().getModel().getValueAt(i, 0).toString()));
                                concepto.setDescripcion(vista.getTabla_Conceptos().getModel().getValueAt(i, 1).toString());
                                concepto.setTipoConcepto(vista.getTabla_Conceptos().getModel().getValueAt(i, 2).toString());
                                concepto.setFijo(vista.getTabla_Conceptos().getModel().getValueAt(i, 3).toString());
                                concepto.setPorcentaje(Double.valueOf(vista.getTabla_Conceptos().getModel().getValueAt(i, 4).toString()));
                                concepto.setMonto(Double.valueOf(vista.getTabla_Conceptos().getModel().getValueAt(i, 5).toString()));
                                //INSTANCIO UNA LINEA DE LIQUIDACION
                                linea = new LineaLiquidacion();

                                //EMPIEZO EL PROCESO PARA CALCULAR LOS MONTOS

                                switch (concepto.getIdConcepto()) {
                                //EL CONCEPTO BASICO NO LO TOMARE AQUI SINO EN EL DEFAULT DEBIDO A QUE ES OTRO TIPO DE CALCULO SIMPLE
                                case 2://EL ID 2 ES LA AYUDA POR PRIMARIA
                                     if (primaria > 0) {
                                         linea.setConcepto(concepto);
                                         linea.setCantidad(primaria);
                                         linea.setMonto(concepto.getMonto() * linea.getCantidad());
                                         totalHaberes += linea.getMonto();
                                         liquidacion.agregarLinea(linea);
                                     }
                                     break;
                                case 3://EL ID 3 ES LA AYUDA POR SECUNDARIA
                                     if (secundaria > 0) {
                                        linea.setConcepto(concepto);
                                        linea.setCantidad(secundaria);
                                        linea.setMonto(concepto.getMonto() * linea.getCantidad());
                                        totalHaberes += linea.getMonto();
                                        liquidacion.agregarLinea(linea);
                                     }
                                     break;
                                case 4://EL ID 4 ES LA AYUDA POR UNIVERSIDAD
                                     if (universidad > 0) {
                                        linea.setConcepto(concepto);
                                        linea.setCantidad(universidad);
                                        linea.setMonto(concepto.getMonto() * linea.getCantidad());
                                        totalHaberes += linea.getMonto();
                                        liquidacion.agregarLinea(linea);
                                     }
                                     break;
                                case 5://EL ID 5 ES DE MATRIMONIO
                                     if (matrimonio > 0) {
                                        linea.setConcepto(concepto);
                                        linea.setCantidad(matrimonio);
                                        linea.setMonto(concepto.getMonto() * linea.getCantidad());
                                        totalHaberes += linea.getMonto();
                                        liquidacion.agregarLinea(linea);
                                     }
                                     break;
                                case 6://EL ID 6 ES DE AYUDA POR HIJOS CON DISCAPACIDAD
                                     if (hijosDiscapacidad > 0) {
                                        linea.setConcepto(concepto);
                                        linea.setCantidad(hijosDiscapacidad);
                                        linea.setMonto(concepto.getMonto() * linea.getCantidad());
                                        totalHaberes += linea.getMonto();
                                        liquidacion.agregarLinea(linea);
                                     }
                                     break;
                                case 7:
                                     linea.setConcepto(concepto);
                                     linea.setCantidad(1);
                                     //CALCULO LA ANTIGUEDAD
                                     int mes = vista.getDate_Mes().getMonth();
                                     String fecha = vista.getDate_Año().getYear() + "/" + ((mes + 1)) + "/01";
                                     int antiguedad = calcularAntiguedad(fecha, empleado.getIdEmpleado());
                                     linea.setMonto(((concepto.getPorcentaje() * cargo.getBasico() * antiguedad) / 100));
                                     totalHaberes += linea.getMonto();
                                     liquidacion.agregarLinea(linea);
                                     break;
                                default:
                                    //PRIMERO ME FIJO SI EL CONCEPTO ES FIJO O NO
                                    if (concepto.getFijo().equals("SI")) {
                                        //SI EL CONCEPTO ES FIJO ME PREGUNTO DE QUE TIPO ES: HABER - DEBE
                                        //SI EL CONCEPTO ES DE HABER, LE SUMO AL TOTAL DE HABERES
                                        if (concepto.getTipoConcepto().equals("Haber")) {
                                            linea.setConcepto(concepto);
                                            linea.setCantidad(1);
                                            linea.setMonto(concepto.getMonto());
                                            totalHaberes += linea.getMonto();
                                            liquidacion.agregarLinea(linea);
                                        }else{
                                        //SI NO ES DE HABER LE SUMO A LOS DESCUENTOS
                                            linea.setConcepto(concepto);
                                            linea.setCantidad(1);
                                            linea.setMonto(concepto.getMonto());
                                            totalDescuentos += linea.getMonto();
                                            liquidacion.agregarLinea(linea);
                                        }

                                    //COMO EL CONCEPTO NO ES FIJO CALCULO EL MONTO, SEGUN SU PORCENTAJE
                                    }else{
                                        //PREGUNTO SI EL CONCEPTO ES DEL TIPO HABER O DESCUENTO
                                        if (concepto.getTipoConcepto().equals("Haber")) {
                                            //SI ES DEL TIPO HABER LE SUMO AL TOTAL DE HABERES CALCULANDO
                                            linea.setConcepto(concepto);
                                            linea.setCantidad(1);
                                            linea.setMonto(((concepto.getPorcentaje() * cargo.getBasico()) / 100));
                                            totalHaberes += linea.getMonto();
                                            liquidacion.agregarLinea(linea);
                                        }else{
                                            linea.setConcepto(concepto);
                                            linea.setCantidad(1);
                                            linea.setMonto(((concepto.getPorcentaje() * cargo.getBasico()) / 100));
                                            totalDescuentos += linea.getMonto();
                                            liquidacion.agregarLinea(linea);
                                        }
                                    }

                                     break; //FIN DEL SWITCH CASE
                                }


                            } //FIN DEL FOR DEL MODELO

                            //UNA VES OBTENIDO LOS TOTALES CALCULO LAS DIFERENCIAS
                            sueldoNeto = totalHaberes - totalDescuentos;

                            System.out.println("Total de Haberes: " + totalHaberes);
                            System.out.println("Total de Descuentos: " + totalDescuentos);
                            System.out.println("Sueldo Neto: " + sueldoNeto);

                            System.out.println();

                            //GRABO PRIMERO LA LIQUIDACION
                            liquidacion.setEmpleado(empleado);
                            liquidacion.setAño(vista.getDate_Año().getYear());
                            liquidacion.setMes((vista.getDate_Mes().getMonth() + 1));
                            liquidacion.setTotalHaberes(totalHaberes);
                            liquidacion.setTotalDescuentos(totalDescuentos);
                            liquidacion.setSueldoNeto(sueldoNeto);
                            liquidacion.grabarLiquidacion();

                            //TRAIGO EL ID DE LA ULTIMA LIQUIDACION Y LO SETEO
                            liquidacion.setIdLiquidacion(liquidacion.maxId());

                            //RECORRO LAS LINEAS DE LIQUIDACION Y LAS GRABO
                            for (int i = 0; i < liquidacion.getLinea().size(); i++) {
                                //SETEO LA LIQUIDACION PRIMERO
                                liquidacion.getLinea().get(i).setLiquidacion(liquidacion);
                                //GRABO LAS LINEAS DE LIQUIDACION
                                liquidacion.getLinea().get(i).grabarLineaLiquidacion();
                            }

                            ResultSet l = liquidacion.filtrarLiquidacionId();
                            while (l.next()) {
                                String idEmpleado = l.getString("idempleado");
                                String nombre = l.getString("empleado");
                                String idLiquidacion = l.getString("idliquidacion");
                                String periodo = l.getString("periodo");
                                String TotalDeHaberes = l.getString("totalHaberes");
                                String TotalDescuento = l.getString("totalDescuentos");
                                String sueldoN = l.getString("sueldoNeto");

                                String fila [] = {idEmpleado, nombre, idLiquidacion, periodo, TotalDeHaberes, TotalDescuento, sueldoN};
                                vista.getModeloLiquidacion().addRow(fila);
                                vista.getTabla_Liquidacion().setModel(vista.getModeloLiquidacion());
                             }

                            cantidadEmpleado ++;
                        }//FIN DEL CICLO WHILE

                    JOptionPane.showMessageDialog(vista, "SE REALIZARON CON EXITO " + cantidadEmpleado + " LIQUIDACIONES DE SUELDO", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
                }
                
                        
            }
        }
        
    }
    
    public static void CargarDetalleLiquidacion(LiquidacionTodos vista) throws SQLException{
        int fila = vista.getTabla_Liquidacion().getSelectedRow();
        if (fila > -1) {
            //LIMPIO EL MODELO DE DETALLE
            vista.getModeloDetalle().setColumnCount(0);
            vista.getModeloDetalle().setNumRows(0);
            vista.getModeloDetalle().addColumn("Concepto");
            vista.getModeloDetalle().addColumn("Tipo");
            vista.getModeloDetalle().addColumn("Fijo");
            vista.getModeloDetalle().addColumn("Porcentaje");
            vista.getModeloDetalle().addColumn("Monto");
            vista.getModeloDetalle().addColumn("Cantidad");
            vista.getModeloDetalle().addColumn("Total");
            vista.getTabla_Detalle().setModel(vista.getModeloDetalle());
            
            liquidacion = new Liquidacion();
            liquidacion.setIdLiquidacion(Integer.valueOf(vista.getTabla_Liquidacion().getModel().getValueAt(fila, 2).toString()));
            ResultSet r = liquidacion.filtrarDetalleLiquidacion();
            while (r.next()) {                
                String concept = r.getString("descripcion");
                String tipo = r.getString("tipoConcepto");
                String fijo = r.getString("fijo");
                String porcentaje = r.getString("porcentaje");
                String monto = r.getString("monto");
                String cantidad = r.getString("cantidad");
                String total = r.getString("total");
                
                String filaE[] = {concept, tipo, fijo, porcentaje, monto, cantidad, total};
                vista.getModeloDetalle().addRow(filaE);
                vista.getTabla_Detalle().setModel(vista.getModeloDetalle());
            }
        }
    }
    
    // METODOS PARA LISTAR LAS LIQUIDACIONES
    
    public static void listarEmpleadosLiquidacion(ListarLiquidaciones vista) throws SQLException{
        empleado = new Empleado();
        vista.getModeloEmpleado().setColumnCount(0);
        vista.getModeloEmpleado().setNumRows(0);
        vista.getModeloEmpleado().addColumn("Id");
        vista.getModeloEmpleado().addColumn("Nombre");
        vista.getModeloEmpleado().addColumn("DNI");
        vista.getModeloEmpleado().addColumn("Fecha de Ingreso");
        vista.getModeloEmpleado().addColumn("Cargo");
        vista.getModeloEmpleado().addColumn("Basico");
        ResultSet r = empleado.listarEmpleado();
        while (r.next()) {
            Object[] fila = new Object[6];
            fila[0] = r.getString("idempleado");
            fila[1] = r.getString("apellido") + "," + r.getString("nombre");
            fila[2] = r.getString("dni");
            fila[3] = r.getString("fechaIngreso");
            fila[4] = r.getString("cargo");
            fila[5] = r.getString("basico");

            vista.getModeloEmpleado().addRow(fila);
        }
        vista.getTabla_Empleados().setModel(vista.getModeloEmpleado());
        
    }
    
    public static void listarLiquidacionesDeEmpleado(ListarLiquidaciones vista) throws SQLException{
        int fila = vista.getTabla_Empleados().getSelectedRow();
        if (fila > -1) {
            empleado = new Empleado();
            empleado.setIdEmpleado(Integer.valueOf(vista.getTabla_Empleados().getModel().getValueAt(fila, 0).toString()));
            liquidacion = new Liquidacion();
            liquidacion.setEmpleado(empleado);
            
            vista.getModeloLiquidacion().setColumnCount(0);
            vista.getModeloLiquidacion().setNumRows(0);
            vista.getModeloLiquidacion().addColumn("Id Liquidación");
            vista.getModeloLiquidacion().addColumn("Periodo");
            vista.getModeloLiquidacion().addColumn("Total de Haberes");
            vista.getModeloLiquidacion().addColumn("Total de Descuentos");
            vista.getModeloLiquidacion().addColumn("Sueldo Neto");
            ResultSet r = liquidacion.filtrarLiquidacionEmpleadoId();
            while (r.next()) {                
                Object[] filaE = new Object[5];
                filaE[0] = r.getString("idliquidacion");
                filaE[1] = r.getString("periodo");
                filaE[2] = r.getString("totalHaberes");
                filaE[3] = r.getString("totalDescuentos");
                filaE[4] = r.getString("sueldoNeto");
            vista.getModeloLiquidacion().addRow(filaE);
            }
            
            vista.getTabla_Liquidacion().setModel(vista.getModeloLiquidacion());
            
            vista.getModeloDetalle().setRowCount(0);
        }
    }
    
    public static void CargarDetalleLiquidacion(ListarLiquidaciones vista) throws SQLException{
        int fila = vista.getTabla_Liquidacion().getSelectedRow();
        if (fila > -1) {
            //LIMPIO EL MODELO DE DETALLE
            vista.getModeloDetalle().setColumnCount(0);
            vista.getModeloDetalle().setNumRows(0);
            vista.getModeloDetalle().addColumn("Concepto");
            vista.getModeloDetalle().addColumn("Tipo");
            vista.getModeloDetalle().addColumn("Fijo");
            vista.getModeloDetalle().addColumn("Porcentaje");
            vista.getModeloDetalle().addColumn("Monto");
            vista.getModeloDetalle().addColumn("Cantidad");
            vista.getModeloDetalle().addColumn("Total");
            vista.getTabla_Detalle().setModel(vista.getModeloDetalle());
            
            liquidacion = new Liquidacion();
            liquidacion.setIdLiquidacion(Integer.valueOf(vista.getTabla_Liquidacion().getModel().getValueAt(fila, 0).toString()));
            ResultSet r = liquidacion.filtrarDetalleLiquidacion();
            while (r.next()) {                
                String concept = r.getString("descripcion");
                String tipo = r.getString("tipoConcepto");
                String fijo = r.getString("fijo");
                String porcentaje = r.getString("porcentaje");
                String monto = r.getString("monto");
                String cantidad = r.getString("cantidad");
                String total = r.getString("total");
                
                String filaE[] = {concept, tipo, fijo, porcentaje, monto, cantidad, total};
                vista.getModeloDetalle().addRow(filaE);
                vista.getTabla_Detalle().setModel(vista.getModeloDetalle());
            }
        }
    }
    
    public static void filtrarXempleado(ListarLiquidaciones vista, String cadena) throws SQLException{
        empleado = new Empleado();
        vista.getModeloEmpleado().setColumnCount(0);
        vista.getModeloEmpleado().setNumRows(0);
        vista.getModeloEmpleado().addColumn("Id");
        vista.getModeloEmpleado().addColumn("Nombre");
        vista.getModeloEmpleado().addColumn("DNI");
        vista.getModeloEmpleado().addColumn("Fecha de Ingreso");
        vista.getModeloEmpleado().addColumn("Cargo");
        vista.getModeloEmpleado().addColumn("Básico");
        ResultSet r = empleado.filtrarEmpleado(cadena);
        while (r.next()) {
            Object[] fila = new Object[6];
            fila[0] = r.getString("idempleado");
            fila[1] = r.getString("apellido") + "," + r.getString("nombre");
            fila[2] = r.getString("dni");
            fila[3] = r.getString("fechaIngreso");
            fila[4] = r.getString("cargo");
            fila[5] = r.getString("basico");
            vista.getModeloEmpleado().addRow(fila);
        }
        vista.getTabla_Empleados().setModel(vista.getModeloEmpleado());
    }
    
    public static void LimpiarPantalla(LiquidacionTodos vista){
        int opt = JOptionPane.showConfirmDialog(null, "¿ESTÁ USTED SEGURO DE LÍMPIAR TODOS LOS CONCEPTOS CARGADOS\nY LOS RESULTADOS OBTENIDOS?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (opt == JOptionPane.YES_OPTION) {
            vista.getModeloConcepto().setRowCount(0);
            vista.getModeloDetalle().setRowCount(0);
            vista.getModeloLiquidacion().setRowCount(0);
        }
    }
    
    //IMPRIMIR COMPROBANTE
    
    public static void exportarPDF(ListarLiquidaciones vista) throws SQLException, ClassNotFoundException{
        int fila = vista.getTabla_Liquidacion().getSelectedRow();
        if (fila > -1) {
            int id = Integer.valueOf(vista.getTabla_Liquidacion().getModel().getValueAt(fila, 0).toString());
            liquidacion = new Liquidacion();
            liquidacion.setIdLiquidacion(id);
            imprimirLiquidacion(liquidacion);
        }else{
            JOptionPane.showMessageDialog(null, "ERROR: DEBE SELECCIONAR UNA COMPRA!", "MENSAJE DE ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void imprimirLiquidacion(Liquidacion liquidacion) throws SQLException, ClassNotFoundException{
        int opt = JOptionPane.showConfirmDialog(null, "¿DESEA IMPRIMIR EL COMPROBANTE DE LIQUIDACIÓN?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opt == JOptionPane.YES_OPTION) {
        Class.forName("com.mysql.jdbc.Driver");
        //Creamos un enlace hacia la base de datos
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/sg_bar", "root", "35548988");
        //Metodo para Exportar
            try {
                String rutaInforme = "/home/fernando/NetBeansProjects/FinalGestionDeDatos/FinalGestionDeDatos2018/src/reportes/LiquidacionSueldo.jasper";
                Map parametros = new HashMap();
                //SETEO LOS PARAMETROS
                parametros.put("Id", liquidacion.getIdLiquidacion());
                //parametros.put("fecha", fecha);
                JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametros, conexion);
                JasperViewer ventanavisor = new JasperViewer(informe, false);
                JOptionPane.showMessageDialog(null, "ESTO PUEDE TARDAR UNOS SEGUNDOS,\nESPERE POR FAVOR", "ESTAMOS GENERANDO EL REPORTE", JOptionPane.WARNING_MESSAGE);
                JOptionPane.showMessageDialog(null, "DOCUMENTO EXPORTADO EXITOSAMENTE!", "GUARDADO EXITOSO!", JOptionPane.INFORMATION_MESSAGE);
                ventanavisor.setTitle("LIQUIDACIÓN DE SUELDO");
                ventanavisor.setExtendedState(Frame.MAXIMIZED_BOTH);
                ventanavisor.setVisible(true);
                ventanavisor.setLocationRelativeTo(null);
            } catch (Exception e) {//Catch del metodo exportar
                JOptionPane.showMessageDialog(null, "ERROR AL EXPORTAR DOCUMENTO");
            }//Fin de del metodo Exportar      
        }
    }
    
    public static void guardarReporte(JFileChooser fileChooser) throws ClassNotFoundException, SQLException, JRException, FileNotFoundException{
//        
//        //ABRIR CUADRO DE DIALOGO PARA GUARDAR EL ARCHIVO 
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("todos los archivos *.PDF", "pdf", "PDF"));//filtro para ver solo archivos .pdf
//        int seleccion = fileChooser.showSaveDialog(null);
//        try {
//            if (seleccion == JFileChooser.APPROVE_OPTION) {//comprueba si ha presionado el boton de aceptar
                Class.forName("com.mysql.jdbc.Driver");
                //Creamos un enlace hacia la base de datos
                Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/sg_bar", "root", "35548988");
                String rutaInforme = "/home/fernando/NetBeansProjects/FinalGestionDeDatos/FinalGestionDeDatos2018/src/reportes/LiquidacionSueldo.jasper";
                File JFC = fileChooser.getSelectedFile();
                String PATH = JFC.getAbsolutePath() +" - " + liquidacion.getIdLiquidacion();//obtenemos la direccion del archivo + el nombre a guardar
//                try (PrintWriter printwriter = new PrintWriter(JFC)) {
//                    printwriter.print(rutaInforme);
//                }
                
                Map parametros = new HashMap();
                //SETEO LOS PARAMETROS
                parametros.put("Id", liquidacion.getIdLiquidacion());
                //parametros.put("fecha", fecha);
                JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametros, conexion);
                JasperExportManager.exportReportToPdfFile(informe, PATH);
                JasperViewer ventanavisor = new JasperViewer(informe, false);
                //JOptionPane.showMessageDialog(null, "ESTO PUEDE TARDAR UNOS SEGUNDOS,\nESPERE POR FAVOR", "ESTAMOS GENERANDO EL REPORTE", JOptionPane.WARNING_MESSAGE);
                //JOptionPane.showMessageDialog(null, "DOCUMENTO EXPORTADO EXITOSAMENTE!", "GUARDADO EXITOSO!", JOptionPane.INFORMATION_MESSAGE);
                //ventanavisor.setTitle("LIQUIDACIÓN DE SUELDO");
                //ventanavisor.setExtendedState(Frame.MAXIMIZED_BOTH);
                //ventanavisor.setVisible(true);
                //ventanavisor.setLocationRelativeTo(null);
                
                
//                if (!(PATH.endsWith(".pdf"))) {
//                    File temp = new File(PATH + ".pdf");
//                    JFC.renameTo(temp);//renombramos el archivo
//                }
//            }
//        } catch (FileNotFoundException | HeadlessException e) {//por alguna excepcion salta un mensaje de error
//            JOptionPane.showMessageDialog(null, "Error al Exportar el archivo!", "Oops! Error", JOptionPane.ERROR_MESSAGE);
//        }
    
    }   

}