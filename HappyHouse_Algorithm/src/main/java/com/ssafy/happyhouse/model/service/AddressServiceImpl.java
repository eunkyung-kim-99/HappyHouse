package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.model.AddressDTO;
import com.ssafy.happyhouse.model.mapper.AddressMapper;

@Repository
public class AddressServiceImpl implements AddressService {
	@Autowired
	private AddressMapper adao;

	@Override
	public ArrayList<AddressDTO> listsido() throws SQLException {
		System.out.println("시도리스트 서비스");
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
		Map<String, String> names = new HashMap<>();
		names.put("sidoName", sidoName);
		names.put("gugunName", gugunName);
		return adao.listdong(names);
	}

}
