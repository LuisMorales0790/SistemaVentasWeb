package controlador;

import beans.Empleado;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.EmpleadoDAO;

@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {
  
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controlador</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controlador at " + request.getContextPath() + "</h1>");
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
                case "validar":
                    this.validarEmpleado(request, response);
                    break;
                case "editar":
                    this.obtenerEmpleado(request, response);
                    break;
                case "eliminar":
                    this.eliminarEmpleado(request, response);
                    break;
                case "listar":
                    this.listarEmpleado(request, response);
                    break;
                default:
                    this.listarEmpleado(request, response);
            }
        } else {
            this.listarEmpleado(request, response);
        }
    }

    private void validarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idempleado = Integer.parseInt(request.getParameter("idempleado"));
            Empleado empleado = new Empleado(idempleado);
            String empleadoDAO = new EmpleadoDAO().getUser(empleado);
            
            HttpSession sesion = request.getSession(); //guardamos en una variable tipo sesion para que tenga mas alcance
            sesion.setAttribute("empleadoDAO", empleadoDAO);//compartir el listado de clientes a nivel de request y expresion language en clientes.jsp
            sesion.setAttribute("idempleado", idempleado);
            
            String userSesion = (String) sesion.getAttribute("empleadoDAO");
            int idUserSesion = (int) sesion.getAttribute("idempleado");
                  
            System.out.println("El empleado logueado en sesion es: " + userSesion);
            System.out.println("El empleado logueado en sesion es: " + idUserSesion);

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println(empleadoDAO);
            out.flush();
            out.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private void obtenerEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
            System.out.println("el id empleado es: " + idEmpleado);
            Empleado empleado = new Empleado(idEmpleado);
            String empleadoDAO = new EmpleadoDAO().getUser(empleado);
            System.out.println("El empleado es: " + empleadoDAO);

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println(empleadoDAO);
            out.flush();
            out.close();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private void eliminarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
            Empleado empleado = new Empleado(idEmpleado);
            int registrosModificados = new EmpleadoDAO().eliminar(empleado);
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

    private void listarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Entramos al controlador");
        HttpSession sesion = request.getSession();
        int idEmpleadoSesion = (int) sesion.getAttribute("idempleado");
        Empleado empleado = new Empleado(idEmpleadoSesion);
        
        String empleados = new EmpleadoDAO().listar(empleado);
        System.out.println("Empleados Ajax = " + empleados); //imprimir en consola el listado
       
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(empleados);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        System.out.println("La accion POST es: " + accion);
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    this.insertarEmpleado(request, response);
                    break;
                case "editar":
                    this.actualizarEmpleado(request, response);
                    break;
                default:
                    this.listarEmpleado(request, response);
            }
        } else {
            this.listarEmpleado(request, response);
        }
    }

    private void actualizarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
            String dni = request.getParameter("dni");
            String nombre = request.getParameter("nombre");
            String telefono = request.getParameter("telefono");
            String estado = request.getParameter("estado");
            String usuario = request.getParameter("usuario");

            Empleado empleado = new Empleado(idEmpleado, dni, nombre, telefono, estado, usuario);

            int registrosModificados = new EmpleadoDAO().actualizar(empleado);
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

    private void insertarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println("Servlet insertarEmpleado");
            String dni = request.getParameter("dni");
            String nombres = request.getParameter("nombres");
            String telefono = request.getParameter("telefono");
            String estado = request.getParameter("estado");
            String usuario = request.getParameter("usuario");

            Empleado empleado = new Empleado(dni, nombres, telefono, estado, usuario);

            int registrosModificados = new EmpleadoDAO().insertar(empleado);
            System.out.println("Registros Modificados = " + registrosModificados);

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println(registrosModificados);
            out.flush();
            out.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
