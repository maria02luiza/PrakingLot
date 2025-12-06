package com.parking.prakinglot.servlets;

import com.parking.prakinglot.common.UserDto;
import com.parking.prakinglot.ejb.InvoiceBean;
import com.parking.prakinglot.ejb.UserBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@DeclareRoles({"READ_USERS", "WRITE_USERS"})
@ServletSecurity(
        value = @HttpConstraint(rolesAllowed = {"READ_USERS"}),
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "POST", rolesAllowed = {"WRITE_USERS"})
        }
)
@WebServlet(name = "Users", value = "/Users")
public class Users extends HttpServlet {

    @Inject
    UserBean usersBean;

    @Inject
    InvoiceBean invoiceBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<UserDto> users = usersBean.findAllUsers();
        request.setAttribute("users", users);

        if(!invoiceBean.getUserIds().isEmpty()){
            Collection<String> usernames = usersBean.findUsernamesByUserIds(invoiceBean.getUserIds());
            request.setAttribute("invoices", usernames);
        }
       request.setAttribute("activePage", "Users");

        request.getRequestDispatcher("/WEB-INF/pages/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] userIdsArray = request.getParameterValues("user_ids");

        if (userIdsArray != null) {
            Set<Long> userIds = new HashSet<>();
            for (String userId : userIdsArray) {
                userIds.add(Long.parseLong(userId));
            }

            invoiceBean.setUserIds(userIds);
        }

        response.sendRedirect(request.getContextPath() + "/Users");
    }
}
