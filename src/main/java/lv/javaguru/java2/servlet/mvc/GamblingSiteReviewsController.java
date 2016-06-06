package lv.javaguru.java2.servlet.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.GamblingSiteDAO;
import lv.javaguru.java2.database.GamblingSiteReviewDAO;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.GamblingSite;
import lv.javaguru.java2.domain.GamblingSiteReview;

@Controller
public class GamblingSiteReviewsController {

	@Autowired
	private UserDAO userDAO;

    @Autowired
	private GamblingSiteDAO gamblingSiteDAO;

    @Autowired
	private GamblingSiteReviewDAO gamblingSiteReviewDAO;

	@RequestMapping(value = "gamblingsitereviews/{gamblingSiteId}", method = { RequestMethod.GET })
	public ModelAndView processRequestGet(
			@PathVariable Long gamblingSiteId,
			@RequestParam(value = "status", required = false) Integer status,
			HttpServletRequest req,
			HttpServletResponse resp) {
		GamblingSite gamblingSite = gamblingSiteDAO.getById(gamblingSiteId);
		List<GamblingSiteReview> gamblingSiteReviews = gamblingSiteReviewDAO.getAllReviewsBySiteId(gamblingSiteId);

		ReviewModel model = new ReviewModel();
		model.setSite(gamblingSite);
		model.setGamblingSiteReviews(gamblingSiteReviews);

		String message;
		switch (status != null ? status : 0) {
		case 1:
			message = "Success!";
			break;
		case 2:
			message = "Failure!";
			break;
		default:
			message = null;
			break;
		}
		model.setMessage(message);
		return new ModelAndView("GamblingSiteReviews", "model", model);
	}

	@RequestMapping(value = "gamblingsitereviews/{gamblingSiteId}", method = { RequestMethod.POST })
	public ModelAndView processRequestPost(@PathVariable Long gamblingSiteId, HttpServletRequest req) {
		if (req.getParameter("back") != null) {
			return new ModelAndView("Redirect", "model", "/gamblingsites");
		}
		try {
			GamblingSiteReview review = new GamblingSiteReview();
			review.setSiteId(gamblingSiteId);
			getGamblingSiteReviewData(review, req);
			gamblingSiteReviewDAO.create(review);
			return new ModelAndView("Redirect", "model", "/gamblingsitereviews/" + gamblingSiteId + "?status=1");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("Redirect", "model", "/gamblingsitereviews/" + gamblingSiteId + "?status=2");
		}
	}

	private void getGamblingSiteReviewData(GamblingSiteReview review, HttpServletRequest request) throws DBException {
		review.setReviewTitle(request.getParameter("reviewTitle"));
		review.setReviewText(request.getParameter("reviewText"));
		review.setReviewRating(Integer.parseInt(request.getParameter("reviewRating")));
		review.setUserId(userDAO.getIdByEmail(request.getUserPrincipal().getName()));
	}

	public static class ReviewModel {

		private GamblingSite site;
		private List<GamblingSiteReview> gamblingSiteReviews;
		private String message;

		public GamblingSite getSite() {
			return site;
		}

		public void setSite(GamblingSite site) {
			this.site = site;
		}

		public List<GamblingSiteReview> getGamblingSiteReviews() {
			return gamblingSiteReviews;
		}

		public void setGamblingSiteReviews(List<GamblingSiteReview> gamblingSiteReviews) {
			this.gamblingSiteReviews = gamblingSiteReviews;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
}