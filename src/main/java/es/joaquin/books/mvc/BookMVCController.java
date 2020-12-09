package es.joaquin.books.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.joaquin.books.model.Book;
import es.joaquin.books.model.api.BookPost;
import es.joaquin.books.model.api.CommentPost;
import es.joaquin.books.service.BookService;
import springfox.documentation.annotations.ApiIgnore;


@Controller
@ApiIgnore
public class BookMVCController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/")
	public String showBooks (Model model) {
		
		model.addAttribute("books", bookService.findAll());
		
		return "index";		
	}
	
	@PostMapping("/book/new")
	public String newPost(Model model, BookPost bookPost) {

		bookService.save(bookPost);

		return "saved_book";
	}
	
	@GetMapping("/book/{numBook}")
	public String showPost(Model model, @PathVariable Long numBook) {

		Book book = bookService.findById(numBook);
		
		model.addAttribute("book", book);
		model.addAttribute("comments", book.getComments());


		return "show_book";
	}
	
	@GetMapping("/book/{numBook}/comment")
	public String newComment(Model model, @PathVariable Long numBook, CommentPost commentPost) {
		
		Book book = bookService.put(numBook, commentPost);
		
		model.addAttribute("book", book);
		model.addAttribute("comments", book.getComments());

		return "forward:/book/" + numBook;
	}
	
	@GetMapping("/book/{numBook}/comment/{numComment}")
	public String deleteComment (Model model, @PathVariable Long numBook, @PathVariable Long numComment) {
		
		bookService.delete(numBook, numComment);
		
		Book book = bookService.findById(numBook);
		
		model.addAttribute("book", book);
		model.addAttribute("comments", book.getComments());

		return "forward:/book/" + numBook;
	}

	
}
