package com.twu.biblioteca;

/**
 * Created by boruch on 17/7/17.
 */
public class UserAccount {

    private String userName;
    private String typeOfUser;
    private String libraryNumber;
    private String password;
    private String emailAddress;
    private String phoneNumber;

    public UserAccount(String userName, String typeOfUser, String libararyNumber, String password) {
        this.userName = userName;
        this.typeOfUser = typeOfUser;
        this.libraryNumber = libararyNumber;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public String getPassword() {
        return password;
    }

    public void displayUserInfo() {
        System.out.println("Name: " + this.userName);
        System.out.println("Email Address: " + this.emailAddress);
        System.out.println("Phone Number: " + this.phoneNumber);
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
