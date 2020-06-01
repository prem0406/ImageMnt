package com.imagemnt.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.imagemnt.dao.UserDao;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao loginDao;

    public void init() {
        loginDao = new UserDao();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            authenticate(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (loginDao.validate(username, password)) {
        	HttpSession session = request.getSession();
        	session.setAttribute("user", username);
        	
        	//setting session to expiry in 30 mins
        	session.setMaxInactiveInterval(30*60);
        	Cookie cookie = new Cookie("user", username);
        	cookie.setMaxAge(30*60);
        	cookie.setSecure(true);
        	response.addCookie(cookie);
        	response.sendRedirect(request.getContextPath() + "/list");
        	

        	
        } else {
        	RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			PrintWriter out= response.getWriter();
			out.println("<div align=\"center\"> <font color=red >Either Username OR Password is wrong!</font></div>");
			rd.include(request, response);
            //throw new Exception("Login not successful..");
        }
    }
}