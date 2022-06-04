package com.ssafy.happyhouse.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.BoardDTO;


public interface BoardService {
	
	public void insertboard(BoardDTO b) throws Exception;
	
	public Map<String,Object> listboard(int pg, int perpage) throws Exception;
	
	public void deleteboard(BoardDTO b) throws Exception;
	
	public void updateboard(BoardDTO b) throws Exception;

	public BoardDTO detailBoard(int articleno)throws Exception;

	public Map<String,Object> searchlistboard(Map<String, String> map)throws Exception;

	public int updateboardhit(int articleno)throws Exception;

}
