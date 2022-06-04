package com.ssafy.happyhouse.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.happyhouse.model.AddressDTO;
import com.ssafy.happyhouse.model.service.AddressService;

@Controller
@RequestMapping("/address")
@CrossOrigin("*")
public class AddressController {

	@Autowired
	private AddressService adser;

	@GetMapping(value = "/dong/{sido}/{gugun}")
	private String donglist(@PathVariable("sido") String sidoname, @PathVariable("gugun") String gugunName, Model model)
			throws Exception {
		System.out.println(sidoname + " " + gugunName);
		ArrayList<AddressDTO> donglist = adser.listdong(sidoname, gugunName);
		System.out.println(donglist);
		model.addAttribute("donglist", donglist);
		return "XML/test3";
	}

	@GetMapping(value = "/gugun/{sido}")
	private String gugunlist(@PathVariable("sido") String sidoname, Model model) throws Exception {
		System.out.println(sidoname);
		ArrayList<AddressDTO> gugunlist = adser.listgugun(sidoname);
		System.out.println(gugunlist);
		model.addAttribute("gugunlist", gugunlist);
		return "XML/test2";

	}

	@GetMapping(value = "/sido")
	private String sidolist(Model model) throws Exception {
		System.out.println("---------------sido");
		ArrayList<AddressDTO> sidolist = adser.listsido();
		model.addAttribute("sidolist", sidolist);
		System.out.println(sidolist);
		return "XML/test";
	}

}
