package com.ssafy.happyhouse.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.UserInterDTO;
import com.ssafy.happyhouse.model.service.UserInterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/userinter")
@CrossOrigin("*")
@Api("유저즐겨찾기 컨트롤러 V1")
public class RestUserInterController {

	private static final Logger logger = LoggerFactory.getLogger(RestUserInterController.class);

	@Autowired
	UserInterService userinterser;
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@ApiOperation(value = "즐겨찾기 데이터를 삭제합니다. 성공 여부를 String으로 반환.(성공='success', 실패='fail')", response = String.class)
	@DeleteMapping("{selsidocode}/{selguguncode}/{seldongcode}")
	private ResponseEntity<String> deleteuserinter(@PathVariable("selsidocode") String selsidocode,
			@PathVariable("selguguncode") String selguguncode, @PathVariable("seldongcode") String seldongcode)
			throws Exception {
		logger.debug("deleteuserinter 실행");
//		if(userser.deletetUser(id)) {
//			return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
//		}else {
//			return new ResponseEntity<String>(FAIL,HttpStatus.NO_CONTENT);
//		}
		UserInterDTO u = new UserInterDTO();
		u.setSelsidocode(selsidocode);
		u.setSelguguncode(selguguncode);
		u.setSeldongcode(seldongcode);
		userinterser.deletetUserInter(u);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	
	@ApiOperation(value = "즐겨찾기 데이터를 삭제합니다. 성공 여부를 String으로 반환.(성공='success', 실패='fail')", response = String.class)
	@DeleteMapping("{no}")
	private ResponseEntity<String> deleteuserinterdetail(@PathVariable("no") String no)
			throws Exception {
		logger.debug("deleteuserinterdetail 실행");
		userinterser.deleteuserinterdetail(no);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "즐겨찾기 정보를 등록합니다. 성공 여부를 String으로 반환.(성공='success', 실패='fail')", response = String.class)
	@PostMapping
	private ResponseEntity<String> registeruserinter(@RequestBody UserInterDTO u) throws Exception {
		System.out.println(u);
		logger.debug("registeruserinter 실행");
//			if(userser.insertUser(user)) {
//				return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
//			}else {
//				return new ResponseEntity<String>(FAIL,HttpStatus.NO_CONTENT);
//			}
		System.out.println("getAptName" + u.getAptName());
		if (userinterser.listUserInterDuplication(u) != null) {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
		userinterser.insertUserInter(u);
		System.out.println(userinterser.listUserInterDuplication(u));
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//			return new ResponseEntity<String>("빈칸이 있습니다.",HttpStatus.BAD_REQUEST);

	}

	@ApiOperation(value = "즐겨찾기 찾기. 입력한 정보의 유저가 있다면 즐겨찾기 목록을 반환합니다.", response = String.class)
	@GetMapping("{id}")
	private ResponseEntity<List<UserInterDTO>> listuserinter(@PathVariable("id") String id) throws Exception {
		logger.debug("listuserinter 실행");

		List<UserInterDTO> list = userinterser.listUserInter(id);
		System.out.println(list);
		return new ResponseEntity<List<UserInterDTO>>(list, HttpStatus.OK);
	}

	@ApiOperation(value = "즐겨찾기 찾기. 입력한 정보의 유저가 있다면 즐겨찾기 목록을 반환합니다.", response = String.class)
	@GetMapping("{id}/{selsidocode}/{selguguncode}/{seldongcode}")
	private ResponseEntity<List<UserInterDTO>> listuserinterdetail(@PathVariable("id") String id,
			@PathVariable("selsidocode") String selsidocode, @PathVariable("selguguncode") String selguguncode,
			@PathVariable("seldongcode") String seldongcode) throws Exception {
		logger.debug("listuserinterdetail 실행");

		System.out.println(selsidocode + " " + selguguncode + " " + seldongcode + " ");

		UserInterDTO u = new UserInterDTO();
		u.setId(id);
		u.setSelsidocode(selsidocode);
		u.setSelguguncode(selguguncode);
		u.setSeldongcode(seldongcode);
		List<UserInterDTO> list = userinterser.listuserinterdetail(u);
		System.out.println(list);
		return new ResponseEntity<List<UserInterDTO>>(list, HttpStatus.OK);
	}

//	@ApiOperation(value = "즐겨찾기 중복값 찾기. 입력한 정보의 유저가 있다면 즐겨찾기 목록을 반환합니다.", response = String.class)
//	@GetMapping
//	private ResponseEntity<String> listuserinterduplication(@RequestBody UserInterDTO u) throws Exception {
//		System.out.println(u);
//		logger.debug("listuserinterduplication 실행");
//		int n = userinterser.listUserInterDuplication(u);
//		if (n == 1) {
//			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
//		}
//	}
}
