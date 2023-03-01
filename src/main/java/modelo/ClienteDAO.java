package modelo;

import beans.Cliente;
import com.google.gson.Gson;
import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IClienteDAO {

    private static final String SQL_SELECT_CLIENTES = "SELECT * FROM cliente";

    private static final String SQL_GET_CLIENTE = "SELECT * "
            + " FROM cliente WHERE idCliente = ? ";

    private static final String SQL_GET_CLIENTE_DNI = "SELECT * "
            + " FROM cliente WHERE dni = ? ";

    private static final String SQL_INSERT_CLIENTE = "INSERT INTO cliente (dni, nombres, direccion, estado) "
            + " VALUES(?, ?, ?, ?)";

    private static final String SQL_UPDATE_CLIENTE = "UPDATE cliente "
            + " SET dni=?, nombres=?, direccion=?, estado=? WHERE idCliente = ?";

    private static final String SQL_DELETE_CLIENTE = "DELETE FROM cliente WHERE idCliente = ? ";

    @Override
    public String getCliente(Cliente cliente) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Gson gson = new Gson();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_GET_CLIENTE);
            stmt.setInt(1, cliente.getIdCliente());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idCliente = rs.getInt("idCliente");
                String dni = rs.getString("dni");
                String nombres = rs.getString("nombres");
                String direccion = rs.getString("direccion");
                String estado = rs.getString("estado");

                cliente.setIdCliente(idCliente);
                cliente.setDni(dni);
                cliente.setNombres(nombres);
                cliente.setDireccion(direccion);
                cliente.setEstado(estado);

                return gson.toJson(cliente);
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
        Cliente cliente = null;
        Gson gson = new Gson();
        List<String> clientes = new ArrayList<String>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_CLIENTES);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idCliente = rs.getInt("idCliente");
                String dni = rs.getString("dni");
                String nombres = rs.getString("nombres");
                String direccion = rs.getString("direccion");
                String estado = rs.getString("estado");

                cliente = new Cliente(idCliente, dni, nombres, direccion, estado);
                clientes.add(gson.toJson(cliente));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            Conexion.desconectar(rs, stmt, conn);
        }
        return gson.toJson(clientes);
    }

    @Override
    public int insertar(Cliente cliente) throws SQLException {
        System.out.println(" MODELO ClienteDAO");
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT_CLIENTE);
            stmt.setString(1, cliente.getDni());
            stmt.setString(2, cliente.getNombres());
            stmt.setString(3, cliente.getDireccion());
            stmt.setString(4, cliente.getEstado());

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
    public int actualizar(Cliente cliente) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_CLIENTE);
            stmt.setString(1, cliente.getDni());
            stmt.setString(2, cliente.getNombres());
            stmt.setString(3, cliente.getDireccion());
            stmt.setString(4, cliente.getEstado());
            stmt.setInt(5, cliente.getIdCliente());

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
    public int eliminar(Cliente cliente) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE_CLIENTE);
            stmt.setInt(1, cliente.getIdCliente());

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
    public String getClienteDNI(Cliente cliente) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Gson gson = new Gson();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_GET_CLIENTE_DNI);
            stmt.setString(1, cliente.getDni());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idCliente = rs.getInt("idCliente");
                String dni = rs.getString("dni");
                String nombres = rs.getString("nombres");
                String direccion = rs.getString("direccion");
                String estado = rs.getString("estado");

                cliente.setIdCliente(idCliente);
                cliente.setDni(dni);
                cliente.setNombres(nombres);
                cliente.setDireccion(direccion);
                cliente.setEstado(estado);

                return gson.toJson(cliente);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            Conexion.desconectar(rs, stmt, conn);
        }
        return "false";
    }
}
