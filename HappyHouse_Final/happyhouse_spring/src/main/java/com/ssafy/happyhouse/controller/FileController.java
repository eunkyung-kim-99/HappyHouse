package com.ssafy.happyhouse.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.ibatis.javassist.NotFoundException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.happyhouse.model.CommunityDTO;
import com.ssafy.happyhouse.model.CommunityImageDTO;
import com.ssafy.happyhouse.model.PFileDTO;
import com.ssafy.happyhouse.model.service.CommunityImageService;
import com.ssafy.happyhouse.model.service.PFileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@CrossOrigin("*")
@RestController
@RequestMapping("/file")
public class FileController {
	@Autowired
	ServletContext servletContext;
	@Autowired
	PFileService pservice;
	@Autowired
	CommunityImageService comuiservice;

	private static String SUCCESS = "success";
	private static String FAIL = "fail";

	@ApiOperation(value = "파일을 업로드합니다.")
	@PostMapping("{id}")
	public ResponseEntity<String> fileUpload(
			@RequestParam(value = "profileimg", required = false) MultipartFile[] profileimg,
			@PathVariable("id") String id) throws IllegalStateException, IOException, SQLException {

		if (profileimg == null) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}

		System.out.println("파일업로드");
		String path = servletContext.getRealPath("/upload");
		System.out.println("파일 경로 : " + path);
		String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String saveFolder = path + File.separator + today + File.separator + id;
		File folder = new File(saveFolder);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		PFileDTO pf = new PFileDTO();
		String name = profileimg[0].getOriginalFilename();

		pf.setId(id);
		pf.setName(name);
		pf.setPath(saveFolder);
		try {
			pservice.insertFile(pf);
			profileimg[0].transferTo(new File(folder, name));
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("여긴가??");
			e.printStackTrace();
			return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "파일을 업데이트합니다.")
	@PutMapping("{id}")
	public ResponseEntity<String> fileUpdate(
			@RequestParam(value = "profileimg", required = false) MultipartFile[] profileimg,
			@PathVariable("id") String id) throws Exception {

		if (profileimg == null) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		System.out.println(profileimg[0]);
		PFileDTO fdto = null;
		fdto = pservice.FileInfo(id);

		System.out.println("파일업데이트");
		String path = servletContext.getRealPath("/upload");
		System.out.println(path);
		String today = new SimpleDateFormat("yyyyMMdd").format(new Date());

		String saveFolder = path + File.separator + today + File.separator + id;
		File folder = new File(saveFolder);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		PFileDTO pf = new PFileDTO();
		String name = profileimg[0].getOriginalFilename();

		pf.setId(id);
		pf.setName(name);
		pf.setPath(saveFolder);
		System.out.println("업데이트 이미지 저장 장소 : " + saveFolder);
		if (fdto != null) {
			System.out.println("1");
			File file = new File(fdto.getPath() + File.separator + fdto.getName());
			if (file.exists()) {
				file.delete();
			}
			try {
				System.out.println("업데이트 거의 성공");
				pservice.UpdateFile(pf);
				profileimg[0].transferTo(new File(folder, name));
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			} catch (Exception e) {
				System.out.println("업데이트 거의 실패");
				e.printStackTrace();
				return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
			}

		} else {
			System.out.println("2");
			try {
				pservice.insertFile(pf);
				profileimg[0].transferTo(new File(folder, name));
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
			}
		}

	}

