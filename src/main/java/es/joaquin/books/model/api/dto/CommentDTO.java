package es.joaquin.books.model.api.dto;

import es.joaquin.books.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
	
	private Long id;
	
	private String text;
	
	private Integer score;
	
	private String author;
	
	private User User;

}
