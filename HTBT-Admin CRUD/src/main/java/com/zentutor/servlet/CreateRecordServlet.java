package com.zentutor.servlet;

import com.zentutor.model.Admin;
import com.zentutor.model.AdminUser;
import com.zentutor.model.EditorUser;
import com.zentutor.utill.DataStorage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class CreateRecordServlet extends HttpServlet {
    private DataStorage dataStorage;

    //initializes & sets up the data storage path
    @Override
    public void init() throws ServletException {
        String filePath = "F:\\sliit\\1st year\\2 sem\\Object Oriented Programming - SE1020\\project\\Final real project\\HTBT-Admin CRUD\\src\\main\\webapp\\records.txt";
        this.dataStorage = new DataStorage(filePath);
    }

    //create newUser page accesses authorisation
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.getRequestDispatcher("add.jsp").forward(request, response);

    }

    //saving the new user data
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Admin ???
        Admin user = (Admin) request.getSession().getAttribute("user");
        if (user == null || !"Admin".equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/list");
            return;
        }

        String role = request.getParameter("role");
        Admin newAdmin;
        int newId = dataStorage.generateNewId(); //assigned a unique ID
        if ("Editor".equals(role)) {
            newAdmin = new EditorUser(
                    newId,
                    request.getParameter("name"),
                    request.getParameter("email"),
                    request.getParameter("password")
            );
        } else {
            newAdmin = new AdminUser(
                    newId,
                    request.getParameter("name"),
                    request.getParameter("email"),
                    request.getParameter("password"),
                    role
            );
        }

        try {
            dataStorage.addRecord(newAdmin); //new user object is added
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/list");
    }
}