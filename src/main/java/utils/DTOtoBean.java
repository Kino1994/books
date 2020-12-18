package utils;

import org.modelmapper.ModelMapper;

import es.joaquin.books.entities.Book;
import es.joaquin.books.model.api.dto.BookDTO;

public class DTOtoBean {
	
	private static final ModelMapper modelMapper = new ModelMapper();
	
	/*public static BookDTO toBookDTOWithComments (Book book) {
		BookDTO bookDTO = modelMapper.map(book,BookDTO.class));
		List<>
		for (Comment book.getComments()) {
			
		}
		
	}*/

	
}
