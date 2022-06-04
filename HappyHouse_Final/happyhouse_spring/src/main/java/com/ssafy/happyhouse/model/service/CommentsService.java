package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.ssafy.happyhouse.model.CommentsDTO;


public interface CommentsService {
	public int insertcomments(CommentsDTO b) throws SQLException;
	
	public ArrayList<CommentsDTO> listcomments(String no) throws SQLException;
	
	public int deletecomments(String no) throws SQLException;
	
	public int updatecomments(Map<String, String> map) throws SQLException;

}
