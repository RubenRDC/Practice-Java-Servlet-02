package com.rubenrdc.manejoheadhttp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ManejoCookiesHTTP", urlPatterns = {"/api/ManejoCookiesHTTP"})
public class ManejoCookiesHTTP extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String content = """
                         <html>
                             <head>
                                 <title>Manejo de Cookies</title>
                                 <meta charset="UTF-8">
                                 <meta name="viewport" content="width=device-width, initial-scale=1.0">
                             </head>
                             <body>
                                 <a href="/ManejoGeneralHTTP/ManejoCookies" >Volver Atras</a>
                                 <h1>Manejo de Basico de Cookies</h1>
                                 %s
                             </body>
                         </html>
                         """;
        
        response.setContentType("text/html;charset=UTF-8");
        Cookie[] cookies = request.getCookies();
        boolean isRecurrentUser = false;
        
        if (cookies != null) {
            for (Cookie cooky : cookies) {
                if (cooky.getName().equals("isRecurrentUser") && cooky.getValue().equals("true")) {
                    isRecurrentUser=true;
                    break;
                }
            }
        }
        PrintWriter writer = response.getWriter();
        if (isRecurrentUser) {
            writer.printf(content, "<p>Bienvenido Nuevamente.</p>");
        }else{
            Cookie cookie = new Cookie("isRecurrentUser","true");
            cookie.setMaxAge(864000);//Tiempo de expiracion 10 dias (864000s)
            response.addCookie(cookie);
            writer.printf(content, "<p>Bienvenido Nuevo Usuario.</p>");
        }
    }

}
