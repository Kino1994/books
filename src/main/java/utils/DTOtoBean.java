package utils;

import es.joaquin.books.model.Book;
import es.joaquin.books.model.Comment;
import es.joaquin.books.model.api.BookPost;
import es.joaquin.books.model.api.CommentPost;

public class DTOtoBean {
	
	public static Book toBook(BookPost bookPost) {
		Book book = new Book();
		book.setTittle(bookPost.getTittle());
		book.setSummary(bookPost.getSummary());
		book.setAuthor(bookPost.getAuthor());
		book.setEditorial(bookPost.getEditorial());
		book.setPublicationYear(bookPost.getPublicationYear());
		return book;
	}
	
	public static Comment toComment(CommentPost CommentPost) {
		Comment comment = new Comment();
		comment.setMessage(CommentPost.getMessage());
		comment.setName(CommentPost.getMessage());
		comment.setScore(CommentPost.getScore());
		return comment;
	}

}
