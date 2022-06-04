package com.ssafy.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonValue;
import com.ssafy.dto.AddressDTO;
import com.ssafy.service.AddressService;
import com.ssafy.service.AddressServiceImpl;

@WebServlet("/address")
public class AddressController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   private AddressService adser = new AddressServiceImpl();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		String path = "/index.jsp";
		try {
			if("sidoxml".equals(act)) {
				path = sidolist(request, response);
				request.getRequestDispatcher(path).forward(request, response);
			} 
			if("gugunxml".equals(act)) {
				path = gugunlist(request, response);
				request.getRequestDispatcher(path).forward(request, response);
			} 
			if("dongxml".equals(act)) {
				path = donglist(request, response);
				request.getRequestDispatcher(path).forward(request, response);
			} 
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	private String donglist(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String sidoname = request.getParameter("sido");
		String gugunName = request.getParameter("gugun");
		System.out.println(sidoname+" "+gugunName);
		ArrayList<AddressDTO> donglist = adser.listdong(sidoname, gugunName);
		System.out.println(donglist);
		request.setAttribute("donglist", donglist);
		return "/WEB-INF/XML/test3.jsp";
	}

	private String gugunlist(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String sidoname = request.getParameter("sido");
		System.out.println(sidoname);
		ArrayList<AddressDTO> gugunlist = adser.listgugun(sidoname);
		System.out.println(gugunlist);
		request.setAttribute("gugunlist", gugunlist);
		return "/WEB-INF/XML/test2.jsp";
		
	}

	private String sidolist(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		ArrayList<AddressDTO> sidolist = adser.listsido();
		request.setAttribute("sidolist", sidolist);
		return "/WEB-INF/XML/test.jsp";
	}

}
