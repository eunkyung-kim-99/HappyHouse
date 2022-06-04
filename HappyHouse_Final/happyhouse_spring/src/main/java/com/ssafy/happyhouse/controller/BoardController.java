//package com.ssafy.happyhouse.controller;
//
//import java.util.ArrayList;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.ssafy.happyhouse.model.BoardDTO;
//import com.ssafy.happyhouse.model.service.BoardService;
//
//@CrossOrigin(origins = { "*" }, maxAge = 6000)
//@RestController
//@RequestMapping("/board")
//public class BoardController {
//
////	@Autowired
////	UserService userser;
//
//	@Autowired
//	BoardService bser;
//
//	@RequestMapping(value = "/updateboardform", method = { RequestMethod.GET, RequestMethod.POST })
//	private String updateboardform(BoardDTO board, Model model) {
//		model.addAttribute("no", board.getNo());
//		model.addAttribute("id", board.getId());
//		model.addAttribute("subject", board.getSubject());
//		model.addAttribute("contents", board.getContents());
//		return "board/updateboard";
//	}
//
//	@RequestMapping(value = "/registboardform", method = { RequestMethod.GET, RequestMethod.POST })
//	private String registboardform(BoardDTO board, Model model) {
//		return "board/registboard";
//	}
//
//	@RequestMapping(value = "/deleteboard", method = { RequestMethod.GET, RequestMethod.POST })
//	private String deleteboard(BoardDTO board, Model model) throws Exception {
//		System.out.println(board);
//		bser.deleteboard(board);
//		return listboard(model);
//	}
//
//	@RequestMapping(value = "/updateboard", method = { RequestMethod.GET, RequestMethod.POST })
//	private String updateboard(BoardDTO board, Model model) throws Exception {
//		bser.updateboard(board);
//		return listboard(model);
//	}
//
//	@RequestMapping(value = "/registboard", method = { RequestMethod.GET, RequestMethod.POST })
//	private String registboard(@RequestParam("id") String id, @RequestParam("subject") String subject,
//			@RequestParam("contents") String contents, Model model) throws Exception {
//		String no = "";
//		bser.insertboard(new BoardDTO(no, id, subject, contents));
//		return listboard(model);
//	}
//
//	@RequestMapping(value = "/infoboard", method = { RequestMethod.GET, RequestMethod.POST })
//	private String infoboard(BoardDTO board, Model model) throws Exception {
//		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
//		list.add(board);
//		System.out.println(list);
//		model.addAttribute("infolist", list);
//		return listboard(model);
//	}
//
//	@RequestMapping(value = "/listboard", method = { RequestMethod.GET, RequestMethod.POST })
//	private String listboard(Model model) throws Exception {
//		ArrayList<BoardDTO> list = bser.listboard();
//		model.addAttribute("list", list);
//		return "board/listboard";
//	}
//}
