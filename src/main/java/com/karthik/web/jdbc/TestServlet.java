package com.karthik.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.jasper.tagplugins.jstl.core.Out;


@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name="jdbc/web_student_tracker")
	private DataSource datasource;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		response.setContentType("text/plain");
		
		Connection con = null;
		Statement stmt = null;
		ResultSet set = null;
		
		try {
			con = datasource.getConnection();
			String query = "select * from student";
			
			stmt = con.createStatement();
			set = stmt.executeQuery(query);
			
			while(set.next()) {
				String email = set.getString("email");
				writer.println(email);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
