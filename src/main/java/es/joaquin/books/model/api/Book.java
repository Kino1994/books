package es.joaquin.books.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {
	
	@JsonProperty("id")
	private Long id = null;

	@JsonProperty("tittle")
	private String tittle = null;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

}
