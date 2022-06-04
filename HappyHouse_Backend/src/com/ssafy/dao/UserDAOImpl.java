package com.ssafy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.dto.UserDTO;
import com.ssafy.util.DBUtil;

public class UserDAOImpl implements UserDAO {
	Connection con = null;
	PreparedStatement ps = null;

	@Override
	public void insertUser(UserDTO u) throws SQLException {
		con = DBUtil.getConnection();
		String sql = "insert into user values (?,?,?,?,?)";
		ps = con.prepareStatement(sql);
		ps.setString(1, u.getId());
		ps.setString(2, u.getPassword());
		ps.setString(3, u.getName());
		ps.setString(4, u.getEmail());
		ps.setString(5, u.getPhonenumber());
		ps.executeUpdate();
		DBUtil.close(ps, con);
	}

	@Override
	public void updateUser(UserDTO u) throws SQLException {
		con = DBUtil.getConnection();
		String sql = "update user set password = ? , name = ?, email = ?, phonenumber = ? where id = ?";
		ps = con.prepareStatement(sql);
		ps.setString(1, u.getPassword());
		ps.setString(2, u.getName());
		ps.setString(3, u.getEmail());
		ps.setString(4, u.getPhonenumber());
		ps.setString(5, u.getId());
		ps.executeUpdate();
		DBUtil.close(ps, con);
	}

	@Override
	public void deletetUser(String id) throws SQLException {
		con = DBUtil.getConnection();
		String sql = "delete from user where id = ?";
		ps = con.prepareStatement(sql);
		ps.setString(1, id);
		ps.executeUpdate();
		DBUtil.close(ps, con);
	}

	@Override
	public UserDTO login(String id, String pw) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "select * from user where id=? and password=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, pw);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			UserDTO u = new UserDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5));
			DBUtil.close(rs, pstmt, conn);
			return u;
		}
		DBUtil.close(rs, pstmt, conn);
		return null;
	}

	@Override
	public String searchpw(String searchid) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "select password from user where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, searchid);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			String pw = rs.getString(1);
			DBUtil.close(rs, pstmt, conn);
			return pw;
		}
		DBUtil.close(rs, pstmt, conn);
		return null;
	}

}
