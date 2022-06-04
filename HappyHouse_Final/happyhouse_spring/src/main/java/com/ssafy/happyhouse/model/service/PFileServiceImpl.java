package com.ssafy.happyhouse.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.model.PFileDTO;
import com.ssafy.happyhouse.model.mapper.PFileMapper;

@Repository
public class PFileServiceImpl implements PFileService {
	
	
	@Autowired
	PFileMapper pfmapper;

	@Override
	public int insertFile(PFileDTO f) throws Exception {
		// TODO Auto-generated method stub
		return pfmapper.InsertProfile(f);
	}

	@Override
	public PFileDTO FileInfo(String id) throws Exception {
		// TODO Auto-generated method stub
		return pfmapper.FileInfo(id);
	}

	@Override
	public int DeleteFile(String id) throws Exception {
		// TODO Auto-generated method stub
		return pfmapper.DeleteProfile(id);
	}

	@Override
	public int UpdateFile(PFileDTO f) throws Exception {
		// TODO Auto-generated method stub
		return pfmapper.UpdateProfile(f);
	}
	
}
