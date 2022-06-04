package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.BoardDTO;

@Mapper
public interface BoardMapper {
	
	public void insertboard(BoardDTO b) throws SQLException;
	
	public ArrayList<BoardDTO> listboard() throws SQLException;
	
	public void deleteboard(BoardDTO b) throws SQLException;
	
	public void updateboard(BoardDTO b) throws SQLException;

}
