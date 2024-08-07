package com.rubenrdc.manejoheadhttp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Ruben
 */
@WebServlet(name = "ManejoSesionesHTTP", urlPatterns = {"/api/ManejoSesionesHTTP"})
public class ManejoSesionesHTTP extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String content = """
                         <html>
                             <head>
                                 <title>Manejo de Sesion</title>
                                 <meta charset="UTF-8">
                                 <meta name="viewport" content="width=device-width, initial-scale=1.0">
                             </head>
                             <body>
                                 <a href="/ManejoGeneralHTTP/ManejoSesion" >Volver Atras</a>
                                 <h1>Manejo de Basico de Sesiones</h1>
                                 %s
                             </body>
                         </html>
                         """;
        //La sesion default dura 30 minutos de inactividad, si no se realiza peticiones despues de 30 minutos de la peticion anterior la sesion se resetea.
        HttpSession session = request.getSession();
        Boolean isRecurrentUser = (Boolean) session.getAttribute("isRecurrentUser");
        
        PrintWriter writer = response.getWriter();
        if (isRecurrentUser == null) {
            session.setAttribute("isRecurrentUser", true);
            writer.printf(content, "<p>Bienvenido Nuevo Usuario.</p>");
        } else {
            writer.printf(content, "<p>Bienvenido Nuevamente.</p>");
        }

    }
}
