/*
 * Author: Yashar Rahvar
 * Student#: A00871798
 * COMP 4656 Assignment2
 */
package ca.bcit.comp4656.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.XMLGregorianCalendar;

import ca.bcit.comp4656.data.EmployeeObj;
import ca.bcit.comp4656.decorators.DateConverter;
import ca.bcit.comp4656.employee.types.AddRequest;
import ca.bcit.comp4656.employee.types.AddResponse;
import ca.bcit.comp4656.employee.types.DeleteRequest;
import ca.bcit.comp4656.employee.types.DeleteResponse;
import ca.bcit.comp4656.employee.types.Employee;
import ca.bcit.comp4656.employee.types.FindRequest;
import ca.bcit.comp4656.employee.types.FindResponse;
import ca.bcit.comp4656.employee.types.GetEmployeesRequest;
import ca.bcit.comp4656.employee.types.GetEmployeesResponse;
import ca.bcit.comp4656.jaxws.services.AddError;
import ca.bcit.comp4656.jaxws.services.BasicEmployeeServiceService;
import ca.bcit.comp4656.jaxws.services.DeleteError;
import ca.bcit.comp4656.jaxws.services.EmployeeServicePort;
import ca.bcit.comp4656.jaxws.services.FindError;
import ca.bcit.comp4656.jaxws.services.GetEmployeesError;

public class EmployeeServices {

	/**
	 * This method is responsible to delete employee.
	 * 
	 * @param request
	 * @param service
	 */
	public void delete(HttpServletRequest request, BasicEmployeeServiceService service) {
		if (null != request.getParameter("employeeID")) {
			try {
				service = new BasicEmployeeServiceService();
				EmployeeServicePort port = service.getBasicEmployeeServicePort();
				DeleteRequest deleteRequest = new DeleteRequest();
				deleteRequest.setDeleteEmployeeID((request.getParameter("employeeID")));

				DeleteResponse deleteResponse = port.delete(deleteRequest);
				String code = deleteResponse.getResponseCode().getCode();
				String description = deleteResponse.getResponseCode().getDescription();
				request.setAttribute("deleteCode", "Code: " + code);
				request.setAttribute("deleteDescription", "Description: " + description);
			} catch (NumberFormatException e) {
				request.setAttribute("deleteError", "Invalid Input");
			} catch (DeleteError e) {
				request.setAttribute("deleteError", e.getMessage());
			} catch (Exception e) {
				request.setAttribute("deleteError", e.getMessage());
			}
		}
	}

	/**
	 * This method is responsible to find employee.
	 * 
	 * @param request
	 * @param service
	 */
	public void find(HttpServletRequest request, BasicEmployeeServiceService service) {
		if (null != request.getParameter("employeeID")) {
			try {
				service = new BasicEmployeeServiceService();
				EmployeeServicePort port = service.getBasicEmployeeServicePort();
				FindRequest findRequest = new FindRequest();
				findRequest.setFindEmployeeID((request.getParameter("employeeID")));
				FindResponse findResponse = port.find(findRequest);
				String code = findResponse.getResponseCode().getCode();
				String description = findResponse.getResponseCode().getDescription();
				request.setAttribute("employeeObj", findResponse.getResult());
				request.setAttribute("findCode", "Code: " + code);
				request.setAttribute("findDescription", "Description: " + description);
			} catch (NumberFormatException e) {
				request.setAttribute("findError", "Invalid Input");
			} catch (FindError e) {
				request.setAttribute("findError", e.getMessage());
			} catch (Exception e) {
				request.setAttribute("findError", e.getMessage());

			}
		}
	}

	/**
	 * This method is responsible to getAllEmployees employee.
	 * 
	 * @param request
	 * @param service
	 */
	public List<EmployeeObj> getAllEmployees(BasicEmployeeServiceService service) {
		List<Employee> employees = null;
		List<EmployeeObj> employeesObj = new ArrayList<EmployeeObj>();
		try {
			service = new BasicEmployeeServiceService();
			EmployeeServicePort port = service.getBasicEmployeeServicePort();
			GetEmployeesRequest getEmployeesReq = new GetEmployeesRequest();
			GetEmployeesResponse getEmployeesRes = port.getEmployees(getEmployeesReq);
			employees = getEmployeesRes.getGetEmployees();

			for (Employee employee : employees) {
				EmployeeObj employeeObj = new EmployeeObj();
				employeeObj.setId(employee.getId());
				employeeObj.setFirstName(employee.getFirstName());
				employeeObj.setLastName(employee.getLastName());
				employeeObj.setDob(DateConverter.toDate(employee.getDob()));
				employeesObj.add(employeeObj);
			}
		} catch (GetEmployeesError e) {
			e.printStackTrace();
		}
		return employeesObj;
	}

	/**
	 * This method is responsible to add employee.
	 * 
	 * @param request
	 * @param service
	 */
	public void add(HttpServletRequest request, BasicEmployeeServiceService service) {
		boolean checkForSuccess = true;
		Employee employee = null;
		EmployeeServicePort port = null;
		if (null != request.getParameter("addNewEmployee")) {
			try {
				service = new BasicEmployeeServiceService();
				port = service.getBasicEmployeeServicePort();
			} catch (Exception e) {
				request.setAttribute("addError", e.getMessage());
			}

			employee = new Employee();
			employee.setId(request.getParameter("employeeID"));
			employee.setFirstName(request.getParameter("employeeFirstName"));
			employee.setLastName(request.getParameter("employeeLastName"));
			String dobString = request.getParameter("employeeDOB");
			try {
				Date dobDate = DateConverter.formatter.parse(dobString);
				XMLGregorianCalendar dobXGC = DateConverter.toXMLGregorianCalendar(dobDate);
				employee.setDob(dobXGC);
			} catch (Exception e) {
				request.setAttribute("addError", "Invalid Date Format");
				checkForSuccess = false;
			}

			if (checkForSuccess) {
				try {
					AddRequest addRequest = new AddRequest();
					addRequest.setAddEmployee(employee);
					AddResponse addResponse = port.add(addRequest);
					String confirmation = addResponse.getResponseCode().getDescription() + " "
							+ addResponse.getResponseCode().getCode();
					System.out.println(confirmation);
					request.setAttribute("addConfirmation", confirmation);
					String code = addResponse.getResponseCode().getCode();
					String description = addResponse.getResponseCode().getDescription();
					request.setAttribute("addCode", "Code: " + code);
					request.setAttribute("addDescription", "Description: " + description);
				} catch (AddError e) {
					request.setAttribute("addError", e.getMessage());
				} catch (Exception e) {
					request.setAttribute("addError", e.getMessage());
				}
			}
		}
	}

}
