package logic;

import javax.persistence.EntityManagerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Country;
import service.CountryJpaController;

public class InsertUpdateCountry implements Logic {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub

		Country country = new Country();
		country.setLanguage(req.getParameter("language").trim());
		country.setName(req.getParameter("name").trim());
		long id = -1;
		if (!req.getParameter("id").isEmpty()) {
			id = Long.parseLong(req.getParameter("id"));
			country.setId(id);
		}
		if (country.getName().isEmpty()) {
			req.setAttribute("errorMessageInsertEditCountryName", "Name must be provided");

			req.setAttribute("country", country);
			return "/WEB-INF/view/country_insert_edit.jsp";

		} else {

			EntityManagerFactory emf = (EntityManagerFactory) req.getAttribute("emf");
			CountryJpaController service = new CountryJpaController(emf);

			if (id == -1) {
				service.create(country);
			} else {

				service.edit(country);
			}

			return "FlowControl?logic=ListCountries";
		}
	}

}