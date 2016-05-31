package lv.javaguru.java2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reviews")

public class GamblingSiteReview {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "ReviewTitle", nullable = false)
	private String reviewTitle;

	@Column(name = "ReviewText", nullable = false)
	private String reviewText;

	@Column(name = "ReviewRating", nullable = false)
	private int reviewRating;

	@Column(name = "UserID", nullable = false)
	private Long userId;

	@Column(name = "SiteID", nullable = false)
	private Long siteId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public int getReviewRating() {
		return reviewRating;
	}

	public void setReviewRating(int reviewRating) {
		this.reviewRating = reviewRating;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
