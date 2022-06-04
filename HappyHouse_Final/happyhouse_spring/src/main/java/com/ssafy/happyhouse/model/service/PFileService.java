package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.PFileDTO;


public interface PFileService {
	public int insertFile(PFileDTO f) throws Exception;
	public PFileDTO FileInfo(String id)throws Exception;
	public int DeleteFile(String id)throws Exception;
	public int UpdateFile(PFileDTO f)throws Exception;
}
