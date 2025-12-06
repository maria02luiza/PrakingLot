package com.parking.prakinglot.servlets;

import com.parking.prakinglot.common.CarDto;
import com.parking.prakinglot.ejb.CarsBean;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

@MultipartConfig
@WebServlet(name = "AddCarPhoto", value = "/AddCarPhoto")
public class AddCarPhoto extends HttpServlet {

    @Inject
    CarsBean carsBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long carId = Long.parseLong(request.getParameter("id"));
        CarDto car = carsBean.findById(carId);
        request.setAttribute("car", car);

        request.getRequestDispatcher("/WEB-INF/pages/addCarPhoto.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // IMPORTANT: Citește parametrii ÎNAINTE de a accesa Parts
        String carIdParam = request.getParameter("car_id");

        System.out.println("DEBUG - car_id parameter: '" + carIdParam + "'");

        if (carIdParam == null || carIdParam.trim().isEmpty()) {
            System.err.println("ERROR: car_id is null or empty");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing car_id parameter");
            return;
        }

        Long carId = Long.parseLong(carIdParam);

        Part filePart = request.getPart("file");

        if (filePart == null || filePart.getSize() == 0) {
            System.err.println("ERROR: No file uploaded");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No file selected");
            return;
        }

        String fileName = filePart.getSubmittedFileName();
        String fileType = filePart.getContentType();
        long fileSize = filePart.getSize();
        byte[] fileContent = new byte[(int) fileSize];
        filePart.getInputStream().read(fileContent);

        System.out.println("DEBUG - Uploading photo: " + fileName + " for car ID: " + carId);

        carsBean.addPhotoToCar(carId, fileName, fileType, fileContent);
        response.sendRedirect(request.getContextPath() + "/Cars");
    }
}