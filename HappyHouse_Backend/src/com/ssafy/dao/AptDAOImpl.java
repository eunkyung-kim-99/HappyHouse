package com.ssafy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.dto.AptDTO;
import com.ssafy.dto.listParameterDTO;
import com.ssafy.util.DBUtil;

public class AptDAOImpl implements AptDAO {

	@Override
	public ArrayList<AptDTO> select(String sido, String gugun, String dong, listParameterDTO listParameterDto) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "select aptName, hi.lat, hi.lng, dealAmount, dealYear, dealMonth, dealDay, area\r\n"
				+ "from baseaddress b join houseinfo hi\r\n" + "on (b.dongcode = hi.dongcode)\r\n"
				+ "join housedeal hd\r\n" + "on (hi.aptCode = hd.aptCode)\r\n"
				+ "where b.sidoName = ? "
				+ "and b.gugunName = ? "
				+ "and b.dongName = ?";
		
		
		String key = listParameterDto.getKey();
		String word = listParameterDto.getWord();
		System.out.println("여기왔나요?" + word.isEmpty());
		if (!word.isEmpty()) {
			if (key.equals("aptName")) {
				sql += "and hi.aptName like concat('%', ?, '%') \n";
			}
		}
		sql += "\n limit ?,?\n";
		
		//System.out.println(sql);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, sido);
		pstmt.setString(2, gugun);
		pstmt.setString(3, dong);
		
		
		int idx = 3;
		if (!word.isEmpty()) {
			pstmt.setString(++idx, word);
		}
		pstmt.setInt(++idx, listParameterDto.getStart());
		pstmt.setInt(++idx, listParameterDto.getCurrentPerPage());
		
		
		ResultSet rs = pstmt.executeQuery();
		ArrayList<AptDTO> list = new ArrayList<AptDTO>();
		while(rs.next()) {
			list.add(new AptDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getString(8)));
		}
		
		return list;
	}

	@Override
	public int getTotalCount(String sido, String gugun, String dong, listParameterDTO listParameterDto)
			throws SQLException {
		
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = DBUtil.getConnection();
		String sql = "select count(aptName)\r\n"
				+ "from baseaddress b join houseinfo hi\r\n" + "on (b.dongcode = hi.dongcode)\r\n"
				+ "join housedeal hd\r\n" + "on (hi.aptCode = hd.aptCode)\r\n"
				+ "where b.sidoName = ? "
				+ "and b.gugunName = ? "
				+ "and b.dongName = ?";
		
		String key = listParameterDto.getKey();
		String word = listParameterDto.getWord();
		if (!word.isEmpty()) {
			if (key.equals("aptName")) {
				sql+="and aptName like concat('%', ?, '%') \n";
			} else {
				sql+="and " + key + " = ? \n";
			}
		}

		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, sido);
		pstmt.setString(2, gugun);
		pstmt.setString(3, dong);
		
		if (!word.isEmpty())
			pstmt.setString(4, word);
		rs = pstmt.executeQuery();
		rs.next();
		cnt = rs.getInt(1);

		DBUtil.close(rs, pstmt, conn);

		return cnt;
	}
}