
package modelo;
import beans.Producto;
import java.sql.SQLException;

public interface IProductoDAO {
    public String getProducto(Producto producto) throws SQLException;
    public String listar() throws SQLException;
    public int insertar(Producto producto) throws SQLException;
    public int actualizar(Producto producto) throws  SQLException;
    public int eliminar(Producto producto) throws SQLException;
}
