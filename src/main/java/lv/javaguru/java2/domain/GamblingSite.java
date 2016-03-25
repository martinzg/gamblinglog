package lv.javaguru.java2.domain;

public class GamblingSite {

	private Long id;
	private String name;
	private String url;
	private String description;

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

}
