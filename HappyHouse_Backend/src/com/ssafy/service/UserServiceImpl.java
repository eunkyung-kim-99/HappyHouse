package com.ssafy.service;

import java.sql.SQLException;

import com.ssafy.dao.UserDAO;
import com.ssafy.dao.UserDAOImpl;
import com.ssafy.dto.UserDTO;

public class UserServiceImpl implements UserService {
	static UserDAO udao;

	public UserServiceImpl() {
		udao = new UserDAOImpl();
	}

	@Override
	public void insertUser(UserDTO u) throws SQLException {
		udao.insertUser(u);
	}

	@Override
	public void updateUser(UserDTO u) throws SQLException {
		udao.updateUser(u);
	}

	@Override
	public void deletetUser(String id) throws SQLException {
		udao.deletetUser(id);
	}

	@Override
	public UserDTO login(String id, String pw) throws SQLException {
		// TODO Auto-generated method stub
		return udao.login(id, pw);
	}

	@Override
	public String searchpw(String searchid) throws SQLException {
		return udao.searchpw(searchid);
	}


}
