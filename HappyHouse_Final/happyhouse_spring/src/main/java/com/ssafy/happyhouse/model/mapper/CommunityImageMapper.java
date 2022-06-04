package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.CommunityDTO;
import com.ssafy.happyhouse.model.CommunityImageDTO;

@Mapper
public interface CommunityImageMapper {
	public int InsertCommunityImage(CommunityImageDTO f) throws SQLException;

	public int UpdateCommunityImage(CommunityImageDTO f) throws SQLException;
	public int DeleteCommunityImage(String id) throws SQLException;
	public List<CommunityImageDTO> CommunityImageInfo(String id) throws SQLException;
}
