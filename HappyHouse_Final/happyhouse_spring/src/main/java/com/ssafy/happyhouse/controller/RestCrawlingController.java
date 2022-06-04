package com.ssafy.happyhouse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.CommunityDTO;
import com.ssafy.happyhouse.model.FoodDTO;
import com.ssafy.happyhouse.model.UserDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@RestController
@RequestMapping("/crawling")
@CrossOrigin("*")
@Api("크롤링 컨트롤러 V1")
public class RestCrawlingController {
	private static final Logger logger = LoggerFactory.getLogger(RestCrawlingController.class);

	@ApiOperation(value = "크롤링", response = String.class)
	@GetMapping("{sidocode}/{guguncode}/{dongcode}")
	private ResponseEntity<Map<String, List<FoodDTO>>> crawling(@PathVariable("sidocode") String sidocode,
			@PathVariable("guguncode") String guguncode, @PathVariable("dongcode") String dongcode) throws Exception {
		System.out.println(sidocode + " " + guguncode + " " + dongcode);
		String URL = "https://www.mangoplate.com/search/" + guguncode + " " + dongcode;
		Document doc;
		List<FoodDTO> foods = new ArrayList<FoodDTO>();
		List<FoodDTO> cafes = new ArrayList<FoodDTO>();
		try {
			doc = Jsoup.connect(URL).get();
//			Elements elem = doc.select(".date");
//			String[] str = elem.text().split(" ");
			Elements foodsTitle = doc.select("div.list-restaurant-item div.info h2.title");
			Elements foodsLink = doc.select("div.list-restaurant-item div.info a");
			Elements foodsPoint = doc.select("div.list-restaurant-item div.info strong.point");
			Elements foodsEtc = doc.select("div.list-restaurant-item div.info p.etc > span");
			Elements foodsImg = doc.select("div.list-restaurant-item div.thumb > img");
			Elements foodsReview = doc.select("div.list-restaurant-item span.review_count");

			System.out.println("foodsTitle" + foodsTitle);
			System.out.println("foodsLink" + foodsLink);
			System.out.println("foodsPoint" + foodsPoint);
			System.out.println("foodsEtc" + foodsEtc);
			System.out.println("foodsImg" + foodsImg);
			System.out.println("foodsReview" + foodsReview);

			for (int i = 0; i < foodsTitle.size(); i++) {
				String etc = foodsEtc.get(i).text();
				String title = foodsTitle.get(i).text();
				String link = foodsLink.get(i).attr("abs:href");
				String point = foodsPoint.get(i).text();
				String img = foodsImg.get(i).attr("abs:data-original");
				String review = foodsReview.get(i).text();
				if (etc.equals("카페 / 디저트") || etc.equals("베이커리")) {
					cafes.add(new FoodDTO(title, link, point, etc, img, review));

				} else {
					foods.add(new FoodDTO(title, link, point, etc, img, review));
				}
			}

			System.out.println(foods);
			System.out.println(cafes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, List<FoodDTO>> map = new LinkedHashMap<String, List<FoodDTO>>();
		map.put("foods", foods);
		map.put("cafes", cafes);
		return new ResponseEntity<Map<String, List<FoodDTO>>>(map, HttpStatus.OK);
	}

	@ApiOperation(value = "크롤링 아파트 이미지", response = String.class)
	@GetMapping("{sidocode}/{guguncode}/{dongcode}/{aptName}")
	private ResponseEntity<String> crawling_img(@PathVariable("sidocode") String sidocode,
			@PathVariable("guguncode") String guguncode, @PathVariable("dongcode") String dongcode,
			@PathVariable("aptName") String aptName) throws Exception {
		System.out.println(sidocode + " " + guguncode + " " + dongcode + " " + aptName);

		String URL = "https://www.google.co.kr/search?q=" + sidocode + " " + guguncode + " " + dongcode + " " + aptName
				+ "아파트&tbm=isch";
		Document doc;
		String src="";
		try {
			doc = Jsoup.connect(URL).get();
//			Elements elem = doc.select(".date");
//			String[] str = elem.text().split(" ");
			Elements element = doc.select("img");

			src = element.get(2).attr("abs:src");
			System.out.println("======================"+element);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<String>(src, HttpStatus.OK);
	}

}
