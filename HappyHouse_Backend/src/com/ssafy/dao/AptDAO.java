package com.ssafy.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.dto.AptDTO;
import com.ssafy.dto.listParameterDTO;

public interface AptDAO {

	ArrayList<AptDTO> select(String sido, String gugun, String dong, listParameterDTO listParameterDto) throws SQLException;
	int getTotalCount(String sido, String gugun, String dong,listParameterDTO listParameterDto) throws SQLException;
}
