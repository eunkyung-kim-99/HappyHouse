package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.AptDTO;
import com.ssafy.happyhouse.model.mapper.AptMapper;

@Service
public class AptServiceImpl implements AptService{
	
	@Autowired
	private	AptMapper aptMapper;
	@Override
	public ArrayList<AptDTO> select(String sido, String gugun, String dong, String key, String word) throws Exception {
		Map<String,String> map = new HashMap<>();
		map.put("sidoName", sido);
		map.put("gugunName", gugun);
		map.put("dongName", dong);
		map.put("key",key == null|| key == ""? "1":key.trim());
		map.put("word",word == null|| word == ""? "1": "%" + word.trim() + "%");
		
		System.out.println(map.toString());

		return aptMapper.select(map);
	}

	


}