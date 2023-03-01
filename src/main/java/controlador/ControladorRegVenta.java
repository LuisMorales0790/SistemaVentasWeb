package controlador;

import beans.Venta;
import modelo.VentaDAO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ControladorRegVenta", urlPatterns = {"/ControladorRegVenta"})
public class ControladorRegVenta extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorRegVenta</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorRegVenta at " + request.getContextPath() + "</h1>");
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
                case "numserie":
                    this.obtenerNumSerie(request, response);
                    break;
                default:
                    this.obtenerNumSerie(request, response);
            }
        } else {
            this.obtenerNumSerie(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        System.out.println("La accion POST es: " + accion);
        if (accion != null) {
            switch (accion) {
                case "generarVenta":
                    this.registrarVenta(request, response);
                    break;
                case "editar":
                    //   this.actualizarProducto(request, response);
                    break;
                default:
                //   this.listarProducto(request, response);
            }
        } else {
            // this.listarProducto(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void registrarVenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println("GENERAR VENTA");
            // VentaDAO ventaDAO = null;
            List<String> listaVenta = new ArrayList<String>();
            Gson gson = new Gson();
            
            HttpSession sesion = request.getSession();
            int idEmpleadoSesion = (int) sesion.getAttribute("idempleado");
            
            String dni = request.getParameter("dni");
            String lista = request.getParameter("productList");
            System.out.println("lista" + lista);

           // int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
            int idEmpleado = idEmpleadoSesion;
            String numeroSerie = request.getParameter("numSerie");
            Float totalPagar = Float.parseFloat(request.getParameter("totalPagar"));
            int estado = Integer.parseInt(request.getParameter("estado"));

            Venta venta = new Venta(dni, idEmpleado, numeroSerie, totalPagar, estado);

            new VentaDAO().guardarVenta(venta);

            //Guardar Detalle ventas
            
            int idVenta = Integer.parseInt(new VentaDAO().idVentas());

            JsonArray ja = new JsonParser().parse(request.getParameter("productList")).getAsJsonArray();
            for (JsonElement je : ja) {
                
                int codigoProducto = Integer.parseInt(je.getAsJsonObject().get("codigoProducto").toString().replaceAll("\"", ""));
                String nombreProducto = je.getAsJsonObject().get("nombreProducto").toString().replaceAll("\"", "");
                Float precioProducto = Float.parseFloat(je.getAsJsonObject().get("precioProducto").toString().replaceAll("\"", ""));
                int cantidadProducto = Integer.parseInt(je.getAsJsonObject().get("cantidadProducto").toString().replaceAll("\"", ""));
                int stock = Integer.parseInt(je.getAsJsonObject().get("stock").toString().replaceAll("\"", ""));
                int nuevoStock = stock - cantidadProducto;
                
                Venta updateStock = new Venta(codigoProducto, nuevoStock);
                Venta detalleVenta = new Venta(idVenta, codigoProducto, cantidadProducto, precioProducto);
                
                new VentaDAO().actualizarStock(updateStock);
                int registrosModificados = new VentaDAO().guardarDetalleVenta(detalleVenta);

                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println(registrosModificados);
                out.flush();
                out.close();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void obtenerNumSerie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            System.out.println("Entramos al controlador regVentas");
            String numSerie = null;
            int nSerie;
            nSerie = Integer.parseInt(new VentaDAO().generarSerie());
            numSerie = String.format("%08d", nSerie);
            HttpSession sesion = request.getSession();
            sesion.setAttribute("clientes", numSerie);

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println(numSerie);
            out.flush();
            out.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
