package es.joaquin.books.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

public class Comment {
	
	public interface Basico { }

	public interface Info { }
	
	public interface CommentDetail extends Basico, Info { }

	@JsonView(Info.class)
	@JsonProperty("id")
	private Long id;

	@JsonView(Basico.class)
	@JsonProperty("name")
	private String name = null;

	@JsonView(Basico.class)
	@JsonProperty("message")
	private String message = null;

	@JsonView(Basico.class)
	@JsonProperty("score")
	private Integer score = null;

	public Comment(String name, String message, Integer score) {
		this.name = name;
		this.message = message;
		this.score = score;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
