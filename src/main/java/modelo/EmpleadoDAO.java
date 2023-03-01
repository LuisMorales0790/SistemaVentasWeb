package modelo;

import beans.Empleado;
import com.google.gson.Gson;
import config.Conexion;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EmpleadoDAO implements IEmpleadoDAO{
    
    private static final String SQL_SELECT_EMPLEADOS = "SELECT * FROM empleado WHERE idEmpleado != ?";
    
    private static final String SQL_LOGIN = "SELECT * "
            + " FROM empleado WHERE user = ? AND dni = ?";
    
    private static final String SQL_GET_EMPLEADO = "SELECT * "
            + " FROM empleado WHERE idEmpleado = ?";
    
    private static final String SQL_INSERT_EMPLEADO = "INSERT INTO empleado (dni, nombres, telefono, estado, user) "
            + " VALUES(?, ?, ?, ?, ?)";
    
    private static final String SQL_UPDATE_EMPLEADO = "UPDATE empleado "
                + " SET dni=?, nombres=?, telefono=?, estado=?, user=? WHERE idEmpleado = ?";
    
    private static final String SQL_DELETE_EMPLEADO = "DELETE FROM empleado WHERE idEmpleado = ? ";
    
    @Override
    public String logueo(Empleado empleado) throws SQLException {
        System.out.println("EmpleadoDAO logueo");
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Gson gson = new Gson();    
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_LOGIN);
            stmt.setString(1, empleado.getUser());
            stmt.setString(2, empleado.getDni());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idEmpleado = rs.getInt("idEmpleado");
                String dni = rs.getString("dni");
                String nombres = rs.getString("nombres");
                String telefono = rs.getString("telefono");
                String estado = rs.getString("estado");
                String usuario = rs.getString("user");
                
                empleado.setIdEmpleado(idEmpleado);
                empleado.setDni(dni);
                empleado.setNombre(nombres);
                empleado.setTelefono(telefono);
                empleado.setEstado(estado);
                empleado.setUser(usuario);
                
                return gson.toJson(empleado);   
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
           Conexion.desconectar(rs, stmt, conn);
        }
        return "false";
    }

    @Override
    public String getUser(Empleado empleado) throws SQLException {
       // System.out.println("Empleado getUser" + empleado);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Gson gson = new Gson();    
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_GET_EMPLEADO);
            stmt.setInt(1, empleado.getIdEmpleado());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idEmpleado = rs.getInt("idEmpleado");
                String dni = rs.getString("dni");              
                String nombres = rs.getString("nombres");              
                String telefono = rs.getString("telefono");              
                String estado = rs.getString("estado");              
                String usuario = rs.getString("user");
                               
                empleado.setIdEmpleado(idEmpleado);
                empleado.setDni(dni);
                empleado.setNombre(nombres);
                empleado.setTelefono(telefono);
                empleado.setEstado(estado);
                empleado.setUser(usuario);
                
                return gson.toJson(empleado);   
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
           Conexion.desconectar(rs, stmt, conn);
        }
        return "false";
    }

    @Override
    public String listar(Empleado empleado){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //Empleado empleado = null;
        Gson gson = new Gson();   
        List<String> empleados = new ArrayList<String>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_EMPLEADOS);
            stmt.setInt(1, empleado.getIdEmpleado());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idEmpleado = rs.getInt("idEmpleado");
                String dni = rs.getString("dni");
                String nombres = rs.getString("nombres");
                String telefono = rs.getString("telefono");
                String estado = rs.getString("estado");
                String usuario = rs.getString("user");
                
                empleado = new Empleado(idEmpleado, dni, nombres, telefono, estado, usuario);               
                empleados.add(gson.toJson(empleado)); 
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
           Conexion.desconectar(rs, stmt, conn);
        }
        return gson.toJson(empleados);       
    }    

    @Override
    public int insertar(Empleado empleado) throws SQLException {
        System.out.println(" MODELO EmpleadoDAO");
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT_EMPLEADO);
            stmt.setString(1, empleado.getDni());
            stmt.setString(2, empleado.getNombre());
            stmt.setString(3, empleado.getTelefono());
            stmt.setString(4, empleado.getEstado());
            stmt.setString(5, empleado.getUser());
            
            rows = stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }        
        return rows;
    }

    @Override
    public int actualizar(Empleado empleado) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_EMPLEADO);
            stmt.setString(1, empleado.getDni());
            stmt.setString(2, empleado.getNombre());
            stmt.setString(3, empleado.getTelefono());
            stmt.setString(4, empleado.getEstado());
            stmt.setString(5, empleado.getUser());
            stmt.setInt(6, empleado.getIdEmpleado());
            
            rows = stmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return rows;
    }

    @Override
    public int eliminar(Empleado empleado) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE_EMPLEADO);
            stmt.setInt(1, empleado.getIdEmpleado());
            
            rows = stmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return rows;
    }
}
