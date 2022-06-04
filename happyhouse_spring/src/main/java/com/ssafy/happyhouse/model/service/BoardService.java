package com.ssafy.happyhouse.model.service;

import java.util.ArrayList;

import com.ssafy.happyhouse.model.BoardDTO;


public interface BoardService {
	
	public void insertboard(BoardDTO b) throws Exception;
	
	public ArrayList<BoardDTO> listboard() throws Exception;
	
	public void deleteboard(BoardDTO b) throws Exception;
	
	public void updateboard(BoardDTO b) throws Exception;

	public BoardDTO detailBoard(int articleno);

}
