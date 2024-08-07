package com.rubenrdc.manejoheadhttp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Ruben
 */
@WebServlet(name = "CodeHttp", urlPatterns = {"/api/CodeHttp"})
public class ManejoCodeHTTP extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String UserOK = "Ruben";
        String PassOk = "1234";
        
        String user = request.getParameter("usuario");
        String password = request.getParameter("password");

        if (UserOK.equals(user) & PassOk.equals(password)) {
            String htmlContent = """
                  <html>
                      <head>
                          <title>HeadHttp</title>
                      </head>
                      <body>
                          <a href="/ManejoGeneralHTTP">Volver Al Home</a>
                          <h2>Usuario:</h2>
                          <p>%s</p>
                          <h2>Contraseña</h2>
                          <p>%s</p>
                      </body>
                  </html>
                  """;

            PrintWriter writer = response.getWriter();
            writer.printf(htmlContent, user, password);
        }else{
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Credenciales Incorrectos.");
        }
    }
}
