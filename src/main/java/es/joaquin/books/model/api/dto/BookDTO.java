package es.joaquin.books.model.api.dto;

import java.util.List;

import es.joaquin.books.entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
	
	private Long id;
	
	private String title;
	
	private String summary;
	
	private String author;
	
	private String publisher;
	
	private Integer year;
	
	private List<Comment> comments;

}
