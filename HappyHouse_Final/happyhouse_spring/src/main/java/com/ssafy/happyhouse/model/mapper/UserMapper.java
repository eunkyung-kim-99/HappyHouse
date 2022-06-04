package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.PFileDTO;
import com.ssafy.happyhouse.model.UserDTO;



@Mapper
public interface UserMapper {
	
	public void insertUser(UserDTO u) throws SQLException;
	
	public void updateUser(UserDTO u) throws SQLException;
	
	public void deletetUser(String id) throws SQLException;

	public UserDTO login(Map idpw) throws SQLException;

	public String searchpw(String searchid) throws SQLException;

	public UserDTO userInfo(String userid) throws SQLException;

}
