/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulos;

import config.Coneccion;
import interfaces.Archivar;
import interfaces.Conectar;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mauro
 * LA CLASE CONFIGURACIONES SE ENCARGA DE LOS PARAMETROS DEL SISTEMA
 * INCLUYE:
 * - CONECCIONES
 * - MODULOS HABILITADOS
 * -LLAVE DEL SISTEMA
 * 
 * 
 */
public class Configuraciones implements Archivar{
    private static String usuario;
    private static String fechaLlave;
    private static int numeroPermiso;
    private static Boolean moduloVentas;
    private static Boolean moduloCompras;
    private static Boolean moduloAdministracion;
    private static Boolean moduloFinanzas;
    private static Boolean moduloRecursosHumanos;

    public static String getFechaLlave() {
        return fechaLlave;
    }

    public static void setFechaLlave(String fechaLlave) {
        Configuraciones.fechaLlave = fechaLlave;
    }

    public static Boolean getModuloAdministracion() {
        return moduloAdministracion;
    }

    public static void setModuloAdministracion(Boolean moduloAdministracion) {
        Configuraciones.moduloAdministracion = moduloAdministracion;
    }

    public static Boolean getModuloCompras() {
        return moduloCompras;
    }

    public static void setModuloCompras(Boolean moduloCompras) {
        Configuraciones.moduloCompras = moduloCompras;
    }

    public static Boolean getModuloFinanzas() {
        return moduloFinanzas;
    }

    public static void setModuloFinanzas(Boolean moduloFinanzas) {
        Configuraciones.moduloFinanzas = moduloFinanzas;
    }

    public static Boolean getModuloRecursosHumanos() {
        return moduloRecursosHumanos;
    }

    public static void setModuloRecursosHumanos(Boolean moduloRecursosHumanos) {
        Configuraciones.moduloRecursosHumanos = moduloRecursosHumanos;
    }

    public static Boolean getModuloVentas() {
        return moduloVentas;
    }

    public static void setModuloVentas(Boolean moduloVentas) {
        Configuraciones.moduloVentas = moduloVentas;
    }

    public static int getNumeroPermiso() {
        return numeroPermiso;
    }

    public static void setNumeroPermiso(int numeroPermiso) {
        Configuraciones.numeroPermiso = numeroPermiso;
    }

    public static String getUsuario() {
        return usuario;
    }

    public static void setUsuario(String usuario) {
        Configuraciones.usuario = usuario;
    }
    
    

    @Override
    public Object leerArchivo(String rutaArchivo) {
        File fl=new File(rutaArchivo);
        BufferedReader entrada;
        Configuraciones config=new Configuraciones();
        try {
            entrada =new BufferedReader(new FileReader(fl));
            String linea="";
            int numer=0;
            while(entrada.ready()){
                linea=entrada.readLine();
                switch (numer){
                    case 0:
                    config.usuario=linea;
                    break;
                    case 1:
                    config.fechaLlave=linea;
                        break;
                    case 2:
                        config.numeroPermiso=Integer.parseInt(linea);
                        break;
                        
                }
                System.out.println(linea);
                numer++;
                Conectar con=new Coneccion();
                Connection ccn=con.obtenerConeccionString();
                Statement st=con.obtenerStatement(ccn);
                String sql="select * from coneccionesGestion where numero="+config.numeroPermiso;
                try {
                    st.execute(sql);
                    ResultSet rs=st.getResultSet();
                    while(rs.next()){
                        if(rs.getInt("moduloVentas")==1)config.moduloVentas=true;
                        if(rs.getInt("moduloCompras")==1)config.moduloCompras=true;
                        if(rs.getInt("moduloAdministracion")==1)config.moduloAdministracion=true;
                        if(rs.getInt("moduloFinanzas")==1)config.moduloFinanzas=true;
                        if(rs.getInt("moduloRecursosHumanos")==1)config.moduloRecursosHumanos=true;
                        
                    }
                    rs.close();
                    //st.close();
                    con.cerrarStatement(st);
                    con.cerrarConeccion(ccn);
                } catch (SQLException ex) {
                    Logger.getLogger(Configuraciones.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        } catch (IOException ex) {
            Logger.getLogger(Configuraciones.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return config;
    }
    
}
