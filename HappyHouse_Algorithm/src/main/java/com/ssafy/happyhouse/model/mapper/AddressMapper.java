package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.AddressDTO;

@Mapper
public interface AddressMapper {
	
	public ArrayList<AddressDTO> listsido() throws SQLException;
	
	public ArrayList<AddressDTO> listgugun(String sidoName) throws SQLException;

	public ArrayList<AddressDTO> listdong(Map<String,String> names) throws SQLException;
	
}
