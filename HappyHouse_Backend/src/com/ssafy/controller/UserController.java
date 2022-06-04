package com.ssafy.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.dto.BoardDTO;
import com.ssafy.dto.UserDTO;
import com.ssafy.service.BoardService;
import com.ssafy.service.BoardServiceImpl;
import com.ssafy.service.UserService;
import com.ssafy.service.UserServiceImpl;

@WebServlet("*.user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String url = "index.jsp";
	UserService userser = new UserServiceImpl();
	BoardService bser = new BoardServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		try {

			if (path != null) {
				if (path.equals("/login.user")) {
					url = login(request, response);
				} else if (path.equals("/register.user")) {
					url = register(request, response);
				} else if (path.equals("/logout.user")) {
					url = logout(request, response);
				} else if (path.equals("/update.user")) {
					url = update(request, response);
				} else if (path.equals("/del.user")) {
					url = delete(request, response);
				} else if (path.equals("/searchpw.user")) {
					url = searchpw(request, response);
				} else if (path.equals("/listboardform.user")) {
					url = listboard(request, response);
				} else if (path.equals("/boardinfo.user")) {
					url = infoboard(request, response);
				} else if (path.equals("/registboardform.user")) {
					url = "WEB-INF/board/registboard.jsp";
				} else if (path.equals("/regboard.user")) {
					url = registboard(request, response);
				}
				else if (path.equals("/updateboardform.user")) {
					url = updateboardform(request, response);
				}
				else if (path.equals("/updateboard.user")) {
					url = updateboard(request, response);
				}
				else if (path.equals("/deleteboard.user")) {
					url = deleteboard(request, response);
				}
			}

		} catch (SQLException e) {
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
	
	private String updateboardform(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		String id = request.getParameter("id");
		String subject = request.getParameter("subject");
		String contents = request.getParameter("contents");
		request.setAttribute("no", no);
		request.setAttribute("id", id);
		request.setAttribute("subject", subject);
		request.setAttribute("contents", contents);
		return "WEB-INF/board/updateboard.jsp";
	}

	private String deleteboard(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String no = request.getParameter("no");
		String id = request.getParameter("id");
		String subject = request.getParameter("subject");
		String contents = request.getParameter("contents");
		bser.deleteboard(new BoardDTO(no, id, subject, contents));
		
		return "listboardform.user";
	}

	private String updateboard(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String no = request.getParameter("no");
		String id = request.getParameter("id");
		String subject = request.getParameter("subject");
		String contents = request.getParameter("contents");
		bser.updateboard(new BoardDTO(no, id, subject, contents));
		
		return "listboardform.user";
	}

	private String registboard(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String no = "";
		String id = request.getParameter("id");
		String subject = request.getParameter("subject");
		String contents = request.getParameter("contents");
		bser.insertboard(new BoardDTO(no, id, subject, contents));
		
		return "redirect:listboardform.user";
	}

	private String infoboard(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		String id = request.getParameter("id");
		String subject = request.getParameter("subject");
		String contents = request.getParameter("contents");
		System.out.println("no:"+no+" id:"+id+" sub:"+subject+" con:"+contents);
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		list.add(new BoardDTO(no, id, subject, contents));
		System.out.println(list);
		request.setAttribute("infolist", list);
		return "listboardform.user";
	}

	private String listboard(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		ArrayList<BoardDTO> list = bser.listboard();
		request.setAttribute("list", list);
		return "/WEB-INF/board/listboard.jsp";
	}

	private String searchpw(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String searchname = request.getParameter("searchname");
		String searchid = request.getParameter("searchid");
		String searchNumber = request.getParameter("searchNumber");
		if (searchname != "" && searchid != "" && searchNumber != "") {
			String pw = userser.searchpw(searchid);
			if (pw != null) {
				request.setAttribute("msg", "비밀번호는 " + pw + " 입니다.");
			} else {
				request.setAttribute("msg", "회원정보가 없습니다.");
			}
			return "main.do";
		} else {
			request.setAttribute("msg", "빈칸이 있습니다");
			return "main.do";
		}
	}

	private String delete(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String id = request.getParameter("id");
		userser.deletetUser(id);
		return logout(request, response);
	}

	private String update(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String inputNumber = request.getParameter("updateNumber");
		System.out.println("회원수정");
		if (id != "" && pw != "" && name != "" && email != "" && inputNumber != "") {
			userser.updateUser(new UserDTO(id, pw, name, email, inputNumber));
			request.getSession().setAttribute("userInfo", new UserDTO(id, pw, name, email, inputNumber));

		}
		return "main.do";
	}

	private String logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		System.out.println("로그아웄");
		return "main.do";
	}

	private String register(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String inputNumber = request.getParameter("inputNumber");
		System.out.println("회원강비");
		if (id != "" && pw != "" && name != "" && email != "" && inputNumber != "") {
			userser.insertUser(new UserDTO(id, pw, name, email, inputNumber));
		}
		return "main.do";
	}

	private String login(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		System.out.println("로그인");
		if (id != "" && pw != "" && userser.login(id, pw) != null) {
			System.out.println("dfdf");
			request.getSession().setAttribute("userInfo", userser.login(id, pw));
			return "main.do";
		} else {
			request.setAttribute("msg", "아이디 비번이 다릅니다");
			System.out.println("로그인 오류");
			return "main.do";
		}
	}

}
