package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.JobDAO;
import com.entity.Jobs;

@WebServlet("/add_jobs")
public class AddPostServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			String title = req.getParameter("title");
			String desc = req.getParameter("desc");
			String category = req.getParameter("category");
			String status = req.getParameter("status");
			String location = req.getParameter("location");
			
			
		
			Jobs j = new Jobs();
			
			j.setTitle(title);
			j.setDescription(desc);
			j.setCategory(category);
			j.setStatus(status);
			j.setLocation(location);
			
			
			
			
	
			
			
			HttpSession session = req.getSession();	
			
			JobDAO dao = new JobDAO(DBConnect.getConn());
			boolean f = dao.addJobs(j);
			
			if(f) {
				
				session.setAttribute("succMsg", "Job post Succesfully...");
				resp.sendRedirect("add_jobs.jsp");
			}else {
				session.setAttribute("succMsg", "Something Wrong On Server...");
				resp.sendRedirect("add_jobs.jsp");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	
}
