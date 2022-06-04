package com.ssafy.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.dao.AddressDAO;
import com.ssafy.dao.AddressDAOImpl;
import com.ssafy.dto.AddressDTO;

public class AddressServiceImpl implements AddressService{
	static AddressDAO adao=null;
	
	public AddressServiceImpl() {
		adao = new AddressDAOImpl();
	}

	@Override
	public ArrayList<AddressDTO> listsido() throws SQLException {
		return adao.listsido();
	}

	@Override
	public ArrayList<AddressDTO> listgugun(String sidoName) throws SQLException {
		// TODO Auto-generated method stub
		return adao.listgugun(sidoName);
	}

	@Override
	public ArrayList<AddressDTO> listdong(String sidoName, String gugunName) throws SQLException {
		// TODO Auto-generated method stub
		return adao.listdong(sidoName, gugunName);
	}
	

}
