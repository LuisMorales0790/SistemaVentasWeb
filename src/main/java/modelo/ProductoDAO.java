package modelo;

import beans.Producto;
import com.google.gson.Gson;
import config.Conexion;
import java.sql.*;
import java.util.*;

public class ProductoDAO implements IProductoDAO {

    private static final String SQL_SELECT_PRODUCTOS = "SELECT * FROM producto";

    private static final String SQL_GET_PRODUCTO = "SELECT * "
            + " FROM producto WHERE idProducto = ?";

    private static final String SQL_INSERT_PRODUCTO = "INSERT INTO producto (nombres, precio, stock, estado) "
            + " VALUES(?, ?, ?, ?)";

    private static final String SQL_UPDATE_PRODUCTO = "UPDATE producto "
            + " SET nombres=?, precio=?, stock=?, estado=? WHERE idProducto = ?";

    private static final String SQL_DELETE_PRODUCTO = "DELETE FROM producto WHERE idProducto =? ";

    @Override
    public String getProducto(Producto producto) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Gson gson = new Gson();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_GET_PRODUCTO);
            stmt.setInt(1, producto.getIdProducto());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idProducto = rs.getInt("idProducto");
                String nombres = rs.getString("nombres");
                Float precio = rs.getFloat("precio");
                int stock = rs.getInt("stock");
                String estado = rs.getString("estado");
                
                producto.setIdProducto(idProducto);
                producto.setNombres(nombres);
                producto.setPrecio(precio);
                producto.setStock(stock);
                producto.setEstado(estado);

                return gson.toJson(producto);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            Conexion.desconectar(rs, stmt, conn);
        }
        return "false";
    }

    @Override
    public String listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Producto producto = null;
        Gson gson = new Gson();
        List<String> productos = new ArrayList<String>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_PRODUCTOS);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idProducto = rs.getInt("idProducto");
                String nombres = rs.getString("nombres");
                Float precio = rs.getFloat("precio");
                int stock = rs.getInt("stock");
                String estado = rs.getString("estado");

                producto = new Producto(idProducto, nombres, precio, stock, estado);
                productos.add(gson.toJson(producto));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            Conexion.desconectar(rs, stmt, conn);
        }
        return gson.toJson(productos);
    }

    @Override
    public int insertar(Producto producto) throws SQLException {
        System.out.println(" MODELO ProductoDAO");
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT_PRODUCTO);
            stmt.setString(1, producto.getNombres());
            stmt.setFloat(2, producto.getPrecio());
            stmt.setInt(3, producto.getStock());
            stmt.setString(4, producto.getEstado());

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
    public int actualizar(Producto producto){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_PRODUCTO);
            stmt.setString(1, producto.getNombres());
            stmt.setFloat(2, producto.getPrecio());
            stmt.setInt(3, producto.getStock());
            stmt.setString(4, producto.getEstado());
            stmt.setInt(5, producto.getIdProducto());

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
    public int eliminar(Producto producto) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE_PRODUCTO);
            stmt.setInt(1, producto.getIdProducto());

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
