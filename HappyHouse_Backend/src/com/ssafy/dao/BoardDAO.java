package com.ssafy.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.dto.BoardDTO;

public interface BoardDAO {
	
public void insertboard(BoardDTO b) throws SQLException;
	
	public ArrayList<BoardDTO> listboard() throws SQLException;
	
	public void deleteboard(BoardDTO b) throws SQLException;
	
	public void updateboard(BoardDTO b) throws SQLException;

}
