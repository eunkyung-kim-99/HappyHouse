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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.AddressDTO;
import com.ssafy.happyhouse.model.AptDTO;
import com.ssafy.happyhouse.model.AptDetailDTO;
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
	private ResponseEntity<List<AptDTO>> searchlist(@RequestParam("sido") String sido,
			@RequestParam("gugun") String gugun, @RequestParam("dong") String dong,
			@RequestParam(value = "key", required = false) String key,
			@RequestParam(value = "word", required = false) String word) throws Exception {
		
		ArrayList<AptDTO> list = aptser.select(sido, gugun, dong, key, word);
		if (list != null) {
			return new ResponseEntity<List<AptDTO>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<AptDTO>>(HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "여러가지 검색 조건을 통해 아파트 리스트를 가져옵니다.", response = List.class)
	@GetMapping("{aptcode}")
	private ResponseEntity<List<AptDetailDTO>> searchlistdetail(@PathVariable("aptcode") String aptcode)
			throws Exception {

		ArrayList<AptDetailDTO> list = aptser.selectdetail(aptcode);
		if (list != null) {
			return new ResponseEntity<List<AptDetailDTO>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<AptDetailDTO>>(HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "아파트리스트를 정렬합니다.", response = List.class)
	@PostMapping("/sort/{sortValue}")
	private ResponseEntity<List<AptDetailDTO>> sort(@RequestBody List<AptDetailDTO> list,
			@PathVariable("sortValue") String sortValue) throws Exception {
		List<AptDetailDTO> result = new ArrayList<>();
		System.out.println(list);
		AptDetailDTO tmp = new AptDetailDTO();

//	
//		for(Map<String,String> tlist :list) {
//			AptDetailDTO tempdto = new AptDetailDTO();
//			tempdto.setDealAmount(tlist.get("dealAmount"));
//			tempdto.setDealAmount(tlist.get("dealYear"));
//			tempdto.setDealAmount(tlist.get("dealMonth"));
//			tempdto.setDealAmount(tlist.get("dealDay"));
//			tempdto.setDealAmount(tlist.get("area"));
//			tempdto.setDealAmount(tlist.get("floor"));
//			result.add(tempdto);
//		}
		if(list.size() == 1) {
			return new ResponseEntity<List<AptDetailDTO>>(list, HttpStatus.OK);
		}
		if (sortValue.equals("sortValuePrice")) {
			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < list.size() - i - 1; j++) {
					if (Integer.parseInt(String.join("",list.get(j).getDealAmount().trim().split(","))) > Integer
							.parseInt(String.join("",list.get(j+1).getDealAmount().trim().split(",")))) {
						tmp = list.get(j + 1);
						list.set(j + 1, list.get(j));
						list.set(j, tmp);
					}
				}
			}
		} else {
			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < list.size() - i - 1; j++) {
					if (Double.parseDouble(list.get(j).getArea()) > Double.parseDouble(list.get(j + 1).getArea())) {
						tmp = list.get(j + 1);
						list.set(j + 1, list.get(j));
						list.set(j, tmp);
					}
				}
			}
		}

		return new ResponseEntity<List<AptDetailDTO>>(list, HttpStatus.OK);

	}

}