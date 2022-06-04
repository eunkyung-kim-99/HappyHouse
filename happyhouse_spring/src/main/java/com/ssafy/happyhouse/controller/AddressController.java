package com.ssafy.happyhouse.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.AddressDTO;
import com.ssafy.happyhouse.model.service.AddressService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/address")
@CrossOrigin("*")
@Api("지역 리스트 컨트롤러 V1")
public class AddressController {
	@Autowired
	private AddressService adser;
	
	@ApiOperation(value = "시/도 및 구/군을 입력하면 동을 반환합니다.", response = List.class)
	@GetMapping(value = "/dong/{sido}/{gugun}")
	private ResponseEntity<List<AddressDTO>> donglist(@PathVariable("sido") String sidoname, @PathVariable("gugun") String gugunName, Model model)
			throws Exception {
		List<AddressDTO> donglist = adser.listdong(sidoname, gugunName);
		if(donglist != null) {
			return new ResponseEntity<List<AddressDTO>>(donglist, HttpStatus.OK);
		}else {
			return new ResponseEntity<List<AddressDTO>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value = "시/도를 입력하면 구/군을 반환합니다.")
	@GetMapping(value = "/gugun/{sido}")
	private ResponseEntity<List<AddressDTO>> gugunlist(@PathVariable("sido") String sidoname, Model model) throws Exception {
		
		List<AddressDTO> gugunlist = adser.listgugun(sidoname);
		if(gugunlist != null) {
			return new ResponseEntity<List<AddressDTO>>(gugunlist, HttpStatus.OK);
		}else {
			return new ResponseEntity<List<AddressDTO>>(HttpStatus.NO_CONTENT);
		}

	}
	
	@ApiOperation(value = "시/도를 반환합니다.")
	@GetMapping(value = "/sido")
	private ResponseEntity<List<AddressDTO>> sidolist(Model model) throws Exception {
		
		List<AddressDTO> sidolist = adser.listsido();
		if(sidolist != null) {
			return new ResponseEntity<List<AddressDTO>>(sidolist, HttpStatus.OK);
		}else {
			return new ResponseEntity<List<AddressDTO>>(HttpStatus.NO_CONTENT);
		}
	}
}
