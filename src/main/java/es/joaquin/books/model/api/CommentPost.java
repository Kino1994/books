package es.joaquin.books.model.api;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentPost {
	
	@NotNull
	@JsonProperty("name")
	private String name;

	@NotNull
	@JsonProperty("message")
	private String message;

	@NotNull
	@Min(1)
	@Max(5)
	@JsonProperty("score")
	private Integer score;

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
