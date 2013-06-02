/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author mauro
 */
public interface Conectar {
    public Connection obtenerConeccionString();
    public Statement obtenerStatement(Connection cnn);
    public Boolean cerrarStatement(Statement st);
    public Boolean cerrarConeccion(Connection cnn);
}
