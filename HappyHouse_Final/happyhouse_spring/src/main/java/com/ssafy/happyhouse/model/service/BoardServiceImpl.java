package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.model.BoardDTO;
import com.ssafy.happyhouse.model.mapper.BoardMapper;

@Repository
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;

	@Override
	public Map<String, Object> listboard(int pg, int perpage) throws SQLException {

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

	@Override
	public BoardDTO detailBoard(int articleno) throws SQLException {
		return boardMapper.detailBoard(articleno);

	}

	@Override
	public Map<String,Object> searchlistboard(Map<String, String> map) throws Exception {
		Map<String, String> newmap = new HashMap<>();
		int ipg = Integer.parseInt(map.get("pg")) - 1;
		int iperpage = Integer.parseInt(map.get("perpage"));
		newmap.put("select", map.get("select"));
		newmap.put("content", map.get("content"));
		newmap.put("start", Integer.toString(ipg * iperpage));
		newmap.put("perpage", Integer.toString(iperpage));
		System.out.println(map.get("select")+ " : " + map.get("content") );
		
		List<BoardDTO> boardlist =  boardMapper.searchlistboard(newmap);
		int totalcount = boardMapper.totalCount(newmap);
		Map<String,Object> toreturn = new HashMap<>();
		toreturn.put("totalcount", totalcount);
		toreturn.put("boardlist", boardlist);
		
		
		return toreturn;
	}

	@Override
	public int updateboardhit(int no) throws Exception {
		return boardMapper.updateboardhit(no);
	}

}
