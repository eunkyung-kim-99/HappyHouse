package com.ssafy.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.dao.AptDAO;
import com.ssafy.dao.AptDAOImpl;
import com.ssafy.dto.AptDTO;
import com.ssafy.dto.listParameterDTO;
import com.ssafy.util.PageNavigation;

public class AptServiceImpl implements AptService{
	
	AptDAO dao = new AptDAOImpl();
	@Override
	public ArrayList<AptDTO> select(String sido, String gugun, String dong, String pg, String key, String word) throws SQLException {
		int pgno = pg != null ? Integer.parseInt(pg) : 1;
		int countPerPage = 3;
		int start= (pgno - 1) * countPerPage;
		
		listParameterDTO listParameterDto = new listParameterDTO();
		listParameterDto.setKey(key == null ? "" : key.trim());
		listParameterDto.setWord(word == null ? "" : word.trim());
		listParameterDto.setStart(start);
		listParameterDto.setCurrentPerPage(countPerPage);
		System.out.println("서비스단에선");
		System.out.println(sido + " " + gugun + " " + dong+" "+pgno+" "+listParameterDto.toString());
		
		return dao.select(sido, gugun, dong, listParameterDto);
	}
	
	@Override
	public PageNavigation makePageNavigation(String sido, String gugun, String dong, String pg, String key, String word)
			throws Exception {
		PageNavigation pageNavigation = new PageNavigation();
		int currentPage = pg != null ? Integer.parseInt(pg) : 1;
		int naviSize = 3;
		int countPerPage = 3;
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setCountPerPage(countPerPage);
		pageNavigation.setNaviSize(naviSize);
		
		listParameterDTO listParameterDto = new listParameterDTO();
		listParameterDto.setKey(key == null ? "" : key.trim());
		listParameterDto.setWord(word == null ? "" : word.trim());
		
		int totalCount = dao.getTotalCount(sido, gugun, dong, listParameterDto);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / countPerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		pageNavigation.setStartRange(currentPage <= naviSize);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}

}
