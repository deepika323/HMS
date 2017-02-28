package controller.doctorServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Department;
import model.bean.Doctor;
import model.bean.Technician;
import model.bl.AdminBusinessLogic;

/**
 * Servlet implementation class ViewOrRemoveOrUpdateMedicalReportController
 */
public class ViewOrRemoveOrUpdateMedicalReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer regNo=Integer.parseInt(request.getParameter("regNo"));
		AdminBusinessLogic abl=new AdminBusinessLogic();
		try {
			try {
				request.setAttribute("medicalReport",abl.viewMedicalReports(regNo));
				//appList=dbl.myAppointments(doctorId);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String operation=request.getParameter("selectedValue");
		
		
		//To DELETE
		
		if(operation.equalsIgnoreCase("Remove"))
		{
			String servlet="./deleteMedicalReport";
			String button="DELETE";
			
		request.setAttribute("servlet", servlet);
		request.setAttribute("button", button);
		request.setAttribute("regNo", regNo);
		}
		
		//TO FIND AND DISPLAY
		else if(operation.equalsIgnoreCase("Find"))
		{
			String servlet="./doctor.jsp";
			String button="CONTINUE";
			
		request.setAttribute("servlet", servlet);
		request.setAttribute("button", button);

		}
		
		//TO UPDATE
		else {
			ArrayList<Doctor> doctorList=new ArrayList<Doctor>();
			ArrayList<Technician> technicianList=new ArrayList<Technician>();
			ArrayList<Department> departmentList=new ArrayList<Department>();
			try {
				doctorList=abl.listDoctor();
				technicianList=abl.listTechnician();
				departmentList=abl.listDepartment();
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			 request.setAttribute("doctorList", doctorList);
			 request.setAttribute("technicianList", technicianList);
			 request.setAttribute("departmentList", departmentList);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/updateMedicalReportForm.jsp");
		    rd.forward(request, response);
			
		}

		//PrintWriter writerA = response.getWriter();
		//writerA.println(operation);
		
		    RequestDispatcher rd = getServletContext().getRequestDispatcher("/ViewOrRemoveOrUpdateFormMedicalReport.jsp");
		    rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}