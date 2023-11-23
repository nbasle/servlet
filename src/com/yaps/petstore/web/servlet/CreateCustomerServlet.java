/*
 * Created on 3 déc. 2005
 * createCustomerServlet.java
 */
package com.yaps.petstore.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;


//import java.rmi.RemoteException;




import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yaps.petstore.common.rmi.RMIConstant;

//import com.yaps.petstore.common.delegate.CustomerDelegate;
import com.yaps.petstore.common.delegate.CustomerDelegate;
import com.yaps.petstore.common.dto.CustomerDTO;

import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.common.exception.DuplicateKeyException;
import com.yaps.petstore.common.logging.Trace;




/**
 * @author Veronique
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public final class CreateCustomerServlet extends HttpServlet implements RMIConstant {
//	 ======================================
    // =             Attributes             =
    // ======================================

    // Used for logging
    private final transient String _cname = this.getClass().getName();

    // ======================================
    // =         Entry point method         =
    // ======================================
    /*public void init(ServletConfig config) throws ServletException {
    	try {
            Naming.rebind(CUSTOMER_SERVICE, new CustomerService());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    
    public void service(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        final String mname = "service";
        Trace.entering(_cname, mname);
        String URL_ERROR; 
        response.setContentType("text/html");
        final PrintWriter out = response.getWriter();
         
       final CustomerDTO customerDTO = new CustomerDTO(request.getParameter("id"), request.getParameter("firstname"), request.getParameter("lastname"));
       customerDTO.setTelephone(request.getParameter("telephone"));
	   customerDTO.setEmail(request.getParameter("email"));
	   customerDTO.setCity(request.getParameter("city"));
	   customerDTO.setCountry(request.getParameter("country"));
	   customerDTO.setState(request.getParameter("state"));
	   customerDTO.setStreet1(request.getParameter("street1"));
	   customerDTO.setStreet2(request.getParameter("street2"));
	   customerDTO.setZipcode(request.getParameter("zipcode"));
	   customerDTO.setCreditCardExpiryDate(request.getParameter("creditCardExpiryDate"));
	   customerDTO.setCreditCardNumber(request.getParameter("creditCardNumber"));
	   customerDTO.setCreditCardType(request.getParameter("creditCardType"));
       try {
       	CustomerDelegate.createCustomer(customerDTO);
    } catch (DuplicateKeyException e) {
    	out.print(request.getParameter("exception"));
    	URL_ERROR = "/error?exception="+e;
       request.getRequestDispatcher(URL_ERROR).forward(request, response);    
       //getServletContext().getRequestDispatcher(URL_ERROR).forward(request,response); 
    } catch(CheckException e){
    	URL_ERROR = "/error?exception="+e;
    	out.print(request.getParameter("exception"));
    	request.getRequestDispatcher(URL_ERROR).forward(request, response);
    	//getServletContext().getRequestDispatcher(URL_ERROR).forward(request,response);
    }
    catch(Exception e) {
    	URL_ERROR = "/error?exception="+e;
    	out.print(request.getParameter("exception"));
    	request.getRequestDispatcher(URL_ERROR).forward(request,response);
    	//getServletContext().getRequestDispatcher(URL_ERROR).forward(request,response);
    } 
        
        out.println("<html>");
        out.println("<head>");
        out.println("<title>YAPS Create customer</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1><center>Customer created !</center></h1>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
 
}
