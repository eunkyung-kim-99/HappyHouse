package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.model.UserInterDTO;
import com.ssafy.happyhouse.model.mapper.UserInterMapper;

@Repository
public class UserInterServiceImpl implements UserInterService {

	
	@Autowired
	private UserInterMapper userintermapper;
	@Override
	public int insertUserInter(UserInterDTO u) throws Exception {
		return userintermapper.insertUserInter(u);
	}

	@Override
	public int deletetUserInter(UserInterDTO u) throws Exception{
		return userintermapper.deletetUserInter(u);
	}

	@Override
	public List<UserInterDTO> listUserInter(String id) throws SQLException {
		System.out.println("유저인터 조회");
		System.out.println("DDF"+userintermapper.listUserInter(id));
		return userintermapper.listUserInter(id);
	}

	@Override
	public UserInterDTO listUserInterDuplication(UserInterDTO u) throws SQLException {
		return userintermapper.listUserInterDuplication(u);
	}

	@Override
	public void deleteuserinterdetail(String no) throws SQLException {
		userintermapper.deleteuserinterdetail(no);
		
	}

	@Override
	public List<UserInterDTO> listuserinterdetail(UserInterDTO u) throws SQLException {
		return userintermapper.listuserinterdetail(u);
	}



	

}
