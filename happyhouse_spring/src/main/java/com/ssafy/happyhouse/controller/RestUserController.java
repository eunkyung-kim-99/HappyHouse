package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.server.PathParam;

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

import com.ssafy.happyhouse.model.UserDTO;
import com.ssafy.happyhouse.model.service.UserService;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@Api("유저 컨트롤러 V1")
public class RestUserController {

	private static final Logger logger = LoggerFactory.getLogger(RestUserController.class);

	@Autowired
	UserService userser;
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	@ApiOperation(value = "비밀번호 찾기. 입력한 정보의 유저가 있다면 비밀번호를 반환합니다.", response = String.class)
	@GetMapping("/searchpw")
	private ResponseEntity<String> searchpw(UserDTO user) throws Exception {
		if(user.getName() != "" && user.getPhonenumber()!= "" && user.getId() != "") {
			String pw = userser.searchpw(user.getId());
			if(pw != null) {
				return new ResponseEntity<String>(pw, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("일치하는 회원 정보가 없습니다.", HttpStatus.NO_CONTENT);
			}
		}else {
			return new ResponseEntity<String>("빈칸이 있습니다.",HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "회원 데이터를 삭제합니다. 성공 여부를 String으로 반환.(성공='success', 실패='fail')", response = String.class)
	@DeleteMapping("{id}")
	private ResponseEntity<String> deleteuser(@PathVariable("id") String id) throws Exception{
		logger.debug("deleteuser 실행");
//		if(userser.deletetUser(id)) {
//			return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
//		}else {
//			return new ResponseEntity<String>(FAIL,HttpStatus.NO_CONTENT);
//		}
		userser.deletetUser(id);
		return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
	}
	
	@ApiOperation(value = "회원 정보를 수정합니다. 성공 여부를 String으로 반환.(성공='success', 실패='fail')", response = String.class)
	@PutMapping
	private ResponseEntity<String> updateuser(@RequestBody UserDTO user) throws Exception {
		if (user.getId() != "" && user.getPassword() != "" && user.getName() != "" && user.getEmail() != ""
				&& user.getPhonenumber() != "") {
//			if(userser.updateUser(user)) {
//				return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
//			}else {
//				return new ResponseEntity<String>(FAIL,HttpStatus.NO_CONTENT);
//			}
			userser.updateUser(user);
			return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("빈칸이 있습니다.",HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "회원 정보를 등록합니다. 성공 여부를 String으로 반환.(성공='success', 실패='fail')", response = String.class)
	@PostMapping
	private ResponseEntity<String> registeruser(@RequestBody UserDTO user) throws Exception{
		System.out.println(user);
		logger.debug("registeruser 실행");
		if(user.getId() != "" && user.getPassword() != "" && user.getName() != "" && user.getEmail() != ""
				&& user.getPhonenumber() != "") {
//			if(userser.insertUser(user)) {
//				return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
//			}else {
//				return new ResponseEntity<String>(FAIL,HttpStatus.NO_CONTENT);
//			}
			userser.insertUser(user);
			return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("빈칸이 있습니다.",HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "로그인을 합니다. 성공 여부를 String으로 반환.(성공='success', 실패='fail')", response = UserDTO.class)
	@GetMapping("{id}/{password}")
	private ResponseEntity<UserDTO> loginuser(@PathVariable("id") String id, @PathVariable("password") String password ) throws Exception{
		System.out.println(id+" "+password);
		logger.debug("loginuser 실행");
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("password", password);
		UserDTO user = null;
		user = userser.login(id,password);
		if(user == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<UserDTO>(user,HttpStatus.OK);
		}
		
	}
	
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
	
//	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
//	private String logout(HttpSession session) {
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
	
	
//	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
//	private String delete(@RequestParam("id") String id, HttpSession session) throws Exception {
//		userser.deletetUser(id);
//		session.invalidate();
//		return "redirect:/";
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

}
