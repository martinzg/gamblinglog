package lv.javaguru.java2.servlet.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import lv.javaguru.java2.database.GamblingSiteDAO;
import lv.javaguru.java2.database.GamblingSiteReviewDAO;
import lv.javaguru.java2.domain.GamblingSite;
import lv.javaguru.java2.domain.GamblingSiteReview;

@Controller
public class GamblingSiteReviewsController {

    @Autowired
	private GamblingSiteDAO gamblingSiteDAO;

    @Autowired
	private GamblingSiteReviewDAO gamblingSiteReviewDAO;

	@RequestMapping(value = "gamblingsitereviews/{gamblingSiteId}", method = { RequestMethod.GET })
	public ModelAndView processRequestGet(@PathVariable Long gamblingSiteId, HttpServletRequest req,
			HttpServletResponse resp) {
		System.out.println("Need to show reviews for site " + gamblingSiteId);
		GamblingSite gamblingSite = gamblingSiteDAO.getById(gamblingSiteId);
		List<GamblingSiteReview> gamblingSiteReviews = gamblingSiteReviewDAO.getAllReviewsBySiteId(gamblingSiteId);

		ReviewModel model = new ReviewModel();
		model.setSite(gamblingSite);
		model.setGamblingSiteReviews(gamblingSiteReviews);
		return new ModelAndView("GamblingSiteReviews", "model", model);
	}

	@RequestMapping(value = "gamblingsitereviews/{gamblingSiteId}", method = { RequestMethod.POST })
	public ModelAndView processRequestPost(HttpServletRequest req) {
		if (req.getParameter("back") != null) {
			return new ModelAndView("Redirect", "model", "/gamblingsites");
		}
		return new ModelAndView("GamblingSiteReviews", "site", null);
	}

	public static class ReviewModel {

		private GamblingSite site;
		private List<GamblingSiteReview> gamblingSiteReviews;

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

	}
}