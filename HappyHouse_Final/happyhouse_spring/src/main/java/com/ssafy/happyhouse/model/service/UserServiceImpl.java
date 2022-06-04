package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.model.UserDTO;
import com.ssafy.happyhouse.model.mapper.UserMapper;

@Repository
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper UserMapper;

	@Override
	public void deletetUser(String id) throws SQLException {
		UserMapper.deletetUser(id);
	}

	@Override
	public String searchpw(String searchid) throws SQLException {
		return UserMapper.searchpw(searchid);
	}

	@Override
	public void insertUser(UserDTO u) throws Exception {
		UserMapper.insertUser(u);

	}

	@Override
	public void updateUser(UserDTO u) throws Exception {
		UserMapper.updateUser(u);

	}

	@Override
	public UserDTO login(String id, String pw) throws Exception {
		Map<String, String> idpw = new HashMap<String, String>();
		idpw.put("id", id);
		idpw.put("pw", pw);
		System.out.println(idpw.get("id") + "          -- " + idpw.get("pw"));
		return UserMapper.login(idpw);
	}

	@Override
	public UserDTO userInfo(String userid) throws Exception {
		return UserMapper.userInfo(userid);
	}

	

}
