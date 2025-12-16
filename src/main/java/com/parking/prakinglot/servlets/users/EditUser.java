package com.parking.prakinglot.servlets.users;

import com.parking.prakinglot.common.UserDto;
import com.parking.prakinglot.ejb.UserBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

@DeclareRoles({"WRITE_USERS"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"WRITE_USERS"}))
@WebServlet(name = "EditUser", value = "/EditUser")
public class EditUser extends HttpServlet {

    @Inject
    UserBean userBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long userId = Long.parseLong(request.getParameter("id"));
        UserDto user = userBean.findById(userId);
        request.setAttribute("user", user);

        // Preia grupurile utilizatorului
        Collection<String> userGroups = userBean.findUserGroupsByUserId(userId);
        request.setAttribute("userGroups", userGroups);

        request.getRequestDispatcher("/WEB-INF/pages/users/editUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long userId = Long.parseLong(request.getParameter("user_id"));
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String[] userGroupsArray = request.getParameterValues("user_groups");

        // Convertește array-ul în colecție (sau listă goală dacă este null)
        Collection<String> userGroups = userGroupsArray != null ? Arrays.asList(userGroupsArray) : Arrays.asList();

        // Actualizează utilizatorul - password poate fi null/empty
        userBean.updateUser(userId, username, email, password, userGroups);

        response.sendRedirect(request.getContextPath() + "/Users");
    }
}