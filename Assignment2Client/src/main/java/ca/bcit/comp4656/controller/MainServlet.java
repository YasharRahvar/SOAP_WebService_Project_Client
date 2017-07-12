/**
 * @author Yashar Rahvar
 * Date: 10/Sep/2016
 * Project: SOAP Web Service Project Client
 * Class: MainServlet
 * MainServlet Servlet. 
 */
package ca.bcit.comp4656.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

import ca.bcit.comp4656.jaxws.services.BasicEmployeeServiceService;

import ca.bcit.comp4656.services.EmployeeServices;

public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@WebServiceRef(wsdlLocation = "http://localhost:8080/A00871798ws/BasicEmployeeService?wsdl")
	private static BasicEmployeeServiceService service;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EmployeeServices employeeServices = new EmployeeServices();

		if (null != request.getParameter("findByID")) {
			employeeServices.find(request, service);
		}

		if (null != request.getParameter("deleteByID")) {
			employeeServices.delete(request, service);
		}

		if (null != request.getParameter("addNewEmployee")) {
			employeeServices.add(request, service);
		}
		request.getServletContext().setAttribute("employees", employeeServices.getAllEmployees(service));
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
