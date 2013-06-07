/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import interfaces.Conectar;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class ConeccionI implements Conectar{
         private String base="jdbc:mysql://201.235.253.65:3306/maurodim_net";
    private String stringDeConeccion;
    private String driver="com.mysql.jdbc.Driver";
    private String usuario="maurodim";
    private String clave="mau*2012";
    private static Connection cn=null;


    @Override
    public Connection obtenerConeccionString() {
        try {
            try {
                Class.forName(this.driver).newInstance();
            } catch (InstantiationException ex) {
                Logger.getLogger(Coneccion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Coneccion.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                cn=DriverManager.getConnection(this.base,this.usuario,this.clave);
            } catch (SQLException ex) {
                Logger.getLogger(Coneccion.class.getName()).log(Level.SEVERE, null, ex);
            }
            return cn;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Coneccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Statement obtenerStatement(Connection cnn) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean cerrarStatement(Statement st) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean cerrarConeccion(Connection cnn) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
