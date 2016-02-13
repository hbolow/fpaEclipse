package logic;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Country;
import entity.InflationRate;
import service.CountryJpaController;
import service.InflationRateJpaController;

public class ListCountries implements Logic {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		EntityManagerFactory emf = (EntityManagerFactory)req.getAttribute("emf");
		CountryJpaController countryService = new CountryJpaController(emf);
		List<Country> countries = countryService.findCountryEntities();
		req.setAttribute("countries", countries);
		InflationRateJpaController inflationRateService = new InflationRateJpaController(emf);
		List<InflationRate> inflationRates = inflationRateService.findInflationRateEntities();
		req.setAttribute("inflationRates", inflationRates);
		return "/WEB-INF/view/countries.jsp";
	}

}
