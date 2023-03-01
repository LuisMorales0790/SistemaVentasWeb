package modelo;

import beans.Venta;
import java.sql.SQLException;

public interface IVentaDAO {
    
    public String generarSerie() throws SQLException;
    public String idVentas() throws SQLException;
    public int guardarVenta(Venta venta);
    public int guardarDetalleVenta(Venta venta);
    public int actualizarStock(Venta venta);
}
