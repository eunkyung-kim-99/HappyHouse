package com.ssafy.happyhouse.model.service;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.model.AddressDTO;


public interface AddressService {
	
	public List<AddressDTO> listsido() throws Exception;
	
	public List<AddressDTO> listgugun(String sidoName) throws Exception;

	public List<AddressDTO> listdong(String sidoName, String gugunName) throws Exception;
	
}
