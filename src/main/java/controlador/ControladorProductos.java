package controlador;

import beans.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.ProductoDAO;

@WebServlet(name = "ControladorProductos", urlPatterns = {"/ControladorProductos"})
public class ControladorProductos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorProductos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorProductos at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        System.out.println("La accion es: " + accion);
        if (accion != null) {
            switch (accion) {
                case "editar":
                    this.obtenerProducto(request, response);
                    break;
                case "eliminar":
                    this.eliminarProducto(request, response);
                    break;
                case "listar":
                    this.listarProducto(request, response);
                    break;
                default:
                    this.listarProducto(request, response);
            }
        } else {
            this.listarProducto(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        System.out.println("La accion POST es: " + accion);
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    this.insertarProducto(request, response);
                    break;
                case "editar":
                    this.actualizarProducto(request, response);
                    break;
                default:
                    this.listarProducto(request, response);
            }
        } else {
            this.listarProducto(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void obtenerProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idProducto = Integer.parseInt(request.getParameter("idProducto"));
            System.out.println("el id producto es: " + idProducto);
            Producto producto = new Producto(idProducto);
            String productoDAO = new ProductoDAO().getProducto(producto);
            System.out.println("El producto es: " + productoDAO);

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println(productoDAO);
            out.flush();
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idProducto = Integer.parseInt(request.getParameter("idProducto"));
            Producto producto = new Producto(idProducto);
            int registrosModificados = new ProductoDAO().eliminar(producto);
            System.out.println("Registros Modificados" + registrosModificados);

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println(registrosModificados);
            out.flush();
            out.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void listarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Entramos al controlador Productos");
        String productos = new ProductoDAO().listar();
        System.out.println("Productos Ajax = " + productos); //imprimir en consola el listado
        HttpSession sesion = request.getSession(); //guardamos en una variable tipo sesion para que tenga mas alcance
        sesion.setAttribute("productos", productos);//compartir el listado de clientes a nivel de request y expresion language en clientes.jsp

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(productos);
        out.flush();
        out.close();
    }

    private void insertarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println("Servlet insertarProducto");
            String nombres = request.getParameter("nombres");
            Float precio = Float.parseFloat(request.getParameter("precio"));
            int stock = Integer.parseInt(request.getParameter("stock"));
            String estado = request.getParameter("estado");

            // Cliente cliente = new Cliente(dni, nombres, direccion, estado);
            Producto producto = new Producto(nombres, precio, stock, estado);

            int registrosModificados = new ProductoDAO().insertar(producto);
            System.out.println("Registros Modificados = " + registrosModificados);

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println(registrosModificados);
            out.flush();
            out.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        String nombre = request.getParameter("nombre");
        Float precio = Float.parseFloat(request.getParameter("precio"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        String estado = request.getParameter("estado");

        Producto producto = new Producto(idProducto, nombre, precio, stock, estado);
        int registrosModificados = new ProductoDAO().actualizar(producto);
        System.out.println("Registros modificados" + registrosModificados);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(registrosModificados);
        out.flush();
        out.close();
    }
}
