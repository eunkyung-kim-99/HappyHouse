package com.ssafy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.dto.AddressDTO;
import com.ssafy.util.DBUtil;

public class AddressDAOImpl implements AddressDAO{

	@Override
	public ArrayList<AddressDTO> listsido() throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "select sidoName from sidocode";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ArrayList<AddressDTO> list = new ArrayList<AddressDTO>();
		while(rs.next()) {
			list.add(new AddressDTO(rs.getString(1), "", ""));
		}
		return list;
	}

	@Override
	public ArrayList<AddressDTO> listgugun(String sidoName) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "select distinct gugunName \r\n" + 
				"from baseaddress\r\n" + 
				"where sidoName = ?;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, sidoName);
		ResultSet rs = ps.executeQuery();
		ArrayList<AddressDTO> list = new ArrayList<AddressDTO>();
		while(rs.next()) {
			list.add(new AddressDTO("", rs.getString(1), ""));
		}
		return list;
	}

	@Override
	public ArrayList<AddressDTO> listdong(String sidoName, String gugunName) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "select distinct dongName\r\n" + 
				"from baseaddress\r\n" + 
				"where sidoName = ? and gugunName = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, sidoName);
		ps.setString(2, gugunName);
		ResultSet rs = ps.executeQuery();
		ArrayList<AddressDTO> list = new ArrayList<AddressDTO>();
		while(rs.next()) {
			list.add(new AddressDTO("", "", rs.getString(1)));
		}
		return list;
	}

}
