package com.twu.biblioteca;

/**
 * Created by boruch on 15/7/17.
 */
public class Book {
    private String name;
    private String author;
    private  int yearPublished;
    private boolean onLoan = false;
    private UserAccount loanedUser;

    public Book(String name, String author, int yearPublished) {
        this.name = name;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public boolean isOnLoan() {
        return onLoan;
    }

    public void setOnLoan(boolean onLoan) {
        this.onLoan = onLoan;
    }

    public void setLoanedUser(UserAccount loanedUser) {
        this.loanedUser = loanedUser;
    }

    public UserAccount getLoanedUser() {
        return loanedUser;
    }
}
