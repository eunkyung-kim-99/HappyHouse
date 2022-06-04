package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.BoardDTO;
import com.ssafy.happyhouse.model.CommunityDTO;


@Mapper
public interface CommunityMapper {
	
	public int insertcommunity(CommunityDTO b) throws SQLException;
	
	public ArrayList<CommunityDTO> listcommunity() throws SQLException;
	
	public int deletecommunity(CommunityDTO b) throws SQLException;
	
	public int updatecommunity(CommunityDTO b) throws SQLException;

	public CommunityDTO detailcommunity(int articleno);
	
	public int deletecomments(CommunityDTO b);
	//검색
//	public List<CommunityDTO> listsido(Map<String, String> map) throws SQLException;
//	public List<CommunityDTO> listgugun(Map<String, String> map) throws SQLException;
	public List<CommunityDTO> listdong(Map<String, String> map) throws SQLException;
//	public List<CommunityDTO> keywordlist(Map<String, String> map) throws SQLException;
	public int totalCount(Map<String,String> map) throws SQLException;
	public void updatecommunityhit(int articleno) throws SQLException;

	public CommunityDTO lastCommunity() throws SQLException;


}
