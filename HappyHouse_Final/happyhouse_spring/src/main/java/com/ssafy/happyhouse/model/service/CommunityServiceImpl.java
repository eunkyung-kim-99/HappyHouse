package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.model.BoardDTO;
import com.ssafy.happyhouse.model.CommunityDTO;
import com.ssafy.happyhouse.model.mapper.BoardMapper;
import com.ssafy.happyhouse.model.mapper.CommunityMapper;

@Repository
public class CommunityServiceImpl implements CommunityService {
	@Autowired
	private CommunityMapper communityMapper;
	@Override
	public int insertcommunity(CommunityDTO b) throws SQLException {
		return communityMapper.insertcommunity(b);
	}

	@Override
	public ArrayList<CommunityDTO> listcommunity() throws SQLException {
		return communityMapper.listcommunity();
	}

	@Override
	public int deletecommunity(CommunityDTO b) throws SQLException {
		return communityMapper.deletecommunity(b);
	}

	@Override
	public int updatecommunity(CommunityDTO b) throws SQLException {
		return communityMapper.updatecommunity(b);
	}

	@Override
	public CommunityDTO detailcommunity(int articleno) {
		return communityMapper.detailcommunity(articleno);
	}

//	@Override
//	public List<CommunityDTO> listsido(Map<String, String> map) throws SQLException {
//		return communityMapper.listsido(map);
//	}
//
//	@Override
//	public List<CommunityDTO> listgugun(Map<String, String> map) throws SQLException {
//		return communityMapper.listgugun(map);
//	}

	@Override
	public Map<String,Object> listdong(Map<String, String> map) throws SQLException {
		Map<String,String>newmap = new HashMap<>();
		int ipg = Integer.parseInt(map.get("pg")) - 1;
		int iperpage = Integer.parseInt(map.get("perpage"));
		int start = ipg*iperpage;
		newmap.put("sidoname", map.get("sidoname"));
		newmap.put("gugunname", map.get("gugunname"));
		newmap.put("dongname", map.get("dongname"));
		newmap.put("keyword", map.get("keyword").trim());
		newmap.put("start", Integer.toString(start));
		newmap.put("perpage", Integer.toString(iperpage));
		
		
		List<CommunityDTO> list = communityMapper.listdong(newmap);
		int totalcount = communityMapper.totalCount(newmap);
		Map<String,Object> toreturn = new HashMap<>();
		toreturn.put("totalcount", totalcount);
		toreturn.put("communitylist", list);
		
		return toreturn;
	}

	@Override
	public int deletecomments(CommunityDTO b) {
		return communityMapper.deletecomments(b);
	}

	@Override
	public void updatecommunityhit(int articleno) throws SQLException {
		communityMapper.updatecommunityhit(articleno);
		
	}

//	@Override
//	public List<CommunityDTO> keywordlist(Map<String, String> map) throws SQLException {
//		return communityMapper.keywordlist(map);
//	}
	public CommunityDTO lastCommunity() throws SQLException {
		return communityMapper.lastCommunity();
	}
	

}
