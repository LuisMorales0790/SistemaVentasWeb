/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import beans.Empleado;

import java.sql.SQLException;

public interface IEmpleadoDAO {
    
    public String logueo(Empleado empleado) throws SQLException;
    public String getUser(Empleado empleado) throws SQLException;
    public String listar(Empleado empleado) throws SQLException;
    public int insertar(Empleado empleado) throws SQLException;
    public int actualizar(Empleado empleado) throws  SQLException;
    public int eliminar(Empleado empleado) throws SQLException;
}
