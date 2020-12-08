package es.joaquin.books.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentPost {
	@JsonProperty("name")
	private String name = null;

	@JsonProperty("message")
	private String message = null;

	@JsonProperty("score")
	private Integer score = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}
