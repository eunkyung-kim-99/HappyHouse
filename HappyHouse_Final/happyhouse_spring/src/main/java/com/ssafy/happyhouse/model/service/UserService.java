package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;



import com.ssafy.happyhouse.model.UserDTO;




public interface UserService {
	
	public void insertUser(UserDTO u) throws Exception;
	
	public void updateUser(UserDTO u) throws Exception;
	
	public void deletetUser(String id) throws Exception;

	public UserDTO login(String id, String pw) throws Exception;

	public String searchpw(String searchid) throws Exception;
	public UserDTO userInfo(String id) throws Exception;

}
