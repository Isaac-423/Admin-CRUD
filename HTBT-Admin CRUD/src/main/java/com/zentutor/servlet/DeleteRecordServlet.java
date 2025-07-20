package com.zentutor.servlet;

import com.zentutor.model.Admin;
import com.zentutor.utill.DataStorage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteRecordServlet extends HttpServlet {
    private DataStorage dataStorage;

    @Override
    public void init() throws ServletException {
        String filePath = "F:\\sliit\\1st year\\2 sem\\Object Oriented Programming - SE1020\\project\\Final real project\\HTBT-Admin CRUD\\src\\main\\webapp\\records.txt";
        this.dataStorage = new DataStorage(filePath);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Admin user = (Admin) request.getSession().getAttribute("user");
        if (user == null || !"Admin".equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/list");
            return;
        }

        //retrieve a specific parameter from an HTTP request
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            dataStorage.deleteRecord(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/");
    }
}