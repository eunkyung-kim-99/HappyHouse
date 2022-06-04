package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.BoardDTO;
import com.ssafy.happyhouse.model.CommunityDTO;


public interface CommunityService {
	public int insertcommunity(CommunityDTO b) throws SQLException;
	
	public ArrayList<CommunityDTO> listcommunity() throws SQLException;
	
	public int deletecommunity(CommunityDTO b) throws SQLException;
	
	public int updatecommunity(CommunityDTO b) throws SQLException;

	public CommunityDTO detailcommunity(int articleno) throws SQLException;;

	public int deletecomments(CommunityDTO b);
	
	//검색
//	public List<CommunityDTO> listsido(Map<String, String> map) throws SQLException;
//	public List<CommunityDTO> listgugun(Map<String, String> map) throws SQLException;
	public Map<String,Object> listdong(Map<String, String> map) throws SQLException;

	public void updatecommunityhit(int articleno) throws SQLException;


//	public List<CommunityDTO> keywordlist(Map<String, String> map) throws SQLException;

	public CommunityDTO lastCommunity() throws SQLException;

}
