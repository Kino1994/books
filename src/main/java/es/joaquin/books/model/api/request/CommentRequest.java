package es.joaquin.books.model.api.request;

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
public class CommentRequest {
	
	public interface Basico { }

	public interface Info { }
	
	public interface CommentDetail extends Basico, Info { }

	@JsonView(Basico.class)
	@JsonProperty("id")
	private Integer id;

	@JsonView(Info.class)
	@JsonProperty("message")
	private String message;

	@JsonView(Info.class)
	@JsonProperty("score")
	private Integer score;

}