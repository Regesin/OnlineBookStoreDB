package com.bookapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import com.bookapp.bean.Book;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;

public class BookImpl implements BookInter {

	Scanner input = new Scanner(System.in);

	@Override
	public void addBook(Book book) {
		PreparedStatement statement = null;
		Connection connection = ModelDAO.openConnection();
		try {
			statement = connection.prepareStatement("insert into online_book_details values(?,?,?,?,?)");
			statement.setInt(1, book.getBookId());
			statement.setString(2, book.getTitle());
			statement.setString(3, book.getCategory());
			statement.setString(4, book.getAuthor());
			statement.setDouble(5, book.getPrice());
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ModelDAO.closeConnection();
		}
	}

	@Override
	public boolean deleteBook(int bookId) {
		// TODO Auto-generated method stub
		PreparedStatement statement = null;
		Connection connection = ModelDAO.openConnection();
		Statement statement1 = null;
		int cat = 0;
		try {
			statement1 = connection.createStatement();
			ResultSet rs = statement1.executeQuery("select BookID from online_book_details");

			while (rs.next()) {
				cat = rs.getInt("BookID");
				if (cat == bookId) {
					statement = connection.prepareStatement("delete from online_book_details where BookID = ? ");
					statement.setDouble(1, bookId);
					statement.execute();
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Book getBookById(int bookId) throws BookNotFoundException {
		// TODO Auto-generated method stub
		Statement statement = null;
		Statement statement2 = null;
		Connection connection = ModelDAO.openConnection();
		String query = "select BookID from online_book_details";
		Book k = null;
		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				int cat = rs.getInt("BookID");
				if (cat == bookId) {
					statement2 = connection.createStatement();
					String query2 = "select * from online_book_details where BookID = '" + bookId + "' ";
					ResultSet rs1 = statement2.executeQuery(query2);
					while (rs1.next()) {
						int bookId1 = rs1.getInt(1);
						String title = rs1.getString(2);
						String category = rs1.getString(3);
						String author = rs1.getString(4);
						int price = rs1.getInt(5);
						k = new Book(title, author, category, bookId1, price);
						k.setAuthor(author);
						k.setBookId(bookId1);
						k.setCategory(category);
						k.setPrice(price);
						k.setTitle(title);
						return k;
					}
				}
			}
			if (k == null) {
				throw new BookNotFoundException("Book not found");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public boolean updateBook(int bookId, int price) throws BookNotFoundException {
		PreparedStatement statement = null;
		Connection connection = ModelDAO.openConnection();
		Statement statement1 = null;
		int cat = 0;
		try {
			statement1 = connection.createStatement();
			ResultSet rs = statement1.executeQuery("select BookID from online_book_details");
			while (rs.next()) {
				cat = rs.getInt("BookID");
				if (cat == bookId) {
					statement = connection
							.prepareStatement("update online_book_details set price = ? where BookID = ?");
					statement.setDouble(1, bookId);
					statement.setInt(2, price);
					statement.execute();
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void getAllBooks() {
		Statement statement = null;
		Connection connection = ModelDAO.openConnection();
		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from online_book_details");
			while (rs.next()) {
				int bookId = rs.getInt(1);
				String title = rs.getString(2);
				String category = rs.getString(3);
				String author = rs.getString(4);
				double price = rs.getDouble(5);
				System.out.println(bookId + " " + title + " " + category + " " + author + " " + price);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Book getBookByAuthor(String author) throws AuthorNotFoundException {
		Statement statement = null;
		Statement statement2 = null;
		Connection connection = ModelDAO.openConnection();
		String query = "select BookAuthor from online_book_details";
		Book k = null;
		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				String cat = rs.getString("BookAuthor");
				if (cat.equalsIgnoreCase(author)) {
					statement2 = connection.createStatement();
					String query2 = "select * from online_book_details where BookAuthor = '" + author + "' ";
					ResultSet rs1 = statement2.executeQuery(query2);
					while (rs1.next()) {
						int bookId = rs1.getInt(1);
						String title = rs1.getString(2);
						String category = rs1.getString(3);
						String author1 = rs1.getString("BookAuthor");
						int price = rs1.getInt(5);
						k = new Book(title, author1, category, bookId, price);
						k.setAuthor(author1);
						k.setBookId(bookId);
						k.setCategory(category);
						k.setPrice(price);
						k.setTitle(title);
						return k;
					}
				}
			}

			if (k == null) {
				throw new AuthorNotFoundException("Author not found");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Book getBookByCategory(String category) throws CategoryNotFoundException {
		Statement statement = null;
		Statement statement2 = null;
		Connection connection = ModelDAO.openConnection();
		String query = "select BookGener from online_book_details";
		Book k = null;
		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				String cat = rs.getString("BookGener");
				if (cat.equalsIgnoreCase(category)) {
					statement2 = connection.createStatement();
					String query2 = "select * from online_book_details where BookGener = '" + category + "' ";
					ResultSet rs1 = statement2.executeQuery(query2);
					while (rs1.next()) {
						int bookId = rs1.getInt(1);
						String title = rs1.getString(2);
						String category1 = rs1.getString("BookGener");
						String author = rs1.getString(4);
						int price = rs1.getInt(5);
						k = new Book(title, author, category1, bookId, price);
						k.setAuthor(author);
						k.setBookId(bookId);
						k.setCategory(category1);
						k.setPrice(price);
						k.setTitle(title);
						return k;
					}
				}
			}

			if (k == null) {
				throw new CategoryNotFoundException("Category not found");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}
