//package com.ssafy.happyhouse.controller;
//
//import java.util.ArrayList;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.ssafy.happyhouse.model.BoardDTO;
//import com.ssafy.happyhouse.model.UserDTO;
//import com.ssafy.happyhouse.model.service.BoardService;
//import com.ssafy.happyhouse.model.service.UserService;
//
//@Controller
//@RequestMapping("/user")
//public class UserController {
//	@Autowired
//	UserService userser;
//	@Autowired
//	BoardService bser;
//	@RequestMapping(value = "/updateboardform", method = { RequestMethod.GET, RequestMethod.POST })
//	private String updateboardform(BoardDTO board, Model model) {
//		model.addAttribute("no", board.getNo());
//		model.addAttribute("id", board.getId());
//		model.addAttribute("subject", board.getSubject());
//		model.addAttribute("contents", board.getContents());
//		return "board/updateboard";
//	}
//	@RequestMapping(value = "/registboardform", method = { RequestMethod.GET, RequestMethod.POST })
//	private String registboardform(BoardDTO board, Model model) {
//		return "board/registboard";
//	}
//	@RequestMapping(value = "/deleteboard", method = { RequestMethod.GET, RequestMethod.POST })
//	private String deleteboard(BoardDTO board, Model model) throws Exception {
//		System.out.println(board);
//		bser.deleteboard(board);
//		return listboard(model);
//	}
//	@RequestMapping(value = "/updateboard", method = { RequestMethod.GET, RequestMethod.POST })
//	private String updateboard(BoardDTO board, Model model) throws Exception {
//		bser.updateboard(board);
//		return listboard(model);
//	}
//	@RequestMapping(value = "/registboard", method = { RequestMethod.GET, RequestMethod.POST })
//	private String registboard(@RequestParam("id") String id, @RequestParam("subject") String subject,
//			@RequestParam("contents") String contents, Model model) throws Exception {
//		String no = "";
//		bser.insertboard(new BoardDTO(no, id, subject, contents));
//		return listboard(model);
//	}
//	@RequestMapping(value = "/infoboard", method = { RequestMethod.GET, RequestMethod.POST })
//	private String infoboard(BoardDTO board, Model model) throws Exception {
//		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
//		list.add(board);
//		System.out.println(list);
//		model.addAttribute("infolist", list);
//		return listboard(model);
//	}
//	@RequestMapping(value = "/listboard", method = { RequestMethod.GET, RequestMethod.POST })
//	private String listboard(Model model) throws Exception {
//		ArrayList<BoardDTO> list = bser.listboard();
//		model.addAttribute("list", list);
//		return "board/listboard";
//	}
//	@RequestMapping(value = "/searchpw", method = { RequestMethod.GET, RequestMethod.POST })
//	private String searchpw(@RequestParam("searchname") String searchname, @RequestParam("searchid") String searchid,
//			@RequestParam("searchNumber") String searchNumber, Model model) throws Exception {
//		if (searchname != "" && searchid != "" && searchNumber != "") {
//			String pw = userser.searchpw(searchid);
//			if (pw != null) {
//				model.addAttribute("msg", "비밀번호는 " + pw + " 입니다.");
//			} else {
//				model.addAttribute("msg", "회원정보가 없습니다.");
//			}
//			return "forward:/";
//		} else {
//			model.addAttribute("msg", "빈칸이 있습니다.");
//			return "forward:/";
//		}
//	}
//	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
//	private String delete(@RequestParam("id") String id, HttpSession session) throws Exception {
//		userser.deletetUser(id);
//		session.invalidate();
//		return "redirect:/";
//	}
//	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
//	private String update(UserDTO user, HttpSession session) throws Exception {
//		System.out.println("회원수정");
//		if (user.getId() != "" && user.getPassword() != "" && user.getName() != "" && user.getEmail() != ""
//				&& user.getPhonenumber() != "") {
//			System.out.println(user);
//			userser.updateUser(user);
//			session.setAttribute("userInfo", user);
//		}
//		return "redirect:/";
//	}
//	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
//	private String logout(HttpSession session) {
//		session.invalidate();
//		return "redirect:/";
//	}
//	@RequestMapping(value = "/register", method = { RequestMethod.GET, RequestMethod.POST })
//	private String register(UserDTO user) throws Exception {
//		System.out.println("회원강비");
//		if (user.getId() != "" && user.getPassword() != "" && user.getName() != "" && user.getEmail() != ""
//				&& user.getPhonenumber() != "") {
//			System.out.println(user);
//			userser.insertUser(user);
//		}
//		return "redirect:/";
//	}
//	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
//	private String loginMember(@RequestParam("id") String id, @RequestParam("password") String pw, Model model,
//			HttpSession session) throws Exception {
//		System.out.println(id + " " + pw);
//		System.out.println("로그인");
//		if (id != "" && pw != "" && userser.login(id, pw) != null) {
//			session.setAttribute("userInfo", userser.login(id, pw));
//			return "redirect:/";
//		} else {
//			model.addAttribute("msg", "아이디 비번이 다릅니다");
//			System.out.println("로그인 오류");
//			return "forward:/";
//		}
//	}
//}
