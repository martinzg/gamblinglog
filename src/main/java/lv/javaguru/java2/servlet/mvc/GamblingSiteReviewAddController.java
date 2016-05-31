package lv.javaguru.java2.servlet.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.GamblingSiteReviewDAO;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.GamblingSiteReview;

@Controller
public class GamblingSiteReviewAddController {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private GamblingSiteReviewDAO siteReviewDAO;

	@RequestMapping(value = "gambling-site-review/{siteId}", method = { RequestMethod.GET })
	public ModelAndView processRequestGet(long siteId, HttpServletRequest req, HttpServletResponse resp) {
		return new ModelAndView("GamblingSiteReview", "model", null);
	}

	@RequestMapping(value = "gambling-site-review/{siteId}", method = { RequestMethod.POST })
	public ModelAndView processRequestPost(HttpServletRequest request) {
		if (request.getParameter("back") != null) {
			return new ModelAndView("Redirect", "model", "/gamblingsites");
		}
		try {
			GamblingSiteReview review = new GamblingSiteReview();
			getGamblingSiteReviewData(review, request);
			siteReviewDAO.create(review);
			return new ModelAndView("GamblingSiteReview", "message", "Success!");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("GamblingSiteReview", "message", "Failure!");
		}
	}

	private void getGamblingSiteReviewData(GamblingSiteReview review, HttpServletRequest request) throws DBException {
		review.setReviewTitle(request.getParameter("reviewTitle"));
		review.setReviewText(request.getParameter("reviewText"));
		review.setReviewRating(Integer.parseInt(request.getParameter("reviewRating")));
		review.setUserId(userDAO.getIdByEmail(request.getUserPrincipal().getName()));
	}


}
