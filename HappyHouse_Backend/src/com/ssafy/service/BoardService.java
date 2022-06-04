package com.ssafy.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.dto.BoardDTO;

public interface BoardService {
	
	public void insertboard(BoardDTO b) throws SQLException;
	
	public ArrayList<BoardDTO> listboard() throws SQLException;
	
	public void deleteboard(BoardDTO b) throws SQLException;
	
	public void updateboard(BoardDTO b) throws SQLException;

}
