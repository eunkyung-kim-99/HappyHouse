//package com.ssafy.happyhouse.controller;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.ssafy.happyhouse.model.AddressDTO;
//import com.ssafy.happyhouse.model.AptDTO;
//import com.ssafy.happyhouse.model.service.AddressService;
//import com.ssafy.happyhouse.model.service.AptService;
//
//@Controller
//@RequestMapping("/apt")
//public class AptController {
//
//	@Autowired
//	AptService aptser;
//
//	@Autowired
//	AddressService adser;
//
//	@RequestMapping(value = "/sidolist", method = { RequestMethod.GET, RequestMethod.POST })
//	private String sidolist(Model model) throws Exception {
//		ArrayList<AddressDTO> sidolist = adser.listsido();
//		System.out.println(sidolist.toString());
//		model.addAttribute("sidolist", sidolist);
//		return "redirect:index";
//	}
//
//	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
//	private String searchlist(@RequestParam("sido") String sido, @RequestParam("gugun") String gugun,
//			@RequestParam("dong") String dong, @RequestParam(value = "key", required = false) String key,
//			@RequestParam(value = "word", required = false) String word, Model model) throws Exception {
//		System.out.println("컨트롤러에선");
//		String pg = "1";
//		System.out.println(sido + " " + gugun + " " + dong + " " + pg + " " + key + " " + word);
//
//		ArrayList<AptDTO> list = aptser.select(sido, gugun, dong, key, word);
//
//		System.out.println(list.toString());
//		model.addAttribute("list", list);
//		model.addAttribute("sido", sido);
//		model.addAttribute("gugun", gugun);
//		model.addAttribute("dong", dong);
//		model.addAttribute("key", key);
//		model.addAttribute("word", word);
//		model.addAttribute("searchaddress", sido + " " + gugun + " " + dong);
//		return "index";
//	}
//
//
//	@PostMapping(value = "/sort")
//	private @ResponseBody ResponseEntity<Map<String, Object>> sort(String sortValue,
//			// ArrayList<AptDTO> list)
//			// @RequestParam(value="list[]") List<AptDTO> list)
//			@RequestBody Map<String, Object> list) throws Exception {
//		System.out.println("소트 컨트롤러");
//		System.out.println(list.getClass());
//
//		AptDTO tmp = null;
//
//		ArrayList<LinkedHashMap<String, Object>> temparr = (ArrayList<LinkedHashMap<String, Object>>) list.get("list");
//		System.out.println(temparr);
//		ArrayList<AptDTO> aptlist = new ArrayList<AptDTO>();
//		for (int i = 0; i < temparr.size(); i++) {
//			LinkedHashMap<String, String> tmptmp = new LinkedHashMap<String, String>();
//			tmptmp.put("aptName", (String) temparr.get(i).get("aptName"));
//			tmptmp.put("lat", (String) temparr.get(i).get("lat"));
//			tmptmp.put("lng", (String) temparr.get(i).get("lng"));
//			tmptmp.put("dealAmount", (String) temparr.get(i).get("dealAmount"));
//			tmptmp.put("dealYear", (String) temparr.get(i).get("dealYear"));
//			tmptmp.put("dealMonth", (String) temparr.get(i).get("dealMonth"));
//			tmptmp.put("dealDay", (String) temparr.get(i).get("dealDay"));
//			tmptmp.put("area", (String) temparr.get(i).get("area"));
//			aptlist.add(new AptDTO(tmptmp));
//		}
//		System.out.println("aptlist" + aptlist);
//		
//		if (list.get("sortValue").equals("sortValuePrice")) {
//			for (int i = 0; i < aptlist.size() - 1; i++) {
//				for (int j = i + 1; j < aptlist.size(); j++) {
//					if (aptlist.get(i).getDealAmount().compareTo(aptlist.get(j).getDealAmount()) > 0) {
//						tmp = aptlist.get(i);
//						aptlist.set(i, aptlist.get(j));
//						aptlist.set(j, tmp);
//					}
//				}
//			}
//		} else {
//			for (int i = 0; i < aptlist.size() - 1; i++) {
//				for (int j = i + 1; j < aptlist.size(); j++) {
//					double ia = Double.parseDouble(aptlist.get(i).getArea());
//					double ja = Double.parseDouble(aptlist.get(j).getArea());
//					System.out.println(ia + " " + ja);
//					if (Double.compare(ia, ja) > 0) {// 뒤에께 작다
//						tmp = aptlist.get(i);
//						aptlist.set(i, aptlist.get(j));
//						aptlist.set(j, tmp);
//					}
//				}
//			}
//		}
//
//		System.out.println("list" + aptlist);
//
//		ResponseEntity<Map<String, Object>> resEntity = null;
//		Map<String, Object> map = new HashMap<>();
//		map.put("list", aptlist);
//		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
//		return resEntity;
//	}
//
//}