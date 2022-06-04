package com.ssafy.happyhouse.model.service;

import java.util.ArrayList;


import com.ssafy.happyhouse.model.AptDTO;
import com.ssafy.happyhouse.model.AptDetailDTO;

public interface AptService {

	ArrayList<AptDTO> select(String sido, String gugun, String dong, String key, String word) throws Exception;

	ArrayList<AptDetailDTO> selectdetail(String aptcode) throws Exception;
}