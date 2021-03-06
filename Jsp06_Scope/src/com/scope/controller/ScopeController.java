package com.scope.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet("/ScopeController")
public class ScopeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
            
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		System.out.println("[controller]");
		
		request.setAttribute("reqId", "reqId in servlet");
		
		request.getSession().setAttribute("sessionId", "session in servlet");
		request.getServletContext().setAttribute("appId", "appId in servlet");
		
		RequestDispatcher dispatch = request.getRequestDispatcher("result.jsp");
		dispatch.forward(request, response);
	
	
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 	doGet(request, response);
	}

}
