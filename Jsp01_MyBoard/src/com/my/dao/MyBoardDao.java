package com.my.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.my.dto.MyBoardDto;

public class MyBoardDao {
	Connection con = null;

	public MyBoardDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("01. driver 연결");
		} catch (ClassNotFoundException e) {
			System.out.println("01. driver 연결 실패");
			e.printStackTrace();
		}
	}

	// 전체 출력
	public List<MyBoardDto> selectAll() {

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/multi", "root", "1234");
			System.out.println("02. 계정 연결");
		} catch (SQLException e) {
			System.out.println("02. 계정 연결 실패");
			e.printStackTrace();
		}

		Statement stmt = null;
		ResultSet rs = null;
		List<MyBoardDto> res = new ArrayList<MyBoardDto>();
		String sql = " SELECT * FROM MYBOARD ";
		
		try {
			stmt = con.createStatement();
			System.out.println("03. query 준비: " + sql);
			
			rs = stmt.executeQuery(sql);
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				MyBoardDto dto = new MyBoardDto(rs.getInt(1),rs.getString(2),rs.getString(3),
										rs.getString(4),rs.getDate(5));
				res.add(dto);
			}
			
		} catch (SQLException e) { 
			System.out.println("3/4 단계 에러");
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				con.close();
				System.out.println("05. db 종료\n");
			} catch (SQLException e) {
				System.out.println("05. db 종료 에러\n");
				e.printStackTrace();
			} 
		}
		return res;
	}
	
	//선택출력
		public MyBoardDto selectOne(int myno) {
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/multi", "root", "1234");
				System.out.println("02. 계정 연결");
			} catch (SQLException e) { 
				System.out.println("02. 계정 연결 실패");
				e.printStackTrace();
			}
			PreparedStatement pstm = null;
			ResultSet rs = null;
			MyBoardDto res = null;
			
			String sql = "SELECT * FROM MYBOARD WHERE MYNO=?";
			
			try {
				pstm = con.prepareStatement(sql);
				pstm.setInt(1, myno);
				System.out.println("03. query 준비: "+sql);
				
				rs = pstm.executeQuery();
				System.out.println("04. query 실행 및 리턴");
				while(rs.next()) {
					res = new MyBoardDto();
					res.setMyno(rs.getInt(1));
					res.setMyname(rs.getString(2));
					res.setMytitle(rs.getString(3));
					res.setMycontent(rs.getString(4));
					res.setMydate(rs.getDate(5));
				}
			} catch (SQLException e) { 

				System.out.println("3/4 단계 오류");
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					pstm.close();
					con.close();
					System.out.println("05. db 종류\n");
				} catch (SQLException e) {
					System.out.println("05. db 종류 오류\n");
					e.printStackTrace();
				}
			} 
			return res;
		}
	
}
