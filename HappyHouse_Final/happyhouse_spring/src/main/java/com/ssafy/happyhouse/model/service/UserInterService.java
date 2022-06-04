package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.model.UserInterDTO;




public interface UserInterService {
	
	public int insertUserInter(UserInterDTO u) throws Exception;
	
//	public void updateUserInter(UserInterDTO u) throws Exception;
	
	public int deletetUserInter(UserInterDTO u) throws Exception;
	
	public List<UserInterDTO> listUserInter(String id) throws SQLException;

	public UserInterDTO listUserInterDuplication(UserInterDTO u)throws SQLException;

	public List<UserInterDTO> listuserinterdetail(UserInterDTO u)throws SQLException;

	public void deleteuserinterdetail(String no)throws SQLException;





}
