package com.ssafy.happyhouse.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.AddressDTO;
import com.ssafy.happyhouse.model.AptDTO;
import com.ssafy.happyhouse.model.service.AddressService;
import com.ssafy.happyhouse.model.service.AptService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/apt")
@CrossOrigin("*")
@Api
public class RestAptController {

	@Autowired
	AptService aptser;

	@ApiOperation(value = "여러가지 검색 조건을 통해 아파트 리스트를 가져옵니다.", response = List.class)
	@GetMapping
	private ResponseEntity<List<AptDTO>> searchlist(@RequestParam("sido") String sido, @RequestParam("gugun") String gugun,
			@RequestParam("dong") String dong, @RequestParam(value = "key", required = false) String key,
			@RequestParam(value = "word", required = false) String word) throws Exception {
		
		ArrayList<AptDTO> list = aptser.select(sido, gugun, dong, key, word);
		if(list != null) {
			return new ResponseEntity<List<AptDTO>>(list, HttpStatus.OK);
		}else {
			return new ResponseEntity<List<AptDTO>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value = "아파트리스트를 정렬합니다.", response = List.class)
	@GetMapping("/sort")
	private ResponseEntity<Map<String, Object>> sort(String sortValue,
			@RequestBody Map<String, Object> list) throws Exception {
		System.out.println("소트 컨트롤러");
		System.out.println(list.getClass());

		AptDTO tmp = null;

		ArrayList<LinkedHashMap<String, Object>> temparr = (ArrayList<LinkedHashMap<String, Object>>) list.get("list");
		System.out.println(temparr);
		ArrayList<AptDTO> aptlist = new ArrayList<AptDTO>();
		for (int i = 0; i < temparr.size(); i++) {
			LinkedHashMap<String, String> tmptmp = new LinkedHashMap<String, String>();
			tmptmp.put("aptName", (String) temparr.get(i).get("aptName"));
			tmptmp.put("lat", (String) temparr.get(i).get("lat"));
			tmptmp.put("lng", (String) temparr.get(i).get("lng"));
			tmptmp.put("dealAmount", (String) temparr.get(i).get("dealAmount"));
			tmptmp.put("dealYear", (String) temparr.get(i).get("dealYear"));
			tmptmp.put("dealMonth", (String) temparr.get(i).get("dealMonth"));
			tmptmp.put("dealDay", (String) temparr.get(i).get("dealDay"));
			tmptmp.put("area", (String) temparr.get(i).get("area"));
			aptlist.add(new AptDTO(tmptmp));
		}
		System.out.println("aptlist" + aptlist);
		
		if (list.get("sortValue").equals("sortValuePrice")) {
			for (int i = 0; i < aptlist.size() - 1; i++) {
				for (int j = i + 1; j < aptlist.size(); j++) {
					if (aptlist.get(i).getDealAmount().compareTo(aptlist.get(j).getDealAmount()) > 0) {
						tmp = aptlist.get(i);
						aptlist.set(i, aptlist.get(j));
						aptlist.set(j, tmp);
					}
				}
			}
		} else {
			for (int i = 0; i < aptlist.size() - 1; i++) {
				for (int j = i + 1; j < aptlist.size(); j++) {
					double ia = Double.parseDouble(aptlist.get(i).getArea());
					double ja = Double.parseDouble(aptlist.get(j).getArea());
					System.out.println(ia + " " + ja);
					if (Double.compare(ia, ja) > 0) {// 뒤에께 작다
						tmp = aptlist.get(i);
						aptlist.set(i, aptlist.get(j));
						aptlist.set(j, tmp);
					}
				}
			}
		}

		System.out.println("list" + aptlist);
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<>();
		map.put("list", aptlist);
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}

}