package com.parking.prakinglot.servlets;

import com.parking.prakinglot.common.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.rmi.ServerException;
import java.rmi.server.ServerCloneException;
import java.util.List;

@WebServlet(name="Login", value="/Login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // NU trebuie să faci nimic aici când folosești FORM authentication
        // GlassFish se ocupă de tot prin j_security_check
        doGet(request, response);
    }
}

