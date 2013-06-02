/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import interfaces.Conectar;
import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author mauro
 */
public class Coneccion implements Conectar{
       private String base="jdbc:mysql://localhost/observatorio";//201.235.253.65:3306/maurodim_observatorio";
    private String stringDeConeccion;
    private String driver="com.mysql.jdbc.Driver";
    private String usuario="root";//maurodim";
    private String clave="";//mau*2012";

    @Override
    public Connection obtenerConeccionString() {
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

    @Override
    public Statement obtenerStatement(Connection cnn) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
