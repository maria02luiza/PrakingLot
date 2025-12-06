package com.parking.prakinglot.servlets;

import com.parking.prakinglot.common.CarPhotoDto;
import com.parking.prakinglot.ejb.CarsBean;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="CarPhotos", value="/CarPhotos")
public class CarPhoto extends HttpServlet {
    @Inject
    CarsBean carsBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Integer carId=Integer.parseInt(req.getParameter("id"));
        CarPhotoDto photo=carsBean.findPhotoByCarId(carId);
        if(photo!=null){
            resp.setContentType(photo.getFileType());
            resp.setContentLength(photo.getFileContent().length);
            resp.getOutputStream().write(photo.getFileContent());
        }else{
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
