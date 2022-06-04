package com.ssafy.service;

import java.sql.SQLException;

import com.ssafy.dto.UserDTO;


public interface UserService {
	
	public void insertUser(UserDTO u) throws SQLException;
	
	public void updateUser(UserDTO u) throws SQLException;
	
	public void deletetUser(String id) throws SQLException;

	public UserDTO login(String id, String pw) throws SQLException;

	public String searchpw(String searchid) throws SQLException;

}
