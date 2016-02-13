package servlet;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.Logic;

/**
 * Servlet implementation class CountryServlet
 */
@WebServlet("/FlowControl" )
		
public class FlowControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FlowControlServlet() {
        super();
        // TODO Auto-generated constructor stub


    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	
	 protected void service(HttpServletRequest request,
             HttpServletResponse response) 
             throws ServletException, IOException {
		
		 
         String logic = request.getParameter("logic");
         String className = "logic." + logic;

         try {
        	 EntityManagerFactory emf = Persistence.createEntityManagerFactory("fpa");
        	 request.setAttribute("emf", emf);
        	 Class<?> classe = Class.forName(className);

             Logic logicClass = (Logic) classe.newInstance();
             String page = logicClass.execute(request, response);
             emf.close();
             request.getRequestDispatcher(page).forward(request, response);

         } catch (Exception e) {
            throw new ServletException("Logic execution exception", e);
        	
         }
     }

}
