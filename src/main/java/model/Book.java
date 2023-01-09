package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Book {
//	isbn, book_name, price, author, publication_date, publisher_id, image, image_name
	private String isbn;
	private String bookName;
	private double price;
	private String author;
	private String publicationDate;
	private String publisherId;
	private byte[] image;

	public Book() {
		// TODO Auto-generated constructor stub
	}

	public Book(String isbn, String bookName, double price, String author, String publicationDate, String publisherId,
			byte[] image) {
		super();
		this.isbn = isbn;
		this.bookName = bookName;
		this.price = price;
		this.author = author;
		this.publicationDate = publicationDate;
		this.publisherId = publisherId;
		this.image = image;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
