/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import interfaces.Archivar;
import modulos.Configuraciones;



/**
 *
 * @author mauro
 * LA CLASE MANAGER ES LA QUE ADMINISTRA LA CARGA Y ACCESO A LOS DISTINTOS MODULOS
 * LEE EL ARCHIVO DE CONFIGURACION Y SE CONECTA POR MEDIO DE ESOS PARAMETROS A LA BASE, TOMANDO EN CUENTA
 * QUE DE ACUERDO A LOS PERMISSOS Y LLAVE CUALES SON LOS MODULOS A LOS QUE ACCEDE EL USUARIO
 * - LLAMA A LA CLASE CONFIGURACIONES Y ALLI LE EL ARCHIVO CORRESPONDIENTE, DE ESTA FORMA CARGA LAS CONSTANTES NECESARIAS
 * 
 * 
 * 
 */
public class Manager{

    public Manager() {
        Archivar conf=new Configuraciones();
        Configuraciones conff=new Configuraciones();
        conff=(Configuraciones) conf.leerArchivo("src//archivos//Wfer.txt");
        
        System.out.println(conff.getNumeroPermiso()+" /ventas "+conff.getModuloVentas()+" /fecha "+conff.getFechaLlave());
        
    }
    

  
    
    
}
