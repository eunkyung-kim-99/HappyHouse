package com.ssafy.happyhouse.model.service;

import java.util.ArrayList;

import com.ssafy.happyhouse.model.AddressDTO;


public interface AddressService {
	
	public ArrayList<AddressDTO> listsido() throws Exception;
	
	public ArrayList<AddressDTO> listgugun(String sidoName) throws Exception;

	public ArrayList<AddressDTO> listdong(String sidoName, String gugunName) throws Exception;
	
}
