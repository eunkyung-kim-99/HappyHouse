package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



import com.ssafy.happyhouse.model.CommunityImageDTO;
import com.ssafy.happyhouse.model.mapper.CommunityImageMapper;





@Repository
public class CommunityImageServiceImpl implements CommunityImageService {
	@Autowired
	CommunityImageMapper cmapper;
	
	@Override
	public int insertCommunityImage(CommunityImageDTO f) throws Exception {
		// TODO Auto-generated method stub
		return cmapper.InsertCommunityImage(f);
	}

	@Override
	public List<CommunityImageDTO> CommunityImageInfo(String id) throws Exception {
		// TODO Auto-generated method stub
		return cmapper.CommunityImageInfo(id);
	}

	@Override
	public int DeleteCommunityImage(String id) throws Exception {
		// TODO Auto-generated method stub
		return cmapper.DeleteCommunityImage(id);
	}

	@Override
	public int UpdateCommunityImage(CommunityImageDTO f) throws Exception {
		// TODO Auto-generated method stub
		return cmapper.UpdateCommunityImage(f);
	}

}
