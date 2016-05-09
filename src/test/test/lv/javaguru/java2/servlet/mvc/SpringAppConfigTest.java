package test.lv.javaguru.java2.servlet.mvc;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import lv.javaguru.java2.database.GamblingSiteDAO;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.servlet.mvc.GamblingSiteAddController;

@Configuration
@ComponentScan(basePackages = { "lv.javaguru.java2.servlet.mvc" }, useDefaultFilters = false, includeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = GamblingSiteAddController.class) })
public class SpringAppConfigTest {

	@Bean
	public GamblingSiteDAO siteDAO() {
		return Mockito.mock(GamblingSiteDAO.class);
	}

	@Bean
	public UserDAO userDAO() {
		return Mockito.mock(UserDAO.class);
	}
}
