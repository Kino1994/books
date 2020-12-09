package es.joaquin.books.model.api;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import es.joaquin.books.model.Comment;

public class Book {
	
public interface Basico { }
	
	public interface Info { }
	
	public interface Extra { }
	
	public interface BookDetail extends Basico, Info { }
	
	public interface BookDetailWithComments extends Basico, Info, Extra { }

	@JsonView(Basico.class)
	@JsonProperty("id")
	private Long id;

	@JsonView(Basico.class)
	@JsonProperty("tittle")
	private String tittle;

	@JsonView(Info.class)
	@JsonProperty("summary")
	private String summary;

	@JsonView(Info.class)
	@JsonProperty("author")
	private String author;

	@JsonView(Info.class)
	@JsonProperty("editorial")
	private String editorial;

	@JsonView(Info.class)
	@JsonProperty("publication_year")
	private Integer publicationYear;

	@JsonView(Extra.class)
	@JsonProperty("comments")
	private List<Comment> comments;

	public Book(String tittle, String summary, String author, String editorial, Integer publicationYear) {
		this.tittle = tittle;
		this.summary = summary;
		this.author = author;
		this.editorial = editorial;
		this.publicationYear = publicationYear;
		this.comments = new ArrayList<Comment>();
	}

	public Book(String tittle, String summary, String author, String editorial, Integer publicationYear,
			List<Comment> comments) {
		this.tittle = tittle;
		this.summary = summary;
		this.author = author;
		this.editorial = editorial;
		this.publicationYear = publicationYear;
		this.comments = comments;
	}



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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}
