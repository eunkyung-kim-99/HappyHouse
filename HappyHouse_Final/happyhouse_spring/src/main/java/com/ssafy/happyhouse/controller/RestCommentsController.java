package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.CommentsDTO;
import com.ssafy.happyhouse.model.CommunityDTO;
import com.ssafy.happyhouse.model.service.CommentsService;
import com.ssafy.happyhouse.model.service.CommunityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/comments")
@CrossOrigin("*")
@Api("코멘트 컨트롤로 V1")
public class RestCommentsController {
	private static final Logger logger = LoggerFactory.getLogger(RestCommentsController.class);
	@Autowired
	CommentsService commentsser;
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@ApiOperation(value = "글번호에 해당하는 게시글의 댓글 수정합니다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환합니다.", response = String.class)
	@PutMapping("{no}/{content}")
	private ResponseEntity<String> updatecomments(@PathVariable("no") String no, @PathVariable("content") String content) throws Exception {
		logger.debug("updatecomments - 호출");
		logger.debug("" + content);
//		if (boardser.updateboard(board)) {
//			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//		}
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("no", no);
		map.put("content", content);
		commentsser.updatecomments(map);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@ApiOperation(value = "댓글을 등록합니다. 성공여부 String으로 반환.('success' or 'fail')", response = String.class)
	@PostMapping
	private ResponseEntity<String> registcomments(@RequestBody CommentsDTO commmunity) throws Exception {
		System.out.println(commmunity);
		logger.debug("registcomments - 호출");
		logger.debug("" + commmunity);
//		if (boardser.insertboard(board)) {
//			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//		}
		commentsser.insertcomments(commmunity);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@ApiOperation(value = "댓글을 삭제합니다. 성공여부 String으로 반환.('success' or 'fail')", response = String.class)
	@DeleteMapping("{no}")
//	private ResponseEntity<String> deletecomments(@RequestBody CommentsDTO commmunity) throws Exception {
	private ResponseEntity<String> deletecomments(@PathVariable String no) throws Exception {
		logger.debug("deletecomments - 호출");
		logger.debug("" + no);
//		if(boardser.deleteboard(board)){
//			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//		}

		commentsser.deletecomments(no);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);

	}

	@ApiOperation(value = "댓글 목록을 불러옵니다.", response = List.class)
	@GetMapping("{no}")
	private ResponseEntity<List<CommentsDTO>> listcommunity(@PathVariable String no) throws Exception {
		logger.debug("listcommunity - 호출");
		return new ResponseEntity<List<CommentsDTO>>(commentsser.listcomments(no), HttpStatus.OK);
	}
}
