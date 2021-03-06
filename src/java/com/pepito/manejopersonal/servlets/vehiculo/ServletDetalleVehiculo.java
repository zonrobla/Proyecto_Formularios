package com.pepito.manejopersonal.servlets.vehiculo;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import com.pepito.manejopersonal.persistencia.vehiculo.ConexionVehiculo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
@WebServlet(name = "Detalle", urlPatterns = {"/Detalle"})
public class ServletDetalleVehiculo extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String IDVehiculo = request.getParameter(ServletCapturaVehiculo.ID_VEHICULO);
        ConexionVehiculo c = new ConexionVehiculo();
        PrintWriter out = response.getWriter();
        ResultSet rs = null;
        
        try(Connection con = c.getConexion();
                
            PreparedStatement st = con.prepareStatement("SELECT * FROM VEHICULO WHERE IDVEHICULO = ?");) {
            st.setString(1, IDVehiculo);
            rs = st.executeQuery(); 
            
            if(rs.next()) { 
               
                out.println("<html>");
                out.println("<head>");  
                out.println("<title>Captura de datos</title>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<meta name='viewport' content=\"width=device-width, initial-scale=1.0\">");
                out.println("</head>");
                out.println("<body>");
                out.println(" <form action=\"ServletCapturaVehiculo\" method=\"POST\">");
                out.println("Por favor ingrese ID del vehiculo :");
                out.println("<input type=\"number\" name=\"IDVehiculo\" value='" + IDVehiculo + "' "
                        + "disabled=true' ><br>");
                out.println("Por favor ingrese modelo :");
                out.println("<input type=\"text\" name=\"modelo\" value='" + rs.getString("MODELO") + "' "
                        + "disabled=true'  size=\"40\"><br>");
                out.println("Por favor ingrese marca :");
                out.println("<input type=\"text\" name=\"marca\" value='" + rs.getString("MARCA") + "' "
                        + "disabled=true'  size=\"40\"><br>");
                out.println("Por favor seleccione el numero de puertas :");
                out.println("<input type=\"text\" name=\"numero puertas\" value='" + rs.getShort("NUMEROPUERTAS") + "' "
                        + "disabled=true'>");
                out.println("Por favor ingrese placa :");
                out.println("<input type=\"text\" name=\"placa\" value='" + rs.getString("PLACA") + "' " 
                        + "disabled=true' size=\"40\"><br>");
                out.println("Por favor seleccione color:");
                out.println("<input type=\"text\" name=\"color\" value='" + rs.getString("COLOR") + "' "
                        + "disabled=true'>");        
                out.println("</form>");
                out.println("</<body>");
                out.println("</html>");

                st.close();
            }
            
        } catch (SQLException e) {
                    
            e.printStackTrace();
        } finally {
            
            if (rs != null) {
                
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServletDetalleVehiculo.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }                
        
    }

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
    