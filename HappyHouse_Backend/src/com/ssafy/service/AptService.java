package com.ssafy.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.dto.AptDTO;
import com.ssafy.util.PageNavigation;

public interface AptService {

	ArrayList<AptDTO> select(String sido, String gugun, String dong, String pg, String key, String word) throws SQLException;
	PageNavigation makePageNavigation(String sido, String gugun, String dong,String pg, String key, String word) throws Exception;
}
