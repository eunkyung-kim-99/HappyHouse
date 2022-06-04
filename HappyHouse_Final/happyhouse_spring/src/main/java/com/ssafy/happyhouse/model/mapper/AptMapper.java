package com.ssafy.happyhouse.model.mapper;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.AptDTO;
import com.ssafy.happyhouse.model.AptDetailDTO;

@Mapper
public interface AptMapper {

	ArrayList<AptDTO> select(Map<String, String> map) throws Exception;

	ArrayList<AptDetailDTO> selectdetail(String aptcode) throws Exception;

}