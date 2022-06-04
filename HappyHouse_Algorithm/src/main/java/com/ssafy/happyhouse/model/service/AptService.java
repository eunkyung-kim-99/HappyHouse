package com.ssafy.happyhouse.model.service;

import java.util.ArrayList;


import com.ssafy.happyhouse.model.AptDTO;

public interface AptService {

	ArrayList<AptDTO> select(String sido, String gugun, String dong, String key, String word) throws Exception;
}