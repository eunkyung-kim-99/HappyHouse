package com.ssafy.happyhouse.model.mapper;


import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.PFileDTO;

@Mapper
public interface PFileMapper {
	public int InsertProfile(PFileDTO f) throws SQLException;

	public int UpdateProfile(PFileDTO f) throws SQLException;
	public int DeleteProfile(String id) throws SQLException;
	public PFileDTO FileInfo(String id) throws SQLException;
}
