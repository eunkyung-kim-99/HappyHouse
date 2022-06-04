package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.happyhouse.model.BoardDTO;
import com.ssafy.happyhouse.model.CommunityDTO;
import com.ssafy.happyhouse.model.service.CommunityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/community")
@CrossOrigin("*")
@Api("커뮤니티 컨트롤로 V1")
public class RestCommunityController {
	private static final Logger logger = LoggerFactory.getLogger(RestCommunityController.class);
	@Autowired
	CommunityService communityser;
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 수정합니다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환합니다.", response = String.class)
	@PutMapping
	private ResponseEntity<String> updatecommunity(@RequestBody CommunityDTO commmunity) throws Exception {
		logger.debug("updateCommunity - 호출");
		logger.debug("" + commmunity);
//		if (boardser.updateboard(board)) {
//			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//		}
		communityser.updatecommunity(commmunity);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 반환한다.", response = CommunityDTO.class)
	@GetMapping("{articleno}")
	public ResponseEntity<CommunityDTO> detailcommunity(@PathVariable int articleno) throws SQLException {
		logger.debug("detailCommunity - 호출");
		communityser.updatecommunityhit(articleno);;
		return new ResponseEntity<CommunityDTO>(communityser.detailcommunity(articleno), HttpStatus.OK);
	}

	@ApiOperation(value = "글을 등록합니다. 성공여부 String으로 반환.('success' or 'fail')", response = String.class)
	@PostMapping
	private ResponseEntity<String> registcommunity(@RequestBody CommunityDTO commmunity ,@RequestParam( value = "communityimages",required = false) MultipartFile[] profileimg) throws Exception {
		System.out.println(commmunity);
		System.out.println(profileimg);
		logger.debug("registCommunity - 호출");
		logger.debug("" + commmunity);
//		if (boardser.insertboard(board)) {
//			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//		}
		communityser.insertcommunity(commmunity);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@ApiOperation(value = "글을 삭제합니다. 성공여부 String으로 반환.('success' or 'fail')", response = String.class)
	@DeleteMapping("{articleno}")
	private ResponseEntity<String> deletecommunity(@PathVariable String articleno) throws Exception {
		logger.debug("deleteCommunity - 호출");
		logger.debug("" + articleno);
//		if(boardser.deleteboard(board)){
//			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//		}

		CommunityDTO b = new CommunityDTO();
		b.setNo(articleno);
		communityser.deletecomments(b);
		communityser.deletecommunity(b);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);

	}
	
	
	@ApiOperation(value="마지막글을 불러옵니다.",response = BoardDTO.class)
	@GetMapping("/last")
	private ResponseEntity<CommunityDTO> getLastBoard() throws SQLException{
		return new ResponseEntity<CommunityDTO>(communityser.lastCommunity(),HttpStatus.OK);
	}

//	@ApiOperation(value = "전체 글 목록을 불러옵니다.", response = List.class)
//	@GetMapping
//	private ResponseEntity<List<CommunityDTO>> listcommunity() throws Exception {
//		logger.debug("listCommunity - 호출");
//		return new ResponseEntity<List<CommunityDTO>>(communityser.listcommunity(), HttpStatus.OK);
//	}
	
	
	
	
}
