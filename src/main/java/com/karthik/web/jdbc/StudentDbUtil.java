package com.karthik.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {

	private static DataSource dataSource;
	
	public StudentDbUtil(DataSource dataSource) {
		StudentDbUtil.dataSource = dataSource;
	}
	
	public List<Student> getStudents() throws Exception{
		
		List<Student> students = new ArrayList<>();
		
		Connection con = null;
		Statement stmt = null;
		ResultSet set = null;
		
		try {
			con=dataSource.getConnection();
			stmt = con.createStatement();
			String query = "select * from student order by last_name";
			set = stmt.executeQuery(query);
			
			while(set.next()) {
				int id = set.getInt("id");
				String firstName = set.getString("first_name");
				String lastName = set.getString("last_name");
				String email = set.getString("email");
				
				Student student = new Student(id,firstName,lastName,email);
				
				
				students.add(student);
				
			}
			return students;
		}finally {
			set.close();
			stmt.close();
			con.close();
		}
	}

	public static void addStudent(Student student) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "insert into student(first_name,last_name,email) values (?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, student.getFirstName());
			pstmt.setString(2, student.getLastName());
			pstmt.setString(3, student.getEmail());
			
			pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pstmt.close();
			con.close();
		}
	}

	public static Student getStudent(String theStudentId) throws Exception {
		
		Student student = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		int studentId;
		try {
			studentId = Integer.parseInt(theStudentId);
			
			con = dataSource.getConnection();
			
			String query = "select * from student where id=?";
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, studentId);
			
			set = pstmt.executeQuery();
			
			if(set.next()) {
				String firstName = set.getString("first_name");
				String lastName = set.getString("last_name");
				String email = set.getString("email");
				
				student = new Student(firstName,lastName,email);	
			}else {
				throw new Exception("Could not find the student with id :"+studentId);
			}
			
			
			return student;
		} finally {
			set.close();
			pstmt.close();
			con.close();
		}	
	}

	public static void updateStudent(Student student) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = dataSource.getConnection();
			
			String query = "update student set first_name=?,last_name=?,email=? where id=?";
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, student.getFirstName());
			pstmt.setString(2, student.getLastName());
			pstmt.setString(3, student.getEmail());
			pstmt.setInt(4, student.getId());
			
			pstmt.execute();
			
			
		} finally {
			pstmt.close();
			con.close();
		}	
	}

	public static void deleteStudent(String studentId) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = dataSource.getConnection();
			
			String query = "delete from student where id=?";
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, Integer.parseInt(studentId));
			
			pstmt.execute();
			
			
		} finally {
			pstmt.close();
			con.close();
		}
	}


		
}
