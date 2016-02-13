package logic;

import javax.persistence.EntityManagerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Country;
import service.CountryJpaController;

public class FetchCountry implements Logic {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub

		Country country = new Country();
		long id = -1;
		if (!req.getParameter("id").isEmpty()) {
			id = Long.parseLong(req.getParameter("id"));
			EntityManagerFactory emf = (EntityManagerFactory) req.getAttribute("emf");
			CountryJpaController service = new CountryJpaController(emf);
			country = service.findCountry(id);
			req.setAttribute("country", country);
			return "/WEB-INF/view/country_insert_edit.jsp?logic=EditCountrys&id=" + id;

		} else {
			req.setAttribute("country", country);
			return "/WEB-INF/view/country_insert_edit.jsp";

		}

	}

}