	@ApiOperation(value = "파일을 삭제합니다.")
	@DeleteMapping("{id}")
	public ResponseEntity<String> fileDelete(@PathVariable("id") String id) throws Exception {

		PFileDTO fdto = pservice.FileInfo(id);
		File file = new File(fdto.getPath() + File.separator + fdto.getName());
		if (file.exists()) {
			file.delete();
		}
		try {
			pservice.DeleteFile(id);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "파일을 받아옵니다.")
	@GetMapping("{id}")
	public ResponseEntity<String> fileDownLoad(@PathVariable("id") String id) throws Exception {
		int readCount;
		PFileDTO fdto;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			fdto = pservice.FileInfo(id);
			System.out.println(fdto);
			String path = fdto.getPath() + File.separator + fdto.getName();

			InputStream imagestream = new FileInputStream(path);
			System.out.println(path);
			byte[] imageByteArray = null;
			byte[] buffer = new byte[1024];
			try {
				while ((readCount = imagestream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, readCount);
				}
				imageByteArray = outputStream.toByteArray();
				imagestream.close();
				outputStream.close();
				System.out.println("이미지 파일 : " + imageByteArray);

			} catch (IOException e) {
				throw new Exception("파일을 변환하는데 문제가 발생했습니다.");
			}
			String result = new String(Base64.encodeBase64(imageByteArray));
			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

	}

	// 커뮤니티

	@ApiOperation(value = "커뮤니티 파일을 업데이트합니다.")
	@PutMapping("/communityimg/{no}")
	public ResponseEntity<String> communityfileUpdate(
			@RequestParam(value = "communityimg", required = false) MultipartFile[] communityimage,
			@PathVariable("no") String no) throws Exception {
		System.out.println("업데이트 정보 : " + communityimage);
		if (communityimage == null) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		System.out.println(communityimage[0]);
		List<CommunityImageDTO> fdto = new ArrayList<>();
		fdto = comuiservice.CommunityImageInfo(no);

		System.out.println("파일업데이트");
		String path = servletContext.getRealPath("/upload") + "/community";
		System.out.println(path);
		String today = new SimpleDateFormat("yyyyMMdd").format(new Date());

		String saveFolder = path + File.separator + today + File.separator + no;
		File folder = new File(saveFolder);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		for (CommunityImageDTO cmdto : fdto) {
			File file = new File(cmdto.getPath() + File.separator + cmdto.getName());
			if (file.exists()) {
				file.delete();
			}
		}
		comuiservice.DeleteCommunityImage(no);

		for (MultipartFile mf : communityimage) {
			CommunityImageDTO pf = new CommunityImageDTO();
			String name = mf.getOriginalFilename();

			pf.setNo(no);
			pf.setName(name);
			pf.setPath(saveFolder);
			try {
				comuiservice.insertCommunityImage(pf);
				mf.transferTo(new File(folder, name));

			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);

	}

	@ApiOperation(value = "파일을 삭제합니다.")
	@DeleteMapping("/communityimg/{no}")
	public ResponseEntity<String> communityfileDelete(@PathVariable("no") String no) throws Exception {
		List<CommunityImageDTO> fdto = comuiservice.CommunityImageInfo(no);
		for (CommunityImageDTO cidto : fdto) {
			File file = new File(cidto.getPath() + File.separator + cidto.getName());
			if (file.exists()) {
				file.delete();
			}
		}
		try {
			comuiservice.DeleteCommunityImage(no);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "커뮤니티 사진 입력", response = String.class)
	@PostMapping("/communityimg/{no}")
	public ResponseEntity<String> communityfileUpload(
			@RequestParam(value = "communityimg", required = false) List<MultipartFile> communityimage,
			@PathVariable("no") String no) throws IllegalStateException, IOException, SQLException {
		System.out.println("사진 입력 오긴함?");
		System.out.println("communityimage : " + communityimage);
		if (communityimage == null) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		System.out.println("파일업로드");
		String path = servletContext.getRealPath("/upload") + "/community";
		System.out.println("파일 경로 : " + path);
		String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String saveFolder = path + File.separator + today + File.separator + no;
		File folder = new File(saveFolder);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		for (MultipartFile image : communityimage) {
			CommunityImageDTO pf = new CommunityImageDTO();
			String name = image.getOriginalFilename();
			pf.setNo(no);
			pf.setName(name);
			pf.setPath(saveFolder);
			try {
				comuiservice.insertCommunityImage(pf);
				image.transferTo(new File(folder, name));
			} catch (Exception e) {
				System.out.println("여긴가??");
				e.printStackTrace();
				return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);

	}

	@ApiOperation(value = "커뮤니티 파일을 받아옵니다.")
	@PostMapping("/communityimg/files/{no}")
	public ResponseEntity<ArrayList<String[]>> communityfileDownLoad(@PathVariable("no") String no) throws Exception {
		int readCount;
		List<CommunityImageDTO> fdto;
		ArrayList<String[]> toreturn = new ArrayList<>();

		try {
			fdto = comuiservice.CommunityImageInfo(no);
			System.out.println("??? " + fdto);
			for (CommunityImageDTO image : fdto) {
				System.out.println(image);
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				String path = image.getPath() + File.separator + image.getName();
				InputStream imagestream = new FileInputStream(path);


				System.out.println("이거아님?" + path);
				byte[] imageByteArray = null;
				byte[] buffer = new byte[1024];
				try {
					while ((readCount = imagestream.read(buffer)) != -1) {
						outputStream.write(buffer, 0, readCount);
					}
					imageByteArray = outputStream.toByteArray();
					imagestream.close();
					outputStream.close();
					System.out.println("이미지 파일 : " + imageByteArray);

				} catch (IOException e) {
					e.printStackTrace();
					throw new Exception("파일을 변환하는데 문제가 발생했습니다.");

				}
				String[] item = new String[2];
				item[0] = image.getName();
				item[1] = new String(Base64.encodeBase64(imageByteArray));
				toreturn.add(item);

			}
			return new ResponseEntity<ArrayList<String[]>>(toreturn, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<ArrayList<String[]>>(HttpStatus.BAD_REQUEST);
		} 

	}

}
