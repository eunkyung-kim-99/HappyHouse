package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.model.BoardDTO;
import com.ssafy.happyhouse.model.mapper.BoardMapper;
@Repository
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper boardMapper;
	
	

	@Override
	public ArrayList<BoardDTO> listboard() throws SQLException {
		return boardMapper.listboard();
	}

	@Override
	public void insertboard(BoardDTO b) throws Exception {
		boardMapper.insertboard(b);
		
	}

	@Override
	public void deleteboard(BoardDTO b) throws Exception {
		boardMapper.deleteboard(b);
	}

	@Override
	public void updateboard(BoardDTO b) throws Exception {
		boardMapper.updateboard(b);
		
	}

}
