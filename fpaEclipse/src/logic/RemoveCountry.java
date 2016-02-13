package logic;

import javax.persistence.EntityManagerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Country;
import service.CountryJpaController;

public class RemoveCountry implements Logic{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		long id = Long.parseLong(req.getParameter("id"));
	    Country country = new Country();
	    country.setId(id);
		EntityManagerFactory emf = (EntityManagerFactory)req.getAttribute("emf");
		CountryJpaController service = new CountryJpaController(emf);
		service.destroy(id);
	    return "FlowControl?logic=ListCountries";
	}

}
