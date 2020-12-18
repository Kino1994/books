package es.joaquin.books.model.api.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
	
	public interface Basico { }
	
	public interface Info { }
	
	public interface Extra { }
	
	public interface BookDetail extends Basico, Info { }
	
	public interface BookDetailWithComments extends Basico, Info, Extra, CommentResponse.CommentInfoDetail{ }
	
	public interface BookDetailWithCommentsFull extends Basico, Info, Extra, CommentResponse.CommentDetail{ }

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
