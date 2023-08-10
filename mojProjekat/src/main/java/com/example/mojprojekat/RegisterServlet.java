package com.example.mojprojekat;

import com.example.mojprojekat.database.Database;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "register", value = "/register")
public class RegisterServlet extends HttpServlet {
    private String message;
    Database db = Database.getConnection();

    public void init()
    {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String pass = request.getParameter("password");
        if(username == "" || username==null)
        {
            response.sendRedirect("greska.jsp");
            return;
        }
        if(pass == "" || pass==null)
        {
            response.sendRedirect("greska.jsp");
            return;
        }
        if(db.register(username,pass))
        {
            response.sendRedirect("uspesnoPrijavljen.jsp");
        }
        else{
            response.sendRedirect("greska.jsp");
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    public void destroy() {
    }
}
