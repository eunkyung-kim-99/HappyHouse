package com.ssafy.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.dto.AddressDTO;

public interface AddressService {
	
	public ArrayList<AddressDTO> listsido() throws SQLException;
	
	public ArrayList<AddressDTO> listgugun(String sidoName) throws SQLException;

	public ArrayList<AddressDTO> listdong(String sidoName, String gugunName) throws SQLException;
	
}
