
package modelo;
import beans.Cliente;

import java.sql.SQLException;


public interface IClienteDAO {
    public String getCliente(Cliente cliente) throws SQLException;
    public String getClienteDNI(Cliente cliente) throws SQLException;
    public String listar() throws SQLException;
    public int insertar(Cliente cliente) throws SQLException;
    public int actualizar(Cliente cliente) throws  SQLException;
    public int eliminar(Cliente cliente) throws SQLException;
}
