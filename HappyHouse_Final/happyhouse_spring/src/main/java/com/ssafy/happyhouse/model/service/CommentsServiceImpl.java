package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.model.BoardDTO;
import com.ssafy.happyhouse.model.CommentsDTO;
import com.ssafy.happyhouse.model.CommunityDTO;
import com.ssafy.happyhouse.model.mapper.BoardMapper;
import com.ssafy.happyhouse.model.mapper.CommentsMapper;
import com.ssafy.happyhouse.model.mapper.CommunityMapper;

@Repository
public class CommentsServiceImpl implements CommentsService {

	@Autowired
	private CommentsMapper commentsmapper;

	@Override
	public int insertcomments(CommentsDTO b) throws SQLException {
		return commentsmapper.insertcomments(b);
	}

	@Override
	public ArrayList<CommentsDTO> listcomments(String no) throws SQLException {
		return commentsmapper.listcomments(no);
	}

	@Override
	public int deletecomments(String no) throws SQLException {
		return commentsmapper.deletecomments(no);
	}

	@Override
	public int updatecomments(Map<String, String> map)  throws SQLException {
		return commentsmapper.updatecomments(map);
	}

}
