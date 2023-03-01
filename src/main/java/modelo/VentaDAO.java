
package modelo;

import beans.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import config.Conexion;

public class VentaDAO implements IVentaDAO{
    
    private static final String SQL_GET_NUM_SERIE = "SELECT LPAD(IFNULL(MAX(numeroserie)+1,1),10,0) FROM ventas";
    private static final String SQL_GET_ID_VENTAS = "SELECT max(idVentas) FROM ventas";
    private static final String SQL_INSERT_VENTA = "INSERT INTO ventas(dni, idEmpleado, numeroSerie, monto, estado)values(?,?,?,?,?)";
    private static final String SQL_INSERT_DETALLE_VENTA = "INSERT INTO detalle_ventas(idVentas, idProducto, cantidad, precioVenta)values(?,?,?,?)";
    private static final String SQL_UPDATE_STOCK = "UPDATE producto SET stock=? WHERE idProducto= ?";
    
    @Override
    public String generarSerie(){
        System.out.println("MODELO generar Serie");
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String numSerie="";
        
        try {
            
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_GET_NUM_SERIE);
            rs = stmt.executeQuery();
            while(rs.next()){
                numSerie = rs.getString(1);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally{
            Conexion.desconectar(rs, stmt, conn);
        }      
        return numSerie;
    }

    @Override
    public String idVentas() throws SQLException {
        System.out.println("MODELO id Ventas");
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String idVentas="";
        
        try {
            
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_GET_ID_VENTAS);
            rs = stmt.executeQuery();
            while(rs.next()){
                idVentas = rs.getString(1);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally{
            Conexion.desconectar(rs, stmt, conn);
        }      
        return idVentas;
    }

    @Override
    public int guardarVenta(Venta venta) {
        System.out.println(" MODELO guardar Venta");
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT_VENTA);
            stmt.setString(1, venta.getIdCliente());
            stmt.setInt(2, venta.getIdEmpleado());
            stmt.setString(3, venta.getNumSerie());
           // stmt.setString(4, venta.getFechaVenta());
            stmt.setDouble(4, venta.getMontoVenta());
            stmt.setInt(5, venta.getEstadoVenta());
        
            rows = stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    @Override
    public int guardarDetalleVenta(Venta venta) {
        System.out.println(" MODELO guardar detalle Venta");
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT_DETALLE_VENTA);
            stmt.setInt(1, venta.getIdVenta());
            stmt.setInt(2, venta.getIdProducto());
            stmt.setInt(3, venta.getCantidad());
            stmt.setDouble(4, venta.getPrecioProd());
        
            rows = stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    @Override
    public int actualizarStock(Venta venta) {
        System.out.println("Actualizar Stock");
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_STOCK);
           stmt.setInt(1, venta.getStock());
           stmt.setInt(2, venta.getIdProducto());
            
           rows = stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }
    
    
}
