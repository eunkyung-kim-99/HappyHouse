package com.ssafy.happyhouse.model.service;


import java.util.List;

import com.ssafy.happyhouse.model.CommunityImageDTO;

public interface CommunityImageService {

	
	public int insertCommunityImage(CommunityImageDTO f) throws Exception;
	public List<CommunityImageDTO> CommunityImageInfo(String id)throws Exception;
	public int DeleteCommunityImage(String id)throws Exception;
	public int UpdateCommunityImage(CommunityImageDTO f)throws Exception;
}
