package com.ssafy.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.dao.BoardDAO;
import com.ssafy.dao.BoardDAOImpl;
import com.ssafy.dto.BoardDTO;

public class BoardServiceImpl implements BoardService{
	static BoardDAO bdao;
	public BoardServiceImpl() {
		bdao = new BoardDAOImpl();
	}

	@Override
	public void insertboard(BoardDTO b) throws SQLException {
		bdao.insertboard(b);
	}

	@Override
	public ArrayList<BoardDTO> listboard() throws SQLException {
		return bdao.listboard();
	}

	@Override
	public void deleteboard(BoardDTO b) throws SQLException {
		bdao.deleteboard(b);
	}

	@Override
	public void updateboard(BoardDTO b) throws SQLException {
		bdao.updateboard(b);
	}

}
