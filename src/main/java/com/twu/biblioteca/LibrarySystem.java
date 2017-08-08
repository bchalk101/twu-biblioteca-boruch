package com.twu.biblioteca;

import java.util.List;

/**
 * Created by boruch on 16/7/17.
 */
public class LibrarySystem {


    private List<Book> bookList;
    private List<Movie> movieList;
    private List<UserAccount> allUsers;

    private UserAccount loggedInUser;

    public LibrarySystem(List<Book> bookList, List<Movie> movieList, List<UserAccount> allUsers) {
       this.bookList = bookList;
       this.movieList = movieList;
       this.allUsers = allUsers;
    }

    public void init()
    {
        this.bookList.add(new Book("Harry Potter and the Philosopher's Stone", "J.K Rowling", 2001));
        this.bookList.add(new Book("Test-Driven Development", "Kent Beck", 2004));

        addMovie(new Movie("Saving Private Ryan", 1998, "Steven Spielberg", 10));

        UserAccount newUser = new UserAccount("John Smith", "Librarian", "001-0001", "password");
        UserAccount newUser1 = new UserAccount("John Doe", "Borrower", "002-0002", "password");
        addUser(newUser);
        addUser(newUser1);
    }

    public List<Book> getBookList() {
        return this.bookList;
    }

    public  List<Book> printListOfBooks() {
        for(Book book : bookList) {
            this.printBookName(book);
            this.printBookDetails(book);
            System.out.println();
        }

        return this.bookList;

    }

    public String printBookName(Book book)
    {
        System.out.println(book.getName());
        return book.getName();
    }

    public String printBookDetails(Book book) {
        String bookDetails = book.getAuthor() + " " + book.getYearPublished();
        System.out.println(bookDetails);

        return bookDetails;
    }

    public Book getBookByName(String book) {
        for (Book bk: bookList)
        {
            if (bk.getName().equals(book))
            {
                return bk;
            }
        }
        return null;
    }

    public Boolean checkOutItem(String itemName) {

        String success = "Thank you! Enjoy the book";
        String unsuccessful = "That item is not available";

        if (this.loggedInUser == null) {
            System.out.println("Please log in to loan a book");
            return false;
        }

        Book bk = this.getBookByName(itemName);
        if (bk != null) {
            if (!bk.isOnLoan()) {
                bk.setOnLoan(true);
                bk.setLoanedUser(this.loggedInUser);
                System.out.println(success);
                return true;
            }
        } else {
            Movie mv = this.getMovieByName(itemName);
            String successMovie = "Thank you! Enjoy the movie";

            if (mv != null){
                mv.setOnLoan(true);
                mv.setLoanedUser(this.loggedInUser);
                System.out.println(successMovie);
                return true;
            }
        }
        System.out.println(unsuccessful);
        return false;
    }

    public String returnBook(String bookName) {
        String success = "Thank you for returning the book";
        String unsuccessful = "That is not a valid book to return.";

        if (this.loggedInUser == null){
            return unsuccessful;
        }

        Book bk = this.getBookByName(bookName);
        if (bk != null) {
            if (bk.isOnLoan() && this.loggedInUser.equals(bk.getLoanedUser())) {
                bk.setOnLoan(false);
                System.out.println(success);
                return success;
            }
        }
        System.out.println(unsuccessful);
        return unsuccessful;
    }

    public void addMovie(Movie movie1) {
        this.movieList.add(movie1);

    }

    public List<Movie> printAllMovies() {
        for(Movie movie : movieList) {
            this.printMovieName(movie);
            this.printMovieDetails(movie);
            System.out.println();
        }

        return this.movieList;
    }

    public String printMovieName(Movie movie)
    {
        System.out.println(movie.getName());
        return movie.getName();
    }

    public String printMovieDetails(Movie movie) {
        String bookDetails = movie.getDirector() + ", " + movie.getYear();
        System.out.println(bookDetails);

        return bookDetails;
    }

    public List<Movie> getAllMovies() {
        return movieList;
    }

    public void checkOutMovie(String name) {
        Movie movie = this.getMovieByName(name);
        if (!movie.isOnLoan()) movie.setOnLoan(true);
    }

    public Movie getMovieByName(String name) {
        for(Movie _movie : this.movieList)
        {

            if(_movie.getName().equals(name)){
                return _movie;
            }
        }
        return null;
    }

    public void addUser(UserAccount newUser) {
        this.allUsers.add(newUser);
    }

    public UserAccount getUser(String name) {
        for (UserAccount user : allUsers){
            if (user.getUserName().equals(name)) return user;
        }
        return null;
    }

    public boolean loginUser(String libraryNumber, String password) {
        for (UserAccount user: allUsers)
        {

            if (user.getLibraryNumber().equals(libraryNumber) && user.getPassword().equals(password)){
                this.loggedInUser = user;
                return true;
            }
        }
        return false;
    }

    public UserAccount getLoggedInUser() {

        return loggedInUser;
    }

    public UserAccount getUserByLibraryNumber(String libraryNumber) {

        for (UserAccount user : this.allUsers){
            if (user.getLibraryNumber().equals(libraryNumber)) return user;
        }

        return null;
    }

    public void logoutUser() {
        this.loggedInUser = null;
    }
}
