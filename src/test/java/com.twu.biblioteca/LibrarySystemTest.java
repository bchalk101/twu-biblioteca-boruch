package com.twu.biblioteca;

import com.twu.biblioteca.Book;
import com.twu.biblioteca.LibrarySystem;
import com.twu.biblioteca.Movie;
import com.twu.biblioteca.UserAccount;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by boruch on 20/7/17.
 */
public class LibrarySystemTest {

    private List<Book> bookList = new ArrayList<Book>();
    private List<Movie> movieList = new ArrayList<Movie>();
    private List<UserAccount> allUsers = new ArrayList<UserAccount>();

    private LibrarySystem lbSystem = new LibrarySystem(this.bookList, this.movieList, this.allUsers);


    ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void initialize()
    {
        lbSystem.init();
        System.setOut(new java.io.PrintStream(out));
    }





    @Test
    public void displayListOfBooks(){

        Book book1 = new Book("Test-Driven Development", "Kent Beck", 2003);
        assertEquals(lbSystem.printBookName(book1), book1.getName());

    }

    @Test
    public void displayDetailsOfBooks()
    {
        ArrayList<Book> bookList = new ArrayList<Book>();
        Book book1 = new Book("Test-Driven Development", "Kent Beck", 2003);
        bookList.add(new Book("Harry Potter and the Philosopher's Stone", "J.K Rowling", 2001));
        bookList.add(new Book("Test-Driven Development", "Kent Beck", 2003));
        assertEquals(lbSystem.printBookDetails(book1), book1.getAuthor() + " " + book1.getYearPublished());
    }



    @Test
    public void bookRemovedFromListWhenCheckedOut()
    {
        lbSystem.loginUser("002-0002", "password");
        Book book = lbSystem.getBookByName("Test-Driven Development");
        lbSystem.checkOutItem("Test-Driven Development");
        assertEquals(true, book.isOnLoan() );

    }

    @Test
    public void successfulCheckoutMessageDisplayed()
    {
        lbSystem.loginUser("002-0002", "password");
        Book book = lbSystem.getBookByName("Test-Driven Development");
        assertEquals(lbSystem.checkOutItem("Test-Driven Development"), true);
    }

    @Test
    public void unsuccessfulCheckoutMessageDisplayed()
    {
        Book book = lbSystem.getBookByName("Dr. Seuss");
        assertEquals(lbSystem.checkOutItem("Dr. Seuss"), false);
    }

    @Test
    public void returnBook()
    {
        List<Book> allBooks = lbSystem.getBookList();
        Book book = new Book("Test-Driven Development", "Kent Beck", 2003);

        lbSystem.returnBook("Test-Driven Development");
        assertEquals(lbSystem.getBookByName("Test-Driven Development").isOnLoan(), book.isOnLoan());
    }



    @Test
    public void returnBookUnsuccessfull() {
        List allBooks = lbSystem.getBookList();
        Book book = new Book("Test-Driven Development", "Kent Beck", 2003);
        allBooks.add(book);

        assertEquals(lbSystem.returnBook("Test-Driven Development"), "That is not a valid book to return.");
    }

    @Test
    public void printMovieList() throws IOException {
        lbSystem.printAllMovies();
        assertEquals(out.toString(), "Saving Private Ryan\nSteven Spielberg, 1998\n\n");
    }

    @Test
    public void checkOutMovie()
    {
        lbSystem.checkOutMovie("Saving Private Ryan");
        assertEquals(lbSystem.getMovieByName("Saving Private Ryan").isOnLoan(), true);
    }

    @Test
    public void createUserAccountLibrarian()
    {
        UserAccount newUser = new UserAccount("Jack Smith", "Librarian", "001-0001", "password");
        lbSystem.addUser(newUser);
        assertEquals(lbSystem.getUser("Jack Smith"), newUser);
    }

    @Test
    public void loginLibrarian()
    {

        assertEquals(lbSystem.loginUser("001-0001", "password"), true);
    }

    @Test
    public void loginUser()
    {
        lbSystem.loginUser("002-0002", "password");
        assertEquals(lbSystem.getUserByLibraryNumber("002-0002"), lbSystem.getLoggedInUser());
    }

    @Test
    public void requireLoginToLoanBook()
    {
        //lbSystem.loginUser("002-0002", "password");
        lbSystem.checkOutItem("Test-Driven Development");
        assertEquals(false, lbSystem.getBookByName("Test-Driven Development").isOnLoan());
    }

    @Test
    public void loggedInToLoanBook()
    {
        lbSystem.loginUser("002-0002", "password");
        lbSystem.checkOutItem("Test-Driven Development");
        assertEquals(true, lbSystem.getBookByName("Test-Driven Development").isOnLoan());
    }

    @Test
    public void bookAssignedToUserWhenLoaned()
    {
        lbSystem.loginUser("002-0002", "password");
        lbSystem.checkOutItem("Test-Driven Development");
        assertEquals(lbSystem.getLoggedInUser(), lbSystem.getBookByName("Test-Driven Development").getLoanedUser());
    }

    @Test
    public void requiresUserToReturnBook()
    {
        lbSystem.loginUser("002-0002", "password");
        lbSystem.checkOutItem("Test-Driven Development");
        lbSystem.logoutUser();
        lbSystem.returnBook("Test-Driven Development");
        assertEquals(true, lbSystem.getBookByName("Test-Driven Development").isOnLoan());
    }

    @Test
    public void userReturnsBookSuccessful()
    {
        lbSystem.loginUser("002-0002", "password");
        lbSystem.checkOutItem("Test-Driven Development");
        lbSystem.returnBook("Test-Driven Development");
        assertEquals(false, lbSystem.getBookByName("Test-Driven Development").isOnLoan());
    }

    @Test
    public void incorrectUserReturnsBook()
    {
        lbSystem.loginUser("002-0002", "password");
        lbSystem.checkOutItem("Test-Driven Development");
        lbSystem.logoutUser();
        lbSystem.loginUser("001-0001", "password");
        lbSystem.returnBook("Test-Driven Development");
        assertEquals(true, lbSystem.getBookByName("Test-Driven Development").isOnLoan());
    }

    @Test
    public void correctUserReturnsBook()
    {
        lbSystem.loginUser("002-0002", "password");
        lbSystem.checkOutItem("Test-Driven Development");
        lbSystem.returnBook("Test-Driven Development");
        assertEquals(false, lbSystem.getBookByName("Test-Driven Development").isOnLoan());
    }

    @Test
    public void displayUserInfo()
    {
        lbSystem.loginUser("002-0002", "password");
        lbSystem.getLoggedInUser().displayUserInfo();
        assertEquals("Name: " + lbSystem.getLoggedInUser().getUserName()
                + "\nEmail Address: " + lbSystem.getLoggedInUser().getEmailAddress()
                + "\nPhone Number: " + lbSystem.getLoggedInUser().getPhoneNumber() +"\n" ,out.toString());

    }
}
