package es.joaquin.books.model.api.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import es.joaquin.books.model.api.response.CommentResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
	
public interface Basico { }
	
	public interface Info { }
	
	public interface Extra { }
	
	public interface BookDetail extends Basico, Info { }
	
	public interface BookDetailWithComments extends Basico, Info, Extra{ }
	
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
	@JsonProperty("publisher")
	private String publisher;

	@JsonView(Info.class)
	@JsonProperty("year")
	private Integer year;

	@JsonView(Extra.class)
	@JsonProperty("comments")
	private List<CommentResponse> comments;

}
