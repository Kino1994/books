package es.joaquin.books.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookPost {
	@JsonProperty("tittle")
	private String tittle = null;

	@JsonProperty("summary")
	private String summary = null;

	@JsonProperty("author")
	private String author = null;

	@JsonProperty("editorial")
	private String editorial = null;

	@JsonProperty("publication_year")
	private Integer publicationYear = null;

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public Integer getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(Integer publicationYear) {
		this.publicationYear = publicationYear;
	}

}
