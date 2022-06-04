package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.BoardDTO;

@Mapper
public interface BoardMapper {
	
	public int insertboard(BoardDTO b) throws SQLException;
	
	public Map<String, Object> listboard() throws SQLException;
	
	public int deleteboard(BoardDTO b) throws SQLException;
	
	public int updateboard(BoardDTO b) throws SQLException;

	public BoardDTO detailBoard(int articleno)throws SQLException;

	public List<BoardDTO> searchlistboard(Map<String, String> map)throws SQLException;

	public int updateboardhit(int no)throws SQLException;
	
	public int totalCount(Map<String,String> map)throws SQLException;
}
