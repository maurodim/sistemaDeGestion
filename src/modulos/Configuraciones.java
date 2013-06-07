/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulos;

import config.Coneccion;
import config.ConeccionI;
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
    private String usuario;
    private String fechaLlave;
    private int numeroPermiso;
    private Boolean moduloVentas;
    private Boolean moduloCompras;
    private Boolean moduloAdministracion;
    private Boolean moduloFinanzas;
    private Boolean moduloRecursosHumanos;

    public String getFechaLlave() {
        return fechaLlave;
    }

    public void setFechaLlave(String fechaLlave) {
        this.fechaLlave = fechaLlave;
    }

    public Boolean getModuloAdministracion() {
        return moduloAdministracion;
    }

    public void setModuloAdministracion(Boolean moduloAdministracion) {
        this.moduloAdministracion = moduloAdministracion;
    }

    public Boolean getModuloCompras() {
        return moduloCompras;
    }

    public void setModuloCompras(Boolean moduloCompras) {
        this.moduloCompras = moduloCompras;
    }

    public Boolean getModuloFinanzas() {
        return moduloFinanzas;
    }

    public void setModuloFinanzas(Boolean moduloFinanzas) {
        this.moduloFinanzas = moduloFinanzas;
    }

    public Boolean getModuloRecursosHumanos() {
        return moduloRecursosHumanos;
    }

    public void setModuloRecursosHumanos(Boolean moduloRecursosHumanos) {
        this.moduloRecursosHumanos = moduloRecursosHumanos;
    }

    public Boolean getModuloVentas() {
        return moduloVentas;
    }

    public void setModuloVentas(Boolean moduloVentas) {
        this.moduloVentas = moduloVentas;
    }

    public int getNumeroPermiso() {
        return numeroPermiso;
    }

    public void setNumeroPermiso(int numeroPermiso) {
        this.numeroPermiso = numeroPermiso;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
                    config.setUsuario(linea);
                    break;
                    case 1:
                    config.setFechaLlave(linea);
                        break;
                    case 2:
                        config.setNumeroPermiso(Integer.parseInt(linea));
                        break;
                        
                }
                System.out.println(linea);
                numer++;
                Conectar con=new ConeccionI();
                Connection ccn=con.obtenerConeccionString();
                Statement st=null;
                try {
                    st = ccn.createStatement();
                } catch (SQLException ex) {
                    Logger.getLogger(Configuraciones.class.getName()).log(Level.SEVERE, null, ex);
                }
                String sql="select * from coneccionesGestion where numero="+config.getNumeroPermiso();
                try {
                    st.execute(sql);
                    ResultSet rs=st.getResultSet();
                    while(rs.next()){
                        if(rs.getInt("moduloVentas")==1)config.setModuloVentas(true);
                        if(rs.getInt("moduloCompras")==1)config.setModuloCompras(true);
                        if(rs.getInt("moduloAdministracion")==1)config.setModuloAdministracion(true);
                        if(rs.getInt("moduloFinanzas")==1)config.setModuloFinanzas(true);
                        if(rs.getInt("moduloRecursosHumanos")==1)config.setModuloRecursosHumanos(true);
                        
                    }
                    rs.close();
                    st.close();
                    //con.cerrarStatement(st);
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
