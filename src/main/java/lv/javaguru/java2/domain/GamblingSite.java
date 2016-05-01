package lv.javaguru.java2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sites")

public class GamblingSite {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "Name", nullable = false)
	private String name;

	@Column(name = "URL", nullable = false)
	private String url;

	@Column(name = "Description", nullable = false)
	private String description;

	@Column(name = "UserID", nullable = false)
	private Long userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String siteName) {
		this.name = siteName;
	}

	public String getURL() {
		return url;
	}

	public void setURL(String siteURL) {
		this.url = siteURL;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String siteDescription) {
		this.description = siteDescription;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
