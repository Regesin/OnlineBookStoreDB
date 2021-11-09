package com.bookapp.main;
/**
 * @author SurapaneniArunSai
 *
 */

import java.util.Scanner;

import com.bookapp.bean.Book;
import com.bookapp.dao.BookImpl;
import com.bookapp.dao.BookInter;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;

public class Client {

	public static void main(String[] args) {
		int select = 0, bookid = 0, price = 0;
		String title, author, category,  categoryfind;

		BookInter bookinter = new BookImpl();
		Scanner sc = new Scanner(System.in);

		while (true) { 
			// Running infinite loop to get the Input from user 
			System.out.println("Press 1) To Add Book");
			System.out.println("Press 2) To Display all books");
			System.out.println("Press 3) To Search Book By ID");
			System.out.println("Press 4) To Search Book By Category");
			System.out.println("Press 5) To Search Book By author");
			System.out.println("Press 6) To Update Book Price by ID");
			System.out.println("Press 7) To Delete Book by ID");
			System.out.println("Press 8) To Exit");

			select = sc.nextInt();
			switch (select) {

			case 1:
				// To add Book
				sc.nextLine();
				System.out.println("Enter the Title of Book");
				title = sc.nextLine();
				System.out.println("Enter the Name of the Author");
				author = sc.nextLine();
				System.out.println("Enter the Category of Book");
				category = sc.nextLine();
				System.out.println("Enter the BookId");
				bookid = sc.nextInt();
				System.out.println("Enter the Book Price");
				price = sc.nextInt();
				Book book = new Book(title, author, category, bookid, price);
				bookinter.addBook(book);
				break;
			case 2:
				// To display all Books in Database
				System.out.println();
				System.out.println("All Book in the database ::: ");
				bookinter.getAllBooks();
				break;
			case 3:
				// To Search Book by BookID
				System.out.println("Enter the Book Id to Search");
				sc.nextLine();
				bookid = sc.nextInt();
				try {
					System.out.println(bookinter.getBookById(bookid));
				} catch (BookNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					System.exit(0);
				}
				break;

			case 4:
				// To search Book by Category
				System.out.println("Enter the Category to Search");
				sc.nextLine();
				categoryfind = sc.nextLine();
				try {
					System.out.println(bookinter.getBookByCategory(categoryfind));
				} catch (CategoryNotFoundException e) {
					System.out.println(e.getMessage());
					System.exit(0);

				}
				break;
			case 5:
				// To Search Book by Author of the book
				System.out.println("Enter the Author of the Book to Search");
				sc.nextLine();
				author = sc.nextLine();
				try {
					System.out.println(bookinter.getBookByAuthor(author));
				} catch (AuthorNotFoundException e) {
					System.out.println(e.getMessage());
					System.exit(0);

				}
				break;
			case 6:
				// To update Book price by BookId
				System.out.println("Enter the Book Price that to be updated ");
				sc.nextLine();
				price = sc.nextInt();
				System.out.println("Enter the BookID ");
				bookid = sc.nextInt();
				
				try {
					bookinter.updateBook(bookid, price);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 7:
				// To delete the book from database using BookID
				System.out.println("Enter the BookID to Delete the Book");
				sc.nextLine();
				bookid = sc.nextInt();
				try {
					bookinter.deleteBook(bookid);
				} catch (BookNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				// To exit the infinite loop 
				sc.close();
				System.exit(0);
			}

		}
	}

}
