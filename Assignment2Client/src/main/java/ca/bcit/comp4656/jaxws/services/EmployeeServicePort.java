
package ca.bcit.comp4656.jaxws.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import ca.bcit.comp4656.employee.types.AddRequest;
import ca.bcit.comp4656.employee.types.AddResponse;
import ca.bcit.comp4656.employee.types.DeleteRequest;
import ca.bcit.comp4656.employee.types.DeleteResponse;
import ca.bcit.comp4656.employee.types.FindRequest;
import ca.bcit.comp4656.employee.types.FindResponse;
import ca.bcit.comp4656.employee.types.GetEmployeesRequest;
import ca.bcit.comp4656.employee.types.GetEmployeesResponse;
import ca.bcit.comp4656.employee.types.ObjectFactory;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "EmployeeServicePort", targetNamespace = "http://www.bcit.ca/comp4656/employee/xml")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface EmployeeServicePort {


    /**
     * 
     * @param findReq
     * @return
     *     returns ca.bcit.comp4656.employee.types.FindResponse
     * @throws FindError
     */
    @WebMethod
    @WebResult(name = "findResponse", targetNamespace = "http://www.bcit.ca/comp4656/employee/types/", partName = "findRes")
    public FindResponse find(
        @WebParam(name = "findRequest", targetNamespace = "http://www.bcit.ca/comp4656/employee/types/", partName = "findReq")
        FindRequest findReq)
        throws FindError
    ;

    /**
     * 
     * @param deleteReq
     * @return
     *     returns ca.bcit.comp4656.employee.types.DeleteResponse
     * @throws DeleteError
     */
    @WebMethod
    @WebResult(name = "deleteResponse", targetNamespace = "http://www.bcit.ca/comp4656/employee/types/", partName = "deleteRes")
    public DeleteResponse delete(
        @WebParam(name = "deleteRequest", targetNamespace = "http://www.bcit.ca/comp4656/employee/types/", partName = "deleteReq")
        DeleteRequest deleteReq)
        throws DeleteError
    ;

    /**
     * 
     * @param addReq
     * @return
     *     returns ca.bcit.comp4656.employee.types.AddResponse
     * @throws AddError
     */
    @WebMethod
    @WebResult(name = "addResponse", targetNamespace = "http://www.bcit.ca/comp4656/employee/types/", partName = "addRes")
    public AddResponse add(
        @WebParam(name = "addRequest", targetNamespace = "http://www.bcit.ca/comp4656/employee/types/", partName = "addReq")
        AddRequest addReq)
        throws AddError
    ;

    /**
     * 
     * @param getEmployeesReq
     * @return
     *     returns ca.bcit.comp4656.employee.types.GetEmployeesResponse
     * @throws GetEmployeesError
     */
    @WebMethod
    @WebResult(name = "getEmployeesResponse", targetNamespace = "http://www.bcit.ca/comp4656/employee/types/", partName = "getEmployeesRes")
    public GetEmployeesResponse getEmployees(
        @WebParam(name = "getEmployeesRequest", targetNamespace = "http://www.bcit.ca/comp4656/employee/types/", partName = "getEmployeesReq")
        GetEmployeesRequest getEmployeesReq)
        throws GetEmployeesError
    ;

}
