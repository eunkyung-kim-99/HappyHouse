package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.BoardDTO;
import com.ssafy.happyhouse.model.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/board")
@CrossOrigin("*")
@Api("게시판 컨트롤로 V1")
public class RestBoardController {
	private static final Logger logger = LoggerFactory.getLogger(RestBoardController.class);
	@Autowired
	BoardService boardser;
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 수정합니다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환합니다.", response = String.class)
	@PutMapping
	private ResponseEntity<String> updateboard(@RequestBody BoardDTO board) throws Exception {
		logger.debug("updateBoard - 호출");
		logger.debug("" + board);
//		if (boardser.updateboard(board)) {
//			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//		}
		boardser.updateboard(board);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.NO_CONTENT);
	}
	
//	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 수정합니다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환합니다.", response = String.class)
//	@PutMapping("{no}")
//	private ResponseEntity<String> updateboardhit(@PathVariable("no") String no) throws Exception {
//		logger.debug("updateboardhit - 호출");
//		logger.debug("" + no);
////		if (boardser.updateboard(board)) {
////			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
////		}
//		boardser.updateboardhit(no);
//		return new ResponseEntity<String>(SUCCESS, HttpStatus.NO_CONTENT);
//	}
	
    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 반환한다.", response = BoardDTO.class)    
	@GetMapping("{articleno}")
	public ResponseEntity<BoardDTO> detailBoard(@PathVariable int articleno) throws Exception {
		logger.debug("detailBoard - 호출");
		boardser.updateboardhit(articleno);
		return new ResponseEntity<BoardDTO>(boardser.detailBoard(articleno), HttpStatus.OK);
    }

	@ApiOperation(value = "글을 등록합니다. 성공여부 String으로 반환.('success' or 'fail')", response = String.class)
	@PostMapping
	private ResponseEntity<String> registboard(@RequestBody BoardDTO board) throws Exception {
		System.out.println(board);
		logger.debug("registBoard - 호출");
		logger.debug("" + board);
//		if (boardser.insertboard(board)) {
//			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//		}
		boardser.insertboard(board);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글을 삭제합니다. 성공여부 String으로 반환.('success' or 'fail')", response = String.class)
	@DeleteMapping("{articleno}")
	private ResponseEntity<String> deleteboard(@PathVariable String articleno) throws Exception {
		logger.debug("deleteBoard - 호출");
		logger.debug("" + articleno);
		
		BoardDTO b= new BoardDTO();
		b.setNo(articleno);
		boardser.deleteboard(b);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.NO_CONTENT);

	}

//	@ApiOperation(value = "전체 글 목록을 불러옵니다.", response = List.class)
//	@GetMapping("{pg}/{perpage}")
//	private ResponseEntity<Map<String,Object>> listboard(@PathVariable("pg") String pg, @PathVariable("perpage") String perpage) throws Exception {
//		logger.debug("listboard - 호출");
//		Map<String,Object> resultMap = new HashMap<>();
//		int ipg = Integer.parseInt(pg);
//		int iperpage = Integer.parseInt(perpage);
//		
//		
//		return new ResponseEntity<Map<String,Object>>(boardser.listboard(ipg,iperpage), HttpStatus.OK);
//	}
	
	@ApiOperation(value = "검색 글 목록을 불러옵니다.", response = Map.class)
	@GetMapping()
	private ResponseEntity<Map<String,Object>> searchlistboard(@RequestParam(value = "select", required = false) String select,@RequestParam(value="content" ,required = false) String content,
			@RequestParam("pg") String pg, @RequestParam("perpage") String perpage) throws Exception {
		logger.debug("searchlistboard - 호출");
		Map<String, String> map = new LinkedHashMap<String, String>();
		if(select == null) {
			select = "1";
			content = "1";
		}
		else if(select.equals("전체"))select = "전체";
		else if(select.equals("작성자")) select = "id";
		else select = "subject";
		map.put("pg", pg);
		map.put("perpage", perpage);
		map.put("select", select);
		map.put("content", content);
		return new ResponseEntity<Map<String,Object>>(boardser.searchlistboard(map), HttpStatus.OK);
	}

//	@RequestMapping(value = "/listboard", method = { RequestMethod.GET, RequestMethod.POST })
//	private String listboard(Model model) throws Exception {
//		ArrayList<BoardDTO> list = boardser.listboard();
//		model.addAttribute("list", list);
//		return "board/listboard";
//	}
//	@RequestMapping(value = "/infoboard", method = { RequestMethod.GET, RequestMethod.POST })
//	private String infoboard(BoardDTO board, Model model) throws Exception {
//		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
//		list.add(board);
//		System.out.println(list);
//		model.addAttribute("infolist", list);
//		return listboard(model);
//	}
//	@RequestMapping(value = "/deleteboard", method = { RequestMethod.GET, RequestMethod.POST })
//	private String deleteboard(BoardDTO board, Model model) throws Exception {
//		System.out.println(board);
//		boardser.deleteboard(board);
//		return listboard(model);
//	}
//	@RequestMapping(value = "/registboardform", method = { RequestMethod.GET, RequestMethod.POST })
//	private String registboardform(BoardDTO board, Model model) {
//		return "board/registboard";
//	}
//	@RequestMapping(value = "/registboard", method = { RequestMethod.GET, RequestMethod.POST })
//	private String registboard(@RequestParam("id") String id, @RequestParam("subject") String subject,
//			@RequestParam("contents") String contents, Model model) throws Exception {
//		String no = "";
//		boardser.insertboard(new BoardDTO(no, id, subject, contents));
//		return listboard(model);
//	}
//	@RequestMapping(value = "/updateboardform", method = { RequestMethod.GET, RequestMethod.POST })
//	private String updateboardform(BoardDTO board, Model model) {
//		model.addAttribute("no", board.getNo());
//		model.addAttribute("id", board.getId());
//		model.addAttribute("subject", board.getSubject());
//		model.addAttribute("contents", board.getContents());
//		return "board/updateboard";
//	}
//	@RequestMapping(value = "/updateboard", method = { RequestMethod.GET, RequestMethod.POST })
//	private String updateboard(BoardDTO board, Model model) throws Exception {
//		boardser.updateboard(board);
//		return listboard(model);
//	}
}
