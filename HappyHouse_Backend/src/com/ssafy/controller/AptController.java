package com.ssafy.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.dto.AddressDTO;
import com.ssafy.dto.AptDTO;
import com.ssafy.service.AddressService;
import com.ssafy.service.AddressServiceImpl;
import com.ssafy.service.AptService;
import com.ssafy.service.AptServiceImpl;
import com.ssafy.util.PageNavigation;

@WebServlet("*.do")
public class AptController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String url = "index.jsp";
	AptService aptser = new AptServiceImpl();
	AddressService adser = new AddressServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		try {

			if (path != null) {
				if (path.equals("/main.do")) {
					url = "index.jsp";
				} else if (path.equals("/search.do")) {
					url = searchlist(request, response);
				} else if (path.equals("/sidolist.do")) {
					url = sidolist(request, response);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (url.startsWith("redirect")) {
			url = url.substring(url.indexOf(":") + 1);
			response.sendRedirect(url);
		} else {
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

	private String sidolist(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		ArrayList<AddressDTO> sidolist = adser.listsido();
		request.setAttribute("sidolist", sidolist);
		return "main.do";
	}

	private String searchlist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sido = request.getParameter("sido");
		String gugun = request.getParameter("gugun");
		String dong = request.getParameter("dong");
		String pg = request.getParameter("pg");
		String key = request.getParameter("key");
		String word = request.getParameter("word");

		System.out.println("컨트롤러에선");
		System.out.println(sido + " " + gugun + " " + dong + " " + pg + " " + key + " " + word);

		ArrayList<AptDTO> list = aptser.select(sido, gugun, dong, pg, key, word);
		PageNavigation navigation = aptser.makePageNavigation(sido, gugun, dong, pg, key, word);

		request.setAttribute("list", list);
		request.setAttribute("sido", sido);
		request.setAttribute("gugun", gugun);
		request.setAttribute("dong", dong);
		request.setAttribute("navi", navigation);
		request.setAttribute("key", key);
		request.setAttribute("word", word);
		request.setAttribute("searchaddress", sido + " " + gugun + " " + dong);
		return "main.do";
	}

}
