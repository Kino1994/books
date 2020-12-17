package es.joaquin.books.model.api.response;

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
public class CommentResponse {
	
	public interface Basico { }

	public interface Info { }
	
	public interface CommentDetail extends Basico, Info { }

	@JsonView(Info.class)
	@JsonProperty("id")
	private Long id;

	@JsonView(Basico.class)
	@JsonProperty("message")
	private String message;

	@JsonView(Basico.class)
	@JsonProperty("score")
	private Integer score;	

}
