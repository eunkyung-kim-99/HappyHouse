package com.ssafy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.dto.BoardDTO;
import com.ssafy.util.DBUtil;

public class BoardDAOImpl implements BoardDAO{
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public void insertboard(BoardDTO b) throws SQLException {
		con = DBUtil.getConnection();
		String sql = "insert into board (id, subject, contents) values (?,?,?)";
		ps = con.prepareStatement(sql);
		ps.setString(1, b.getId());
		ps.setString(2, b.getSubject());
		ps.setString(3, b.getContents());
		ps.executeUpdate();
		DBUtil.close(ps, con);
		
	}

	@Override
	public ArrayList<BoardDTO> listboard() throws SQLException {
		con = DBUtil.getConnection();
		String sql = "select no, id, subject, contents from board";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		while(rs.next()) {
			list.add(new BoardDTO(rs.getString("no"), rs.getString("id"), 
					rs.getString("subject"), rs.getString("contents")));
		}
		DBUtil.close(rs, ps, con);
		return list;
	}

	@Override
	public void deleteboard(BoardDTO b) throws SQLException {
		con = DBUtil.getConnection();
		String sql = "delete from board where no = ?";
		ps = con.prepareStatement(sql);
		ps.setString(1, b.getNo());
		ps.executeUpdate();
		DBUtil.close(ps, con);
		
	}

	@Override
	public void updateboard(BoardDTO b) throws SQLException {
		con = DBUtil.getConnection();
		String sql = "update board set subject = ?, contents = ? where no = ?";
		ps = con.prepareStatement(sql);
		ps.setString(1, b.getSubject());
		ps.setString(2, b.getContents());
		ps.setString(3, b.getNo());
		ps.executeUpdate();
		DBUtil.close(ps, con);
		
	}

}
