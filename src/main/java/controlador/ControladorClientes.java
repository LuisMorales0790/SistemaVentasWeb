package controlador;

import beans.Cliente;
import beans.Empleado;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.ClienteDAO;

@WebServlet(name = "ControladorClientes", urlPatterns = {"/ControladorClientes"})
public class ControladorClientes extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorClientes</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorClientes at " + request.getContextPath() + "</h1>");
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
                    this.obtenerCliente(request, response);
                    break;
                case "eliminar":
                    this.eliminarCliente(request, response);
                    break;
                case "listar":
                    this.listarCliente(request, response);
                    break;
                case "dni_cliente":
                    this.dniCliente(request, response);
                    break;
                default:
                    this.listarCliente(request, response);
            }
        } else {
            this.listarCliente(request, response);
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
                    this.insertarCliente(request, response);
                    break;
                case "editar":
                    this.actualizarCliente(request, response);
                    break;
                default:
                    this.listarCliente(request, response);
            }
        } else {
            this.listarCliente(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void obtenerCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idCliente = Integer.parseInt(request.getParameter("idCliente"));
            System.out.println("el id cliente es: " + idCliente);
            Cliente cliente = new Cliente(idCliente);
            String clienteDAO = new ClienteDAO().getCliente(cliente);
            System.out.println("El cliente es: " + clienteDAO);

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println(clienteDAO);
            out.flush();
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idCliente = Integer.parseInt(request.getParameter("idCliente"));
            Cliente cliente = new Cliente(idCliente);
            int registrosModificados = new ClienteDAO().eliminar(cliente);
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

    private void listarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Entramos al controlador Clientes");
        String clientes = new ClienteDAO().listar();
        System.out.println("Clientes Ajax = " + clientes); //imprimir en consola el listado
       
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(clientes);
        out.flush();
        out.close();
    }

    private void insertarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          try {
            System.out.println("Servlet insertarCliente");
            String dni = request.getParameter("dni");
            String nombres = request.getParameter("nombres");
            String direccion = request.getParameter("direccion");
            String estado = request.getParameter("estado");

            //Empleado empleado = new Empleado(dni, nombres, telefono, estado, usuario);
            Cliente cliente = new Cliente(dni, nombres, direccion, estado);

            int registrosModificados = new ClienteDAO().insertar(cliente);
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

    private void actualizarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         try {
            int idCliente = Integer.parseInt(request.getParameter("idCliente"));
            String dni = request.getParameter("dni");
            String nombre = request.getParameter("nombre");
            String direccion = request.getParameter("direccion");
            String estado = request.getParameter("estado");

            Cliente cliente = new Cliente(idCliente, dni, nombre, direccion, estado);

            int registrosModificados = new ClienteDAO().actualizar(cliente);
            System.out.println("Registros modificados" + registrosModificados);
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println(registrosModificados);
            out.flush();
            out.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void dniCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         try {
            String dniCliente = request.getParameter("dni");
            System.out.println("el dniCliente es: " + dniCliente);
            Cliente cliente = new Cliente(dniCliente);
            String clienteDAO = new ClienteDAO().getClienteDNI(cliente);
            System.out.println("El dni cliente es: " + clienteDAO);

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println(clienteDAO);
            out.flush();
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
