package es.joaquin.books.model.api.dto;

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
	
	private String message;
	
	private Integer score;
	
	private String nick;
	
	private String email;
	
	private Long bookId;
	
	private UserDTO user;
	
	public CommentDTO setBookId(Long bookId) {
		this.bookId = bookId;
		return this;
	}

}
