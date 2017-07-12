/**
 * @author Yashar Rahvar
 * Date: 10/Sep/2016
 * Project: SOAP Web Service Project 
 * Class: EmployeeFilter
 * EmployeeFilter which helps getting all employees. 
 */
package ca.bcit.comp4656.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import ca.bcit.comp4656.jaxws.services.BasicEmployeeServiceService;
import ca.bcit.comp4656.services.EmployeeServices;

@WebFilter(filterName = "employeeFilter", urlPatterns = "/*")
public class EmployeeFilter implements Filter {

	private static BasicEmployeeServiceService service;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		try {
			service = new BasicEmployeeServiceService();
			EmployeeServices employeeServices = new EmployeeServices();
			filterConfig.getServletContext().setAttribute("employees", employeeServices.getAllEmployees(service));
		} catch (javax.xml.ws.WebServiceException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {

	}

}
