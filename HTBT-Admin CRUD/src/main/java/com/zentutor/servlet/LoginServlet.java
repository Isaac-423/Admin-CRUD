package com.zentutor.servlet;

import com.zentutor.model.Admin;
import com.zentutor.utill.DataStorage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private DataStorage dataStorage;

    @Override
    public void init() throws ServletException {
        String filePath = "F:\\sliit\\1st year\\2 sem\\Object Oriented Programming - SE1020\\project\\Final real project\\HTBT-Admin CRUD\\src\\main\\webapp\\records.txt"; // abs path
        this.dataStorage = new DataStorage(filePath);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        List<Admin> records = dataStorage.getAllRecords();

        for (Admin record : records) {
            if (record.getEmail().equals(email) && record.getPassword().equals(password)) {
                request.getSession().setAttribute("user", record);
                response.sendRedirect(request.getContextPath() + "/list");
                return;
            }
        }

        request.setAttribute("errorMessage", "Invalid email or password");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}