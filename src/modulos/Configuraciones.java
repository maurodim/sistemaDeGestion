/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulos;

import interfaces.Archivar;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
                
            }
        } catch (IOException ex) {
            Logger.getLogger(Configuraciones.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return config;
    }
    
}
