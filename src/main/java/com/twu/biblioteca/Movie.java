package com.twu.biblioteca;

/**
 * Created by boruch on 17/7/17.
 */
public class Movie {

    private  String name;
    private int year;
    private String director;
    private int rating;
    private boolean onLoan;
    private UserAccount loanedUser;

    public Movie(String name, int year, String director, int rating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getDirector() {
        return director;
    }

    public int getRating() {
        return rating;
    }

    public int getYear() {

        return year;
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
}
