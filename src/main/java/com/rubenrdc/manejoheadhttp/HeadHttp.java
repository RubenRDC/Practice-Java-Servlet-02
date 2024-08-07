/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rubenrdc.manejoheadhttp;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 *
 * @author Ruben
 */
@WebServlet(name = "HeadHttp", urlPatterns = {"/api/HeadHttp"})
public class HeadHttp extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        String remoteUser = request.getRemoteUser();
        Enumeration<String> headerNames = request.getHeaderNames();
        String headerNamesString = "";
        while (headerNames.hasMoreElements()) {
            String nextElement = headerNames.nextElement();

            headerNamesString += "<p>" + nextElement + " : " + request.getHeader(nextElement) + "</p>";
            headerNamesString += "\n";
        }
        String htmlContent = """
                  <html>
                      <head>
                          <title>HeadHttp</title>
                      </head>
                      <body>
                          <a href="/ManejoGeneralHTTP">Volver Al Home</a>
                          <h1>Propiedades HeadHttp</h1>
                          <h2>Metodo:</h2>
                          <p>%s</p>
                          <h2>Request URI:</h2>
                          <p>%s</p>
                          <h2>Usuario Remoto:</h2>
                          <p>%s</p>
                          <h2>HeaderNamesString:</h2>
                          %s
                      </body>
                  </html>
                  """;

        PrintWriter writer = response.getWriter();
        writer.printf(htmlContent, method, requestURI, remoteUser, headerNamesString);

    }
}
