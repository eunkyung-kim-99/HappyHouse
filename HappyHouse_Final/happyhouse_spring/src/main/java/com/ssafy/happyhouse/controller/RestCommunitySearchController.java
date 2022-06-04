package com.ssafy.happyhouse.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.CommunityDTO;
import com.ssafy.happyhouse.model.service.CommunityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/communitysearch")
@CrossOrigin("*")
@Api("커뮤니티검색 리스트 컨트롤러 V1")
public class RestCommunitySearchController {
	@Autowired
	private CommunityService communityser;
	private static final Logger logger = LoggerFactory.getLogger(RestCommunityController.class);

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@ApiOperation(value = "시/도 및 구/군을 입력하면 동을 반환합니다.", response = Map.class)
	@GetMapping(value = "{sido}/{gugun}/{dong}/{keyword}")
	private ResponseEntity<Map<String,Object>> searchsido(@PathVariable("sido") String sidoname,
			@PathVariable("gugun") String gugunname, @PathVariable("dong") String dongname,
			@PathVariable("keyword") String keyword,@RequestParam("pg") String pg,
			@RequestParam("perpage") String perpage) throws Exception {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("sidoname", sidoname);
		map.put("gugunname", gugunname);
		map.put("dongname", dongname);
		map.put("keyword", keyword);
		map.put("pg", pg);
		map.put("perpage", perpage);
		System.out.println(map);
		Map<String,Object> donglist = communityser.listdong(map);
		
		
		
		if (donglist != null) {
			return new ResponseEntity<Map<String,Object>>(donglist, HttpStatus.OK);
		} else {
			return new ResponseEntity<Map<String,Object>>(HttpStatus.NO_CONTENT);
		}
	}

//	@ApiOperation(value = "시/도를 입력하면 구/군을 반환합니다.")
//	@GetMapping(value = "{sido}/{gugun}/{keyword}")
//	private ResponseEntity<List<CommunityDTO>> gugunlist(@PathVariable("sido") String sidoname,
//			@PathVariable("gugun") String gugunname, @PathVariable("keyword") String keyword) throws Exception {
//		Map<String, String> map = new LinkedHashMap<String, String>();
//		map.put("sidoname", sidoname);
//		map.put("gugunname", gugunname);
//		map.put("keyword", keyword);
//		List<CommunityDTO> gugunlist = communityser.listgugun(map);
//		if (gugunlist != null) {
//			return new ResponseEntity<List<CommunityDTO>>(gugunlist, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<List<CommunityDTO>>(HttpStatus.NO_CONTENT);
//		}
//
//	}
//
//	@ApiOperation(value = "시/도를 반환합니다.")
//	@GetMapping(value = "{sido}/{keyword}")
//	private ResponseEntity<List<CommunityDTO>> searchsido(@PathVariable("sido") String sidoname,
//			@PathVariable("keyword") String keyword) throws Exception {
//		logger.debug("searchsido - 호출");
//		logger.debug("" + sidoname);
//		Map<String, String> map = new LinkedHashMap<String, String>();
//		map.put("sidoname", sidoname);
//		map.put("keyword", keyword);
//
//		List<CommunityDTO> sidolist = communityser.listsido(map);
//		if (sidolist != null) {
//			return new ResponseEntity<List<CommunityDTO>>(sidolist, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<List<CommunityDTO>>(HttpStatus.NO_CONTENT);
//		}
//	}
//	@GetMapping(value = "{keyword}")
//	private ResponseEntity<List<CommunityDTO>> searchkeyword(@PathVariable("keyword") String keyword) throws Exception {
//		logger.debug("searchsido - 호출");
//		logger.debug("" + keyword);
//		Map<String, String> map = new LinkedHashMap<String, String>();
//		map.put("keyword", keyword);
//
//		List<CommunityDTO> keywordlist = communityser.keywordlist(map);
//		if (keywordlist != null) {
//			return new ResponseEntity<List<CommunityDTO>>(keywordlist, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<List<CommunityDTO>>(HttpStatus.NO_CONTENT);
//		}
//	}
}
