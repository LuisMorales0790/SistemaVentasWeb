package beans;

public class Venta {
    
    private int idVenta;
    private int item;
    private String idCliente;
    private int idEmpleado;
    private int idProducto;
    private int cantidad;
    private int stock;
    private String numSerie;
    private String descripProd;
    private String fechaVenta;
    private int estadoVenta;
    private double precioProd; 
    private double subTotal;
    private double montoVenta;

    public Venta() {
    }
    
    
    public Venta(int idVenta, int idProducto, int cantidad, double precioProd) {
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioProd = precioProd;
    }

    public Venta(String idCliente, int idEmpleado, String numSerie, double montoVenta, int estadoVenta) {
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.numSerie = numSerie;
        this.montoVenta = montoVenta;
        this.estadoVenta = estadoVenta;
    }

    public Venta(int idProducto, int stock) {
        this.idProducto = idProducto;
        this.stock = stock;
    }

    public Venta(int idVenta, int item, String idCliente, int idEmpleado, int idProducto, int cantidad, String numSerie, String descripProd, String fechaVenta, int estadoVenta, double precioProd, double subTotal, double montoVenta) {
        this.idVenta = idVenta;
        this.item = item;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.numSerie = numSerie;
        this.descripProd = descripProd;
        this.fechaVenta = fechaVenta;
        this.estadoVenta = estadoVenta;
        this.precioProd = precioProd;
        this.subTotal = subTotal;
        this.montoVenta = montoVenta;
    }

    public Venta(int item, int idProducto, int cantidad, String descripProd, double precioProd, double subTotal) {
        this.item = item;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.descripProd = descripProd;
        this.precioProd = precioProd;
        this.subTotal = subTotal;
    }
    
    
    

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public String getDescripProd() {
        return descripProd;
    }

    public void setDescripProd(String descripProd) {
        this.descripProd = descripProd;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getEstadoVenta() {
        return estadoVenta;
    }

    public void setEstadoVenta(int estadoVenta) {
        this.estadoVenta = estadoVenta;
    }

    public double getPrecioProd() {
        return precioProd;
    }

    public void setPrecioProd(double precioProd) {
        this.precioProd = precioProd;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getMontoVenta() {
        return montoVenta;
    }

    public void setMontoVenta(double montoVenta) {
        this.montoVenta = montoVenta;
    }  
    
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
