package es.joaquin.books.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookPost {
	
	@JsonProperty("tittle")
	private String tittle;

	@JsonProperty("summary")
	private String summary;

	@JsonProperty("author")
	private String author;

	@JsonProperty("editorial")
	private String editorial;

	@JsonProperty("publication_year")
	private Integer publicationYear;
		
	public BookPost(String tittle, String summary, String author, String editorial, Integer publicationYear) {
		this.tittle = tittle;
		this.summary = summary;
		this.author = author;
		this.editorial = editorial;
		this.publicationYear = publicationYear;
	}

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
