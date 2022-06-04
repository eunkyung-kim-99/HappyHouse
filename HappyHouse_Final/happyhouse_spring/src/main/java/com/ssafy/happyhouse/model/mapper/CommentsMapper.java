package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.CommentsDTO;


@Mapper
public interface CommentsMapper {
	
	public int insertcomments(CommentsDTO b) throws SQLException;
	
	public ArrayList<CommentsDTO> listcomments(String no) throws SQLException;
	
	public int deletecomments(String no) throws SQLException;
	
	public int updatecomments(Map<String, String> map) throws SQLException;

}
