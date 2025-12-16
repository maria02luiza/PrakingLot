package com.parking.prakinglot.servlets.cars;

import com.parking.prakinglot.common.CarDto;
import com.parking.prakinglot.ejb.CarsBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@DeclareRoles({"READ_CARS", "WRITE_CARS"})
@ServletSecurity(
        value = @HttpConstraint(rolesAllowed = {"READ_CARS"}),
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "POST", rolesAllowed = {"WRITE_CARS"})
        }
)
@WebServlet(name = "Cars", value = "/Cars")
public class Cars extends HttpServlet {

    @Inject
    CarsBean carsBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CarDto> cars = carsBean.findAllCars();
        request.setAttribute("cars", cars);

        // Calculează numărul de locuri libere: 10 - numărul de mașini
        int totalSpots = 10;
        int carsCount = carsBean.countCars();
        int freeParkingSpots = totalSpots - carsCount;

        request.setAttribute("numberOfFreeParkingSpots", freeParkingSpots);
        request.setAttribute("activePage", "Cars");

        request.getRequestDispatcher("/WEB-INF/pages/cars/cars.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] carIdsStr = request.getParameterValues("car_ids");
        if (carIdsStr != null) {
            List<Long> carIds = new ArrayList<>();
            for (String carIdStr : carIdsStr) {
                carIds.add(Long.parseLong(carIdStr));
            }
            carsBean.deleteCarsByIds(carIds);
        }

        response.sendRedirect(request.getContextPath() + "/Cars");
    }
}