package beans;

public class Cliente {
    private int idCliente;
    private String dni;
    private String nombres;
    private String direccion;
    private String estado;

    public Cliente() {
    }

    public Cliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente(String dni) {
        this.dni = dni;
    }
    
    public Cliente(String dni, String nombres, String direccion, String estado) {
        this.dni = dni;
        this.nombres = nombres;
        this.direccion = direccion;
        this.estado = estado;
    }

    public Cliente(int idCliente, String dni, String nombres, String direccion, String estado) {
        this.idCliente = idCliente;
        this.dni = dni;
        this.nombres = nombres;
        this.direccion = direccion;
        this.estado = estado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", dni=" + dni + ", nombres=" + nombres + ", direccion=" + direccion + ", estado=" + estado + '}';
    }
}
